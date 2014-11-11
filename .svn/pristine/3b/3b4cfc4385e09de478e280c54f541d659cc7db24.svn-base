<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备配置信息管理 添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="js/ztree/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" href="js/ztree/demoStyle/demo.css" type="text/css">
	<link rel="stylesheet" href="css/style_shuichan.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/style_min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/tooltips.css" />		
	
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script> 
	<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgdialog.min.js"></script>
	
	<script src="${ctx}/js/rapid-validation/src/prototype_for_validation.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/lib/tooltips.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/lib/effects.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/src/validation_cn.js" type="text/javascript" charset="utf-8"></script>
	
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/ztree/jquery.ztree-2.6.js"></script>
    
	<script type="text/javascript" src="${ctx}/js/uuid.js"></script>
	<style>
			.ipt{border: 1px solid #00a8e6;height:20px}
			.ipt1{border: 1px solid #00a8e6;height:15px}
			.blueborder{border: 1px solid blue;}
			#loading {
			  position:fixed !important;
			  position:absolute;
			  top:0;
			  left:0;
			  height:100%; 
			  width:100%; 
			  z-index:999; 
			  background:#000 no-repeat center; 
			  opacity:0.6; 
			  filter:alpha(opacity=60);
			  font-size:14px;
			  line-height:20px;
			}
			#loading p{
			  color:#fff;
			  position:absolute; 
			  top:50%; 
			  left:50%; 
			  margin:50px 0 0 -50px; 
			  padding:3px 10px;
			}
	</style>
	<!-- 验证控件 -->
 	<script>    
   		//选择设备所属场景的树
   		var sceneNodes = ${scenes}
    	var shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezTree;
		var shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezmNodes = sceneNodes;
		var shebeipeizhixinxiguanli_load = true;
		var ch_uuid;
		var shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeSetting = {
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
				if (!(event.target.id == "menuBtn" || event.target.id == "shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground" || $(event.target).parents("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").length>0)) {
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
		
		function showMenu(curr_ch_uuid){
			if(curr_ch_uuid == 'sceneSel'){
				if(shebeipeizhixinxiguanli_load){			
					reloadTree();
					shebeipeizhixinxiguanli_load = false;
				}
				ch_uuid = "";
				var sceneObj = $("#sceneSel");
				var sceneOffset = $("#sceneSel").offset();
				$("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").css({left:sceneOffset.left + "px", top:sceneOffset.top + sceneObj.outerHeight() + "px"}).slideDown("fast");
			}else{
				ch_uuid = curr_ch_uuid;
				var scene_name = $("#"+ch_uuid+"_scene_name");
				var scene_nameOffset = $("#"+ch_uuid+"_scene_name").offset();
				$("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").css({left:scene_nameOffset.left + "px", top:scene_nameOffset.top + scene_name.outerHeight() + "px"}).slideDown("fast");
			}			
		}
		
		function hideMenu() {
			$("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground").fadeOut("fast");
		}
		
		function zTreeOnClick(event, treeId, treeNode) {
			if (treeNode) {
				if(ch_uuid!=""){					
					var scene_name = $("#"+ch_uuid+"_scene_name");
					var scene_id = document.getElementById(ch_uuid+"_scene_id");
					scene_name.attr("value", treeNode.name);
					scene_id.value = treeNode.id;
					hideMenu();
				}else{
					var sceneObj = $("#sceneSel");
					var sceneHid = document.getElementById("gm_Device.scene_id.scene_id");
					sceneObj.attr("value", treeNode.name);
					sceneHid.value = treeNode.id;
					hideMenu();
				}				
			}
		}
		
		function reloadTree() {
			hideMenu();
			shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezTree = $("#shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenu").zTree(shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeSetting, shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezmNodes);
			shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdezTree.expandAll(true);			
		}
		//网络节点信息树 
		var shebeipeizhixinxiguanli_wangluojiedianxinxizTree;
		
		var shebeipeizhixinxiguanli_wangluojiedianxinxiSetting = {
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			callback: {
				rightClick: zTreeOnRightClick
			}
    	};
		
		function zTreeOnRightClick(event, treeId, treeNode) { 
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				//shebeipeizhixinxiguanli_wangluojiedianxinxizTree.cancelSelectedNode();
				//showRMenu("root", event.clientX, event.clientY);;
			} else if (treeNode && !treeNode.noR) {				
				shebeipeizhixinxiguanli_wangluojiedianxinxizTree.selectNode(treeNode);				
				//如果是根节点就不要删了 Wang_Yuliang 2011-08-18
				if(treeNode.id=='root'){
					showRMenu("root", event.clientX, event.clientY);
				}else{
					showRMenu("node", event.clientX, event.clientY);
				}
				//--end
			}
		}
				
		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_edit").hide();
				$("#m_del").hide();
			}
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});
		}
		
		function hideRMenu() {
			if (rMenu) rMenu.style.visibility = "hidden";
		}
		
	    var shebeipeizhixinxiguanli_wangluojiedianxinxizNodes = [{id:'root',pid:'0',name:'root',net_depth:0,dev_id:'root',dev_no:'root'}];
	    
	    var addCount = 1;
		function addTreeNode() {			
			hideRMenu();
			if(valid.validate()){
				var tianjiawangluojiedianxinxi_dialog = new J.dialog({ 
					id:'dd3', 
					title:'添加网络节点信息',
					width:854,
					height:350,
					iconTitle:false,								
					cover:true,
					bgcolor:'#000',
					rang: true,
					page:'${ctx}/devSetup_toTianjiawangluojiedianxinxi.action?d='+Math.random()
				}); 
				tianjiawangluojiedianxinxi_dialog.ShowDialog();
				//shebeipeizhixinxiguanli_wangluojiedianxinxizTree.addNodes(shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode(), [{id:"xxx", name:"增加" + (addCount++)}]);
			}			
		}
		
		function editTreeNode() {
			hideRMenu();
			if(valid.validate()){
				var bianjiwangluojiedianxinxi_dialog = new J.dialog({ 
					id:'dd3', 
					title:'编辑网络节点信息',
					width:854,
					height:350,
					iconTitle:false,								
					cover:true,
					bgcolor:'#000',
					rang: true,
					page:'${ctx}/devSetup_toBianjiwangluojiedianxinxi.action?d='+Math.random()
				}); 
				bianjiwangluojiedianxinxi_dialog.ShowDialog();
				//shebeipeizhixinxiguanli_wangluojiedianxinxizTree.addNodes(shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode(), [{id:"xxx", name:"增加" + (addCount++)}]);
			}	
		}
		
		function removeTreeNode() {
			hideRMenu();
			var node = shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getSelectedNode();
			if (node) {
				if (node.nodes && node.nodes.length > 0) {
					var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
					if (confirm(msg)==true){
						shebeipeizhixinxiguanli_wangluojiedianxinxizTree.removeNode(node);
					}
				} else {
					shebeipeizhixinxiguanli_wangluojiedianxinxizTree.removeNode(node);
				}
			}
		}
	    $(document).ready(function(){
	    	//alert($("#shebeipeizhixinxiguanli_wangluojiedianxinxitreeDemo"));
	    	//alert(shebeipeizhixinxiguanli_wangluojiedianxinxiSetting);
	    	//alert(shebeipeizhixinxiguanli_wangluojiedianxinxizNodes.length);
	    	shebeipeizhixinxiguanli_wangluojiedianxinxizTree = $("#shebeipeizhixinxiguanli_wangluojiedianxinxitreeDemo").zTree(shebeipeizhixinxiguanli_wangluojiedianxinxiSetting, shebeipeizhixinxiguanli_wangluojiedianxinxizNodes);
	    	shebeipeizhixinxiguanli_wangluojiedianxinxizTree.expandAll(true);
		});
		
		//添加通道
		var str_op_ChannelType_list = "${requestScope.json_op_ChannelType_list}";
		var str_ch_procesStyle_list = "${requestScope.json_ch_procesStyle_list}";
		function tianjiatongdao(count){
			if(valid.validate()){
				var table = document.getElementById('table1');
				var c = table.rows.length - 1;
				var scene_name = document.getElementById('sceneSel').value;
				var scene_id = document.getElementById('gm_Device.scene_id.scene_id').value;
				var dev_no = document.getElementById('gm_Device.dev_no').value;
			
				var json_op_ChannelType_list;
				if(str_op_ChannelType_list.length>0) 
					json_op_ChannelType_list = eval("(" + str_op_ChannelType_list + ")");
				var json_ch_procesStyle_list;
				if(str_ch_procesStyle_list.length>0)
					json_ch_procesStyle_list = eval("(" + str_ch_procesStyle_list + ")");
				if(count != null && count != ""){
					for(var n=0;n<count;n++){
						var ch_uuid = new UUID();
						var tr = "<tr id=\""+ch_uuid+"_tr_channel\">";
	 					tr += "<td>";
	  						tr += "<input id=\""+ch_uuid+"_scene_name\" name=\"_scene_name\" style=\"width:140px; float: left;\" readonly=\"readonly\" value=\""+scene_name+"\" class=\"required\"> <a href=\"#\" style=\" float: left;\" onclick=\"showMenu('"+ch_uuid+"')\">选择</a>";
	  						tr += "<input type=\"hidden\" id=\""+ch_uuid+"_scene_id\" name=\"_scene_id\" value=\""+scene_id+"\" class=\"required\">";
	  					tr += "</td>";
	  					tr += "<td>";
							tr += "<input id=\""+ch_uuid+"_ch_no\" name=\"_ch_no\" style=\"width:70px;\" value=\""+dev_no+"-"+c+"\" class=\"required\">";
						tr += "</td>";
	  					tr += "<td>";
	  						tr += "<input id=\""+ch_uuid+"_ch_name\" name=\"_ch_name\" style=\"width:100px;\" class=\"required\">";
	  					tr += "</td>";
	  					tr += "<td>";
	  						tr += "<select id=\""+ch_uuid+"_dev_collectId\" style=\"width:100px;\" name=\"_dev_collectId\">";  
	  							var simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes = shebeipeizhixinxiguanli_wangluojiedianxinxizTree.transformToArray(shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getNodes());						
	  							for(var i=0;i<simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes.length;i++){
	  								var shebeipeizhixinxiguanli_wangluojiedianxinxiNode = simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes[i];
	  								tr += "<option value=\""+shebeipeizhixinxiguanli_wangluojiedianxinxiNode.id+"\">"+shebeipeizhixinxiguanli_wangluojiedianxinxiNode.name+"</option>";
	  							}
	  						tr += "</select>";
	  					tr += "</td>";
	  					tr += "<td>";
	  						tr += "<input id=\""+ch_uuid+"_ch_chlNo\" name=\"_ch_chlNo\" style=\"width:50px;\" class=\"required validate-number\">";
	  					tr += "</td>";
	  					tr += "<td>";
	  						tr += "<select id=\""+ch_uuid+"_chtype_id\" style=\"width: 90px;\" name=\"_chtype_id\">";
	  							for(var j=0;j<json_op_ChannelType_list.length;j++){
	  								var json_op_ChannelType = json_op_ChannelType_list[j];
	  								tr += "<option value=\""+json_op_ChannelType.id+"\">"+json_op_ChannelType.name+"("+json_op_ChannelType.no+")"+"</option>";
	  							}  							
	  						tr += "</select>";
	  					tr += "</td>";  						
	  					tr += "<td>";
	  						tr += "<select id=\""+ch_uuid+"_ch_offerSer\" name=\"_ch_offerSer\" style=\"width:60px;\">";
	  							tr += "<option value=1>是</option>";
	  							tr += "<option value=0>否</option>";
	  						tr += "</select>";	
	  					tr += "</td>";
	  					tr += "<td>";
							tr += "<input id=\""+ch_uuid+"_order\" name=\"_order\" style=\"width:50px;\" value=\""+c+"\" class=\"required validate-number\">";
						tr += "</td>";
	  					tr += "<td>";
	  						tr += "<select id=\""+ch_uuid+"_procesStyle\" style=\"width:90px\" name=\"_procesStyle\">";
		  						for(var k=0;k<json_ch_procesStyle_list.length;k++){
		  							var json_ch_procesStyle = json_ch_procesStyle_list[k];
		  							if(json_ch_procesStyle.id == "1")
		  								tr += "<option value=\""+json_ch_procesStyle.id+"\" selected=\"selected\">"+json_ch_procesStyle.name+"</option>";
		  							else		
		  								tr += "<option value=\""+json_ch_procesStyle.id+"\">"+json_ch_procesStyle.name+"</option>";
		  						}
	  						tr += "</select>";
	  					tr += "</td>";
	  					tr += "<td>";
	  						tr += "<input id=\""+ch_uuid+"_memoryPeriod\" name=\"_memoryPeriod\" style=\"width:50px;\" value=\"600\" class=\"required validate-number\">";
	  					tr += "</td>";
	  					tr += "<td align=\"center\">";
	  						tr += "<a onclick=\"deltongdao('"+ch_uuid+"')\" style=\"cursor: hand\">删除</a>";
	  					tr += "</td>";
			 			tr += "</tr>";
						$("#tr_maodian").before(tr);
						c = parseInt(c) + 1;
					}
				}else{alert('请填写添加通道数');}				
			}			
		}
		
		//删除通道
		function deltongdao(ch_uuid){
			var dev_no = document.getElementById('gm_Device.dev_no').value;
			$("#"+ch_uuid+"_tr_channel").remove();			
			$("#table1 tr td:nth-child(2)").each(function (i) {
                var val = $("input", this).val();
                if(val != undefined){
                	$("input", this).val(dev_no+"-"+i);
                }
            })
			$("#table1 tr td:nth-child(8)").each(function (i) {
                var val = $("input", this).val();
                if(val != undefined){
                	$("input", this).val(i);
                }
            })
		}
		
		//提交
		function doSubmit(){
			document.getElementById("gm_DevNet.net_addr").value = document.getElementById("gm_Device.dev_no").value;					
			var dev_list = shebeipeizhixinxiguanli_wangluojiedianxinxizTree.transformToArray(shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getNodes());
			var str_dev_list = "[";
			for(var i=0;i<dev_list.length;i++){
				var dev = dev_list[i];
				if(dev.id!='root' && dev.dev_no==document.getElementById('gm_Device.dev_no').value){
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
					str_dev_list += "dev_powerType:'" + dev.dev_powerType + "',";
					
					str_dev_list += "tianjiawangluojiedian_scene_id:'" + dev.tianjiawangluojiedian_scene_id + "',";
					str_dev_list += "net_addr:'" + dev.net_addr + "',";
					
					str_dev_list += "net_role:'" + dev.net_role + "',";
					str_dev_list += "net_linkSts:'" + dev.net_linkSts + "',";
					
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
			document.getElementById('dev_list').value=str_dev_list+"]";
			
			$.getJSON("${ctx }/devSetup_checkGPRS_DEV_NO.action?gm_Device.dev_no="+document.getElementById('gm_Device.dev_no').value,{
				random:Math.random()
			},function(isExist){
				if(isExist.value){
					alert(isExist.msg);
					return;
				}else{
					$.getJSON("${ctx }/devSetup_checkM2M_NET_ADDR.action?gm_DevNet.net_addr="+document.getElementById('gm_DevNet.net_addr').value,{
						random:Math.random()
					},function(isExist){
						if(isExist.value){
							alert(isExist.msg);
							return;
						}else{
							if(valid.validate()){
								var serial = document.getElementById("gm_Device.dev_serial").value;
								var strs = serial.split("-");
								if(strs.length != 4){
									alert("序列号不合法");
									return;
								}				
								document.getElementById("gm_Device.dev_model").value = strs[1];		
								var phonezhengze = /^(130|131|132|133|134|135|136|137|138|139|159|156|157|158|153|155|152|154|151|180|181|182|183|184|185|186|187|188|189)(\d){8}$/;
								var SIM = document.getElementById("gm_DevNet.net_sim").value;
								if(phonezhengze.test(SIM)==false){
									alert("SIM卡号不正确，请重新输入！");
									return;
								}	
								//document.forms[0].action = "${ctx }/devSetup_save.action";
								document.forms[0].submit();
							}
						}
					});
				}
			});
		}		
		
		//添加模板
		function addTemplate(){	
			if(valid.validate()){
				var serial = document.getElementById("gm_Device.dev_serial").value;
				var strs = serial.split("-");
				if(strs.length != 4){
					alert("序列号不合法");
					return;
				}
				document.getElementById("gm_Device.dev_model").value = strs[1];
				var phonezhengze = /^(130|131|132|133|134|135|136|137|138|139|159|156|157|158|153|155|152|154|151|180|181|182|183|184|185|186|187|188|189)(\d){8}$/;
				var SIM = document.getElementById("gm_DevNet.net_sim").value;
				if(phonezhengze.test(SIM)==false){
					alert("SIM卡号不正确，请重新输入！");
					return;
				}
				var addTemplate_dialog = new J.dialog({ 
					id:'addTemplate', 
					title:'添加模板信息',
					width:370,
					height:130,
					iconTitle:false,								
					cover:true,
					maxBtn:false,
					bgcolor:'#000',
					rang: true,
					page:'devSetup/addTemplate.jsp'
				}); 
				addTemplate_dialog.ShowDialog();
			}						
		}	

		function saveTemplate(devst_name){
			document.getElementById('gm_devSetupTemplate.devst_name').value = devst_name;
			var data = $("#form").serialize(); //序列化表单数据
			//alert(data);
			//alert(decodeURIComponent(data));
			$.getJSON("${ctx }/devSetup_saveTemplate.action",data,function(json){
				alert(json.message);
			});  
		}
		
		function updateDevCollect(){
			var option = "";
			var simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes = shebeipeizhixinxiguanli_wangluojiedianxinxizTree.transformToArray(shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getNodes());						
	  		for(var i=0;i<simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes.length;i++){
	  			var shebeipeizhixinxiguanli_wangluojiedianxinxiNode = simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes[i];
	  			option += "<option value=\""+shebeipeizhixinxiguanli_wangluojiedianxinxiNode.id+"\">"+shebeipeizhixinxiguanli_wangluojiedianxinxiNode.name+"</option>";	  								
	  			
	  		}
			$("#table1 tr td:nth-child(4)").each(function (i) {
                var val = $("select", this).val();
                if(val != undefined){
                	$("option", this).remove();
                	$("select", this).append(option);
                }
            })			
		}
		
		//加载模板
		function loadTemplate(){
			var loadTemplate_dialog = new J.dialog({ 
				id:'loadTemplate', 
				title:'加载模板信息',
				width:754,
				height:400,
				iconTitle:false,								
				cover:true,
				bgcolor:'#000',
				rang: true,
				page:'devSetup_loadTemplate.action'
			}); 
			loadTemplate_dialog.ShowDialog();			
		}	
		
		function loadSet(){
			var loadSet_dialog = new J.dialog({ 
				id:'loadSet', 
				title:'加载设置信息',
				width:450,
				height:350,
				iconTitle:false,
				cancelBtn: false, 							
				cover:true,
				maxBtn:false,
				bgcolor:'#000',
				rang: true,
				page:'devSetup_loadSet.action'
			});
			loadSet_dialog.ShowDialog();			
		}
		
		function iniTemplateOrder(dev_no,scene_id,sceneSel){
			var devst_template = document.getElementById('devst_template').value;
			$('#loading-one').empty().append('加载模板中,请稍后...').parent().show();
            var table = "<tr align=\"center\"><td width=\"18%\">所属场景</td><td width=\"8%\">通道编号</td><td width=\"8%\">通道名称</td><td width=\"10%\">采集设备</td><td width=\"6%\">通道号</td><td width=\"10%\">应用类型</td><td width=\"6%\">提供服务</td><td width=\"6%\">上报顺序</td><td width=\"10%\">数据处理方式</td><td width=\"6%\">存储周期</td><td width=\"8%\">操作</td></tr><tr id=\"tr_maodian\"><input type=\"hidden\" id=\"count\" value=\"\"/></tr>";
            $("#table1").html(table);
			var devTemlXML = new ActiveXObject("Microsoft.XMLDOM");
			devTemlXML.loadXML(devst_template);
			devTeml = devTemlXML.documentElement;
			nodes = devTeml.childNodes;
			document.getElementById('gm_Device.dev_no').value = dev_no;
			document.getElementById('gm_Device.dev_name').value = nodes[1].text;
			document.getElementById('gm_Device.dev_serial').value = nodes[2].text;
			document.getElementById('gm_Device.dev_powerType').value = nodes[4].text;
			document.getElementById('gm_Device.scene_id.scene_id').value = scene_id;
			document.getElementById('sceneSel').value = sceneSel;
			document.getElementById('gm_DevNet.net_role').value = nodes[7].text;
			document.getElementById('gm_DevNet.net_linkSts').value = nodes[8].text;
			document.getElementById('gm_DevNet.net_sim').value = nodes[9].text;	
			var nets = nodes[10];
			var wangluojiedianxinxi = "[{id:'root',pid:'0',name:'root',net_depth:0,dev_id:'root',dev_no:'root'}";
			for(var i=0;i<nets.childNodes.length;i++){				
				var net = nets.childNodes[i].childNodes; 
				var strs = net[4].text.split("-");				
				wangluojiedianxinxi += ",{";
				wangluojiedianxinxi += "dev_id:'" + net[3].text + "',"; 
				wangluojiedianxinxi += "dev_no:'" + net[4].text.replace(strs[0],dev_no) + "',";
				wangluojiedianxinxi += "dev_name:'" + net[5].text + "',";
				wangluojiedianxinxi += "dev_serial:'" + net[6].text + "',";
				wangluojiedianxinxi += "dev_model:'" + net[7].text + "',";
				wangluojiedianxinxi += "dev_powerType:'" + net[8].text + "',";
				wangluojiedianxinxi += "tianjiawangluojiedian_scene_id:'" + scene_id + "',";
				wangluojiedianxinxi += "net_addr:'" + net[11].text + "',";
				wangluojiedianxinxi += "net_role:'" + net[12].text + "',";
				wangluojiedianxinxi += "net_linkSts:'" + net[13].text + "',";
				wangluojiedianxinxi += "scene_name:'" + sceneSel + "',";
				wangluojiedianxinxi += "id:'" + net[0].text + "',";
				wangluojiedianxinxi += "pid:'" + net[1].text + "',";
				wangluojiedianxinxi += "net_depth:" + net[14].text + ",";
				wangluojiedianxinxi += "name:'" + net[4].text.replace(strs[0],dev_no)+"(场景:"+sceneSel+",设备编号:"+net[4].text.replace(strs[0],dev_no)+")" + "'";
				wangluojiedianxinxi += "}";
			}
			wangluojiedianxinxi += "]";
			var json_wangluojiedianxinxi = eval("(" + wangluojiedianxinxi + ")");
			shebeipeizhixinxiguanli_wangluojiedianxinxizTree = $("#shebeipeizhixinxiguanli_wangluojiedianxinxitreeDemo").zTree(shebeipeizhixinxiguanli_wangluojiedianxinxiSetting, json_wangluojiedianxinxi);
	    	shebeipeizhixinxiguanli_wangluojiedianxinxizTree.expandAll(true);				
			var channels = nodes[11];						
			var json_op_ChannelType_list;
			if(str_op_ChannelType_list.length>0) 
				json_op_ChannelType_list = eval("(" + str_op_ChannelType_list + ")");
			var json_ch_procesStyle_list;
			if(str_ch_procesStyle_list.length>0)
				json_ch_procesStyle_list = eval("(" + str_ch_procesStyle_list + ")"); 
				
			for(var c=0;c<channels.childNodes.length;c++){
				var channel = channels.childNodes[c].childNodes;	
				var strs = channel[2].text.split("-");			
				var ch_uuid = new UUID();
				var tr = "<tr id=\""+ch_uuid+"_tr_channel\">";
 				tr += "<td>";
  					tr += "<input id=\""+ch_uuid+"_scene_name\" name=\"_scene_name\" style=\"width:140px; float: left;\" readonly=\"readonly\" value=\""+sceneSel+"\" class=\"required\"> <a href=\"#\" style=\" float: left;\" onclick=\"showMenu('"+ch_uuid+"')\">选择</a>";
  					tr += "<input type=\"hidden\" id=\""+ch_uuid+"_scene_id\" name=\"_scene_id\" value=\""+scene_id+"\" class=\"required\">";
  				tr += "</td>";
  				tr += "<td>";
					tr += "<input id=\""+ch_uuid+"_ch_no\" name=\"_ch_no\" style=\"width:70px;\" value=\""+channel[2].text.replace(strs[0],dev_no)+"\" class=\"required\">";
				tr += "</td>";
  				tr += "<td>";
  					tr += "<input id=\""+ch_uuid+"_ch_name\" name=\"_ch_name\" style=\"width:100px;\" value=\""+channel[3].text+"\" class=\"required\">";
  				tr += "</td>";
				tr += "<td>";
	  				tr += "<select id=\""+ch_uuid+"_dev_collectId\" style=\"width:100px;\" name=\"_dev_collectId\">";  
	  					var simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes = shebeipeizhixinxiguanli_wangluojiedianxinxizTree.transformToArray(shebeipeizhixinxiguanli_wangluojiedianxinxizTree.getNodes());						
	  					for(var i=0;i<simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes.length;i++){
	  						var shebeipeizhixinxiguanli_wangluojiedianxinxiNode = simple_shebeipeizhixinxiguanli_wangluojiedianxinxizNodes[i];
	  						if(shebeipeizhixinxiguanli_wangluojiedianxinxiNode.id == channel[4].text){
	  							tr += "<option value=\""+shebeipeizhixinxiguanli_wangluojiedianxinxiNode.id+"\" selected=\"selected\">"+shebeipeizhixinxiguanli_wangluojiedianxinxiNode.name+"</option>";
	  						}else{
	  							tr += "<option value=\""+shebeipeizhixinxiguanli_wangluojiedianxinxiNode.id+"\">"+shebeipeizhixinxiguanli_wangluojiedianxinxiNode.name+"</option>";	  								
	  						}
	  					}
	  				tr += "</select>";
	  			tr += "</td>";
  				tr += "<td>";
  					tr += "<input id=\""+ch_uuid+"_ch_chlNo\" name=\"_ch_chlNo\" style=\"width:50px;\" value=\""+channel[5].text+"\" class=\"required validate-number\">";
  				tr += "</td>";
  				tr += "<td>";
  					tr += "<select id=\""+ch_uuid+"_chtype_id\" style=\"width: 90px;\" name=\"_chtype_id\">";
  						for(var j=0;j<json_op_ChannelType_list.length;j++){
  							var json_op_ChannelType = json_op_ChannelType_list[j];
  							if(json_op_ChannelType.id == channel[6].text){
  								tr += "<option value=\""+json_op_ChannelType.id+"\" selected=\"selected\">"+json_op_ChannelType.name+"("+json_op_ChannelType.no+")"+"</option>";  									
  							}else{
  								tr += "<option value=\""+json_op_ChannelType.id+"\">"+json_op_ChannelType.name+"("+json_op_ChannelType.no+")"+"</option>";  								
  							}
  						}  							
  					tr += "</select>";
  				tr += "</td>";
  				tr += "<td>";
  					tr += "<select id=\""+ch_uuid+"_ch_offerSer\" name=\"_ch_offerSer\" style=\"width:60px;\">";
  						if(channel[7].text == 1){					
	  						tr += "<option value=1 selected=\"selected\">是</option>";
	  						tr += "<option value=0>否</option>";
  						}else if(channel[7].text == 0){
  							tr += "<option value=1>是</option>";
	  						tr += "<option value=0 selected=\"selected\">否</option>";
  						}
  					tr += "</select>";	
  				tr += "</td>";
  				tr += "<td>";
					tr += "<input id=\""+ch_uuid+"_order\" name=\"_order\" style=\"width:50px;\" value=\""+channel[8].text+"\" class=\"required validate-number\">";
				tr += "</td>";
  				tr += "<td>";
  					tr += "<select id=\""+ch_uuid+"_procesStyle\" style=\"width:90px\" name=\"_procesStyle\">";
	  					for(var k=0;k<json_ch_procesStyle_list.length;k++){
	  						var json_ch_procesStyle = json_ch_procesStyle_list[k];
	  						if(json_ch_procesStyle.id == channel[9].text){
  								tr += "<option value=\""+json_ch_procesStyle.id+"\" selected=\"selected\">"+json_ch_procesStyle.name+"</option>";  									
  							}else{
  								tr += "<option value=\""+json_ch_procesStyle.id+"\">"+json_ch_procesStyle.name+"</option>";								
  							}	  							
	  					}
  					tr += "</select>";
  				tr += "</td>";
  				tr += "<td>";
  					tr += "<input id=\""+ch_uuid+"_memoryPeriod\" name=\"_memoryPeriod\" style=\"width:50px;\" value=\""+channel[10].text+"\" class=\"required validate-number\">";
  				tr += "</td>";
  				tr += "<td align=\"center\">";
	  				tr += "<a onclick=\"deltongdao('"+ch_uuid+"')\" style=\"cursor: hand\">删除</a>";
  				tr += "</td>";
 				tr += "</tr>"; 	
 				$("#tr_maodian").before(tr);
			}
			$('#loading-one').empty().append('模板加载完毕.').parent().fadeOut('slow');		
		}
    </script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;text-align: left;">
			<form id="form" name="form" action="${ctx }/devSetup_save.action" method="post">
			<a href="javascript:window.location.href='${ctx }/devSetup_list.action'">首页</a> 》 
	<a href="javascript:window.location.href='${ctx }/devSetup_list.action'">设备配置信息管理</a> 》 添加 <br>
    <table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
  		<tr class="list_head">
  			<!-- 
  			<td colspan="3"><b>设备信息</b></td>
  			-->
  			<td align="left" style="border-right: none;"><input id="btnConfirm" type="button" value="提交" onclick="doSubmit()"></td>
  			<td align="center" style="border-right: none;"><b>设备信息</b></td>
  			<td align="right">			  				
			  	<input type="button" value="存为模板" onclick="addTemplate()">
			  	<input type="button" value="加载模板" onclick="loadTemplate()">
  			</td>
  			 
  		</tr>
  		<tr>
  			<td>
  				设备编号:
  				<input id="gm_Device.dev_no" name="gm_Device.dev_no" class="required validate-number"/>
  				<input id="gm_DevNet.net_addr" name="gm_DevNet.net_addr" type="hidden"/>
  				<input id="gm_Device.dev_btype" name="gm_Device.dev_btype" value="0" type="hidden"/>
  				<input id="gm_Device.dev_state" name="gm_Device.dev_state" value="1" type="hidden"/>
  				<input id="gm_DevNet.net_depth" name="gm_DevNet.net_depth" value="0" type="hidden"/>
  				<input id="gm_DevNet.net_state" name="gm_DevNet.net_state" value="1" type="hidden"/>
  				<input id="gm_DevNet.net_type" name="gm_DevNet.net_type" value="0" type="hidden"/>
  				<input id="gm_Device.dev_model" name="gm_Device.dev_model" type="hidden"/>
  				<input id="devst_template" name="devst_template" type="hidden"/>
  				<input id="gm_devSetupTemplate.devst_name" name="gm_devSetupTemplate.devst_name" type="hidden"/>
  			</td>
  			<td>
  				设备名称:
  				<input id="gm_Device.dev_name" name="gm_Device.dev_name" class="required"/>
  			</td>
  			<td>
  				设备序列号:
  				<input id="gm_Device.dev_serial" name="gm_Device.dev_serial" class="required"/>
  			</td>
  		</tr>
  		<tr>
  			<td>
  				所属场景:
  				<input id="sceneSel" name="gm_Device.scene_id.scene_name" readonly="readonly" class="required"/> <a href="#" onclick="showMenu('sceneSel')">选择</a>
  				<input type="hidden" id="gm_Device.scene_id.scene_id" name="gm_Device.scene_id.scene_id" class="required">
  			</td>  			
  			<td>
  				网内角色:
				<select id="gm_DevNet.net_role" name="gm_DevNet.net_role" class="required">	
					<%
						List<Net_role> net_roles = StaticDataManage.getNet_roles();
						Iterator<Net_role> net_rolesIterator = net_roles.iterator();
					%>
					<%
						while (net_rolesIterator.hasNext()) {
							Net_role net_role = net_rolesIterator.next();
							String id = net_role.getId();
							if("0".equals(id.substring(0,1))){
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
				<select id="gm_Device.dev_powerType" style="width:156px;" name="gm_Device.dev_powerType" class="required">
   					<option value="">-- 请选择 --</option>
					<%
						List<String> dev_powerTypes = StaticDataManage.getDev_powerTypes();
						Iterator<String> dev_powerTypesIterator = dev_powerTypes.iterator();
					%>
					<%
						while (dev_powerTypesIterator.hasNext()) {
							String dev_powerType = dev_powerTypesIterator.next();
							if("220VAC".equals(dev_powerType)){
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
  		</tr>
  		<tr>
  			<td>
  				&nbsp;SIM卡号:
  				<input id="gm_DevNet.net_sim" name="gm_DevNet.net_sim" class="required validate-number"/> 
  			</td>
  			<td>
  				连接方式:
				<select id="gm_DevNet.net_linkSts" name="gm_DevNet.net_linkSts" class="required">
					<%
						List<Net_linkSts> net_linkStss = StaticDataManage.getNet_linkStss();
						Iterator<Net_linkSts> net_linkStssIterator = net_linkStss.iterator();
					%>
					<%
						while (net_linkStssIterator.hasNext()) {
							Net_linkSts net_linkSts = net_linkStssIterator.next();
					%>
					<option value="<%=net_linkSts.getId()%>"><%=net_linkSts.getName()%></option>
					<%
						}
					%>
				</select>
  			</td>
  			<td>&nbsp;</td>
  		</tr>
  		<tr class="list_head">
  			<td colspan="3"><b>网络节点信息</b></td>
  		</tr>
  		<tr>
  			<td colspan="3">
  				<ul id="shebeipeizhixinxiguanli_wangluojiedianxinxitreeDemo" class="tree"></ul>
  				<input id="dev_list" name="dev_list" type="hidden">
  			</td>
  		</tr>
  		<tr class="list_head">
  			<td colspan="3"><b>通道信息</b></td>
  		</tr>
  		<tr>
  			<td colspan="3">
  				添加通道数：<input id="addcount" type="text" style="width: 80px;"/> <input type="button" value="添加通道" onclick="tianjiatongdao(document.getElementById('addcount').value)">
  				<table cellpadding="0" cellspacing="0" width="100%" class="senfe1" id="table1">
  					<tr align="center">  	
  						<td width="18%">所属场景</td>					
	  					<td width="8%">通道编号</td>
	  					<td width="8%">通道名称</td>
	  					<td width="10%">采集设备</td>
						<td width="6%">通道号</td>
						<td width="10%">应用类型</td>						
						<td width="6%">提供服务</td>
						<td width="6%">上报顺序</td>
						<td width="10%">数据处理方式</td>
						<td width="6%">存储周期</td>
						<td width="8%">操作</td>
  					</tr>
 					<tr id="tr_maodian"><input type="hidden" id="count" value=""/></tr>
  				</table> 				
  			</td>
  		</tr>
  	</table>
  	<table width="100%" cellpadding="0" cellspacing="0"><tr><td align="left"><input id="btnConfirm" type="button" value="提交" onclick="doSubmit()"></td><td align="right"><input type="button" value="存为模板" onclick="addTemplate()"><input type="button" value="加载模板" onclick="loadTemplate()"></td></tr></table>
  	<div id="shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto; ">
	<ul id="shebeipeizhixinxiguanli_xuanzeshebeisuoshuchangjingdeDropdownMenu" class="tree"></ul>
</div>
<div id="rMenu" style="position:absolute; visibility:hidden;">
<li>
<ul id="m_add" onclick="addTreeNode();"><li>增加节点</li></ul>
<ul id="m_edit" onclick="editTreeNode();"><li>编辑节点</li></ul>
<ul id="m_del" onclick="removeTreeNode();"><li>删除节点</li></ul>
</li>
</div>
</form>
	<div id="loading" style="display:none">
		<p id="loading-one"></p>
	</div>
	<script>
		var valid = new Validation('form');
	</script>
  </body>
</html>
