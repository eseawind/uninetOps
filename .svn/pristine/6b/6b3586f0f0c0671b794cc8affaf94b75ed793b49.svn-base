<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>故障信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${ctx }/css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="${ctx }/js/date.js"></script>

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <form action="${ctx}/Gm_DevFault_updateGm_DevFault.action" method="post">
    	<input type="hidden" name="gm_DevFault.def_id" value="${gm_DevFault.def_id }">
    	<input type="hidden" name="gm_DevFault.dev_id.dev_id" value="${gm_DevFault.dev_id.dev_id }">
    	<input type="hidden" name="def_type" value="${gm_DevFault.def_type }">
    	<input type="hidden" name="def_grade" value="${gm_DevFault.def_grade }">
    	<input type="hidden" name="gm_DevFault.ch_id" value="${gm_DevFault.ch_id }">
    	<input type="hidden" name="gm_DevFault.def_grade" value="${gm_DevFault.def_grade }">
    	<input type="hidden" name="gm_DevFault.def_type" value="${gm_DevFault.def_type }">
    	<input type="hidden" name="gm_DevFault.def_occurTime" value="${gm_DevFault.def_occurTime }">
    	<input type="hidden" name="gm_DevFault.def_dealTime" value="${dateTime }" />
    	<table align="center" class="senfe1" style="font: 12px/1.5 tahoma,arial,宋体;scrollbar-face-color:#70807d; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff;">
    		<tr >
    			<td width="150px" align="center">设备编号:</td>
    			<td>${gm_DevFault.dev_id.dev_no }&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="center">故障等级:</td>
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
				&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="center">故障类型:</td>
    			<td>
    				<c:choose>
						<c:when test="${gm_DevFault.def_type == 0 }">
							平台
						</c:when>
						<c:when test="${gm_DevFault.def_type == 1 }">
							GPRS
						</c:when>
						<c:when test="${gm_DevFault.def_type == 2 }">
							WSN
						</c:when>
						<c:when test="${gm_DevFault.def_type == 3 }">
							传感器
						</c:when>
						<c:when test="${gm_DevFault.def_type == 4 }">
							控制设备
						</c:when>
						<c:otherwise>
							未知
						</c:otherwise>
					</c:choose>
    			&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="center">发生故障时间:</td>
    			<td>${gm_DevFault.def_occurTime }&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="center">故障发生原因:</td>
    			<td>
    				<textarea name="gm_DevFault.def_occurReason" style="width: 600px;height: 100" rows="" cols="">${gm_DevFault.def_occurReason }</textarea>
    			</td>
    		</tr>
    		<tr>
    			<td align="center">解决故障时间:</td>
    			<td>
    				${dateTime }&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<td align="center">解决故障方法:</td>
    			<td>
    				<textarea name="gm_DevFault.def_dealMethod" style="width: 600px;height: 100px;" rows="" cols="">${gm_DevFault.def_dealMethod }</textarea>
    			</td>
    		</tr>
    		<tr>
    			<td align="center">故障描述:</td>
    			<td>
    				<textarea name="gm_DevFault.def_desc" readonly="readonly" style="width: 600px;height: 100px;" rows="" cols="">${gm_DevFault.def_desc }</textarea>
    			&nbsp;</td>
    		</tr>
    		<tr>
    			<td colspan="2" align="center">
    				<input type="submit" value="保存">
    				<input type="button" onclick="javascript:history.go(-1);" value="返回">
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
