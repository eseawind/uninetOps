<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/yunwei_style.css" />
	<script language="javascript" type="text/javascript" src="${ctx }/js/jquery.js"></script>
	<style type="text/css">
		.right_overflow{
			width:174px;
			overflow:auto;
			}
	</style>
	<script type="text/javascript">
		//自适应屏高度
		$(document).ready(function() {
			var k = $(document.body).height();
			var hh = k - 50;
			$("#ls").css("height", hh);
			$("#ls1").css("height", hh);
		});
		
		function isShowTree(val){
		if(val == "close"){
			document.getElementById("treeJsp").style.display="none";
			document.getElementById("rtf").style.display="none";
			document.getElementById("closeTree").style.display="none";
			document.getElementById("openTree").style.display="";
			document.getElementById("treeButton").style.display="none";
			var col = window.parent.window.document.getElementById("middleFrame").cols;
			var colsArr = col.split(","); 
			window.parent.window.document.getElementById("middleFrame").cols=colsArr[0]+","+colsArr[1]+",9";
			$("#rightTable").css("width","9");
		}else{
			document.getElementById("treeJsp").style.display="";
			document.getElementById("rtf").style.display="";
			document.getElementById("closeTree").style.display="";
			document.getElementById("openTree").style.display="none";
			document.getElementById("treeButton").style.display="";
			var cols = window.parent.window.document.getElementById("middleFrame").cols;
			var colArr = cols.split(",");
			window.parent.window.document.getElementById("middleFrame").cols=colArr[0]+","+colArr[1]+",182";
			$("#rightTable").css("width","182");
		}
	}
	
	function changeDevice(){
		$("#sceneTreeTab").attr("src","${ctx }/images/yunwei_images/sence_tree_1.jpg");
		$("#deviceTreeTab").attr("src","${ctx }/images/yunwei_images/device_tree_2.jpg");
		$("#ls").css("display","none");
		$("#ls1").css("display","");
	}
	
	function changeScene(){
		$("#sceneTreeTab").attr("src","${ctx }/images/yunwei_images/sence_tree_2.jpg");
		$("#deviceTreeTab").attr("src","${ctx }/images/yunwei_images/device_tree_1.jpg");
		$("#ls1").css("display","none");
		$("#ls").css("display","");
	}
	//窗口大小改变触发事件
	$(window).resize(function() {
	    //var width = $(this).width();
	    var height = $(this).height();
	    var h = height-50;
	    $("#ls").css("height",h);
	    $("#ls1").css("height", h);
	});
	</script>
  </head>
  
  <body>
  	<table id="rightTable" width="182" align="right" height="100%" border="0" cellpadding="0" cellspacing="0" style="border-right:1px blue solid;border-left:1px blue solid;">
  <tr id="rtf">
    <td colspan="2" valign="top"><img src="${ctx}/images/yunwei_images/rtf.jpg" width="182" height="22" /></td>
  </tr>
  <tr>
    <td id="closeTree" class="RightTree_button_bg"><img onclick="isShowTree('close')" src="${ctx}/images/yunwei_images/showTree.jpg" style="cursor:pointer;"/></td>
    <td id="openTree" class="RightTree_button_bg" style="display: none;"><img onclick="isShowTree('open')" src="${ctx}/images/yunwei_images/hidTree.jpg" style="cursor:pointer;"/></td>
    <td id="treeJsp" width="174" valign="top">
		<div id="ls" class="right_overflow">
			<jsp:include page="/op_scene/scene_tree.jsp"></jsp:include>
		</div>
		<div id="ls1" style="display: none;" class="right_overflow" >
			<jsp:include page="/gm_device/device_tree_0723.jsp"></jsp:include>
		</div>
	</td>
  </tr>
  <tr id="treeButton">
    <td class="RightTree_button_bg"></td>
  	<td height="28" valign="top"><img id="sceneTreeTab" onclick="changeScene();" src="${ctx }/images/yunwei_images/sence_tree_2.jpg" style="cursor: pointer;"/><img id="deviceTreeTab" onclick="changeDevice();" src="${ctx }/images/yunwei_images/device_tree_1.jpg" style="cursor: pointer;"/></td>
  </tr>
</table>
  </body>
</html>
