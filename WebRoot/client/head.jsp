<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>Front Page Head</title>
       
  <script type="text/javascript" src="../js/jquery-2.0.3.js"></script>
  <script type="text/javascript">
  	$(function(){
  		$("#register").click(function(){
  			window.parent.body.location.href="${pageContext.request.contextPath}/client/register.jsp";
  		});
  	});
  	
  </script>
  </head>
  
  <body style="text-align: center;">
    	<h1> Zhuwei Bookstore</h1>
    	<br/><br/>
    	<div style=" width: 40%; margin: 0 auto;">
	    	<a href="${pageContext.request.contextPath }/client/IndexServlet?method=getAll" target="body">Show All Books</a>
	    	<a href="${pageContext.request.contextPath }/client/ListCartServlet" target="body">Cart</a>
	    	<a href="${pageContext.request.contextPath }/client/ListUserOrderServlet" target="body">My Order</a>
		</div>
		<div style="position: absolute; right: 10px; top: 80px;">
			<c:if test="${user == null }">
		    	<form action="${pageContext.request.contextPath }/client/LoginServlet" method="post">
		    		Username:<input type="text" name="username" style="width: 100px;">
		    		Password:<input type="password" name="password" style="width: 80px;"><br/>
		    		<input type="submit" value="Login">
		    		<input id="register" type="button" value="Register">
		    	</form>
	    	</c:if>
	    	
	    	<c:if test="${user != null }">
	    		Welcome! ${user.username }<br/>
	    		<a href="${pageContext.request.contextPath }/client/LogoutServlet">Logout</a>
	    	</c:if>
    	</div>
  </body>
</html>
