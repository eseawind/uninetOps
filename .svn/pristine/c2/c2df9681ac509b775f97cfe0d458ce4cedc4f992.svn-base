<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.unism.gm.action.form.DevFaultTypeForm" %>
<%@page import="javax.persistence.criteria.CriteriaBuilder.In"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

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
	
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<script type="text/javascript">
		function findHistorydata(){
			var beginTime = document.getElementById("beginTime").value;
			var endTime = document.getElementById("endTime").value;
			var hid_scene_id = document.getElementById("hid_scene_id").value;
			if(beginTime == "" ){
				alert("请输入开始时间……");
				return false;
			}
			if(endTime == "" ){
				alert("请输入结束时间……");
				return false;
			}
			window.location.href = "${ctx}/Gm_DevFault_findAllDevFaultTypeList.action?beginTime="+beginTime+"&endTime="+endTime+"&scene_id="+hid_scene_id;
		}
		
		function exportExcelData(){
			var beginTime = document.getElementById("beginTime").value;
			var endTime = document.getElementById("endTime").value;
			var hid_scene_id = document.getElementById("hid_scene_id").value;
			if(beginTime == "" ){
				alert("请输入开始时间……");
				return false;
			}
			if(endTime == "" ){
				alert("请输入结束时间……");
				return false;
			}
			window.location.href = "${ctx}/Gm_DevFault_exportExcel.action?beginTime="+beginTime+"&endTime="+endTime+"&scene_id="+hid_scene_id;
		}
		
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			document.getElementById("hid_scene_id").value = id;
			findHistorydata();
		}
	</script>
		
	
	

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    	<table  cellpadding="0" cellspacing="0" width="95%">
    		<tr>
    			<td>
    				故障发生时间：<input type="text" readonly="readonly" id="beginTime" name="beginTime" 
    				onClick="WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${beginTime }" />
				     至<input type="text" id="endTime" readonly="readonly" name="endTime" 
				     onClick="WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${endTime }" />
    				     <input type="button" onclick="findHistorydata();" value="查询">
    				     <!-- <input type="button" onclick="exportExcelData();" value="导出数据"> -->
    				     <input type="hidden" id="hid_scene_id" value="${scene_id }"/>
    			</td>
    		</tr>
    	</table>
    	<table  cellpadding="0" cellspacing="0" width="99%" border="1">
    	    <tr bgcolor='#8FABDE' align="center" style="height:25px;">
    			<td width="15%">场景名称</td>
    			<td width="10%">设备地址</td>
    			<td width="10%">平台故障</td>
    			<td width="10%">网关故障</td>
    			<td width="10%">小无线故障</td>
    			<td width="10%">传感器故障</td>
    			<td width="15%">控制设备故障</td>
    			<td width="8%">合计</td>
    		</tr>
    		<%
    		    int allCount=0;
    			int platformCountAll=0;
    			int GPRSCountAll=0;
    			int WsnCountAll=0;
    			int SensorCountAll=0;
    			int DevctrlCountAll=0;
    			Object mapObj = request.getAttribute("map");
    			if(mapObj != null) {
    				Map<String, DevFaultTypeForm> map = (Map<String, DevFaultTypeForm>)mapObj;    				
    				for(Map.Entry<String, DevFaultTypeForm> entry : map.entrySet())   { 
    					out.print("<tr align='center'>");
    					DevFaultTypeForm form = entry.getValue();
    					Integer rowCount=0;
    					Integer platformCount=form.getPlatformCount();
    					Integer GPRSCount=form.getGPRSCount();
    					Integer WsnCount=form.getWsnCount();
    					Integer SensorCount=form.getSensorCount();
    					Integer DevctrlCount=form.getDevctrlCount();
    					rowCount=platformCount+GPRSCount+WsnCount+SensorCount+DevctrlCount;
    					platformCountAll=platformCountAll+platformCount;
    					GPRSCountAll=GPRSCountAll+GPRSCount;
    					WsnCountAll=WsnCountAll+WsnCount;
    					SensorCountAll=SensorCountAll+SensorCount;
    					DevctrlCountAll=DevctrlCountAll+DevctrlCount;
    					out.print("<td>" + form.getScene_name() + "</td>");
    					out.print("<td>" + form.getNet_addr() + "</td>");
    					out.print("<td>" + form.getPlatformCount() + "</td>");
    					out.print("<td>" + form.getGPRSCount() + "</td>");
    					out.print("<td>" + form.getWsnCount() + "</td>");
    					out.print("<td>" + form.getSensorCount() + "</td>");
    					out.print("<td>" + form.getDevctrlCount() + "</td>");    					
    					out.print("<td>" + rowCount + "</td>");
    					out.print("</tr>");
    				}  
    				allCount=platformCountAll+GPRSCountAll+WsnCountAll+SensorCountAll+DevctrlCountAll;
                    out.print("<tr align='center'>");
                    out.print("<td colspan='2'>合计</td>");
                    out.print("<td>"+ platformCountAll +"</td>");
                    out.print("<td>"+ GPRSCountAll +"</td>");
                    out.print("<td>"+ WsnCountAll +"</td>");
                    out.print("<td>"+ SensorCountAll +"</td>");
                    out.print("<td>"+ DevctrlCountAll +"</td>");
                    out.print("<td>"+ allCount +"</td>");
                    out.print("</tr>");
    			}
    		%>
    	</table>
    	<script type="text/javascript">
		$(document).ready(function (){
			//获取当前时间
	       	var date = new Date();
	       	function chkTime(num){
	            return num<10?"0"+num:num;
			}
			//格式化时间
	       	Date.prototype.format = function(date , dformat){
	       		var year = date.getFullYear();
	            var month = chkTime(date.getMonth()+1);
	            var day = chkTime(date.getDate());
	            var hour = chkTime(date.getHours());
	            var minute = chkTime(date.getMinutes());
	            var second = chkTime(date.getSeconds());
	       	 	return dformat.replace("y",year).replace("M",month).replace("d",day).replace("H",hour).replace("m",minute).replace("s",second)
	       	}
	      	var beginTimeDefault = date.format(date,"y-M-d")+" 00:00:00";
	      	var endTimeDefault = date.format(date,"y-M-d")+" 23:59:59";
	      	var beginTime = "${beginTime}";
	      	if(beginTime==""){
	      		document.getElementById("beginTime").value = beginTimeDefault;
	      	}else{	      		
	      		document.getElementById("beginTime").value=beginTime;
	      	}
	      	var endTime = "${endTime}";
	      	if(endTime==""){	      		
	      		document.getElementById("endTime").value = endTimeDefault;
	      	}else{
	      		document.getElementById("endTime").value=endTime;
	      	}
		
		});		
	</script>
  </body>
</html>
