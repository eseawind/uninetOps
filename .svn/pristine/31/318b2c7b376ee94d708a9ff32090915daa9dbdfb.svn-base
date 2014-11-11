<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/sty.css">
	<script type="text/javascript" src="${ctx}/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/StringBuffer.js"></script>
	<s:actionmessage theme="custom" cssClass="success"/>
  </head>
  
  <body>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr height="10" style="line-height:10px;"></tr>
  <tr>
    <td>
    	<table width="98%" border="0" cellspacing="0" cellpadding="0" class="list_main">
    		<tr>
    			<td class="title" style="border-right: none;">
    				场景名称：
    			</td>
    			<td align="left" id="sceneName" style="border-right: none;" colspan="7">&nbsp;</td>
    			<td>
    				<input type="button" value="添加" onclick="add();" />
    			</td>
    		</tr>
          <tr>
            <td class="device_name title">控制设备名称</td>
            <td class="mold title">操作类型</td>
            <td class="relation title">条件关系</td>
            <td class="title">是否开启</td>
            <td id="tt" class="Channel_name title" width="200">通道名称(编号)</td>
            <td class="data_processing title" width="120">数据处理方式</td>
            <td class="controlling_condition title" width="80">控制条件</td>
            <td class="trigger_point title" width="100">触发值</td>
            <td class="operation title">操作</td>
          </tr>
			<tbody id="data">
			
			</tbody>
        </table>
    </td>
  </tr>
  <tr height="10" style="line-height:10px;"></tr>
</table>
  </body>
  <script type="text/javascript">
    	var sceneId = "";
	    function echoSceneTree(id,name,no,rank,gtype){
	    	if(gtype != 1 && gtype != 2 && gtype != 3 && gtype != 4 && gtype != 5){
	    		$("#sceneName").html(name);
		    	loadData(id);
		    	sceneId = id;
	    	}
		}
	    
	    function loadData(id){
	    	$("#data").html("");
	    	$.ajax({
	    	    url : "${ctx}/autoCtrlConfig_findBySceneId.action",
	    	    type : "POST",
	    	    async : false,
	    	    dataType : "json",//xml,html,script,json
	    	    data : {sceneId:id},
	    	    success : function(data) {
	    	    	var cnw = $(".Channel_name").css("width");
	    			var dpw = $(".data_processing").css("width");
	    			var ccw = $(".controlling_condition").css("width");
	    			var tpw = $(".trigger_point").css("width");
	    	    	var html= new StringBuffer();
	    	    	$.each(data,function(i,obj){
	    	    		var name = obj.name;
		    	    	var type  = obj.type;
		    	    	var typeStr = ""
		    	    	if(type == 1){
		    	    		typeStr = "开";
		    	    	}else if(type == 2){
		    	    		typeStr = "停";
		    	    	}else if(type == 3){
		    	    		typeStr = "关";
		    	    	}
		    	    	var cond = obj.cond;
		    	    	var id = obj.id;
		    	    	var enable = obj.enable;
		    	    	html.append("<tr><td>");
		    	    	html.append(name);
		    	    	html.append("</td><td>");
		    	    	html.append(typeStr);
		    	    	html.append("</td><td>");
		    	    	html.append(cond);
		    	    	html.append("</td><td>");
		    	    	html.append(enable);
		    	    	html.append("</td><td colspan=\"4\" style=\"border-bottom:none;\">");
		    	    	var channel = obj.chName;
		    	    	var handle = obj.handle;
		    	    	var condition = obj.condition;
		    	    	var trigger = obj.trigger;
		    	    	var channelHtml = new StringBuffer();
		    	    	channelHtml.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"");
		    	    	
		    	    	$.each(channel,function(i,chName){
		    	    		channelHtml.append("<tr><td class=\"Channel_name_width\" width=\""+cnw+"\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"embedded_tab\"><tr><td>");
		    	    		channelHtml.append(channel[i].name);
		    	    		channelHtml.append("</td></tr></table><td class=\"data_processing_width\" width=\""+dpw+"\">");
		    	    		channelHtml.append(handle[i].handle);
		    	    		channelHtml.append("</td><td class=\"controlling_condition_width\" width=\""+ccw+"\">");
		    	    		channelHtml.append(condition[i].condition);
		    	    		channelHtml.append("</td><td class=\"trigger_point_width\" width=\""+tpw+"\" style=\"border-right:none;\">");
		    	    		channelHtml.append(trigger[i].trigger);
		    	    		channelHtml.append("</td></tr>");
		    	    	});
		    	    	channelHtml.append("</table>");
		    	    	html.append(channelHtml.toString());
		    	    	html.append("</td><td>");
		    	    	html.append("<a href=\"${ctx}/autoCtrlConfig_edit.action?accId=");
		    	    	html.append(id);
		    	    	html.append("\">编辑</a>&nbsp;&nbsp;<a href=\"javascript:deleteautoCtrlConfig('");
		    	    	html.append(id);
		    	    	html.append("')\">删除</a>");
		    	    	html.append("</td></tr>");
	    	      	});
	    	    	$("#data").html(html.toString());
	    	    }
	    	});
	    }
	    
	    function deleteautoCtrlConfig(id){
	    	if (confirm("确定要删除吗？")){
	    		window.location.href="${ctx}/autoCtrlConfig_delete.action?accId="+id;
	    	}
	    }
	    
	    function add(){
	    	if(sceneId != ""){
	    		window.location.href="${ctx}/autoCtrlConfig_add.action?sceneId="+sceneId;
	    	}
	    }
	    
	    if(window.parent.right.scene_tree.selectedNode!=null){
			var curr_node_id = this.parent.right.scene_tree.aNodes[window.parent.right.scene_tree.selectedNode].id;
			var scene_id = curr_node_id.substr(2);
			$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
				random:Math.random()
			},function(op_Scene){
				echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
			});		
		}else{
			alert("请选择场景！");
			//$("#addButton").attr("disabled","disabled");
		}
	    
    </script>
</html>
