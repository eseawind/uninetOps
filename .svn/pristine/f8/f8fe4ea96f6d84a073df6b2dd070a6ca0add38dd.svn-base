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
    
    <title>My JSP 'left.jsp' starting page</title>
    
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
		$(document).ready(function(){
			var k=$(document.body).height();
	 		var hh = k-22;
	 		$("#ls").css("height",hh);
		});
	
	function isShowTree(val){
		if(val == "close"){
			document.getElementById("treeJsp").style.display="none";
			document.getElementById("rtf").style.display="none";
			document.getElementById("closeTree").style.display="none";
			document.getElementById("openTree").style.display="";
			var col = window.parent.window.document.getElementById("middleFrame").cols;
			var colsArr = col.split(","); 
			window.parent.window.document.getElementById("middleFrame").cols="9,"+colsArr[1]+","+colsArr[2];
			$("#leftTable").css("width","9");
		}else{
			document.getElementById("treeJsp").style.display="";
			document.getElementById("rtf").style.display="";
			document.getElementById("closeTree").style.display="";
			document.getElementById("openTree").style.display="none";
			var cols = window.parent.window.document.getElementById("middleFrame").cols;
			var colArr = cols.split(","); 
			window.parent.window.document.getElementById("middleFrame").cols="182,"+colArr[1]+","+colArr[2];
			$("#leftTable").css("width","182");
		}
	}
	
	$(window).resize(function() {
	    //var width = $(this).width();
	    var height = $(this).height();
	    var h = height-22;
	    $("#ls").css("height",h);
	});
	
	</script>
  </head>
  
  <body>
    <table id="leftTable" width="182" align="left" height="100%" border="0" cellpadding="0" cellspacing="0" style="border-right:1px blue solid;border-left:1px blue solid;">
  <tr id="rtf">
    <td colspan="2" valign="top"><img src="${ctx }/images/yunwei_images/rtf.jpg" width="182" height="22" /></td>
  </tr>
  <tr>
    <td id="treeJsp" width="174" valign="top">
		<div id="ls" class="right_overflow">
			<jsp:include page="/op_sysfun/menu_tree_0726.jsp"></jsp:include>
		</div>
	</td>
    <td id="closeTree" class="LeftTree_button_bg"><img onclick="isShowTree('close');" src="${ctx }/images/yunwei_images/hidTree.jpg" style="cursor:pointer;"/></td>
    <td id="openTree" class="LeftTree_button_bg" style="display: none;"><img onclick="isShowTree('open');" src="${ctx }/images/yunwei_images/showTree.jpg" style="cursor:pointer;"/></td>
  </tr>
</table>
  </body>
</html>
