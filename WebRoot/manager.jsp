<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>End Page</title>
    
  </head>
  
  <frameset rows="25%,*">
  		<frame src="${pageContext.request.contextPath }/manager/head.jsp" name="head">	
  		<frameset cols="15%,*">
  			<frame src="${pageContext.request.contextPath }/manager/left.jsp" name="left">
  			<frame src="${pageContext.request.contextPath }/manager/body.jsp" name="body">
  		</frameset>
  </frameset>
</html>
