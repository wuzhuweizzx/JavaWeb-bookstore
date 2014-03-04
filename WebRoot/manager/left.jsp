<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>End Page Left</title>
  </head>
  
  <body>
	<h4 style="font-style: oblique;">Categories</h4> 
		<a href="${pageContext.request.contextPath }/manager/addcategory.jsp" target="body">Add Category</a><br/>
		<a href="${pageContext.request.contextPath }/manager/CategoryServlet?method=listall" target="body">List Category</a><br/>
	<br/>	 
	<br/>	 
	<h4 style="font-style: oblique;">Books</h4> 
	    <a href="${pageContext.request.contextPath }/manager/BookServlet?method=addbookUI" target="body">Add Book</a><br/>
	    <a href="${pageContext.request.contextPath }/manager/BookServlet?method=listbook" target="body">List Book</a><br/>
	<br/>	 
	<br/>	 
	<h4 style="font-style: oblique;">Orders</h4> 
	<a href="${pageContext.request.contextPath }/manager/ListOrderServlet?state=false" target="body">Uncheck Order</a><br/>
	<a href="${pageContext.request.contextPath }/manager/ListOrderServlet?state=true" target="body">Check Order</a><br/>
	<br/>	 
	<br/>	 
	<h4 style="font-style: oblique;">Database</h4> 
	<a href="${pageContext.request.contextPath }/manager/DatabaseServlet?method=backup" target="body">Backup Database</a><br/>
	<a href="${pageContext.request.contextPath }/manager/DatabaseServlet?method=list" target="body">List Database</a><br/>

  </body>
</html>
