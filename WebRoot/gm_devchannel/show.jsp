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
    
    <title>显示设备上报通道配置信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  <input type="button" value="添加用户" onclick="location.href='Gm_DevChannel_save.action?post=0'"/>
  		<table border="1" cellpadding="0" cellspacing="0" class="senfe1">
			<tr class="list_head">
				<td>序号</td>
				<td>采集通道信息</td>				
				<td>设备信息</td>
				<td>设备地址</td>
				<td>通道顺序</td>
				<td>通道数据处理方式</td>
				<td>存储周期</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="devchannel" varStatus="n">
			<tr>
				<td>${n.count}</td>
				<td>${devchannel.ch_id.ch_name}</td>				
				<td>${devchannel.dev_id.dev_no}</td>
				<td>${devchannel.dev_addr}</td>	
				<td>${devchannel.dch_order}</td>				
				<td>
					<c:if test="${devchannel.ch_procesStyle==0}">无效通道</c:if>
					<c:if test="${devchannel.ch_procesStyle==1}">一般采集，数据上报-存储模式</c:if>
					<c:if test="${devchannel.ch_procesStyle==2}">实时采集数据，平台定时存储</c:if>
					<c:if test="${devchannel.ch_procesStyle==3}">校正后存储-设备能量状态显示</c:if>
					<c:if test="${devchannel.ch_procesStyle==4}">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</c:if>
				</td>
				<td>${devchannel.ch_memoryPeriod}</td>			
				<td>
					<a href="Gm_DevChannel_findBydchid.action?gm_DevChannel.dch_id=${devchannel.dch_id}">编辑</a>
					<a href="Gm_DevChannel_delete.action?gm_DevChannel.dch_id=${devchannel.dch_id}"
						onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
  </body>
</html>
