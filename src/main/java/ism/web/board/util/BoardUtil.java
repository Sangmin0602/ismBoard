package ism.web.board.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoardUtil {

	/**
	 * 주어진 path가 
	 * "/admin/users/today" - 오늘 가입한 사용자들을 보여주는 요청
	 * "/admin/users/탈퇴/tody" -오늘 탈퇴한 사용자들...
	 * 
	 * 
	 * "/postings/:number" 의 형태인지를 판단하는 메소드.
	 * 
	 * 
	 * @param path
	 * @return
	 */
//	else if ( BoardUtil.isPagenation(path)) {
//	// '/postings/page/:number' 
//	view = new Pagenation().process(boardCtx, request, response);
//}
	public static boolean isViewPostingUri ( String path) {
		String regex = "^/postings/\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(path);
		
		return matcher.find();
	}
	
	public static boolean isPagenation ( String path) {
		String regex = "^/postings/page/\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(path);
		
		return matcher.find();
	}
}
