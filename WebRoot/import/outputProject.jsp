<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>用户场景关联信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Chomo" />
	<link href="${ctx }/css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${ctx }/zTree/zTreeStyle.css" type="text/css"/>
	<style type="text/css">
.bg{position:absolute;z-index:999;filter:alpha(opacity=50);background:#666;opacity: 0.5;-moz-opacity: 0.5;left:0;top:0;height:100%;width:100%;text-align: center; }


	</style>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
	<script type="text/javascript" src="${ctx }/js/demoTools.js"></script>
	<script type="text/javascript">
		//响应设备树
		function echoDeviceTree(id,addr,no,type){			
			
		}
		
		//构建树
		function buildTree(){
			document.getElementById('ul_tree').style.display = 'none';
			document.getElementById('d1').style.width = window.screen.availWidth+"px"; 
			document.getElementById('d1').style.height = window.screen.availHeight+"px"; 		
			document.getElementById('d1').style.display = 'block';
			document.getElementById('l1').style.display = 'block';
			$.getJSON("${ctx}/op_userinfo_scene_loadTree.action",{
					random:Math.random()
				},function(zNodes){
					zTree = $("#ul_tree").zTree(setting, zNodes);
					document.getElementById('d1').style.display = 'none';
					document.getElementById('l1').style.display = 'none';
					document.getElementById('ul_tree').style.display = 'block';
				}
			);		
		}
		
		//表单提交
		function doSubmit(){
			if(!confirm('确定导出?')){
				return;
			}
			var node_id_list = "-1,";
			var checkedNodes = zTree.getCheckedNodes();
			for(var i=0;i<checkedNodes.length;i++){
				var checkedNode = checkedNodes[i];
				if(checkedNode!=null){
					node_id_list += checkedNode.id+",";
				}
			}
			node_id_list = node_id_list.substr(0,(node_id_list.length-1));
			document.getElementById("scene_id_arr").value = node_id_list;
			document.forms[0].submit();	
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
	<form action="${ctx }/projectExcel_daochuProjectExcel.action" method="post">
		<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》
		<a href="javascript:window.location.href='${ctx }/import/importProject.jsp'">项目信息导入导出</a> 》		
		<a href="#">导出</a><br />	
		<div class="fixed">
			<input id="scene_id_arr" name="scene_id_arr" type="hidden"/>
			<input id="btn_baocun" type="button" value="导出" onclick="doSubmit();"/>
			<input type="button" value="展开" onclick="operation(true);" >
  			<input type="button" value="关闭" onclick="operation(false);" > 
		</div>	  
		<div class="wrapper">
			<div class="body">
				<label id="l1" style="display: none;">正在更新场景树,请等待...</label>
			    <ul id="ul_tree" class="tree"></ul>	
			    <div id="d1" style="top: 60px; left: 0; position: absolute; z-index: 1000; " class="bg" style="display:none;">&nbsp;</div>
			</div>
		</div>
	</form> 	
	<script type="text/javascript">
		var zTree;
		var setting;
		setting = {
			isSimpleData : true,
			treeNodeKey : "id",
			treeNodeParentKey : "pId",
			checkable : true,
			checkType : { "Y": "ps", "N": "s" }
		};
		buildTree();
		function operation(b){
			if(b){
				zTree.expandAll(true);
			}else{
				zTree.expandAll(false);
			}
		}
	</script>
	</body>
</html>
