<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.unism.op.action.Op_SysFunAction"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="org.wangzz.core.web.struts2.Struts2Utils"%>
<%@page import="org.unism.op.domain.Op_UserInfo"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>农业物联网监控系统运维平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script> 
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.min.js"></script>
	<!-- 
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			font-size: 12px;
		}
		.menuTree{
			WIDTH: 183; 
			HEIGHT: 510; 
			OVERFLOW: scroll; 
			scrollbar-face-color:#70807d; 
			scrollbar-arrow-color:#ffffff; 
			scrollbar-highlight-color:#ffffff; 
			scrollbar-3dlight-color:#70807d; 
			scrollbar-shadow-color:#ffffff; 
			scrollbar-darkshadow-color:#70807d; 
			scrollbar-track-color:#ffffff;	
		}	
		.sceneDeviceTree{
			WIDTH: 183; 
			HEIGHT: 450; 
			OVERFLOW: scroll; 
			scrollbar-face-color:#70807d; 
			scrollbar-arrow-color:#ffffff; 
			scrollbar-highlight-color:#ffffff; 
			scrollbar-3dlight-color:#70807d; 
			scrollbar-shadow-color:#ffffff; 
			scrollbar-darkshadow-color:#70807d; 
			scrollbar-track-color:#ffffff;	
		}
		.head_bg {
			background-image: url(${ctx }/images/fisheries/wlwxx_02.jpg);
			background-repeat: repeat-x;
		}
	-->
	</style>
	<script language="javascript" type="text/javascript" src="${ctx }/js/jquery.js"></script>
	<link href="${ctx}/css/fisheries/css.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		var sceneId = "";
		var sceneGtype = -1;
		window.onload=function(){
		  //var a=document.body.clientWidth-366;
		  //document.getElementById("td_centerFrame").style.width=a+"px";
		  document.getElementById("td_centerFrame").innerHTML = "<iframe frameborder=\"no\" width=\"100%\" id=\"centerFrame\" name=\"centerFrame\" height=\"532\" src=\"${ctx }/devSetup_list.action\"/>";
		  //document.getElementById("centerFrame").style.width=a+"px";
		  //document.getElementById("img_sceneDeviceShow").style.display='none';
		  //document.getElementById('img_menuHid').style.display='block';
		  //document.getElementById('td_menuTree').style.display='block';
		  var now = new Date();
		  document.getElementById("nowDate").innerHTML = now.toLocaleDateString();
		}
		
		function setupProject() {//应用配置
			document.getElementById("frameContent").src = "${ctx}/fisherirs_list.action?page.pageSize=10";
		}
		
		function aa(){
		}

		function toLogin() {
			window.location.href = "${ctx}/login/logout.jsp";
		}

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
  <input id="hid_showDeviceTree" type="hidden" value="1"><!-- 先给成1吧 最后再想办法改它 -->
