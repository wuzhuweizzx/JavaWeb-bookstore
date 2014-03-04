<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Front Page Body</title>
    
	
 	<link rel="stylesheet" href="../css/body.css" type="text/css"/>
 </head>
  
  <body>
  		<div id="content" style="width: 1000px; height: 600px; margin: 0 auto; margin-top: 30px; ">
  			<div id="category" style="float: left; margin-left: 200px;">
  				Category(<a href="${pageContext.request.contextPath }/client/IndexServlet?method=getAll">All Books</a>):
  				<ul>
	  				<c:forEach items="${categories }" var="c">
	  					<li>
	  						<a href="${pageContext.request.contextPath }/client/IndexServlet?method=listBookWithCategory&category_id=${c.id}">${c.name }</a>
	  					</li>	
	  				</c:forEach>
  				</ul>
  			</div>
  			
  			<div id="booksandpage" style="float: left;margin: 0;margin-left: 100px; ">
  				<div id="books" >
  					<c:forEach items="${page.list }" var="book">
  						<div id="book" style="margin: 0; ">
  							<div id="iamge" style="margin: 0; padding: 0px;float: left;">
  								<img style="width: 100px; height: 150px; margin: 0; padding: 0;" src="${pageContext.request.contextPath }/images/${book.image}"/>
  							</div>
  							<div id="bookinfo" style="float: left;">
  								<ul>
  									<li>${book.name }</li>
  									<li>${book.author }</li>
  									<li>$${book.price }</li>
  									<li>
  										<a href="${pageContext.request.contextPath }/client/BuyServlet?book_id=${book.id}">Buy</a>
  									</li>
  								</ul>
  							</div>
  							<div style="clear: both; height: 20px;"></div><!--This div use for clear float  -->
  						</div>
  					</c:forEach>
  				</div>
  				
  				<div id="page">
  					<c:forEach begin="${page.startpage }" end="${page.endpage }" var="pagenum">
    					[<a href="${pageContext.request.contextPath }/client/IndexServlet?pagenum=${pagenum}&category_id=${param.category_id}&method=${param.method}">${pagenum }</a>]
    				</c:forEach>
  				</div>
  			
  			</div>
  			
  		</div>
    
  </body>
</html>
