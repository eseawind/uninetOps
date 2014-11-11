	<input type="button" value="展开" onclick="device_tree.openAll()" class="input_bg" style="width: 83px; float: left; margin-left: 0px;">
	<input type="button" value="关闭" onclick="device_tree.closeAll()" class="input_bg" style="width: 83px;float:left;  margin-left: 0px;">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>农业物联网应用平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${ctx }/css/dtree.css">
	<style type="text/css">
		.input_bg {
			background-image: url(${ctx}/images/button8.gif);
			background-repeat: no-repeat;
			width:60px;
			height:25px;
			background-color: transparent;
			border: #0000FF 1px;
			color:#000000;
			padding:6px;		
		}
	</style>
	<script type="text/javascript" src="${ctx }/js/dtree.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">
		var device_tree=new dTree('device_tree',"${ctx}");
		device_tree.add(0,-1,'农业物联网应用平台','');
	    $.ajaxSettings.async = false;
		$.getJSON("${ctx}/gm_device_deviceTree.action",{
			random:Math.random()
		},function(list){
			$.each(list,function(i,object){
				if(object.type == "scene"){
					device_tree.add(object.node,object.pnode,object.text,object.url,"","","${ctx}/images/dtree/folder.gif");
				}else if(object.type == "channel"){
					device_tree.add(object.node,object.pnode,object.text,object.url,"","","${ctx}/images/dtree/channel.gif");
				}else if(object.type == "devctrl"){
					device_tree.add(object.node,object.pnode,object.text,object.url,"","","${ctx}/images/dtree/devctrl.gif");
				}else{
					device_tree.add(object.node,object.pnode,object.text,object.url,"","","${ctx}/images/dtree/device.gif","${ctx}/images/dtree/device.gif");
				}				
			})		
		});
		document.write(device_tree);
		device_tree.openAll();	
	</script>
  </head>
  
  <body style="font-size: 12px;">

  </body>
</html>
