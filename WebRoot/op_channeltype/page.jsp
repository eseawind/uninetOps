<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>显示采集通道应用类型信息</title>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		//分页查询
		function gotoPage(pageNo){	
			var queryValue = document.getElementById("queryValue").value;
			//alert(queryValue);	
			window.location.href = "${ctx}/Op_ChannelType_findAll.action?page.pageNo=" + pageNo + "&page.pageSize=10&queryValue=" + encodeURI(queryValue);
		}
	</script>
	
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">

<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
<a href="#">采集通道应用类型信息管理</a> 
<s:actionmessage theme="custom" cssClass="success"/>
<br>

  				类型名称：
  				<input id="queryValue" type="text" value="${queryValue }">
  				<input type="button" onclick="gotoPage(1)" value="查 询" >
  				<c:if test='${role.role_id=="role-1"}'>
  					<input type="button" value="添 加" onclick="location.href='Op_ChannelType_save.action?post=0'"/>
  				</c:if>
	  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="senfe1" style="text-align: center;">
	  		<tr class="list_head">
				<td>序号</td>
				<td>类型编号</td>	
				<td>类型名称</td>	
				<td>小数位数</td>	
				<td>校正公式</td>
				<td>校正后单位</td>	
				<td>操作</td>
			</tr>
			<c:forEach items="${page.result}" var="channeltype" varStatus="n">
			<tr>
				<td>${n.count}&nbsp;</td>
				<td>${channeltype.chtype_no}&nbsp;</td>
				<td>${channeltype.chtype_displayName}&nbsp;</td>	
				<td>${channeltype.ch_dectDig}&nbsp;</td>	
				<td>${channeltype.ch_corrFormula}&nbsp;</td>
				<td>${channeltype.ch_corrUnit}&nbsp;</td>
				<td>
					<c:if test='${role.role_id=="role-1"}'>
						<a href="Op_ChannelType_findByroleid.action?op_ChannelType.chtype_id=${channeltype.chtype_id}">编辑</a>
					</c:if>
					<!-- 
					<a href="Op_ChannelType_delete.action?op_ChannelType.chtype_id=${channeltype.chtype_id}"
						onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
					 --> &nbsp;
				</td>
			</tr>
			</c:forEach>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">	
			<tr>
			<td colspan="5" align="left" valign="middle">
				<span style="padding-left: 10px; font-size: 12px;">
					共${page.totalCount}条记录，
					每页${page.pageSize}条，
					当前第${page.pageNo}页，
					共${page.totalPages}页
				</span>
			</td>
			<td colspan="9" align="right" valign="middle"
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
				</a> 跳转到
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
		
  </body>
</html>
