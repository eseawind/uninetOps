<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:v>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gm_devchannelMsg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<SCRIPT type=text/javascript>
<!--
var ie5 = (document.all && document.getElementsByTagName);
var step = 0;
function setSB(v, el, inforEl, message) {
if (ie5 || document.readyState == "complete") {
filterEl = el.children[0];
valueEl = el.children[1];
if (filterEl.style.pixelWidth > 0) {
var filterBackup = filterEl.style.filter;
filterEl.style.filter = ""; 
filterEl.style.filter = filterBackup;
}
filterEl.style.width = v + "%";
valueEl.innerText = v + "%";
inforEl.innerText = message;
//inforEl.innerHTML += "<DIV align=center>"+message +"</DIV>";
}
}
function setSBByStep(v, el, inforEl, message) {
if (ie5 || document.readyState == "complete") {
step = step + v;
filterEl = el.children[0];
valueEl = el.children[1];
if (filterEl.style.pixelWidth > 0) {
var filterBackup = filterEl.style.filter;
filterEl.style.filter = "";
filterEl.style.filter = filterBackup;
}
filterEl.style.width = step + "%";
valueEl.innerText = step + "%"
inforEl.innerText = message;
//inforEl.innerText += message +"<br />";
}
}
var dg = frameElement.lhgDG; 
function checkajaxkill(isneedtoKillAjax){                 
	if(isneedtoKillAjax){                
		//myAjaxCall.abort();                
		alert('删除超时！');
		//var dg = frameElement.lhgDG;  
		dg.curWin.location.reload();                             
	}       
}

var autoPlay;

var dev_id_arr_str = dg.curWin.dev_id_arr_str;

window.onload = function(){
	infor.innerHTML = "<DIV id=gm_Latestdata align=center>删除最新数据中...</DIV>";
	gm_LatestdataDel();
}
 
//删除gm_Latestdata
function gm_LatestdataDel(){
	clearTimeout(autoPlay);	
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_Latestdata_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){							
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_Latestdata,'最新数据删除完成!');
			infor.innerHTML += "<DIV id=gm_Historydata align=center>删除历史数据中...</DIV>";
			gm_HistorydataDel();
		}else{
			alert(isSuc.message);
		}		
	});
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}

//删除gm_Historydata
function gm_HistorydataDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_Historydata_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_Historydata,'历史数据删除完成!');
			infor.innerHTML += "<DIV id=gm_DevChannel align=center>删除设备上报通道配置数据中...</DIV>";
			gm_DevChannelDel();
		}else{
			alert(isSuc.message);
		}
	});
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_DevChannel
function gm_DevChannelDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_DevChannel_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_DevChannel,'设备上报通道配置数据删除完成!');
			infor.innerHTML += "<DIV id=gm_DevFault align=center>删除故障信息数据中...</DIV>";
			gm_DevFaultDel();
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_DevFault
function gm_DevFaultDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_DevFault_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_DevFault,'故障信息数据删除完成!');
			infor.innerHTML += "<DIV id=op_alarmargument align=center>删除报警配置参数数据中...</DIV>";
			op_alarmargumentDel();
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除op_alarmargument
function op_alarmargumentDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_op_alarmargument_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, op_alarmargument,'报警配置参数数据删除完成!');
			infor.innerHTML += "<DIV id=op_DevCtrlSts align=center>删除控制设备状态配置数据中...</DIV>";
			op_DevCtrlStsDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除op_DevCtrlSts
function op_DevCtrlStsDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_op_DevCtrlSts_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, op_DevCtrlSts,'控制设备状态配置数据删除完成!');
			infor.innerHTML += "<DIV id=op_DevCtrlBtn align=center>删除控制设备按钮配置数据中...</DIV>";
			op_DevCtrlBtnDel();
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除op_DevCtrlBtn
function op_DevCtrlBtnDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_op_DevCtrlBtn_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, op_DevCtrlBtn,'控制设备按钮配置数据删除成功!');
			infor.innerHTML += "<DIV id=gm_DevCtrlOperate align=center>删除控制设备操作数据中...</DIV>";
			gm_DevCtrlOperateDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_DevCtrlOperate
function gm_DevCtrlOperateDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_DevCtrlOperate_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_DevCtrlOperate,'控制设备操作数据删除完成!');
			infor.innerHTML += "<DIV id=gm_DevCtrlOperateHistory align=center>删除控制设备操作记录数据中...</DIV>";
			gm_DevCtrlOperateHistoryDel();
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_DevCtrlOperateHistory
function gm_DevCtrlOperateHistoryDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_DevCtrlOperateHistory_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_DevCtrlOperateHistory,'控制设备操作记录数据删除完成!');
			infor.innerHTML += "<DIV id=phone_DevCtrl align=center>删除手机控制设备数据中...</DIV>";
			phone_DevCtrlDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除phone_DevCtrl
function phone_DevCtrlDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_phone_DevCtrl_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, phone_DevCtrl,'手机控制设备数据删除完成!');
			infor.innerHTML += "<DIV id=gm_DevCtrlSts align=center>删除控制设备状态数据中...</DIV>";
			gm_DevCtrlStsDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_DevCtrlSts
