<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>编辑LED场景关联信息</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
	<script type="text/javascript" src="${ctx }/js/demoTools.js"></script>
	<script type="text/javascript">
		//表单验证
		function checkForm(){
			var led_id = document.getElementById("op_led_scene.led_id.led_id");
			var scene_id = document.getElementById("op_led_scene.scene_id.scene_id");
			var scene_name = document.getElementById("op_led_scene.scene_name");
			var id = document.getElementById("op_led_scene.id");
			
			if(led_id.value=="-1"){
				alert("LED卡号不能为空");
				return;
			}
			if(scene_id.value.length == 0){
				alert("关联的场景不能为空");
				return;
			}
			if(scene_name.value.length == 0){
				alert("场景名称不能为空");
				return;
			}
			$.getJSON("${ctx}/op_led_scene_exist_edit.action?op_led_scene.id="+id.value+"&op_led_scene.led_id.led_id="+led_id.value+"&op_led_scene.scene_id.scene_id="+scene_id.value,{
				random:Math.random()
			},function(exist){
				if(exist==0){
					document.forms[0].submit();
					return;
				}else if(exist==1){
					alert("LED场景关联信息已存在");
					return;
				}else{
					alert("未知错误");
					return;
				}
			});	
			

		}
		
		//下拉树
		var zTree_edit_1205;
		var scenes_edit_1205 = ${scenes};
		var setting_edit_1205 = {
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			fontCss: setFont,
			callback: {
				click: zTreeOnClick
			}			
		};
		
		
		$(document).ready(function(){
			reloadTree();	
			$("body").bind("mousedown",function(event){
				if (!(event.target.id == "menuBtn" || event.target.id == "DropdownMenuBackground" || $(event.target).parents("#DropdownMenuBackground").length>0)) {
					hideMenu();
				}
			});
		});		
		
		function setFont(treeId, treeNode) {
			if (treeNode && treeNode.isParent) {
				return {color: "blue"};
			} else {
				return null;
			}
		}
		
		function showMenu() {
			var sceneObj = $("#sceneSel");
			var sceneOffset = $("#sceneSel").offset();
			$("#DropdownMenuBackground").css({left:sceneOffset.left + "px", top:sceneOffset.top + sceneObj.outerHeight() + "px"}).slideDown("fast");
			
		}
		
		function hideMenu() {
			$("#DropdownMenuBackground").fadeOut("fast");
		}
		
		function zTreeOnClick(event, treeId, treeNode) {
			if (treeNode) {
				var sceneObj = $("#sceneSel");
				var sceneHid = document.getElementById("op_led_scene.scene_id.scene_id");
				sceneObj.attr("value", treeNode.name);
				sceneHid.value = treeNode.id;
				hideMenu();
			}
		}
		
		function reloadTree() {
			hideMenu();
			zTree_edit_1205 = $("#dropdownMenu").zTree(setting_edit_1205, scenes_edit_1205);
			zTree_edit_1205.expandAll(true);			
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
			<a href="op_led_scene_page.action">LED场景关联信息管理</a> 》
			<a href="#">编辑LED场景关联信息</a><br>	
		<form action="${ctx }/op_led_scene_edit.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&hid_condition=${hid_condition}&hid_value=${hid_value}" method="post">       		
    		<input id="op_led_scene.id" name="op_led_scene.id" value="${op_led_scene.id }" type="hidden"/>
    		<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
    			<tr class="list_head">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>*LED卡号</td>
					<td>
						<select id="op_led_scene.led_id.led_id" name="op_led_scene.led_id.led_id" style="width: 200px;">					
						<option value="-1">-- 请选择 --</option>
						<c:forEach items="${requestScope.led_list}" var="op_led">
							<c:choose>
								<c:when test="${op_led.led_id==op_led_scene.led_id.led_id}">
									<option value="${op_led.led_id}" selected="selected">${op_led.led_name}(${op_led.led_no})</option>
								</c:when>
								<c:otherwise>
									<option value="${op_led.led_id}">${op_led.led_name}(${op_led.led_no})</option>
								</c:otherwise>
							</c:choose>												
						</c:forEach>
						</select> 必填
					</td>
				</tr>
				<tr>
					<td>关联的场景</td>
					<td>
						<input type="text" disabled="disabled" id="sceneSel" readonly="readonly" value="${op_led_scene.scene_id.scene_name}" style="width: 200px;"> <a href="javascript:showMenu()">选择</a> 必填
						<input type="hidden" id="op_led_scene.scene_id.scene_id" name="op_led_scene.scene_id.scene_id" value="${op_led_scene.scene_id.scene_id}"/>
					</td>
				</tr>
				<tr>
					<td>场景名称</td>
					<td>
						<input type="text" id="op_led_scene.scene_name" name="op_led_scene.scene_name" value="${op_led_scene.scene_name }" style="width: 200px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="提交" onclick="checkForm()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回" onclick="window.location.href='op_led_scene_page.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&hid_condition=${hid_condition}&hid_value=${hid_value}'">
					</td>
				</tr>
			</table>			
    </form>	
    <div id="DropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto; ">
	<ul id="dropdownMenu" class="tree"></ul>
</div>		
  </body>
</html>
