<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>显示用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
		//分页查询
		function gotoPage(pageNo){
			var queryName = document.getElementById("queryName").value;
			var queryValue = document.getElementById("queryValue").value;
			//alert(queryName);
			//alert(queryValue);			
			window.location.href = "${ctx}/Op_UserInfo_findAll.action?page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}" + "&queryName="+queryName +"&queryValue="+encodeURI(queryValue);
		}

		function change(value){
			var html = "";
			if(value == "user_loginName"){
				html = "<input id=\"queryValue\" type=\"text\" value=\"\">";
			}
			if(value == "user_name"){
				html = "<input id=\"queryValue\" type=\"text\" value=\"\">";
			}
			if(value == ""){
				html = "<input id=\"queryValue\" type=\"text\" value=\"\">";
			}
			if(value == "role_id"){
				var role = "${role}";
				html = "<select id=\"queryValue\">"+role+"</select>";
			}
			$("#values").html(html);
		}
		
	</script>

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
			<a href="javascript:window.location.href=javascript:window.location.href">用户信息管理</a> <br/>
  			<s:actionmessage theme="custom" cssClass="success"/>
  		查询条件<select id="queryName" onchange="change(this.value)">
  				<option value="">请选择……</option>
				<option value="user_loginName">
					用户名
				</option>
				<option value="user_name">
					真实姓名
				</option>
				<option value="role_id">
					用户角色
				</option>
			</select>
			<span id="values"><input id="queryValue" type="text" value="${queryValue }"></span>
			<input type="button" onclick="gotoPage(1)" value="查询"/>
			<input type="button" value="添加用户" onclick="location.href='Op_UserInfo_save.action?post=0'"/>
	
    	<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="100%">
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
			<c:forEach items="${page.result}" var="userinfo" varStatus="n">
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
						onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="6" align="left" valign="middle">
					<span style="padding-left: 10px; font-size: 12px;">
						共${page.totalCount}条记录， 每页${page.pageSize}条， 当前第${page.pageNo}页，
						共${page.totalPages}页 </span>
				</td>
				<td colspan="6" align="right" valign="middle"
					style="padding-right: 10px; font-size: 12px;" class="more">
					<a href="javascript:gotoPage(1)">首&nbsp;&nbsp;页</a>
					<a href="javascript:gotoPage(${page.prePage})"> 上一页 </a>
					<a href="javascript:gotoPage(${page.nextPage})"> 下一页 </a>
					<a href="javascript:gotoPage(${page.totalPages})">
						尾&nbsp;&nbsp;页 </a> 跳转到
					<select onChange="gotoPage(this.value)">
						<script type="text/javascript">
							for(var i=1;i<=parseInt("${page.totalPages}");i++){
							  if(i==parseInt("${page.pageNo}"))
					  			document.write("<option value="+i+" selected>"+i+"</option>");
					 		  else
					  			document.write("<option value="+i+">"+i+"</option>");
							}
						</script>
					</select>
					页
				</td>
			</tr>
		</table>
		
		<script type="text/javascript">
			var queryName = document.getElementById("queryName");
			for(var i = 0;i < queryName.options.length;i++){
				var op = queryName.options[i];
				if(op.value == '${queryName}'){
					op.selected = "selected";
				}
			}
			if('${queryName}' == "role_id"){
				var role = "${role}";
				var html = "<select id=\"queryValue\">"+role+"</select>";
				$("#values").html(html); 
			}

			if('${queryName}' == "role_id"){
				var queryValue = document.getElementById("queryValue");
				for(var i = 0;i < queryValue.options.length;i++){
					var op = queryValue.options[i];
					if(op.value == '${queryValue}'){
						op.selected = "selected";
					}
				}
			}
			
		</script>
  </body>
</html>
