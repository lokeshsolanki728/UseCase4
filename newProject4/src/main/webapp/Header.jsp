
<%@page import="in.co.rays.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css">

<meta name="viewport" content="width=device-width, initial-scale=1">

<meta charset="ISO-8859-1">
<title>Hello</title>
</head>
<body style="padding-top: 10px;">
	<%
		UserDTO user = (UserDTO) session.getAttribute("user");
	%>
	<table>
		<tr>
			<td width="90%"><a href="WelcomeCtl"><b>Welcome</b></a> | <%
				if (user != null) {
			%> <a href="LoginCtl?operation=logout"><b>Logout</b></a> <%
 	} else {
 %><a href="LoginCtl"><b>Login</b></a> <%
 	}
 %></td>
		</tr>
		<tr>
			<td>
				<%
					if (user != null) {
				%>
				<h3>
					Hii,
					<%=user.getFirstName()%></h3> <a href="UserCtl.do" style="color: green"><b>Add
						User</b></a> | <a href="UserListCtl.do" style="color: green"><b>User
						List</b></a> <%
 	} else {
 %>
				<h3>Hi, Guest</h3> <%
 	}
 %>
			</td>
		</tr>
	</table>
	<hr width="100%" size="7" color="orange">
</body>
</html>