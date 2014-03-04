<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addcategory.jsp' starting page</title>
    
  </head>
  
  <body style="">
    <form action="${pageContext.request.contextPath }/manager/CategoryServlet?method=add" method="post">
    	Category Name:<input type="text" name="name"><br/>
    	Description: <br/><textarea rows="5" cols="30" name="description"></textarea><br/>
    	<input type="submit" value="submit">
    </form>
  </body>
</html>
