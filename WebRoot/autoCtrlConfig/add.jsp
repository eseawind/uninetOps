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

<title>添加</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/css/sty.css" />
</head>

<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr height="10" style="line-height:10px;"></tr>
  <tr>
    <td valign="top">
    <form action="${ctx }/autoCtrlConfig_save.action" id="myForm">
    	<table width="98%" border="0" cellspacing="0" cellpadding="0" class="add_main row_height">
            <tr>
                <td>
                    <table border="0" cellspacing="0" cellpadding="0" class="embedded_tab">
                        <tr>
                            <td class="title">场景名称：</td>
                            <td>&nbsp;${scene.scene_name }</td>
                            <input name="autoCtrlConfigForm.scene.scene_id" type="hidden" value="${scene.scene_id }"/>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="title" style="border-bottom:none;">控制设备名称：</td>
                            <td style="border-bottom:none;">
                               <select id="dectId" name="autoCtrlConfigForm.devCtrl.dect_id">
									<c:forEach items="${devCtrls }" var="devCtrl">
										<option value="${devCtrl.dect_id }">${devCtrl.dect_name}（${devCtrl.dect_no }）</option>
									</c:forEach>
								</select>
                            </td>
                            <td class="title" style="border-bottom:none;">操作类型：</td>
                            <td style="border-bottom:none;">
                                <select name="autoCtrlConfigForm.type">
                                    <option value="1">开</option>
                                    <option value="2">停</option>
                                    <option value="3">关</option>
                                </select>
                            </td>
                            <td class="title" style="border-bottom:none;">是否开启：</td>
                            <td style="border-bottom:none;">
                                 <select name="autoCtrlConfigForm.enable">
                                    <option value="NOT">否</option>
                                    <option value="IS">是</option>
                                </select>
                            </td>
                            <td class="title" style="border-bottom:none;">条件关系：</td>
                            <td style="border-right:none; border-bottom:none;">
                                <select name="autoCtrlConfigForm.cond">
                                    <option value="AND">并且</option>
                                    <option value="OR">或者</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table border="0" cellspacing="0" cellpadding="0" class="embedded_tab">
                        <tr>
                            <td class="row_height"><input id="addCondition" type="button" value="&nbsp;添加条件&nbsp;"></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="border-bottom:none;">
                    <table id="condition" width="100%" border="0" cellspacing="0" cellpadding="0">
                    	<tbody>
                         <tr><td colspan="2" style="border-right:none;" class="row_space">&nbsp;</td></tr>
                        <tr>
                            <td style="border-right:none;">
                                <table id="conditionTab" width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td class="title">通道名称（编号） &nbsp;&nbsp;&nbsp;<input type="button" value="&nbsp;添加通道&nbsp;" onclick="addChannel(this);" />
                                        </td>
                                        <td class="title">数据处理方式</td>
                                        <td class="title">控制条件</td>
                                        <td class="title">触发值</td>
                                    </tr>
                                    <tr>
                                        <td style="border-bottom:none;" class="row_height">
                                            <table id="chTab" border="0" cellspacing="0" cellpadding="0" class="embedded_tab">
                                                <input id="chId" name="autoCtrlConfigForm.chIds" type="hidden" value = "" />
                                                <tr id="chTr">
                                                    <td>
                                                        <select id="channel">
															<c:forEach items="${channels }" var="channel">
																<option title="${channel.chtype_id.chtype_no }" value="${channel.ch_id }">${channel.ch_name}（${channel.ch_no }）</option>
															</c:forEach>
														</select>
                                                    </td>
                                                    <td class="row_height">
                                                        <input type="button" value="&nbsp;删除&nbsp;" onclick="deleteChannel(this);">
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td style="border-bottom:none;">
                                        	<select name="autoCtrlConfigForm.handles">
                                                <option value="ORA">原始值</option>
                                                <option value="AVG">平均值</option>
                                                <option value="MAX">最大值</option>
                                                <option value="MIN">最小值</option>
                                        	</select>
                                        </td>
                                        <td style="border-bottom:none;">
                                        	<select name="autoCtrlConfigForm.conditions">
                                                <option value="GREATER">大于</option>
                                                <option value="GREATOREQUAL">大于等于</option>
                                                <option value="EQUAL">等于</option>
                                                <option value="LESS">小于</option>
                                                <option value="LESSOREQUAL">小于等于</option>
                                            </select>
                                        </td>
                                        <td style="border-bottom:none;"><input id="trigger" name="autoCtrlConfigForm.trigger" type="text" /></td>
                                    </tr>				
                                </table>
                            </td>
                            <td style="border-right:none;">
                                <input type="button" value="&nbsp;删除&nbsp;" onclick="deleteCondition(this);" />
                            </td>
                        </tr>
                       <tbody>
                    </table>	
                </td>
            </tr>
             <tr><td colspan="2" style="border-right:none;" class="row_space">&nbsp;</td></tr>
            <tr>
                <td class="row_height" style="text-align:right;padding-right:10px;">
                    <input type="button" value = "&nbsp;保存&nbsp;" onclick="save();" >
                </td>
            </tr>
        </table>
        </form>
    </td>
  </tr>
  <tr height="10" style="line-height:10px;"></tr>
</table>

