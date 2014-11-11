<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="userRole" value="${user.role_id.role_id}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Chomo" />
<link rel="start" href="http://www.14px.com" title="Home" />
<title>模拟position:fixed</title>
<style type="text/css">
.bg{position:absolute;z-index:999;filter:alpha(opacity=50);background:#666;opacity: 0.5;-moz-opacity: 0.5;left:0;top:0;height:100%;width:100%;text-align: center; }
</style>
	<link href="${ctx }/css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${ctx }/zTree3.5/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
	<script type="text/javascript" src="${ctx }/zTree3.5/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/zTree3.5/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx }/zTree3.5/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript">
		//构建树
		function buildTree(ele){
			document.getElementById('ul_tree').style.display = 'none';
			if(ele.value!="-1"){
				document.getElementById('d1').style.width = window.screen.availWidth+"px"; 
				document.getElementById('d1').style.height = window.screen.availHeight+"px"; 		
				document.getElementById('d1').style.display = 'block';
				document.getElementById('l1').style.display = 'block';
				/**
				 * 老版本		
				$.getJSON("${ctx}/op_userinfo_scene_loadTree.action?user_id="+ele.value,{
						random:Math.random()
					},function(zNodes){
						alert(zNodes);
						zTree = $.fn.zTree.init($("#ul_tree"), setting, zNodes);
						document.getElementById('d1').style.display = 'none';
						document.getElementById('l1').style.display = 'none';
						document.getElementById('ul_tree').style.display = 'block';
					}
				);		 
				*/
				
				/**
				 * liucl 2012-12-05	
			    */			 
				$.post("${ctx}/op_userinfo_scene_loadTree.action",{user_id:ele.value},function(zNodesJson){
					var zNodes = eval("(" + zNodesJson + ")");
					zTree = $.fn.zTree.init($("#ul_tree"), setting, zNodes);
					document.getElementById('d1').style.display = 'none';
					document.getElementById('l1').style.display = 'none';
					document.getElementById('ul_tree').style.display = 'block';
					document.getElementById('P_checkAll').style.display = 'block';
			    });
			}else{
				document.getElementById('d1').style.display = 'none';
				document.getElementById('l1').style.display = 'none';
			}			
		}
		
		//表单提交
		function doSubmit(){
			var user_id = document.getElementById("sel_user").value;
			if(user_id!="-1"){
				if(!confirm('确定保存')){
					return;
				}
				var node_id_list = "";
				var checkedNodes = zTree.getCheckedNodes();
				for(var i=0;i<checkedNodes.length;i++){
					var checkedNode = checkedNodes[i];
					if(checkedNode!=null){
						node_id_list += checkedNode.id+",";
					}
				}
				if(node_id_list.length>0){
					node_id_list = node_id_list.substr(0,(node_id_list.length-1));
				}
				$.ajax({
				  type:"post",
				  url: "${ctx}/op_userinfo_scene_manage.action?user_id="+user_id,
				  data: {
						random:Math.random(),
						node_id_list: node_id_list
					},
				  success: function(isSuc){
						if(isSuc){
							alert('保存成功，重新登录后生效');
						}else{
							alert('保存关联关系失败，未知错误');
						}
					},
				  dataType: "json"
				});					
			}else{
				alert("请选择用户");
			}			
		}
		
		$(document).ready(function(){
			if("${userRole}" == "role-1"){
				document.getElementById("btn_delScene").style.display=""; 
			}
		});
		
		function delScene(){
			if(confirm('是否确认删除勾选的场景')){
				var scene_id_list = "";
				var checkedNodes = zTree.getCheckedNodes();
				if(!checkedNodes.length>0){
					alert("请勾选需要删除的场景！");
					return;
				}				
				var node_arr = new Array(); 
				var index = 0;
				for(var i=0;i<checkedNodes.length;i++){
					var checkedNode = checkedNodes[i];
					var halfCheck = checkedNode.getCheckStatus();
					if(!halfCheck.half&&checkedNode!=null){
						scene_id_list += checkedNode.id+",";
						node_arr[index] = checkedNode;
						index++;
					}
				}
				if(scene_id_list.length>0){
					scene_id_list = scene_id_list.substr(0,(scene_id_list.length-1));
				}
				$.post("${ctx}/op_scene_op_Scene_del.action",{scene_id_list:scene_id_list},function(message){
					if(message == "1"){
						for(var i=0;i<node_arr.length;i++){
							zTree.removeNode(node_arr[i]);
						}
						zTree.refresh();
						alert("删除成功！");
					}else{
						alert(message);
					}
			    });			    
			}
		}
	</script>
<style type="text/css">
* { padding:0; margin:0;}
body { height:100%; overflow:hidden; font-size:14px; line-height:2; position:relative;}
html { height:100%; overflow:hidden;}
.fixed { position:absolute; width:97%; height:30px; background:#ddd;}
.wrapper { height:93%; overflow:auto;}
.body { padding-top:35px;}
</style>
</head>

<body style="font-size:12px;overflow: auto;padding: 10px;padding-right:0px;text-align: left;">
<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》
<a href="#">用户场景关联信息管理</a> 》
<a href="#">管理</a> <br/>
<div class="fixed">
	<form action="#">
	   	用户信息：<select id="sel_user" onchange="buildTree(this);">
	   				<option value="-1"/>--- 请选择 ---</option>
	   				<c:forEach items="${op_UserInfo_list}" var="op_UserInfo">
	   					<option value="${op_UserInfo.user_id }">${op_UserInfo.user_loginName }-${op_UserInfo.user_name }</option>
	   				</c:forEach>
	   			</select>
	   			<input id="btn_baocun" type="button" value="保 存" onclick="doSubmit();" />	
	   			<input type="button" value="展 开" onclick="operation(true);" />
  				<input type="button" value="关 闭" onclick="operation(false);" />
  				<input id="btn_delScene" type="button" value="删 除" onclick="delScene();" style="display: none"/>			
	</form>
</div>
<div class="wrapper">
	<div class="body">
		<label id="l1" style="display: none;">正在更新场景树,请等待...</label><p id="P_checkAll" style="display: none;"><input id="checkAll" type="checkbox" onclick="checkAll();"/>全选 / 反选</p><ul id="ul_tree" class="ztree"></ul>	
	<div id="d1" style="top: 60px; left: 0; position: absolute; z-index: 1000; " class="bg" style="display:none;">&nbsp;</div>
	
	<script type="text/javascript">
		var zTree;
		var setting = {	
			check: {
				enable: true,
				chkboxType:{Y:'ps',N:'s'}
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		function operation(b){
			if(b){
				zTree.expandAll(true);
			}else{
				zTree.expandAll(false);
			}
		}
		
		function checkAll(){
			var checkAll = document.getElementById("checkAll");
			if(checkAll.checked){
				zTree.checkAllNodes(checkAll.checked);
				zTree.expandAll(true);				
			}else{
				zTree.checkAllNodes(checkAll.checked);	
			}
		}
	</script>
	</div>
</div>
</body>
</html>
