<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>菜单树</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${ctx }/zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
	<script type="text/javascript" src="${ctx }/js/demoTools.js"></script>
	<script type="text/javascript">
		var menu_tree;
		var setting_menu_tree;
		setting_menu_tree = {
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			expandSpeed: "",
			callback: {
				rightClick: tt
			}
		};
		function tt(e,treeid,treeNode){
			//alert(treeNode.click);
		}
		
		var zNodes_menu_tree = ${menu_tree};
	</script>
  </head>
  
  <body>
      <ul id="ul_menu_tree" class="tree"></ul>
      <script type="text/javascript">
      	//alert($("#ul_menu_tree"));
		menu_tree = $("#ul_menu_tree").zTree(setting_menu_tree, zNodes_menu_tree);
	  </script>
  </body>
</html>
