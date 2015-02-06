package ism.web.board.action;

import ism.web.board.BoardContext;
import ism.web.board.action.json.PostingAction;
import ism.web.board.db.dao.IPostingDao;
import ism.web.board.model.PostingVO;
import ism.web.board.model.UserVO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public class NewPost implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IPostingDao postingDao= ctx.getDaoRepository().getPostingDao();
		UserVO userInfo = (UserVO) request.getSession().getAttribute("user");
		PostingVO posting = new PostingVO(request.getParameter("title"),
										request.getParameter("content"),
										userInfo);
		posting = postingDao.insert(posting);
		
		// 2. json 으로 응답 보내주기
		JSONObject json = new JSONObject();
//		if(posting != null) {
			json.put("success", Boolean.TRUE);
			json.put("nextUrl", "/postings/"+posting.getSeq());
//		}
		/*
		 *  { success : true, nextUrl : /postings/39393983999 }
		 */
		
		// int seq = posting.getSeq();
		// nextUrl = /SimpleBoard/post/39388883
		request.setAttribute("json", json.toJSONString());
		return Views.FORWARD("/WEB-INF/jsp/json/json-response.jsp");
	}

}
