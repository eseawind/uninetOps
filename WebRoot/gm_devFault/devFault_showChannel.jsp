<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.gm.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'devFault_showChannel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
	<%
		try{
			Gm_Channel gm_Channel = (Gm_Channel)request.getAttribute("gm_Channel");
			out.print("通道名称:" + gm_Channel.getCh_name() + "<br/>");
			out.print("通道编号:" + gm_Channel.getCh_no() + "<br/>");
			String col = gm_Channel.getDev_collectId()!=null? gm_Channel.getDev_collectId().getDev_name():"";
			out.print("采集设备:" + col + "<br/>");
			String type = gm_Channel.getChtype_id()!=null? gm_Channel.getChtype_id().getChtype_displayName():"";
			out.print("应用类型:" + type + "<br/>");
		}catch(Exception e){
			out.print("没有找到指定的数据");
		}
	 %>
  </body>
</html>
