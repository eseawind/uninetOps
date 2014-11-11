<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>场景树</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/dtree.css">
	<script type="text/javascript" src="${ctx }/js/dtree.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">
		d=new dTree('d',"${ctx}");
		d.add(0,-1,'运维系统','');
	    $.ajaxSettings.async = false;
		$.getJSON("${ctx}/op_scene_sceneTree.action",{
			random:Math.random()
		},function(list){
			$.each(list,function(i,object){
				if(object.type == "scene"){
					d.add(object.node,object.pnode,object.text,object.url,"","","${ctx}/images/dtree/folder.gif");
				}else{
					d.add(object.node,object.pnode,object.text,object.url,"","","${ctx}/images/dtree/" + object.type + ".gif");
				}
			})		
		});
		document.write(d);	
	</script>
  </head>
  
  <body style="font-size: 12px;">
  
  </body>
</html>
