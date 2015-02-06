package ism.web.board.action;

/**
 * Array - : Arrays
 * Collection : Collections
 * View : Views
 * @author sangmin
 *
 */
public class Views {
	/**
	 * 주어진 path 로 포워딩합니다.
	 * @param path
	 * @return
	 */
	public static View FORWARD ( String path ) {
		return new View ( path, false);
	}
	/**
	 * 리다이렉션 응답을 보냅니다.
	 * @param path
	 * @return
	 */
	public static View REDIRECT ( String path) {
		return new View ( path, true);
	}
	
	public static View NOT_FOUND ( ) {
		return new View ( "/WEB-INF/jsp/404.jsp", false);
	}
}
