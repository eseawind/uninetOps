<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>编辑LED信息</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">
	  	//表单验证
		function checkForm(){
			var led_no = document.getElementById("op_led.led_no");
			if(led_no.value.length == 0){
				alert("LED卡号不能为空");
				return;
			}
			var led_interval = document.getElementById("op_led.led_interval");
			var re_led_interval = /^\d+$/;
			if(!re_led_interval.test(led_interval.value)){
				alert("请填写正确的更新间隔，正整数和0");
				return;
			}
			var led_id = document.getElementById("op_led.led_id");
			$.getJSON("${ctx}/op_led_exist_edit.action?op_led.led_id="+led_id.value+"&op_led.led_no="+led_no.value,{
				random:Math.random()
			},function(exist){
				if(exist==0){
					document.forms[0].submit();
					return;
				}else if(exist==1){
					alert("LED卡号已存在");
					return;
				}else{
					alert("未知错误");
					return;
				}
			});
		}
	</script>
  </head>

  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  		<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
		<a href="${ctx }/op_led_page.action">LED信息管理</a> 》
		<a href="#">编辑LED信息</a> <br/> 
		<form action="${ctx }/op_led_edit.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&hid_condition=${hid_condition}&hid_value=${hid_value}" method="post">       		
    		<input id="op_led.led_id" name="op_led.led_id" value="${op_led.led_id }" type="hidden"/>
    		<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
    			<tr class="list_head">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>LED名称: </td>
					<td><input id="op_led.led_name" name="op_led.led_name" value="${op_led.led_name }"/> </td>
				</tr>
				<tr>
					<td>*LED卡号: </td>
					<td><input id="op_led.led_no" name="op_led.led_no" value="${op_led.led_no }"/> 必填，唯一</td>
				</tr>
				<tr>
					<td>显示内容: </td>
					<td><input id="op_led.led_content" name="op_led.led_content" value="${op_led.led_content }"/> </td>
				</tr>
				<tr>
					<td>*更新间隔: </td>
					<td><input id="op_led.led_interval" name="op_led.led_interval" value="${op_led.led_interval }"/> 必填，整数</td>
				</tr>
				<tr>
					<td>*是否启用: </td>
					<td>
						<c:if test="${op_led.led_enable==1 }">
							<input name="op_led.led_enable" type="radio" value="1" checked="checked"/>是&nbsp;
							<input name="op_led.led_enable" type="radio" value="0"/>否&nbsp;
						</c:if>
						<c:if test="${op_led.led_enable==0}">
							<input name="op_led.led_enable" type="radio" value="1"/>是&nbsp;
							<input name="op_led.led_enable" type="radio" value="0" checked="checked"/>否&nbsp;
						</c:if>
						必填
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="提交" onclick="checkForm()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回" onclick="window.location.href='op_led_page.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&hid_condition=${hid_condition}&hid_value=${hid_value}'">
					</td>
				</tr>
			</table>			
		</form>
	</body>
</html>
