package ism.web.board.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBoardUtil {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String path = "/postings/399883";
		
		assertFalse ( BoardUtil.isViewPostingUri("/postings") );
		assertTrue  ( BoardUtil.isViewPostingUri("/postings/393989") );
//		assertFalse ( BoardUtil.isViewPostingUri("/postings/3ddkdd33") );
		assertTrue ( BoardUtil.isViewPostingUri("/postings/8") );
	}
	
	@Test
	public void test_regex_with_logs() {
		String [] logs = {
			"[2012-12-11T12:32:21] 입장완료",
			"[2012-12-03T02:12:23] 탈출",
			"[2012-11-11T16:52:04] 로깅",
			"[2011-11-03T02:11:12] 작년"
		};
		
		// 2011년도 로그를 찾는다.
		List<String> byYear2012 = findLogger( logs, "^\\[2011" ) ;
		assertEquals ( 1, byYear2012.size());
		assertEquals ( logs[3], byYear2012.get(0));
		
		// 03일날 발생한 로그만 찾는다.
//		List<String> byDate03 = findLogger( logs, "^\\[\\d\\d\\d\\d-\\d\\d-03");
		List<String> byDate03 = findLogger( logs, "^\\[[\\d]{4}-[\\d]{2}-03");
		assertEquals ( 2, byDate03.size());
		assertEquals ( logs[1], byDate03.get(0));
		assertEquals ( logs[3], byDate03.get(1));
		
	}
	
	private List<String> findLogger(String[] logs, String regex) {
		Pattern p = Pattern.compile(regex);
		
		ArrayList<String> retList = new ArrayList<>();
		
		for( String log : logs ) {
			Matcher m = p.matcher(log);
			if ( m.find()) {
				retList.add(log);
			}
		}
		
		return retList;
	}

	

	
	

	
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
	public static boolean isViewPostingUri( String path ) {
		String [] parts = path.split("/"); // { "", "postings", "393939" }
		
		// uri의 갯수가 2여야 함( +1은 empty string 추가된 것)
		if ( parts.length != 3) {
			return false;
		}
		
		if ( ! "postings".equals(parts[1])) {
			return false;
		}
		
		try {
			Integer.parseInt(parts[2]);
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
}
