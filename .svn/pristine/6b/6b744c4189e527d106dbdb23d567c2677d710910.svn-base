<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <c:forEach items="${requestScope.userinfo}" var="userinfo">
    	欢迎登录&nbsp;&nbsp;&nbsp;${userinfo.role_id.role_name}&nbsp;&nbsp;${userinfo.user_loginName}
    </c:forEach>
    <c:forEach items="${requestScope.sysfun}" var="sysfun">
    	<div><a href="${sysfun.node_url}">${sysfun.node_displayName}</a></div>
    </c:forEach>
  </body>
</html>
