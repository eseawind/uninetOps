<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加网络节点信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="js/ztree/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" href="css/style_shuichan.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/style_min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/tooltips.css" />	
		
	<script src="${ctx}/js/rapid-validation/src/prototype_for_validation.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/lib/tooltips.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/lib/effects.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/src/validation_cn.js" type="text/javascript" charset="utf-8"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/ztree/jquery.ztree-2.6.js"></script>
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/uuid.js"></script>
	<script type="text/javascript">		//alert(new UUID());
		var shebeipeizhixinxiguanli_load = true;	
		//选择设备所属场景的树
    	var tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdezTree;
		var tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdezmNodes = ${scenes};
		var tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeSetting = {
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			fontCss: setFont,
			callback: {
				click: zTreeOnClick
			}			
		};
		
		$(document).ready(function(){
			$("body").bind("mousedown",function(event){
				if (!(event.target.id == "menuBtn" || event.target.id == "tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground" || $(event.target).parents("#tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").length>0)) {
					hideMenu();
				}
			});
		});		
		
		function setFont(treeId, treeNode) {
			if (treeNode && treeNode.isParent) {
				return {color: "blue"};
			} else {
				return null;
			}
		}
		
		function showMenu() {
			if(shebeipeizhixinxiguanli_load){			
				reloadTree();
				shebeipeizhixinxiguanli_load = false;
			}	
			var sceneObj = $("#tianjiawangluojiedian_sceneSel");
			var sceneOffset = $("#tianjiawangluojiedian_sceneSel").offset();
			$("#tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").css({left:sceneOffset.left + "px", top:sceneOffset.top + sceneObj.outerHeight() + "px"}).slideDown("fast");
			
		}
		
		function hideMenu() {
			$("#tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").fadeOut("fast");
		}
		
		function zTreeOnClick(event, treeId, treeNode) {
			if (treeNode) {
				var sceneObj = $("#tianjiawangluojiedian_sceneSel");
				var sceneHid = document.getElementById("tianjiawangluojiedian_scene_id.scene_id");
				var scene_name = document.getElementById('tianjiawangluojiedian_scene_name');
				sceneObj.attr("value", treeNode.name);
				scene_name.value = treeNode.name;
				sceneHid.value = treeNode.id;				
				hideMenu();
			}
		}
		
		function reloadTree() {
			hideMenu();
			tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdezTree = $("#tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenu").zTree(tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeSetting, tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdezmNodes);
			tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdezTree.expandAll(true);			
		}
		
		//网络类型下拉列表改变
		/**
		function net_typeOnChange(obj){
			var net_role = document.getElementById("net_role");
			net_role.length = 0;
			net_role.add(new Option("-- 请选择 --","-1"));
			if(obj.value == 0){
				$.getJSON("${ctx}/gm_devnet_findRolesByType.action?gm_DevNet.net_type="+obj.value,{
					random:Math.random()
				},function(roles){
					$.each(roles,function(i,role){
						net_role.add(new Option(role.name,role.id));
					})	
				});
			}else if(obj.value == 1){
				$.getJSON("${ctx}/gm_devnet_findRolesByType.action?gm_DevNet.net_type="+obj.value,{
					random:Math.random()
				},function(roles){
					$.each(roles,function(i,role){
						net_role.add(new Option(role.name,role.id));
					})	
				});
			}else if(obj.value == 2){
				$.getJSON("${ctx}/gm_devnet_findRolesByType.action?gm_DevNet.net_type="+obj.value,{
					random:Math.random()
				},function(roles){
					$.each(roles,function(i,role){
						net_role.add(new Option(role.name,role.id));
					})	
				});
			}
		}
		*/		
		var DG = frameElement.lhgDG;
		
		//默认所属场景
		$(document).ready(function(){
			var tianjiawangluojiedian_sceneSel = document.getElementById('tianjiawangluojiedian_sceneSel');
			var tianjiawangluojiedian_scene_id_scene_id = document.getElementById('tianjiawangluojiedian_scene_id.scene_id');
			var dev_no = document.getElementById('dev_no');
			var dev_no_main = DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode().dev_no;
			if(dev_no_main=="root"){
				dev_no_main = DG.curDoc.getElementById('gm_Device.dev_no').value + "-";
			}else{
				dev_no_main = dev_no_main+"-";
			}
			var scene_name = document.getElementById('tianjiawangluojiedian_scene_name');
			tianjiawangluojiedian_sceneSel.value = DG.curDoc.getElementById('sceneSel').value;
			tianjiawangluojiedian_scene_id_scene_id.value = DG.curDoc.getElementById('gm_Device.scene_id.scene_id').value;			
			dev_no.value = dev_no_main;
			scene_name.value = DG.curDoc.getElementById('sceneSel').value;
		});
		
		//确定按钮及单击事件
		DG.addBtn( 'ok', '确定', ok ); 
		function ok() 
		{			
			if(!valid2.validate())
				return;	
			var dev_list = DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.transformToArray(DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getNodes());
			for(var i=0;i<dev_list.length;i++){
				var dev = dev_list[i];
				if(dev.id!='root' && dev.dev_no==document.getElementById('gm_Device.dev_no').value){
					alert("WSN设备编号重复");
					return;
				}
			}	
			
			//alert(DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree);
			var dev_no = document.getElementById('dev_no').value;
			var dev_name = document.getElementById('dev_name').value;
			var dev_serial = document.getElementById('dev_serial').value;
			var strs = dev_serial.split("-");
			if(strs.length != 4){
				alert("序列号不合法");
				return;
			}
			var dev_model = strs[1];	
			//var dev_model = document.getElementById('dev_model').value;
			var dev_powerType = document.getElementById('dev_powerType').value;
			
			var tianjiawangluojiedian_scene_id = document.getElementById('tianjiawangluojiedian_scene_id.scene_id').value;
			var net_addr = document.getElementById('dev_no').value;
			
			var net_role = document.getElementById('net_role').value;
			var net_linkSts = document.getElementById('net_linkSts').value;
			
			var scene_name = document.getElementById('tianjiawangluojiedian_scene_name').value;
			
			var wangluojiedianxinxi = "[";
			wangluojiedianxinxi += "{";
			wangluojiedianxinxi += "dev_id:'" + new UUID + "',"; //这个ID现在json中没什么用了 ，后台还是通过id找节点信息对象，在后台对应的javaBean中这个字段是有用的，用来临时保存save后的设备ID Wang_Yuliang 2011-08-18
																 //这个字段后台也不用了 改成对象了gm_Device (先留着吧，反正不碍事) 0903
			wangluojiedianxinxi += "dev_no:'" + dev_no + "',";
			wangluojiedianxinxi += "dev_name:'" + dev_name + "',";
			wangluojiedianxinxi += "dev_serial:'" + dev_serial + "',";
			
			wangluojiedianxinxi += "dev_model:'" + dev_model + "',";
			wangluojiedianxinxi += "dev_powerType:'" + dev_powerType + "',";
			
			wangluojiedianxinxi += "tianjiawangluojiedian_scene_id:'" + tianjiawangluojiedian_scene_id + "',";
			wangluojiedianxinxi += "net_addr:'" + net_addr + "',";
			
			wangluojiedianxinxi += "net_role:'" + net_role + "',";
			wangluojiedianxinxi += "net_linkSts:'" + net_linkSts + "',";
			wangluojiedianxinxi += "scene_name:'" + scene_name + "',";
			wangluojiedianxinxi += "id:'" + new UUID() + "',";
			wangluojiedianxinxi += "pid:'" + DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode().id + "',";
			wangluojiedianxinxi += "net_depth:" + (DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode().net_depth+1) + ",";
			wangluojiedianxinxi += "name:'" + dev_no+"(场景:"+scene_name+",设备编号:"+dev_no+")" + "'";
			wangluojiedianxinxi += "}";
			wangluojiedianxinxi += "]";
			//alert(eval("(" + wangluojiedianxinxi+ ")").length);
			var json_wangluojiedianxinxi = eval("(" + wangluojiedianxinxi + ")");
			DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.addNodes(DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode(), json_wangluojiedianxinxi);
			//alert(22);
			DG.cancel(); 			
		}
	</script>
  </head>
  
  <body>
  	<font id="form2">
	  	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
	    	<tr>
	  			<td>
	  				设备编号:
	  				<input id="dev_no" name="gm_Device.dev_no" class="required validate-ajax-validate-${ctx }/devSetup_checkWSN_DEV_NO.action?what=gm_Device.dev_no"/>
	  			</td>
	  			<td>
	  				设备名称:
	  				<input id="dev_name" class="required"/>
	  			</td>
	  			<td>
	  				设备序列号:
	  				<input id="dev_serial" class="required"/>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>
	  				网内角色:
					<select id="net_role" class="required">	
						<%
							List<Net_role> net_roles = StaticDataManage.getNet_roles();
							Iterator<Net_role> net_rolesIterator = net_roles.iterator();
						%>
						<%
							while (net_rolesIterator.hasNext()) {
								Net_role net_role = net_rolesIterator.next();
								String id = net_role.getId();
								if("1".equals(id.substring(0,1))){
						%>
						<option value="<%=net_role.getId()%>"><%=net_role.getName()%></option>
						<%
								}
							}
						%>				
					</select>
				</td>
	  			<td>
	  				供电方式:
					<select id="dev_powerType" style="width:156px;" class="required">
						<%
							List<String> dev_powerTypes = StaticDataManage.getDev_powerTypes();
							Iterator<String> dev_powerTypesIterator = dev_powerTypes.iterator();
						%>
						<%
							while (dev_powerTypesIterator.hasNext()) {
								String dev_powerType = dev_powerTypesIterator.next();
								if("干电池".equals(dev_powerType)){
						%>
						<option value="<%=dev_powerType%>" selected="selected"><%=dev_powerType%></option>
						<%
								}else{
						%>	
						<option value="<%=dev_powerType%>"><%=dev_powerType%></option>
						<%
								}
							}
						%>
	  				</select>
	  			</td>
	  			<td>
	  				所属场景:
	  				<input id="tianjiawangluojiedian_sceneSel" readonly="readonly" class="required"/> <a href="#" onclick="showMenu()">选择</a>
	  				<input type="hidden" id="tianjiawangluojiedian_scene_id.scene_id" class="required">
	  				<input type="hidden" id="tianjiawangluojiedian_scene_name" class="required">
	  			</td>
	  		</tr>
	  		<tr>	  			
				<td>
	  				连接方式:
					<select id="net_linkSts" class="required">
						<%
							List<Net_linkSts> net_linkStss = StaticDataManage.getNet_linkStss();
							Iterator<Net_linkSts> net_linkStssIterator = net_linkStss.iterator();
						%>
						<%
							while (net_linkStssIterator.hasNext()) {
								Net_linkSts net_linkSts = net_linkStssIterator.next();
								if(net_linkSts.getId()=="2" && "2".equals(net_linkSts.getId())){
						%>
						<option value="<%=net_linkSts.getId()%>" selected="selected"><%=net_linkSts.getName()%></option>
						<%
								}else{
						%>		
						<option value="<%=net_linkSts.getId()%>"><%=net_linkSts.getName()%></option>
						<%
								}
						%>			
						<%
							}
						%>
					</select>
	  			</td>	  			
	  			<td>&nbsp;</td>
	  			<td>&nbsp;</td>
	  		</tr>
	    </table>
  	<div id="tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto; ">
	<ul id="tianjiawangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenu" class="tree"></ul>
</div>
	</font>
	<script>
		var valid2 = new Validation('form2');
	</script>
  </body>
</html>