function gm_DevCtrlStsDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_DevCtrlSts_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_DevCtrlSts,'控制设备状态数据删除完成!');
			infor.innerHTML += "<DIV id=gm_DevCtrlStsHistory align=center>删除控制设备状态历史数据数据中...</DIV>";
			gm_DevCtrlStsHistoryDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_DevCtrlStsHistory
function gm_DevCtrlStsHistoryDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_DevCtrlStsHistory_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_DevCtrlStsHistory,'控制设备状态历史数据删除完成!');
			infor.innerHTML += "<DIV id=pro_fisheries align=center>删除水产应用配置数据中...</DIV>";
			pro_fisheriesDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除pro_fisheries
function pro_fisheriesDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_pro_fisheries_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, pro_fisheries,'水产应用配置数据删除完成!');
			infor.innerHTML += "<DIV id=gm_Channel align=center>删除采集通道信息数据中...</DIV>";
			gm_ChannelDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_Channel
function gm_ChannelDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_Channel_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_Channel,'采集通道信息数据删除完成!');
			infor.innerHTML += "<DIV id=gm_DevCtrl align=center>删除控制设备信息数据中...</DIV>";
			gm_DevCtrlDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_DevCtrl
function gm_DevCtrlDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_DevCtrl_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_DevCtrl,'控制设备信息数据删除完成!');
			infor.innerHTML += "<DIV id=gm_Devnet align=center>删除网络信息数据中...</DIV>";
			gm_DevnetDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_Devnet
function gm_DevnetDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_Devnet_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_Devnet,'网络信息数据删除完成!');
			infor.innerHTML += "<DIV id=gm_Devsts align=center>删除智能设备状态数据中...</DIV>";
			gm_DevstsDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_Devsts
function gm_DevstsDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_Devsts_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_Devsts,'智能设备状态数据删除完成!');
			infor.innerHTML += "<DIV id=gm_DevstsHis align=center>删除智能设备状态历史数据中...</DIV>";
			gm_DevstsHisDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_DevstsHis
function gm_DevstsHisDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_DevstsHis_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_DevstsHis,'智能设备状态历史数据删除完成!');
			infor.innerHTML += "<DIV id=gm_Device align=center>删除设备信息表数据中...</DIV>";
			gm_DeviceDel();	
		}else{
			alert(isSuc.message);
		}
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
//删除gm_Device
function gm_DeviceDel(){
	clearTimeout(autoPlay);
	var isneedtoKillAjax =true;
	$.getJSON("${ctx}/gm_device_gm_Device_del.action?dev_id_arr_str="+dev_id_arr_str,{						
		random:Math.random()
	},function(isSuc){
		if(isSuc.type==1){
			isneedtoKillAjax =false;
			setSB(isSuc.message, sb, gm_Device,'设备信息表数据删除完成!');
			alert('删除成功！');
			var dg = frameElement.lhgDG;  
			dg.cancel(); 
			dg.curWin.location.href = "${ctx}/devSetup_list.action";	
			//dg.curWin.location.reload();
		}else{
			alert(isSuc.message);
		}		
	});	
	autoPlay = setTimeout(function(){            
		checkajaxkill(isneedtoKillAjax);        
	},300*1000);
}
function fakeProgress(v,s, el,str) {
if (v >= (s+1))
setSB(s, el, infor,str);
else {
setSB(v, el, infor,str);
window.setTimeout("fakeProgress(" + (v + 1) + "," + s + ", document.all['" + el.id + "'],'"+str+"')", 150);
}
}
//-->
</SCRIPT>
  
  </head>
  
  <body>
    <DIV align=center>
<DIV id=sb 
style="BORDER-RIGHT: #ffffff 1px solid; BORDER-TOP: #ffffff 1px solid; BACKGROUND: #99ccff; BORDER-LEFT: medium none; WIDTH: 400px; BORDER-BOTTOM: #cccccc 1px solid; HEIGHT: 14px; TEXT-ALIGN: left">
<DIV id=sbChild1 
style="FILTER: Alpha(Opacity=0, FinishOpacity=80, Style=1, StartX=0, StartY=0, FinishX=100, FinishY=0); OVERFLOW: hidden; WIDTH: 100%; POSITION: absolute; HEIGHT: 12px">
<DIV style="BACKGROUND: #000000; WIDTH: 100% ;height:12px; overflow: 
hidden"></DIV></DIV>
<DIV 
style="FONT-SIZE: 10px; WIDTH: 400px; COLOR: white; FONT-FAMILY: arial; POSITION: absolute; HEIGHT: 14px; TEXT-ALIGN: center"></DIV></DIV>
<P></P>
<DIV id=infor 
style="FONT-SIZE: 11px; WIDTH: 100%; COLOR: #999999; FONT-FAMILY: arial; POSITION: relative; HEIGHT: 14px; TEXT-ALIGN: center"></DIV></DIV>
	</body>
</html>
