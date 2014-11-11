<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>水产养殖监控管理系统</title>

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
		${message}
		<form action="projectExcel_importProjectExcel.action" method="post" enctype="multipart/form-data">
			导入项目Excel数据
			<input type="file" name="upload" value="">
			<br>
			<input type="submit" value="导入">
		</form>
		
		导出项目数据到Excel<input type="button" value="导出" onclick="javascript:window.location.href='${ctx }/import/outputProject.jsp'">
		<!--
		<form action="projectExcel_daochuProjectExcel.action" method="post">
			导出项目数据到Excel<input type="submit" value="导出">			
		</form>
		<form action="projectExcel_importTidesExcel.action" method="post" enctype="multipart/form-data">
			导入潮汐信息
			<input type="file" name="upload" value="">
			<br>
			<input type="submit" value="提 交">
		</form>			
		-->		
		
	</body>
</html>
