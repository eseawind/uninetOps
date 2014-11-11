<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>数据查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	<!--
		table {
		 border-collapse: collapse;
		 border: 2px #bbd1fa solid;
		}
		
		td {
		  border: 1px #bbd1fa solid;
		  font-size: 12px;
		}
		
		
	-->
	</style>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>
	<script type="text/javascript" src="${ctx }/js/date.js"></script>	
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			loadTd_channels(id,gtype);
			sceneid(id);
		}
		
		function sceneid(id){
			document.getElementById("sceneId").value=id;
		}
		
		//加载通道表单
		function loadTd_channels(id,gtype){
			var td_channels = document.getElementById("td_channels");
			//td_channels.innerHTML = "正在加载采集通道列表，请等待...";
			td_channels.innerHTML = ".";
			if(document.getElementById('rad_device').checked){
				$.getJSON("${ctx}/gm_device_json_findAllByScene_id_min.action?scene_id="+id,{
					random:Math.random()
				},function(devices){
					var inner = "没有找到指定的数据";
					if(devices.length>0){
						inner = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"border:0px;\" width=\"100%\">";
						$.each(devices,function(i,device){
							inner += "<input name=\"devices\" type=\"checkbox\" value=\""+device.dev_id+"\">"+device.dev_name+"("+device.dev_no+")&nbsp;";
						})
						inner += "</td></tr>";
						inner += "<tr><td style=\"border:0px; border-top:1px solid;\" width=\"100%\"><input type=\"button\" value=\"显示通道\" onclick=\"showChannelByDevice()\"></td></tr>";
						inner += "<tr><td id=\"td_channelsByDevice\" style=\"border:0px; border-top:1px solid;\" width=\"100%\">&nbsp;</td></tr>";
						inner += "</table>";
					}
					td_channels.innerHTML = inner;
				});
			}else{
				if(gtype == 1){
					$.getJSON("${ctx}/Gm_Channel_json_findByScene_idAndCh_offerSer_run.action?scene_id="+id,{
						random:Math.random()
					},function(channels){
						var inner = "没有找到指定的数据";
						if(channels.length>0){
							inner_show = "&nbsp;";
							inner_hid = "&nbsp;";
		  					$.each(channels,function(i,channel){
		  						//if(channel.chtype_no == "DO10-T" || channel.chtype_no == "DO10-O" || channel.chtype_no == "DL-C" || channel.chtype_no == "NULL-P"){
		  							inner_show += "<input name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.scene_name+"-"+channel.ch_name+"&nbsp;";
		  						//}else{
		  						//	inner_hid += "<input name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.scene_name+"-"+channel.ch_name+"&nbsp;";
		  						//}
		  					})
		  				}
		  				//td_channels.innerHTML = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"border:0px;\" width=\"100%\">"+inner_show+"</td></tr><tr class=\"tr_hid\"><td style=\"border:0px;\" width=\"100%\">"+inner_hid+"</td></tr></table>";				
						td_channels.innerHTML = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"border:0px;\" width=\"100%\">"+inner_show+"</td></tr></table>";		
					});
				}else{
					$.getJSON("${ctx}/Gm_Channel_json_findAllByScene_idAndCh_offerSer_run.action?scene_id="+id,{
						random:Math.random()
					},function(types){
						var inner = "没有找到指定的数据";
						if(types.length>0){
							inner = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">";
		  					$.each(types,function(i,type){
		  						//if(type.chtype_no == "DO10-T" || type.chtype_no == "DO10-O" || type.chtype_no == "DL-C" || type.chtype_no == "NULL-P"){
			  						inner += "<tr>";
									inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 100%;\">"+type.chtype_no+type.chtype_name;
									inner += "<input type=\"checkbox\" onclick=\"fenlei_quanxuan(this,'ck_"+type.chtype_no+"')\"/> 全选 / 反选";
									inner += "</td>";
									inner += "</tr>";
									inner += "<tr>";
									inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 100%;\">&nbsp;";
									$.each(type.channels,function(j,channel){
										inner += "<input class=\"ck_"+type.chtype_no+"\" name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.scene_name+"-"+channel.ch_name+"&nbsp;";
									})
									inner += "</td>";
									inner += "</tr>";
								//}else{
								//	inner += "<tr class=\"tr_hid\">";
								//	inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">"+type.chtype_no+type.chtype_name+"</td>";
								//	inner += "</tr>";
								//	inner += "<tr class=\"tr_hid\">";
								//	inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">&nbsp;";
								//	$.each(type.channels,function(j,channel){
								//		inner += "<input name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.scene_name+"-"+channel.ch_name+"&nbsp;";
								//	})
								//	inner += "</td>";
								//	inner += "</tr>";
								//}
		  					})
		  					inner += "<tr>";
		  					inner += "<td style=\"border:0px; width: 100%;\">&nbsp;</td>";
		  					inner += "</tr>";
		  					inner += "</table>";
		  				}				
		  				td_channels.innerHTML = inner;			
					});
				}
			}	
		}
		
		//指定设备集合 查询通道集合
		function showChannelByDevice(){
			var devices = "";			
			$.each(document.getElementsByName("devices"),function(i,device){
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
			
			var td_channelsByDevice = document.getElementById("td_channelsByDevice");
			//td_channels.innerHTML = "正在加载采集通道列表，请等待...";
			td_channelsByDevice.innerHTML = ".";
			$.getJSON("${ctx}/Gm_Channel_json_showChannelByDevice_run.action?dev_id_arr_str="+devices,{
				random:Math.random()
			},function(channels){
				var inner = "没有找到指定的数据";
				if(channels.length>0){
					inner = "";
					$.each(channels,function(i,channel){
  						inner += "<input name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.scene_name+"-"+channel.ch_name+"&nbsp;";
  					})
				}
				td_channelsByDevice.innerHTML = inner;
			});			
		}
		
		//加载曲线图
		function loadCharts(pageNo){		
			var channels = "";
			$.each(document.getElementsByName("channels"),function(i,channel){
				if(channel.checked){
					channels += channel.value+",";
					
				}
			})			
			if(channels.length>0){			
				channels = channels.substr(0,channels.length-1);
				
			}else{				
				alert("请输入采集通道");
				return;
			}			
			var beginTime = document.getElementById("beginTime").value;
			var endTime = document.getElementById("endTime").value;	
			var hours = document.getElementById("hours").value;	
			var minutes = document.getElementById("minutes").value;	
			//var seconds = document.getElementById("seconds").value;
			var zhengze = /^(0|[1-9]\d*)$/;			
			if(zhengze.test(hours)==false){
				window.confirm("请输入一个小时整数！");
				return false;
			}
			if(zhengze.test(minutes)==false){
				window.confirm("请输入一个分钟整数！");
				return false;
			}
			/*
			if(zhengze.test(seconds)==false){
				window.confirm("请输入一个秒数整数！");
				return false;
			}
			*/
			if(hours > 24){
				window.confirm("请输入一个0~24之间的小时整数！");
				return false;
			}
			if(minutes > 60){
				window.confirm("请输入一个0~60之间的分钟整数！");
				return false;
			}
			/*
			if(seconds > 60){
				window.confirm("请输入一个0~60之间的秒数整数！");
				return false;
			}
			*/
			 var start = beginTime + " 00:00:00";
				var end = endTime + " 23:59:59";
				var startT = Date.parse(start.replace(/\-/g,"/"));
				var endT = Date.parse(end.replace(/\-/g,"/"));
				var ms = parseInt(hours*60*60*1000)+parseInt(minutes*60*1000);
				var size = parseInt(endT-startT+1000)/ms;
				var pageCount = Math.ceil(size/500);
				var pageHtml = "<table><tr>";
				if(pageNo > 10){
					pageHtml +="<td border=\"0\">&nbsp;&nbsp;<a href=\"javascript:loadCharts(1);\">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				}
				for(var i = 1;i < pageCount+1;i++){
					if(pageNo < i+10){
						if(pageNo > i-10){
							if(pageNo == i ){
								pageHtml+="<td border=\"0\">&nbsp;&nbsp;<a href=\"#\" style=\"color:#000;\">"+i+"</a>&nbsp;&nbsp;</td>";
							}else{
								pageHtml+="<td border=\"0\">&nbsp;&nbsp;<a href=\"javascript:loadCharts('"+i+"');\">"+i+"</a>&nbsp;&nbsp;</td>";
							}
						}
					}
				}
				if(pageNo < pageCount-10){
					pageHtml +="<td border=\"0\">&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:loadCharts('"+pageCount+"');\">尾页</a>&nbsp;&nbsp;</td>";
				}
				pageHtml += "</tr></table>";
			document.getElementById("container").innerHTML = "正在加载数据，请等待...";
			var dataType = 	$("input[name='dataType']:checked").val();
			$.getJSON("${ctx}/gm_historydata_findHistoryByChannelsAndTime.action?ch_id_list="+channels
				+"&beginTime="+beginTime
				+"&endTime="+endTime
				+"&hours="+hours
				+"&minutes="+minutes
				+"&seconds="+seconds
				+"&pageIndex="+pageNo
				+"&dataType="+dataType
				,{
				random:Math.random()
			},function(series){	
				var rows = ""; 			 				
				document.getElementById("container").innerHTML = "";
				var inner = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">";
				if(series.length>0){	    					
					$.each(series,function(i,column){
						inner += "<tr align=\"center\">";
						inner += "<td style=\"width: 150px;\">"+column.time+"&nbsp;</td>";
						$.each(column.data,function(i,ele){
							rows = i
							if(ele=="null"){								
								inner += "<td>&nbsp;</td>";
							}else{							
								inner += "<td>"+ele+"&nbsp;</td>";
							}								
						});
						if(column.data == ""){
							for(var j = 0;j < rows+1;j++){
								inner += "<td>&nbsp;</td>";
							}
						}
						inner += "</tr>";
					})
				}else{
					document.getElementById("container").innerHTML = "没有找到指定的数据";
				}
				inner += "</table>";
				document.getElementById("container").innerHTML = inner;	
				jQuery("#page").html(pageHtml);
			});
		}
		
		//全选 反选
		function quanxuan(ck){
			var arr = document.getElementsByName("channels");
			if(ck.checked){				
				for(var i=0;i<arr.length;i++){
					var c = arr[i];
					c.checked = true;
				}
			}else{
				for(var i=0;i<arr.length;i++){
					var c = arr[i];
					c.checked = false;
				}
			}
		}
		
		//分类全选 反选
		function fenlei_quanxuan(ck,className){
			var arr = $("input."+className);
			if(ck.checked){				
				for(var i=0;i<arr.length;i++){
					var c = arr[i];
					c.checked = true;
				}
			}else{
				for(var i=0;i<arr.length;i++){
					var c = arr[i];
					c.checked = false;
				}
			}
		}		
	</script>
	
	
	<script type="text/javascript">
		function dataExport(){
			var channels = "";
			$.each(document.getElementsByName("channels"),function(i,channel){
				if(channel.checked){
					channels += channel.value+",";
					
				}
			})			
			if(channels.length>0){			
				channels = channels.substr(0,channels.length-1);
				
			}else{				
				alert("请输入采集通道");
				return;
			}			
			var beginTime = document.getElementById("beginTime").value;
			var endTime = document.getElementById("endTime").value;	
			var hours = document.getElementById("hours").value;	
			var minutes = document.getElementById("minutes").value;	
			//var seconds = document.getElementById("seconds").value;
			var zhengze = /^(0|[1-9]\d*)$/;			
			if(zhengze.test(hours)==false){
				window.confirm("请输入一个小时整数！");
				return false;
			}
			if(zhengze.test(minutes)==false){
				window.confirm("请输入一个分钟整数！");
				return false;
			}
			/*
			if(zhengze.test(seconds)==false){
				window.confirm("请输入一个秒数整数！");
				return false;
			}
			*/
			if(hours > 24){
				window.confirm("请输入一个0~24之间的小时整数！");
				return false;
			}
			if(minutes > 60){
				window.confirm("请输入一个0~60之间的分钟整数！");
				return false;
			}
			/*
			if(seconds > 60){
				window.confirm("请输入一个0~60之间的秒数整数！");
				return false;
			}
			*/
			var id = document.getElementById("sceneId").value;
			if(id != ""){
				window.location.href="${ctx}/gm_historydata_dataExport.action?ch_id_list="+channels+"&beginTime="+beginTime+"&endTime="+endTime+"&hours="+hours+"&minutes="+minutes+"&seconds="+seconds+"&scene_id="+id
			}
		}
		
		function showOrhiddenChannel(c){
			if(c.checked){
				$("#trChannels").css("display","block");
			}else{
				$("#trChannels").css("display","none");
			}
		}
	</script>
	
	
	
  </head>  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<table width="100%" cellpadding="0" cellspacing="0">
  		<tr>
  			<td colspan="2">
  				<input name="rad_sceneOrDevice" type="radio" id="rad_scene" checked="checked">按场景&nbsp;&nbsp;
  				<input name="rad_sceneOrDevice" type="radio" id="rad_device">按设备
  				<input type="checkbox" checked="checked" onclick="showOrhiddenChannel(this)" > 显示/隐藏通道
  			</td>
  		</tr>
  		<tr id="trChannels">
  			<td width="100">
  				输入采集通道<br/>
  				<input type="checkbox" onclick="quanxuan(this)"/> 全选 / 反选
  			</td>
  			<td id="td_channels">
				用户点击左侧树状菜单上的节点<br/>
				查找到节点下的所有数据通道，<br/>
				如果不是最后一级场景则要对通道进行分类显示．<br/>
  			</td>
  		</tr>
  		<tr>
  			<td width="100"  height="33">数据间隔</td>
  			<td height="33"> 
  				<input id="hours" type="text" style="width: 40px;" value="0">小时
  				<input id="minutes" type="text" style="width: 40px;" value="10">分钟
  				<input id="seconds" type="hidden" style="width: 40px;" value="0">
  			</td>
  		</tr>
  		<tr>
  			<td width="100"  height="33">选择视图类型</td>
  			<td height="33"> 
  				<input name="viewType" type="radio" value="date" checked="checked" onclick="document.getElementById('hid_viewType').value='date';document.getElementById('beginTime').disabled='disabled';document.getElementById('endTime').disabled='disabled';document.getElementById('beginTime').value='${dateView_beginTime}';document.getElementById('endTime').value='${dateView_endTime}'">24小时
  				<input name="viewType" type="radio" value="week" onclick="document.getElementById('hid_viewType').value='week';document.getElementById('beginTime').disabled='disabled';document.getElementById('endTime').disabled='disabled';document.getElementById('beginTime').value='${weekView_beginTime}';document.getElementById('endTime').value='${weekView_endTime}'"> 7天
  				<input name="viewType" type="radio" value="month" onclick="document.getElementById('hid_viewType').value='month';document.getElementById('beginTime').disabled='disabled';document.getElementById('endTime').disabled='disabled';document.getElementById('beginTime').value='${monthView_beginTime}';document.getElementById('endTime').value='${monthView_endTime}'"> 30天
  				<input name="viewType" type="radio" value="year" onclick="document.getElementById('hid_viewType').value='year';document.getElementById('beginTime').disabled='disabled';document.getElementById('endTime').disabled='disabled';document.getElementById('beginTime').value='${yearView_beginTime}';document.getElementById('endTime').value='${yearView_endTime}'"> 全年
  				<input name="viewType" type="radio" value="select" onclick="document.getElementById('hid_viewType').value='select';document.getElementById('beginTime').disabled=false;document.getElementById('endTime').disabled=false;">自定义
  				<input id="hid_viewType" type="hidden" value="date">
  			</td>
  		</tr>
  		<tr>
  			<td width="100">选择时间</td>
  			<td>
  				<input id="beginTime" size="8" onfocus="setday(this,this)" value="${dateView_beginTime }">-
  				<input id="endTime" size="8" onfocus="setday(this,this)" value="${dateView_endTime }">
  				<input type="radio" name="dataType" id="origValue" value="origValue" >原始数据&nbsp;&nbsp;
  				<input type="radio" name="dataType" id="corrValue" value="corrValue" checked="checked" >处理后数据
  				<input type="button" onclick="loadCharts(1)" value="提交">
  				<input type="button" onclick="dataExport()" value="导出数据">
  				<input id="sceneId" type="hidden" value="">
  			</td>
  		</tr>
  		<tr>
  			<td colspan="2" width="1006" style="position: relative;font-size:12px;">
				<div id="container" style="border: 1 solid #BBD1FA; top:1px; left:-3px; width: 1006px; text-align: center;">请选择查询条件</div>
  			</td>
  		</tr>
  		<tr align="center">
  			<td colspan="2">
  				<div id="page">
  					
  				</div>
  			</td>
  		</tr>
  	</table>
  	<script type="text/javascript">
  		if(window.parent.right.scene_tree.selectedNode!=null){
			var curr_node_id = this.parent.right.scene_tree.aNodes[window.parent.right.scene_tree.selectedNode].id;
			var scene_id = curr_node_id.substr(2);
			//alert(scene_id);
			$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
				random:Math.random()
			},function(op_Scene){
				echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
			});		
		}
	</script>
  </body>
</html>
  