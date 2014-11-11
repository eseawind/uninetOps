<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@page import="org.unism.op.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加设备信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/date.js"></script>
	<script type="text/javascript">		
		function checkForm(){
			var scene_gtype = document.getElementById("op_ChtypeOperate.scene_gtype");
			var cho_rowname = document.getElementById("op_ChtypeOperate.cho_rowname");
			var cho_sequence = document.getElementById("op_ChtypeOperate.cho_sequence");
			var chtype_id = document.getElementById("op_ChtypeOperate.chtype_id.chtype_id");
			var ch_seat_no = document.getElementById("op_ChtypeOperate.ch_seat_no");
			var ch_depth = document.getElementById("op_ChtypeOperate.ch_depth");
			var cho_datetype = document.getElementById("op_ChtypeOperate.cho_datetype");
			if(scene_gtype.value=="-1"){
				alert('请选择场景类型细类');
				return false;
			}	
			if(cho_rowname.value.length==0){
				alert('请输入列名称');
				return false;
			}
			if(cho_sequence.value.length==0){
				alert('请输入排序号');
				return false;
			}
			var re_cho_sequence = /^[1-9]\d*|0$/;
			if(!re_cho_sequence.test(cho_sequence.value)){
				alert("排序号的格式不合法");
				return false;
			}
			if(chtype_id.value=="-1"){
				alert('请选择应用类型');
				return false;
			}
						
			return true;
		}
		
		//响应场景树
		function echoSceneTree(id,name,no){
			//无响应
		}
		
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="javascript:window.location.href='${ctx }/op_chtypeoperate_page.action?page.pageSize=2&hid_condition=-1'">数据汇总配置管理</a> 》 
  	<a href="#">添加数据汇总配置</a> </br>
    <form action="${ctx }/op_chtypeoperate_edit.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&hid_condition=${hid_condition}&hid_value=${hid_value}" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
			<input id="op_ChtypeOperate.cho_id" name="op_ChtypeOperate.cho_id" type="hidden" value="${op_ChtypeOperate.cho_id }">
			<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
				<tr class="list_head">
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>场景类型细类</td>
					<td>
						<select id="op_ChtypeOperate.scene_gtype" name="op_ChtypeOperate.scene_gtype" style="width:180px;">
							<option value="-1">-- 请选择 --</option>
							<c:forEach items="${sceneGtype }" var="scenGtype">
								<c:choose>
									<c:when test="${scenGtype.gtype == op_ChtypeOperate.scene_gtype }">
										<option value="${scenGtype.gtype }" selected="selected">${scenGtype.name }</option>
									</c:when>
									<c:otherwise>
										<option value="${scenGtype.gtype }">${scenGtype.name }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> 必填
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>列名称: </td>
					<td>
						<input id="op_ChtypeOperate.cho_rowname" name="op_ChtypeOperate.cho_rowname" style="width:180px;" value="${op_ChtypeOperate.cho_rowname }"> 必填
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>排序号: </td>
					<td>
						<input id="op_ChtypeOperate.cho_sequence" name="op_ChtypeOperate.cho_sequence" style="width:180px;" value="${op_ChtypeOperate.cho_sequence}"/> 必填
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">*</td>
					<td>应用类型: </td>
					<td>
						<select id="op_ChtypeOperate.chtype_id.chtype_id" name="op_ChtypeOperate.chtype_id.chtype_id" style="width:180px;"> 
							<option value="-1">-- 请选择 --</option>
							<c:forEach var="op_ChannelType" items="${op_ChannelType_list}">
								<c:choose>
									<c:when test="${op_ChtypeOperate.chtype_id.chtype_id==op_ChannelType.chtype_id}">
										<option value="${op_ChannelType.chtype_id }" selected="selected">${op_ChannelType.chtype_displayName }(${op_ChannelType.chtype_no})</option> 
									</c:when>
									<c:otherwise>
										<option value="${op_ChannelType.chtype_id }">${op_ChannelType.chtype_displayName }(${op_ChannelType.chtype_no})</option> 
									</c:otherwise>
								</c:choose> 
								
							</c:forEach> 
						</select> 必填
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>位置编号: </td>
					<td>
						<input id="op_ChtypeOperate.ch_seat_no" name="op_ChtypeOperate.ch_seat_no" style="width:180px;" value="${op_ChtypeOperate.ch_seat_no }"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>通道深度: </td>
					<td>
						<input id="op_ChtypeOperate.ch_depth" name="op_ChtypeOperate.ch_depth" style="width:180px;" value="${op_ChtypeOperate.ch_depth }"/>
					</td>
				</tr>
				<tr>
					<td style="width:12px; border-right: 0px;">&nbsp;</td>
					<td>数值类型: </td>
					<td>
						<select id="op_ChtypeOperate.cho_datetype" name="op_ChtypeOperate.cho_datetype" style="width:180px;">
							<option value="">-- 请选择 --</option>
							<%
								Op_ChtypeOperate op_ChtypeOperate = (Op_ChtypeOperate)request.getAttribute("op_ChtypeOperate");
								List<Cho_datetype> cho_datetypes = StaticDataManage.getCho_datetypes();
								Iterator<Cho_datetype> cho_datetypesIterator = cho_datetypes.iterator();
							%>
							<%
								while (cho_datetypesIterator.hasNext()) {
									Cho_datetype cho_datetype = cho_datetypesIterator.next();
									if(op_ChtypeOperate.getCho_datetype()!=null && String.valueOf(op_ChtypeOperate.getCho_datetype()).equals(cho_datetype.getId())){
							%>
							<option value="<%=cho_datetype.getId()%>" selected="selected"><%=cho_datetype.getName()%></option>
							<%		}else{ %>
							<option value="<%=cho_datetype.getId()%>"><%=cho_datetype.getName()%></option>
							<%
									}
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="提 交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返 回" onclick="window.location.href='${ctx }/op_chtypeoperate_page.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&hid_condition=${hid_condition}&hid_value=${hid_value}'">
					</td>
				</tr>															
			</table>	
		    
    </form>
  </body>
</html>
