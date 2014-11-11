<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
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
	<link rel="stylesheet" href="zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/date.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
	<script type="text/javascript" src="${ctx }/js/demoTools.js"></script>
	<script type="text/javascript">		
		function checkForm(){
			//验证设备编号非空
			var dev_no = document.getElementById("gm_Device.dev_no");
			if(dev_no.value.length == 0){
				alert("请填写设备编号");
				return;
			}
			//验证设备名称非空
			var dev_name = document.getElementById("gm_Device.dev_name");
			if(dev_name.value.length == 0){
				alert("请填写设备名称");
				return;
			}
			//验证设备序列号非空
			var dev_serial = document.getElementById("gm_Device.dev_serial");
			if(dev_serial.value.length == 0){
				alert("请填写设备序列号");
				return;
			}
			//验证设备大类不能为-1
			var dev_btype = document.getElementById("gm_Device.dev_btype");
			if(dev_btype.value == "-1"){
				alert("请选择设备大类");
				return
			}
			//验证设备型号不能为空
			var dev_model = document.getElementById("gm_Device.dev_model");
			if(dev_model.value.length == 0){
				alert("请填写设备型号");
				return;
			}
			//验证供电方式不能为-1
			var dev_powerType = document.getElementById("gm_Device.dev_powerType");
			if(dev_powerType.value == "-1"){
				alert("请选择供电方式");
				return;
			}
			//验证所属场景不能为空
			var scene_id = document.getElementById("gm_Device.scene_id.scene_id");
			if(scene_id.value.length == 0){
				alert("请选择所属场景");
				return;
			}
			
			//验证设备编号是否唯一
			$.getJSON("${ctx}/gm_device_json_isExist_dev_no.action?gm_Device.dev_no="+dev_no.value,{
				random:Math.random()
			},function(isExist_dev_no){
				if(isExist_dev_no.value){
					alert(isExist_dev_no.msg);	
					return;
				}else{					
					//验证设备序列号是否唯一
					$.getJSON("${ctx}/gm_device_json_isExist_dev_serial.action?gm_Device.dev_serial="+dev_serial.value,{
						random:Math.random()
					},function(isExist_dev_serial){
						if(isExist_dev_serial.value){
							alert(isExist_dev_serial.msg);	
							return;
						}else{
							document.forms[0].submit();
							return;
						}
					});
				}
			});		
		}
		
		//响应场景树
		function echoSceneTree(id,name,no){
			//无响应
		}
		
		
		var zTree_save_0715;
		var scenes_save_0715 = ${scenes};
		var setting_save_0715 = {
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			fontCss: setFont,
			callback: {
				click: zTreeOnClick
			}
			
		};
		
		
		$(document).ready(function(){
			reloadTree();	
			$("body").bind("mousedown",function(event){
				if (!(event.target.id == "menuBtn" || event.target.id == "DropdownMenuBackground" || $(event.target).parents("#DropdownMenuBackground").length>0)) {
					hideMenu();
				}
			});
		});		
		
		function setFont(treeId, treeNode) {
			if (treeNode && treeNode.isParent) {
				return {color: "blue"};
			} else {
				return null;
			}
		}
		
		function showMenu() {
			var sceneObj = $("#sceneSel");
			var sceneOffset = $("#sceneSel").offset();
			$("#DropdownMenuBackground").css({left:sceneOffset.left + "px", top:sceneOffset.top + sceneObj.outerHeight() + "px"}).slideDown("fast");
			
		}
		
		function hideMenu() {
			$("#DropdownMenuBackground").fadeOut("fast");
		}
		
		function zTreeOnClick(event, treeId, treeNode) {
			if (treeNode) {
				var sceneObj = $("#sceneSel");
				var sceneHid = document.getElementById("gm_Device.scene_id.scene_id");
				sceneObj.attr("value", treeNode.name);
				sceneHid.value = treeNode.id;
				hideMenu();
			}
		}
		
		function reloadTree() {
			hideMenu();
			zTree_save_0715 = $("#dropdownMenu").zTree(setting_save_0715, scenes_save_0715);
			zTree_save_0715.expandAll(true);			
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="javascript:window.location.href='${ctx }/gm_device_page.action?page.pageSize=12&gm_Device.dev_id='">设备信息管理</a> 》 
  	<a href="#">添加设备信息</a> </br>
    <form action="${ctx }/gm_device_save.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&scene_id=${scene_id}&hid_condition=${hid_condition}&hid_value=${hid_value}" method="post" enctype="multipart/form-data">       		
			<input name="gm_Device.dev_state" type="hidden" value="1">
			<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
				<tr class="list_head">
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备编号：</td>
					<td><input id="gm_Device.dev_no" name="gm_Device.dev_no"/> 必须唯一,必填</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备名称: </td>
					<td><input id="gm_Device.dev_name" name="gm_Device.dev_name"/> 必填</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备序列号: </td>
					<td><input id="gm_Device.dev_serial" name="gm_Device.dev_serial"/> 必须唯一,必填</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备大类: </td>
					<td>
						<select id="gm_Device.dev_btype" name="gm_Device.dev_btype" style="width:156px;">
	    					<option value="-1">-- 请选择 --</option>
							<%
								List<Dev_btype> dev_btypes = StaticDataManage.getDev_btypes();
								Iterator<Dev_btype> dev_btypesIterator = dev_btypes.iterator();
							%>
							<%
								while (dev_btypesIterator.hasNext()) {
									Dev_btype dev_btype = dev_btypesIterator.next();
							%>
							<option value="<%=dev_btype.getId()%>"><%=dev_btype.getName()%></option>
							<%
								}
							%>
    					</select> 必填
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>设备型号: </td>
					<td>
						<input id="gm_Device.dev_model" name="gm_Device.dev_model"/> 必填 
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>供电方式: </td>
					<td>
						<select id="gm_Device.dev_powerType" name="gm_Device.dev_powerType" style="width:156px;">
	    					<option value="-1">-- 请选择 --</option>
							<%
								List<String> dev_powerTypes = StaticDataManage.getDev_powerTypes();
								Iterator<String> dev_powerTypesIterator = dev_powerTypes.iterator();
							%>
							<%
								while (dev_powerTypesIterator.hasNext()) {
									String dev_powerType = dev_powerTypesIterator.next();
							%>
							<option value="<%=dev_powerType%>"><%=dev_powerType%></option>
							<%
								}
							%>
    					</select> 必填
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>所属场景: </td>
					<td>
						<input type="text" disabled="disabled" id="sceneSel"> <a href="#" onclick="showMenu()">选择</a> 必填
						<input type="hidden" id="gm_Device.scene_id.scene_id" name="gm_Device.scene_id.scene_id" value=""/>
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
						<input type="button" value="提 交" onclick="checkForm()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返 回" onclick="window.location.href='${ctx }/gm_device_page.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&scene_id=${scene_id}&hid_condition=${hid_condition}&hid_value=${hid_value}'">
					</td>
				</tr>															
			</table>	
		    
    </form>
    
<div id="DropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto; ">
	<ul id="dropdownMenu" class="tree"></ul>
</div>
  </body>
</html>
