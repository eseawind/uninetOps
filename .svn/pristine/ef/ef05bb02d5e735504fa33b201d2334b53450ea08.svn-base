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
    
    <title>显示采集通道信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <input type="button" value="添加用户" onclick="location.href='Gm_Channel_save.action?post=0'"/>
    	<table border="1" cellpadding="0" cellspacing="0" class="senfe1">
			<tr class="list_head">
				<td>序号</td>
				<td>通道编号</td>
				<td>通道名称</td>				
				<td>采集设备编号</td>
				<td>设备通道号</td>
				<td>传感设备编号</td>
				<td>应用类型编号</td>
				<td>通道数据处理方式</td>
				<td>存储周期</td>
				<td>小数位数</td>
				<td>原数据单位</td>
				<td>量程上限</td>
				<td>量程下限</td>
				<td>变化率上限</td>
				<td>校准周期</td>
				<td>校正公式</td>
				<td>校正后的单位</td>
				<td>通道使用状态</td>
				<td>场景名称</td>
				<td>是否对外提供服务</td>			
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="channel" varStatus="n">
			<tr>
				<td>${n.count}</td>
				<td>${channel.ch_no}</td>
				<td>${channel.ch_name}</td>
				<td>${channel.dev_collectId.dev_no}</td>
				<td>${channel.ch_chlNo}</td>
				<td>${channel.dev_sensorId.dev_no}</td>				
				<td>${channel.chtype_id.chtype_no}</td>
				<td>
					<c:if test="${channel.ch_procesStyle==0}">无效通道</c:if>
					<c:if test="${channel.ch_procesStyle==1}">一般采集，数据上报-存储模式</c:if>
					<c:if test="${channel.ch_procesStyle==2}">实时采集数据，平台定时存储</c:if>
					<c:if test="${channel.ch_procesStyle==3}">校正后存储-设备能量状态显示</c:if>
					<c:if test="${channel.ch_procesStyle==4}">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</c:if>
				</td>				
				<td>${channel.ch_memoryPeriod}秒</td>
				<td>${channel.ch_dectDig}</td>
				<td>${channel.ch_unit}</td>
				<td>${channel.ch_max}</td>	
				<td>${channel.ch_min}</td>
				<td>${channel.ch_crateMax}</td>
				<td>${channel.ch_corrCyc}</td>				
				<td>${channel.ch_corrFormula}</td>
				<td>${channel.ch_corrUnit}</td>
				<td>
					<c:if test="${channel.ch_state == 0}">未使用</c:if>
					<c:if test="${channel.ch_state == 1}">已使用</c:if>
				</td>
				<td>${channel.scene_id.scene_name}</td>				
				<td>
					<c:if test="${channel.ch_offerSer == 0}">否</c:if>
					<c:if test="${channel.ch_offerSer == 1}">是</c:if>		
				</td>			
				<td>
					<a href="Gm_Channel_findBychid.action?gm_Channel.ch_id=${channel.ch_id}">编辑</a>
					<a href="Gm_Channel_delete.action?gm_Channel.ch_id=${channel.ch_id}" 
						onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
  </body>
</html>
