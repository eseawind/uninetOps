<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>编辑网络节点信息</title>
    
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
    	var bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdezTree;
		var bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdezmNodes = ${scenes};
		var bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeSetting = {
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
				if (!(event.target.id == "menuBtn" || event.target.id == "bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground" || $(event.target).parents("#bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").length>0)) {
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
			var sceneObj = $("#bianjiwangluojiedian_sceneSel");
			var sceneOffset = $("#bianjiwangluojiedian_sceneSel").offset();
			$("#bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").css({left:sceneOffset.left + "px", top:sceneOffset.top + sceneObj.outerHeight() + "px"}).slideDown("fast");
			
		}
		
		function hideMenu() {
			$("#bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").fadeOut("fast");
		}
		
		function zTreeOnClick(event, treeId, treeNode) {
			if (treeNode) {
				var sceneObj = $("#bianjiwangluojiedian_sceneSel");
				var sceneHid = document.getElementById("bianjiwangluojiedian_scene_id.scene_id");
				var scene_name = document.getElementById('bianjiwangluojiedian_scene_name');
				sceneObj.attr("value", treeNode.name);
				scene_name.value = treeNode.name;
				sceneHid.value = treeNode.id;				
				hideMenu();
			}
		}
		
		function reloadTree() {
			hideMenu();
			bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdezTree = $("#bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenu").zTree(bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeSetting, bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdezmNodes);
			bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdezTree.expandAll(true);			
		}	

		var DG = frameElement.lhgDG;
		
		//默认所属场景
		$(document).ready(function(){ 
			var dev_no = document.getElementById('dev_no');
			var dev_name = document.getElementById('dev_name');
			var dev_serial = document.getElementById('dev_serial');
			var net_role = document.getElementById('net_role');
			var dev_powerType = document.getElementById('dev_powerType');
			var bianjiwangluojiedian_sceneSel = document.getElementById('bianjiwangluojiedian_sceneSel');
			var scene_id = document.getElementById('bianjiwangluojiedian_scene_id.scene_id');
			var scene_name = document.getElementById('bianjiwangluojiedian_scene_name');
			var net_linkSts = document.getElementById('net_linkSts');
			
			var node = DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode();
			dev_no.value = node.dev_no;
			dev_name.value = node.dev_name;
			dev_serial.value = node.dev_serial;
			net_role.value = node.net_role;
			dev_powerType.value = node.dev_powerType;
			bianjiwangluojiedian_sceneSel.value = node.scene_name;
			scene_id.value = node.scene_id;
			scene_name.value = node.scene_name;
			net_linkSts.value = node.net_linkSts;
		});
		
		//确定按钮及单击事件
		DG.addBtn( 'ok', '确定', ok ); 
		function ok() 
		{			
			if(!valid2.validate())
				return;	
			var node = DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode();			
			var dev_list = DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.transformToArray(DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getNodes());
			for(var i=0;i<dev_list.length;i++){
				var dev = dev_list[i];
				if(dev.id!='root' && dev.dev_no==document.getElementById('gm_Device.dev_no').value && dev.dev_no!=node.dev_no){
					alert("WSN设备编号重复");
					return;
				}
			}
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
			var bianjiwangluojiedian_scene_id = document.getElementById('bianjiwangluojiedian_scene_id.scene_id').value;
			var net_addr = document.getElementById('dev_no').value;			
			var net_role = document.getElementById('net_role').value;
			var net_linkSts = document.getElementById('net_linkSts').value;			
			var scene_name = document.getElementById('bianjiwangluojiedian_scene_name').value;
			if (node.isParent) {
				var childNodes = DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.transformToArray(node);								
				var pnode=childNodes[0].dev_no;
		        for(i = 0; i < childNodes.length; i++) {
                   	childNodes[i].dev_no = childNodes[i].dev_no.replace(pnode,dev_no);
                    childNodes[i].name = childNodes[i].dev_no + "(场景:"+scene_name+",设备编号:"+childNodes[i].dev_no+")";
                    DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.updateNode(childNodes[i]);
         		}							
			}else{
				node.dev_no=dev_no;
				node.dev_name=dev_name;
				node.dev_serial=dev_serial;
				node.dev_model=dev_model;
				node.dev_powerType=dev_powerType;
				node.bianjiwangluojiedian_scene_id = bianjiwangluojiedian_scene_id;
				node.net_role=net_role;
				node.net_linkSts=net_linkSts;
				node.scene_name=scene_name;
				node.name = dev_no + "(场景:"+scene_name+",设备编号:"+dev_no+")";
			}			
			//DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.updateNode(DG.curWin.shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode());
			DG.curWin.updateDevCollect();
			DG.cancel(node.id);
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
						%>					
						<option value="<%=dev_powerType%>"><%=dev_powerType%></option>
						<%								
							}
						%>
	  				</select>
	  			</td>
	  			<td>
	  				所属场景:
	  				<input id="bianjiwangluojiedian_sceneSel" readonly="readonly" class="required"/> <a href="#" onclick="showMenu()">选择</a>
	  				<input type="hidden" id="bianjiwangluojiedian_scene_id.scene_id" class="required">
	  				<input type="hidden" id="bianjiwangluojiedian_scene_name" class="required">
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
  	<div id="bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto; ">
	<ul id="bianjiwangluojiedian_xuanzeshebeisuoshuchangjingdeDropdownMenu" class="tree"></ul>
</div>
	</font>
	<script>
		var valid2 = new Validation('form2');
	</script>
  </body>
</html>
