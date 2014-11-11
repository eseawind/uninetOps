<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main-new.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<frameset cols="*" rows="91,*,18" id="frame_main"  border="0" frameborder="no" framespacing="0" >
    <frame src="${ctx }/main/top.jsp" noresize="noresize" name="header" frameborder="no" marginheight="0" marginwidth="0" style="border:none" >
    <frameset id="middleFrame" cols="182,*,182"  border="0" frameborder="no" framespacing="0">
    	<frame src="${ctx }/main/left.jsp" name="left" frameborder="no" marginheight="0" marginwidth="0" style="border:none" />
    	<frame src="${ctx }/devSetup_list.action" id="centerFrame" name="centerFrame" frameborder="no" marginheight="0" marginwidth="0" style="border:none"/>
        <frame src="${ctx }/main/right.jsp" name="right" frameborder="no" marginheight="0" marginwidth="0" style="border:none"/>
    </frameset>
    <frame src="${ctx }/main/bottom.jsp" noresize="noresize" name="bottom" frameborder="no" marginheight="0" marginwidth="0" style="border:none;">
</frameset>
  </head>
  <noframes>
  <body>
  </body>
  </noframes>
</html>
