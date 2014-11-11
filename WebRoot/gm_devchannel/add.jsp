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
    
    <title>添加设备上报通道配置信息</title>

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <form action="Gm_DevChannel_save.action?post=1" method="post">
    	<table border="1" cellpadding="0" cellspacing="0">
    		<tr>
				<td>采集通道信息</td>
				<td>
					<select name="gm_DevChannel.ch_id.ch_id">
					<c:forEach items="${requestScope.channel}" var="channel">
						<option value="${channel.ch_id}">${channel.ch_name}</option>					
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>设备信息</td>
				<td>
					<select name="gm_DevChannel.dev_id.dev_id">
					<c:forEach items="${requestScope.device}" var="device">
						<option value="${device.dev_id}">${device.dev_serial}</option>					
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>设备地址</td>
				<td><input type="text" name="gm_DevChannel.dev_addr"/></td>
			</tr>			
			<tr>
				<td>通道顺序</td>
				<td><input type="text" name="gm_DevChannel.dch_order"/></td>
			</tr>
			<tr>
				<td>通道数据处理方式</td>
				<td>					
					<select name="gm_DevChannel.ch_procesStyle">					
						<option value="0">表示不处理（无效通道）</option>
						<option value="1">校正后存储（一般采集，数据上报-存储模式）</option>
						<option value="2">校正后定时存储（实时采集数据，平台定时存储）</option>
						<option value="3">校正后存储-设备能量状态显示</option>
						<option value="4">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>存储周期</td>
				<td><input type="text" name="gm_DevChannel.ch_memoryPeriod"/>秒</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="添 加" />				
					<input type="reset" value="取 消" onclick="window.location.href='Gm_DevChannel_findAll.action'"/>
				</td>
			</tr>
    	</table>
    </form>
  </body>
</html>
