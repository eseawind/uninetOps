<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>编辑设备信息</title>
    
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
  	<a href="#">编辑设备信息</a> <br/> 
    <form action="${ctx }/gm_device_edit.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">       		
			<input name="gm_Device.dev_id" type="hidden" value="${gm_Device.dev_id }">
			<input name="gm_Device.dev_state" type="hidden" value="${gm_Device.dev_state }">
			
			<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
				<tr class="list_head">
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备编号：</td>
					<td>
						<c:if test="${!gm_Device.isUsing}">
							<input id="gm_Device.dev_no" name="gm_Device.dev_no" value="${gm_Device.dev_no }"/>
						</c:if>
						<c:if test="${gm_Device.isUsing}">
							${gm_Device.dev_no }<input type="hidden" id="gm_Device.dev_no" name="gm_Device.dev_no" value="${gm_Device.dev_no }"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备名称: </td>
					<td>
						<input id="gm_Device.dev_name" name="gm_Device.dev_name" value="${gm_Device.dev_name }"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备序列号: </td>
					<td>
						<input id="gm_Device.dev_serial" name="gm_Device.dev_serial" value="${gm_Device.dev_serial }"/>
					</td>
				</tr>
				<c:if test="${!gm_Device.isUsing}">	
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备大类: </td>
					<td>
						<select id="gm_Device.dev_btype" name="gm_Device.dev_btype">
	    					<option value="-1">-- 请选择 --</option>
	    					<c:choose>
								<c:when test="${gm_Device.dev_btype == 0 }">
			    					<option value="0" selected="selected">M2M</option>
			    					<option value="1">WSN</option>
			    					<option value="2">智能单元</option>
			    					<option value="3">执行器(可控设备)</option>
			    					<option value="4">传感器</option>
								</c:when>
								<c:when test="${gm_Device.dev_btype == 1 }">
			    					<option value="0">M2M</option>
			    					<option value="1" selected="selected">WSN</option>
			    					<option value="2">智能单元</option>
			    					<option value="3">执行器(可控设备)</option>
			    					<option value="4">传感器</option>
								</c:when>
								<c:when test="${gm_Device.dev_btype == 2 }">
			    					<option value="0">M2M</option>
			    					<option value="1">WSN</option>
			    					<option value="2" selected="selected">智能单元</option>
			    					<option value="3">执行器(可控设备)</option>
			    					<option value="4">传感器</option>
								</c:when>
								<c:when test="${gm_Device.dev_btype == 3 }">
			    					<option value="0">M2M</option>
			    					<option value="1">WSN</option>
			    					<option value="2">智能单元</option>
			    					<option value="3" selected="selected">执行器(可控设备)</option>
			    					<option value="4">传感器</option>
								</c:when>
								<c:when test="${gm_Device.dev_btype == 4 }">
			    					<option value="0">M2M</option>
			    					<option value="1">WSN</option>
			    					<option value="2">智能单元</option>
			    					<option value="3">执行器(可控设备)</option>
			    					<option value="4" selected="selected">传感器</option>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
	    				</select> 
					</td>
				</tr>
				</c:if>
				<c:if test="${gm_Device.isUsing}">
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备大类: </td>
					<td>
						<input type="hidden" id="gm_Device.dev_btype" name="gm_Device.dev_btype" value="${gm_Device.dev_btype }"/>
		    			<c:choose>
							<c:when test="${gm_Device.dev_btype == 0 }">
								<input type="text" value="M2M"/>						
							</c:when>
							<c:when test="${gm_Device.dev_btype == 1 }">
								<input type="text" value="WSN"/>
							</c:when>
							<c:when test="${gm_Device.dev_btype == 2 }">
		    					<input type="text" value="智能单元"/>
							</c:when>
							<c:when test="${gm_Device.dev_btype == 3 }">
								<input type="text" value="执行器(可控设备)"/>
							</c:when>
							<c:when test="${gm_Device.dev_btype == 4 }">
		  							<input type="text" value="传感器"/>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:if>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备型号: </td>
					<td>
						<input id="gm_Device.dev_model" name="gm_Device.dev_model" value="${gm_Device.dev_model }"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>供电方式: </td>
					<td>
						<select id="gm_Device.dev_powerType" name="gm_Device.dev_powerType">
	    					<option value="-1">-- 请选择 --</option>
	    					<c:choose>
	    						<c:when test="${gm_Device.dev_powerType == '市电' }">    						
	    							<option value="市电" selected="selected">市电</option>
	    							<option value="仅3V电池供电">仅3V电池供电</option>
	    							<option value="3V+9V电池供电">3V+9V电池供电</option>
	    						</c:when>
	    						<c:when test="${gm_Device.dev_powerType == '仅3V电池供电' }">    						
	    							<option value="市电">市电</option>
	    							<option value="仅3V电池供电" selected="selected">仅3V电池供电</option>
	    							<option value="3V+9V电池供电">3V+9V电池供电</option>
	    						</c:when>
	    						<c:when test="${gm_Device.dev_powerType == '3V+9V电池供电' }">    						
	    							<option value="市电">市电</option>
	    							<option value="仅3V电池供电">仅3V电池供电</option>
	    							<option value="3V+9V电池供电" selected="selected">3V+9V电池供电</option>
	    						</c:when>
	    						<c:otherwise>
	    						</c:otherwise>
	    					</c:choose>
	    				</select>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>所属场景: </td>	
					<td>
						<select id="gm_Device.scene_id.scene_id" name="gm_Device.scene_id.scene_id">
	    					<option value="-1">-- 请选择 --</option>
	    					<c:forEach var="scene_id" items="${scene_id_list}">
	    						<c:choose>
	    							<c:when test="${scene_id.scene_id == gm_Device.scene_id.scene_id}">
	    								<option value="${scene_id.scene_id }" selected="selected">${scene_id.scene_name }-${scene_id.scene_name }</option>
	    							</c:when>
	    							<c:otherwise>
	    								<option value="${scene_id.scene_id }">${scene_id.scene_name }-${scene_id.scene_name }</option>
	    							</c:otherwise>
	    						</c:choose>    						
	    					</c:forEach>
	    				</select>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>设备IMSI: </td>
					<td>
						<input name="gm_Device.dev_imsi" value="${gm_Device.dev_imsi }"/>
					</td>
				</tr>	
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>购买时间: </td>
					<td>
						<input name="gm_Device.dev_buyDate" onfocus="setday(this,this)" readonly="readonly" value="${gm_Device.dev_buyDate }"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>服务到期时间: </td>
					<td>
						<input name="gm_Device.dev_overDate" onfocus="setday(this,this)" readonly="readonly" value="${gm_Device.dev_overDate }"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>生效时间: </td>
					<td>
						<input name="gm_Device.dev_effectTime" onfocus="setday(this,this)" readonly="readonly" value="${gm_Device.dev_effectTime }"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>设备厂家: </td>
					<td>
						<select id="gm_Device.cust_saleId.sup_id" name="gm_Device.cust_saleId.sup_id">
	    					<option value="-1">-- 请选择 --</option>
	    					<c:forEach var="cust_saleId" items="${cust_saleId_list}">
	    						<c:choose>
	    							<c:when test="${cust_saleId.sup_id == m_Device.cust_saleId.sup_id}">
	    								<option value="${cust_saleId.sup_id }" selected="selected">${cust_saleId.sup_name }</option> 
	    							</c:when>
	    							<c:otherwise>
	    								<option value="${cust_saleId.sup_id }">${cust_saleId.sup_name }</option>  
	    							</c:otherwise>
	    						</c:choose>  						
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
	    						<c:choose>
	    							<c:when test="${cust_serviceId.sup_id == gm_Device.cust_serviceId.sup_id}">
	    								<option value="${cust_serviceId.sup_id }" selected="selected">${cust_serviceId.sup_name }</option>
	    							</c:when>
	    							<c:otherwise>
	    								<option value="${cust_serviceId.sup_id }">${cust_serviceId.sup_name }</option>
	    							</c:otherwise>
	    						</c:choose>    						
	    					</c:forEach>
	    				</select>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="提 交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返 回" onclick="window.location.href='${ctx }/gm_device_page.action?page.pageSize=12&gm_Device.dev_id='">
					</td>
				</tr>	
			</table>
    </form>
  </body>
</html>
