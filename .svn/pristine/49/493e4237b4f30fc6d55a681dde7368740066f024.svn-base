<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>畜禽养殖监控管理系统</title>
    
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
		function backMain(){
		    window.location.href="<%=basePath%>login/logout.jsp?jump=login4";
		}
	</script>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
  </head>
  <body scroll="no">
  <form action="op_sysfun_tt.action" method="post">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td height="560" align="center" class="enter_bg3"><table width="702" border="0" cellspacing="0" cellpadding="0" class="center">
      <tr  >
        <td height="30" valign="top" class="pix01"><!--版权信息 & 透明flash动画   start -->
            <div id="Layer1"  >
              <div id="userpw">
               <table width="320" border="0" align="left" cellpadding="0" cellspacing="0" >
          <tr>
            <td width="67" height="35" class="user_txt"> 用户名：</td>
            <td width="173"  class="load_input_td"><input type="text" class="input_bg" name="op_UserInfo.user_loginName" id="loginName" size="18" maxlength="18" /></td>
            <td width="80" rowspan="2" valign="top" class="loadbn"><input name="load" type="submit"  class="bn_bg" id="load" value=" " onclick="return isValidate()"/></td>
          </tr>
          <tr>
            <td height="35" class="pw_txt"> 密　码：</td>
            <td  class="load_input_td"><input type="password" class="input_bg" name="op_UserInfo.user_loginPwd" id="loginPwd" size="18" maxlength="16" /></td>
          </tr>
        </table>
              </div>
              <div id="copyright">版权所有：中国农业大学物联网工程技术中心</div>
            </div>
          <!--版权信息 & 透明flash动画   end -->        </td>
        <td width="100%" >&nbsp;</td>
      </tr>
      <tr  >
        <td width="7" height="30" >&nbsp;</td>
        <td width="695" >&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
