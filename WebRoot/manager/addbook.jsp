<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Add Book</title>
    
  <link rel="stylesheet" href="../css/body.css" type="text/css"/>
  
  </head>
  
  <body>
  
  	<h2 style="text-align: center;">ADD BOOK FORM</h2>
    <form action="${pageContext.request.contextPath }/manager/BookServlet?method=add" method="post" enctype="multipart/form-data">
    
    	<table frame="border" border="1" width="40%">
	    	<tr>
	    		<td>Bookname</td>
	    		<td>
	    			<input type="text" name="name">
	    		</td>
	    	</tr>
    		<tr>
	    		<td>Author</td>
	    		<td>
	    			<input type="text" name="author">
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>Price</td>
	    		<td>
	    			<input type="text" name="price">
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>Image</td>
	    		<td>
	    			<input type="file" name="file">
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>Description</td>
	    		<td>
	    			<textarea rows="5" cols="20" name="description"></textarea>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>Category</td>
	    		<td>
	    			<select name="category_id">
	    				<c:forEach items="${categories }" var="c">
	    					<option value="${c.id }">${c.name }</option>
	    				</c:forEach>
	    			</select>
	    		</td>
	    	</tr>
	    	<tr >
	    		<td colspan="2" style="text-align: center;">
	    			<input type="reset" value="Reset">
	    			<input type="submit" value="Submit">
	    		
	    		</td>
	    		
	    	</tr>
	    	
    	</table>
    </form>
  </body>
</html>
