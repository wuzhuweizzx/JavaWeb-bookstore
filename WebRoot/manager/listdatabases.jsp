<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>Show all backup databases</title>
  </head>
  
  <body>
    <table frame="border" border="1" width="80%">
    	<tr>
    		<td>Database Name</td>
    		<td>Path</td>
    		<td>Backup Time</td>
    		<td>Operation</td>
    	</tr>
    	
    	<c:forEach items="${databases }" var="db">
    		<tr>
	    		<td>${db.name }</td>
	    		<td>${db.path }</td>
	    		<td>${db.backuptime }</td>
	    		<td>
	    			<a href="${pageContext.request.contextPath }/manager/DatabaseServlet?method=restore&id=${db.id}">Restore</a>
	    			<a href="${pageContext.request.contextPath }/manager/DatabaseServlet?method=delete&id=${db.id}">Delete</a>
	    		</td>
    		</tr>
    	</c:forEach>
    
    </table>
  </body>
</html>
