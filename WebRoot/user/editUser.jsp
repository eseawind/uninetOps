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

    <title>更新用户</title>
    <link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
   	<script language="javascript" type="text/javascript">
		function isValidate() {
			var loginname = document.getElementById("user_loginName").value;
			var loginpwd = document.getElementById("user_loginPwd").value;
			var loginpwd2 = document.getElementById("user_loginPwd2").value;
			var user_name = document.getElementById("user_name").value;
			var address = document.getElementById("user_address").value;
			var email = document.getElementById("user_email").value;
			var mobie = document.getElementById("user_mobie").value;
			var phone = document.getElementById("user_phone").value;
			var post = document.getElementById("user_post").value;
			var user_longitude = document.getElementById("user_longitude").value;
			var user_latitude =  document.getElementById("user_latitude").value;
					
			var emailzhengze = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			var mobilezhengze = /^(130|131|132|133|134|135|136|137|138|139|159|156|157|158|153|155|152|154|151|180|181|182|183|184|185|186|187|188|189)(\d){8}$/;	
			if(loginname == ""){
				window.confirm("用户名不能为空！");
				return false;
			}
			if(isNaN(user_longitude)){
				window.confirm("经度输入错误！");
				return false;
			}
			if(isNaN(user_latitude)){
				window.confirm("纬度输入错误！");
				return false;
			}
			if(loginpwd == ""){
				window.confirm("登录密码不能为空！");
				return false;
			}
			if(loginpwd2 == ""){
				window.confirm("确认密码不能为空！");
				return false;
			}
			if (loginpwd != "") {				
				if (loginpwd2 != loginpwd) {
					window.confirm("登录密码与确认密码不同,请重新输入！");
					return false;
				}
			}
			if (email != "") {				
				if(emailzhengze.test(email)==false){
					window.confirm("邮箱格式不正确，请重新输入！");
					return false;
				}
			}
			if(user_name == ""){
				window.confirm("真实姓名不能为空！");
				return false;
			}
			if(mobie != ""){
				if(mobilezhengze.test(mobie)==false){
					window.confirm("手机号码不正确，请重新输入！");
					return false;
				}
			}
			if(phone != ""){
				if(phonezhengze.test(phone)==false){
					window.confirm("电话号码不正确，请重新输入！");
					return false;
				}
			}			
			if(post != ""){
				if(postzhengze.test(post)==false){
					window.confirm("邮编不正确，请重新输入！");
					return false;
				}
			}
			return true;
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
			<a href="javascript:window.location.href='Op_UserInfo_findAll.action'">用户信息管理</a> 》
			<a href="javascript:window.location.href=window.location.href">编辑用户信息</a> <br/>
    <form action="Op_UserInfo_update.action" >
    	<c:forEach items="${requestScope.list}" var="userinfo">
    	<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="1020">
			<tr class="list_head">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr style="width: 8%">
				<td>登录用户</td>
				<td>
					<input type="hidden" name="op_UserInfo.user_id" value="${userinfo.user_id}"/>
					<input type="text" name="op_UserInfo.user_loginName" id="user_loginName" value="${userinfo.user_loginName}"/>					
				</td>
			</tr>
			<tr>
				<td>登录密码</td>
				<td><input type="text" name="op_UserInfo.user_loginPwd" id="user_loginPwd" value="${userinfo.user_loginPwd}"/></td>
			</tr><tr>
				<td>确认密码</td>
				<td><input type="text" name="user_loginPwd2" id="user_loginPwd2" value="${userinfo.user_loginPwd}"/></td>
			</tr>
			<tr>
				<td>真实姓名</td>
				<td><input type="text" name="op_UserInfo.user_name" id="user_name" value="${userinfo.user_name}"/></td>
			</tr>
			<tr>	
				<td>性别</td>
				<td>
					<select name="op_UserInfo.user_sex">
						<c:choose>
							<c:when test="${userinfo.user_sex eq '0'}">
								<option selected="selected" value="0">
									男
								</option>
								<option value="1">
									女
								</option>
							</c:when>
							<c:when test="${userinfo.user_sex eq '1'}">
								<option value="0">
									男
								</option>
								<option selected="selected" value="1">
									女
								</option>
							</c:when>
							<c:otherwise>
								<option selected="selected" value="0">
									男
								</option>
								<option value="1">
									女
								</option>
							</c:otherwise>
						</c:choose>
						
					</select>
				</td>
			</tr>
			<tr>
				<td>用户角色</td>
				<td>
					<select name="op_UserInfo.role_id.role_id" id="sex">
					<c:forEach items="${requestScope.roleInfo}" var="roleInfo">						
						<c:choose>
							<c:when test="${roleInfo.role_id == userinfo.role_id.role_id}">
								<option value="${roleInfo.role_id}" selected="selected">${roleInfo.role_name}</option>
							</c:when>
							<c:otherwise>
								<option value="${roleInfo.role_id}">${roleInfo.role_name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>					
				</td>
			</tr>
			<tr>	
				<td>地址</td>
				<td><input type="text" name="op_UserInfo.user_address" id="user_address" value="${userinfo.user_address}"/></td>
			</tr>
			<tr>	
				<td>公司名称</td>
				<td><input type="text" name="op_UserInfo.user_company" id="user_company" value="${userinfo.user_company}"/></td>
			</tr>
			<tr>	
				<td>邮箱</td>
				<td><input type="text" name="op_UserInfo.user_email" id="user_email" value="${userinfo.user_email}"/></td>
			</tr>
			<tr>
				<td>手机</td>
				<td><input type="text" name="op_UserInfo.user_mobie" id="user_mobie" value="${userinfo.user_mobie}"/></td>
			</tr>
			<tr>
				<td>经度</td>
				<td><input type="text" name="op_UserInfo.user_longitude" id="user_longitude" value="${userinfo.user_longitude}"/></td>
			</tr>
			<tr>
				<td>纬度</td>
				<td><input type="text" name="op_UserInfo.user_latitude" id="user_latitude" value="${userinfo.user_latitude}"/></td>
			</tr>
			<tr>	
				<td>部门</td>
				<td><input type="text" name="op_UserInfo.user_notes" id="user_notes" value="${userinfo.user_notes}"/></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type="text" name="op_UserInfo.user_phone" id="user_phone" value="${userinfo.user_phone}"/></td>
			</tr>
			<tr>
				<td>邮编</td>
				<td><input type="text" name="op_UserInfo.user_post" id="user_post" value="${userinfo.user_post}"/></td>							
			</tr>
			<tr>	
				<td>是否禁用</td>
				<td>
					<select name="op_UserInfo.user_enable">
						<c:choose>
							<c:when test="${userinfo.user_enable eq '0'}">
								<option selected="selected" value="0">
									禁用
								</option>
								<option value="1">
									启用
								</option>
							</c:when>
							<c:when test="${userinfo.user_enable eq '1'}">
								<option value="0">
									禁用
								</option>
								<option selected="selected" value="1">
									启用
								</option>
							</c:when>
							<c:otherwise>
								<option selected="selected" value="0">
									禁用
								</option>
								<option value="1">
									启用
								</option>
							</c:otherwise>
						</c:choose>
						
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="更 新" onclick="return isValidate()"/>	&nbsp;&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;				
					<input type="reset" value="取 消" onclick="window.location.href='Op_UserInfo_findAll.action'"/>
				</td>
			</tr>
		</table>
		</c:forEach>
	</form>
  </body>
</html>