<form name="f1">
<table id="Table_01" width="100%" border="0" cellpadding="0" cellspacing="0">  
  <tr class="head_bg"> 
    <td align="left"><img src="${ctx }/images/fisheries/wlwxx_01.jpg" alt=""></td>
    <td align="right"><!-- <img src="${ctx }/images/fisheries/wlwxx_03.jpg" alt=""> --></td>
  </tr>
  <tr> 
    <td height="34" colspan="2" background="${ctx }/images/fisheries/wlwxx_04.jpg">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="20">&nbsp;</td>
          <td width="18%" height="33" align="left" class="text">当前用户：<a href="#" background="" onclick="changePwd(); return false;"><font color="#FFFFFF">${user.user_name}</font></a>   <span id="nowDate"></span></td>
          <!--
          <td background="images/d4.gif" width="114px" align="center"><a href="${ctx }/op_scene_toSceneSO_0616.action" target="centerFrame"><font color="#FFFFFF" face="宋体" size="3">实时监控</font></a></td>
		  <td background="images/d4.gif" width="114px" align="center"><a href="${ctx }/op_scene_toDataCollect.action" target="centerFrame"><font color="#FFFFFF" face="宋体" size="3">数据汇总</font></a></td>
		  <td background="images/d4.gif" width="114px" align="center"><a href="${ctx }/gm_historydata_toCharts_0609.action" target="centerFrame"><font color="#FFFFFF" face="宋体" size="3">曲线分析</font></a></td>
		  <td background="images/d4.gif" width="114px" align="center"><a href="${ctx }/gm_historydata_toDataquery_0601.action" target="centerFrame"><font color="#FFFFFF" face="宋体" size="3">数据查询</font></a></td>
		  <td background="images/d4.gif" width="114px" align="center"><a href="${ctx}/fisherirs_list.action?page.pageSize=10" target="centerFrame"><font color="#FFFFFF" face="宋体" size="3">应用配置</font></a></td>
		  <!-- <td background="images/d4.gif" width="114px" align="center"><a href="http://221.174.25.225:6671/Aquaculture/login.jsp" target="_blank"><font color="#FFFFFF" face="宋体" size="3">生产管理</font></a></td> -->
		  <!--<td background="images/d4.gif" width="114px" align="center"><a href="${ctx}/forecast/forecast_60min.jsp" target="_blank"><font color="#FFFFFF" face="宋体" size="3">生产管理</font></a></td>
		  <td background="images/d4.gif" width="114px" align="center"><a href="${ctx}/map/map.jsp" target="centerFrame"><font color="#FFFFFF" face="宋体" size="3">地图浏览</font></a></td>
		  -->
          <td align="right" class="text"><a href="${ctx}/login/logout.jsp"><img src="${ctx}/images/fisheries/exit_1.png" name="Image1" width="96" height="26" hspace="5" border="0" id="Image1"></a></td>
        </tr>
      </table>
	 </td>																											
  </tr>
  <tr>
    <td colspan="2">
		<table width="100%" height="460" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td id="td_menuTree" align="left" background="images/1.jpg" width="185" height="100%;" valign="top">
				<table width="183" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td height="22" colspan="2" background="images/ltf.jpg">&nbsp;</td>
				  </tr>
				</table>
				<div id="div_menuTree" align="left" class="menuTree">			   		
					<jsp:include page="/op_sysfun/menu_tree_0726.jsp"></jsp:include>
			    </div>
			</td>
			<td width="8px;" height="460px;" valign="middle" background="images/tiao3.jpg" style="border-right: solid 1px green; ">
				<img id="img_menuHid" src="${ctx }/images/hidTree1.jpg" onclick="this.style.display='none';document.getElementById('img_menuShow').style.display='block';document.getElementById('td_menuTree').style.display='none';">
				<img id="img_menuShow" src="${ctx }/images/showTree1.jpg" onclick="this.style.display='none';document.getElementById('img_menuHid').style.display='block';document.getElementById('td_menuTree').style.display='block';" style="display: none;"/>
			</td>
			<td id="td_centerFrame" name="td_centerFrame" style="border:thick #acd586" valign="top">
				&nbsp;
			</td>
			<td width="8px;" height="460px;" valign="middle" background="images/tiao3.jpg" style="border-left: solid 1px green;">
				<img id="img_sceneDeviceHid" src="${ctx }/images/showTree1.jpg" onclick="this.style.display='none';document.getElementById('img_sceneDeviceShow').style.display='block';document.getElementById('td_sceneDeviceTree').style.display='none';"/>
				<img id="img_sceneDeviceShow" src="${ctx }/images/hidTree1.jpg" onclick="this.style.display='none';document.getElementById('img_sceneDeviceHid').style.display='block';document.getElementById('td_sceneDeviceTree').style.display='block';" style="display: none;"/>
			</td>
			<td id="td_sceneDeviceTree" background="images/1.jpg" width="185" height="460" valign="top" align="left">
				<table width="183" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td height="22" colspan="2" background="images/rtf.jpg">&nbsp;</td>
				  </tr>
				</table>
				<div id="div_changeToSceneTree" align="center" style="background:url(images/button7.jpg); font-size: 12px; width: 183; height: 22px; line-height: 22px;display: none;" onclick="this.style.display='none';document.getElementById('div_changeToDeviceTree').style.display='block';document.getElementById('div_deviceTree').style.display='none';document.getElementById('div_sceneTree').style.display='block';"></div>
				<div id="div_changeToDeviceTree" align="center" style="background:url(images/button7.jpg); font-size: 12px; width: 183; height: 22px;  line-height: 22px;" onclick="this.style.display='none';document.getElementById('div_changeToSceneTree').style.display='block';document.getElementById('div_sceneTree').style.display='none';document.getElementById('div_deviceTree').style.display='block';"></div>
				<div id="div_sceneTree" align="left" class="sceneDeviceTree">
					<jsp:include page="/op_scene/scene_tree.jsp"></jsp:include>
				</div>					
				<div id="div_deviceTree" align="left" class="sceneDeviceTree" style="display: none;">					
					<jsp:include page="/gm_device/device_tree_0723.jsp"></jsp:include>
				</div>
				<table cellpadding="0" cellspacing="0" border="0">
					<tr><td colspan="2" style="background-color:#ffffff; padding:0px;border-width0px; height: 1px; line-height: 2px;">&nbsp;</td></tr>
					<tr><td id="td_changeSNDTree" colspan="2" style="background-color:#ffffff; padding:0px;border-width0px; height: 1px; line-height: 1px;" align="right"><img width="95" src="${ctx }/images/g.jpg"/></td></tr>
					<tr>
						<td style="padding: 0px; border-width: 0px; width: 91px; height: 30px;">
							<img id="img_changeToSceneTree" src="${ctx }/images/button7.gif" style="line-height: 26px;" onclick="document.getElementById('div_sceneTree').style.display='block';document.getElementById('div_deviceTree').style.display='none';document.getElementById('td_changeSNDTree').align='right';this.src='${ctx }/images/button7.gif';document.getElementById('img_changeToDeviceTree').src='${ctx }/images/button4.gif';">
						</td>
						<td style="padding: 0px; border-width: 0px; width: 91px; height: 30px;">
							<img id="img_changeToDeviceTree" src="${ctx }/images/button4.gif" style="line-height: 26px;" onclick="document.getElementById('div_deviceTree').style.display='block';document.getElementById('div_sceneTree').style.display='none';document.getElementById('td_changeSNDTree').align='left';this.src='${ctx }/images/button6.gif';document.getElementById('img_changeToSceneTree').src='${ctx }/images/button5.gif';">
						</td>
					</tr>
				</table>
			</td>
		  </tr>
		</table>
	</td>
  </tr>
  <tr>
    <td width="100%" style="background-color: #2459cd; line-height: 14px;" height="14" colspan="2">&nbsp;</td>
  </tr>
</table>
</form>
<script type="text/javascript">
	/**
	if(selectedID!=null && !this.parent.scene_tree.selectedFound){
		history.go(0);
	}	
	*/
	$(document).ready(function(){
		if('${scene_id}'!=''){	
			scene_tree.selectById('s_${scene_id}');
		}
	});
</script>
  </body>
</html>
