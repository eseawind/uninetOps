<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>短信发送管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${ctx }/js/date.js"></script>
	
	<script type="text/javascript">
		//分页查询
		function gotoPage(pageNo){	
			var b = timeChecking();
			if(!b){
				return;
			}
			var queryValue = document.getElementById("hidQueryValue").value;
			var queryName = document.getElementById("hidQueryName").value;
			var beginTime = document.getElementById("hidBeginTime").value;
			var endTime = document.getElementById("hidEndTime").value;
			window.location.href = "${ctx}/tblSendSMS_findTblSendSMSPage.action?page.pageNo=" + pageNo + "&page.pageSize=10&queryValue=" + encodeURI(queryValue)+"&queryName="+queryName+"&beginTime="+beginTime+"&endTime="+endTime;
		}
	</script>
	
	<style>
		.ctl{
		   table-layout:fixed
		}
	  	.ctl td{text-overflow:ellipsis;overflow:hidden;white-space: nowrap;border-bottom: hidden; border-right: hidden;}
	</style>
	
	<script type="text/javascript">
		function change(value){
			var html = "";
			if(value == "flag"){
				html = "<select id=\"queryValue\"><option value=\"\">全部</option><option value=\"0\">未发送</option><option value=\"1\">已发送</option></select>";
			}
			if(value == "mobileNumber"){
				html = "<input id=\"queryValue\" type=\"text\" value=\"\">";
			}
			if(value == ""){
				html = "<input id=\"queryValue\" type=\"text\" value=\"\">";
			}
			$("#values").html(html);
		}
	</script>
	
	<script type="text/javascript">
		//时间验证
		function timeChecking(){
			var begin = document.getElementById("beginTime").value.split("-");
			var end = document.getElementById("endTime").value.split("-");
			var beginTime = new Date(begin[0],begin[1],begin[2]);
			var endTime = new Date(end[0],end[1],end[2]);
			if(beginTime > endTime){
				alert("时间不对……");
				return false;
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
			return true;
		}
	</script>
	
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  		查询条件：<select id="queryName" onchange="change(this.value)">
  			<option value="">请选择……</option>
  			<option value="mobileNumber">手机号</option>
  			<option value="flag">标识</option>
  		</select>
  		<span id="values"><input id="queryValue" type="text"value="${queryValue }"></span>
  		开始时间：<input type="text" id="beginTime" readonly="readonly" name="beginTime" onfocus="setday(this,this)" value="${beginTime }" />
				至<input type="text" id="endTime" readonly="readonly" name="endTime" onfocus="setday(this,this)" value="${endTime }" />
  		<input id="hidQueryValue" type="hidden" value="${queryValue }">
  		<input id="hidQueryName" type="hidden" value="${queryName }">
  		<input id="hidBeginTime" type="hidden" value="${beginTime }">
  		<input id="hidEndTime" type="hidden" value="${endTime }">
  		<input type="button" onclick="document.getElementById('hidQueryValue').value=document.getElementById('queryValue').value;document.getElementById('hidQueryName').value=document.getElementById('queryName').value;document.getElementById('hidBeginTime').value=document.getElementById('beginTime').value;document.getElementById('hidEndTime').value=document.getElementById('endTime').value;gotoPage(1)" value="查询">
		<table border="0" style="font: 12px;text-align: center;" class="senfe1" width="100%">
			<tr class="list_head" align="center">
				<td>
					手机号
				</td>
				<td>
					发送时间
				</td>
				<td>
					标识
				</td>
				<td>
					发送内容
				</td>
			</tr>
			<c:forEach items="${page.result}" var="tblSendSMS">
				<tr>
					<td>${tblSendSMS.mobileNumber }</td>
					<td>${tblSendSMS.dd }</td>
					<c:choose>
						<c:when test="${tblSendSMS.flag == 0}">
							<td>未发送</td>
						</c:when>
						<c:when test="${tblSendSMS.flag == 1}">
							<td>已发送</td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<td>
						<table class="ctl" cellSpacing="0" border="0" cellpadding="1" width="90" style="font: 12px;">
							<tr>
								<td id="tip1" style="border: 0;" title="${tblSendSMS.sendmsg }">${tblSendSMS.sendmsg }</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td style="border: 0;" align="left" valign="middle">
					<span style="font-size: 12px;">
						共${page.totalCount}条记录， 每页${page.pageSize}条， 当前第${page.pageNo}页，
						共${page.totalPages}页 </span>
				</td>
				<td style="border: 0;" align="right" valign="middle"
					style="font-size: 12px;">
					<a href="javascript:gotoPage(1)">首&nbsp;&nbsp;页</a>
					<a href="javascript:gotoPage(${page.prePage})"> 上一页 </a>
					<a href="javascript:gotoPage(${page.nextPage})"> 下一页 </a>
					<a href="javascript:gotoPage(${page.totalPages})">
						尾&nbsp;&nbsp;页 </a> 跳转到
					<select onChange="gotoPage(this.value)">
						<script type="text/javascript">
						for(var i=1;i<=parseInt("${page.totalPages}");i++){
						  if(i==parseInt("${page.pageNo}"))
				  			document.write("<option value="+i+" selected>"+i+"</option>");
				 		  else
				  			document.write("<option value="+i+">"+i+"</option>");
						}
					</script>
					</select>
					页
				</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<script type="text/javascript">
			var queryName = document.getElementById("queryName");
			for(var i = 0;i < queryName.options.length;i++){
				var option = queryName.options[i];
				if(option.value == '${queryName}'){
					option.selected="selected";
				}
			}
			
			if(queryName.value == "flag"){
				var html = "<select id=\"queryValue\"><option value=\"\">全部</option><option value=\"0\">未发送</option><option value=\"1\">已发送</option></select>";
				$("#values").html(html);
			}
			
			if(queryName.value == "flag"){
				var queryValue = document.getElementById("queryValue");
				for(var i = 0;i < queryValue.options.length;i++){
					var option = queryValue.options[i];
					if(option.value == '${queryValue}'){
						option.selected="selected";
					}
			}
			}
		</script>
		
	</body>
</html>
