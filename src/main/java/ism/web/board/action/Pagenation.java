package ism.web.board.action;

import ism.web.board.BoardContext;
import ism.web.board.db.dao.IPostingDao;
import ism.web.board.model.PostingVO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Pagenation implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("offset 시작");
		int offset = parseSeq (request) ;
		int length = 2;
		IPostingDao dao = ctx.getDaoRepository().getPostingDao();
		List<PostingVO> list = dao.findByRange(offset, length);
		
		if ( list.isEmpty()) {
			return Views.NOT_FOUND();
		} else {
			request.setAttribute("postings", list);
			return Views.FORWARD("/WEB-INF/jsp/list-postings.jsp");
		}
	}

	/**
	 * '/postings/39883' -> 39883 
	 * @param request
	 * @return
	 */
	private int parseSeq(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String[] seq = uri.split("/");
		int n = Integer.parseInt(seq[seq.length-1]);
		return n;
	}

}
