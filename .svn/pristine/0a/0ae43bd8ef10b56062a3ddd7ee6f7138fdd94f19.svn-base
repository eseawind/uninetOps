<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   	<title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script> 
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.min.js?t=self&s=facebook"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var DG = frameElement.lhgDG;
		DG.addBtn( 'ok', '确定', function(){ commitForm(); });
		
		function commitForm () {
			if(isValidate()) {
				document.forms[0].submit();
			}
		}

		function isValidate() {
			var loginpwd = document.getElementById("loginPwd").value;
			var loginpwd2 = document.getElementById("loginPwd2").value;
			if (loginpwd.value != "") {
				if (loginpwd2 != loginpwd) {
					alert("确认密码错误,请重新输入！");
					return false;
				}
			} else {
				alert("密码不能为空！");
				return false;
			}
			return true;
		}

		
		
	</script>
  </head>
  
  <body>
  		<form action="${ctx}/Op_UserInfo_updatePwd.action" id="addForm" method="post">
  			<table border="0" cellpadding="0" cellspacing="0" class="senfe1" width="100%">
  				<tr class="list_head">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>用户名</td>
					<td>
						${user.user_loginName}
						<input type="hidden" name="uid" value="${user.user_id}"/>
					</td>
				</tr>
				<tr>
					<td>登录密码</td>
					<td><input type="password" name="loginPwd" id="loginPwd" value="${user.user_loginPwd}" /></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input type="password" name="loginPwd2" id="loginPwd2"  /></td>
				</tr>
  			</table>
  		</form>
    </body>
    <c:if test="${message == 'ok'}">
    		<script>
				if (frameElement!=null)
				{
					alert("操作成功！");
					DG.cancel();
				}
			</script>
    </c:if>
</html>
