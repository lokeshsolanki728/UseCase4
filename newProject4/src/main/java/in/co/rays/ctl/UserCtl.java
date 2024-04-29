package in.co.rays.ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.UserDTO;
import in.co.rays.dto.UserModel;
import in.co.rays.exception.DuplicateRecordException;

@WebServlet("/UserCtl.do")
public class UserCtl extends BaseCtl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(UserCtl.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserCtl doGet");
		UserDTO dto = null;
		UserModel model = new UserModel();
		String id = request.getParameter("id");
		if (id != null) {
			try {
				dto = model.findByPK(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("dto", dto);
		}
		ServletUtility.forword("UserView.jsp", request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String id = req.getParameter("id");

		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String dob = req.getParameter("dob");

		if (firstName.isEmpty()) {
			req.setAttribute("fnv", "FirstName is Required");
		ServletUtility.forword("UserView.jsp", req, resp);
		return;
	}

		if (lastName.isEmpty()) {
			req.setAttribute("lnv", "LastName is Required");
		ServletUtility.forword("UserView.jsp", req, resp);
		return;
	}

		UserModel model = new UserModel();

		UserDTO dto = new UserDTO();

		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setLoginId(loginId);
		dto.setPassword(password);
		dto.setAddress(address);
		try {
			dto.setDate(sdf.parse(dob));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println();
		String op = req.getParameter("operation");
		if ((firstName.isEmpty() && lastName.isEmpty() && loginId.isEmpty() && password.isEmpty() && address.isEmpty())) {
			req.setAttribute("allv", "Please Fill All Filds");
			ServletUtility.forword("UserView.jsp", req, resp);
			return;
		}
		if (op.equalsIgnoreCase("update")) {
			dto.setId(Long.parseLong(id));
			try {
				model.update(dto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("dto", dto);
			req.setAttribute("msg", "User Updated Successfully...!!");
		}
		if (op.equalsIgnoreCase("save")) {
				
						try {
							model.add(dto);
					
				} catch ( Exception e) {
					
					req.setAttribute("dre", "Login Id is Already Exists");
					ServletUtility.forword("UserView.jsp", req, resp);
					return;
					
				}
				
			
			req.setAttribute("msg", "User Added Successfully...!!");
		}
		// req.setAttribute("dto", dto);
		ServletUtility.forword("UserView.jsp", req, resp);
	}
}

