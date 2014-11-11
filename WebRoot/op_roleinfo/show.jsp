<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户角色管理</title>
    <link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
		<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
		<s:actionmessage theme="custom" cssClass="success"/>
		<a href="javascript:window.location.href='Op_RoleInfo_findAll.action'">角色信息管理</a> <br/> 
  	<div align="left">
  		<input type="button" value="添加角色" onclick="location.href='Op_RoleInfo_save.action?post=0'"/>
  		<input type="button" value="分配权限" onclick="location.href='Op_RoleRegith_permission.action?post=0'"/>
    	<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="100%">
			<tr class="list_head">
				<td>序号</td>
				<td>角色名称</td>				
				<td>角色说明</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="roleinfo" varStatus="n">
			<tr>
				<td>${n.count}&nbsp;</td>
				<td>${roleinfo.role_name}&nbsp;</td>				
				<td>${roleinfo.role_desc}&nbsp;</td>				
				<td>
					<a href="Op_RoleInfo_edit.action?role_id=${roleinfo.role_id}">编辑</a>
					
					<c:if test="${user.role_id.role_id == 'role-1' }">
						<a href="Op_RoleInfo_delete.action?op_RoleInfo.role_id=${roleinfo.role_id}"
							onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
					</c:if>&nbsp;
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
  </body>
</html>
