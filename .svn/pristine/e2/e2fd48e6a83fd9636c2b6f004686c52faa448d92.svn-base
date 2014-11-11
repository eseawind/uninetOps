<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据汇总（新）</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/StringBuffer.js"></script>
	<script type="text/javascript">
		var ids = "";
		function echoSceneTree(id,name,no,rank,gtype){
			getChildren();
			if(ids != ""){
				ids = ids.substring(0,ids.length-1);
			}
			//alert(ids);
			//window.location.href="${ctx}/op_chtypeoperate_dataCollect.action?scene_id="+ids;
			
			jQuery.ajax({
				url:"${ctx}/op_chtypeoperate_dataCollect.action",
				type : "POST",
				dataType : "json",//xml,html,script,json
				data : {scene_id:ids},
			    error : function() {},
			    success : function(data) {
			    	var htmlBuffer = new StringBuffer();
			    	jQuery.each(data,function(i,list){
				    	var name = list.sceneName;
				    	var value = list.value;
				    	var valArr = value.split(",");
				    	if(i==0){
				    		htmlBuffer.append("<tr bgcolor='#d0dffa'><td>");
				    	}else{
				    		htmlBuffer.append("<tr><td>");
				    	}
				    	htmlBuffer.append(name);
				    	htmlBuffer.append("</td>");
				    	for(var i=0;i < valArr.length;i++){
				    	 	htmlBuffer.append("<td>");
				    	 	htmlBuffer.append(valArr[i]);
				    	 	htmlBuffer.append("</td>");
				    	}
				    	htmlBuffer.append("<tr>");
			    	});
			    	var html = htmlBuffer.toString();
			    	if(html == ""){
			    		html="<tr bgcolor=\"#c1fcc4\"><td>没有查到数据……</td></tr>";
			    	}
			    	jQuery("#data").html(html);
			    	
			    }
			});
			
		}
	</script>
	<style type="text/css">
		.tableCss{
			border-top: 1px solid #bbd1fa;
			border-left: 1px solid #bbd1fa;
			text-align: center;
			width: 100%;
			font-size: 12px;
		}
		.tableCss td{
			border-right: 1px solid #bbd1fa;
			border-bottom: 1px solid #bbd1fa;
			height: 28px;
		}
	</style>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<table id="data" class="tableCss" border="0" cellpadding="0" cellspacing="0">
  		<tr bgcolor="#c1fcc4">
  			<td id="message">请选择场景……</td>
  		</tr>
  	</table>
  </body>
</html>
	<script type="text/javascript">
		//默认加载
		if(window.parent.right.scene_tree.selectedNode!=null){
			var curr_node_id = this.parent.right.scene_tree.aNodes[window.parent.right.scene_tree.selectedNode].id;
			var scene_id = curr_node_id.substr(2);
			//alert(scene_id);
			$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
				random:Math.random()
			},function(op_Scene){
				echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
			});		
		}
		
		
		
		function getChildren() {
			var d = window.parent.right.scene_tree;
			var str = "";
			//alert(d.aNodes[d.selectedNode]._ls);
			//if(d.aNodes[d.selectedNode]._ls){
				str+=d.aNodes[d.selectedNode].id.substr(2)+",";
			//}
	    	for (var n=0; n<d.aNodes.length; n++) {
		        if(d.aNodes[n].id == d.aNodes[d.selectedNode].id){
		          getAllChildren(d.aNodes[n]);
		        }
		    }
		    function getAllChildren(node){
	      		for (var n=0; n<d.aNodes.length; n++) {
			        if (d.aNodes[n].pid == node.id) {
			        	//if(d.aNodes[n]._ls){
			        		str+=d.aNodes[n].id.substr(2)+",";
			        	//}
			            getAllChildren(d.aNodes[n]);		
			        }
				}
    		}
    		ids = str;
      }
	</script>
