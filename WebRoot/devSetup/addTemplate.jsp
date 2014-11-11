<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加模板信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/style_min.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/tooltips.css" />	
		
		<script src="${ctx}/js/rapid-validation/src/prototype_for_validation.js" type="text/javascript"></script>
		<script src="${ctx}/js/rapid-validation/lib/tooltips.js" type="text/javascript"></script>
		<script src="${ctx}/js/rapid-validation/lib/effects.js" type="text/javascript"></script>
		<script src="${ctx}/js/rapid-validation/src/validation_cn.js" type="text/javascript" charset="utf-8"></script>
		<script language="javascript" type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script>
		
		<script type="text/javascript">
			var DG = frameElement.lhgDG;
			//确定按钮及单击事件
			DG.addBtn( 'ok', '确定', ok ); 
			function ok() 
			{			
				if(!tmlvalid.validate())
					return;
				var dev_list = DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.transformToArray(DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getNodes());
				var str_dev_list = "[";
				for(var i=0;i<dev_list.length;i++){
					var dev = dev_list[i];
					if(dev.id!='root' && dev.dev_no==DG.curDoc.getElementById('gm_Device.dev_no').value){
						alert("GPRS设备编号与WSN设备编号重复");
						return;
					}
					if(dev.id!='root'){
						str_dev_list += "{";
						str_dev_list += "dev_id:'" + dev.dev_id + "',";
						str_dev_list += "dev_no:'" + dev.dev_no + "',";
						str_dev_list += "dev_name:'" + dev.dev_name + "',";
						str_dev_list += "dev_serial:'" + dev.dev_serial + "',";
						
						str_dev_list += "dev_model:'" + dev.dev_model + "',";
						str_dev_list += "net_powerType:'" + dev.dev_powerType + "',";
						
						str_dev_list += "tianjiawangluojiedian_scene_id:'" + dev.tianjiawangluojiedian_scene_id + "',";
						str_dev_list += "net_addr:'" + dev.net_addr + "',";
						
						str_dev_list += "net_role:'" + dev.net_role + "',";
						str_dev_list += "net_linkSts:'" + dev.net_linkSts + "',";
						str_dev_list += "net_sceneName:'" + dev.scene_name + "',";
						str_dev_list += "id:'" + dev.id + "',";
						str_dev_list += "pid:'" + dev.pid + "',";
						str_dev_list += "net_depth:" + dev.net_depth + ",";
						str_dev_list += "name:'" + dev.name + "'";
						str_dev_list += "}";
						str_dev_list += ",,";
					}	
				}
				if(str_dev_list.length>1)
					str_dev_list = str_dev_list.substr(0,(str_dev_list.length-2));
				DG.curDoc.getElementById('dev_list').value=str_dev_list+"]";
				var devst_name = document.getElementById('gm_devSetupTemplate.devst_name').value;		
				$.getJSON("${ctx}/devSetup_checkTemplate.action?gm_devSetupTemplate.devst_name="+devst_name,{
					random:Math.random()
				},function(isExist){
					if(isExist.value){
						alert(isExist.msg);
						return;
					}else{	
						DG.curWin.saveTemplate(devst_name);
						DG.cancel();
					}
				});			
			}
		</script>
	</head>

	<body>
	<font id="tmlform">
		<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>模板名称:</td>
				<td><input id="gm_devSetupTemplate.devst_name" name="gm_devSetupTemplate.devst_name" class="required" /></td>
			</tr>
		</table>
	</font>
	<script>
		var tmlvalid = new Validation('tmlform');
	</script>
	</body>
</html>
