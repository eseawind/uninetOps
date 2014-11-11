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
    
    <title>更新平台用户</title>
    <link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript">
		function isValidate() {
			var plausername = document.getElementById("pla_username").value;
			var plauserType = document.getElementById("pla_userType").value;
			if(plausername == ""){
				window.confirm("平台用户名称不能为空！");
				return false;
			}
			if(plauserType == ""){
				window.confirm("平台用户类型不能为空！");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
		<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
		<a href="javascript:window.location.href='Op_PlantformUser_findAll.action'">平台用户管理</a> 》
		<a href="javascript:window.location.href=window.location.href">编辑平台用户</a>	<br/> 
    	
    <form action="Op_PlantformUser_update.action" method="post">
    	<c:forEach items="${requestScope.list}" var="plantformuser">
   	  	<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
   			<tr class="list_head">
				<td colspan="2">&nbsp;</td>
			</tr>
    		<tr>
				<td>平台用户名称</td>
				<td>
					<input type="hidden" name="op_PlantformUser.pla_id" value="${plantformuser.pla_id}"/>
					<input type="text" name="op_PlantformUser.pla_username" value="${plantformuser.pla_username}"/>
				</td>
			</tr>
			<tr>
				<td>平台用户类型</td>
				<td><input type="text" name="op_PlantformUser.pla_userType" value="${plantformuser.pla_userType}"/></td>
			</tr>
			<tr>
				<td>对应场景</td>
				<td>					
					<select name="op_PlantformUser.opScene.scene_id">
					<c:forEach items="${requestScope.scene}" var="scene">						
						<c:choose>
							<c:when test="${scene.scene_id == plantformuser.opScene.scene_id}">
								<option value="${scene.scene_id}" selected="selected">${scene.scene_name}</option>
							</c:when>
							<c:otherwise>
								<option value="${scene.scene_id}">${scene.scene_name}</option>
							</c:otherwise>
						</c:choose>					
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="更 新" onclick="return isValidate()"/>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
					<input type="reset" value="取 消" onclick="window.location.href='Op_PlantformUser_findAll.action'"/>
				</td>
			</tr>
    	</table>
    	</c:forEach>
    </form>
  </body>
</html>
