package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebFilter(filterName = "FrontCtl" ,urlPatterns = { "*.do" })
public class FrontCtl implements Filter{
	
	 public static  Logger log = Logger.getLogger(FrontCtl.class);
		
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("fctl started");
	HttpServletRequest req = (HttpServletRequest)request;
	HttpServletResponse resp =(HttpServletResponse)response;

	HttpSession session = req.getSession();
	if(session.getAttribute("user")==null) {
		String uri = req.getRequestURI();
		req.setAttribute("uri", uri);
		req.setAttribute("session", "Your Session Has been Expired...!! please Login Again");
		ServletUtility.forword("LoginView.jsp", req, resp);
	}
	else {
		chain.doFilter(request, response);
	}
	}

	@Override
	public void destroy() {
		
		
	}

}
