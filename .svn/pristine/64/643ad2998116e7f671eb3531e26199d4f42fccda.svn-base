<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.unism.util.*"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>配置扩展属性</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/style_min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/rapid-validation/styles/tooltips.css" />	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/validate/jquery.validate.css" />		
	
	<script src="${ctx}/js/rapid-validation/src/prototype_for_validation.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/lib/tooltips.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/lib/effects.js" type="text/javascript"></script>
	<script src="${ctx}/js/rapid-validation/src/validation_cn.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.validate.js" ></script>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.metadata.js" ></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#fm_extform").validate({
			rules: { 
				askForAddr: { 
        			remote: {
	                    url: "${ctx}/gm_devsts_checkAskForAddr.action?oldAskForAddr=${requestScope.gm_Devsts.dest_askForAddr}&d=" + Math.random(),
	                    type: "get",
	                	data: {
	                		askForAddr: function(){
	                        	return $("#askForAddr").val();
	                        }
	                    }
	                }
    			},
    			txt_repCyc: {
    				digits: true
    			},
    			txt_collectCyc:{
    				digits: true
    			},
    			txt_storageCyc:{
    				digits: true
    			}
			},
			messages: {
				askForAddr: {remote:"此地址不存在！"}
			},
			submitHandler:function(f){
				save();
			}
		});
	});
	
	
	
		function save(){
			var txt_repCyc = document.getElementById("txt_repCyc");
			var txt_collectCyc = document.getElementById("txt_collectCyc");
			var txt_storageCyc = document.getElementById("txt_storageCyc");
			var txt_askForAddr = document.getElementById("askForAddr");
			var ck_ifClearData = document.getElementById("ck_ifClearData");
			var ck_ifReset = document.getElementById("ck_ifReset");
			var ck_ifOnlineTmp = document.getElementById("ck_ifOnlineTmp");			
			$.getJSON("${ctx}/gm_devsts_extup.action?dest_id=${requestScope.gm_Devsts.dest_id}&gm_Devsts.dest_repCyc="+txt_repCyc.value+"&gm_Devsts.dest_collectCyc="+txt_collectCyc.value+"&gm_Devsts.dest_storageCyc=" + txt_storageCyc.value + "&gm_Devsts.dest_askForAddr=" + txt_askForAddr.value+ "&gm_Devsts.dest_ifClearData=" + (ck_ifClearData.checked?1:0) + "&gm_Devsts.dest_ifReset=" + (ck_ifReset.checked?1:0) + "&gm_Devsts.dest_ifOnlineTmp=" + (ck_ifOnlineTmp.checked?1:0),{
				random:Math.random()
			},function(suc){
				if(suc)
					alert("保存成功!");
				else
					alert("保存失败!")	
			});
		}
	</script>
  </head>
  
  <body style="text-align: center;">
  <form id="fm_extform">
  	${requestScope.gm_Devsts.dev_addr }</br>
    <table cellpadding="0" cellspacing="0" width="600" class="senfe1">
		<tr>
			<td width="300" align="left">序列号</td>
			<td width="300" align="left">${requestScope.gm_Devsts.dest_serial }&nbsp;</td>
		</tr>
		<tr>
			<td width="300" align="left">软件版本</td>
			<td width="300" align="left">${requestScope.gm_Devsts.dest_softVersion }&nbsp;</td>
		</tr>
		<tr>
			<td width="300" align="left">硬件版本</td>
			<td width="300" align="left">${requestScope.gm_Devsts.dest_hardwareVersion }&nbsp;</td>
		</tr>
		<tr>
			<td width="300" align="left">供电方式</td>
			<td width="300" align="left">${requestScope.gm_Devsts.dest_powerType_str }&nbsp;</td>
		</tr>
		<tr>
			<td width="300" align="left">上报周期</td>
			<td width="300" align="left">
				<input id="txt_repCyc" value="${requestScope.gm_Devsts.dest_repCyc }"  class="digits"/>				
			</td>
		</tr>
		<tr>
			<td width="300" align="left">采集周期</td>
			<td width="300" align="left">
				<input id="txt_collectCyc" value="${requestScope.gm_Devsts.dest_collectCyc }"  class="digits"/>				
			</td>
		</tr>
		<tr>
			<td width="300" align="left">存储周期</td>
			<td width="300" align="left">
				<input id="txt_storageCyc" value="${requestScope.gm_Devsts.dest_storageCyc }"  class="digits"/>
			</td>
		</tr>
		<tr>
			<td width="300" align="left">索要地址</td>
			<td width="300" align="left">
				<input id="askForAddr" name = "askForAddr" value="${requestScope.gm_Devsts.dest_askForAddr }"/>
			</td>
		</tr>
		<tr>
			<td width="300" align="left">是否清除历史数据</td>
			<td width="300" align="left">
				<c:choose>
					<c:when test="${requestScope.gm_Devsts.dest_ifClearData == 1 }">
						<input id="ck_ifClearData" type="checkbox" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="ck_ifClearData" type="checkbox"/>
					</c:otherwise>
				</c:choose>			
			</td>
		</tr>
		<tr>
			<td width="300" align="left">是否重启</td>
			<td width="300" align="left">
				<c:choose>
					<c:when test="${requestScope.gm_Devsts.dest_ifReset == 1 }">
						<input id="ck_ifReset" type="checkbox" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="ck_ifReset" type="checkbox"/>
					</c:otherwise>
				</c:choose>				
			</td>
		</tr>
		<tr>
			<td width="300" align="left">是否临时在线</td>
			<td width="300" align="left">
				<c:choose>
					<c:when test="${requestScope.gm_Devsts.dest_ifOnlineTmp == 1 }">
						<input id="ck_ifOnlineTmp" type="checkbox" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input id="ck_ifOnlineTmp" type="checkbox"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td width="600" colspan="2" align="center">
				<input type="submit" value="保存">
			</td>
		</tr>
	</table>
	</form>	
  </body>
</html>
