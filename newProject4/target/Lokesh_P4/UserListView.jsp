<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>UserList</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="UserListCtl.do" method="post">
		<%
			UserDTO dto = new UserDTO();
			List<?> list = (List<?>) request.getAttribute("List");
			Iterator<?> it = list.iterator();
			String emsg = (String) request.getAttribute("emsg");
			String smsg = (String) request.getAttribute("smsg");
			int pageNo = (Integer) request.getAttribute("pageNo");
			int pageSize = (Integer) request.getAttribute("pageSize");
			int index = ((pageNo - 1) * pageSize) + 1;
		%>
		<div align="center">
			<h1>User List</h1>
			<table>
			
				<tr>
					<td>FirstName :<input type="text" name="firstName" class ="form-control" border="1" placeholder="Enter Name For Search"> 
					<input type="submit" name="operation" value="Search">

					</td>
			</table>



			<%
				if (emsg != null) {
			%>

			<h3 style="color: red">
				<%=emsg%>
			</h3>

			<%
				}
			%>


			<%
				if (smsg != null) {
			%>

			<h3 style="color: green">
				<%=smsg%>
			</h3>

			<%
				}
			%>

	<div style="margin-bottom: 20px;" class="table-responsive">
			<table class="table table-bordered table-primary table-hover" border="1" style="width: 100%; border: groove;"
				cellpadding=8px>

				<tr style="background-color: orange">
					<th>Select</th>
					<th>Index</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>LoginId</th>
					<th>Password</th>
					<th>DOb</th>
					<th>Address</th>
					<th>Edit</th>
				</tr>
				<%
					while (it.hasNext()) {
						dto = (UserDTO) it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=dto.getId()%>" <%=dto.getId() == 1 ? "disabled" : ""%>>
					<td><%=index++%></td>
					<td><%=dto.getFirstName()%></td>
					<td><%=dto.getLastName()%></td>
					<td><%=dto.getLoginId()%></td>
					<td><%=dto.getPassword()%></td>
					<td><%=dto.getDate()%></td>
					<td><%=dto.getAddress()%></td>
					<td><a href="UserCtl.do?id=<%=dto.getId()%>" style="text-decoration: none"
						<%if (dto.getId() == 1) {%> onclick="return false;" <%}%>>Edit</a></td>
					<%
						}
					%>
				</tr>
			</table>
</div>
			<table style="width: 100%;">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td align="left"><input type="submit" name="operation"
						value="previous" disabled="disabled"></td>
					<%
						} else {
					%>
					<td align="left"><input type="submit" name="operation"
						value="previous"></td>
					<%
						}
					%>
					<td align="center"><input type="submit" name="operation"
						value="delete"></td>


					<td align="right"><input type="submit" name="operation"
						value="next" <%=pageSize > list.size() ? "disabled" : ""%>></td>

				</tr>
			</table>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</div>
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>