<div style="display: none;">
<table id="chTabData" border="0" cellspacing="0" cellpadding="0">
    <tr id="chTr">
        <td>
        	<select id="channel">
					<c:forEach items="${channels }" var="channel">
						<option title="${channel.chtype_id.chtype_no }" value="${channel.ch_id }">${channel.ch_name}（${channel.ch_no }）</option>
					</c:forEach>
			</select>
        </td>
        <td class="row_height">
            <input type="button" value="&nbsp;删除&nbsp;" onclick="deleteChannel(this);">
        </td>
    </tr>
</table>

<table id="conditionTabData" border="0" cellspacing="0" cellpadding="0">
	<tbody>
  <tr><td colspan="2" style="border-right:none;" class="row_space">&nbsp;</td></tr>
    <tr>
        <td style="border-right:none;">
            <table id="conditionTab" width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="title">通道名称（编号） &nbsp;&nbsp;&nbsp;<input type="button" value="&nbsp;添加通道&nbsp;" onclick="addChannel(this);" /></td>
                    <td class="title">数据处理方式</td>
                    <td class="title">控制条件</td>
                    <td class="title">触发值</td>
                </tr>
                <tr>
                    <td style="border-bottom:none;" class="row_height">
                        <table id="chTab" border="0" cellspacing="0" cellpadding="0" class="embedded_tab">
                            <input id="chId" name="autoCtrlConfigForm.chIds" type="hidden" value = "" />
                            <tr id="chTr">
                                <td>
                                	<select id="channel">
											<c:forEach items="${channels }" var="channel">
												<option title="${channel.chtype_id.chtype_no }"
													value="${channel.ch_id }">${channel.ch_name
													}（${channel.ch_no }）</option>
											</c:forEach>
									</select>
                                </td>
                                <td class="row_height">
                                    <input type="button" value="&nbsp;删除&nbsp;" onclick="deleteChannel(this);">
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td style="border-bottom:none;">
                        <select name="autoCtrlConfigForm.handles">
                            <option value="ORA">原始值</option>
                            <option value="AVG">平均值</option>
                            <option value="MAX">最大值</option>
                            <option value="MIN">最小值</option>
                        </select>
                    </td>
                    <td style="border-bottom:none;">
                    	<select name="autoCtrlConfigForm.conditions">
                            <option value="GREATER">大于</option>
                            <option value="GREATOREQUAL">大于等于</option>
                            <option value="EQUAL">等于</option>
                            <option value="LESS">小于</option>
                            <option value="LESSOREQUAL">小于等于</option>
                        </select>
                    </td>
                    <td style="border-bottom:none;"><input id="trigger" name="autoCtrlConfigForm.trigger" type="text" /></td>
                </tr>
            </table>
        </td>
        <td style="border-right:none;">
            <input type="button" value="&nbsp;删除&nbsp;" onclick="deleteCondition(this);" />
        </td>
    </tr>
   </tbody>
</table>	
</div>
</body>
<script type="text/javascript">
  	var is=true;
  	function addChannel(obj){
  		var chTab = $(obj).parent().parent().parent().find("#chTab");
  		var trObj = $("#chTabData").children().clone();
  		chTab.append(trObj);
  	}

  	function deleteChannel(obj){
  		$(obj).parent().parent().remove();
  	}
  	
  	function deleteCondition(obj){
  		$(obj).parent().parent().parent().remove();
  	}
  	
  	$("#addCondition").click(function(){
  		$("#condition").append($("#conditionTabData").children().clone());
  	});
  	
  	function save(){
  		var dectId = $("#dectId").val();
  		if(dectId == "" || dectId == null){
  			alert("控制设备不能为空");
  			is = false;
  			return;
  		}else{
  			is = true;
  		}
  		var channel = $("#channel").val();
  		if(channel == "" || channel == null){
  			alert("通道不能为空");
  			is = false;
  			return;
  		}else{
  			is = true;
  		}
  		if($("#condition").find("#conditionTab").length < 1){
  			alert("条件不能为空");
  			is = false;
  			return;
  		}else{
  			is = true;
  		}
  		$("#condition").find("#conditionTab").each(function(i){
  			var messageId = 0; 
  			var messageNo = 0;
  			var chIdArr = [];
  			var chtype = "";
  			var message = [];
  			$(this).find("#chTab").find("select").each(function(i){
  				var i = $(this).val();
  				var n = $(this).find("option:selected").attr("title");
  				var idR = $.inArray(i,chIdArr);
  				chIdArr.push(i);
  				
  				if(idR > -1){
  					messageId++;
  				}
  				if(chtype == ""){
  					chtype = n;
  				}else{
  					if(chtype != n){
  						messageNo++;
  					}
  				}
  			});
  			
  			var trigger = $(this).find("#trigger").val();
  			if(trigger == "" || trigger == null){
  				message.push("触发值不能为空");
  			}
  			if(isNaN(trigger)){
  				message.push("触发值只能为数字");
  			}
  			if(messageId > 0 || messageNo > 0){
  				if(messageId > 0){
  	  				message.push("通道有重复的");
  	  			}
  	  			if(messageNo > 0){
  	  				message.push("通道类型有不同类型");
  	  			}
  			}
  			if(message.length > 0){
  				var num = i+1;
  				alert("第"+num+"个条件有错误："+message.toString());
  				is = false;
  				return false;
  			}else{
  				is=true;
  			}
  			$(this).find("#chTab").find("#chId").val(chIdArr.toString());
  		});
  		
  		if(is){
  			$("#myForm").submit();
  		}
  		
  	}
  	
  	function echoSceneTree(id,name,no,rank,gtype){
  		
  	}
  </script>
</html>
