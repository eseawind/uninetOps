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
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <title>添加平台用户</title>
    <link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript">
		function isValidate() {
			var plausername = document.getElementById("pla_username").value;
			var plauserType = document.getElementById("pla_userType").value;
			var zhengze = /^(0|[1-9]\d*)$/;
			if(plausername == ""){
				window.confirm("平台用户名称不能为空！");
				return false;
			}
			if(plauserType == ""){
				window.confirm("平台用户类型不能为空！");
				return false;
			}
			if(zhengze.test(plauserType)==false){
				window.confirm("平台用户类型为整数，请输入一个整数！");
				return false;
			}	
			return true;
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
		<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
		<a href="javascript:window.location.href='Op_PlantformUser_findAll.action'">平台用户管理</a> 》
		<a href="javascript:window.location.href=window.location.href">添加平台用户</a>	<br/> 
    <form action="Op_PlantformUser_save.action?post=1" method="post">
   	  	<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
   			<tr class="list_head">
				<td colspan="2">&nbsp;</td>
			</tr>
    		<tr>
				<td>平台用户名称</td>
				<td><input type="text" name="op_PlantformUser.pla_username" id="pla_username"/></td>
			</tr>
			<tr>
				<td>平台用户类型</td>
				<td><input type="text" name="op_PlantformUser.pla_userType" id="pla_userType"/></td>
			</tr>
			<tr>
				<td>对应场景</td>
				<td>				
					<select name="op_PlantformUser.opScene.scene_id">
					<c:forEach items="${requestScope.list}" var="scene">
						<option value="${scene.scene_id}">${scene.scene_name}</option>					
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="添 加" onclick="return isValidate()"/>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="返 回" onclick="window.location.href='Op_PlantformUser_findAll.action'"/>
				</td>
			</tr>
    	</table>
    </form>
  </body>
</html>
