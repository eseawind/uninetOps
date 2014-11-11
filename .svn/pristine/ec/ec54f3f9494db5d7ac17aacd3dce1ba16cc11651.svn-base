<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	<script type="text/javascript">
		var operate_timer = null;
		var id = "";
		var DG = frameElement.lhgDG;
		J(function(){
        		//alert( J('#txt',DG.curDoc).val() ); 
        		id = J('#txt',DG.curDoc).val();
        		findOperateMsg();
        		
		});
		function findOperateMsg(){
			//alert(id+"----llllll");
			$.ajax({
			   url: "${ctx}/gm_devctrloperate_operateMsg.action", 
		       type: "POST",  
		       dataType: "json",
		       data:{dect_id:id},//传值
		       error: function(){ },  
		       success: function(json){
		       	 jQuery.each(json, function(i, json) {
		       	 	var dectNo = json.dectNo;
		       	 	var decotType = json.decoType;
		       	 	var decoState = json.decoState;
		       	 	var decoResult = json.decoResult
		       	 	var decoErrorCode = json.decoErrorCode;
		       	 	var type = document.getElementById("type");
		       	 	var state = document.getElementById("state");
		       	 	var result = document.getElementById("result");
		       	 	var error = document.getElementById("error");
		       	 	document.getElementById("dectNo").innerHTML = dectNo;
		       	 	
		       	 	
		       	 	switch(parseInt(decotType)){
		       	 		case 1:
		       	 			type.innerHTML="开启……";
		       	 			break;
		       	 		case 2:
		       	 			type.innerHTML="停止……";
		       	 			break;
		       	 		case 3:
		       	 			type.innerHTML="关闭……";
		       	 			break;
		       	 	}
		       	 	
		       	 	switch(parseInt(decoState)){
		       	 		case 0:
		       	 			state.innerHTML="空闲……";
		       	 			break;
		       	 		case 1:
		       	 			state.innerHTML="操作请求……";
		       	 			break;
		       	 		case 2:
		       	 			state.innerHTML="命令下发……";
		       	 			break;
		       	 		case 3:
		       	 			state.innerHTML="命令返回……";
		       	 			break;
		       	 		case 4:
		       	 			state.innerHTML="处理完成……";
		       	 			break;
		       	 	}
		       	 	
		       	 	
		       	 	switch(parseInt(decoResult)){
		       	 		case 0:
		       	 			result.innerHTML="等待中……";
		       	 			break;
		       	 		case 1:
		       	 			result.innerHTML="成功……";
		       	 			break;
		       	 		case 2:
		       	 			result.innerHTML="失败……";
		       	 			switch(parseInt(decoErrorCode)){
		       	 				case 5:
		       	 					error.innerHTML="控制失败，参数错……";
		       	 					break;
		       	 				case 19:
		       	 					error.innerHTML="控制失败，电流异常……";
		       	 					break;
		       	 				case 21:
		       	 					error.innerHTML="控制失败，电源缺项……";
		       	 					break;
		       	 				case 28:
		       	 					error.innerHTML="控制失败，没有接电流互感器……";
		       	 					break;
		       	 			}
		       	 			break;
		       	 		case 3:
		       	 			result.innerHTML="超时……";
		       	 			break;
		       	 		case 4:
		       	 			result.innerHTML="GPRS为离线状态……";
		       	 			break;
		       	 		case 5:
		       	 			result.innerHTML="设备状态表配置错误……";
		       	 			break;
		       	 		case 6:
		       	 			result.innerHTML="设备状态无效……";
		       	 			break;
		       	 		case 7:
		       	 			result.innerHTML="设备按钮表配置错误……";
		       	 			break;	
		       	 		case 8:
		       	 			result.innerHTML="控制设备不响应……";
		       	 			break;
		       	 		case 9:
		       	 			result.innerHTML="设备操作中……";
		       	 			break;
		       	 		default:
		       	 			result.innerHTML="超时……";
		       	 			break;
		       	 	}
		       	 	
		       	 	
		       	 	if(decoResult != 0){
		       	 		var closebtn = document.getElementById("closebtn");
		       	 		closebtn.disabled = false;
		       	 		document.getElementById("loading").style.display = "none";
		       	 		if(operate_timer!=null){
							clearInterval(operate_timer);
						}
		       	 	}
		       	 	if(decoResult == 0){
		       	 		document.getElementById("loading").style.display = "block";
		       	 	}
				 })
        	   }  
			});
		}
		
		operate_timer = window.setInterval("findOperateMsg()",3000);
		
		//关闭窗口函数
		function closeWindow(){
			//DG.cancel();
			//window.location.href = window.location.href;
			DG.curWin.refresh();
			DG.cancel();
		}
		
	</script>
	
  </head>
  
  <body>
    <table align="center" style="font-size: 12px">
    	<tr>
    		<td width="120px">设备编号：</td>
    		<td width="200px" id="dectNo"></td>
    	</tr>
    	<tr>
    		<td>操作类型：</td>
    		<td id="type"></td>
    	</tr>
    	<tr>
    		<td>操作状态：</td>
    		<td id="state"></td>
    	</tr>
    	<tr>
    		<td>操作结果：</td>
    		<td id="result"></td>
    	</tr>
    	<tr>
    		<td id="error" colspan="2" style="color: red;">
    			
    		</td>
    	</tr>
    	<tr align="center">
    		<td colspan="2" id="loading" style="display: none;"><img id="loadImg" src="${ctx}/images/loading-bars.gif"></td>
    	</tr>
    	<tr align="center">
    		<td colspan="2">
    			<input id="closebtn" disabled="disabled" type="button" onclick="closeWindow()" value="关闭">
    		</td>
    	</tr>
    </table>
  </body>
</html>
