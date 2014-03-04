<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>List Books</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/body.css" type="text/css"/>
  </head>
  
  <body style="text-align: center; margin: 0 auto;">
  		<h2>
  			All Book List
  		</h2>
  		<br/>
  
    	<table frame="border" border="1" width="60%" >
    		<tr>
    			<td>Book Name</td>
    			<td>Author</td>
    			<td>Price</td>
    			<td>Image</td>
    			<td>Description</td>
    			<td>Operation</td>
    		</tr>
    		
    		<c:forEach items="${page.list }" var="book">
    			<tr>
		    		<td>${book.name }</td>
	    			<td>${book.author }</td>
	    			<td>$ ${book.price }</td>
	    			<td>
	    				<a href="${pageContext.request.contextPath }/images/${book.image}">View Image</a>
	    			</td>
	    			<td> ${book.description }</td>
	    			<td>
						<a href="#">Edit</a>
						<a href="#">Delete</a>
					</td>
    			</tr>
    		</c:forEach>   	
    	</table>
    	<br/>
    	
    	<c:forEach begin="${page.startpage }" end="${page.endpage }" var="pagenum">
    		[<a href="${pageContext.request.contextPath }/manager/BookServlet?method=listbook&pagenum=${pagenum}">${pagenum }</a>]
    	</c:forEach>
    	
  </body>
</html>
