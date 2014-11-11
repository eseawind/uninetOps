<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>编辑网络信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/date.js"></script>
	<script type="text/javascript">		
		function checkForm(){
			//验证网络编号非空
			var net_no = document.getElementById("gm_DevNet.net_no");
			if(net_no.value.length == 0){
				alert("请填写网络编号");
				return false;
			}
			//验证设备地址非空，数字
			var net_addr = document.getElementById("gm_DevNet.net_addr");
			if(net_addr.value.length == 0){				
				alert("请填写设备地址");
				return false;
			}
			var re_net_addr = /^[0-9]*$/;
			if(!re_net_addr.test(net_addr.value)){
				alert("设备地址只能是0-9的数字");
				return false;
			}	
			//验证网络类型不能为-1
			var net_type = document.getElementById("gm_DevNet.net_type");
			if(net_type.value == "-1"){
				alert("请选择网络类型");
				return false;
			}			
			//验证网内角色不能为-1
			var net_role = document.getElementById("gm_DevNet.net_role");
			if(net_role.value == "-1"){
				alert("请选择网内角色");
				return false;
			}
			//验证关联设备不能为-1
			var dev_id = document.getElementById("gm_DevNet.dev_id.dev_id");
			if(dev_id.value == "-1"){
				alert("请选择关联设备");
				return false;
			}
			//验证网络深度不能为空，数字 远程网深度只能为0
			var net_depth = document.getElementById("gm_DevNet.net_depth");
			if(net_depth.value.length == 0){
				alert("请填写网络深度");
				return false;
			}
			var re_net_depth = /^\d$/;
			if(!re_net_depth.test(net_depth.value)){
				alert("网络深度只能是0-9的数字");
				return false;
			}			
			if(net_type.value == 0){
				if(net_depth.value != 0){
					alert("远程网 M2M 深度只能为0");
					return false;
				}
			}	
			//连接方式不能为-1
			var net_linkSts = document.getElementById("gm_DevNet.net_linkSts");
			if(net_linkSts.value == "-1"){
				alert("请选择供电方式");
				return false;
			}
			//远程网 M2M SIM卡号不能为空
			var net_sim = document.getElementById("gm_DevNet.net_sim");
			if(net_type.value == 0){
				var re_net_sim = /^[0-9]*$/;
				if(net_sim.value.length == 0){
					alert("远程网 M2M SIM卡号不能为空");
					return false;
				}else if(!re_net_sim.test(net_sim.value)){
					alert("SIM卡号格式不合法");
					return false;
				}
			}
			//SIM卡号 非空时 格式验证
			if(net_sim.value.length != 0){
				var re_net_sim = /^[0-9]*$/;
				if(!re_net_sim.test(net_sim.value)){
					alert("SIM卡号格式不合法");
					return false;
				}
			}
			//应用类型 非空时 格式验证
			var net_appType = document.getElementById("gm_DevNet.net_appType");
			if(net_appType.value.length != 0){
				var re_net_appType = /^\d$/;
				if(!re_net_appType.test(net_appType.value)){
					alert("应用类型格式不合法");
					return false;
				}
			}
			//协议类型 非空时 格式验证
			var net_pltType = document.getElementById("gm_DevNet.net_pltType");
			if(net_pltType.value.length != 0){
				var re_net_pltType = /^\d$/;
				if(!re_net_pltType.test(net_pltType.value)){
					alert("协议类型格式不合法");
					return false;
				}
			}
			//网络类型 为远程网 M2M2 则父节点为null
			if(net_type.value == 0){
				document.getElementById("gm_DevNet.net_pid").disabled = true;
			}
			return true;
		}
		
		//响应场景树
		function echoSceneTree(id,name,no){
			//无响应
		}
		
		//网络类型下拉列表改变
		function net_typeOnChange(obj){
			var net_role = document.getElementById("gm_DevNet.net_role");
			net_role.length = 0;
			net_role.add(new Option("-- 请选择 --","-1"));
			var dev_id = document.getElementById("gm_DevNet.dev_id.dev_id");
			dev_id.length = 0;
			dev_id.add(new Option("-- 请选择 --","-1"));
			if(obj.value == 0){
				net_role.add(new Option("网关","01"));
				net_role.add(new Option("独立设备","02"));
				$.getJSON("${ctx}/gm_devnet_findFreeM2M.action",{
					random:Math.random()
				},function(list){
					$.each(list,function(i,device){
						dev_id.add(new Option(device.dev_no+"-"+device.dev_name,device.dev_id));
					})
					//在编辑的时候要加上自己原有的关联设备
					//dev_id.add(new Option("${gm_DevNet.dev_id.dev_no}-${gm_DevNet.dev_id.dev_name}","${gm_DevNet.dev_id.dev_id}"));	
				});
				document.getElementById("sp_gm_DevNet.net_sim").style.display = "block";
			}else if(obj.value == 1){
				net_role.add(new Option("RD","11"));
				net_role.add(new Option("ED","12"));
				net_role.add(new Option("CD","13"));
				net_role.add(new Option("LD","14"));
				$.getJSON("${ctx}/gm_devnet_findFreeWSN.action",{
					random:Math.random()
				},function(list){
					$.each(list,function(i,device){
						dev_id.add(new Option(device.dev_no+"-"+device.dev_name,device.dev_id));
					})	
					//在编辑的时候要加上自己原有的关联设备
					//dev_id.add(new Option("${gm_DevNet.dev_id.dev_no}-${gm_DevNet.dev_id.dev_name}","${gm_DevNet.dev_id.dev_id}"));	
				});
				document.getElementById("sp_gm_DevNet.net_sim").style.display = "none";
			}else if(obj.value == 2){
				net_role.add(new Option("传感器","21"));
				net_role.add(new Option("变送器","22"));
				$.getJSON("${ctx}/gm_devnet_findFreeISENSOR.action",{
					random:Math.random()
				},function(list){
					$.each(list,function(i,device){
						dev_id.add(new Option(device.dev_no+"-"+device.dev_name,device.dev_id));
					})
					//在编辑的时候要加上自己原有的关联设备
					//dev_id.add(new Option("${gm_DevNet.dev_id.dev_no}-${gm_DevNet.dev_id.dev_name}","${gm_DevNet.dev_id.dev_id}"));		
				});
				document.getElementById("sp_gm_DevNet.net_sim").style.display = "none";
			}
		}
		
		
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="javascript:window.location.href='${ctx }/gm_devnet_page.action?page.pageSize=12&gm_DevNet.net_addr='">网络信息管理</a> 》
  	<a href="#">编辑网络信息</a> <br/> 
    <form action="${ctx }/gm_devnet_edit.action?no=${gm_DevNet.net_no }&dest_id=${dest_id}" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">       		
			<input name="gm_DevNet.net_id" type="hidden" value="${gm_DevNet.net_id }"/>
			<c:choose>
				<c:when test="${gm_DevNet.net_pid == null}">
					<!-- root -->					
					<input name="gm_DevNet.net_state" type="hidden" value="${gm_DevNet.net_state }">
					<input id="gm_DevNet.net_pid" name="gm_DevNet.net_pid" type="hidden" value="${gm_DevNet.net_pid }">					
					<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
						<tr class="list_head">
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络编号：</td>
							<td>
								<input id="gm_DevNet.net_no" name="gm_DevNet.net_no" value="${gm_DevNet.net_no }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>设备地址：</td>
							<td>
								<input disabled="disabled" id="gm_DevNet.net_addr" value="${gm_DevNet.net_addr }"/> 
							 	<input name="gm_DevNet.net_addr" type="hidden" value="${gm_DevNet.net_addr }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络类型：</td>
							<td>
								<select disabled="disabled" id="gm_DevNet.net_type" onchange="net_typeOnChange(this)">
									<option value="-1">-- 请选择 --</option>
									<c:choose>
										<c:when test="${gm_DevNet.net_type == 0}">
											<option value="0" selected="selected">远程网 M2M</option>
											<option value="1">无线传感网络 wsn</option>
											<option value="2">有线传感网 智能传感器</option>									
										</c:when>
										<c:when test="${gm_DevNet.net_type == 1}">
											<option value="0">远程网 M2M</option>
											<option value="1" selected="selected">无线传感网络 wsn</option>
											<option value="2">有线传感网 智能传感器</option>									
										</c:when>
										<c:when test="${gm_DevNet.net_type == 2}">
											<option value="0">远程网 M2M</option>
											<option value="1">无线传感网络 wsn</option>
											<option value="2" selected="selected">有线传感网 智能传感器</option>									
										</c:when>
									</c:choose>
								</select> 
								<input type="hidden" name="gm_DevNet.net_type" value="${gm_DevNet.net_type }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网内角色：</td>
							<td>
								<select disabled="disabled" id="gm_DevNet.net_role">
									<option value="-1">-- 请选择 --</option>						
								</select> 
								<input type="hidden" name="gm_DevNet.net_role" value="${gm_DevNet.net_role }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>关联设备：</td>
							<td>
								<select disabled="disabled" id="gm_DevNet.dev_id.dev_id">
									<option value="-1">-- 请选择 --</option>
								</select>
								<input name="gm_DevNet.dev_id.dev_id" type="hidden" value="${gm_DevNet.dev_id.dev_id}"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络深度：</td>	
							<td>
								<input disabled="disabled" id="gm_DevNet.net_depth" value="${gm_DevNet.net_depth }"/> M2M设备为0,以此类推为0,1,2...整型
								<input type="hidden" name="gm_DevNet.net_depth" value="${gm_DevNet.net_depth }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>连接方式：</td>
							<td>
								<select id="gm_DevNet.net_linkSts" name="gm_DevNet.net_linkSts" >
									<option value="-1">-- 请选择 --</option>
									<c:choose>
										<c:when test="${gm_DevNet.net_linkSts == 0}">
											<option value="0" selected="selected">无效</option>
											<option value="1">长连接</option>
											<option value="2">短连接</option>
										</c:when>
										<c:when test="${gm_DevNet.net_linkSts == 1}">
											<option value="0">无效</option>
											<option value="1" selected="selected">长连接</option>
											<option value="2">短连接</option>
										</c:when>
										<c:when test="${gm_DevNet.net_linkSts == 2}">
											<option value="0">无效</option>
											<option value="1">长连接</option>
											<option value="2" selected="selected">短连接</option>
										</c:when>
									</c:choose>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;"><span id="sp_gm_DevNet.net_sim">*</span></td>
							<td>SIM卡号：</td>
							<td>
								<input id="gm_DevNet.net_sim" name="gm_DevNet.net_sim" value="${gm_DevNet.net_sim }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>应用类型：</td>	
							<td>
								<input id="gm_DevNet.net_appType" name="gm_DevNet.net_appType" value="${gm_DevNet.net_appType }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>短信中心号：</td>
							<td>
								<input id="gm_DevNet.net_smsc" name="gm_DevNet.net_smsc" value="${gm_DevNet.net_smsc }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>协议类型：</td>
							<td>
								<input id="gm_DevNet.net_pltType" name="gm_DevNet.net_pltType" value="${gm_DevNet.net_pltType }"/>
							</td>
						</tr>	
				</c:when>
				<c:when test="${gm_DevNet.net_pid == 'FF'}">
					<!-- 未配置节点 -->					
					<input name="gm_DevNet.net_state" type="hidden" value="${gm_DevNet.net_state }">
					<input id="gm_DevNet.net_pid" name="gm_DevNet.net_pid" type="hidden" value="${gm_DevNet.net_pid }">
					<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
						<tr class="list_head">
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络编号：</td>
							<td>
								<input id="gm_DevNet.net_no" name="gm_DevNet.net_no"/ value="${gm_DevNet.net_no }">
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>设备地址：</td>	
							<td><input id="gm_DevNet.net_addr" name="gm_DevNet.net_addr" value="${gm_DevNet.net_addr }"/></td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络类型：</td>
							<td>
								<select id="gm_DevNet.net_type" name="gm_DevNet.net_type" onchange="net_typeOnChange(this)">
									<option value="-1">-- 请选择 --</option>
									<c:choose>
										<c:when test="${gm_DevNet.net_type == 0}">
											<option value="0" selected="selected">远程网 M2M</option>
											<option value="1">无线传感网络 wsn</option>
											<option value="2">有线传感网 智能传感器</option>									
										</c:when>
										<c:when test="${gm_DevNet.net_type == 1}">
											<option value="0">远程网 M2M</option>
											<option value="1" selected="selected">无线传感网络 wsn</option>
											<option value="2">有线传感网 智能传感器</option>									
										</c:when>
										<c:when test="${gm_DevNet.net_type == 2}">
											<option value="0">远程网 M2M</option>
											<option value="1">无线传感网络 wsn</option>
											<option value="2" selected="selected">有线传感网 智能传感器</option>									
										</c:when>
									</c:choose>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网内角色：</td>
							<td>
								<select id="gm_DevNet.net_role" name="gm_DevNet.net_role">
									<option value="-1">-- 请选择 --</option>						
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>关联设备：</td>
							<td>
								<select id="gm_DevNet.dev_id.dev_id" name="gm_DevNet.dev_id.dev_id" >
									<option value="-1">-- 请选择 --</option>
								</select>
							</td>
						</tr>	
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络深度：</td>
							<td>
								<input id="gm_DevNet.net_depth" name="gm_DevNet.net_depth" value="${gm_DevNet.net_depth }"/> M2M设备为0,以此类推为0,1,2...整型
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>连接方式：</td>
							<td>
								<select id="gm_DevNet.net_linkSts" name="gm_DevNet.net_linkSts" >
									<option value="-1">-- 请选择 --</option>
									<c:choose>
										<c:when test="${gm_DevNet.net_linkSts == 0}">
											<option value="0" selected="selected">无效</option>
											<option value="1">长连接</option>
											<option value="2">短连接</option>
										</c:when>
										<c:when test="${gm_DevNet.net_linkSts == 1}">
											<option value="0">无效</option>
											<option value="1" selected="selected">长连接</option>
											<option value="2">短连接</option>
										</c:when>
										<c:when test="${gm_DevNet.net_linkSts == 2}">
											<option value="0">无效</option>
											<option value="1">长连接</option>
											<option value="2" selected="selected">短连接</option>
										</c:when>
									</c:choose>
								</select>
							</td>
						</tr>	
						<tr>
							<td style="width:12px; border-right: 0px;"><span id="sp_gm_DevNet.net_sim">*</span></td>
							<td>SIM卡号：</td>
							<td>
								<input id="gm_DevNet.net_sim" name="gm_DevNet.net_sim" value="${gm_DevNet.net_sim }"/>
							</td>
						</tr>	
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>应用类型：</td>
							<td>
								<input id="gm_DevNet.net_appType" name="gm_DevNet.net_appType" value="${gm_DevNet.net_appType }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>短信中心号：</td>
							<td>
								<input id="gm_DevNet.net_smsc" name="gm_DevNet.net_smsc" value="${gm_DevNet.net_smsc }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>协议类型：</td>
							<td>
								<input id="gm_DevNet.net_pltType" name="gm_DevNet.net_pltType" value="${gm_DevNet.net_pltType }"/>
							</td>
						</tr>
				</c:when>	
				<c:otherwise>
					<!-- 其他节点 -->					
					<input name="gm_DevNet.net_state" type="hidden" value="${gm_DevNet.net_state }">
					<input id="gm_DevNet.net_pid" name="gm_DevNet.net_pid" type="hidden" value="${gm_DevNet.net_pid }">
					<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
						<tr class="list_head">
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络编号：</td>
							<td>
								<input id="gm_DevNet.net_no" name="gm_DevNet.net_no"/ value="${gm_DevNet.net_no }">
							</td>
						</tr>	
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>设备地址：</td>
							<td>
								<input id="gm_DevNet.net_addr" name="gm_DevNet.net_addr" value="${gm_DevNet.net_addr }"/>
							</td>
						</tr>	
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络类型：</td>
							<td>
								<select disabled="disabled" id="gm_DevNet.net_type" onchange="net_typeOnChange(this)">
									<option value="-1">-- 请选择 --</option>
									<c:choose>
										<c:when test="${gm_DevNet.net_type == 0}">
											<option value="0" selected="selected">远程网 M2M</option>
											<option value="1">无线传感网络 wsn</option>
											<option value="2">有线传感网 智能传感器</option>									
										</c:when>
										<c:when test="${gm_DevNet.net_type == 1}">
											<option value="0">远程网 M2M</option>
											<option value="1" selected="selected">无线传感网络 wsn</option>
											<option value="2">有线传感网 智能传感器</option>									
										</c:when>
										<c:when test="${gm_DevNet.net_type == 2}">
											<option value="0">远程网 M2M</option>
											<option value="1">无线传感网络 wsn</option>
											<option value="2" selected="selected">有线传感网 智能传感器</option>									
										</c:when>
									</c:choose>
								</select> 
								<input type="hidden" name="gm_DevNet.net_type" value="${gm_DevNet.net_type }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网内角色：</td>
							<td>
								<select disabled="disabled" id="gm_DevNet.net_role">
									<option value="-1">-- 请选择 --</option>						
								</select> 
								<input type="hidden" name="gm_DevNet.net_role" value="${gm_DevNet.net_role }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>关联设备：</td>
							<td>
								<select id="gm_DevNet.dev_id.dev_id" name="gm_DevNet.dev_id.dev_id" >
									<option value="-1">-- 请选择 --</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>网络深度：</td>
							<td>
								<input disabled="disabled" id="gm_DevNet.net_depth" value="${gm_DevNet.net_depth }"/> M2M设备为0,以此类推为0,1,2...整型
								<input type="hidden" name="gm_DevNet.net_depth" value="${gm_DevNet.net_depth }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">*</td>
							<td>连接方式：</td>
							<td>
								<select id="gm_DevNet.net_linkSts" name="gm_DevNet.net_linkSts" >
									<option value="-1">-- 请选择 --</option>
									<c:choose>
										<c:when test="${gm_DevNet.net_linkSts == 0}">
											<option value="0" selected="selected">无效</option>
											<option value="1">长连接</option>
											<option value="2">短连接</option>
										</c:when>
										<c:when test="${gm_DevNet.net_linkSts == 1}">
											<option value="0">无效</option>
											<option value="1" selected="selected">长连接</option>
											<option value="2">短连接</option>
										</c:when>
										<c:when test="${gm_DevNet.net_linkSts == 2}">
											<option value="0">无效</option>
											<option value="1">长连接</option>
											<option value="2" selected="selected">短连接</option>
										</c:when>
									</c:choose>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;"><span id="sp_gm_DevNet.net_sim">*</span></td>
							<td>SIM卡号：</td>
							<td>
								<input id="gm_DevNet.net_sim" name="gm_DevNet.net_sim" value="${gm_DevNet.net_sim }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>应用类型：</td>
							<td>
								<input id="gm_DevNet.net_appType" name="gm_DevNet.net_appType" value="${gm_DevNet.net_appType }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>短信中心号：</td>
							<td>
								<input id="gm_DevNet.net_smsc" name="gm_DevNet.net_smsc" value="${gm_DevNet.net_smsc }"/>
							</td>
						</tr>
						<tr>
							<td style="width:12px; border-right: 0px;">&nbsp;</td>
							<td>协议类型：</td>
							<td>
								<input id="gm_DevNet.net_pltType" name="gm_DevNet.net_pltType" value="${gm_DevNet.net_pltType }"/>
							</td>
						</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="提 交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="返 回" onclick="window.location.href='${ctx }/gm_devnet_page.action?page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_id=${scene_id}&hid_condition=${hid_condition }&hid_value=${hid_value }'">
				</td>
			</tr>
		</table>					
    </form>
    <script type="text/javascript">    	
    	//加载网络角色列表 和 关联设备信息
    	var obj = document.getElementById("gm_DevNet.net_type");
    	var net_role = document.getElementById("gm_DevNet.net_role");
		net_role.length = 0;
		net_role.add(new Option("-- 请选择 --","-1"));
		var dev_id = document.getElementById("gm_DevNet.dev_id.dev_id");
		dev_id.length = 0;
		dev_id.add(new Option("-- 请选择 --","-1"));
		if(obj.value == 0){
			net_role.add(new Option("网关","01"));
			net_role.add(new Option("独立设备","02"));
			$.getJSON("${ctx}/gm_devnet_findFreeM2M.action",{
				random:Math.random()
			},function(list){
				$.each(list,function(i,device){
					dev_id.add(new Option(device.dev_no+"-"+device.dev_name,device.dev_id));
				})
				//在编辑的时候要加上自己原有的关联设备
				dev_id.add(new Option("${gm_DevNet.dev_id.dev_no}-${gm_DevNet.dev_id.dev_name}","${gm_DevNet.dev_id.dev_id}"));	
				//加载关联设备
				$.each(dev_id,function(i,op){		
		 			if(op.value == "${gm_DevNet.dev_id.dev_id }"){
		 				op.selected = true;
		 			}
				})	
			});
			document.getElementById("sp_gm_DevNet.net_sim").style.display = "block";
		}else if(obj.value == 1){
			net_role.add(new Option("RD","11"));
			net_role.add(new Option("ED","12"));
			net_role.add(new Option("CD","13"));
			net_role.add(new Option("LD","14"));
			$.getJSON("${ctx}/gm_devnet_findFreeWSN.action",{
				random:Math.random()
			},function(list){
				$.each(list,function(i,device){
					dev_id.add(new Option(device.dev_no+"-"+device.dev_name,device.dev_id));
				})	
				//在编辑的时候要加上自己原有的关联设备
				dev_id.add(new Option("${gm_DevNet.dev_id.dev_no}-${gm_DevNet.dev_id.dev_name}","${gm_DevNet.dev_id.dev_id}"));	
				//加载关联设备
				$.each(dev_id,function(i,op){		
		 			if(op.value == "${gm_DevNet.dev_id.dev_id }"){
		 				op.selected = true;
		 			}
				})	
			});
			document.getElementById("sp_gm_DevNet.net_sim").style.display = "none";
		}else if(obj.value == 2){
			net_role.add(new Option("传感器","21"));
			net_role.add(new Option("变送器","22"));
			$.getJSON("${ctx}/gm_devnet_findFreeISENSOR.action",{
				random:Math.random()
			},function(list){
				$.each(list,function(i,device){
					dev_id.add(new Option(device.dev_no+"-"+device.dev_name,device.dev_id));
				})
				//在编辑的时候要加上自己原有的关联设备
				dev_id.add(new Option("${gm_DevNet.dev_id.dev_no}-${gm_DevNet.dev_id.dev_name}","${gm_DevNet.dev_id.dev_id}"));		
				//加载关联设备
				$.each(dev_id,function(i,op){		
		 			if(op.value == "${gm_DevNet.dev_id.dev_id }"){
		 				op.selected = true;
		 			}
				})	
			});
			document.getElementById("sp_gm_DevNet.net_sim").style.display = "none";
		}
		
		//加载网络角色信息
    	var net_role = document.getElementById("gm_DevNet.net_role");
 		$.each(net_role,function(i,op){
 			if(op.value == "${gm_DevNet.net_role }"){
 				op.selected = true;
 			}
		})	
    </script>
  </body>  
</html>
