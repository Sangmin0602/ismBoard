package reflection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;

import ism.web.board.servlet.FrontController;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestReflection {

	HashMap<String, HttpServlet> servletMaps = new HashMap<String, HttpServlet>();
	HashMap<String, String> urlMappings = new HashMap<String, String>();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, ServletException, IOException {
		String servletPath = "ism.web.board.servlet.FrontController";
		Class.forName(servletPath);
		
		ClassLoader rootLoader = TestReflection.class.getClassLoader();
		Class servletClass = rootLoader.loadClass(servletPath); // ***.class 파일을 디스크에서 읽어서 클래스정보를 메모리에 올려줌.
		
		String servletName = "controller";
		HttpServlet controller  = (HttpServlet) servletClass.newInstance(); // new FrontController();
		servletMaps.put(servletName, controller);
		
		urlMappings.put(servletName, "/ctrl/*");
		
		servletClass.getAnnotation(WebListener.class);
		controller.service(null, null);
//		new FrontController();
		
		
		
	}

}
