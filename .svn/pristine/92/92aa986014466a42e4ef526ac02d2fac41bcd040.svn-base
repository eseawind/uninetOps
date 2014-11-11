<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示采集通道应用类型信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <input type="button" value="添加用户" onclick="location.href='Op_ChannelType_save.action?post=0'"/>
    	<table border="1" cellpadding="0" cellspacing="0" class="senfe1">
			<tr class="list_head">
				<td>序号</td>
				<td>类型编号</td>	
				<td>类型名称</td>	
				<td>小数位数</td>	
				<td>校正公式</td>
				<td>校正后单位</td>	
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="channeltype" varStatus="n">
			<tr>
				<td>${n.count}</td>
				<td>${channeltype.chtype_no}</td>
				<td>${channeltype.chtype_displayName}</td>	
				<td>${channeltype.ch_dectDig}</td>	
				<td>${channeltype.ch_corrFormula}</td>
				<td>${channeltype.ch_corrUnit}</td>	
				<td>
					<a href="Op_ChannelType_findByroleid.action?op_ChannelType.chtype_id=${channeltype.chtype_id}">编辑</a>
					<a href="Op_ChannelType_delete.action?op_ChannelType.chtype_id=${channeltype.chtype_id}"
						onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
  </body>
</html>
