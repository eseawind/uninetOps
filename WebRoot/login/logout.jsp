<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
session.invalidate(); 
response.setHeader("cache-control", "no-cache");
%>
<script type="text/javascript">
<!--
window.top.location.replace("<%=basePath %>");
//-->
</script>
