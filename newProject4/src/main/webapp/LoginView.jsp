<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login View</title>
</head>
<body>
<%@ include file="Header.jsp"%>
<form action="LoginCtl" method="post">
<div align="center">
<h1>Login </h1>

<br>
<% String msg1 =(String)request.getAttribute("session");
String uri =(String)request.getAttribute("uri");
String msg = (String)request.getAttribute("msg");
String lmsg = (String)request.getAttribute("lmsg");
String loginValidation = (String)request.getAttribute("loginValidation");
String passValidation = (String)request.getAttribute("passValidation");

if(msg!=null){
%>
<h3 style="color:red"><%=msg %></h3>
<%} %>
<% if(msg1!=null){
%>
<h3 style="color:red"><%=msg1 %></h3>
<%} %>
<% if(lmsg!=null){
%>
<h3 style="color:red"><%=lmsg %></h3>
<%} %>

<input type="hidden" name="uri" value="<%= uri%>">
<table><tr>
<th>LoginId :<span style="color:red">*</span></th>
<td  bgcolor="grey"><input type="text" name ="login"  placeholder= "Enter LoginId"></td>
<%if(loginValidation!=null){ %>
<td style="color:red"><%=loginValidation%></td>
<%} %>
</tr>
<tr>
<th>Password :<span style="color:red">*</span></th>
<td bgcolor="grey"><input type="password" name="password" placeholder="Enter password"></td>
<%if(passValidation!=null){ %>
<td style="color:red"><%=passValidation%></td>
<%} %>
</tr>
<tr>
<th></th>
<td><input type="submit" name="operation" value="Login">

<input type="submit" name="operation" value="Resat"></td>
</tr>
</table>
</div>
</form>

<%@ include file="Footer.jsp"%>
</body>
</html>