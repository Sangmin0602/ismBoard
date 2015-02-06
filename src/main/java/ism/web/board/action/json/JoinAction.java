package ism.web.board.action.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import ism.web.board.BoardContext;
import ism.web.board.action.IAction;
import ism.web.board.action.View;
import ism.web.board.action.Views;
import ism.web.board.db.dao.IUserDao;
import ism.web.board.model.UserVO;
/**
 * 회원 가입 프로세스를 진행하는 action 클래스입니다.
 * 
 * @author sangmin
 *
 */
public class JoinAction implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// 1. 값을 검증합니다. ( 성가신 작업 )
		verifyUserId ( userid );
		verifyEmail ( email );
		verifyPassword ( password );
		
		IUserDao dao = ctx.getDaoRepository().getUserDao();
		if ( dao.existsUserId ( userid) ) {
			// FIXME 가입 실패 응답을 보내야 함.
			return null ;
		}
		
		// 2. DB에 넣스니다.
		
		UserVO newUser = null ;
		newUser = dao.insert(new UserVO(userid, userid, email, password));
		System.out.println("삽입된 user : " + newUser);
		
		HttpSession session = request.getSession();
		session.setAttribute ("user", newUser);
		
		// 3. json 형식으로 응답을 보냅니다.
		JSONObject json = new JSONObject();
		json.put("success", Boolean.TRUE);
		
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("userid", newUser.getUserId());
		jsonUser.put("email", newUser.getEmail());
		jsonUser.put("password", newUser.getPassword());
		
		json.put("user", jsonUser);
		json.put("href", "/SimpleBoard");
		
//		System.out.println(json.toJSONString());
		
		request.setAttribute("json", json.toJSONString());
		return Views.FORWARD("/WEB-INF/jsp/json/json-response.jsp");
		
//		response.setHeader("Content-Type", "");
//		PrintWriter out = response.getWriter();
//		out.println( json.toJSONString());
//		return null;
	}

	private void verifyPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	private void verifyEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	private void verifyUserId(String userid) {
		// TODO Auto-generated method stub
		
	}

}
