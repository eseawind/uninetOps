<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'latestdata.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function echoSceneTree(id,name,no,rank,gtype){
			findLatestdataByScene(id);
			findDev(id);
		}
		
	function findLatestdataByScene(id) {
		var type = $("input[name='type']:checked").val();
		//var devId = $("#dev").val();
		$.ajax( {
			url : "${ctx}/gm_latestdata_findLatestdataByScene.action",
			type : "POST",
			dataType : "json",//xml,html,script,json
			data : {
				id : id,searchType : type
			},
			error : function() {
			},
			success : function(json) {
				var html = "";
				jQuery.each(json, function(i, data) {
					var chNo = data.chNo;
					var origValue = data.origValue;
					var corrValue = data.corrValue;
					var corrUnit = data.corrUnit;
					var recordTime = data.recordTime;
					var recordTime = data.recordTime;
					html += "<tr><td>"+chNo+"&nbsp;</td><td>"+origValue+"&nbsp;</td><td>"+corrValue+"&nbsp;</td><td>"+corrUnit+"&nbsp;</td><td>"+recordTime+"&nbsp;</td><td>"+recordTime+"&nbsp;</td></tr>" 
				})
				//alert(html);
				$("#data").html(html);
			}
		});
	}

	function findDev(id){
		$.ajax( {
			url : "${ctx}/op_scene_findDevByScene.action",
			type : "POST",
			dataType : "json",//xml,html,script,json
			data : {
				sceneId : id
			},
			error : function() {
			},
			success : function(json) {
				var html = "<option value=\"\">请选择……</option>";
				jQuery.each(json, function(i, data) {
					var devId = data.devId;
					var devName = data.devName;
					html += "<option value=\""+devId+"\">"+devName+"</option>";
				})
				$("#dev").html(html);
			}
		});
	}

	function displayDev(){
		document.getElementById("disDev").style.display="";
	}
	function hideDev(){
		document.getElementById("disDev").style.display="none";
	}
	function search(){
		var devId = $("#dev").val();
		findLatestdataByScene(devId);
	}
</script>
	
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<div>
  		<input type="radio" checked="checked" value="scene" name="type" onclick="hideDev()" />按场景查询
  		<input type="radio" value="device" name="type" onclick="displayDev()" />按设备查询
  	</div><P/>
  	<div id="disDev" style="display: none;width: 100%">
  		设备：<select id="dev" style="width: 200px;">
  			
  		</select>
  		<input type="button" value="查询" onclick="search()" />
  	</div><P/>
  	<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="100%">
  		<tr class="list_head">
  			<td>通道编号</td>
  			<td>原始数据</td>
  			<td>处理后数据</td>
  			<td>数据单位</td>
  			<td>采集时间</td>
  			<td>上报时间</td>
  		</tr>
  		<tr id="data"></tr>
  	</table>
    <script type="text/javascript">
			//默认加载
			if(window.parent.right.scene_tree.selectedNode!=null){
				var curr_node_id = this.parent.right.scene_tree.aNodes[window.parent.right.scene_tree.selectedNode].id;
				var scene_id = curr_node_id.substr(2);
				//alert(scene_id);
				$.getJSON("/uninetOps/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
					random:Math.random()
				},function(op_Scene){
					echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
				});		
			}
	</script>
  </body>
</html>
