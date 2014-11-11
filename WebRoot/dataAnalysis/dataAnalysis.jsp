<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>数据分析</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>
	<script type="text/javascript">
		function findChannel(){
			var netAddr = document.getElementById("netAddr").value;
			$.ajax({
				   url: "${ctx}/gm_devsts_findDevByAddr.action", 
			       type: "POST",  
			       dataType: "json",//xml,html,script,json
			       data:{net_addr:netAddr},
			       error: function(){},  
			       success: function(json){ 
			       	var html = "";
			       	//var chtypeNo = "";	 
			       	jQuery.each(json, function(i, list){
			       	  	var devId = list.devId;
			       	  	var devNo = list.devNo;
			       	  	var devName = list.devName;
			       	  	var sceneName = list.sceneName;
			       	  	//var chtype_displayName = list.chtype_displayName;
						//if(chtype_no == chtypeNo){
							html += "<input id="+devId+" name=\"channels\" menu="+sceneName+"-"+devName+" type=\"checkbox\" value="+devId+" />"+sceneName+"["+devName+"("+devNo+")"+"]";
						//}else{
							//html += "<HR style=\"FILTER: alpha(opacity=100,finishopacity=0,style=1)\" width=\"100%\" color=\"#9fa9dc\" SIZE=\"1\"><div style=\"color: #3b6dcd;\">" + chtype_no + "-" + chtype_displayName + "</div><input id="+ch_id+" name=\"channels\" menu="+scene_name+"-"+ch_name+" type=\"checkbox\" value="+ch_id+" />"+scene_name+"-"+ch_name;
							//chtypeNo=chtype_no;
						//}
			       	});
			       	var type = "<HR style=\"FILTER: alpha(opacity=100,finishopacity=0,style=1)\" width=\"100%\" color=\"#9fa9dc\" SIZE=\"1\"><input type=\"radio\" id=\"type\" name=\"type\" checked=\"checked\" value=\"dType\" />X轴：采集时间，Y轴：时间差<input type=\"radio\" id=\"type\" name=\"type\" value=\"nType\" />X轴：采集时间，Y轴：上报时间";
			       	var date = "开始时间：<input id=\"beginTime\" readonly=\"readonly\" name=\"beginTime\" type=\"text\" onClick=\"WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" value=\"\">结束时间：<input id=\"endTime\" readonly=\"readonly\" name=\"endTime\" type=\"text\" onClick=\"WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" value=\"\"><input type=\"button\" onclick=\"findHistoryCharts();\" value=\"分析\">"
			       	jQuery("#channel").html(html);
			       	jQuery("#date").html(date);
			       	$("#type").html(type);
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
			       	document.getElementById("beginTime").value = beginTimeDefault;
			       	document.getElementById("endTime").value = endTimeDefault;
		 	   }  
				}); 
		}

			function selectAll(){
				$("[name='channels']").attr("checked",$("#all").attr("checked"));
			}

			function findHistoryCharts(){
				var devices = "";
				$.each(document.getElementsByName("channels"),function(i,device){
					if(device.checked){
						devices += device.value+",";
					}
				})
				if(devices.length>0){			
					devices = devices.substr(0,devices.length-1);
				}else{				
					alert("请选择设备");
					return;
				}
				var type="";
				$.each(document.getElementsByName("type"),function(i,radio){
					if(radio.checked){
						type = radio.value;
					}
				})
				var begin = $("#beginTime").val();
				var end = $("#endTime").val();
				$.ajax({
					   url: "${ctx}/gm_historydata_dataAnalysis.action", 
				       type: "POST",  
				       dataType: "json",//xml,html,script,json
				       data:{devId:devices,beginTime:begin,endTime:end,chartsType:type},
				       error: function(){},  
				       success: function(lines){ 
				    	  document.getElementById("charts").value="";
				    	  if(type=='dType'){
				    		  if(lines.length>0){
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'charts',
											defaultSeriesType: 'spline'
										},	
										title: {
											text: '数据分析'
										},
										subtitle: {
											text: ''
										},
										xAxis: {
								        	type: 'datetime'
								        },
										yAxis: {
											title: {
												text: '当前值'
											}
										},
										tooltip: {
											crosshairs: true,
											formatter: function() {
								                return this.series.name + '' +Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +' ' + '时间差:<b>' + Highcharts.numberFormat(this.y, 2) + '</b>';
											}
										},
										plotOptions: {
											spline: {
												marker: {
													enabled: false,
													radius: 4,
													lineColor: '#666666',
													lineWidth: 1
												}
											}
										},
										series: lines,
										exporting: {
											enabled: false
										},
										credits: {
											enabled: false
										}
									});
								}else{
									document.getElementById(content).innerHTML = "<font size='4'>未配置该数据类型</font>";
								}
				    	  }else if(type=='nType'){
				    		  if(lines.length>0){
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'charts',
											defaultSeriesType: 'spline'
										},	
										title: {
											text: '数据分析'
										},
										subtitle: {
											text: ''
										},
										xAxis: {
								        	type: 'datetime'
								        },
										yAxis: {
											title: {
												text: '当前值'
											},
											type: 'datetime'
										},
										tooltip: {
											crosshairs: true,
											formatter: function() {
								                return this.series.name +''+Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +' ' + '上报时间:<b>' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S',this.y, 2) + '</b>';
											}
										},
										plotOptions: {
											spline: {
												marker: {
													enabled: false,
													radius: 4,
													lineColor: '#666666',
													lineWidth: 1
												}
											}
										},
										series: lines,
										exporting: {
											enabled: false
										},
										credits: {
											enabled: false
										}
									});
								}else{
									document.getElementById(content).innerHTML = "<font size='4'>未配置该数据类型</font>";
								}
				    	  }
			 	   	   }  
					});
			}

			function onchane(id){
				if(id == 'scene'){
					document.getElementById("displayAddr").style.display="none";
				}else if(id == 'addr'){
					document.getElementById("displayAddr").style.display="";
				}
			}

			
			function echoSceneTree(id,name,no,rank,gtype){
				$.ajax({
					   url: "${ctx}/gm_devsts_findDeviceByScene.action", 
				       type: "POST",  
				       dataType: "json",//xml,html,script,json
				       data:{scene_id:id},
				       error: function(){},  
				       success: function(json){ 
				       	var html = "";
				       //	var chtypeNo = "";	 
				       	jQuery.each(json, function(i, list){
				       		var devId = list.devId;
				       	  	var devNo = list.devNo;
				       	  	var devName = list.devName;
				       	  	var sceneName = list.sceneName;
				       	  	//var chtype_displayName = list.chtype_displayName;
							//if(chtype_no == chtypeNo){
								html += "<input id="+devId+" name=\"channels\" menu="+sceneName+"-"+devName+" type=\"checkbox\" value="+devId+" />"+sceneName+"["+devName+"("+devNo+")"+"]";
							//}else{
								//html += "<HR style=\"FILTER: alpha(opacity=100,finishopacity=0,style=1)\" width=\"100%\" color=\"#9fa9dc\" SIZE=\"1\"><div style=\"color: #3b6dcd;\">" + chtype_no + "-" + chtype_displayName + "</div><input id="+ch_id+" name=\"channels\" menu="+scene_name+"-"+ch_name+" type=\"checkbox\" value="+ch_id+" />"+scene_name+"-"+ch_name;
								//chtypeNo=chtype_no;
							//}
				       	});
				       	var type = "<HR style=\"FILTER: alpha(opacity=100,finishopacity=0,style=1)\" width=\"100%\" color=\"#9fa9dc\" SIZE=\"1\"><input type=\"radio\" id=\"type\" name=\"type\" checked=\"checked\" value=\"dType\" />X轴：采集时间，Y轴：时间差<input type=\"radio\" id=\"type\" name=\"type\" value=\"nType\" />X轴：采集时间，Y轴：上报时间";
				       	var date = "开始时间：<input id=\"beginTime\" readonly=\"readonly\" name=\"beginTime\" type=\"text\" onClick=\"WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" value=\"\">结束时间：<input id=\"endTime\" readonly=\"readonly\" name=\"endTime\" type=\"text\" onClick=\"WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" value=\"\"><input type=\"button\" onclick=\"findHistoryCharts();\" value=\"分析\">"
				       	jQuery("#channel").html(html);
				       	jQuery("#date").html(date);
				       	$("#type").html(type);
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
				       	document.getElementById("beginTime").value = beginTimeDefault;
				       	document.getElementById("endTime").value = endTimeDefault;
			 	   }  
					}); 
			}
	</script>

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  
    <table style="font: 12px/1.5 tahoma,arial,宋体;" width="100%">
    	<tr>
    		<td>
    			<input type="radio" id="scene" checked="checked" onclick="onchane(id)" name="t"/>按场景查询
    			<input type="radio" id="addr" onclick="onchane(id)" name="t"/>按设备地址
    		</td>
    	</tr>
    		<tr>
    			<td id="displayAddr" style="display: none;">
    				设备地址： <input type="text" id="netAddr" name="" />
    						<input type="button" onclick="findChannel()" value="查询">
    			</td>
    		</tr>
    		<tr>
    			<td id="channel"></td>
    		</tr>
    		<tr>
    			<td id="type"></td>
    		</tr>
    		<tr>
    			<td id="date"></td>
    		</tr>
    		<tr>
    			<td id="charts"></td>
    		</tr>
    	</table>
  </body>
</html>
  <script type="text/javascript">
			//默认加载
			if(window.parent.right.scene_tree.selectedNode!=null){
				var curr_node_id = this.parent.right.scene_tree.aNodes[window.parent.right.scene_tree.selectedNode].id;
				var scene_id = curr_node_id.substr(2);
				//alert(scene_id);
				$.getJSON("/uninetOps/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
					random:Math.random()
				},function(op_Scene){
					echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
				});		
			}
	</script>
