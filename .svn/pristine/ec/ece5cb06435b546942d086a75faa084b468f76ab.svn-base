<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<script type="text/javascript">
		
			var type = "scene";
		function findChannel(){
			var netAddr = document.getElementById("netAddr").value;
			var num = /^[A-Za-z0-9]+$/;
			if(netAddr == ""){
				alert("设备地址不能为空……");
				document.getElementById("netAddr").focus();
				return;
			}
			if(!num.test(netAddr)){
				alert("请填写正确的设备地址……");
				document.getElementById("netAddr").value="";
				document.getElementById("netAddr").focus();
				return;
			}
			$.ajax({
				   url: "${ctx}/gm_devsts_findChannelByAddr.action", 
			       type: "POST",  
			       dataType: "json",//xml,html,script,json
			       data:{net_addr:netAddr},
			       error: function(){},  
			       success: function(json){ 
			       	var html = "";
			       	var chtypeNo = "";	 
			       	jQuery.each(json, function(i, list){
			       	  	var ch_id = list.ch_id;
			       	  	var ch_name = list.ch_name;
			       	  	var scene_name = list.scene_name;
			       	  	var chtype_no = list.chtype_no;
			       	  	var chtype_displayName = list.chtype_displayName;
						if(chtype_no == chtypeNo){
							html += "<input id="+ch_id+" name=\"channelsValue\" menu="+scene_name+"-"+ch_name+" type=\"checkbox\" value="+ch_id+" />"+scene_name+"-"+ch_name;
						}else{
							html += "<HR width=\"100%\" color=\"#9fa9dc\" SIZE=\"1\"><div style=\"color: #3b6dcd;\">" + chtype_no + "-" + chtype_displayName + "</div><input id="+ch_id+" name=\"channelsValue\" menu="+scene_name+"-"+ch_name+" type=\"checkbox\" value="+ch_id+" />"+scene_name+"-"+ch_name;
							chtypeNo=chtype_no;
						}
			       	});
			       	var date = "开始时间：<input id=\"beginTime\" readonly=\"readonly\" name=\"beginTime\" type=\"text\" onClick=\"WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" value=\"\">结束时间：<input id=\"endTime\" readonly=\"readonly\" name=\"endTime\" type=\"text\" onClick=\"WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" value=\"\"><input type=\"button\" onclick=\"findHistorydata();\" value=\"查询\">"
			       	jQuery("#channel").html(html);
			       	jQuery("#date").html(date);
			       	nowDate();
	        	   }  
				}); 
		}
		
		function nowDate(){
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
		
		function findHistorydata(){
			var channels = "";
			var menu = "";
			var select = "";
			if(document.getElementById("isSelectAll").checked){
				select = true;
			}else{
				select = false;
			}
			var statTime = document.getElementById("beginTime").value;
			var overTime = document.getElementById("endTime").value;
			var netAddr = document.getElementById("netAddr").value;
			var begin = document.getElementById("beginTime").value.split("-");
			var end = document.getElementById("endTime").value.split("-");
			var beginTime = new Date(begin[0],begin[1],begin[2]);
			var endTime = new Date(end[0],end[1],end[2]);
			$.each(document.getElementsByName("channelsValue"),function(i,channel){
				if(channel.checked){
					channels += channel.value+",";
					//menu += channel.attributes["menu"].nodeValue+",";
					menu += $(channel).attr("menu")+",";
					//select += channel.id+","
				}
			})
			//if(select.length >0 ){
				//select = select.substr(0,select.length-1);
			//}			
			if(channels.length>0){			
				channels = channels.substr(0,channels.length-1);
			}else{				
				alert("请输入采集通道");
				return;
			}
			if(menu.length > 0){
				menu = menu.substr(0,menu.length-1);
			}
			if(Date.parse(statTime.replace(/\-/g,"/"))>Date.parse(overTime.replace(/\-/g,"/"))){
				alert("时间输入有误……");
				return;
			}
			if(begin != "" ){
				if(end == ""){
					alert("请输入结束时间……");
					return false;
				}
			}
			if(end != "" ){
				if(begin == ""){
					alert("请输入开始时间……");
					return false;
				}
			}
			document.getElementById("channelsHidden").value=channels;
			document.getElementById("netAddrHidden").value=netAddr;
			document.getElementById("menuHidden").value=menu;
			document.getElementById("beginTimeHidden").value=statTime;
			document.getElementById("endTimeHidden").value=overTime;
			document.getElementById("typeHidden").value=type;
			document.getElementById("selectHidden").value=select;
			$("#myform").submit();
			//window.location.href = "${ctx}/gm_historydata_findHistorydataDevByCh_id.action?channels=" + channels + "&netAddr="+netAddr +"&select="+select + "&menu="+encodeURIComponent(menu)+"&beginTime="+statTime+"&endTime="+overTime+"&type="+type;
		}
		
		function checkOrUncheckAll(c){
			if(c.checked){
				$.each(document.getElementsByName("channelsValue"),function(i,channel){
					channel.checked = true;
				});
			}else{
				$.each(document.getElementsByName("channelsValue"),function(i,channel){
					channel.checked = false;
				});
			}
		}
		
		function hidAddr(){
			document.getElementById("addr").style.display="none";
			type = "scene";
		}
		function showAddr(){
			document.getElementById("addr").style.display="";
			type = "addr"
		}
		
		function echoSceneTree(id,name,no,rank,gtype){
			if(type == "scene"){
				loadTd_channels(id);
			}
		}
		
		function loadTd_channels(id){
			$.ajax({
				   url: "${ctx}/Gm_Channel_findChannelByScene.action", 
			       type: "POST",  
			       dataType: "json",//xml,html,script,json
			       data:{scene_id:id},
			       error: function(){},  
			       success: function(json){ 
			       	var html = "";
			       	var chtypeNo = "";	 
			       	jQuery.each(json, function(i, list){
			       	  	var ch_id = list.chId;
			       	  	var ch_name = list.chName;
			       	  	var scene_name = list.sceneName;
			       	  	var chtype_no = list.chtypeNo;
			       	  	var chtype_displayName = list.chtypeName;
						if(chtype_no == chtypeNo){
							html += "<input id="+ch_id+" name=\"channelsValue\" menu="+scene_name+"-"+ch_name+" type=\"checkbox\" value="+ch_id+" />"+scene_name+"-"+ch_name;
						}else{
							html += "<HR width=\"100%\" color=\"#9fa9dc\" SIZE=\"1\"><div style=\"color: #3b6dcd;\">" + chtype_no + "-" + chtype_displayName + "</div><input id="+ch_id+" name=\"channelsValue\" menu="+scene_name+"-"+ch_name+" type=\"checkbox\" value="+ch_id+" />"+scene_name+"-"+ch_name;
							chtypeNo=chtype_no;
						}
			       	});
			       	var date = "开始时间：<input id=\"beginTime\" readonly=\"readonly\" name=\"beginTime\" type=\"text\" onClick=\"WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" value=\"\">结束时间：<input id=\"endTime\" readonly=\"readonly\" name=\"endTime\" type=\"text\" onClick=\"WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" value=\"\"><input type=\"button\" onclick=\"findHistorydata();\" value=\"查询\">"
			       	jQuery("#channel").html(html);
			       	jQuery("#date").html(date);
			       	nowDate();
	        	   }  
				}); 
				}
		function showOrhiddenChannel(c){
			if(c.checked){
				$("#channel").css("display","");
			}else{
				$("#channel").css("display","none");
			}
		}
	</script>
  </head>
  
  <body style="width:100%;height:100%;overflow: auto;padding: 10px;">
    	<table style="font: 12px/1.5 tahoma,arial,宋体;">
    		<tr>
    			<td>
    				<input type="radio" name="type" onclick="hidAddr()" checked="checked">按场景查询
    				<input type="radio" name="type" onclick="showAddr()">按设备地址
    				<input id="isSelectAll" type="checkbox" onclick="checkOrUncheckAll(this)" > 是/否全选
    				<input type="checkbox" checked="checked" onclick="showOrhiddenChannel(this)" > 显示/隐藏通道
    			</td>
    		</tr>
    		<tr>
    			<td id="addr" style="display:none">
    				设备地址：<input type="text" id="netAddr" name="" />
    						<input type="button" onclick="findChannel()" value="查询">
    			</td>
    		</tr>
    		<tr>
    			<td id="channel"></td>
    		</tr>
    		<tr>
    			<td id="date"></td>
    		</tr>
    	</table>
    	<form action="${ctx}/gm_historydata_findHistorydataDevByCh_id.action" method="post" id="myform">
    		<input type="hidden" id="channelsHidden" name="channels" value="" >
    		<input type="hidden" id="netAddrHidden" name="netAddr" value="" >
    		<input type="hidden" id="menuHidden" name="menu" value="" >
    		<input type="hidden" id="beginTimeHidden" name="beginTime" value="" >
    		<input type="hidden" id="endTimeHidden" name="endTime" value="" >
    		<input type="hidden" id="typeHidden" name="type" value="" >
    		<input type="hidden" id="selectHidden" name="select" value="" >
    	</form>
  </body>
</html>
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
