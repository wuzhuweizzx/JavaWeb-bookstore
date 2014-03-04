<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Cart</title>

  </head>
  
  <body >
  
  		<h3 style="text-align: center;">Your Cart</h3>
  		<br/>
  		<c:if test="${empty(cart.map) }">
  			<h2 style="text-align: center;">Your Cart is empty</h2>
  		</c:if>
  		<c:if test="${!empty(cart.map) }">
	    	<table frame="border" border="1" style="margin: 0 auto;">
	    		<tr>
	    			<td>Book Name</td>
	    			<td>Author</td>
	    			<td>Price(1)</td>
	    			<td>Quantity</td>
	    			<td>Price(N)</td>
	    			<td>Operation</td>
	    		</tr>
	    		
	    		<c:forEach items="${cart.map }" var="me">
	    			<tr>
		    			<td>${me.value.book.name }</td>
		    			<td>${me.value.book.author }</td>
		    			<td>$${me.value.book.price }</td>
		    			<td>${me.value.quantity }</td>
		    			<td>$${me.value.price }</td>
		    			<td>
		    				<a href="#">Delete</a>
		    			</td>
	    			</tr>
	    		</c:forEach>
	    		
	    		<tr>
	    			<td colspan="3">Total Price</td>
	    			<td colspan="3">$${cart.price } </td>
	    		</tr>
	    		<tr>
	    			<td colspan="6">
	    				<a href="${pageContext.request.contextPath }/client/OrderServlet">Order</a>
	    			</td>
	    		</tr>
	    	</table>
    	</c:if>
  </body>
</html>
