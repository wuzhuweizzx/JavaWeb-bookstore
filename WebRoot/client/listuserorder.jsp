<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'listuserorder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <br/><br/>
    <h2>Order</h2>
   	<table frame="border" border="1"  width="90%">
   		<tr>
   			<td>Username</td>
   			<td>Order Time</td>
   			<td>Order State</td>
 			<td>Total Price</td>
 			<td>Operation</td>
   		</tr>
   		
   		<c:forEach var="order" items="${orders}">
   			<tr>
   				<td>${order.user.username }</td>
   				<td>${order.ordertime }</td>
   				<td>${order.state==false?'Uncheck':'Check' }</td>
   				<td>${order.price }</td>
	   			<td>
	   				<a href="${pageContext.request.contextPath }/client/UserOrderDetailServlet?order_id=${order.id }">Show Detail</a>
	   			</td>
   			</tr>
   		</c:forEach>
   	</table>
  </body>
</html>
