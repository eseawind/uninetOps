<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${ctx }/zTree/zTreeStyle.css" type="text/css">
  <link rel="stylesheet" href="${ctx }/zTree/demoStyle/demo.css" type="text/css">
  <script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
  <script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
  <script type="text/javascript" src="${ctx }/js/demoData.js"></script>
  <script type="text/javascript" src="${ctx }/js/demoTools.js"></script>
  <SCRIPT LANGUAGE="JavaScript">
  <!--
	var zTree1;
	var setting;
	setting = {
		checkable: true,
		isSimpleData: true,
		treeNodeKey: "id",
		treeNodeParentKey: "pId"
	};

	$(document).ready(function(){
		zTree1 = $("#treeDemo").zTree(setting,simpleNodes);
	}	
  //-->
  </SCRIPT>
 </HEAD>

<BODY>
	<ul id="treeDemo" class="tree"></ul>
</BODY>
</html>
