<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>菜单信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/js/validate/jquery.validate.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.metadata.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#myForm").validate();
		});
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <form id="myForm" action="${ctx }/op_sysfun_update.action" method="post">
    	<input type="hidden" name="op_SysFun.node_id" value="${opSysFun.node_id }">
    	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
    		<tr class="list_head">
				<td colspan="2">&nbsp;</td>
			</tr>
    		<tr>
    			<td>菜单名称</td>
    			<td>
    				<input type="text" id="name" name="op_SysFun.node_displayName" class="required" value="${opSysFun.node_displayName }"/>
    			</td>
    		</tr>
    		<tr>
    			<td>连接地址</td>
    			<td>
    				<input type="text" id="url" name="op_SysFun.node_url" class="required" value="${opSysFun.node_url }"/>
    			</td>
    		</tr>
    		<tr>
    			<td>父菜单</td>
    			<td>
    				<select id="nodePid" name="op_SysFun.node_pid.node_id">
    					<c:forEach items="${opSysFuns}" var="sysFun">
    						<option value="${sysFun.node_id }">${sysFun.node_displayName }</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td>排序号</td>
    			<td>
    				<input type="text" id="node" name="op_SysFun.node_sequence" class="{required:true,number:true}" value="${opSysFun.node_sequence }"/>
    			</td>
    		</tr>
    		<tr align="center">
    			<td colspan="2">
    				<input type="submit" value="提交" >
    				<input type="button" value="返回" onclick="javascript:window.location.href='${ctx}/op_sysfun_findSysfunAll.action'" >
    			</td>
    		</tr>
    	</table>
    </form>
    <script type="text/javascript">
    $(document).ready(function(){
		var nodeId = '${opSysFun.node_pid.node_id}';
		if(nodeId!=null && nodeId.length > 0){
			$("#nodePid").val(nodeId);
		}
	});
    </script>
  </body>
</html>
