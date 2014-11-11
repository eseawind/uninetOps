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
	<link href="${ctx}/css/qxz/css.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx}/js/jquery-1.3.2.min.js"></script>
	<style type="text/css">
		<!--
			body {
					
			}	
			td{
				font-size: 12px;
			}
		-->
	</style>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>
	<script src="${ctx}/js/qxz/AC_RunActiveContent.js" type="text/javascript"></script>	
	<script type="text/javascript">
		//响应场景树
		var showAvgFX_timer = null;//风向
		var showHistory_timer = null;//历史曲线
		var showAvgKQWD_timer = null;//空气温度
		var showAvgKQSD_timer = null;//空气湿度
		var showAvgTYFS_timer = null;//太阳辐射
		var showAvgDQYQ_timer = null;//大气压强
		var showAvgFS_timer = null;//风速
		function echoSceneTree(id,name,no,rank,gtype){
			var hid_gtype = document.getElementById("hid_gtype");
			if(hid_gtype.value != gtype){
				if(gtype == 1){//养殖池
					this.location = "${ctx }/op_scene_toSceneSO_0616.action";//养殖池的实时监控页面
				}else if(gtype == 2){//池塘组
					//
				}else if(gtype == 3){//基地
					//
				}else if(gtype == 4){//企业
					//
				}else if(gtype == 5){//项目
					//
				}else if(gtype == 97){//视频点
				
				}else if(gtype == 98){//气象站
					//
				}else if(gtype == 99){//移动场景
					//
				}
			}else{
			    //alert("6666666666666");
				showHistory(id);//历史曲线图（包括：空气温度变化趋势，空气湿度变化趋势，太阳辐射变化趋势）
				showAvgKQWD(id);//空气温度
				showAvgKQSD(id);//空气湿度
				showAvgTYFS(id);//太阳辐射
				showAvgDQYQ(id);//大气压强
				showAvgFS(id);//风速
				showAvgFX(id);//风向
				channelInformation(id);
				if(showHistory_timer!=null){
					clearInterval(showHistory_timer);
				}
				if(showAvgKQWD_timer!=null){
					clearInterval(showAvgKQWD_timer);
				}
				if(showAvgKQSD_timer!=null){
					clearInterval(showAvgKQSD_timer);
				}
				if(showAvgTYFS_timer!=null){
					clearInterval(showAvgTYFS_timer);
				}
				if(showAvgDQYQ_timer!=null){
					clearInterval(showAvgDQYQ_timer);
				}
				if(showAvgFS_timer!=null){
					clearInterval(showAvgFS_timer);
				}
				if(showAvgFX_timer!=null){
					clearInterval(showAvgFX_timer);
				}
				showAvgFX_timer = window.setInterval("showAvgFX('"+id+"')",5*60*1000);//风向
				showHistory_timer = window.setInterval("showHistory('"+id+"')",5*60*1000);//历史曲线图
				showAvgKQWD_timer = window.setInterval("showAvgKQWD('"+id+"')",5*60*1000);//空气温度
				showAvgKQSD_timer = window.setInterval("showAvgKQSD('"+id+"')",5*60*1000);//空气湿度
				showAvgTYFS_timer = window.setInterval("showAvgTYFS('"+id+"')",5*60*1000);//太阳辐射
				showAvgDQYQ_timer = window.setInterval("showAvgDQYQ('"+id+"')",5*60*1000);//大气压强
				showAvgFS_timer = window.setInterval("showAvgFS('"+id+"')",5*60*1000);//风速
				
				
				
			}
		}
	</script>
	<script type="text/javascript">
		<!--
			function MM_preloadImages() { //v3.0
			  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			}
		//-->
	</script>	
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
	
	<script type="text/javascript" language="javascript">
		var height=0;//温度计高度 
		var topvalue=0;//层离上边的高度 
		var zhi=0;//温度值  温度计高度/100 
		var currwenduzhi=0;//当前的温度值 
		var changezhi=0;//要改变的温度值 
		function textchange(si_value) 
		{
		//alert("si_value="+si_value); 
		var textvalue= (parseInt(si_value)+40)*100/118;
		//alert("textvalue="+textvalue);
		//var textvalue= si_value*100*(100/40)+1;
		if(isNaN(textvalue)||textvalue.length==0) 
		{
			//alert('请填写数字'); 
		}else 
		{ 
		//if(textvalue>100){alert('水位值过高值太大');return;} 
		var v=document.getElementById('div_tel'); 
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
		var v=document.getElementById('div_tel'); 
		var value0=zhi*currwenduzhi; 
		v.style.height=value0+'px'; 
		v.style.top=topvalue+(height-value0)+'px'; 
		}
	</script>
	
	<script type="text/javascript">
		//Wang_Yuliang 0711 UP 据小华姐修改意见
		/**
		function shidujiValue(val){
			//alert(val);
			if(!isNaN(val)&&val.length!=0){
				var value = val*1.7-80;
				//alert(value);
				if(value > 50){
					value = 50;
				}
				if(value < -80){
					value = -80;
				}
				var v=document.getElementById('shidu');
				v.style.bottom = value+'px';
			}
		}
		*/
		function shidujiValue(val){
			//alert(val);
			if(!isNaN(val)&&val.length!=0){
				var value = val*1.68;
				//alert(value);
				if(value > 168){
					value = 0;
				}
				if(value < 0){
					value = 168;
				}
				var v=document.getElementById('shidu');
				v.style.top = (168-value)+'px';
			}
		}
	</script>
	
	<script type="text/javascript">
		function taiyangfushe(val){
			//alert(val);
			if(!isNaN(val)&&val.length!=0){
				var value = val*0.1-30;
				//alert(value);
				if(value > 150){
					value = 150;
				}
				if(value < -30){
					value = -30;
				}
				var v=document.getElementById('fushe');
				v.style.bottom = value+'px';
			}
		}
	</script>
	<script type="text/javascript">
		/**据0711需求修改 Wang_Yuliang
		function daqiyaqiang(val){
			//alert(val);
			if(!isNaN(val)&&val.length!=0){
				var value = val*0.68-608;
				//alert(value);
				if(value > 140){
					value = 140;
				}
				if(value < 4){
					value = 4;
				}
				var v=document.getElementById('yaqiang');
				v.style.bottom = value+'px';
			}
		}
		*/
		function daqiyaqiang(val){
			//alert(val);
			if(!isNaN(val)&&val.length!=0){
				var value = (val-80)*3.36;
				//alert(value);
				if(value > 168){
					value = 0;
				}
				if(value < 0){
					value = 168;
				}
				var v=document.getElementById('yaqiang');
				v.style.top = (168-value)+'px';
			}
		}
	</script>
	
	<script type="text/javascript">
		//空气温度
		function showAvgKQWD(id) {
			//alert("场景:"+id)
			$.getJSON("${ctx}/op_scene_json_findAvgByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=KQSD1201-T",{
				random:Math.random()
				},function(avg){
				var div_tel_value = document.getElementById("div_tel_value");
				//var wd = document.getElementById("kongqiwendu");
				div_tel_value.innerHTML = avg.value +"℃";
				//wd.innerHTML = avg.value +"℃";
				textchange(avg.value);
				
				//alert("空气温度:"+avg.value);				
			});
		}
		
		//空气湿度
		function showAvgKQSD(id) {
			$.getJSON("${ctx}/op_scene_json_findAvgByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=KQSD1201-H",{
				random:Math.random()
				},function(avg){
				var shiduvalue = document.getElementById("shiduvalue");
				//var sd = document.getElementById("kongqishidu");
				shiduvalue.innerHTML = avg.value +"%";
				//sd.innerHTML = avg.value +"%";
				shidujiValue(avg.value);
				//alert(avg.value);					
			});
		}
		
		//太阳辐射
		function showAvgTYFS(id) {
			$.getJSON("${ctx}/op_scene_json_findAvgByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=CMP6-P",{
				random:Math.random()
				},function(avg){
				var	fushevalue = document.getElementById("fushevalue");
				//var fushe = document.getElementById("taiyangfushe");
				fushevalue.innerHTML = avg.value +"W/㎡";
				//fushe.innerHTML = avg.value +"W/㎡";
				taiyangfushe(avg.value);
				//alert(avg.value);
			});
		}
		
		//大气压强
		function showAvgDQYQ(id) {
			$.getJSON("${ctx}/op_scene_json_findAvgByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=PA-P",{
				random:Math.random()
				},function(avg){
				var yaqiangvalue = document.getElementById("yaqiangvalue");
				//var pa = document.getElementById("pa");
				//pa.innerHTML = avg.value +"Pa";
				yaqiangvalue.innerHTML = avg.value +"KPa";
				daqiyaqiang(avg.value);		
			});
		}
		
		//风速
		function showAvgFS(id) {
			$.getJSON("${ctx}/op_scene_json_findAvgByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=1200-S",{
				random:Math.random()
				},function(avg){
				var fengsuvalue = document.getElementById("fengsuvalue");
				//var fs = document.getElementById("fs");
				fengsuvalue.innerHTML = avg.value +"m/s"
				//fs.innerHTML = avg.value +"MPH";
				//alert(avg.value);					
			});
		}
		
		//风向
		function showAvgFX(id) {
			$.getJSON("${ctx}/op_scene_json_findAvgByScene_idAndChtype_id.action?op_Scene.scene_id="+id+"&chtype_no=1200-D",{
				random:Math.random()
				},function(avg){
				//alert(avg.value);
				var value = avg.value;
				if(avg.value >0 && avg.value < 91){
					if(value < 16){
						document.getElementById("fengxiang_15").src="${ctx }/images/fengxiang/yuan-15.jpg";
					}else if(value > 15 && value < 31){
						document.getElementById("fengxiang_30").src="${ctx }/images/fengxiang/yuan-30.jpg";
					}else if(value > 30 && value < 46){
						document.getElementById("fengxiang_45").src="${ctx }/images/fengxiang/yuan-45.jpg";
					}else if(value > 45 && value < 61){
						document.getElementById("fengxiang_60").src="${ctx }/images/fengxiang/yuan-60.jpg";
					}else if(value > 60 && value < 76){
						document.getElementById("fengxiang_75").src="${ctx }/images/fengxiang/yuan-75.jpg";
					}else {
						document.getElementById("fengxiang_90").src="${ctx }/images/fengxiang/yuan-90.jpg";
					}
				}else if(avg.value > 90 && avg.value < 181){
					if(value < 106){
						document.getElementById("fengxiang_105").src="${ctx }/images/fengxiang/yuan-105.jpg";
					}else if(value > 105 && value < 121){
						document.getElementById("fengxiang_120").src="${ctx }/images/fengxiang/yuan-120.jpg";
					}else if(value > 120 && value < 136){
						document.getElementById("fengxiang_135").src="${ctx }/images/fengxiang/yuan-135.jpg";
					}else if(value > 135 && value < 151){
						document.getElementById("fengxiang_150").src="${ctx }/images/fengxiang/yuan-150.jpg";
					}else if(value > 150 && value < 166){
						document.getElementById("fengxiang_165").src="${ctx }/images/fengxiang/yuan-165.jpg";
					}else {
						document.getElementById("fengxiang_180").src="${ctx }/images/fengxiang/yuan-180.jpg";
					}
				}else if(avg.value > 180 && avg.value < 271){
					if(value < 196){
						document.getElementById("fengxiang_195").src="${ctx }/images/fengxiang/yuan-195.jpg";
					}else if(value > 195 && value < 211){
						document.getElementById("fengxiang_210").src="${ctx }/images/fengxiang/yuan-210.jpg";
					}else if(value > 210 && value < 226){
						document.getElementById("fengxiang_225").src="${ctx }/images/fengxiang/yuan-225.jpg";
					}else if(value > 225 && value < 241){
						document.getElementById("fengxiang_240").src="${ctx }/images/fengxiang/yuan-240.jpg";
					}else if(value > 240 && value < 256){
						document.getElementById("fengxiang_255").src="${ctx }/images/fengxiang/yuan-255.jpg";
					}else {
						document.getElementById("fengxiang_270").src="${ctx }/images/fengxiang/yuan-270.jpg";
					}
				}else if(avg.value > 270 && avg.value < 361){
					if(value < 286){
						document.getElementById("fengxiang_285").src="${ctx }/images/fengxiang/yuan-285.jpg";
					}else if(value > 285 && value < 301){
						document.getElementById("fengxiang_300").src="${ctx }/images/fengxiang/yuan-300.jpg";
					}else if(value > 300 && value < 316){
						document.getElementById("fengxiang_315").src="${ctx }/images/fengxiang/yuan-315.jpg";
					}else if(value > 315 && value < 331){
						document.getElementById("fengxiang_330").src="${ctx }/images/fengxiang/yuan-330.jpg";
					}else if(value > 330 && value < 346){
						document.getElementById("fengxiang_345").src="${ctx }/images/fengxiang/yuan-345.jpg";
					}else {
						document.getElementById("fengxiang_360").src="${ctx }/images/fengxiang/yuan-360.jpg";
					}
				}
				var fengxiangValue = document.getElementById("fengxiang");
				//var fx = document.getElementById("fx");
				//fx.innerHTML = avg.value;
				fengxiangValue.innerHTML = avg.value;		
			});
		}
	</script>
	
	<script type="text/javascript">
		function channelInformation(id){
		//alert("laile");
			$.getJSON("${ctx}/op_scene_findChannelInformation.action?op_Scene.scene_id="+id,{
				random:Math.random()
				},function(list){
					var html = "";	
					var time="";
					//alert("huilaile");			
					jQuery.each(list, function(i, channelList) {
						var name = channelList.name;
						var value = channelList.value;
						time=channelList.time;
						html += "<tr><td width='40%' align='center' style='padding:0px;border-bottom:1px #bbd1fa solid;border-right: 1px #bbd1fa solid; line-height: 24px;'>"+name+"</td><td width='60%' align='center' style='padding:0px;border-bottom:1px #bbd1fa solid;border-left: 1px #bbd1fa solid; line-height: 24px;'>"+value+"</td></tr>";
					})
					time+="";					
					$("#channel").html(html);
					$("#recordTime").html(time);
			});
	}
	</script>
	

	<style type="text/css">
		
		.tabTitle{
			border-top:1px solid #d8d8d8;
		    border-bottom:1px solid #d8d8d8;
		    background-image:url(${ctx }/images/fisheries/teb-1.jpg);	
			float: left;
			width: 208px;
			height: 30px
		
		}
		.selectTabTitle{
			border-left:1px solid #d8d8d8;
			font-weight: bold;
			border-top:2px solid #045ca4;
			border-right: 1px solid #d8d8d8;
			background-image:url(${ctx }/images/fisheries/teb-2.jpg);
			float: left;
			width: 208px;
			height: 30px
		}
		.compost{
			width: 100%;
			height: 100%;
			display: none;
		}
		.selectCompost{
			width: 100%;
			height: 100%;
			border-top: 0px;
			
		}
   body {
			font: 12px/1.5 tahoma,arial,宋体;
		}
	</style>
	

	
	
	
	<style type="text/css">
		
		.tabTitle{
			border-top:1px solid #d8d8d8;
		    border-bottom:1px solid #d8d8d8;	
			float: left;
			width: 208px;
			height: 30px
		
		}
		.selectTabTitle{
			border-left:1px solid #d8d8d8;
			font-weight: bold;
			border-top:2px solid #045ca4;
			border-right: 1px solid #d8d8d8;
			float: left;
			width: 208px;
			height: 30px
		}
		.compost{
			width: 100%;
			height: 100%;
			display: none;
		}
		.selectCompost{
			width: 100%;
			height: 100%;
			border-top: 0px;
			
		}
   body {
			font: 12px/1.5 tahoma,arial,宋体;
		}
	</style>
	
	<script type="text/javascript">

		function mouseover(title){
			for(var i = 1;i<4;i++){
				document.getElementById("title"+i).className="tabTitle";
				document.getElementById("content"+i).style.display="none";
			}
			title.className="selectTabTitle";
			var str=title.getAttribute("id");
			str=str.substring(str.length-1,str.length);
			document.getElementById("content"+str).style.display="block";
		}
	</script>
	
	
	<script type="text/javascript">
		var chart1,chart2,chart3; //定义图表对象
		var timer1,timer2,timer3; //定义计时器
	</script>
	
	<script type="text/javascript">

		function showHistory(id){
			his(id,'KQSD1201-T','content1','空气温度变化趋势',chart1,timer1);
			his(id,'KQSD1201-H','content2','空气湿度变化趋势',chart2,timer2);
			his(id,'CMP6-P','content3','太阳辐射变化趋势',chart3,timer3);//RAD-10
		}
		//曲线图，实时曲线
		function his(id,chtype_no,content,title,chart,timer){
			$.getJSON("${ctx}/op_scene_json_findHistoryBySceneIDAndChtype_no.action?op_Scene.scene_id="+id+"&chtype_no="+chtype_no,{
					random:Math.random()
				},function(lines){
					document.getElementById(content).innerHTML = "";
					if(lines.length>0)
					{
						chart = new Highcharts.Chart({
							chart: {
								renderTo: content,
								defaultSeriesType: 'spline',
								height: 215,
								width: 625,							
								events: {
									load: function() {
										var timer = setInterval(function() {
											for(var ii=0;ii<chart.series.length;ii++){	//？？？？？？？？？？？？？？？？？？？？？							
												// set up the updating of the chart each second
												var series = chart.series[ii];
												var ch_no = series.name.substr(0,series.name.indexOf("("))//截取出通道编号
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
								text: title
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
						document.getElementById(content).innerHTML = "没有找到指定的数据";
					}
				}
			);
		}
	</script>
	
	
	
	
 </head> 
<body style="width:100%;height:100%;overflow: auto;padding: 10px;" onLoad="MM_preloadImages('${ctx }/images/jy-01.jpg','${ctx }/images/nyqs-01.jpg','${ctx }/images/sqjc-01.jpg')">
<input id="hid_gtype" type="hidden" value="98">
<table width="100%" border="0" cellspacing="2" cellpadding="0">
  <tr>
    <td><table width="75%" border="0" align="center" cellpadding="0" cellspacing="6">
      <tr>
        <td height="299" class="xie-1"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td height="91"><div align="center" class="wz-07">鹏瑶生态园气象站</div><br><div align="center" id="recordTime" style="font-size:18"></div></td>
          </tr>
            <tr>
              <td><div align="center"><img src="${ctx }/images/qxz/tu-1.jpg" width="188" height="193"></div></td>
            </tr>
          </table></td>
        <td width="720" class="xie-1"><table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
          <tr>
            <td width="40%" height="25" valign="top" class="wz-09"><div align="center" class="wz-09">北</div></td>
            <td width="15%" valign="top" class="wz-09"><div align="left">　温　度</div></td>
            <td width="15%" valign="top" class="wz-09"><div align="left">湿　度</div></td>
            <td width="15%" valign="top" class="wz-09"><div align="left">太阳辐射</div></td>
            <td width="15%" valign="top" class="wz-09"><div align="left">大气压力</div></td>
          </tr>
          <tr>
            <td valign="middle"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="wz-08"><div align="center" class="wz-09">西</div></td>
                <td width="195" height="200" class="wz-08"><table border="0" cellpadding="0" cellspacing="0" width="195">
                  <!-- fwtable fwsrc="未命名" fwbase="yuan-01.jpg" fwstyle="Dreamweaver" fwdocid = "1084872268" fwnested="0" -->
                  <tr>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="35" height="1" border="0" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="19" height="1" border="0" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="17" height="1" border="0" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="26" height="1" border="0" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="22" height="1" border="0" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="23" height="1" border="0" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="19" height="1" border="0" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="34" height="1" border="0" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="1" height="1" border="0" alt="" /></td>
                  </tr>
                  <tr>
                    <td><img id="fengxiang_315" name="yuan01_r1_c1" src="${ctx }/images/qxz/yuan-01.jpg" width="35" height="56" border="0" id="yuan01_r1_c1" alt="" /></td>
                    <td><img id="fengxiang_330" name="yuan01_r1_c2" src="${ctx }/images/qxz/yuan-02.jpg" width="19" height="56" border="0" id="yuan01_r1_c2" alt="" /></td>
                    <td><img id="fengxiang_345" name="yuan01_r1_c3" src="${ctx }/images/qxz/yuan-03.jpg" width="17" height="56" border="0" id="yuan01_r1_c3" alt="" /></td>
                    <td><img id="fengxiang_360" name="yuan01_r1_c4" src="${ctx }/images/qxz/yuan-04.jpg" width="26" height="56" border="0" id="yuan01_r1_c4" alt="" /></td>
                    <td><img id="fengxiang_15" name="yuan01_r1_c5" src="${ctx }/images/qxz/yuan-05.jpg" width="22" height="56" border="0" id="yuan01_r1_c5" alt="" /></td>
                    <td><img id="fengxiang_30" name="yuan01_r1_c6" src="${ctx }/images/qxz/yuan-06.jpg" width="23" height="56" border="0" id="yuan01_r1_c6" alt="" /></td>
                    <td><img id="fengxiang_45" name="yuan01_r1_c7" src="${ctx }/images/qxz/yuan-07.jpg" width="19" height="56" border="0" id="yuan01_r1_c7" alt="" /></td>
                    <td><img id="fengxiang_60" name="yuan01_r1_c8" src="${ctx }/images/qxz/yuan-08.jpg" width="34" height="56" border="0" id="yuan01_r1_c8" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="1" height="56" border="0" alt="" /></td>
                  </tr>
                  <tr>
                    <td><img id="fengxiang_300" name="yuan01_r2_c1" src="${ctx }/images/qxz/yuan-09.jpg" width="35" height="24" border="0" id="yuan01_r2_c1" alt="" /></td>
                    <td height="92" colspan="6" rowspan="4" valign="top" background="${ctx }/images/qxz/yuan-10.jpg"><table width="84%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="22">&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="21"><div id="fengsuvalue" align="center" class="wz-02"></div></td>
                      </tr>
                      <tr>
                        <td height="24">&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="21"><div id="fengxiang" align="center" class="wz-02"></div></td>
                      </tr>
                    </table></td>
                    <td><img id="fengxiang_75" name="yuan01_r2_c8" src="${ctx }/images/qxz/yuan-011.jpg" width="34" height="24" border="0" id="yuan01_r2_c8" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="1" height="24" border="0" alt="" /></td>
                  </tr>
                  <tr>
                    <td><img id="fengxiang_285" name="yuan01_r3_c1" src="${ctx }/images/qxz/yuan-012.jpg" width="35" height="22" border="0" id="yuan01_r3_c1" alt="" /></td>
                    <td><img id="fengxiang_90" name="yuan01_r3_c8" src="${ctx }/images/qxz/yuan-013.jpg" width="34" height="22" border="0" id="yuan01_r3_c8" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="1" height="22" border="0" alt="" /></td>
                  </tr>
                  <tr>
                    <td><img id="fengxiang_270" name="yuan01_r4_c1" src="${ctx }/images/qxz/yuan-014.jpg" width="35" height="23" border="0" id="yuan01_r4_c1" alt="" /></td>
                    <td><img id="fengxiang_105" name="yuan01_r4_c8" src="${ctx }/images/qxz/yuan-015.jpg" width="34" height="23" border="0" id="yuan01_r4_c8" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="1" height="23" border="0" alt="" /></td>
                  </tr>
                  <tr>
                    <td><img id="fengxiang_255" name="yuan01_r5_c1" src="${ctx }/images/qxz/yuan-016.jpg" width="35" height="23" border="0" id="yuan01_r5_c1" alt="" /></td>
                    <td><img id="fengxiang_120" name="yuan01_r5_c8" src="${ctx }/images/qxz/yuan-017.jpg" width="34" height="23" border="0" id="yuan01_r5_c8" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="1" height="23" border="0" alt="" /></td>
                  </tr>
                  <tr>
                    <td><img id="fengxiang_240" name="yuan01_r6_c1" src="${ctx }/images/qxz/yuan-018.jpg" width="35" height="52" border="0" id="yuan01_r6_c1" alt="" /></td>
                    <td><img id="fengxiang_225" name="yuan01_r6_c2" src="${ctx }/images/qxz/yuan-019.jpg" width="19" height="52" border="0" id="yuan01_r6_c2" alt="" /></td>
                    <td><img id="fengxiang_210" name="yuan01_r6_c3" src="${ctx }/images/qxz/yuan-020.jpg" width="17" height="52" border="0" id="yuan01_r6_c3" alt="" /></td>
                    <td><img id="fengxiang_195" name="yuan01_r6_c4" src="${ctx }/images/qxz/yuan-021.jpg" width="26" height="52" border="0" id="yuan01_r6_c4" alt="" /></td>
                    <td><img id="fengxiang_180" name="yuan01_r6_c5" src="${ctx }/images/qxz/yuan-022.jpg" width="22" height="52" border="0" id="yuan01_r6_c5" alt="" /></td>
                    <td><img id="fengxiang_165" name="yuan01_r6_c6" src="${ctx }/images/qxz/yuan-023.jpg" width="23" height="52" border="0" id="yuan01_r6_c6" alt="" /></td>
                    <td><img id="fengxiang_150" name="yuan01_r6_c7" src="${ctx }/images/qxz/yuan-024.jpg" width="19" height="52" border="0" id="yuan01_r6_c7" alt="" /></td>
                    <td><img id="fengxiang_135" name="yuan01_r6_c8" src="${ctx }/images/qxz/yuan-025.jpg" width="34" height="52" border="0" id="yuan01_r6_c8" alt="" /></td>
                    <td><img src="${ctx }/images/qxz/spacer.gif" width="1" height="52" border="0" alt="" /></td>
                  </tr>
                </table></td>
                <td class="wz-08"><div align="center" class="wz-09">东</div></td>
              </tr>
            </table></td>
            <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="center" style="background-color: red; ">
                  <table width="26" border="0" align="right" cellpadding="0" cellspacing="0">
                    <tr>
                      <td height="210" valign="bottom" background="${ctx }/images/qxz/1-1.jpg">
	                      	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		                        <!-- tr>
		                          <td height="24" valign="middle" class="wz-02">&nbsp;</td>
		                        </tr -->
		                        <tr>
		                          <td height="186" class="wz-02" style="position: relative;">
		                          	<!--美工源码 img src="${ctx }/images/qxz/xie-3.jpg" width="10" height="25" -->
		                          	<div id="div_tel" style="position: absolute;top:5px; left: 8px; height: 181px; width: 10px; background-color: red;"></div>
		                          </td>
		                        </tr>
		                        <tr>
		                          <td height="24" align="center" valign="middle" class="wz-02"><img src="${ctx }/images/qxz/xie-2.jpg" width="26" height="26"></td>
		                        </tr>
	                      	</table>
                      	</td>
                    </tr>
                  </table>
                </div></td>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="30" valign="middle" class="wz-02">&nbsp;60℃</td>
                  </tr>
                  <tr>
                    <td height="30" valign="middle" class="wz-02">&nbsp;40℃</td>
                  </tr>
                  <tr>
                    <td height="30" valign="middle" class="wz-02">&nbsp;20℃</td>
                  </tr>
                  <tr>
                    <td height="30" valign="middle" class="wz-02">&nbsp;0℃</td>
                  </tr>
                  <tr>
                    <td height="30" valign="middle" class="wz-02">&nbsp;-20℃</td>
                  </tr>
                  <tr>
                    <td height="30" valign="middle" class="wz-02">&nbsp;-40℃</td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
            <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="center">
                  <table width="26" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="210" valign="top" background="${ctx }/images/qxz/1-2.jpg">
	                      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 15px;">
	                        <tr>
	                          <td height="168px;" style="position:relative;" valign="top"><img id="shidu" style="position: absolute; top:168px;" src="${ctx }/images/qxz/xie-1.jpg" width="26" height="4"></td>
	                        </tr>
	                      </table>
                      </td>
                    </tr>
                  </table>
                </div></td>
                <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="36" valign="middle" class="wz-02">100%</td>
                    </tr>
                    
                    
                    <tr>
                      <td height="25" valign="bottom" class="wz-02">80%</td>
                    </tr>
                    <tr>
                      <td height="30" valign="bottom" class="wz-02">60%</td>
                    </tr>
                    <tr>
                      <td height="35" valign="bottom" class="wz-02">40%</td>
                    </tr>
                    <tr>
                      <td height="37" valign="bottom" class="wz-02">20%</td>
                    </tr>
                    <tr>
                      <td height="30" valign="bottom" class="wz-02">0%</td>
                    </tr>
                </table></td>
              </tr>
            </table></td>
            <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="center">
                  <table width="26" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="210" valign="bottom" background="${ctx }/images/qxz/1-3.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td><img id="fushe" style="position: relative; bottom: px;" src="${ctx }/images/qxz/xie-1.jpg" width="26" height="4"></td>
                        </tr>
                        <tr>
                          <td height="20" valign="middle" class="wz-02">&nbsp;</td>
                        </tr>
                        <tr>
                          <td height="20" valign="middle" class="wz-02">&nbsp;</td>
                        </tr>
                      </table></td>
                    </tr>
                  </table>
                </div></td>
                <td valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="0" class="wz-02">1800</td>
                  </tr>
                  <tr>
                    <td height="0" class="wz-02">1600</td>
                  </tr>
                  <tr>
                    <td height="0" class="wz-02">1400</td>
                  </tr>
                  <tr>
                    <td height="0" class="wz-02">1200</td>
                  </tr>
                  <tr>
                    <td height="0" class="wz-02">1000</td>
                  </tr>
                  <tr>
                    <td height="12" class="wz-02">800</td>
                  </tr>
                  <tr>
                    <td height="0" class="wz-02">600</td>
                  </tr>
                  <tr>
                    <td height="0" class="wz-02">400</td>
                  </tr>
                  <tr>
                    <td height="0" class="wz-02">200</td>
                  </tr>
                  <tr>
                    <td height="0" class="wz-02">0</td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
            <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="center">
                  <table width="26" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="210" valign="top" background="${ctx }/images/qxz/1-2.jpg">
                      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 15px;">
                        <tr>
                          <td valign="top" height="168" style="position: relative;"><img id="yaqiang" style="position: absolute; top:168px;" src="${ctx }/images/qxz/xie-1.jpg" width="26" height="4"></td>
                        </tr>
                        <!-- tr>
                          <td height="20" valign="middle" class="wz-02" style="background-color: green;">&nbsp;</td>
                        </tr -->
                      </table></td>
                    </tr>
                  </table>
                </div></td>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="38" valign="bottom" class="wz-02">120</td>
                    </tr>
                    <tr>
                      <td height="30" valign="bottom" class="wz-02">110</td>
                    </tr>
                    <tr>
                      <td height="34" valign="bottom" class="wz-02">100</td>
                    </tr>
                    <tr>
                      <td height="35" valign="bottom" class="wz-02">90</td>
                    </tr>
                    <tr>
                      <td height="37" valign="bottom" class="wz-02">80</td>
                    </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="34" align="center" valign="middle" class="wz-09">南</td>
            <td align="center" valign="middle"><table width="90" border="1" cellpadding="0" cellspacing="0" bordercolor="#414141" bgcolor="#FFFFFF">
              <tr>
                <td height="22"><div id="div_tel_value" align="center" class="wz-02"></div></td>
              </tr>
            </table></td>
            <td align="center" valign="middle"><table width="90" border="1" cellpadding="0" cellspacing="0" bordercolor="#414141" bgcolor="#FFFFFF">
              <tr>
                <td height="22"><div id="shiduvalue" align="center" class="wz-02"></div></td>
              </tr>
            </table></td>
            <td align="center" valign="middle"><table width="90" border="1" cellpadding="0" cellspacing="0" bordercolor="#414141" bgcolor="#FFFFFF">
              <tr>
                <td height="22"><div id="fushevalue" align="center" class="wz-02"></div></td>
              </tr>
            </table></td>
            <td align="center" valign="middle"><table width="90" border="1" cellpadding="0" cellspacing="0" bordercolor="#414141" bgcolor="#FFFFFF">
              <tr>
                <td height="22"><div id="yaqiangvalue" align="center" class="wz-02"></div></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="277" class="xie-1">
        	<div style="height: 100%">
        		<table id="channel" width="100%" cellspacing="0" cellpadding="0">
        			
        		</table>
        	</div>
        </td>
        <td width="720" class="xie-1"><table width="100%" border="0" cellspacing="2" cellpadding="0">
          
          <tr>
            <td>
            	<div style="width: 627px;height: 260px;">
			    	<div style="width: 626px;height: 30px;text-align: center;border-left: 1px solid #d8d8d8;border-right: 1px solid #d8d8d8" >
			    		<div id="title1" onclick="mouseover(this)" class="selectTabTitle" style="border-left: 0px;">空气温度曲线</div>
			    		<div id="title2" onclick="mouseover(this)" class="tabTitle">空气湿度曲线</div>
			    		<div id="title3" onclick="mouseover(this)" class="tabTitle" style="border-right: 0px;">太阳辐射曲线</div>
			    	</div>
			    	<div style="width: 626px;height: 230px; border-left: 1px solid #d8d8d8;border-right: 1px solid #d8d8d8;border-bottom: 1px solid #d8d8d8;">
			    		<div id="content1" class="selectCompost" ></div>
			    		<div id="content2" style="width: 626px; height: 235px; display: none"></div>
			    		<div id="content3" style="width: 626px; height: 235px; display: none"></div>
			    	</div>
   		 		</div>
            </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
<script type="text/javascript">
	if('${op_Scene.scene_id}'!=''){
		$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id=${op_Scene.scene_id}",{
			random:Math.random()
		},function(op_Scene){
			window.parent.right.scene_tree.selectById('s_'+op_Scene.scene_id);
			echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
		});	
	}else{
		//默认加载	
		//alert(this.parent.scene_tree);
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
	}
</script>
</body>
</html>
