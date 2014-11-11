<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>权限分配</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<s:actionmessage theme="custom" cssClass="success"/>
	<script language="javascript" type="text/javascript">
		function moveOpt() {// 当点击分配按钮时，获取左右的控件名
			var chks = document.getElementsByTagName('input');//得到所有input
			var toObj = document.form1.sel_childedScene;
			var toObjLength = document.form1.sel_childedScene.length;
			for(var i=0;i <chks.length;i++){
				if(chks[i].name.toLowerCase() == 'check'){//得到所名为check的input
			        if(chks[i].checked){//得到所名为check的input
			        	if (toObjLength > 0) {
							var bool = false;
							for (j = 0; j < toObjLength; j++) {																
								if (chks[i].id == toObj.options[j].value) {
									bool = true;
								}																
							}							
							if (bool == false) {
								toObj.options[toObj.length]=new Option(chks[i].value,chks[i].id);							
								}
						} else {							
							toObj.options[toObj.length]=new Option(chks[i].value,chks[i].id);
						}			        	
			        }
		        }
	      	}
					
		}		
		function del(){
			var toObj = document.form1.sel_childedScene;
			if(toObj.selectedIndex >= 0){
				toObj.remove(toObj.selectedIndex);
			}else{
				alert("请选择您要删除的权限！");
			}
		}
		function delAllOtherOption(){
			var toObj = document.form1.sel_childedScene;
			for (i = 0; i < toObj.length;) {
				toObj.remove(toObj.options[i]);
			}
		}
		function selectAll() {
			var chks = document.getElementsByTagName('input');//得到所有input
			var toObj = document.form1.sel_childedScene;
			for(var i=0;i <chks.length;i++){
				if(chks[i].name.toLowerCase() == 'check'){//得到所名为check的input
			        toObj.options[toObj.length]=new Option(chks[i].value,chks[i].id);
		        }
	      	}			
		}
		function updateAuthorityShare() {
			
			var roleID = jQuery("#roleid").val();
			if(roleID != 0){
				var otherLength = jQuery("#sel_childedScene option").length;
				var nodeID_arr = "";
				for (var i = 0; i < otherLength; i++) {	
					nodeID_arr += jQuery("#sel_childedScene option:eq("+i+")").val() + ",";									
				}
				if(confirm("您确定分配权限么？")){
					window.location.href = "Op_RoleRegith_permission.action?op_RoleInfo.role_id=" + roleID + "&nodeID_arr=" + nodeID_arr+"&post=1";			
				}
			}else{
				alert("请选择角色名称！");
				roleID.focus();
			}		
		}		
		function webRoleInfoByRoleName(roleid) {
			jQuery("#roleid option[value=0]").remove();			
			jQuery.post("Op_RoleInfo_findByroleid.action", {role_id:roleid}, function (objects) {
				var otherUserDisplayName = jQuery("#sel_childedScene");
				otherUserDisplayName.html("");
				jQuery.each(objects, function (i, object) {
					otherUserDisplayName.append("<option id='otherOption' value='" + object.id + "'>" + object.name + "</option>");
				});
			}, "json");
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<form action="#" id="form1" name="form1">
  		 <table>
		  	<tr>
				<td align="" height="250">					
					<jsp:include page="/op_roleregith/menu_tree.jsp"></jsp:include>
					<!--
					<select id="sel_childScene" name="sel_childScene" size="16" multiple="multiple"	style="width: 200px; font-size: 12px; height: 100%;background: white;">
						<c:forEach items="${requestScope.list}" var="roleRegithlist">
							<option value="${roleRegithlist.opSysFun.node_id}">
								${roleRegithlist.opSysFun.node_displayName}
							</option>
						</c:forEach>
					</select>
					 -->
				</td>
				<td nowrap="nowrap">
					<table width="100%" border="0">
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" value=" 部分添加 " style="font: bold;" onclick="moveOpt()" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" value=" 部分删除 "  style="font: bold;" onclick="del()" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" value=" 全部添加 " style="font: bold;" onclick="selectAll()" />
							</td>
						</tr>
						<tr>
								<td align="center">
								<input type="button" value=" 全部删除 " style="font: bold;" onclick="delAllOtherOption()" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" value="确定分配权限" style="font: bold;" onclick="updateAuthorityShare()" />
							</td>
						</tr>
					</table>
				</td>
				<td height="250" align="left">
					用户对应权限<br />
					<select id="roleid" style="width: 200px;" onchange="webRoleInfoByRoleName(this.value)">
						<option selected="selected" value="0">
							请选择角色……
						</option>						
						<c:forEach items="${requestScope.rolelist}" var="rolelist">
									<option id="option" value="${rolelist.role_id}">
										${rolelist.role_name}
									</option>
						</c:forEach>
					</select>
					<br />
					<!-- 
					<jsp:include page="/op_roleregith/to_menu_tree.jsp"></jsp:include>
					 -->
					<select id="sel_childedScene" name="op_RoleRegith.opSysFun.node_id" style="width: 200px; font-size: 12px;background: white;" size="16" multiple="multiple">
						<c:forEach items="${requestScope.roleRegith}" var="roleRegith">
							<option value="${roleRegith.opSysFun.node_id}">${roleRegith.opSysFun.node_displayName}</option>
						</c:forEach>
					</select>
					
				</td>
			</tr>
		</table>
	</form>
  </body>
</html>
