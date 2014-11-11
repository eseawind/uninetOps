<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<LINK href="images/login/login.css" type=text/css rel=stylesheet>
		<script src="js/jquery.js" type="text/javascript"></script>
		<title>物联网监控系统后台_用户登录</title>
		<script type="text/javascript">
			$(document).ready(function(){
	    		$("#loginName").focus();
	    		$("#loginFrom").keydown(function(event){   
					if(event.keyCode==13){   
				     	sub();
				  	}   
				});
	    		<%
				Object messageObj = request.getAttribute("message");
				if(messageObj != null) {
					out.print("alert('"+messageObj.toString()+"');");
				}
				%>
				if(window.top!=window.parent){
					window.parent.location = '${ctx}/login/logout.jsp';
				}
	 		});

			function sub(){
				if(isValidate()){
	 				$("#loginFrom").submit();
	 			}
	 		}
	 		
			function isValidate() {
				var username = document.getElementById("loginName").value;
				var password = document.getElementById("loginPwd").value;
				if (username == "") {
					alert("用户名不能为空！");
					$("#loginName").focus();
					return false;
				} else if (username.length > 20 || username.length < 1) {
					alert("用户名长度不符(1-20)！");
					$("#loginName").focus();
					return false;
				} else if (password == "") {
					alert("密码不能为空");
					$("#loginPwd").focus();
					return false;
				} else if (password.length > 20 || password.length < 1) {
					alert("密码长度不符(1-20)");
					$("#loginPwd").focus();
					return false;
				}else {
					return true;
				}
			}

			var a = self.parent.toLogin;
			var b = self.parent.parent.toLogin;
			if(a != null) {
				self.parent.toLogin();
			} else if(b != null) {
				self.parent.parent.toLogin();
			}
		</script>
	</head>

	<BODY>
		<DIV id="div1">
			<form action="login_val.action" method="post" id="loginFrom">
			<TABLE id="login" height="100%" cellSpacing="0" cellPadding="0" width="800" align="center">
				<TBODY>
					<TR id="main">
						<TD>
							<TABLE height="100%" cellSpacing="0" cellPadding="0" width="100%">
								<TBODY>
									<TR>
										<TD colSpan="4">
											&nbsp;
										</TD>
									</TR>
									<TR height="30">
										<TD width="380">
											&nbsp;
										</TD>
										<TD>
											&nbsp;
										</TD>
										<TD>
											&nbsp;
										</TD>
										<TD>
											&nbsp;
										</TD>
									</TR>
									<TR height="40">
										<TD rowSpan="4">
											&nbsp;
										</TD>
										<TD>
											用户名：
										</TD>
										<TD>
											<INPUT class="textbox" id="loginName" name="userName" value="${userName}">
										</TD>
										<TD width="120">
											&nbsp;
										</TD>
									</TR>
									<TR height="40">
										<TD>
											密 码：
										</TD>
										<TD>
											<INPUT class="textbox" id="loginPwd" type="password" name="password">
										</TD>
										<TD width="120">
											&nbsp;
										</TD>
									</TR>
									<TR height="40">
										<TD>
											&nbsp;
										</TD>
										<TD vAlign="center" colSpan="2">
											<INPUT id="btnLogin" type="button" value=" 登 录 " name="btnLogin" onclick="sub()">
										</TD>
									</TR>
									<TR height="40">
										<TD></TD>
										<TD align="right">
											
										</TD>
										<TD width="120">
											&nbsp;
										</TD>
									</TR>
									<TR height="110">
										<TD colSpan="4">
											&nbsp;
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
					<TR height="54"><!-- <TR height="104" id="root"> -->
						<TD align="center">
							版权所有：中国农业大学物联网工程技术中心&nbsp;
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</form>
		</DIV>
		<DIV id="div2" style="DISPLAY: none"></DIV>
	</BODY>
</html>
