package ism.web.board.action;

import ism.web.board.BoardContext;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsderDeleteAction implements IAction {

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ctx.getDaoRepository().getUserDao().delete(null);
		response.sendRedirect("....");
		
		throw new RuntimeException("아직구현 안했다"); // FIXME 구현 해야함.
		
	}

}
