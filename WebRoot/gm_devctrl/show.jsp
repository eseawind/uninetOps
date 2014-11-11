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
    
    <title>显示控制设备信息</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <input type="button" value="添加用户" onclick="location.href='Gm_DevCtrl_save.action?post=0'"/>
    	<table border="1" cellpadding="0" cellspacing="0" class="senfe1">
			<tr class="list_head">
				<td>序号</td>
				<td>控制设备编号</td>				
				<td>设备名称</td>				
				<td>设备序列号</td>
				<td>控制设备名称</td>
				<td>所属设备编号</td>
				<td>类型按钮数量</td>			
				<td>购买时间</td>
				<td>服务到期时间</td>
				<td>生效时间</td>
				<td>设备使用状态</td>
				<td>场景名称</td>
				<td>是否对外提供服务</td>
				<td>设备说明</td>		
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="devctrl" varStatus="n">
			<tr>
				<td>${n.count}</td>
				<td>${devctrl.dect_no}</td>
				<td>${devctrl.dect_name}</td>				
				<td>${devctrl.dect_serial}</td>				
				<td>${devctrl.decttype_id.decttype_displayName}</td>
				<td>${devctrl.dev_id.dev_no}</td>
				<td>${devctrl.decttype_btnNum}</td>					
				<td>${devctrl.dect_buyDate}</td>
				<td>${devctrl.dect_overDate}</td>
				<td>${devctrl.dect_effectTime}</td>		
				<td>
					<c:if test="${devctrl.dect_state == 0}">
						未使用
					</c:if>
					<c:if test="${devctrl.dect_state == 1}">
						已使用
					</c:if>
				</td>
				<td>${devctrl.scene_id.scene_name}</td>	
				<td>
					<c:if test="${devctrl.ch_offerSer == 0}">
						否
					</c:if>
					<c:if test="${devctrl.ch_offerSer == 1}">
						是
					</c:if>		
				</td>
				<td>${devctrl.dect_decsription}</td>				
				<td>
					<a href="Gm_DevCtrl_findBydectid.action?gm_DevCtrl.dect_id=${devctrl.dect_id}">编辑</a>
					<c:if test="${user.role_id.role_id == 'role-1' }">
						<a href="Gm_DevCtrl_delete.action?gm_DevCtrl.dect_id=${devctrl.dect_id}"
							onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
  </body>
</html>
