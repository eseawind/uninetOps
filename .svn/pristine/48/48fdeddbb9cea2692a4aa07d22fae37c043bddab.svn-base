<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.unism.util.*"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加采集通道信息</title>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
	<script type="text/javascript" src="${ctx }/js/demoTools.js"></script>
	<script type="text/javascript">
		//采集通道应用类型 下拉裂变 改变事件
		function chtype_id_onchange(){
			var sel_chtype_id = document.getElementById("gm_Channel.chtype_id.chtype_id");
			$.getJSON("${ctx}/Op_ChannelType_json_findById.action?op_ChannelType.chtype_id="+sel_chtype_id.value,{
					random:Math.random()
				},function(type){
					/**
					 * @return json
					 * 			{
					 * 				chtype_id:'',
					 * 				chtype_no:'',
					 * 				chtype_displayName:'',
					 * 				dev_model:'',
					 * 				ch_dectDig:'',
					 * 				ch_unit:'',
					 * 				ch_max:'',
					 * 				ch_min:'',
					 * 				ch_crateMax:'',
					 * 				ch_corrCyc:'',
					 * 				ch_corrFormula:'',
					 * 				ch_corrUnit:'',
					 * 				ch_ClassName:'',
					 * 				typeImg:''
					 * 			}
					 */
					if(type.ch_dectDig==null || type.ch_dectDig=='null'){
						document.getElementById("gm_Channel.ch_dectDig").value = "";
					}else{
						document.getElementById("gm_Channel.ch_dectDig").value = type.ch_dectDig;
					}
					if(type.ch_unit==null || type.ch_unit=='null'){
						document.getElementById("gm_Channel.ch_unit").value = "";
					}else{
						document.getElementById("gm_Channel.ch_unit").value = type.ch_unit;
					}	
					if(type.ch_max==null || type.ch_max=='null'){
						document.getElementById("ch_max").value = "";
					}else{
						document.getElementById("ch_max").value = type.ch_max;
					}
					if(type.ch_min==null || type.ch_min=='null'){
						document.getElementById("ch_min").value = "";
					}else{
						document.getElementById("ch_min").value = type.ch_min;
					}
					if(type.ch_crateMax==null || type.ch_crateMax=='null'){
						document.getElementById("ch_crateMax").value = "";
					}else{
						document.getElementById("ch_crateMax").value = type.ch_crateMax;
					}
					if(type.ch_corrCyc==null || type.ch_corrCyc=='null'){
						document.getElementById("gm_Channel.ch_corrCyc").value = "";
					}else{
						document.getElementById("gm_Channel.ch_corrCyc").value = type.ch_corrCyc;
					}
					if(type.ch_corrFormula==null || type.ch_corrFormula=='null'){
						document.getElementById("gm_Channel.ch_corrFormula").value = "";
					}else{
						document.getElementById("gm_Channel.ch_corrFormula").value = type.ch_corrFormula;
					}
					if(type.ch_corrUnit==null || type.ch_corrUnit=='null'){
						document.getElementById("gm_Channel.ch_corrUnit").value = "";
					}else{
						document.getElementById("gm_Channel.ch_corrUnit").value = type.ch_corrUnit;
					}					
				}
			);	
		}
		
		//下拉树
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
				var sceneHid = document.getElementById("gm_Channel.scene_id.scene_id");
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
		
		//表单验证
		function checkForm(){
			var ch_no = document.getElementById("gm_Channel.ch_no");
			var ch_name = document.getElementById("gm_Channel.ch_name");
			var dev_collectId = document.getElementById("gm_Channel.dev_collectId.dev_id");
			var ch_chlNo = document.getElementById("gm_Channel.ch_chlNo");
			var chtype_id = document.getElementById("gm_Channel.chtype_id.chtype_id");
			var ch_memoryPeriod = document.getElementById("gm_Channel.ch_memoryPeriod");
			var scene_id = document.getElementById("gm_Channel.scene_id.scene_id");
			var dev_sensorId = document.getElementById("gm_Channel.dev_sensorId.dev_id");
			var ch_procesStyle = document.getElementById("gm_Channel.ch_procesStyle");
			
			var ch_dectDig = document.getElementById("gm_Channel.ch_dectDig");
			//var ch_unit = document.getElementById("gm_Channel.ch_unit");
			var ch_max = document.getElementById("ch_max");
			var ch_min = document.getElementById("ch_min");
			var ch_crateMax = document.getElementById("ch_crateMax");
			var ch_corrCyc = document.getElementById("gm_Channel.ch_corrCyc");
			//var ch_corrFormula = document.getElementById("gm_Channel.ch_corrFormula");
			//var ch_corrUnit = document.getElementById("gm_Channel.ch_corrUnit");
			var ch_datetype = document.getElementById("gm_Channel.ch_datetype");
			
			//alert(22);
			if(ch_no.value.length==0){
				alert("通道编号不能为空");
				return;
			}
			if(ch_no.value.indexOf("-") < 0){
				alert("通道编号格式不正确");
				return;
			}
			if(ch_name.value.length==0){
				alert("通道名称不能为空");
				return;
			}
			
			if(ch_datetype.value==''){
				if(dev_collectId.value=="-1"){
					alert("采集设备不能为空");
					return;
				}
				var re_ch_chlNo = /^[1-9]\d*$/;
				if(!re_ch_chlNo.test(ch_chlNo.value)){
					alert("设备通道号只能是正整数");
					return;
				}
			}
			
			if(chtype_id.value=="-1"){
				alert("采集通道应用类型不能为空");
				return;
			}
			
			if(ch_memoryPeriod.value.length>0){
				var re_ch_memoryPeriod = /^[1-9]\d*|0$/;
				if(!re_ch_memoryPeriod.test(ch_memoryPeriod.value)){
					alert("存储周期的格式不合法");
					return;
				}
			}
			
			if(ch_dectDig.value.length>0){
				var re_ch_dectDig = /^[1-9]\d*$/;
				if(!re_ch_dectDig.test(ch_dectDig.value)){
					alert("小数位数的格式不合法");
					return;
				}
			}
			
			if(ch_max.value.length>0){
				var re_ch_max_1 = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
				var re_ch_max_2 = /^-?\d+$/;
				if(!re_ch_max_1.test(ch_max.value) && !re_ch_max_2.test(ch_max.value)){
					alert("量程上限的格式不合法");
					return;
				}
			}
			
			if(ch_min.value.length>0){
				var re_ch_min_1 = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
				var re_ch_min_2 = /^-?\d+$/;
				if(!re_ch_min_1.test(ch_min.value) && !re_ch_min_2.test(ch_min.value)){
					alert("量程下限的格式不合法");
					return;
				}
			}
			
			if(ch_crateMax.value.length>0 && ch_min.value.length>0){
				var re_ch_crateMax_1 = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
				var re_ch_crateMax_2 = /^-?\d+$/;
				if(!re_ch_crateMax_1.test(ch_crateMax.value) && !re_ch_crateMax_2.test(ch_crateMax.value)){
					alert("变化率上限的格式不合法");
					return;
				}
			}
			
			if(ch_min.value.length>0){
				if(ch_corrCyc.value.length>0){
					var re_ch_corrCyc = /^[1-9]\d*|0$/;
					if(!re_ch_corrCyc.test(ch_corrCyc.value)){
						alert("校准周期的格式不合法");
						return;
					}
				}
			}
				
			if(scene_id.value.length == 0){
				alert("所属场景不能为空");
				return;
			}
			
			if(ch_max.value<ch_min.value){
				alert("量程上限不能小于量程下限");
				return;
			}
							
			$.getJSON("${ctx}/Gm_Channel_isCh_noExist.action?gm_Channel.ch_no="+ch_no.value,{
					random:Math.random()
				},function(isCh_noExist){
					if(isCh_noExist==1){
						alert("通道编号已存在");
						return;
					}else if(isCh_noExist==0){
						//实体里已经new了 后台处理吧if(dev_sensorId.value=="-1"){
						//	dev_sensorId.disabled=true;
						//}
						if(ch_procesStyle.value=="-1"){
							ch_procesStyle.disabled=true;
						}
						document.forms[0].submit();
						return;
					}else{//alert(33);
						alert("操作失败!未知错误");
						return;
					}
				}
			);	
		}
		
		function ch_datetype_onchange(value){
			var gm_Channel_dev_collectId_dev_id = document.getElementById('gm_Channel.dev_collectId.dev_id');
			var gm_Channel_ch_chlNo = document.getElementById('gm_Channel.ch_chlNo');
			var gm_Channel_dev_sensorId_dev_id = document.getElementById('gm_Channel.dev_sensorId.dev_id');
			if(value!=""){
				gm_Channel_dev_collectId_dev_id.options[0].selected = true;
				gm_Channel_dev_collectId_dev_id.disabled = true;
				gm_Channel_ch_chlNo.value = '';
				gm_Channel_ch_chlNo.disabled = true;
				gm_Channel_dev_sensorId_dev_id.options[0].selected = true;
				gm_Channel_dev_sensorId_dev_id.disabled = true;
			}else{
				gm_Channel_dev_collectId_dev_id.disabled = false;
				gm_Channel_ch_chlNo.disabled = false;
				gm_Channel_dev_sensorId_dev_id.disabled = false;				
			}			
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='${ctx }/op_scene_toSceneSO_0518.action'">首页</a> 》 
			<a href="javascript:window.location.href='${ctx }/Gm_Channel_findAll.action'">采集通道信息管理</a> 》
			<a href="#">添加采集通道信息</a><br>			
    <form action="Gm_Channel_save.action?post=1" method="post">
	  	<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
			<tr class="list_head">
				<td colspan="2">&nbsp;</td>
			</tr>	
    		<tr>
				<td>通道编号</td>
				<td>
					<input id="gm_Channel.ch_state" name="gm_Channel.ch_state" type="hidden" value="1"/>
					<input type="text" id="gm_Channel.ch_no" name="gm_Channel.ch_no"/> 必须唯一,必填
				</td>
			</tr>
    		<tr>
				<td>通道名称</td>
				<td><input type="text" id="gm_Channel.ch_name" name="gm_Channel.ch_name"/> 必填</td>
			</tr>
			<tr>
				<td>采集设备编号</td>
				<td>
					<select id="gm_Channel.dev_collectId.dev_id" name="gm_Channel.dev_collectId.dev_id" style="width: 156px;">					
					<option value="-1">-- 请选择 --</option>
					<c:forEach items="${requestScope.device_list}" var="device">
						<option value="${device.dev_id}">${device.dev_no}</option>					
					</c:forEach>
					</select> 必填
				</td>
			</tr>
			<tr>
				<td>设备通道号</td>
				<td><input type="text" id="gm_Channel.ch_chlNo" name="gm_Channel.ch_chlNo"/> 必填</td>
			</tr>
    		<tr>
				<td>传感设备编号</td>
				<td>
					<select id="gm_Channel.dev_sensorId.dev_id" name="gm_Channel.dev_sensorId.dev_id" style="width: 156px;">					
					<option value="-1">-- 请选择 --</option>
					<c:forEach items="${requestScope.device_list}" var="device">
						<option value="${device.dev_id}">${device.dev_no}</option>					
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>采集通道应用类型信息编号</td>
				<td>
					<select id="gm_Channel.chtype_id.chtype_id" name="gm_Channel.chtype_id.chtype_id" onchange="chtype_id_onchange()">					
					<option value="-1">-- 请选择 --</option>
					<c:forEach items="${requestScope.channeltype}" var="channeltype">
						<option value="${channeltype.chtype_id}">${channeltype.chtype_displayName}(${channeltype.chtype_no})</option>					
					</c:forEach>
					</select> 必填
				</td>
			</tr>
			<tr>
				<td>通道数据处理方式</td>
				<td>					
					<select id="gm_Channel.ch_procesStyle" name="gm_Channel.ch_procesStyle">
					<option value="-1">-- 请选择 --</option>					
						<%
							List<Ch_procesStyle> ch_procesStyles = StaticDataManage.getCh_procesStyles();
							Iterator<Ch_procesStyle> ch_procesStylesIterator = ch_procesStyles.iterator();
						%>
						<%
							while (ch_procesStylesIterator.hasNext()) {
								Ch_procesStyle ch_procesStyle = ch_procesStylesIterator.next();
						%>
						<option value="<%=ch_procesStyle.getId()%>"><%=ch_procesStyle.getName()%></option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td>存储周期</td>
				<td><input type="text" id="gm_Channel.ch_memoryPeriod" name="gm_Channel.ch_memoryPeriod"/></td>
			</tr>
			<tr>
				<td>小数位数</td>
				<td><input type="text" id="gm_Channel.ch_dectDig" name="gm_Channel.ch_dectDig"/></td>
			</tr>
			<tr>
				<td>原数据单位</td>
				<td><input type="text" id="gm_Channel.ch_unit" name="gm_Channel.ch_unit"/></td>
			</tr>
			<tr>
				<td>量程上限</td>
				<td><input type="text" id="ch_max" name="gm_Channel.ch_max"/></td>
			</tr>
			<tr>
				<td>量程下限</td>
				<td><input type="text" id="ch_min" name="gm_Channel.ch_min"/></td>
			</tr>
			<tr>
				<td>变化率上限</td>
				<td><input type="text" id="ch_crateMax" name="gm_Channel.ch_crateMax"/></td>
			</tr>
			<tr>
				<td>校准周期</td>
				<td><input type="text" id="gm_Channel.ch_corrCyc" name="gm_Channel.ch_corrCyc"/></td>
			</tr>
			<tr>
				<td>校正公式</td>
				<td><input type="text" id="gm_Channel.ch_corrFormula" name="gm_Channel.ch_corrFormula"/></td>
			</tr>
			<tr>
				<td>校正后的单位</td>
				<td><input type="text" id="gm_Channel.ch_corrUnit" name="gm_Channel.ch_corrUnit"/></td>
			</tr>				
			<tr>
				<td>场景名称</td>
				<td>
					<input type="text" disabled="disabled" id="sceneSel" readonly="readonly"> <a href="javascript:showMenu()">选择</a> 必填
					<input type="hidden" id="gm_Channel.scene_id.scene_id" name="gm_Channel.scene_id.scene_id" value=""/>
				</td>
			</tr>
			<tr>
				<td>位置编号</td>
				<td><input type="text" name="gm_Channel.ch_seat_no"/></td>
			</tr>
			<tr>
				<td>通道深度</td>
				<td><input type="text" name="gm_Channel.ch_depth"/></td>
			</tr>
			<tr>
				<td>是否对外提供服务</td>
				<td>
					<input type="radio" name="gm_Channel.ch_offerSer" value="0"/>否
					<input type="radio" name="gm_Channel.ch_offerSer" value="1" checked="checked"/>是 必填
				</td>
			</tr>
			<tr>
				<td>数值类型</td>
				<td>
					<select name="gm_Channel.ch_datetype" onchange="ch_datetype_onchange(this.value)">
						<option value="">-- 请选择 --</option>					
						<%
							List<Ch_dateType> ch_dateTypes = StaticDataManage.getCh_dateTypes();
							Iterator<Ch_dateType> ch_dateTypesIterator = ch_dateTypes.iterator();
						%>
						<%
							while (ch_dateTypesIterator.hasNext()) {
								Ch_dateType ch_dateType = ch_dateTypesIterator.next();
						%>
						<option value="<%=ch_dateType.getId()%>"><%=ch_dateType.getName()%></option>
						<%
							}
						%>
					</select>
				</td>
			</tr>			
			<tr>
				<td align="center" colspan="2">
					<input type="button" value="提 交" onclick="checkForm()"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
					<input type="button" value="返 回" onclick="window.location.href='Gm_Channel_findAll.action'"/>
				</td>
			</tr>
    	</table>
    </form>
    <div id="DropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto; ">
	<ul id="dropdownMenu" class="tree"></ul>
</div>
  </body>
</html>
