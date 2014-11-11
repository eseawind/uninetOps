<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加采集通道应用类型信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
			<a href="#">添加类型信息</a> <br>
    <form action="Op_ChannelType_save.action?post=1" method="post" onsubmit="return isValidate()" enctype="multipart/form-data">
	  	<table width="1020" border="0" cellpadding="0" cellspacing="0" class="senfe1">
	  		<tr class="list_head">
	  			<td colspan="2">&nbsp;</td>
	  		</tr>
    		<tr>
				<td>类型编号</td>
				<td><input type="text" name="op_ChannelType.chtype_no" id="chtype_no"/></td>
			</tr>
			<tr>
				<td>类型名称</td>
				<td><input type="text" name="op_ChannelType.chtype_displayName" id="chtype_displayName"/></td>
			</tr>
			<tr>
				<td>设备型号</td>
				<td><input type="text" name="op_ChannelType.dev_model" id="dev_model"/></td>
			</tr>
			<tr>
				<td>小数位数</td>
				<td><input type="text" name="op_ChannelType.ch_dectDig" id="ch_dectDig"/></td>
			</tr>
			<tr>
				<td>原数据单位</td>
				<td><input type="text" name="op_ChannelType.ch_unit" id="ch_unit"/></td>
			</tr>
			<tr>
				<td>量程上限</td>
				<td><input type="text" name="op_ChannelType.ch_max" id="ch_max"/></td>
			</tr>
			<tr>
				<td>量程下限</td>
				<td><input type="text" name="op_ChannelType.ch_min" id="ch_min"/></td>
			</tr>
			<tr>
				<td>变化率上限</td>
				<td><input type="text" name="op_ChannelType.ch_crateMax" id="ch_crateMax"/></td>
			</tr>
			<tr>
				<td>校准周期</td>
				<td><input type="text" name="op_ChannelType.ch_corrCyc" id="ch_corrCyc"/></td>
			</tr>
			<tr>
				<td>校正公式</td>
				<td><input type="text" name="op_ChannelType.ch_corrFormula" id="ch_corrFormula"/></td>
			</tr>
			<tr>
				<td>校正后单位</td>
				<td><input type="text" name="op_ChannelType.ch_corrUnit" id="ch_corrUnit"/></td>
			</tr>
			<tr>
				<td>显示样式</td>
				<td><input type="text" name="op_ChannelType.ch_className" id="ch_className"/></td>
			</tr>
			<tr>
				<td>采集量显示的图片</td>
				<td><input type="file" name="file" id="typeImg"/></td>
			</tr>				
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="提 交"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
					<input type="reset" value="返 回" onclick="window.location.href='Op_ChannelType_findAll.action'"/>
				</td>
			</tr>
    	</table>
    </form>
  </body>
</html>
