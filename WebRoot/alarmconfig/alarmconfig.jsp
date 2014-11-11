<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'alarmconfig.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/zonghe.css">
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/alarmconfig.js" ></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script> 
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx }/js/StringBuffer.js"></script>
	<script type="text/javascript" src="${ctx }/js/map.js"></script>

  </head>
  
  <body>
		   	<table width="98%">
    	<tr>
    		<td align="center">
				<form id="myform" name="myform" action="${ctx}/Op_AlarmConfig_save.action" method="post" >
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
				  <tr>
				  	<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr>
						  	<td width="14" height="36" style="background:url(${ctx }/images/alarm/_01.gif) no-repeat;">&nbsp;</td>
							<td style="background:url(${ctx }/images/alarm/_02.gif) repeat-x;">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
								  <tr>
									<td align="left" class="ft">
										预警配置<span id="sceneName">&nbsp;</span>
									</td>
									<td align="right">
										<!--<img src="${ctx }/images/alarm/save.gif" height="28" width="57" />-->
										<img alt="" style="cursor: pointer;" src="${ctx }/images/alarm/save.gif" onclick="submitForm();">
									</td>
								  </tr>
								 </table>
							</td>
							<td width="15" style="background:url(${ctx }/images/alarm/_03.gif) no-repeat;" >&nbsp;</td>
						  </tr>
						</table>
					</td>
				  </tr>
				  <tr>
				  	<td height="8" align="center" style="background-color:#fff3fa;"></td>
				  </tr>
				  <tr>
				  	<td height="22" valign="top" align="center" style="background-color:#fff3fa;border-bottom:1px #90adff solid;">
						 <input id="scene_id" name="configForm.sceneId" type="hidden" value="" />
						 <input id="isEnable" name="configForm.enable" type="hidden" value="" />
						 通知方式：<select id="al_noticeType" name="configForm.noticeType" style="width:50px;"><option value="All">全部</option><option value="Phone">手机</option><option value="Email">邮件</option></select>&nbsp;&nbsp;
						手机号：<input id="al_phone" type="text" textType="phone" name="configForm.phone" value="" size="10" class="required"/>&nbsp;
						邮箱：<input id="al_email" type="text" textType="email" name="configForm.email" value="" size="10" class="{required:true,validate-email:true}" />&nbsp;
						通知间隔：<input id="al_interval" type="text" textType="interval" name="configForm.interval" size="5" value="" class="{required:true,digits:true}" />&nbsp;
						是否开启：<input id="isConfigure" onclick="isChangeEnable(this)" type="checkbox" >
						<div class="error">
						</div>
				  			
					</td>
				  </tr>
				  <tr>
				  	<td>
				  		<input id="chIdHidden" type="hidden" >
				  		<table id="list" width="100%" border="0" cellpadding="0" cellspacing="0">
				  			
				  		</table>
				  	</td>
				  </tr>
				  <tr style="background:none;">
				  	<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr>
						  	<td width="14" height="36" style="background:url(${ctx }/images/alarm/_04.gif) no-repeat;">&nbsp;</td>
							<td align="right" style="background:url(${ctx }/images/alarm/_05.gif) repeat-x;">
							<img alt="" style="cursor: pointer;" src="${ctx }/images/alarm/save.gif" onclick="submitForm();">
							</td>
							<td width="15" style="background:url(${ctx }/images/alarm/_06.gif) no-repeat;" >&nbsp;</td>
						  </tr>
						</table>
					</td>
				  </tr>
				</table>
				</form>
			</td>
    	</tr>
    </table>
		  
  </body>
</html>
	<script type="text/javascript">
	if('${op_Scene.scene_id}'!=''){	
		$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id=${op_Scene.scene_id}",{
			random:Math.random()
		},function(op_Scene){
			window.parent.right.scene_tree.selectById('s_'+op_Scene.scene_id);
			echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
		});	
	}else{	
		if(window.parent.right.scene_tree.selectedNode!=null){
			var curr_node_id = this.parent.right.scene_tree.aNodes[window.parent.right.scene_tree.selectedNode].id;				
			var scene_id = curr_node_id.substr(2);
			$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
				random:Math.random()
			},function(op_Scene){
				echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
			});		
		}
	}
</script>
