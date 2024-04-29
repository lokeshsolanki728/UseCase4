package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import in.co.rays.dto.UserDTO;
import in.co.rays.dto.UserModel;

@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("operation");
		if (op != null) {
			HttpSession session = req.getSession();
			session.invalidate();
			req.setAttribute("lmsg", "User Logout SuccessFully");
		}
		resp.sendRedirect("LoginView.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("operation");
		String login = req.getParameter("login");
		HttpSession session = req.getSession();
		String uri = req.getParameter("uri");
		String pass = req.getParameter("password");
		UserDTO dto = null;
		UserModel model = new UserModel();

		if (op.equalsIgnoreCase("resat")) {
			ServletUtility.redirect("LoginView.jsp", resp);
			return;
		}
		if (login.isEmpty() && pass.isEmpty()) {
			req.setAttribute("loginValidation", "Login Id is Requird");
			req.setAttribute("passValidation", "Password is Requird");
			ServletUtility.forword("LoginView.jsp", req, resp);
			return;
		}

		if (login.isEmpty()) {
			req.setAttribute("loginValidation", "Login Id is Requird");
			ServletUtility.forword("LoginView.jsp", req, resp);
			return;
		}
		if (pass.isEmpty()) {
			req.setAttribute("passValidation", "Abeee password to dal le");
			ServletUtility.forword("LoginView.jsp", req, resp);
			return;
		}

		if (op.equalsIgnoreCase("login")) {
			try {
				dto = model.authenticate(login, pass);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			if (dto != null) 
				session.setAttribute("user", dto);
				if (uri.equalsIgnoreCase("null")) {
					resp.sendRedirect("WelcomeCtl");
				} else {
					resp.sendRedirect(uri);
				}
			} else {
				req.setAttribute("msg", "Invalid Login Id & password");
				ServletUtility.forword("LoginView.jsp", req, resp);
			}
		}
	
}
