package ism.web.board.action.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ism.web.board.BoardContext;
import ism.web.board.action.IAction;
import ism.web.board.action.View;
import ism.web.board.db.dao.IPostingDao;
import ism.web.board.model.PostingVO;
import ism.web.board.model.UserVO;

public class PostingAction implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			System.out.println("글쓰기를 합니다");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			//로그인 한사람만 아래프로세스를 진행할 수있다.
			//로그인 검증도 넣어야한다.
			HttpSession session = request.getSession(false); //기본값 true 세션이 없으면 새로만들어서 반환해준다.
			UserVO uservo = (UserVO)session.getAttribute("user");

			IPostingDao dao = ctx.getDaoRepository().getPostingDao();
			PostingVO posting = new PostingVO();
			
			posting.setContent(content);
			posting.setTitle(title);
			posting.setWriter(uservo);
			dao.insert(posting);
			
			
			
		return null;
	}

}
