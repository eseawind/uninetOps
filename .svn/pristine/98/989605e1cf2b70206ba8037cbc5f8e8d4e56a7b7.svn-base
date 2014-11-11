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
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
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
	<script type="text/javascript" src="${ctx }/js/jquery1.8.2.js"></script>
	
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	<script src="http://code.highcharts.com/stock/highstock.js"></script>
	<script src="http://code.highcharts.com/stock/modules/exporting.js"></script>
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
				if(gtype == 00){
					$.getJSON("${ctx}/Gm_Channel_json_findByScene_idAndCh_offerSer_run.action?scene_id="+id,{
						random:Math.random()
					},function(channels){
						var inner = "没有找到指定的数据";
						if(channels.length>0){
							inner_show = "&nbsp;";
							inner_hid = "&nbsp;";
		  					$.each(channels,function(i,channel){
		  						inner_show += "<input class=\"\" name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.scene_name+"-"+channel.ch_name+"&nbsp;";
		  					})
		  				}
						td_channels.innerHTML = "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"border:0px;\" width=\"100%\">"+inner_show+"</td></tr></table>";
					});
				}else{
					$.getJSON("${ctx}/Gm_Channel_json_findAllByScene_idAndCh_offerSer_run.action?scene_id="+id,{
						random:Math.random()
					},function(types){
						var inner = "没有找到指定的数据";
						if(types.length>0){
							inner = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">";
		  					$.each(types,function(i,type){
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
		  					});
		  					inner += "<tr>";
		  					inner += "<td style=\"border:0px; width: 900px;\">&nbsp;</td>";
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
		var j = 0;
		function loadCharts(){
			j = 0;
			var dataType = 	$("input[name='dataType']:checked").val();
			var chNoArr = new Array();
			var channels = "";	
			var chtype = "";
			var isSameType = false;
			$.each(document.getElementsByName("channels"),function(i,channel){
				if(channel.checked){
					channels += channel.value+",";
					//var type = channel.attributes["class"].nodeValue;
					var type = channel.className;
					if(chtype == ""){
						chtype = type;
					}else{
						if(chtype != type){
							isSameType = true;
						}
					}
				}
			});
			if(isSameType){
				alert("请选择相同类型的通道");
				return;
			}
			if(channels.length>0){
				channels = channels.substr(0,channels.length-1);
			}else{
				alert("请输入采集通道");
				return;
			}
			var glValue = document.getElementById("glValue").value;
			var beginTime = document.getElementById("beginTime").value;
			var endTime = document.getElementById("endTime").value;		
			$.ajax({
					url : "${ctx}/gm_historydata_curveAnalysis.action",
					type : "POST",
					dataType : "json",//xml,html,script,json
					data : {
						ch_id_list : channels,
						beginTime : beginTime,
						endTime : endTime,
						dataType : dataType,
						glValue : glValue
					},
					error : function() {
					},
					beforeSend:function(){
						prompt();
					},
					success : function(json) {
						if (json.message != undefined) {
							alert(json.message);
							closeWin();
							return;
						}
						if (json.length < 1) {
							$("#td_container").html("没有查到数据……");
							closeWin();
							return;
						}
						
						Highcharts.setOptions({
						    lang: {
						       shortMonths:['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '十二月'],
						       weekdays: ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
						        
						    }
						});
						
						var chart = new Highcharts.StockChart(
								{
									chart : {
										renderTo : 'td_container',
										events : {
											load : function() {
												setInterval(function() {
													append();
												}, 3000);
											}
										}
									},
									  tooltip : {
									  	pointFormat : '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
										xDateFormat:'%Y-%m-%d %H:%M:%S'
									},
									global: {
        								useUTC: false
    								},
									xAxis: {
							            type: 'datetime',
							            dateTimeLabelFormats: {
							                second: '%Y-%m-%d<br/>%H:%M:%S',
							                minute: '%Y-%m-%d<br/>%H:%M',
							                hour: '%Y-%m-%d<br/>%H:%M',
							                day: '%Y<br/>%m-%d',
							                week: '%Y<br/>%m-%d',
							                month: '%Y-%m',
							                year: '%Y'
							            }
							        },
							        
							        exporting:{
									    url:'${ctx}/ImageExport_chartsExport.action'
								},
							        

									rangeSelector : {
										buttons : [ {
											count : 1,
											type : 'day',
											text : '日'
										} ,
										{
											count : 1,
											type : 'month',
											text : '月'
										},
										{
											type: 'all',
											text: 'All'
										}
										],
										inputEnabled : false,
										selected : 2
									},
									 credits: {
								            enabled: false
								        },
									legend: {
							            enabled: true,
							            verticalAlign: 'top',
							            align: 'center'
							        },
									navigator : {
										enabled : true,
										xAxis: {
											type: 'datetime',
								            dateTimeLabelFormats: {
								                second: '%Y-%m-%d %H:%M:%S',
								                minute: '%Y-%m-%d %H:%M',
								                hour: '%Y-%m-%d %H:%M',
								                day: '%Y %m-%d',
								                week: '%Y %m-%d',
								                month: '%Y-%m',
								                year: '%Y'
								            }
										}
									},
									title : {
										text : ''
									},
									series : json
								});

						function append() {
							j++;
							for ( var k = 0; k < chart.series.length - 1; k++) {//chart.series.length表示一共有几条曲线
								var series = chart.series[k];//选择是第几条曲线
								var name = series.name;
								name = name.substring(0, name.length - 1);
								var chNo = name.split("(")[1];//解析出当前曲线的通道编号
								var is = true;
								for ( var i = 0; i < chNoArr.length; i++) {//chNoArr中保存的是不需要再追加数据的通道编号
									if (chNo == chNoArr[i])//如果当前曲线的通道编号在chNoArr中，表示不需要追加数据
										is = false;
								}
								var seriesLen = chart.series.length - 1;
								if (is) {
									addPoint(chNo, series,j,seriesLen);//如果当前曲线的通道编号不在chNoArr中，表示需要追加数据
								}
							}
								
						}
						function addPoint(chNo, series,num,seriesLen) {//chNo为通道编号， series为Json串，num代表是第几次去取数据
							$.ajax({
								url : "${ctx}/gm_historydata_curveAnalysisAdd.action",
								type : "POST",
								async : false,
								dataType : "json",//xml,html,script,json
								data : {
									chNo : chNo,
									dataBegin : num,
									beginTime : beginTime,
									endTime : endTime,
									dataType : dataType,
									glValue : glValue
								},
								error : function() {
								},
								success : function(data) {
									if (data.length < 1) {
										chNoArr.push([ chNo ]);
									}
									if (chNoArr.length == seriesLen) {
										if (data.length < 1) {
											closeWin();
										}
									}
									if (data.length > 1) {
										for ( var i = 0; i < data.length; i++) {
											series.addPoint(data[i], false);
										}
										chart.redraw();
									}
								}
							});
						}
					}
				});			
		}
		
		var DG = new J.dialog(
				{
					xButton : false,
					btnBar : false,
					maxBtn : false,
					iconTitle : false,
					cover : true,
					title : '提示信息',
					width : '310',
					height : '170',
					id : 'test2',
					page : ""
				});
		function prompt() {
			DG.ShowDialog();
		}
		function closeWin() {
			DG.cancel();
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
		
		function showOrhiddenChannel(c){
			if(c.checked){
				$("#trChannels").css("display","");
			}else{
				$("#trChannels").css("display","none");
			}
		}
		
		function changeState(c){
			if(c.checked){
				$("#glValue").attr("disabled",false);
			}else{
				$("#glValue").attr("disabled",true);
			}
		}
		
		function echoDeviceTree(){
			
		}
	</script>
  </head>  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<input name="lbl_hid" style="display: none;"/>
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
  			<td><input type="checkbox" onclick="changeState(this);"/> 是否过滤数值</td>
  			<td><input id="glValue" disabled="disabled" > &nbsp;格式: &nbsp;0,1,2,3</td>
  		</tr>
  		<tr>
  			<td width="100">起止时间</td>
  			<td width="904">
  			<!-- 	<input id="beginTime" size="8" disabled="disabled" value="${dateView_beginTime }" readonly="readonly" onfocus="setday(this,this)">-
  				<input id="endTime" size="8" disabled="disabled" value="${dateView_endTime }" readonly="readonly" onfocus="setday(this,this)">&nbsp;&nbsp; -->
  				
  				<input id="beginTime" size="8" disabled="disabled" value="${dateView_beginTime }" onFocus="WdatePicker({isShowClear:false,readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" readonly="readonly" onfocus="setday(this,this)">-
  				
  				<input id="endTime" size="8" disabled="disabled" value="${dateView_endTime }" onFocus="WdatePicker({isShowClear:false,readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" readonly="readonly" onfocus="setday(this,this)">&nbsp;&nbsp;
  				<input type="radio" name="dataType" id="origValue" value="origValue" >原始数据&nbsp;&nbsp;
  				<input type="radio" name="dataType" id="corrValue" value="corrValue" checked="checked" >处理后数据
  				<input type="button" onclick="loadCharts()" value="提交">
  			</td>
  		</tr>
  		<tr>
  			<td id="td_container" colspan="2" width="100%" height="405" style="position: relative;" >
				&nbsp;
  			</td>
  		</tr>
  	</table>
<div id="div_qq"> 	

</div>  	
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
