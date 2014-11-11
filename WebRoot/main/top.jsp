<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/yunwei_style.css" />
	<script language="javascript" type="text/javascript" src="${ctx }/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script> 
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.min.js"></script>
	<script type="text/javascript">
		//系统当前时间
		jQuery(document).ready(function(){
			var now = new Date();
			var dateTime = now.toLocaleDateString();
			$("#nowDate").html(dateTime);
		});
		//用户修改密码
		function changePwd() {
			var dg = new J.dialog({ 
				id:'dd2', 
				title:'修改密码',
				iconTitle:false,
				page:'${ctx}/Op_UserInfo_editPwd.action', 
				cover:true,
				bgcolor:'#000',
				drag:false, 
				resize:false
			}); 
			dg.ShowDialog(); 
		}
	</script>
  </head>
  
  <body>
    <table width="100%" height="91" border="0" cellspacing="0" cellpadding="0">
  <tr class="logo_bg">
    <td colspan="2" class="align_left"><img src="${ctx }/images/yunwei_images/wlwxx_01.jpg" /></td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr class="nav_bg">
  	<td width="6"></td>
    <td class="align_left user_ft_sty">当前用户：<a href="#" onclick="changePwd(); return false;"><font color="#FFFFFF" style="text-decoration:none;">${user.user_name}</font></a>&nbsp;&nbsp;<span id="nowDate"></span></td>
    <td class="align_right"><a href="${ctx}/login/logout.jsp"><img src="${ctx }/images/yunwei_images/exit_1.png"></a></td>
    <td width="6"></td>
  </tr>
</table>
  </body>
</html>
