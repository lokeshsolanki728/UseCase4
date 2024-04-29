<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Insert title here</title>

  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      
    }

   /*  .container {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      text-align: center;
    } */

    h1 {
      font-size: 3em;
      color: maroon;
      margin-bottom: 20px;
    }

    p {
      font-size: 1.2em;
      color: grey;
      margin-bottom: 30px;
    }
  </style>
</head>
<body>
<form action="WelcomeCtl" method="post">
<%@ include file="Header.jsp"%>
<div class="container" align="center" style="padding-top: 30px" >

  <h1>Welcome to Our Application</h1>
  <p>This is a stylish And Dynamic Application designed using Jsp And Servlet.</p>
  
 </div>


</form>
</body>
<%@ include file="Footer.jsp"%>
</html>