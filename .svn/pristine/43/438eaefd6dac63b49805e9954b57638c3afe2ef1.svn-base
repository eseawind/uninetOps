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
    <link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
    <title>更新用户角色</title>
    <script language="javascript" type="text/javascript">
		function isValidate() {
			var rolename = document.getElementById("role_name").value;
			var roledesc = document.getElementById("role_desc").value;
			if(rolename == ""){
				window.confirm("角色名称不能为空！");
				return false;
			}
			if(roledesc == ""){
				window.confirm("角色说明不能为空！");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
			<a href="javascript:window.location.href='Op_RoleInfo_findAll.action'">角色信息管理</a> 》 
			<a href="javascript:window.location.href=window.location.href">编辑角色信息</a> <br/>
    <form action="Op_RoleInfo_update.action" method="post">
    	<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="1020">
			<tr class="list_head">
				<td colspan="2">&nbsp;</td>
			</tr>
    		<tr>
				<td>角色名称</td>
				<td>
					<input type="hidden" name="op_RoleInfo.role_id" id="role_name" value="${op_RoleInfo.role_id}" />
					<input type="text" name="op_RoleInfo.role_name" value="${op_RoleInfo.role_name}"/>
				</td>
			</tr>
			<tr>
				<td>角色说明</td>
				<td><textarea name="op_RoleInfo.role_desc" style="width: 400px;height: 50px;" >${op_RoleInfo.role_desc}</textarea></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="更 新" onclick="return isValidate()"/>		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		&nbsp;		
					<input type="reset" value="返 回" onclick="window.location.href='Op_RoleInfo_findAll.action'"/>
				</td>
			</tr>
    	</table>
    </form>
  </body>
</html>
