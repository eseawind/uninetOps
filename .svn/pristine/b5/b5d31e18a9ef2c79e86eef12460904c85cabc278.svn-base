<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>物联网监控系统后台_用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="${ctx }/js/jquery.js" type="text/javascript"></script>
	<style type="text/css">
	*{
		border:none;
		margin:0 auto;
		padding:0;
		}
	html,body{
		margin:0 auto;
		padding:0;
		height:100%;
		width:100%;
		overflow:hidden;
		}
	body{
		background-color:#02366a;
		}
	a{
		text-decoration:none;
		}
	.login_bg{
		background:url(${ctx}/images/login/login_bg.jpg) repeat-x center center;
		}
	.login_box{
		background:url(${ctx}/images/login/login_box.jpg) no-repeat center center;
		}
	.name_ft{
		font-family:"宋体";
		font-size:14px;
		line-height:36px;
		text-align:center;
		width:66px;
		}
	.text_box{
		height:36px;
		width:184px;
		text-align:left;
		}
	.input_box{
		background:url(${ctx}/images/login/text_box.png) no-repeat; 
		width:172px;
		height:25px;
		line-height:25px;
		font-family:Verdana, Geneva, sans-serif;
		font-size:12px;
		text-align:left;
		border:none;
		outline: none;
		padding-left: 5px;
		padding-right: 5px;
		}
	.login_button{
		text-align:left;
		width:74px;
		}
	.copyright{
		font-family:"宋体";
		font-size:12px;
		color:#fff;
		position:absolute;
		bottom:30px;
		width:100%;
		text-align:center;
		}
</style>
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
  
  <body>
  
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="login_bg">
  <tr>
    <td align="center" height="100%">
    	<table width="980" height="100%" border="0" cellpadding="0" cellspacing="0" class="login_box">
          <tr>
          	<td valign="middle">
            	<table width="100%" height="768" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="center">
                        <table width="100%" height="300" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td rowspan="3" width="460">&nbsp;</td>
                            <td height="333">&nbsp;</td>
                            <td rowspan="3" width="186">&nbsp;</td>
                          </tr>
                          <tr>
                            <td>
                            	<form action="login_val.action" method="post" id="loginFrom">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td class="name_ft">用户名:</td>
                                    <td class="text_box"><input type="text" size="13" maxlength="13" id="loginName" tabindex="1" name="userName" value="${userName}" class="input_box" /></td>
                                    <td rowspan="2" class="login_button"><a href="javascript:sub();" onFocus="blur()"><img src="${ctx}/images/login/login_button.png"/></a></td>
                                  </tr>
                                  <tr>
                                    <td class="name_ft">密&nbsp;&nbsp;码:</td>
                                    <td class="text_box"><input id="loginPwd" size="22" maxlength="22" type="password" tabindex="2" name="password" class="input_box" /></td>
                                  </tr>
                                </table>
                                </form>
                            </td>
                          </tr>
                          <tr>
                            <td height="367">&nbsp;</td>
                          </tr>
                        </table>
                    </td>
                  </tr>
                </table>
            </td>
          </tr>
        </table>
    </td>
  </tr>
</table>

<div class="copyright">版权所有：中国农业大学物联网工程技术中心</div>
  </body>
</html>
