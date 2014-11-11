<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>加载设置</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="js/ztree/zTreeStyle.css" type="text/css">
		<link rel="stylesheet" href="css/style_shuichan.css" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/style_min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/tooltips.css" />	
		<link rel="stylesheet" href="${ctx}/css/style_shuichan.css" type="text/css" />
			
		<script src="${ctx}/js/rapid-validation/src/prototype_for_validation.js" type="text/javascript"></script>
		<script src="${ctx}/js/rapid-validation/lib/tooltips.js" type="text/javascript"></script>
		<script src="${ctx}/js/rapid-validation/lib/effects.js" type="text/javascript"></script>
		<script src="${ctx}/js/rapid-validation/src/validation_cn.js" type="text/javascript" charset="utf-8"></script>
		<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
	    <script language="javascript" type="text/javascript" src="js/ztree/jquery.ztree-2.6.js"></script>
		<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script>
		
		<script type="text/javascript">
		
			//选择设备所属场景的树
	    	var shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezTree;
			var shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezmNodes = ${scenes};
			var shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeSetting = {
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
					if (!(event.target.id == "menuBtn" || event.target.id == "shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground" || $(event.target).parents("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").length>0)) {
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
				$("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").css({left:sceneOffset.left + "px", top:sceneOffset.top + sceneObj.outerHeight() + "px"}).slideDown("fast");
				
			}
			
			function hideMenu() {
				$("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").fadeOut("fast");
			}
			
			function zTreeOnClick(event, treeId, treeNode) {
				if (treeNode) {
					var sceneObj = $("#sceneSel");
					var sceneHid = document.getElementById("gm_Device.scene_id.scene_id");
					sceneObj.attr("value", treeNode.name);
					sceneHid.value = treeNode.id;
					hideMenu();
				}
			}
			
			function reloadTree() {
				hideMenu();
				shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezTree = $("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenu").zTree(shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeSetting, shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezmNodes);
				shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezTree.expandAll(true);			
			}
			var DG = frameElement.lhgDG;
			//确定按钮及单击事件
			DG.addBtn( 'ok', '确定', ok );
			function ok() 
			{
				if(!tmlvalid.validate())
					return;
				var dev_no = document.getElementById("dev_no").value;
				var scene_id = document.getElementById("gm_Device.scene_id.scene_id").value;
				var sceneSel = document.getElementById("sceneSel").value;				
				DG.curWin.iniTemplateOrder(dev_no,scene_id,sceneSel);
				DG.cancel();
			}
		</script>
	</head>

	<body>
	<font id="tmlform">
		<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
			<tr>
				<td>设备编号:</td>
				<td><input id="dev_no" name="dev_no" class="required validate-number"/></td>
			</tr>
			<tr>
				<td>所属场景:</td>
				<td>
					<input id="sceneSel" name="gm_Device.scene_id.scene_name" readonly="readonly" class="required"/> <a href="#" onclick="showMenu()">选择</a>
  					<input type="hidden" id="gm_Device.scene_id.scene_id" name="gm_Device.scene_id.scene_id" class="required">
				</td>
			</tr>
		</table>
		<div id="shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto; ">
			<ul id="shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenu" class="tree"></ul>
		</div>
	</font>
	<script>
		var tmlvalid = new Validation('tmlform');
	</script>
	</body>
</html>
