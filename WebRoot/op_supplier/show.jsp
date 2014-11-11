<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示供应商信息</title>
    <link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
			<a href="#">供应商信息管理</a> 
			<br>
			<s:actionmessage theme="custom" cssClass="success"/>
    <input type="button" value="添加供应商信息" onclick="location.href='Op_Supplier_save.action?post=0'"/>
	  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="senfe1" style="text-align: center;">
	  		<tr class="list_head">
				<td>序号</td>
				<td>供应商名称</td>
				<td>供应商类型</td>				
				<td>供应商固定电话</td>
				<td>联系人姓名</td>
				<td>联系人手机</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="supplier" varStatus="n">
			<tr>
				<td>${n.count}&nbsp;</td>
				<td>${supplier.sup_name}&nbsp;</td>
				<td>
					<c:choose>
						<c:when test="${supplier.sup_type eq '0'}">
							销售商
						</c:when>
						<c:when test="${supplier.sup_type eq '1'}">
							服务商
						</c:when>
					</c:choose>
				&nbsp;</td>		
				<td>${supplier.sup_phone}&nbsp;</td>
				<td>${supplier.sup_contactName}&nbsp;</td>				
				<td>${supplier.sup_contactMobile}&nbsp;</td>
				<td>
					<a href="Op_Supplier_findBysupid.action?op_Supplier.sup_id=${supplier.sup_id}">编辑</a>
					<a href="Op_Supplier_delete.action?op_Supplier.sup_id=${supplier.sup_id}"
						onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
  </body>
</html>
