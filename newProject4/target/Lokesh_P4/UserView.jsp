<%@page import="in.co.rays.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%@ include file="Header.jsp"%>
<body ><div align="center">
	<h3>
		
		<%
			UserDTO dto = (UserDTO) request.getAttribute("dto");
			String msg = (String) request.getAttribute("msg");
			String fnv = (String) request.getAttribute("fnv");
			String lnv = (String) request.getAttribute("lnv");
			String allv = (String) request.getAttribute("allv");
			String dre = (String) request.getAttribute("dre");
		%>
	</h3>
	<form action="UserCtl.do" method="post">
		
			<%
				if (dto != null) {
			%>
			<h1>Edit User</h1>
			<%
				} else {
			%>
			<h1>Add User</h1>
			<%
				}
			%>
			<h3>
			<%
				if (dre != null) {
			%>
			<font color="red"><%=dre%></font>
			<%
				}
			%>

			<%
				if (msg != null) {
			%>
			<font color="green"><%=msg%></font>
			<%
				}
			%>
			<%
				if (allv != null) {
			%>
			<font color="red"><%=allv%></font>
			<%
				}
			%>
			</h3>

			<table>
				
				<tr>
				
					<th>FirstName <span style="color: red">*</span> :
					</th>
					<td bgcolor="grey">
					<input type="hidden" name="id"
					value="<%=(dto != null) ? dto.getId(): ""%>">
					
					<input type="text" name="firstName"
						value="<%=(dto != null) ? dto.getFirstName() : ""%>"></td>
					<%
						if (fnv != null) {
					%>
					<td style="color: red;"><%=fnv%></td>
					<%
						}
					%>
				
				</tr>
				<tr>
					<th>LastName <span style="color: red">*</span> :
					</th>
					<td bgcolor="grey"><input type="text" name="lastName"
						value="<%=(dto != null) ? dto.getLastName() : ""%>"></td>
						<%
						if (lnv != null) {
					%>
					<td style="color: red;"><%=lnv%></td>
					<%
						}
					%>
						</tr>

				<%
					if (dto != null) {
				%>
			
				<tr>
					<th>LoginId <span style="color: red">*</span> :
					</th>
					<td bgcolor="grey"><input type="text" name="loginId" readonly="readonly"
						value="<%=(dto != null) ? dto.getLoginId() : ""%>"></td>
				</tr>

				<%
					} else {
				%>
	
				<tr>
					<th>LoginId <span style="color: red">*</span> :
					</th>
					<td bgcolor="grey"><input type="text" name="loginId"
						value="<%=(dto != null) ? dto.getLoginId() : ""%>"></td>
				</tr>
				
				<%
					}
				%>
				
				<tr>
					<th>Password<span style="color: red">*</span> :
					</th>
					<td bgcolor="grey"><input type="text" name="password"
						value="<%=(dto != null) ? dto.getPassword() : ""%>"></td>
				</tr>
				
				<tr>

					<th>Dob <span style="color: red">*</span> :
					</th>
					<td bgcolor="grey"><input type="<%=(dto != null) ? "text" : "date"%>"
						name="dob" value="<%=(dto != null) ? dto.getDate() : ""%>"></td>
				</tr>
				
				<tr>
					<th>Address<span style="color: red">*</span> :
					</th>
					<td bgcolor="grey"><input type="text" name="address"
						value="<%=(dto != null) ? dto.getAddress() : ""%>"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td bgcolor="grey"><input type="submit" name="operation"
						value="<%=dto != null ? "update" : "save"%>">
				</tr>
			</table>
		
	</form>
	<%@ include file="Footer.jsp"%>
	</div>
</body>
</html>