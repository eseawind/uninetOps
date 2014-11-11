<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>用户信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		//分页查询
		function gotoPage(pageNo){
			var queryName = document.getElementById("queryName").value;
			var queryValue = document.getElementById("queryValue").value;			
			window.location.href = "${ctx}/Op_UserInfo_page.action?page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}";
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <div align="left">首页 》用户信息管理</div>
    	
	    <form action="Op_UserInfo_Page.action">	
	    	<div align="left">   
		  	查询条件<select id="queryName" name="type">
				<option value="0">
					用户名
				</option>
				<option value="1">
					真实姓名
				</option>
				<option value="2">
					用户角色
				</option>
			</select>
				<input type="submit" value="查询"/>
				<input type="button" value="添加用户" onclick="location.href='Op_UserInfo_save.action?post=0'"/>
			</div>		
		</form>	
	<div align="left">
		<table width="100%" border="1" cellpadding="0" cellspacing="0" class="senfe1">		
			<tr class="list_head">
				<td>序号</td>
				<td>用户名</td>
				<td>真实姓名</td>	
				<td>用户角色</td>
				<td>地址</td>
				<td>公司名称</td>
				<td>邮箱</td>
				<td>手机</td>
				<td>部门</td>
				<td>电话</td>
				<td>邮编</td>
				<td>操作</td>
			</tr>		
			<c:forEach items="${page.result }" var="userinfo" varStatus="n">
			<tr>
				<td>${n.count}&nbsp;</td>
				<td>${userinfo.user_loginName}&nbsp;</td>
				<td>${userinfo.user_name}&nbsp;</td>				
				<td>${userinfo.role_id.role_name}&nbsp;</td>
				<td>${userinfo.user_address}&nbsp;</td>			
				<td>${userinfo.user_company}&nbsp;</td>
				<td>${userinfo.user_email}&nbsp;</td>
				<td>${userinfo.user_mobie}&nbsp;</td>
				<td>${userinfo.user_notes}&nbsp;</td>
				<td>${userinfo.user_phone}&nbsp;</td>
				<td>${userinfo.user_post}&nbsp;</td>
				<td>
					<a href="Op_UserInfo_findByUserId.action?op_UserInfo.user_id=${userinfo.user_id}">编辑</a>
					<a href="Op_UserInfo_delete.action?op_UserInfo.user_id=${userinfo.user_id}"
						onclick='return window.confirm("你确定要是删除吗？")'>删除</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
			<div style="width: 100%;">
				<div style="float: left; padding-left: 10px; font-size: 14px; width: 30%;">
					共${page.totalCount}条记录，每页${page.pageSize}条，当前第${page.pageNo}页，共${page.totalPages}页
				</div>
				<div style="float: right;padding-right: 35px; font-size: 14px;">
					<a href="javascript:gotoPage(1)">首&nbsp;&nbsp;页</a>
					<a href="javascript:gotoPage(${page.prePage})">上一页	</a>
					<a href="javascript:gotoPage(${page.nextPage})">下一页</a>
					<a href="javascript:gotoPage(${page.totalPages})">尾&nbsp;&nbsp;页</a> 跳转到
					<select onChange="gotoPage(this.value)">
						<script type="text/javascript">
							for(var i=1;i<=parseInt("${page.totalPages}");i++){
								if(i==parseInt("${page.pageNo}"))
							  		document.write("<option value="+i+" selected>"+i+"</option>");
							 	else
							  		document.write("<option value="+i+">"+i+"</option>");
							}
						</script>
					</select>页
				</div>
			</div>		
		</div>		
  </body>
</html>
