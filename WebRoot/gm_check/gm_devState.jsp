<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gm_devState.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${ctx }/css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table cellpadding="0" cellspacing="0" class="senfe1" align="center">
    	<tr>
    		<td width="100px">设备地址：</td>
    		<td colspan="3">${requestScope.gm_Devsts.dev_addr }&nbsp;</td>
    	</tr>
    	<tr>
    		<td>最近通讯时间：</td>
    		<td width="200px"><fmt:formatDate value="${requestScope.gm_Devsts.dest_lastCommTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
    		<td width="100px">设备当前时间：</td>
    		<td width="200px"><fmt:formatDate value="${requestScope.gm_Devsts.dest_curTime }" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
    	</tr>
    	<tr>
    		<td>运行状态：</td>
    		<td colspan="3">
    			<c:choose>
    				<c:when test="${requestScope.gm_Devsts.dest_workSts == 0 }">离线</c:when>
    				<c:when test="${requestScope.gm_Devsts.dest_workSts == 1 }">在线</c:when>
    				<c:when test="${requestScope.gm_Devsts.dest_workSts == 2 }">网关小无线模块故障</c:when>
    				<c:when test="${requestScope.gm_Devsts.dest_workSts == 3 }">小无线能量故障</c:when>
    				<c:when test="${requestScope.gm_Devsts.dest_workSts == 4 }">小无线通讯故障</c:when>
    				<c:when test="${requestScope.gm_Devsts.dest_workSts == 5 }">传感器故障</c:when>
    				<c:when test="${requestScope.gm_Devsts.dest_workSts == 6 }">传感器超量程</c:when>
    				<c:when test="${requestScope.gm_Devsts.dest_workSts == 7 }">传感器超变化率</c:when>
    				<c:otherwise>
    					离线
    				</c:otherwise>
    			</c:choose>&nbsp;
    		</td>
    	</tr>
    	<tr>
    		<td>详细信息：</td>
    		<td colspan="3">${requestScope.gm_Devsts.dest_workStsInfo }&nbsp;</td>
    	</tr>
    	<tr>
    		<td>建议维护操作：</td>
    		<td colspan="3">${maintenanceOperation }&nbsp;</td>
    	</tr>
    </table>
  </body>
</html>
