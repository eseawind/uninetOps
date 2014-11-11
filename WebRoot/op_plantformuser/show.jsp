<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.unism.util.JsMessage"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示平台用户</title>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
		<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
		<a href="javascript:window.location.href='Op_PlantformUser_findAll.action'">平台用户管理</a> <br/> 
    	<input type="button" value="添加平台用户" onclick="location.href='Op_PlantformUser_save.action?post=0'"/>
    	<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="1020">
			<tr class="list_head">
				<td style="width: 50px;">序号</td>
				<td style="width: 300px;">平台用户名称</td>				
				<td style="width: 100px;">平台用户类型</td>
				<td style="width: 450px;">场景名称</td>
				<td style="width: 120px;">操作</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="plantformuser" varStatus="n">
			<tr>
				<td>${n.count}</td>
				<td>${plantformuser.pla_username}</td>				
				<td>${plantformuser.pla_userType}</td>
				<td>${plantformuser.opScene.scene_name}</td>				
				<td>
					<a href="Op_PlantformUser_findByplaid.action?op_PlantformUser.pla_id=${plantformuser.pla_id}">编辑</a>
					<a href="Op_PlantformUser_delete.action?op_PlantformUser.pla_id=${plantformuser.pla_id}"
						onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<%=JsMessage.alertRequestAttributeMessage(request,"errMsg")%>
  </body>
</html>
