<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>更新控制设备类型信息</title>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	
	<script language="javascript" type="text/javascript">
		function isValidate() {
			var decttypeno = document.getElementById("decttype_no").value;
			var decttypedisplayName = document.getElementById("decttype_displayName").value;
			var decttypetypeNo = document.getElementById("decttype_typeNo").value;
			var decttypepower = document.getElementById("decttype_power").value;
			var decttypevoltage = document.getElementById("decttype_voltage").value;
			var decttypedecription = document.getElementById("decttype_decription").value;
			//var decttypeimg = document.getElementById("decttype_img").value;
			var decttypebtnNum = document.getElementById("decttype_btnNum").value;
			var decttypechlStsNum = document.getElementById("decttype_chlStsNum").value;
			var zhengze = /^(0|[1-9]\d*)$/;
			var feifu = /^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$/;
			if(decttypeno == ""){
				window.confirm("类型编号不能为空！");
				return false;
			}
			if(decttypedisplayName == ""){
				window.confirm("类型名称不能为空！");
				return false;
			}
			if(decttypetypeNo == ""){
				window.confirm("类型型号不能为空！");
				return false;
			}
			if(decttypepower == ""){
				window.confirm("类型功率不能为空！");
				return false;
			}
			if(!feifu.test(decttypepower)){
				window.confirm("类型功率为非负的小数！");
				return false;
			}
			if(decttypevoltage == ""){
				window.confirm("类型供电电压不能为空！");
				return false;
			}
			if(!feifu.test(decttypevoltage)){
				window.confirm("类型供电电压为非负的小数！");
				return false;
			}
			
			if(decttypebtnNum == ""){
				window.confirm("类型按钮数量不能为空！");
				return false;
			}
			
			if(!zhengze.test(decttypebtnNum)){
				window.confirm("类型按钮数量为整数，请输入一个整数！");
				return false;
			}
			if(decttypechlStsNum == ""){
				window.confirm("通道状态反馈数据不能为空！");
				return false;
			}
			if(!zhengze.test(decttypechlStsNum)){
				window.confirm("通道状态反馈数量为整数，请输入一个整数！");
				return false;
			}			
			return true;
		}
	</script>
	
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
				<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
				<a href="javascript:window.location.href='Op_DevCtrlType_findAll.action'">控制设备类型信息管理</a> 》
				<a href="javascript:window.location.href=window.location.href">编辑控制设备类型信息</a> </br>
    <form action="Op_DevCtrlType_update.action" method="post" onsubmit="return isValidate()" enctype="multipart/form-data">
    	<c:forEach items="${requestScope.list}" var="devctrltype">
    	
    	<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
   			<tr class="list_head">
   				<td colspan="2">&nbsp;</td>
   			</tr>
    		<tr>
				<td>类型编号</td>
				<td><input type="text" name="op_DevCtrlType.decttype_no" id="decttype_no" value="${devctrltype.decttype_no}"/></td>
			</tr>
    		<tr>
				<td>类型名称</td>
				<td>
					<input type="hidden" name="op_DevCtrlType.decttype_id" value="${devctrltype.decttype_id}"/>
					<input type="text" name="op_DevCtrlType.decttype_displayName" id="decttype_displayName" value="${devctrltype.decttype_displayName}"/>
				</td>
			</tr>
			<tr>
				<td>类型型号</td>
				<td><input type="text" name="op_DevCtrlType.decttype_typeNo" id="decttype_typeNo" value="${devctrltype.decttype_typeNo}"/></td>
			</tr>
			<tr>
				<td>类型功率</td>
				<td><input type="text" name="op_DevCtrlType.decttype_power" id="decttype_power" value="${devctrltype.decttype_power}"/></td>
			</tr>
			<tr>
				<td>类型供电电压</td>
				<td><input type="text" name="op_DevCtrlType.decttype_voltage" id="decttype_voltage" value="${devctrltype.decttype_voltage}"/></td>
			</tr>
			<tr>
				<td>类型说明</td>
				<td><input type="text" name="op_DevCtrlType.decttype_decription" id="decttype_decription" value="${devctrltype.decttype_decription}"/></td>
			</tr>
			<tr>
				<td>类型图片资源</td>
				<td>
					<input type="hidden" name="op_DevCtrlType.decttype_img" value="${devctrltype.decttype_img }"/>
					<span>未知:<input type="file" name="imagefile" ><br/>
					&nbsp;&nbsp;&nbsp;开:<input type="file" name="imagefile" ><br/>
					&nbsp;&nbsp;&nbsp;停:<input type="file" name="imagefile" ><br/>
					&nbsp;&nbsp;&nbsp;关:<input type="file" name="imagefile" ></span>
				</td>
			</tr>
			<tr>
				<td>类型按钮数量</td>
				<td><input type="text" name="op_DevCtrlType.decttype_btnNum" id="decttype_btnNum" value="${devctrltype.decttype_btnNum}"/></td>
			</tr>
			<tr>
				<td>通道状态反馈数据</td>
				<td><input type="text" name="op_DevCtrlType.decttype_chlStsNum" id="decttype_chlStsNum" value="${devctrltype.decttype_chlStsNum}"/></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="提交"/>	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
					<input type="reset" value="返 回" onclick="window.location.href='Op_DevCtrlType_findAll.action'"/>
				</td>
			</tr>
    	</table>
    	</c:forEach>
    </form>
  </body>
</html>
