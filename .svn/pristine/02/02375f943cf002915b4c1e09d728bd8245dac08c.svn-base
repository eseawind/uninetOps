<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>曲线分析</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
		
		.tr_hid{
			display:none;
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
		}
		
		//加载通道表单
		function loadTd_channels(id,gtype){
			var td_channels = document.getElementById("td_channels");
			//td_channels.innerHTML = "正在加载采集通道列表，请等待...";
			td_channels.innerHTML = ".";
			if(gtype == 2 || gtype == 3 || gtype == 4 || gtype == 5 || gtype == 97 || gtype == 99){
				$.getJSON("${ctx}/Gm_Channel_json_findAllByScene_idAndCh_offerSer.action?scene_id="+id,{
					random:Math.random()
				},function(types){
					var inner = "没有找到指定的数据";
					if(types.length>0){
						inner = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">";
	  					$.each(types,function(i,type){
	  						//if(type.chtype_no == "DO10-T" || type.chtype_no == "DO10-O" || type.chtype_no == "DL-C" || type.chtype_no == "NULL-P"){
		  						inner += "<tr>";
								inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">"+type.chtype_no+type.chtype_name;
								inner += "<input type=\"checkbox\" onclick=\"fenlei_quanxuan(this,'ck_"+type.chtype_no+"')\"/> 全选 / 反选";
								inner += "</td>";
								inner += "</tr>";
								inner += "<tr>";
								inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">&nbsp;";
								$.each(type.channels,function(j,channel){
									inner += "<input class=\"ck_"+type.chtype_no+"\" name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.scene_name+"-"+channel.ch_name+"&nbsp;";
								})
								inner += "</td>";
								inner += "</tr>";
							//}else{
								//inner += "<tr class=\"tr_hid\">";
								//inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">"+type.chtype_no+type.chtype_name+"</td>";
								//inner += "</tr>";
								//inner += "<tr class=\"tr_hid\">";
								//inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">&nbsp;";
								//$.each(type.channels,function(j,channel){
								//	inner += "<input name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.scene_name+"-"+channel.ch_name+"&nbsp;";
								//})
								//inner += "</td>";
								//inner += "</tr>";
							//}
	  					})
	  					inner += "<tr>";
	  					inner += "<td style=\"border:0px; width: 900px;\">&nbsp;</td>";
	  					inner += "</tr>";
	  					inner += "</table>";
	  				}	  				
	  				td_channels.innerHTML = inner;			
				});
			}else{
				$.getJSON("${ctx}/Gm_Channel_json_findByScene_idAndCh_offerSer.action?scene_id="+id,{
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
	  				//td_channels.innerHTML = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"100%\">"+inner_show+"</td></tr><tr class=\"tr_hid\"><td width=\"100%\">"+inner_hid+"</td></tr></table>";
	  				td_channels.innerHTML = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"100%\">"+inner_show+"</td></tr></table>";
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
			$.getJSON("${ctx}/gm_historydata_json_findHistoryByChannelsAndTime_0609.action?ch_id_list="+channels+"&beginTime="+beginTime+"&endTime="+endTime+"&charts_type="+hid_viewType,{
				random:Math.random()
			},function(charts){
				if(charts.length>0){	
					document.getElementById("td_container").innerHTML = "";				
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
						for(var i=0;i<charts.length;i++){							
							document.getElementById("td_container").innerHTML += "<div id=\"container_"+i+"\" style=\"position:absolute; top:"+(1+(i*400))+"px; left:2px; width: 1000px; height: 400px; text-align: center;\">&nbsp;</div>";
						}
						for(var i=0;i<charts.length;i++){
							var c = charts[i];
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container_'+i,
									defaultSeriesType: 'spline'
								},
								title: {
									text: c.chtype_displayName
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
								series: c.lines,
								credits: {
									enabled: false
								}
							});
						}
					}else{
						for(var i=0;i<charts.length;i++){							
							document.getElementById("td_container").innerHTML += "<div id=\"container_"+i+"\" style=\"position:absolute; top:"+(1+(i*400))+"px; left:2px; width: 1000px; height: 400px; text-align: center;\">&nbsp;</div>";
						}
						for(var i=0;i<charts.length;i++){
							var c = charts[i];
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container_'+i,
									defaultSeriesType: 'spline'
								},
								title: {
									text: c.chtype_displayName
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
								series: c.lines,
								credits: {
									enabled: false
								}
							});
						}	
					}	
				}else{
					document.getElementById("td_container").innerHTML = "没有找到指定的数据";
				}			
			});
		}
		
		//显示更多
		function showMore(){
			var lbl_hids = $("label.lbl_hid");
			var tr_hids = $("tr.tr_hid");
			for(var i=0;i<lbl_hids.length;i++){
				lbl_hids[i].style.display = "block";
			}
			for(var j=0;j<tr_hids.length;j++){
				tr_hids[j].style.display = "block";
			}
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
  </head>  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<input name="lbl_hid" style="display: none;"/>
  	<table width="1006" cellpadding="0" cellspacing="0">
  		<tr>
  			<td cols width="100">
  				输入采集通道<br/>
  				<input type="button" value="更多" onclick="showMore()" style="display: none;">
  				<input type="checkbox" onclick="quanxuan(this)"/> 全选 / 反选
  			</td>
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
  	</table>
  	<table width="1006" cellpadding="0" cellspacing="0" style="border: 0px none #fff;"><tr>
		<td id="td_container" colspan="2" width="1004" height="405" style="position: relative; border: 0px;" >
			&nbsp;
		</td> 	
	</tr></table>
 	<script type="text/javascript">
		//默认加载
		document.getElementById("td_container").innerHTML += "<div id=\"container\" style=\"position:absolute; top:1px; left:2px; width: 1000px; height: 400px; text-align: center;\">&nbsp;</div>";
		chart = new Highcharts.Chart({
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
