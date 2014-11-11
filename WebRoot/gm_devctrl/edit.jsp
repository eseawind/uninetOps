<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>更新控制设备信息</title>
    <link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="js/validate/jquery.validate.css"/>
    <link rel="stylesheet" href="zTree/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="css/autocomplete/jquery.autocomplete.css" type="text/css">
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="js/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx }/js/date.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.ztree-2.6.js"></script>
	<script type="text/javascript" src="${ctx }/js/demoTools.js"></script>
	<script type="text/javascript" src="js/jquery.ajaxQueue.js"></script>
	<script type="text/javascript" src="js/jquery.bgiframe.min.js"></script>	
	<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#addForm").validate({
			rules: { 
				"gm_DevCtrl.dect_no": { 
        			required: true, 
        			remote: {
	                	data: {
							code: function(){
	                        	return $('#code').val();
	                        },
	                        oldCode: function(){
	                        	return  '${gm_DevCtrl.dect_no}';
	                        }
	                    },
	                    url: "${ctx}/Gm_DevCtrl_check.action?d=" + Math.random(),
	                    type: "get"
	                }
    			}
			},
			messages: {
				"gm_DevCtrl.dect_no": {remote:"此编号已存在！"}
			},
			submitHandler : function(f){
				var ifSubmit = true;
				var trSize = $("#btnTable tr").size() - 2;//默认有2个,1个是列表头,1个是隐藏tr,不需计算在内
				if(trSize < 2) {
					alert("设备按钮不能小于2个!");
					ifSubmit = false;
				}
				if(trSize > 3) {
					alert("设备按钮不能超过3个!");
					ifSubmit = false;
				}
				var numReg =/^\d+$/;
				var open = 0;
				var stop = 0;
				var close = 0;
				$("#btnTable tr").each(function(i){
					var trId = $(this).attr("id");
					if("btnTableTr" == trId) {
						var btnNameInput = $(this).children().find("input").eq(0);
						if("" == btnNameInput.val()) {
							alert("设备按钮第" + i + "行  '按钮名称' 不能为空!");
							btnNameInput.focus();
							ifSubmit = false;
							return false;
						}
						
						var btnTypeInput = $(this).children().find("select").eq(0);
						var btnType = btnTypeInput.val();
						if(btnType == 1) {
							open++;
						}
						if(btnType == 2) {
							stop++;
						}
						if(btnType == 3) {
							close++;
						}

						var btnChlNoInput = $(this).children().find("input").eq(1);
						var btnChlNo = btnChlNoInput.val()
						if("" == btnChlNo) {
							alert("设备按钮第" + i + "行  '控制通道' 不能为空!");
							btnChlNoInput.focus();
							ifSubmit = false;
							return false;
						} else {
							if(!numReg.test(btnChlNo)) {
								alert("设备按钮第" + i + "行  '控制通道' 只能为整数!");
								btnChlNoInput.focus();
								ifSubmit = false;
								return false;
							}
						}
						
						var btnDelayInput = $(this).children().find("input").eq(2);
						var btnDelay = btnDelayInput.val()
						if("" == btnDelay) {
							alert("设备按钮第" + i + "行  '操作延时' 不能为空!");
							btnDelayInput.focus();
							ifSubmit = false;
							return false;
						} else {
							if(!numReg.test(btnDelay)) {
								alert("设备按钮第" + i + "行  '操作延时' 只能为整数!");
								btnDelayInput.focus();
								ifSubmit = false;
								return false;
							}
						}
					}
					
				}); 

				var stateOpen = 0;
				var stateClose = 0;
				var stateStop = 0;
				var stateRun = 0;

				$("#configTable tr").each(function(i){
					var trId = $(this).attr("id");
					if("devCtrlCongfigTr" == trId) {
						var chMax = $(this).children().find("input").eq(2);
						var chMin = $(this).children().find("input").eq(3);
						if(chMax != null && chMax.val() != ""){
								if(isNaN(chMax.val())){
									alert("控制设备状态配置范围上限应为数字……");
									ifSubmit = false
									return false;
								}
						}
						if(chMin != null && chMin.val() != ""){
							if(isNaN(chMin.val())){
								alert("控制设备状态配置范围下限应为数字……");
								ifSubmit = false
								return false;
							}
						}
						var channel = $(this).children().find("input").eq(1);
						var state = $(this).children().find("input").eq(0);
						var channelId = findCh_idByCh_no(channel.val());
						var stateName = state.val();
						if(channelId==null){
							alert("控制设备状态配置状态通道不合法");
							ifSubmit = false
							return false;
						}else{
							$(this).children().find("input").eq(4).val(channelId);
						}
						if("" == stateName){
							alert("控制设备状态配置状态名称不能为空……");
							ifSubmit = false
							return false
						}

						var stateType = $(this).children().find("select").eq(0);
						var state = stateType.val();
						if(state == 1) {
							stateOpen++;
						}
						if(state == 2) {
							stateStop++;
						}
						if(state == 3) {
							stateClose++;
						}
						if(state == 4) {
							stateRun++;
						}
					}
				});

				if(stateOpen > 1){
					alert("此控制设备状态配置有重复的操作类型 - ' 开 '!");
					ifSubmit = false;
					return false;
				}
				if(stateStop > 1){
					alert("此控制设备状态配置有重复的操作类型 - ' 停 '!");
					ifSubmit = false;
					return false;
				}
				if(stateClose > 1){
					alert("此控制设备状态配置有重复的操作类型 - ' 关 '!");
					ifSubmit = false;
					return false;
				}
				if(stateRun > 1){
					alert("此控制设备状态配置有重复的操作类型 - ' 运行 '!");
					ifSubmit = false;
					return false;
				}
				
				if(open > 1) {
					alert("此控制设备按钮有重复的操作类型 - ' 开 '!");
					ifSubmit = false;
					return false;
				}
				if(stop > 1) {
					alert("此控制设备按钮有重复的操作类型 - ' 停 '!");
					ifSubmit = false;
					return false;
				}
				if(close > 1) {
					alert("此控制设备按钮有重复的操作类型 - ' 反向 '!");
					ifSubmit = false;
					return false;
				}

				if(ifSubmit) {
					f.submit();
				}
			}
		});
	});	

	function addBtn() {
		var trSize = $("#btnTable tr").size() - 2;//默认有2个,1个是列表头,1个是隐藏tr,不需计算在内
		if(trSize >= 3) {
			alert("设备按钮不能超过3个!");
			return;
		}
		$("#TR_List").before("<tr align=\"center\" id=\"btnTableTr\"><td><input type=\"text\" name=\"devCtrlBtnForm.btnName\"/></td>" + 
				"<td><select name=\"devCtrlBtnForm.btnType\"><option value=\"1\" selected=\"selected\">开</option><option value=\"2\">停</option><option value=\"3\">反向</option></select></td>" +
				"<td><input type=\"text\" name=\"devCtrlBtnForm.btnChl\"/></td>" + 
				"<td><select name=\"devCtrlBtnForm.btnCtrlType\"><option value=\"1\">正向脉冲</option><option value=\"2\">反向脉冲</option>" +
				"<option value=\"3\">高电平</option><option value=\"3\">低电平</option></select></td>" +
				"<td><input type=\"text\" name=\"btnDelay\"/></td>" +
				"<td><a href=\"#\" onclick=\"delBtn(this);return false;\">删除</a></td></tr>");
	}


	var selectId = 0;
	function addConfig(){
		var trSize = $("#configTable tr").size()-2;
		if(trSize >=4){
			alert("状态配置不能超过4个……");
			return;
		}
		var id = document.getElementById("devId").value;
		var flag =  1;
		outPutHTML(id,flag);
	}
	
	var channels = [];
	
	function findCh_idByCh_no(ch_no){
		for(var i=0;i<channels.length;i++){
			var channel = channels[i];
			if(ch_no==channel.ch_no){
				return channel.ch_id;
			}
		}
		return null;
	}

	function outPutHTML(){
		$("#configList").before("<tr align=\"center\" id=\"devCtrlCongfigTr\"><td><input name=\"devCtrlStsForm.dectStsName\" /></td>"+
				"<td><select name=\"devCtrlStsForm.dectState\"><option value=\"1\">开</option><option value=\"2\">停</option><option value=\"3\">关</option><option value=\"4\">运行</option></select></td>"+
				"<td align=\"center\"><input id=\"channel"+selectId+"\"/></td>"+
				"<td><input id=\"chMax\" name=\"devCtrlStsForm.chMax\" /></td>"+
				"<td><input id=\"chMin\" name=\"devCtrlStsForm.chMin\" /><input name=\"devCtrlStsForm.chId\" type=\"hidden\"></td>"+
				"<td><a href=\"#\" onclick=\"delBtn(this);return false;\">删除</a></td></tr>");		
				
		$("#channel"+selectId).autocomplete(channels, {
			minChars: 0,
			width: 158,
			matchContains: "word",
			autoFill: false,
			scroll: true,
			scrollHeight: 220,
			formatItem: function(row, i, max) {
				return row.ch_no;
			}
		});
		
		selectId++;
	}

	/**
	function channelNo(id,flag){
		$.ajax({
			   url: "${ctx}/Gm_DevChannel_findChannel.action", 
		       type: "POST",  
		       dataType: "json",//xml,html,script,json
		       data:{devId:id},
		       error: function(){},  
		       success: function(json){
			    var html = ""; 
		       	jQuery.each(json, function(i, channel){
			       	var chId = channel.chId;
			       	var chNo = channel.chNo;
			       	html += "<option value=\""+chId+"\">"+chNo+"</option>";
		       	})
		       	channel = html;
		       	if(flag == 1){
		       		outPutHTML();
			    }else{
			    	findSelect();
				}
     	   	   }  
			}); 
	}
	*/

	function findSelect(ele){
		channels = [];
		$.ajax({
			url:"${ctx}/Gm_DevCtrl_json_jiazaizhuangtaitongdaobianhao.action?q=&random="+Math.random()+"&gm_DevCtrl.dev_id.dev_id="+(ele.value.length==0?'-1':ele.value),
			type: "POST",  
		    dataType: "json",//xml,html,script,json
		    success: function(json){
				channels=json;
				$("#configTable tr").each(function(i){
					var trId = $(this).attr("id");
					if("devCtrlCongfigTr" == trId) {
						var input = $(this).children().find("input").eq(1);
						if(input != null){
							input.val("");
						}
						input.autocomplete(channels, {
							minChars: 0,
							width: 158,
							matchContains: "word",
							scroll: true,
							scrollHeight: 220,
							autoFill: false,
							formatItem: function(row, i, max) {
								return row.ch_no;
							}
						});
					}
				});
     		}  
		});
	}
	
	function delBtn(compent) {
		$(compent).parent().parent().remove();
	}
	
		//下拉树
		var zTree_save_0715;
		var scenes_save_0715 = ${scenes};
		var setting_save_0715 = {
			isSimpleData: true,
			treeNodeKey: "id",
			treeNodeParentKey: "pid",
			fontCss: setFont,
			callback: {
				click: zTreeOnClick
			}			
		};		
		$(document).ready(function(){
			reloadTree();	
			$("body").bind("mousedown",function(event){
				if (!(event.target.id == "menuBtn" || event.target.id == "DropdownMenuBackground" || $(event.target).parents("#DropdownMenuBackground").length>0)) {
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
			var sceneObj = $("#sceneSel");
			var sceneOffset = $("#sceneSel").offset();
			$("#DropdownMenuBackground").css({left:sceneOffset.left + "px", top:sceneOffset.top + sceneObj.outerHeight() + "px"}).slideDown("fast");
			
		}		
		function hideMenu() {
			$("#DropdownMenuBackground").fadeOut("fast");
		}		
		function zTreeOnClick(event, treeId, treeNode) {
			if (treeNode) {
				var sceneObj = $("#sceneSel");
				var sceneHid = document.getElementById("gm_DevCtrl.scene_id.scene_id");
				sceneObj.attr("value", treeNode.name);
				sceneHid.value = treeNode.id;
				hideMenu();
			}
		}		
		function reloadTree() {
			hideMenu();
			zTree_save_0715 = $("#dropdownMenu").zTree(setting_save_0715, scenes_save_0715);
			zTree_save_0715.expandAll(true);			
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
			<a href="javascript:window.location.href='${ctx }/Gm_DevCtrl_findAll.action'">控制设备信息信息管理</a> 》
			<a href="#"/>编辑控制设备信息</a> <br>
    <form action="Gm_DevCtrl_update.action" method="post" id="addForm" name="addForm">
    	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
			<tr class="list_head">
				<td colspan="2">&nbsp;</td>
			</tr>
    		<tr>
				<td><font color="red">*</font>控制设备编号</td>
				<td>
					<input type="hidden" name="gm_DevCtrl.dect_id" value="${gm_DevCtrl.dect_id}"/>
					<input type="text" name="gm_DevCtrl.dect_no" id="code" value="${gm_DevCtrl.dect_no}" class="required"/>
				</td>
			</tr>
    		<tr>
				<td><font color="red">*</font>设备名称</td>
				<td><input type="text" name="gm_DevCtrl.dect_name" value="${gm_DevCtrl.dect_name}" class="required"/></td>
			</tr>
			<tr>
				<td><font color="red">*</font>设备序列号</td>
				<td><input type="text" name="gm_DevCtrl.dect_serial" value="${gm_DevCtrl.dect_serial}" class="required"/></td>
			</tr>
			<tr>
				<td><font color="red">*</font>类型编号</td>
				<td>
					<select name="gm_DevCtrl.decttype_id.decttype_id" class="required">					
					<c:forEach items="${devctrtype}" var="devctrtype">						
						<c:choose>
							<c:when test="${devctrtype.decttype_id == gm_DevCtrl.decttype_id.decttype_id}">
								<option value="${devctrtype.decttype_id}" selected="selected">${devctrtype.decttype_displayName}</option>
							</c:when>
							<c:otherwise>
								<option value="${devctrtype.decttype_id}">${devctrtype.decttype_displayName}</option>
							</c:otherwise>
						</c:choose>					
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><font color="red">*</font>所属设备编号</td>
				<td>
					<select id="devId" name="gm_DevCtrl.dev_id.dev_id" class="required" onchange="findSelect(this)">
					<option value="">请选择……</option>					
					<c:forEach items="${deviceList}" var="device">						
						<c:choose>
							<c:when test="${device.dev_id == gm_DevCtrl.dev_id.dev_id}">
								<option value="${device.dev_id}" selected="selected">${device.dev_no}</option>
							</c:when>
							<c:otherwise>
								<option value="${device.dev_id}">${device.dev_no}</option>
							</c:otherwise>
						</c:choose>					
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>购买时间</td>
				<td><input type="text" name="gm_DevCtrl.dect_buyDate" value="<fmt:formatDate value="${gm_DevCtrl.dect_buyDate}" pattern="yyyy-MM-dd"/>" onfocus="setday(this,this)" readonly="readonly"/></td>
			</tr>	
			<tr>
				<td>服务到期时间</td>
				<td><input type="text" name="gm_DevCtrl.dect_overDate" value="<fmt:formatDate value="${gm_DevCtrl.dect_overDate}" pattern="yyyy-MM-dd"/>" onfocus="setday(this,this)" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>生效时间</td>
				<td><input type="text" name="gm_DevCtrl.dect_effectTime" value="<fmt:formatDate value="${gm_DevCtrl.dect_effectTime}" pattern="yyyy-MM-dd"/>" onfocus="setday(this,this)" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>场景名称</td>
				<td>
					<input type="text" disabled="disabled" id="sceneSel" readonly="readonly" value="${gm_DevCtrl.scene_id.scene_name}"> <a href="javascript:showMenu()">选择</a> 必填
					<input type="hidden" id="gm_DevCtrl.scene_id.scene_id" name="gm_DevCtrl.scene_id.scene_id" value="${gm_DevCtrl.scene_id.scene_id}"/>
				</td>
			</tr>
			<tr>
				<td>是否对外提供服务</td>
				<td>
					<c:if test="${gm_DevCtrl.ch_offerSer == 1}">
						<input type="radio" name="gm_DevCtrl.ch_offerSer" value="1" checked="checked"/>是
						<input type="radio" name="gm_DevCtrl.ch_offerSer" value="0"/>否
					</c:if>		
					<c:if test="${gm_DevCtrl.ch_offerSer == 0}">
						<input type="radio" name="gm_DevCtrl.ch_offerSer" value="1"/>是
						<input type="radio" name="gm_DevCtrl.ch_offerSer" value="0" checked="checked"/>否
					</c:if>
				</td>
			</tr>
			<tr>
				<td>设备说明</td>
				<td><input type="text" name="gm_DevCtrl.dect_decsription" value="${gm_DevCtrl.dect_decsription}"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">控制设备按钮信息</div>
					<div align="right"><input type="button" value="添加按钮" onclick="addBtn()"/></div>
					<table cellpadding="0" cellspacing="0" width="100%" class="senfe1" id="btnTable">
						<tr class="list_head" id="btnTableHead">
							<td>按钮名称</td>
							<td>操作类型</td>
							<td>控制通道</td>
							<td>控制类型</td>
							<td>操作延时</td>
							<td>操作</td>
						</tr>
						<s:iterator value="#request.devCtrlBtnList" var="devCtrlBtn">
						<tr id="btnTableTr" align="center">
							<td><input type="text" name="devCtrlBtnForm.btnName" value="${devCtrlBtn.dectbtn_name}"/></td>
							<td>
    						<s:select name="devCtrlBtnForm.btnType" theme="simple" list="#{1:'开', 2:'停', 3:'反向'}" value="#devCtrlBtn.deco_type"/>
							</td>
							<td><input type="text" name="devCtrlBtnForm.btnChl" value="${devCtrlBtn.dect_chlNo}"/></td>
							<td>
							<s:select name="devCtrlBtnForm.btnCtrlType" theme="simple" list="#{1:'正向脉冲', 2:'反向脉冲', 3:'高电平', 4:'低电平'}" value="#devCtrlBtn.dect_ctlType" />
							</td>
							<td><input type="text" name="devCtrlBtnForm.btnDelay" value="${devCtrlBtn.dect_ctlDelay}"/></td>
							<td><a href="#" onclick="delBtn(this);return false;">删除</a></td>
						</tr>
						</s:iterator>
						<tr id="TR_List"></tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">控制设备状态配置信息</div>
					<div align="right"><input type="button" value="添加配置" onclick="addConfig()"/></div>
					<table cellpadding="0" cellspacing="0" width="100%" class="senfe1" id="configTable">
						<tr class="list_head">
							<td>状态名称</td>
							<td>状态类型</td>
							<td>状态通道编号</td>
							<td>范围上限</td>
							<td>范围下限</td>
							<td>操作</td>
						</tr>
						<s:iterator value="#request.devCtrlStsList" var="devCtrlSts" status="n">
							<tr id="devCtrlCongfigTr" align="center">
								<td><input name="devCtrlStsForm.dectStsName" value="${devCtrlSts.dectSts_name }" /></td>
							<td>
								<s:select name="devCtrlStsForm.dectState" theme="simple" list="#{1:'开',2:'停',3:'关',4:'运行'}" value="#devCtrlSts.dect_state"></s:select>
							</td>
							<td align="center">
								<input id="channel${n.count-1 }" value="${devCtrlSts.ch_id.ch_no }"/>
							</td>
							<td><input id="chMax" name="devCtrlStsForm.chMax" value="${devCtrlSts.ch_max }"/></td>
							<td>
								<input id="chMin" name="devCtrlStsForm.chMin" value="${devCtrlSts.ch_min }"/>
								<input name="devCtrlStsForm.chId" type="hidden">
							</td>
							<td><a href="#" onclick="delBtn(this);return false;">删除</a></td>
							</tr>
						</s:iterator>
						<tr id="configList"></tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input id="sub" type="submit" value="提 交" disabled="disabled"/>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
					<input type="reset" value="返 回" onclick="history.go(-1);"/>
				</td>
			</tr>
    	</table>    	
    </form>
            <div id="DropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto; ">
	<ul id="dropdownMenu" class="tree"></ul>
</div>
<script type="text/javascript">
var devId = document.getElementById('devId');
$.ajax({
	url:"${ctx}/Gm_DevCtrl_json_jiazaizhuangtaitongdaobianhao.action?q=&random="+Math.random()+"&gm_DevCtrl.dev_id.dev_id="+(devId.value.length==0?'-1':devId.value),
	type: "POST",  
    dataType: "json",//xml,html,script,json
    success: function(json){
		channels=json;
		document.getElementById('sub').disabled=false;
		$("#configTable tr").each(function(i){
			var trId = $(this).attr("id");
			if("devCtrlCongfigTr" == trId) {
				var input = $(this).children().find("input").eq(1);				
				input.autocomplete(channels, {
					minChars: 0,
					width: 158,
					matchContains: "word",
					autoFill: false,
					scroll: true,
					scrollHeight: 220,
					formatItem: function(row, i, max) {
						return row.ch_no;
					}
				});
				selectId++;
			}
		});	
   	}  
});
</script>
  </body>
</html>
