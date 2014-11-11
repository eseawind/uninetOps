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
		}
	-->
	</style>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>
	<script type="text/javascript" src="${ctx }/js/date.js"></script>	
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,rank){
			loadTd_channels(id,rank);
		}
		
		//加载通道表单
		function loadTd_channels(id,rank){
			var td_channels = document.getElementById("td_channels");
			td_channels.innerHTML = "正在加载采集通道列表，请等待...";
			if(rank == 0){
				$.getJSON("${ctx}/Gm_Channel_json_findByScene_idAndCh_offerSer.action?scene_id="+id,{
					random:Math.random()
				},function(channels){
					var inner = "没有找到指定的数据";
					if(channels.length>0){
						inner = "";
	  					$.each(channels,function(i,channel){
	  						inner += "<input name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.ch_no+channel.ch_name+"&nbsp;";
	  					})
	  				}
	  				td_channels.innerHTML = inner;				
				});
			}else{
				$.getJSON("${ctx}/Gm_Channel_json_findAllByScene_idAndCh_offerSer.action?scene_id="+id,{
					random:Math.random()
				},function(types){
					var inner = "没有找到指定的数据";
					if(types.length>0){
						inner = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">";
	  					$.each(types,function(i,type){
	  						inner += "<tr>";
							inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">"+type.chtype_no+type.chtype_name+"</td>";
							inner += "</tr>";
							inner += "<tr>";
							inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">&nbsp;";
							$.each(type.channels,function(j,channel){
								inner += "<input name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.ch_no+channel.ch_name+"&nbsp;";
							})
							inner += "</td>";
							inner += "</tr>";
	  					})
	  					inner += "<tr>";
	  					inner += "<td style=\"border:0px; width: 900px;\">&nbsp;</td>";
	  					inner += "</tr>";
	  					inner += "</table>";
	  				}	  				
	  				td_channels.innerHTML = inner;			
				});
			}
		}
		
		//加载曲线图
		function loadCharts(){		
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
			var seconds = document.getElementById("seconds").value;
			var zhengze = /^(0|[1-9]\d*)$/;			
			if(zhengze.test(hours)==false){
				window.confirm("请输入一个小时整数！");
				return false;
			}
			if(zhengze.test(minutes)==false){
				window.confirm("请输入一个分钟整数！");
				return false;
			}
			if(zhengze.test(seconds)==false){
				window.confirm("请输入一个秒数整数！");
				return false;
			}
			if(hours > 24){
				window.confirm("请输入一个0~24之间的小时整数！");
				return false;
			}
			if(minutes > 60){
				window.confirm("请输入一个0~60之间的分钟整数！");
				return false;
			}
			if(seconds > 60){
				window.confirm("请输入一个0~60之间的秒数整数！");
				return false;
			}
			document.getElementById("container").innerHTML = "正在加载数据，请等待...";			
			$.getJSON("${ctx}/gm_historydata_findHistoryByChannelsAndTime.action?ch_id_list="+channels
				+"&beginTime="+beginTime
				+"&endTime="+endTime
				+"&hours="+hours
				+"&minutes="+minutes
				+"&seconds="+seconds
				,{
				random:Math.random()
			},function(series){				 				
				document.getElementById("container").innerHTML = "";
				var inner = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">";
				if(series.length>0){	    					
					$.each(series,function(i,column){
						inner += "<tr align=\"center\">";
						inner += "<td style=\"width: 150px;\">"+column.time+"</td>";						
						$.each(column.data,function(i,ele){
							if(ele=="null"){								
								inner += "<td></td>";
							}else{							
								inner += "<td>"+ele+"</td>";
							}								
						})
						inner += "</tr>";
					})
				}else{
					document.getElementById("container").innerHTML = "没有找到指定的数据";
				}
				inner += "</table>";
				document.getElementById("container").innerHTML = inner;	
			});
		}
	</script>
  </head>  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<table width="1006" cellpadding="0" cellspacing="0">
  		<tr>
  			<td width="100">输入采集通道</td>
  			<td id="td_channels" width="904">
				用户点击左侧树状菜单上的节点<br/>
				查找到节点下的所有数据通道，<br/>
				如果不是最后一级场景则要对通道进行分类显示．<br/>
  			</td>
  		</tr>
  		<tr>
  			<td width="100"  height="33">数据间隔</td>
  			<td width="904" height="33"> 
  				<input id="hours" type="text" style="width: 40px;" value="1">小时
  				<input id="minutes" type="text" style="width: 40px;" value="0">分钟
  				<input id="seconds" type="text" style="width: 40px;" value="0">秒
  			</td>
  		</tr>
  		<tr>
  			<td width="100">选择时间</td>
  			<td width="904">
  				<input id="beginTime" size="8" onfocus="setday(this,this)" value="${nowDate}">-
  				<input id="endTime" size="8" onfocus="setday(this,this)" value="${nowDate}">
  				<input type="button" onclick="loadCharts()" value="提交">
  			</td>
  		</tr>
  		<tr>
  			<td colspan="2" width="1006" style="position: relative;font-size:12px;">
				<div id="container" style="border: 1 solid #BBD1FA;position:absolute; top:1px; left:-3px; width: 1006px; text-align: center;">请选择查询条件</div>
  			</td>
  		</tr>
  	</table>
  	<script type="text/javascript">
  		if(this.parent.right.scene_tree.selectedFound){
			var curr_node_id = this.parent.right.scene_tree.aNodes[this.parent.right.scene_tree.selectedNode].id;
			var scene_id = curr_node_id.substr(2);
			//alert(scene_id);
			$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
				random:Math.random()
			},function(op_Scene){
				echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank);
			});		
		}
	</script>
  </body>
</html>
  