package ism.web.board.servlet;

import ism.web.board.BoardContext;
import ism.web.board.action.IAction;
import ism.web.board.action.ListPostingAction;
import ism.web.board.action.ListUserAction;
import ism.web.board.action.NewPost;
import ism.web.board.action.Pagenation;
import ism.web.board.action.UsderDeleteAction;
import ism.web.board.action.UserLogin;
import ism.web.board.action.View;
import ism.web.board.action.ViewPosting;
import ism.web.board.action.Views;
import ism.web.board.action.json.JoinAction;
import ism.web.board.action.json.PostingAction;
import ism.web.board.db.dao.IUserDao;
import ism.web.board.model.UserVO;
import ism.web.board.util.BoardUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FrontController
 */
/*
@WebServlet(
		urlPatterns = { "*.board" }, 
		initParams = { 
				@WebInitParam(name = "key", value = "value", description = "test params")
		})
*/
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        System.out.println("FrontController");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
    // path 얻어내는 함수를 빼냈음.
    private String parsePath ( HttpServletRequest request ) {
         String ctrl = "/ctrl";
         String ctxpath = this.getServletContext().getContextPath();
         String uri = request.getRequestURI();
         String path = uri.substring(ctxpath.length() + ctrl.length());

         return path ;
     }
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardContext bctx = (BoardContext) this.getServletContext().getAttribute("board.context");
		System.out.println(request.getRequestURI() + " : psize " + bctx.getPageSize());
		
//		String ctxpath = this.getServletContext().getContextPath();
//		String uri = request.getRequestURI();
//		String path = uri.substring(ctxpath.length());
		String path = parsePath(request);
		System.out.println("PATH : " + path);
		
		BoardContext boardCtx  = (BoardContext) request.getServletContext().getAttribute("board.context");
		
		View view = null;
		
		if ( path.endsWith("/users") ) {
			System.out.println("사용자 목록 출력하는 요청");
			IAction action = new ListUserAction();
			view = action.process(boardCtx, request, response);
		} else if ( path.endsWith("/users/delete")) {
			IAction action = new UsderDeleteAction();
			view = action.process(boardCtx, request, response); 
		} else if ( path.endsWith("/login")) {
//			view = new UserLogin().process(boardCtx, request, response); 
			view = Views.FORWARD("/WEB-INF/jsp/login.jsp");
		} else if ( path.equals ( "/login.json")) {
			view = new UserLogin().process(boardCtx, request, response); 
		} else if(path.equals("/write")) {	
			view = Views.FORWARD("/WEB-INF/jsp/writing.jsp"); 
		} else if ( path.equals("/write.json")) {
			view = new NewPost().process(boardCtx, request, response);
		}
		
		else if (path.endsWith("/postings")) {
			view = new ListPostingAction().process(boardCtx, request, response);
		} else if ( path.equals("/join")) {
			view = Views.FORWARD("/WEB-INF/jsp/new-member.jsp");
		} else if ( path.equals("/join.json")) {
			view = new JoinAction().process( boardCtx, request, response);
		} else if ( path.equals ("/posting/write")) {
			view = Views.FORWARD("/WEB-INF/jsp/posting-write.jsp");
		} else if (path.equals("/posting/write.json")){
			view = new PostingAction().process( boardCtx, request, response);
		} else if ( BoardUtil.isViewPostingUri(path)) {
			view = new ViewPosting().process(boardCtx, request, response);
		} else if ( BoardUtil.isPagenation(path)) {
			// '/postings/page/:number' 
			System.out.println("page시작");
			view = new Pagenation().process(boardCtx, request, response);
		}
		
		if ( view != null) {
			String nextPage = view.getPath();
			System.out.println("nextPage"+nextPage );
			if ( view.isForward() ) {
				request.getRequestDispatcher(nextPage).forward(request, response);
			} else {
				// 리다이렉션
				response.sendRedirect(nextPage); 
			}
		} else {
			System.out.println("알 수 없는 url 입니다. : " + path );
		}
		
//		request.getRequestDispatcher(path).forward(request, response);
		
	}
}
