<%@ page language="java" import="java.util.*,org.unism.op.domain.*" pageEncoding="GB18030"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<% %>
<html>
  <head>
    
    <title>故障汇总页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="故障汇总页面">
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
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script> 
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.min.js"></script>
	<s:actionmessage theme="custom" cssClass="success"/>
	<script type="text/javascript">

		function gotoPage(pageNo){
			var date=document.getElementById("queryDate").value;
			var scene_id=document.getElementById("hid_scene_id").value;
			if(date== "" ){
				alert("请重新选择查询日期...");
				return false;
			}
			
			window.location.href = "${ctx}/Pro_FaultTaskAction_findUserNeed.action?page.pageNo=" + pageNo + "&page.pageSize=10&selectedDate=" + date + "&parentSceneId=" + scene_id;
		}

		function findHistorydata(){
			
			var date =document.getElementById("queryDate").value;
			var hid_scene_id =document.getElementById("hid_scene_id").value;
			window.location.href = "${ctx}/Pro_FaultTaskAction_findUserNeed.action?selectedDate="+date+"&parentSceneId="+hid_scene_id;
		}
		
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){

			document.getElementById("hid_scene_id").value = id;
			findHistorydata();
		}

	</script>
  </head>
 <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	 <a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	 <a href="javascript:window.location.href=window.location.href">故障信息每日汇总</a> <br/>
  	 <hr>
  	 （请选定一个日期并单击右侧场景树查询相应场景的故障汇总报表，日期默认为昨日日期）<br/>
	 日期设定：<input type="text" style="width:75px"readonly="readonly" id="queryDate" name="selectedDate" onClick="WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" value="${selectedDate}" />
	<l>
	<input type="hidden" id="hid_scene_id" name="parentSceneId" value="${parentSceneId}">
	<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="100%">
			<tr class="list_head">
				<td>场景名</td>
				<td>设备状态</td>	
				<td>故障详细信息</td>
			</tr>
				<c:forEach items="${page.result}" var="op_SceneFaultState" varStatus="n">
					<tr>
						<td>${op_SceneFaultState.sfs_sceneName}&nbsp;</td>
						<td>${op_SceneFaultState.sfs_devState }&nbsp;</td>
						<td>${op_SceneFaultState.sfs_inf }&nbsp;</td>
					</tr>
				</c:forEach>
			<tr>
			<td colspan="3" align="left" valign="middle">
				<span style="padding-left: 10px; font-size: 12px;">
					共${page.totalCount}条记录，
					每页${page.pageSize}条，
					当前第${page.pageNo}页，
					共${page.totalPages}页
				
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
				</span>
			</td>
		</tr>
		</table>
	<script type="text/javascript">
		$(document).ready(function (){
			var date = '${selectedDate}';
			if(date==null || date==""){
				//获取当前时间
		       	var dateNow = new Date();
		       	function chkTime(num){
		            return num<10?"0"+num:num;
				}
				//格式化时间
		       	Date.prototype.format = function(date , dformat){
		       		var year = date.getFullYear();
		       		var day = chkTime(date.getDate()-1);
		            var month = chkTime(date.getMonth()+1);
		       	 	return dformat.replace("y",year).replace("M",month).replace("d",day)

		       	}
		      	var dateFormated = dateNow.format(dateNow,"y-M-d");
		       	document.getElementById("queryDate").value = dateFormated;
			}
			});
		</script>
	</body>
</html>
