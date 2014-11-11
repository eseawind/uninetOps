<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>设备树</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${ctx }/css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${ctx }/zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
	<script type="text/javascript" src="${ctx }/js/demoTools.js"></script>
	<script type="text/javascript">
		
		var zTree;
		var setting;
		setting = {
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			expandSpeed: "",
			async: true,
			asyncUrl: getUrl,
			callback: {
				rightClick: tt,
				beforeExpand: zTreeBeforeExpand
			}
		};
		function tt(e,treeid,treeNode){
			//alert(treeNode.click);
		}
		
		var zNodes = ${device_tree};
		function getUrl(treeNode) {//alert(11);
	//		var curCount = (treeNode.nodes) ? treeNode.nodes.length : 0;
	//		var getCount = (curCount + perCount) > treeNode.count ? (treeNode.count - curCount) : perCount;
	//		var param = "id="+treeNode.id+"_"+(treeNode.times++) +"&count="+getCount;
			//if(treeNode.type=='scene'){
			//alert(22);
				return "${ctx}/gm_device_loadDeviceTree_beginwithMinScene.action?scene_id="+treeNode.id.substr(2);
			//}
	//		return "asyncData/nodeForHugeData.php?" + param;
		}
		
		function zTreeBeforeExpand(treeId, treeNode) {//alert(treeNode.type);
			if(treeNode.id.substr(0,2)!='e_'){
				if (!treeNode.isAjaxing) {
					startTime = new Date();
					ajaxGetNodes(treeNode, "refresh");
					return false;
				} else {
					alert("zTree 正在下载数据中，请稍后展开节点。。。");
					return false;
				}
			}
			return true;
		}
		
		function ajaxGetNodes(treeNode, reloadType) {//alert("ajaxGetNodes");
			if (reloadType == "refresh") {
				//treeNode.icon = "zTreeStyle/img/loading.gif";
				zTree.updateNode(treeNode);
			}
			zTree.reAsyncChildNodes(treeNode, reloadType);
		}
	</script>
  </head>
  
  <body style="font-size: 12px;">
  <ul id="ul_tree" class="tree"></ul>
	<script type="text/javascript">
		zTree = $("#ul_tree").zTree(setting, zNodes);
	</script>
  </body>
</html>
