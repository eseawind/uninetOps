<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>曲线分析</title>
    
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
		function echoSceneTree(id,name,no,rank){
			loadTd_channels(id,rank);
		}
		
		//加载通道表单
		function loadTd_channels(id,rank){
			var td_channels = document.getElementById("td_channels");
			//td_channels.innerHTML = "正在加载采集通道列表，请等待...";
			td_channels.innerHTML = ".";
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
			var hid_viewType = document.getElementById("hid_viewType").value;		
			$.getJSON("${ctx}/gm_historydata_json_findHistoryByChannelsAndTime.action?ch_id_list="+channels+"&beginTime="+beginTime+"&endTime="+endTime+"&charts_type="+hid_viewType,{
				random:Math.random()
			},function(series){				
				document.getElementById("container").innerHTML = "";
				if(series.length>0){
					var subtitle = "";
					if(hid_viewType == "date"){
						subtitle = "24小时视图";
					}
					if(hid_viewType == "week"){
						subtitle = "最近7日视图";
					}
					if(hid_viewType == "month"){
						subtitle = "最近30日视图";
					}
					if(hid_viewType == "year"){
						subtitle = "全年视图";
					}
					if(hid_viewType == "select"){
						subtitle = "自选视图";
					}					
					//2、曲线分析如果是按7天或30天查询时横坐标做好显示为日期（如05-03）。 0601 UP Wang_Yuliang
					if(hid_viewType == "week" || hid_viewType == "month"){
						chart = new Highcharts.Chart({
							chart: {
								renderTo: 'container',
								defaultSeriesType: 'spline'
							},
							title: {
								text: '曲线分析'
							},
							subtitle: {
								text: subtitle
							},
							xAxis: {
					        	type: 'datetime',
						        dateTimeLabelFormats: {
						            day: '%m-%d',
						            hour: '%m-%d'			               
						        }
					        },
							yAxis: {
								title: {
									text: '当前值'
								},
								labels: {
									formatter: function() {
										return this.value //+'°'
									}
								}
							},
							tooltip: {
								crosshairs: true,
								formatter: function() {
					                return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +' ' + '监测值:<b>' + Highcharts.numberFormat(this.y, 2) + '</b>';
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
							series: series
						});
					}else{
						chart = new Highcharts.Chart({
							chart: {
								renderTo: 'container',
								defaultSeriesType: 'spline'
							},
							title: {
								text: '曲线分析'
							},
							subtitle: {
								text: subtitle
							},
							xAxis: {
					        	type: 'datetime'
					        },
							yAxis: {
								title: {
									text: '当前值'
								},
								labels: {
									formatter: function() {
										return this.value //+'°'
									}
								}
							},
							tooltip: {
								crosshairs: true,
								formatter: function() {
					                return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +' ' + '监测值:<b>' + Highcharts.numberFormat(this.y, 2) + '</b>';
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
							series: series
						});
					}	
				}else{
					document.getElementById("container").innerHTML = "没有找到指定的数据";
				}			
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
  			<td width="100"  height="33">选择视图类型</td>
  			<td width="904" height="33"> 
  				<input name="viewType" type="radio" value="date" checked="checked" onclick="document.getElementById('hid_viewType').value='date';document.getElementById('beginTime').disabled='disabled';document.getElementById('endTime').disabled='disabled';document.getElementById('beginTime').value='${dateView_beginTime}';document.getElementById('endTime').value='${dateView_endTime}'">24小时
  				<input name="viewType" type="radio" value="week" onclick="document.getElementById('hid_viewType').value='week';document.getElementById('beginTime').disabled='disabled';document.getElementById('endTime').disabled='disabled';document.getElementById('beginTime').value='${weekView_beginTime}';document.getElementById('endTime').value='${weekView_endTime}'"> 7天
  				<input name="viewType" type="radio" value="month" onclick="document.getElementById('hid_viewType').value='month';document.getElementById('beginTime').disabled='disabled';document.getElementById('endTime').disabled='disabled';document.getElementById('beginTime').value='${monthView_beginTime}';document.getElementById('endTime').value='${monthView_endTime}'"> 30天
  				<input name="viewType" type="radio" value="year" onclick="document.getElementById('hid_viewType').value='year';document.getElementById('beginTime').disabled='disabled';document.getElementById('endTime').disabled='disabled';document.getElementById('beginTime').value='${yearView_beginTime}';document.getElementById('endTime').value='${yearView_endTime}'"> 全年
  				<input name="viewType" type="radio" value="select" onclick="document.getElementById('hid_viewType').value='select';document.getElementById('beginTime').disabled=false;document.getElementById('endTime').disabled=false;">自定义
  				<input id="hid_viewType" type="hidden" value="date">
  			</td>
  		</tr>
  		<tr>
  			<td width="100">起止时间</td>
  			<td width="904">
  				<input id="beginTime" size="8" disabled="disabled" value="${dateView_beginTime }" readonly="readonly" onfocus="setday(this,this)">-
  				<input id="endTime" size="8" disabled="disabled" value="${dateView_endTime }" readonly="readonly" onfocus="setday(this,this)">
  				<input type="button" onclick="loadCharts()" value="提交">
  			</td>
  		</tr>
  		<tr>
  			<td colspan="2" width="1004" height="405" style="position: relative; background-color: #cccccc;" >
				<div id="container" style="position:absolute; top:1px; left:2px; width: 1000px; height: 400px; text-align: center;">没有找到指定的数据</div>
  			</td>
  		</tr>
  	</table>
 	<script type="text/javascript">
		//默认加载
		var chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container',
				defaultSeriesType: 'spline'
			},
			title: {
				text: '曲线分析'
			},
			subtitle: {
				text: "请输入采集通道、起止日期及曲线类型"
			},
			xAxis: {
	        	type: 'datetime'
	        },
			yAxis: {
				title: {
					text: '当前值'
				},
				labels: {
					formatter: function() {
						return this.value //+'°'
					}
				}
			},
			tooltip: {
				crosshairs: true,
				shared: true
			},
			plotOptions: {
				spline: {
					marker: {
						radius: 4,
						lineColor: '#666666',
						lineWidth: 1
					}
				}
			},
			series: [{
						name: '当前值',
						data: [[0,null]]
					}],
			credits: {
				enabled: false
			}		
		});
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
