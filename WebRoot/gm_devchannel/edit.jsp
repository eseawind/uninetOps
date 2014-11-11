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
    
    <title>更新设备上报通道配置信息</title>

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <form action="Gm_DevChannel_update.action" method="post">
    	<c:forEach items="${requestScope.list}" var="devchannel">
    	<table border="1" cellpadding="0" cellspacing="0">
    		<tr>
				<td>采集通道信息</td>
				<td>
					<input type="hidden" name="gm_DevChannel.dch_id" value="${devchannel.dch_id}"/>
					<select name="gm_DevChannel.ch_id.ch_id">
					<c:forEach items="${requestScope.channel}" var="channel">
						<c:choose>
							<c:when test="${channel.ch_id == devchannel.ch_id.ch_id}">						
								<option value="${channel.ch_id}" selected="selected">${channel.ch_name}</option>
							</c:when>
							<c:otherwise>
								<option value="${channel.ch_id}">${channel.ch_name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>					
				</td>
			</tr>
			<tr>
				<td>设备信息</td>
				<td>
					<select name="gm_DevChannel.dev_id.dev_id">
					<c:forEach items="${requestScope.device}" var="device">
						<c:choose>
							<c:when test="${device.dev_id == devchannel.dev_id.dev_id}">
								<option value="${device.dev_id}" selected="selected">${device.dev_serial}</option>
							</c:when>
							<c:otherwise>
								<option value="${device.dev_id}">${device.dev_serial}</option>
							</c:otherwise>
						</c:choose>										
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>设备地址</td>
				<td><input type="text" name="gm_DevChannel.dev_addr" value="${devchannel.dev_addr}"/></td>
			</tr>
			<tr>
				<td>通道顺序</td>
				<td><input type="text" name="gm_DevChannel.dch_order" value="${devchannel.dch_order}"/></td>
			</tr>
			<tr>
				<td>通道数据处理方式</td>
				<td>					
					<select name="gm_DevChannel.ch_procesStyle">
						<c:if test="${devchannel.ch_procesStyle==0}">
						<option value="0" selected="selected">表示不处理（无效通道）</option>
						<option value="1">校正后存储（一般采集，数据上报-存储模式）</option>
						<option value="2">校正后定时存储（实时采集数据，平台定时存储）</option>
						<option value="3">校正后存储-设备能量状态显示</option>
						<option value="4">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>
						</c:if>
						<c:if test="${devchannel.ch_procesStyle==1}">
						<option value="0">表示不处理（无效通道）</option>
						<option value="1" selected="selected">校正后存储（一般采集，数据上报-存储模式）</option>
						<option value="2">校正后定时存储（实时采集数据，平台定时存储）</option>
						<option value="3">校正后存储-设备能量状态显示</option>
						<option value="4">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>
						</c:if>
						<c:if test="${devchannel.ch_procesStyle==2}">
						<option value="0">表示不处理（无效通道）</option>
						<option value="1">校正后存储（一般采集，数据上报-存储模式）</option>
						<option value="2" selected="selected">校正后定时存储（实时采集数据，平台定时存储）</option>
						<option value="3">校正后存储-设备能量状态显示</option>
						<option value="4">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>
						</c:if>
						<c:if test="${devchannel.ch_procesStyle==3}">
						<option value="0">表示不处理（无效通道）</option>
						<option value="1">校正后存储（一般采集，数据上报-存储模式）</option>
						<option value="2">校正后定时存储（实时采集数据，平台定时存储）</option>
						<option value="3" selected="selected">校正后存储-设备能量状态显示</option>
						<option value="4">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>
						</c:if>
						<c:if test="${devchannel.ch_procesStyle==4}">
						<option value="0">表示不处理（无效通道）</option>
						<option value="1">校正后存储（一般采集，数据上报-存储模式）</option>
						<option value="2">校正后定时存储（实时采集数据，平台定时存储）</option>
						<option value="3">校正后存储-设备能量状态显示</option>
						<option value="4" selected="selected">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<td>存储周期</td>
				<td><input type="text" name="gm_DevChannel.ch_memoryPeriod" value="${devchannel.ch_memoryPeriod}"/>秒</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="更 新" />				
					<input type="reset" value="取 消" onclick="window.location.href='Gm_DevChannel_findAll.action'"/>
				</td>
			</tr>
    	</table>
    	</c:forEach>
    </form>
  </body>
</html>
