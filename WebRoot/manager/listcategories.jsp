<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>List Categories</title>
    
    
  <link rel="stylesheet" href="${pageContext.request.contextPath }/css/body.css" type="text/css"/>
  
  </head>
  
  <body style="text-align: center; margin: 0 auto;">
  		<h2>
  			All Category List
  		</h2>
  		<br/>
  
    	<table frame="border" border="1" width="60%" >
    		<tr>
    			<td>Category Name</td>
    			<td>Description</td>
    			<td>Operation</td>
    		</tr>
    		
    		<c:forEach items="${categories }" var="c">
    			<tr>
	    			<td>${c.name }</td>
	    			<td>${c.description }</td>
	    			<td>
	    				<a href="#" >Delete</a>
	    				<a href="#" >Edit</a>
	    			</td>
    			</tr>
    		</c:forEach>
    	
    	</table>
  </body>
</html>
