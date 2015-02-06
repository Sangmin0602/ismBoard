package ism.web.board.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class GatewayFilter
 */
/*
@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST }
					, 
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "key", value = "value")
		})
*/
public class GatewayFilter implements Filter {

    private FilterConfig fConfig;

	/**
     * Default constructor. 
     */
    public GatewayFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("GatewayFilter GatewayFilter()");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("GatewayFilter destroy()()");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
//		// pass the request along the filter chain
//		HttpServletRequest req = ( HttpServletRequest) request;
//		HttpServletResponse res = ( HttpServletResponse)response;
//		
//		ServletContext ctx = fConfig.getServletContext();
//		String ctxpath = ctx.getContextPath();
//		String uri = req.getRequestURI();
//		String path = uri.substring(ctxpath.length());
//		
//		System.out.println("gateway filter : " + uri + " " + uri + ", path " + path );
//		if ( path.startsWith("/static") || path.endsWith(".jsp") || path.endsWith(".html") ) {			
//			chain.doFilter(request, response);
//		} else {
//			req.getRequestDispatcher("/ctrl" + path ).forward(req, res);
//			
//		}

		// pass the request along the filter chain
		HttpServletRequest req = ( HttpServletRequest) request;
		HttpServletResponse res = ( HttpServletResponse)response;
		
		ServletContext ctx = fConfig.getServletContext();
		String ctxpath = ctx.getContextPath();
		String uri = req.getRequestURI();
		String path = uri.substring(ctxpath.length()).trim();
		
		System.out.println("GatewayFilter doFilter Start");
		System.out.println("ctxpath=="+ctxpath);
		System.out.println("uri=="+uri);
		System.out.println("path=="+path);
		
		if ( path.endsWith("/")) {
			// TODO 요청 url이 '/' 로 끝나는 경우 제거해줘야함.
			path = path.substring(0, path.length()-1);
			System.out.println("stripped '/' : " + path);
		}
		
		System.out.println("path [" + path + "]");
		
		if ( path.equals("") || path.startsWith("/static") || path.endsWith(".jsp") || path.endsWith(".html") ) {
			chain.doFilter(request, response);  //톰캣의 디폴트 서블릿을호출
		} else {
			req.getRequestDispatcher("/ctrl" + path ).forward(req, res); //web.xml에 있는 커스텀 서블릿실행(FrontController실행)
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("GatewayFilter init Start");
		this.fConfig = fConfig;
		fConfig.getServletContext();	
		
		
	}

}
