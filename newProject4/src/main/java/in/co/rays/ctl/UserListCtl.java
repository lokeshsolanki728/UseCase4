package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import in.co.rays.dto.UserDTO;
import in.co.rays.dto.UserModel;

@WebServlet("/UserListCtl.do")
public class UserListCtl extends BaseCtl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserListCtl.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pageNo = 1;
		int pageSize = 5;
		
		log.debug("ok ok thanku");
		UserDTO dto = new UserDTO();
		UserModel model = new UserModel();
		List<?> list=null;
		try {
			list = model.search(dto, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("List", list);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageSize", pageSize);
		RequestDispatcher rd = req.getRequestDispatcher("UserListView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pageNo = Integer.parseInt(req.getParameter("pageNo"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = pageSize == 0 ? 5 : pageSize;
		String op = req.getParameter("operation");
		UserModel model = new UserModel();
		String ids[] = req.getParameterValues("ids");
		String fNmae = req.getParameter("firstName");
		UserDTO dto = new UserDTO();
		if (op.equalsIgnoreCase("delete")) {

			if (ids != null) {
				pageNo = 1;
				for (String id : ids) {
					UserDTO ddto = new UserDTO();
					ddto.setId(Integer.parseInt(id));
					try {
						model.delete(ddto);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					req.setAttribute("smsg", "User Deleted Successfully....!!");
				}
			} else {
				req.setAttribute("emsg", "Select Atleast one Record");
			}
		}
		if (op.equalsIgnoreCase("add")) {

			resp.sendRedirect("AddUserCtl.do");
		}

		if (op.equalsIgnoreCase("search")) {
			pageNo = 1;
			dto.setFirstName(fNmae);

		}
		if (op.equalsIgnoreCase("next")) {
			pageNo++;
		}
		if (op.equalsIgnoreCase("previous") && pageNo > 0) {
			pageNo--;
		}

		List<?> list=null;
		try {
			list = model.search(dto, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("List", list);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageSize", pageSize);
		ServletUtility.forword("UserListView.jsp", req, resp);
	}
}
