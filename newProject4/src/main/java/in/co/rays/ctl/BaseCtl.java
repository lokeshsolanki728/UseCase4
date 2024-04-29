package in.co.rays.ctl;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;

public class BaseCtl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Boolean validate(HttpServletRequest req) {
		return true;
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BasicConfigurator.configure();
		if(!validate(req)) {
			return;
		}
		super.service(req, resp);
	}
	public void prelode(HttpServletRequest req) {
		
	}
	
	
	
}
