<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新采集通道应用类型信息</title>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	
	<script language="javascript" type="text/javascript">
		function isValidate() {
			var chtypeno = document.getElementById("chtype_no").value;
			var chtypedisplayName = document.getElementById("chtype_displayName").value;
			var devmodel = document.getElementById("dev_model").value;
			var chdectDig = document.getElementById("ch_dectDig").value;
			var chunit = document.getElementById("ch_unit").value;
			var chmax = document.getElementById("ch_max").value;
			var chmin = document.getElementById("ch_min").value;
			var chcrateMax = document.getElementById("ch_crateMax").value;
			var chcorrCyc = document.getElementById("ch_corrCyc").value;
			var chcorrFormula = document.getElementById("ch_corrFormula").value;
			var chclassName = document.getElementById("ch_className").value;
			var typeImg = document.getElementById("typeImg").value;
			var zhengze = /^(0|[1-9]\d*)$/;
			var double = /^(\d*\.)?\d+$/;
			if(chtypeno == ""){
				window.confirm("类型编号不能为空！");
				return false;
			}
			if(chtypedisplayName == ""){
				window.confirm("类型名称不能为空！");
				return false;
			}
			if(devmodel == ""){
				window.confirm("设备型号不能为空！");
				return false;
			}
			
			if(chdectDig == ""){
				window.confirm("小数位数不能为空！");
				return false;
			}
			
			if(!zhengze.test(chdectDig)){
				window.confirm("小数位数必须为正整数！");
				return false;
			}
			
			if(!chmax == ""){
				if(!double.test(chmax)){
					window.confirm("量程上限必须为小数！");
					return false;
				}
			}
			if(!chmin == ""){
				if(!double.test(chmax)){
					window.confirm("量程下限必须为小数！");
					return false;
				}
			}
			if(!chcrateMax == ""){
				if(!double.test(chmax)){
					window.confirm("变化率上限必须为小数！");
					return false;
				}
			}
			
			if(!chcorrCyc == ""){
				if(!zhengze.test(chcorrCyc)){
					window.confirm("校准周期必须为正整数！");
					return false;
				}
			}
			
			return true;
		}
	</script>
	
		
	
	
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
			<a href="javascript:window.location.href='${ctx }/Op_ChannelType_findAll.action'">采集通道应用类型信息管理</a> 》 
			<a href="#">编辑类型信息</a> <br>
    <form action="Op_ChannelType_update.action" method="post" onsubmit="return isValidate()" enctype="multipart/form-data">
    	<c:forEach items="${requestScope.list}" var="channeltype">
    	<table width="1020" border="0" cellpadding="0" cellspacing="0" class="senfe1">
	  		<tr class="list_head">
	  			<td colspan="2">&nbsp;</td>
	  		</tr>
    		<tr>
				<td>类型编号</td>
				<td><input type="text" name="op_ChannelType.chtype_no" id="chtype_no" value="${channeltype.chtype_no}"/></td>
			</tr>
    		<tr>
				<td>类型名称</td>
				<td>
					<input type="hidden" name="op_ChannelType.chtype_id" value="${channeltype.chtype_id}"/>
					<input type="text" name="op_ChannelType.chtype_displayName" id="chtype_displayName" value="${channeltype.chtype_displayName}"/>
				</td>
			</tr>
			<tr>
				<td>设备型号</td>
				<td><input type="text" name="op_ChannelType.dev_model" id="dev_model" value="${channeltype.dev_model}"/></td>
			</tr>
			<tr>
				<td>小数位数</td>
				<td><input type="text" name="op_ChannelType.ch_dectDig" id="ch_dectDig" value="${channeltype.ch_dectDig}"/></td>
			</tr>
			<tr>
				<td>原数据单位</td>
				<td><input type="text" name="op_ChannelType.ch_unit" id="ch_unit" value="${channeltype.ch_unit}"/></td>
			</tr>
			<tr>
				<td>量程上限</td>
				<td><input type="text" name="op_ChannelType.ch_max" id="ch_max" value="${channeltype.ch_max}"/></td>
			</tr>
			<tr>
				<td>量程下限</td>
				<td><input type="text" name="op_ChannelType.ch_min" id="ch_min" value="${channeltype.ch_min}"/></td>
			</tr>
			<tr>
				<td>变化率上限</td>
				<td><input type="text" name="op_ChannelType.ch_crateMax" id="ch_crateMax" value="${channeltype.ch_crateMax}"/></td>
			</tr>
			<tr>
				<td>校准周期</td>
				<td><input type="text" name="op_ChannelType.ch_corrCyc" id="ch_corrCyc" value="${channeltype.ch_corrCyc}"/></td>
			</tr>
			<tr>
				<td>校正公式</td>
				<td><input type="text" name="op_ChannelType.ch_corrFormula" id="ch_corrFormula" value="${channeltype.ch_corrFormula}"/></td>
			</tr>
			<tr>
				<td>校正后单位</td>
				<td><input type="text" name="op_ChannelType.ch_corrUnit" id="ch_corrUnit" value="${channeltype.ch_corrUnit}"/></td>
			</tr>
			<tr>
				<td>显示样式</td>
				<td><input type="text" name="op_ChannelType.ch_className" id="ch_className" value="${channeltype.ch_className}"/></td>
			</tr>
			<tr>
				<td>采集量显示的图片</td>
				<td id="image">
					<input type="file" name="file">
					<c:choose>
						<c:when test="${channeltype.typeImg eq null}">
							<img width="62" height="43" src="${ctx }/typeImg/d.jpg">
						</c:when>
						<c:otherwise>
							<img width="62" height="43" src="${ctx }/typeImg/${channeltype.typeImg}">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>		
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="提 交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
					<input type="reset" value="返 回" onclick="window.location.href='Op_ChannelType_findAll.action'"/>
				</td>
			</tr>
    	</table>
    	</c:forEach>
    </form>
  </body>
  
</html>
