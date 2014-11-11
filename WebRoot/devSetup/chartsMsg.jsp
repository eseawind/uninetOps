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
    
    <title>My JSP 'chartsMsg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		/*
		var DG = frameElement.lhgDG;
		var operate_timer = null;
		J(function(){
			getDataMsg();
		});
	    function getDataMsg(){
	    	var dataSum = J('#dataSum',DG.curDoc).val();
    		var appendNum = J('#appendNum',DG.curDoc).val();
    		var seriesNum = J('#seriesNum',DG.curDoc).val();
    		speed(dataSum,appendNum,seriesNum);
	    }
	    
	    function speed(dataSum,appendNum,seriesNum){
	    	var speed = appendNum*seriesNum*10000/dataSum*100;
	    	if(speed < 100){
		    	$("#speed").html(speed+"%");
	    	}
	    }
	    operate_timer = window.setInterval("getDataMsg()",1000);
	    */
	</script>

  </head>
  
  <body>
    <p>请等待……</p>
  </body>
</html>
