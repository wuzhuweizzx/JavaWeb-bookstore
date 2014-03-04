<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align: center;">
  <h1>Register</h1>	
  <br/>
   	<form action="${pageContext.request.contextPath }/client/RegisterServlet" method="post" >
   		Username: <input type="text" name="username"><br/>
   		Password: <input type="password" name="password"><br/>
   		Phone: <input type="text" name="phone"><br/>
   		Address: <input type="text" name="address"><br/>
   		E-mail: <input type="text" name="email"><br/>
   		<input type="reset" value="Resit"> 
   		<input type="submit" value="Submit"><br/>
  	</form>
  </body>
</html>
