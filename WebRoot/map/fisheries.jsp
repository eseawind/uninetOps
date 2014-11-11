<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'my.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
	<script type="text/javascript">
			$(document).ready(function (){
					$.getJSON("${ctx}/Pro_Fisheries_findAllFishInfo.action",{
						random:Math.random()
						},function(list){
						var html = "";			
							jQuery.each(list, function(i, data) {
								var id = data.id;
								var no = data.no;
								var thumb = data.thumb;
								var gType = data.gType;
								var flag = data.flag;
								var area = data.area;
								var aquaticType = data.aquaticType;
								var cultureObject = data.cultureObject;
								var putTime = data.putTime;
								var dectState = data.dectState;
								var value = data.value;
								var pondName = data.pondName;
								if(flag == 'red'){
									html += "<tr align='center'><td width='46%'>"+pondName+"(编号："+no+")</td><td width='19%'>"+value+"</td><td width='21%' style='color: red '>严重缺氧</td><td width='24%'>增氧机："+dectState+"</td></tr>";
						    	}
						    	if(flag == 'green'){
						    		html += "<tr align='center'><td>"+pondName+"(编号："+no+")</td><td>"+value+"</td><td style='color: green '>溶氧正常</td><td>增氧机："+dectState+"</td></tr>";
						    	}
						    	if(flag == 'yellow'){
						    		html += "<tr align='center'><td>"+pondName+"(编号："+no+")</td><td>"+value+"</td><td style='color: blue '>溶氧偏低</td><td>增氧机："+dectState+"</td></tr>";
						    	}
							})
							$("#content").html(html);
						});
			});
	</script>
	
	 <style type="text/css">

        html,body {

            overflow:hidden;

            margin:0px;

            width:100%;

            height:100%;

        }

        .virtual_body {

            width:100%;

            height:100%;

            overflow-y:scroll;

            overflow-x:auto;

        }

        .fixed_div {

            position:absolute;

            z-index:2008;
            
            border-bottom: 1px solid #85c5ee;
            
            width: 100%;

        }

    </style>
	
	
  </head>
  
  <body>
  <div class="fixed_div">
    <table>
    	<tr align="center" id="menu"> 
    		<td width="46%">养殖池及编号</td>
    		<td width="19%">溶氧量</td>
    		<td width="21%">溶氧状态</td>
    		<td width="24%">增氧机状态</td>		
    	</tr>
    </table>
  </div>
    <div class="virtual_body" style="position: relative;top: 25px; height: 348px;">
    	<table id="content">
  			
    	</table>  
    </div>
      
  </body>
</html>
