
 <img onclick="scene_tree.openAll()" src="${pageContext.request.contextPath}/images/yunwei_images/open_white.jpg" style="cursor: pointer;width: 70px;" >
 <img onclick="scene_tree.closeAll()" src="${pageContext.request.contextPath}/images/yunwei_images/close_white.jpg" style="cursor: pointer;width: 70px;" >
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
	
	<script type="text/javascript" src="${ctx }/js/dtree.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">
/**
id : int 每个节点都有唯一ID，增加节点时需要手工定义一个ID。 
pid : int 父节点ID，根节点的父节点是-1。 
name : String 节点名称（显示名字） 
url : String 节点URL（鼠标点击跳转地址） 
title : String 鼠标移动到节点上显示的文字 
target : String 页面跳转所在的frame 
icon : String 节点关闭时显示的图标地址 
iconOpen : String 节点打开时显示的图标地址 
_io : boolean 节点是否已打开，默认值false。 
_is : boolean 节点是否被选中，默认值false。 
_ls : boolean 是否是最后一个节点，默认值false。 
_hc : boolean 是否有子节点，默认值false。 
_ai : int 在树的节点数组中的下标(位置)，默认值0。 
_p : Node 父节点对象，默认值null。 
*/		
//		var selectedID = null; //默认选中节点的ID
//		var si = 1; //节点数组下标
		
		var scene_tree=new dTree('scene_tree',"${ctx}");		
		scene_tree.add(0,-1,'农业物联网应用平台','');
		$(document).ready(function(){
			$.each(${sessionScope.scene_tree},function(i,object){
				if(object.type == "scene"){				
					var imgSrc = "${ctx}/images/dtree/folder.gif";
					var target = "";
					//改为根据场景细类判断*/
					if(object.scene_gtype==1){
						imgSrc = "${ctx}/images/dtree/icon_21_0.gif";
					}else if(object.scene_gtype==2){
						imgSrc = "${ctx}/images/dtree/icon_21_1.gif";
					}else if(object.scene_gtype==3){
						imgSrc = "${ctx}/images/dtree/icon_21_2.gif";
					}else if(object.scene_gtype==4){
						imgSrc = "${ctx}/images/dtree/icon_21_3.gif";
					}else if(object.scene_gtype==98){
						imgSrc = "${ctx}/images/dtree/icon_21_4.gif";
					}else if(object.scene_gtype==97){
						imgSrc = "${ctx}/images/dtree/video.gif";
						target = "_brank";
					}else if(object.scene_gtype==101){
						imgSrc = "${ctx}/images/dtree/icon_11_0.gif";
					}
					else if(object.scene_gtype==301 ||object.scene_gtype==302 ||object.scene_gtype==303){
						imgSrc = "${ctx}/images/dtree/301.png";
					}
					else if(object.scene_gtype==201||object.scene_gtype==203){//养殖虾池塘、育苗虾池塘
						imgSrc = "${ctx}/images/dtree/hexia.gif";
					}else if(object.scene_gtype==202){//鱼池塘
						imgSrc = "${ctx}/images/dtree/fish.gif";
					}
					scene_tree.add(object.node,object.pnode,object.text,object.url,"",target,imgSrc,imgSrc);
				}
			});
			$("#sceneTree").html(scene_tree.toString());
			scene_tree.openAll();
		});
		
	   

		function refreshSceneTree() {
			scene_tree = new dTree('scene_tree', "${ctx}");		
			scene_tree.add(0, -1, '农业物联网应用平台', '');
		    $.ajaxSettings.async = false;
			$.getJSON("${ctx}/op_scene_refreshSceneTree.action",{
				random:Math.random()
			},function(list){			
				$.each(list,function(i,object){
					if(object.type == "scene"){				
						var imgSrc = "${ctx}/images/dtree/folder.gif";
						var target = "";
						//改为根据场景细类判断*/
						if(object.scene_gtype==1){
							imgSrc = "${ctx}/images/dtree/icon_21_0.gif";//蟹池塘
						}else if(object.scene_gtype==2){
							imgSrc = "${ctx}/images/dtree/icon_21_1.gif";
						}else if(object.scene_gtype==3){
							imgSrc = "${ctx}/images/dtree/icon_21_2.gif";
						}else if(object.scene_gtype==4){
							imgSrc = "${ctx}/images/dtree/icon_21_3.gif";
						}else if(object.scene_gtype==98){
							imgSrc = "${ctx}/images/dtree/icon_21_4.gif";//气象站
						}else if(object.scene_gtype==97){
							imgSrc = "${ctx}/images/dtree/video.gif";//视频点
							target = "_brank";
						}else if(object.scene_gtype==201||object.scene_gtype==203){//虾池塘
							imgSrc = "${ctx}/images/dtree/hexia.gif";
						}else if(object.scene_gtype==202){//鱼池塘
							imgSrc = "${ctx}/images/dtree/fish.gif";
						}
						scene_tree.add(object.node,object.pnode,object.text,object.url,"",target,imgSrc,imgSrc);
					}else{
					}
				})
				//document.write(scene_tree);
				$("#sceneTree").html(scene_tree.toString());
				scene_tree.openAll();
			});
		}
			
		
	</script>
  </head>
  
  <body>
	<!-- input id="hid_selectedSceneId_onSceneTree" type="hidden" value="" -->
	<div id="sceneTree"></div>
	<br/>
	<img alt="" onclick="refreshSceneTree()" src="${ctx }/images/yunwei_images/shuaxin.jpg" style="cursor: pointer;width: 70px;">
  </body>
  
</html>
