<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'orderdetail.jsp' starting page</title>

  </head>
  
  <body>
  
  		<h3>Order Detail</h3>
   		<table frame="border" border="1" width="70%">
   			<tr>
   				<td>Book Name</td>
   				<td>Pirce</td>
   				<td>Quantity</td>
   				<td>SubTotal</td>
   			</tr>
   			
   			<c:forEach items="${order.orderitems }" var="orderitem">
   				<tr>
   					<td>${orderitem.book.name }</td>
   					<td>$${orderitem.book.price }</td>
   					<td>${orderitem.quantity }</td>
   					<td>$${orderitem.price }</td>
   				</tr>
   			</c:forEach>
   			
   			<tr>
   				<td colspan="2">Total Price</td>
   				<td colspan="2">${order.price }</td>
   			</tr>
   		</table>
   		<br/>
   		<h3>User Infromaton</h3>
   		<table frame="border" border="1" width="70%">
   			<tr>
   				<td>Username</td>
   				<td>Phone</td>
   				<td>Address</td>
   				<td>Email</td>
   			</tr>
   			<tr>
   				<td>${order.user.username }</td>
   				<td>${order.user.phone }</td>
   				<td>${order.user.address }</td>
   				<td>${order.user.email }</td>
   			</tr> 		
   		</table>
    
		<a href="${pageContext.request.contextPath }/manager/ConfirmOrderServlet?order_id=${order.id}">Confirm Order</a>
  </body>
</html>
