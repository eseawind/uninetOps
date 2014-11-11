<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加设备信息</title>
    
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
			//验证设备编号非空
			var dev_no = document.getElementById("gm_Device.dev_no");
			if(dev_no.value.length == 0){
				alert("请填写设备编号");
				return false;
			}
			//验证设备名称非空
			var dev_name = document.getElementById("gm_Device.dev_name");
			if(dev_name.value.length == 0){
				alert("请填写设备名称");
				return false;
			}
			//验证设备序列号非空
			var dev_serial = document.getElementById("gm_Device.dev_serial");
			if(dev_serial.value.length == 0){
				alert("请填写设备序列号");
				return false;
			}
			//验证设备大类不能为-1
			var dev_btype = document.getElementById("gm_Device.dev_btype");
			if(dev_btype.value == "-1"){
				alert("请选择设备大类");
				return false;
			}
			//验证设备型号不能为空
			var dev_model = document.getElementById("gm_Device.dev_model");
			if(dev_model.value.length == 0){
				alert("请填写设备型号");
				return false;
			}
			//验证供电方式不能为-1
			var dev_powerType = document.getElementById("gm_Device.dev_powerType");
			if(dev_powerType.value == "-1"){
				alert("请选择供电方式");
				return false;
			}
			//验证所属场景不能为空
			var scene_id = document.getElementById("gm_Device.scene_id.scene_id");
			if(scene_id.value == "-1"){
				alert("请选择所属场景");
				return false;
			}
						
			return true;
		}
		
		//响应场景树
		function echoSceneTree(id,name,no){
			//无响应
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="javascript:window.location.href='${ctx }/gm_device_page.action?page.pageSize=12&gm_Device.dev_id='">设备信息管理</a> 》 
  	<a href="#">添加设备信息</a> </br>
    <form action="${ctx }/gm_device_save.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">       		
			<input name="gm_Device.dev_state" type="hidden" value="1">
			<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
				<tr class="list_head">
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备编号：</td>
					<td><input id="gm_Device.dev_no" name="gm_Device.dev_no"/></td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备名称: </td>
					<td><input id="gm_Device.dev_name" name="gm_Device.dev_name"/></td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备序列号: </td>
					<td><input id="gm_Device.dev_serial" name="gm_Device.dev_serial"/></td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备大类: </td>
					<td>
						<select id="gm_Device.dev_btype" name="gm_Device.dev_btype">
	    					<option value="-1">-- 请选择 --</option>
	    					<option value="0">M2M</option>
	    					<option value="1">WSN</option>
	    					<option value="2">智能单元</option>
	    					<option value="3">执行器(可控设备)</option>
	    					<option value="4">传感器</option>
    					</select>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备型号: </td>
					<td>
						<input id="gm_Device.dev_model" name="gm_Device.dev_model"/> 
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>供电方式: </td>
					<td>
						<select id="gm_Device.dev_powerType" name="gm_Device.dev_powerType">
	    					<option value="-1">-- 请选择 --</option>
	    					<option value="市电">市电</option>
	    					<option value="仅3V电池供电">仅3V电池供电</option>
	    					<option value="3V+9V电池供电">3V+9V电池供电</option>
    					</select>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>所属场景: </td>
					<td>
						<select id="gm_Device.scene_id.scene_id" name="gm_Device.scene_id.scene_id">
	    					<option value="-1">-- 请选择 --</option>
	    					<c:forEach var="scene_id" items="${scene_id_list}">
	    						<option value="${scene_id.scene_id }">${scene_id.scene_name }-${scene_id.scene_name }</option>
	    					</c:forEach>
	    				</select>
    				</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>设备IMSI: </td>
					<td>
						<input name="gm_Device.dev_imsi" /> 
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>购买时间: </td>
					<td>
						<input name="gm_Device.dev_buyDate" onfocus="setday(this,this)" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>服务到期时间: </td>
					<td>
						<input name="gm_Device.dev_overDate" onfocus="setday(this,this)" readonly="readonly"/>
					</td>
				</tr>	
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>生效时间: </td>
					<td>
						<input name="gm_Device.dev_effectTime" onfocus="setday(this,this)" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>设备厂家: </td>
					<td>
						<select id="gm_Device.cust_saleId.sup_id" name="gm_Device.cust_saleId.sup_id">
	    					<option value="-1">-- 请选择 --</option>
	    					<c:forEach var="cust_saleId" items="${cust_saleId_list}">
	    						<option value="${cust_saleId.sup_id }">${cust_saleId.sup_name }</option>    						
	    					</c:forEach>
    					</select>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>设备服务商: </td>
					<td>
						<select id="gm_Device.cust_serviceId.sup_id" name="gm_Device.cust_serviceId.sup_id">
	    					<option value="-1">-- 请选择 --</option>
	    					<c:forEach var="cust_serviceId" items="${cust_serviceId_list}">
	    						<option value="${cust_serviceId.sup_id }">${cust_serviceId.sup_name }</option>
	    					</c:forEach>
    					</select>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="提 交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返 回" onclick="window.location.href='${ctx }/gm_device_page.action?page.pageSize=12&gm_Device.dev_id='">
					</td>
				</tr>															
			</table>	
		    
    </form>
  </body>
</html>
