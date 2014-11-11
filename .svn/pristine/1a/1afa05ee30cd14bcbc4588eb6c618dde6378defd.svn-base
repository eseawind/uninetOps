<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>菜单管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<s:actionmessage theme="custom" cssClass="success"/>
	<SCRIPT type="text/javascript">
		function find(){
			var queryValue = document.getElementById("queryValue").value;
			window.location.href="${ctx}/op_sysfun_findSysfunAll.action?queryValue=" + encodeURI(queryValue);
		}
	</SCRIPT>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
	根据<select>
		<option>菜单名称</option>
	</select>
	查询<input id="queryValue" value="${queryValue }"/>
	<input type="button" value="查询" onclick="find()" >
  	<input type="button" onclick="javascript:window.location.href='${ctx}/op_sysfun_add.action'" value="添加" >
    <table cellpadding="0" cellspacing="0" class="senfe1" width="100%">
    	<tr class="list_head">
    		<td>菜单名称</td>
    		<td>连接地址</td>
    		<td>父节点</td>
    		<td>排序号</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${opSysFuns}" var="opSysFun">
    		<tr>
    			<td>${opSysFun.node_displayName }&nbsp;</td>
    			<td>${opSysFun.node_url }&nbsp;</td>
    			<td>${opSysFun.node_pid.node_displayName }&nbsp;</td>
    			<td>${opSysFun.node_sequence }&nbsp;</td>
    			<td>
    				<a href="${ctx }/op_sysfun_edit.action?id=${opSysFun.node_id}">编辑</a>
    				<a href="${ctx }/op_sysfun_delete.action?id=${opSysFun.node_id}">删除</a>
    			</td>	
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
