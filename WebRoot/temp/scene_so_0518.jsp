<!-- 据魏小华15日需求 此页弃用 Wang_Yuliang 0616-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>水产养殖监控管理系统</title>    
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
		td{
			font-size: 12px;
		}
	-->
	</style>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>
	<script type="text/javascript" language="javascript">
		var height=0;//温度计高度 
		var topvalue=0;//层离上边的高度 
		var zhi=0;//温度值  温度计高度/100 
		var currwenduzhi=0;//当前的温度值 
		var changezhi=0;//要改变的温度值 
		function textchange(si_value) 
		{
		//alert(si_value); 
		var textvalue= si_value*100/120;
		//var textvalue= si_value*100*(100/40)+1;
		if(isNaN(textvalue)||textvalue.length==0) 
		{
			//alert('请填写数字'); 
		}else 
		{ 
		if(textvalue>100){alert('水位值过高值太大');return;} 
		var v=document.getElementById('div_ST10_11'); 
		if(height==0) 
		{ 
		height=parseInt(v.style.height.replace('px',''));//得到层的高度 
		zhi=height/100;//把高度分成100份 
		} 
		if(topvalue==0) 
		{ 
		topvalue=parseInt(v.currentStyle.top.replace('px',''));//得到层离上边的距离 
		} 
		currwenduzhi=parseInt(v.style.height.replace('px',''))/zhi;//得到当前的温度值 
		changezhi=parseInt(textvalue);//改变的温度值 
		
		setInterval(changes,30);//实现渐动的效果 
		} 
		}
		 
		function changes() 
		{ 
		if(currwenduzhi==changezhi) 
		{ 
		clearInterval(changes); 
		} 
		if(currwenduzhi>changezhi) 
		{ 
		currwenduzhi=currwenduzhi-1; 
		}else 
		{ 
		currwenduzhi=currwenduzhi+1; 
		} 
		var v=document.getElementById('div_ST10_11'); 
		var value0=zhi*currwenduzhi; 
		v.style.height=value0+'px'; 
		v.style.top=topvalue+(height-value0)+'px'; 
		}
	</script>	
	<script type="text/javascript">
		var showDevCtrl_timer = null;
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			var hid_gtype = document.getElementById("hid_gtype");
			if(hid_gtype.value != gtype){
				if(gtype == 1){
					//
				}else if(gtype == 2){
					//
				}else if(gtype == 3){
					//
				}else if(gtype == 4){
					//
				}else if(gtype == 5){
					//
				}else if(gtype == 98){
					this.location = "${ctx }/op_scene_toSceneSO_0531.action";
				}else if(gtype == 99){
					//
				}
			}else{
			    //alert("6666666666666");
				showFisheries(id);
				//showAvgDO10_O(id);//取溶解氧的平均值
				showMinDO10_O(id);//取溶解氧的最小值
				showAvgST10_11(id);//取水温的最小值
				showChannel(id);
				showDevCtrl(id);
				showHistory(id);
				if(showDevCtrl_timer!=null){
					clearInterval(showDevCtrl_timer);
				}
				//showDevCtrl_timer = window.setInterval("showDevCtrl('"+id+"')",30*1000);
			}
		}
		
		//加载养殖池信息
		function showFisheries(id){
			var sp_fi_pondNo = document.getElementById("sp_fi_pondNo");
			sp_fi_pondNo.innerHTML = "编号：";
			var sp_scene_name = document.getElementById("sp_scene_name");
			sp_scene_name.innerHTML = "养殖池名称：";
			var sp_fi_userName = document.getElementById("sp_fi_userName");
			sp_fi_userName.innerHTML = "养殖户：";
			var sp_fi_phone = document.getElementById("sp_fi_phone");
			sp_fi_phone.innerHTML = "手机号：";			
			var sp_fi_area = document.getElementById("sp_fi_area");
			sp_fi_area.innerHTML = "面积：";
			var sp_fi_depth = document.getElementById("sp_fi_depth");
			sp_fi_depth.innerHTML = "水深：";
			var sp_fi_aquaticType = document.getElementById("sp_fi_aquaticType");
			sp_fi_aquaticType.innerHTML = "水草种类：";			
			var sp_fi_cultureObject = document.getElementById("sp_fi_cultureObject");
			sp_fi_cultureObject.innerHTML = "养殖对象：";
			var sp_fi_species = document.getElementById("sp_fi_species");
			sp_fi_species.innerHTML = "品种：";
			var sp_fi_batch = document.getElementById("sp_fi_batch");
			sp_fi_batch.innerHTML = "批次：";
			var sp_fi_remainNum = document.getElementById("sp_fi_remainNum");
			sp_fi_remainNum.innerHTML = "剩余数：";
			var sp_fi_productionStage = document.getElementById("sp_fi_productionStage");
			sp_fi_productionStage.innerHTML = "生长阶段：";			
			var td_fi_doyj = document.getElementById("td_fi_doyj");
			td_fi_doyj.innerHTML = "预警：";
			var td_fi_do = document.getElementById("td_fi_do");
			td_fi_do.innerHTML = "报警：";
			
			$.getJSON("${ctx}/fisherirs_findBySceneId.action?sceneId="+id,{
					random:Math.random()
				},function(fisheries){
					if(fisheries!=null){				
						if(fisheries.fi_pondNo!=null && fisheries.fi_pondNo!="null")
						{sp_fi_pondNo.innerHTML = "编号："+fisheries.fi_pondNo;}
						if(fisheries.scene_name!=null && fisheries.scene_name!="null")
						{sp_scene_name.innerHTML = "养殖池名称："+fisheries.scene_name;}
						if(fisheries.fi_userName!=null && fisheries.fi_userName!="null")
						{sp_fi_userName.innerHTML = "养殖户："+fisheries.fi_userName;}
						if(fisheries.fi_phone!=null && fisheries.fi_phone!="null")
						{sp_fi_phone.innerHTML = "手机号："+fisheries.fi_phone;}
						if(fisheries.fi_area!=null && fisheries.fi_area!="null")
						{sp_fi_area.innerHTML = "面积："+fisheries.fi_area;}
						if(fisheries.fi_depth!=null && fisheries.fi_depth!="null")
						{sp_fi_depth.innerHTML = "水深："+fisheries.fi_depth;}
						if(fisheries.fi_aquaticType!=null && fisheries.fi_aquaticType!="null")
						{sp_fi_aquaticType.innerHTML = "水草种类："+fisheries.fi_aquaticType;}
						if(fisheries.fi_cultureObject!=null && fisheries.fi_cultureObject!="null")
						{sp_fi_cultureObject.innerHTML = "养殖对象："+fisheries.fi_cultureObject;}
						if(fisheries.fi_species!=null && fisheries.fi_species!="null")
						{sp_fi_species.innerHTML = "品种："+fisheries.fi_species;}
						if(fisheries.fi_batch!=null && fisheries.fi_batch!="null")
						{sp_fi_batch.innerHTML = "批次："+fisheries.fi_batch;}
						if(fisheries.fi_remainNum!=null && fisheries.fi_remainNum!="null")
						{sp_fi_remainNum.innerHTML = "剩余数："+fisheries.fi_remainNum;}
						if(fisheries.fi_productionStage!=null && fisheries.fi_productionStage!="null")
						{sp_fi_productionStage.innerHTML = "剩余数："+fisheries.fi_productionStage;}
						if(fisheries.fi_doyj!=null && fisheries.fi_doyj!="null")
						{td_fi_doyj.innerHTML = "预警："+fisheries.fi_doyj;}	
						if(fisheries.fi_do!=null && fisheries.fi_do!="null")
						{td_fi_do.innerHTML = "报警："+fisheries.fi_do;}
					}					
				}
			);			
		}
		
		//查询场景中溶解氧的最小值
		function showMinDO10_O(id){
			$.getJSON("${ctx}/op_scene_json_findMinByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=DO10-O",{
				random:Math.random()
			},function(avg){//alert(avg.value);
				var img_DO10_O_src = "${ctx}/images/fisheries/ryt-2.jpg";
				if(avg.value!="???"){
					if(avg.value>=9){
						img_DO10_O_src = "${ctx}/images/fisheries/ryt-3.jpg";
					}else if(avg.value>=3 && avg.value<9){
						img_DO10_O_src = "${ctx}/images/fisheries/ryt-2.jpg";
					}else if(avg.value<3){
						img_DO10_O_src = "${ctx}/images/fisheries/ryt-1.jpg";
					}
				}
				//预警报警值 颜色
				var td_fi_doyj = document.getElementById("td_fi_doyj");
				td_fi_doyj.background="${ctx}/images/fisheries/yj2.gif";
				var td_fi_do = document.getElementById("td_fi_do");
				td_fi_do.background="${ctx}/images/fisheries/yj2.gif";
				if(avg.value!="???"){
					if(avg.value>=5){
						td_fi_doyj.background="${ctx}/images/fisheries/yj2.gif";
						td_fi_do.background="${ctx}/images/fisheries/yj2.gif";
					}else if(avg.value>=3 && avg.value<5){
						td_fi_doyj.background="${ctx}/images/fisheries/yj3.gif";
						td_fi_do.background="${ctx}/images/fisheries/yj3.gif";				
					}else if(avg.value<3){
						td_fi_doyj.background="${ctx}/images/fisheries/yj1.gif";
						td_fi_do.background="${ctx}/images/fisheries/yj1.gif";						
					}
				}
				document.getElementById("img_DO10_O").src = img_DO10_O_src;
				var td_DO10_O = document.getElementById("td_DO10_O");
				td_DO10_O.innerHTML = "<font size='+2'>"+avg.value+"</font><font>mg/l</font>";
			});
		}
		
		//查询场景中溶解氧的平均值
		function showAvgDO10_O(id){
			$.getJSON("${ctx}/op_scene_json_findAvgByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=DO10-O",{
				random:Math.random()
			},function(avg){//alert(avg.value);
				var img_DO10_O_src = "${ctx}/images/fisheries/ryt-2.jpg";
				if(avg.value!="???"){
					if(avg.value>=9){
						img_DO10_O_src = "${ctx}/images/fisheries/ryt-3.jpg";
					}else if(avg.value>=3 && avg.value<9){
						img_DO10_O_src = "${ctx}/images/fisheries/ryt-2.jpg";
					}else if(avg.value<3){
						img_DO10_O_src = "${ctx}/images/fisheries/ryt-1.jpg";
					}
				}
				//预警报警值 颜色
				var td_fi_doyj = document.getElementById("td_fi_doyj");
				td_fi_doyj.background="${ctx}/images/fisheries/yj2.gif";
				var td_fi_do = document.getElementById("td_fi_do");
				td_fi_do.background="${ctx}/images/fisheries/yj2.gif";
				if(avg.value!="???"){
					if(avg.value>=5){
						td_fi_doyj.background="${ctx}/images/fisheries/yj2.gif";
						td_fi_do.background="${ctx}/images/fisheries/yj2.gif";
					}else if(avg.value>=3 && avg.value<5){
						td_fi_doyj.background="${ctx}/images/fisheries/yj3.gif";
						td_fi_do.background="${ctx}/images/fisheries/yj3.gif";				
					}else if(avg.value<3){
						td_fi_doyj.background="${ctx}/images/fisheries/yj1.gif";
						td_fi_do.background="${ctx}/images/fisheries/yj1.gif";						
					}
				}
				document.getElementById("img_DO10_O").src = img_DO10_O_src;
				var td_DO10_O = document.getElementById("td_DO10_O");
				td_DO10_O.innerHTML = "<font size='+2'>"+avg.value+"</font><font>mg/l</font>";
			});
		}
		
		//查询场景中水温的平均值
		function showAvgST10_11(id){
			$.getJSON("${ctx}/op_scene_json_findAvgByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=ST10-11",{
				random:Math.random()
			},function(avg){
				var td_ST10_11 = document.getElementById("td_ST10_11"); 
				td_ST10_11.innerHTML = "<font size='+2'>"+avg.value+"</font><font>℃</font>";
				if(avg.value>=-50 && avg.value<=50){
					changezhi = parseInt(avg.value)+50;
				}else{
					changezhi = 10;
				}	
				textchange(changezhi);
			});
		}
		
		//查询通道当前值
		function showChannel(id){
			var td_ST10_11 = document.getElementById("td_ST10_11"); 
			//td_ST10_11.innerHTML = "正在加载，请等待..."前面的获取不管了，把往页面上写的这一步去掉就o了
			var td_DO10_O = document.getElementById("td_DO10_O");
			//td_DO10_O.innerHTML	= "正在加载，请等待...";前面的获取不管了，把往页面上写的这一步去掉就o了
			var td_time = document.getElementById("td_time");
			//td_time.innerHTML = "正在加载，请等待...";
			td_time.innerHTML = ".";
			var div_channels = document.getElementById("div_channels");
			//div_channels.innerHTML = "<table width=\"101%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"100%\" style=\"padding:0px;border: 1px #bbd1fa solid; line-height: 24px;\">正在加载，请等待...</td></tr></table>";
			div_channels.innerHTML = "<table width=\"101%\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"100%\" style=\"padding:0px;border: 1px #bbd1fa solid; line-height: 24px;\">.</td></tr></table>";
			$.getJSON("${ctx}/op_scene_json_findChannelBySceneID.action?op_Scene.scene_id="+id,{
					random:Math.random()
				},function(list){
					var val_ST10_11 = "数据:???";
					var val_DO10_O = "数据:???";
					var val_time = "???";
					var inner = "<table width=\"101%\" cellpadding=\"0\" cellspacing=\"0\">";
					if(list.length>0){
						$.each(list,function(i,channel){
							value = channel.hida_corrValue+"";
							if(value!="???"){
								value = value.substr(0,(value.indexOf(".")+2));
							}
							if(channel.chtype_no == "ST10-11"){
								val_ST10_11 = "<font size='+1'>"+value+channel.ch_corrUnit+"</font>";
							}else if(channel.chtype_no == "DO10-O"){
								val_DO10_O = "<font size='+1'>"+value+channel.ch_corrUnit+"</font>";
								val_time = channel.hida_record_time;
							}
							inner += "<tr>";
							inner += "<td width=\"40%\" align=\"left\" style=\"padding:0px;border-top: 1px #bbd1fa solid;border-left: 1px #bbd1fa solid;border-right: 1px #bbd1fa solid; line-height: 24px;\">"+channel.ch_name+"</td>";
							inner += "<td width=\"60%\" align=\"left\" style=\"padding:0px;border-top: 1px #bbd1fa solid;border-left: 1px #bbd1fa solid;border-right: 1px #bbd1fa solid; line-height: 24px;\">"+value+channel.ch_corrUnit+"</td>";
							inner += "</tr>";
						})
						inner += "<tr>";
						inner += "<td width=\"40%\" align=\"left\" style=\"padding:0px;border: 1px #bbd1fa solid; line-height: 24px;\">&nbsp;</td>";
						inner += "<td width=\"60%\" align=\"left\" style=\"padding:0px;border: 1px #bbd1fa solid; line-height: 24px;\">&nbsp;</td>";
						inner += "</tr>";
						inner += "</table>";
					}else{
						inner += "<tr>";
						inner += "<td width=\"40%\" align=\"left\" style=\"padding:0px;border: 1px #bbd1fa solid; line-height: 24px;\">没有找到指定的数据</td>";
						inner += "<td width=\"60%\" align=\"left\" style=\"padding:0px;border: 1px #bbd1fa solid; line-height: 24px;\">&nbsp;</td>";
						inner += "</tr>";
						inner += "</table>";
					}					
					//td_ST10_11.innerHTML = val_ST10_11;前面的获取不管了，把往页面上写的这一步去掉就o了
					//td_DO10_O.innerHTML = val_DO10_O;前面的获取不管了，把往页面上写的这一步去掉就o了
					div_channels.innerHTML = inner;
					td_time.innerHTML = val_time;
				}
			);
		}
		
		//曲线图，实时曲线
		function showHistory(id){
			$.getJSON("${ctx}/op_scene_json_findHistoryBySceneID_0518.action?op_Scene.scene_id="+id,{
					random:Math.random()
				},function(lines){
					document.getElementById("container").innerHTML = "";
					if(lines.length>0)
					{
						chart = new Highcharts.Chart({
							chart: {
								renderTo: 'container',
								defaultSeriesType: 'spline',
								events: {
									load: function() {
										setInterval(function() {
											for(var ii=0;ii<chart.series.length;ii++){								
												// set up the updating of the chart each second
												var series = chart.series[ii];
												var ch_no = series.name.substr(0,series.name.indexOf("("))
												$.getJSON("${ctx}/gm_latestdata_json_findHida_corrValueByCh_noByNo.action?ch_no="+ch_no+"&no="+ii,{
													random:Math.random()
												},function(pojo){//alert(pojo.no);
													/**
													 pojo {no:ii,value:value}
													*/
													var x = pojo.time;//(new Date()).getTime(), // current time
														y = pojo.value;
													chart.series[pojo.no].addPoint([x, y], true, true);
												});
											}
										}, 5*60*1000);	
									}
								}
							},
							title: {
								text: '实时数据'
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
							series: lines,
							exporting: {
								enabled: false
							},
							credits: {
								enabled: false
							}
						});
					}else{
						document.getElementById("container").innerHTML = "没有找到指定的数据";
					}
				}
			);
		}
		
		//查询控制设备状态
		function showDevCtrl(id){
			//var src = "${ctx}/images/fisheries/deng-3.jpg";	
			//那边服务器网速慢，不能先置为默认，否则总变默认再等回调，只能默认为上一次的状态了先。	
			//王雨良 2011-05-30 UP 上面那句才是正常的逻辑
			var src = document.getElementById("img_state").src;
			
			var a_qd_href = "#";		
			var a_tz_href = "#";
			var td_deco_sort_inner = "";	
			$.getJSON("${ctx}/op_scene_json_findDevCtrlBySceneID.action?op_Scene.scene_id="+id,{
					random:Math.random()
				},function(list){
					//alert(list.length);
					/**
						json format
						[
							{
								dect_id:'',
								desc_no:'',
								dect_name:'',
								decttype_id:
									{
										decttype_No:'SV-1',
										decttype_Img:'null'
									},
								dev_id:'402880fd2f7b7184012f7bbac9aa0001',
								dect_state:2,
								decst_valid:1,
								btns:
									[
										{
											dectbtn_id:'kz1-1',
											Dectbtn_Name:'开启',
											deco_type:'1',
											dect_id:'kz1',
											dev_id:'402880fd2f7b7184012f7bbac9aa0001',
											dect_ChlNo:'0',
											dect_ctlType:'1',
											dect_ctlDelay:'null'
										},
										...
									]
							},
							...
						]
						
					*/					
					if(list.length>0){
						$.each(list,function(i,devCtrl){	
							if(devCtrl.decttype_id.decttype_No == 'ZYJ-1'){
								//alert(devCtrl.dect_id);			
								if(devCtrl.dect_state == 1){
									src = "${ctx}/images/fisheries/deng-2.jpg";
									document.getElementById("tr_state_3").style.display = "none";
									document.getElementById("tr_state_1").style.display = "block";
								}else if(devCtrl.dect_state == 3){
									src = "${ctx}/images/fisheries/deng-1.jpg";	
									document.getElementById("tr_state_1").style.display = "none";
									document.getElementById("tr_state_3").style.display = "block";																					
								}else{//alert(11);
									//这里对应上面的改变也加了默认
									src = "${ctx}/images/fisheries/deng-3.jpg";
									document.getElementById("tr_state_1").style.display = "none";
									document.getElementById("tr_state_3").style.display = "block";	
								}
								a_qd_href = "javascript:operate('"+devCtrl.dect_id+"',1)";
								a_tz_href = "javascript:operate('"+devCtrl.dect_id+"',2)";	
								td_deco_sort_inner = devCtrl.deco_sort;
							}
						})
					}
					document.getElementById("img_state").src = src;
					document.getElementById("a_qd").href = a_qd_href;
					document.getElementById("a_tz").href = a_tz_href;	
					document.getElementById("td_deco_sort").innerHTML = td_deco_sort_inner;				
				}
			);
		}
		
		//设备控制
		function operate(dect_id,deco_type){
		    //alert("ID="+dect_id);
		    //alert("state="+deco_type);
			var deco_type_cn;
			if(deco_type == 1){
				deco_type_cn = "启动";
			}else if(deco_type == 2){
				deco_type_cn = "停止";
			}else if(deco_type == 3){
				deco_type_cn = "关";
			}else{
				alert("无效操作");
				return;
			}
			if(confirm("是否"+deco_type_cn+"增氧设备?")){			
				$.getJSON("${ctx}/gm_devctrloperate_operate.action?dect_id="+dect_id+"&deco_type="+deco_type,{
						random:Math.random()
					},function(msm){
						alert(msm);				
					}
				);
			}		
		}
	</script>
<style type="text/css">
<!--
body {
	
}
-->
</style>
<script type="text/javascript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<link href="${ctx}/css/fisheries/css.css" rel="stylesheet" type="text/css">
<script src="${ctx}/js/AC_RunActiveContent.js" type="text/javascript"></script>
  </head>
<body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;" onLoad="MM_preloadImages('${ctx}/images/fisheries/jy-01.jpg','${ctx}/images/fisheries/nyqs-01.jpg','${ctx}/images/fisheries/sqjc-01.jpg','${ctx}/images/fisheries/tz-01.jpg','${ctx}/images/fisheries/qd-1.jpg')">
<input id="hid_gtype" type="hidden" value="1">
<table width="100%" border="0" cellspacing="2" cellpadding="0">
  <tr>
    <td width="25%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="6">
      <tr>
        <td class="xie-1"><table width="100%" border="0" cellspacing="2" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09" id="sp_fi_pondNo">编号：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09" id="sp_scene_name">养殖池名称：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09" id="sp_fi_userName">养殖户：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09" id="sp_fi_phone">手机号：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_area">面　积：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_depth">水　深：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="wz-01" id="sp_fi_aquaticType">水草种类：</td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-09" id="sp_fi_cultureObject">养殖对象：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_species">品  种：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_batch">批  次：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_remainNum">剩余数：</span><br></td>
              </tr>
              <tr>
                <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="xie"><span class="wz-01" id="sp_fi_productionStage">生长阶段：</span><br></td>
              </tr>
              <tr>
	            <td height="21" background="${ctx}/images/fisheries/bj-1.jpg" class="wz-01"></td>
	          </tr>             
            </table></td>
          </tr>
        </table></td>
        <td width="75%" class="xie-1"><table width="100%" border="0" cellspacing="2" cellpadding="0" align="right">
          <tr height="2%"></tr>
		  <tr>
		  	<td width="5%"></td>
			<td width="30%" valign="middle" align="right"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="145"><div align="center"><img src="${ctx}/images/fisheries/kzq-1.jpg" width="134" height="139"></div></td>
                </tr>
              <tr>
                <td height="40"><table width="72%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="32%" height="33" class="wz-01">状态:</td>
                    <td width="35%"><div align="center"><img id="img_state" src="${ctx}/images/fisheries/deng-3.jpg" width="29" height="26"></div></td>
					<td id="td_deco_sort" width="33%" cl8ass="wz-01" align="right">控制</td>
                  </tr>
                </table></td>
                </tr>
               <tr>
                <td height="39"><table width="81%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="32" align="center" valign="middle"><a id="a_qd" href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image14','','${ctx}/images/fisheries/qd-1.jpg',1)"><img src="${ctx}/images/fisheries/qd-01.jpg" name="Image14" width="53" height="27" border="0"></a></td>
                    <td align="center" valign="middle"><a id="a_tz" href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image7','','${ctx}/images/fisheries/tz-01.jpg',1)"><img src="${ctx}/images/fisheries/tz-1.jpg" name="Image7" width="53" height="27" border="0"></a></td>
                  </tr>
                </table></td>
                </tr>
              <tr>
                <td height="40"><table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" class="xie">
                  <tr>
                    <td id="td_fi_doyj" height="21" background="${ctx}/images/fisheries/yj2.gif" class="wz-01">预警：</td>
                  </tr>
                   <tr>
	                <td id="td_fi_do" height="21" background="${ctx}/images/fisheries/yj2.gif" class="wz-01">报警：</td>
	              </tr>
                </table></td>
                </tr>
            </table></td>
            <td width="2%"></td>
            <td width="52%" height="298" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
              <tr>
                <td width="80%" height="291" valign="bottom"><table width="460" border="0" cellspacing="0" cellpadding="0" align="center">
                  <tr>
                    <td width="461" height="137" valign="top"><table width="337" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="130"><table width="337" border="0" align="center" cellpadding="0" cellspacing="0">
                          <tr>
                            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <!-- td id="td_time" height="28" class="wz-09" align="center">正在加载，请等待...</td -->
                                <td id="td_time" height="28" class="wz-09" align="center">.</td>
                              </tr>
                            </table></td>
                            </tr>
                          <tr>
                            <td height="10" align="center" valign="top"><img src="${ctx}/images/fisheries/xie-1.jpg" width="90%" height="1"></td>
                            </tr>
                          <tr>
                            <td><table width="337" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="168"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td class="wz-09" colspan="2" align="center">溶解氧 <br></td>
                                  </tr>
                                  <tr>
                                    <td class="wz-01" width="50"><img id="img_DO10_O" src="${ctx}/images/fisheries/ryt-2.jpg" width="36" height="56"></td>
									<td id="td_DO10_O" class="wz-09" align="center"><font size="+2"></font></td>
                                  </tr>
                                </table></td>
                                <td width="6%">&nbsp;</td>
                                <td width="168"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td class="wz-09" colspan="2" align="center">水温<br></td>
                                  </tr>
                                  <tr>
                                    <td width="27" height="56" valign="top" background="${ctx}/images/fisheries/wdt-1.jpg">
	                                    <table width="27" border="0" align="left" cellpadding="0" cellspacing="0">
		                                  <tr>
		                                    <td width="1">&nbsp;</td>
		                                    <td height="45" valign="bottom" style="position: relative;">
		                                      &nbsp;
		                                      <div id="div_ST10_11" style="position: absolute; top: 5px; left: 8px; width: 2px; height: 38px; background-color: red;">&nbsp;</div>
		                                      <img src="${ctx }/images/fisheries/wdt-2.jpg" style="position: absolute; top: 41; left: 8px;">
		                                      <img src="${ctx }/images/fisheries/wdt-2.jpg" style="position: absolute; top: 43; left: 8px;">
											</td>
		                                  </tr>                                  
		                                </table>
                                	</td>
									<td id="td_ST10_11" class="wz-09" align="center" width="145"><font size="+2"></font></td>
                                  </tr>
                                </table></td>
                              </tr>
                            </table></td>
                            </tr>
                        </table></td>
                        <td width="123"><img src="${ctx}/images/fisheries/xh.gif" width="123" height="137"></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr id="tr_state_1" style="display: none;">
                    <td height="154" colspan="2" background="${ctx}/images/dh-1_r2_c1.jpg">
                    <script type="text/javascript">
                      AC_FL_RunContent( 'codebase','http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0','width','460','height','154','src','images/dongh-2','quality','high','pluginspage','http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash','movie','images/fisheries/dongh-2' ); //end AC code
                    </script>
                    <noscript>
	                    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0" width="460" height="154">
	                      <param name="movie" value="images/dongh-2.swf">
	                      <param name="quality" value="high">
	                      <embed src="${ctx}/images/fisheries/dongh-2.swf" quality="high" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="460" height="154"></embed>
	                    </object>
                    </noscript>
                    </td>
                  </tr>
                  <tr id="tr_state_3">
                    <td height="154" colspan="2" width="100%" align="right"><img src="${ctx}/images/fisheries/dh-1_r2_c1.jpg" width="460" height="154"></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
            <td width="5%"></td>
          </tr>
		  <tr height="2%"></tr>
        </table></td>
      </tr>
      <tr>
                <td class="xie-1" style=" position: relative; ">
        	<div id="div_channels" style="position: absolute; top:0xp;left:0px; HEIGHT: 279; BACKGROUND-COLOR: transparent; OVERFLOW-Y: scroll;OVERFLOW-X:hidden; scrollbar-face-color: #9EBFE8; scrollbar-shadow-color: #FFFFFF; scrollbar-highlight-color: #FFFFFF; scrollbar-3dlight-color: #9EBFE8; scrollbar-darkshadow-color: #9EBFE8; scrollbar-track-color: #FFFFFF; scrollbar-arrow-color: #FFFFFF;">
				<table width="101%" cellpadding="0" cellspacing="0"><tr><td width="100" style="padding:0px; border: 1px #bbd1fa solid; line-height: 24px;">请选择场景...</td></tr></table>
        	</div>
        </td>
        <td width="75%" class="xie-1"><table width="100%" border="0" cellspacing="2" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr>
                <td width="75%" class="wz-09">变化曲线：</td>
                <td width="11%"><img src="${ctx}/images/fisheries/ry-1.jpg" width="70" height="22" style="display: none;"></td>
                <td width="2%">&nbsp;</td>
                <td width="12%"><img src="${ctx}/images/fisheries/wd-1.jpg" width="64" height="22" style="display: none;"></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="245" style=" text-align: center;">
            	<div id="container" style="width: 621px;height: 243px;">
            		请选择场景...
            	</div>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
<script type="text/javascript">
	//默认加载
	//alert(this.parent.scene_tree.selectedFound);
	if(this.parent.right.scene_tree.selectedFound){
		var curr_node_id = this.parent.right.scene_tree.aNodes[this.parent.right.scene_tree.selectedNode].id;
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
