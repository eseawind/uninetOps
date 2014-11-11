<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@page import="org.unism.gm.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>故障信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		td{
			font-size: 12px;
		}
		input{
			font-size: 12px;
		}
		select{
			font-size: 12px;
		}
	</style>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script> 
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.min.js"></script>
	<s:actionmessage theme="custom" cssClass="success"/>
	<script type="text/javascript">
	
		function gotoPage(pageNo){
			var dev_no = document.getElementById("hidDev_no").value;
			var def_grade = document.getElementById("hidDef_grade").value;
			var def_object = document.getElementById('hidDef_object').value;
			var def_type = document.getElementById("hidDef_type").value;
			var def_symptom = document.getElementById('hidDef_symptom').value;
			var def_state = document.getElementById("hidDef_state").value;
			
			//var queryName = document.getElementById("hidQueryName").value;	
			//var queryValue = document.getElementById("hidQueryValue").value;
			var beginTime = document.getElementById("hidBeginTime").value;
			var endTime = document.getElementById("hidEndTime").value;
			var begin = document.getElementById("beginTime").value;
			var end = document.getElementById("endTime").value;
			var stare = new Date(begin[0],begin[1],begin[2]);
			var over = new Date(end[0],end[1],end[2]);
			
			if(Date.parse(begin.replace(/\-/g,"/"))>Date.parse(end.replace(/\-/g,"/"))){
				alert("请重新选择时间……");
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
			window.location.href = "${ctx}/Gm_DevFault_findAllDevFault.action?page.pageNo=" + pageNo + "&page.pageSize=10&devFaultSearchForm.dev_no="+ encodeURI(dev_no) + "&devFaultSearchForm.def_grade=" + def_grade + "&devFaultSearchForm.def_object="+ def_object + "&devFaultSearchForm.def_type="+ def_type + "&devFaultSearchForm.def_symptom="+ def_symptom + "&devFaultSearchForm.def_state=" + encodeURI(def_state) +"&beginTime="+beginTime+"&endTime="+endTime;
		}
		
		//更新故障症状下来列表
		function update_sel_def_symptom(){
			$.getJSON("${ctx}/Gm_DevFault_json_getDef_symptomList.action?def_object_int="+document.getElementById('def_object').value+"&def_type_int="+document.getElementById('def_type').value,{
					random:Math.random()
			},function(def_symptoms){
				var sel_def_symptom = document.getElementById('def_symptom');
				sel_def_symptom.options.length = 0;
				sel_def_symptom.options.add(new Option('请选择……',''));
				for(var i=0;i<def_symptoms.length;i++){
					var def_symptom = def_symptoms[i];
					sel_def_symptom.options.add(new Option(def_symptom.name,def_symptom.id));
				}
			});
		}
		
		//查看通道信息
		function showChannel(ch_id){
			var charts_dialog = new J.dialog({
				id:'devFault_showChannel',
				title:'通道信息',
				autoSize:true,
				iconTitle:false,
				cover:false,
				bgcolor:'#000',
				rang: true,
				link:false,
				btnBar:false,
				cancelBtn:false,
				maxBtn:false,
				resize:false,
				page:'${ctx}/Gm_Channel_toDevFault_showChannel.action?gm_Channel.ch_id='+ch_id
			}); 
			charts_dialog.ShowDialog(); 
		}
	</script>
	

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	 <a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	 <a href="javascript:window.location.href=window.location.href">故障信息管理</a> <br/>
				
					根据 <bt/>
					<table cellpadding="0" cellspacing="0" border="0" width="768">
						<tr>
							<td width="256" style="padding: 0px;">
								设备编号：<input id="dev_no" style="width: 156px;" value="${devFaultSearchForm.dev_no }"> 
							</td>
							<td width="256" style="padding: 0px;">
								故障对象：<SELECT id="def_object" style="width: 156px;" onchange="update_sel_def_symptom()">
								<option value="">请选择……</option>
								<%
									List<Def_object> def_objects = StaticDataManage.getDef_objects();
									Iterator<Def_object> def_objectsIterator = def_objects.iterator();
								%>
								<%
									while (def_objectsIterator.hasNext()) {
										Def_object def_object = def_objectsIterator.next();
								%>
								<option value="<%=def_object.getId()%>"><%=def_object.getName()%></option>
								<%
									}
								%>
							</SELECT>
							</td>	
							<td width="256" style="padding: 0px;">
								故障类型：<SELECT id="def_type" style="width: 156px;" onchange="update_sel_def_symptom()">
								<option value="">请选择……</option>
								<%
									List<Def_type> def_types = StaticDataManage.getDef_types();
									Iterator<Def_type> def_typesIterator = def_types.iterator();
								%>
								<%
									while (def_typesIterator.hasNext()) {
										Def_type def_type = def_typesIterator.next();
								%>
								<option value="<%=def_type.getId()%>"><%=def_type.getName()%></option>
								<%
									}
								%>
							</SELECT>
							</td>		
						</tr>
						<tr>								
							<td width="256" style="padding: 0px;">
								故障症状：<SELECT id="def_symptom" style=" width: 156px;">
									<option value="">请选择……</option>
								</SELECT>	
							</td>
							<td width="256" style="padding: 0px;">
								故障等级：<SELECT id="def_grade" style="width: 156px;">
									<option value="">请选择……</option>
									<option value="0">轻微</option>
									<option value="1">中度</option>
									<option value="2">严重</option>
								</SELECT> 
							</td>
							<td width="256" style="padding: 0px;">
								故障状态：<SELECT style="width: 156px;" id="def_state">
									<option value="">请选择……</option>
									<option value="0">未解决</option>
									<option value="1">已解决</option>
								</SELECT>
							</td>
						</tr>
					</table>	

				<br/>
				<br/>
				故障发生时间：<input type="text" readonly="readonly" id="beginTime" name="beginTime" onClick="WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${beginTime }" />
				至<input type="text" id="endTime" readonly="readonly" name="endTime" onClick="WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${endTime }" />
				<!-- 
				<input id="hidQueryValue" type="hidden" value="${queryValue }">
  				<input id="hidQueryName" type="hidden" value="${queryName }">
				 -->
  				
  				<input id="hidDev_no" type="hidden" value="${devFaultSearchForm.dev_no }">
  				<input id="hidDef_grade" type="hidden" value="${devFaultSearchForm.def_grade }">
  				<input id="hidDef_object" type="hidden" value="${devFaultSearchForm.def_object }">
  				<input id="hidDef_type" type="hidden" value="${devFaultSearchForm.def_type }">
  				<input id="hidDef_symptom" type="hidden" value="${devFaultSearchForm.def_symptom }">
  				<input id="hidDef_state" type="hidden" value="${devFaultSearchForm.def_state }">
  				
  				<input id="hidBeginTime" type="hidden" value="${beginTime }">
  				<input id="hidEndTime" type="hidden" value="${endTime }">
  				
				<input type="button" onclick="document.getElementById('hidDev_no').value=document.getElementById('dev_no').value;document.getElementById('hidDef_grade').value=document.getElementById('def_grade').value;document.getElementById('hidDef_object').value=document.getElementById('def_object').value;document.getElementById('hidDef_type').value=document.getElementById('def_type').value;document.getElementById('hidDef_symptom').value=document.getElementById('def_symptom').value;document.getElementById('hidDef_state').value=document.getElementById('def_state').value;document.getElementById('hidBeginTime').value=document.getElementById('beginTime').value;document.getElementById('hidEndTime').value=document.getElementById('endTime').value;gotoPage(1)" value="查询">
		<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="100%">
			<tr class="list_head">
				<td>设备编号</td>
				<td>故障对象</td>	
				<td>故障类型</td>
				<td>故障症状</td>	
				<td>故障等级</td>							
				<td>发生故障的时间</td>
				<td>通道</td>
				<td>编辑</td>
			</tr>
			<c:forEach items="${page.result}" var="gm_DevFault" varStatus="n">
			<tr>
				<td>${gm_DevFault.dev_id.dev_no }&nbsp;</td>
				<td>${gm_DevFault.def_object_str }&nbsp;</td>
				<td>${gm_DevFault.def_type_str }&nbsp;</td>
				<td title="${gm_DevFault.def_desc }">${gm_DevFault.def_symptom_str }&nbsp;</td>
				<td>
					<c:choose>
						<c:when test="${gm_DevFault.def_grade == 0 }">
							轻微
						</c:when>
						<c:when test="${gm_DevFault.def_grade == 1 }">
							中度
						</c:when>
						<c:when test="${gm_DevFault.def_grade == 2 }">
							严重
						</c:when>
						<c:otherwise>
							未知
						</c:otherwise>
					</c:choose>
					&nbsp;
				</td>
				<td>${gm_DevFault.def_occurTime }&nbsp;</td>
				<td>
					<c:if test="${!empty gm_DevFault.ch_id}">
						<a href="javascript:showChannel('${gm_DevFault.ch_id }')">查看</a>
					</c:if>
					&nbsp;
				</td>
				<td>
					<a href="Gm_DevFault_editGm_DevFault.action?def_id=${gm_DevFault.def_id }">编辑/详情</a>
				</td>
			</tr>
			</c:forEach>
			<tr>
			<td colspan="4" align="left" valign="middle">
				<span style="padding-left: 10px; font-size: 12px;">
					共${page.totalCount}条记录，
					每页${page.pageSize}条，
					当前第${page.pageNo}页，
					共${page.totalPages}页
				</span>
			</td>
			<td colspan="4" align="right" valign="middle"
				style="padding-right: 10px; font-size: 12px;"
				class="more">
				<a href="javascript:gotoPage(1)">首&nbsp;&nbsp;页</a>
				<a href="javascript:gotoPage(${page.prePage})">
					上一页
				</a>
				<a href="javascript:gotoPage(${page.nextPage})">
					下一页
				</a>
				<a href="javascript:gotoPage(${page.totalPages})">
					尾&nbsp;&nbsp;页
				</a> 
			</td>
		</tr>
		</table>
		
	
	<script type="text/javascript">
		$(document).ready(function (){
			var beginTime = '${beginTime}';
			var endTime = '${endTime}';
			if(beginTime==null||beginTime==""||endTime==null||endTime==""){
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
			});

		$(document).ready(function(){
			var def_grade = '${devFaultSearchForm.def_grade }';
			if(def_grade!=null && def_grade.length > 0){
				$("#def_grade").val(def_grade);
			}
			var def_object = '${devFaultSearchForm.def_object }';
			if(def_object!=null && def_object!=""){
				$("#def_object").val(def_object);
			}
			var def_type = '${devFaultSearchForm.def_type }';
			if(def_type!=null && def_type!=""){
				$("#def_type").val(def_type);
			}
			$.getJSON("${ctx}/Gm_DevFault_json_getDef_symptomList.action?def_object_int="+document.getElementById('def_object').value+"&def_type_int="+document.getElementById('def_type').value,{
				random:Math.random()
			},function(def_symptoms){
				var sel_def_symptom = document.getElementById('def_symptom');
				sel_def_symptom.options.length = 0;
				sel_def_symptom.options.add(new Option('请选择……',''));
				for(var i=0;i<def_symptoms.length;i++){
					var def_symptom = def_symptoms[i];
					sel_def_symptom.options.add(new Option(def_symptom.name,def_symptom.id));
				}
				var def_symptom = '${devFaultSearchForm.def_symptom }';
				if(def_symptom!=null && def_symptom!=""){
					$("#def_symptom").val(def_symptom);
				}
			});		
			var def_state = '${devFaultSearchForm.def_state }';
			if(def_state!=null && def_state!=""){
				$("#def_state").val(def_state);
			}
		});
		
	</script>
	 
  </body>
</html>
