package ism.web.board.action;

import ism.web.board.BoardContext;
import ism.web.board.db.dao.IPostingDao;
import ism.web.board.model.PostingVO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewPosting implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int postingSeq = parseSeq (request) ;
		IPostingDao dao = ctx.getDaoRepository().getPostingDao();
		PostingVO vo = dao.findBySeq(postingSeq);
		
		if ( vo == null ) {
			return Views.NOT_FOUND();
		} else {
			request.setAttribute("posting", vo);
			return Views.FORWARD("/WEB-INF/jsp/viewPosting.jsp");
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
