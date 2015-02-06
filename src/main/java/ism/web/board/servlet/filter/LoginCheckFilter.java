package ism.web.board.servlet.filter;

import ism.web.board.action.View;
import ism.web.board.action.Views;
import ism.web.board.model.UserVO;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
/*
@WebFilter(
		description = "로그인체크", 
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "Key", value = "Value")
		})
		*/
public class LoginCheckFilter implements Filter {
	FilterConfig fConfig;
    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println(":::::::::::::::Login Check Filter OK");
		// 여기서 로그인 여부를 확인합니다.
		// pass the request along the filter chain
		
		// 앞서 로그인을 하지 않은 사람이 '/write' 으로 접근하는 경우
					// session 이 null 일 수 있고, session이 null이 아니더라도
					// 그 안에 user 는 null 인 상태.
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		UserVO user = getUserInSession(request);
		View view = null;
		if (user == null) {
			System.out.println("로그인정보를 찾을 수 없습니다. 리다이렉트 합니다. ");
			view = moveToLoginPage(request, response);
			response.sendRedirect(view.getPath()); 
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("::: logincheck fiter init");
		this.fConfig = fConfig;
	}
	
    private UserVO getUserInSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // 생성된 session이 없으면 user는 당연히 없음.
        if ( session == null ) {
            return null;
        }
        return (UserVO) session.getAttribute("user");
    }
    
    // path 얻어내는 함수를 빼냈음.
    private String parsePath ( HttpServletRequest request ) {
//         String ctrl = "/ctrl";
         String ctxpath = fConfig.getServletContext().getContextPath();
         String uri = request.getRequestURI();
         String path = uri.substring(ctxpath.length());
         System.out.println("uri:" + uri);
         System.out.println("ctxpath : " + ctxpath);
         return path ;
     }
    
    /**
     * 로그인 페이지로 이동을 시키는 역할을 합니다.
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private View moveToLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String urlToGo = parsePath(request);

        String queryPart = request.getQueryString();
        if ( queryPart != null) {
            urlToGo += "?" + queryPart ;
        }
        urlToGo = URLEncoder.encode(urlToGo, "UTF-8"); /* !important */ //  + '/' => %20

        String login = "/SimpleBoard/login" + "?target=" + urlToGo;
        System.out.println("[LOGIN-REQUIRED]" + login);
        return Views.REDIRECT(login);
    }

}
