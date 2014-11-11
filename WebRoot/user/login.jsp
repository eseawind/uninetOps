<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
    <script language="javascript" type="text/javascript">
		function isValidate() {
			var username = document.getElementById("loginName").value;
			var password = document.getElementById("loginPwd").value;
			if (username == "") {
				window.confirm("用户名不能为空！");
				return false;
			} else if (username.length > 20 || username.length < 4) {
				window.confirm("用户名长度不符(4-20)！");
				return false;
			} else if (password == "") {
				window.confirm("密码不能为空");
				return false;
			} else if (password.length > 20 || password.length < 4) {
				window.confirm("密码长度不符(4-20)");
				return false;
			}else {
				return true;
			}
		}
	</script>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body style=" background-image: url('images/bgcolor.jpg')">
    <form action="op_sysfun_tt.action" method="post">		
		<div style="background-image: url('images/bg1.jpg');width: 773px;height: 560px;">
			<table border="0" cellpadding="0" cellspacing="0" class="table">
		  		<tr>
					<td>用户名：</td>
					<td><input type="text" name="op_UserInfo.user_loginName" id="loginName" class="text"/></td>
		 	 	</tr>
		 	 	<tr>
					<td>密&nbsp;码：</td>
					<td><input type="password" name="op_UserInfo.user_loginPwd" id="loginPwd" class="text"/></td>
		 	 	</tr>
		 	 	<tr>	
					<td colspan="2">&nbsp;</td>
		 	 	</tr>
		 	 	<tr>					
					<td colspan="2"><input type="submit" value="登录" class="butten1" onclick="return isValidate()"/>&nbsp;&nbsp;
					<input type="reset" class="butten1" value="取消"/></td>
		 	 	</tr>
			</table>			
		</div>		    
    </form>
  </body>
</html>
