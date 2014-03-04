<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Order</title>

  </head>
  
  <body>
    	<h3 style="text-align: center;">Order</h3>
  		<br/>
    	<table frame="border" border="1" style="margin: 0 auto;">
    		<tr>
    			<td>Order Number</td>
    			<td>Username</td>
    			<td>Order Time</td>
    			<td>Order State</td>
    			<td>Total Price</td>
    			<td>Operation</td>
    		</tr>
    		
    		<c:forEach items="${orders }" var="o">
    			<tr>
	    			<td>${o.id }</td>
	    			<td>${o.user.username }</td>
	    			<td>${o.ordertime }</td>
	    			<td>${o.state==true?'Check':'Uncheck' }</td>
	    			<td>$${o.price }</td>
	    			<td>
	    				<a href="${pageContext.request.contextPath }/manager/OrderDetailServlet?order_id=${o.id}">Show Detail</a>
						<a href="#">Delete</a>
					</td>
    			</tr>
    		</c:forEach>
    		
    	
    	</table>
  </body>
</html>
