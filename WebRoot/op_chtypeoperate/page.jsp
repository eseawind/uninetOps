<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@page import="org.unism.gm.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>数据汇总配置管理</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<style>
	<!--		

	-->
	</style>
	<s:actionmessage theme="custom" cssClass="success"/>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
		//分页查询
		//0704 UP Wang_Yuliang
		//0707 UP Wang_Yuliang
		function gotoPage(pageNo){
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
			window.location.href = "${ctx}/op_chtypeoperate_page.action?"+ "d="+Math.random()+"&page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&hid_condition="+encodeURI(hid_condition)+"&hid_value="+encodeURI(hid_value);
		}
		//响应场景树
		//0704 UP Wang_Yuliang
		//0707 UP Wang_Yuliang
		function echoSceneTree(id,name,no){
			//不需要
		}
		
		//查询条件下拉列表改变事件
		function hid_condition_onchange(){
			var sel_value = document.getElementById("sel_value");
			sel_value[0].selected = true;
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;"> 
  	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="#">数据汇总配置管理</a> </br>
           查询条件:<select id="sel_condition" name="sel_condition" onchange="hid_condition_onchange()">
          	<option value="-1">-- 请选择 --</option>
          	<option value="scene_gtype">场景细类</option>
          </select>
          <input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
           值:<select id="sel_value" name="sel_value">
           		<option value="-1">-- 请选择 --</option>
        		<%
					List<Scene_gtype> scene_gtypes = StaticDataManage.getScene_gtypes();
					Iterator<Scene_gtype> scene_gtypesIterator = scene_gtypes.iterator();
				%>
				<%
					while (scene_gtypesIterator.hasNext()) {
						Scene_gtype scene_gtype = scene_gtypesIterator.next();
				%>
				<option value="<%=scene_gtype.getId()%>"><%=scene_gtype.getName()%></option>
				<%
					}
				%>
           </select>
    	  <input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
    		<input type="button" onclick="document.getElementById('hid_condition').value = document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1)" value="查询"/>
    		<input type="button" onclick="var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = '${ctx }/op_chtypeoperate_toSave.action?d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&hid_condition='+encodeURI(hid_condition)+'&hid_value='+encodeURI(hid_value);" value="添加"/>
    <br/>
    
  	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
		<tr class="list_head">    		
    		<td>排序号</td>
    		<td>场景细类</td>
			<td>位置编号</td>
			<td>通道深度</td>
			<td>数值类型</td>		
			<td>应用类型</td>
			<td>列名称</td>					
			<td>操作</td>
    	</tr>
    	<c:forEach var="op_Chtypeoperate" items="${page.result }" varStatus="n">
    		<tr>
    			<td>${op_Chtypeoperate.cho_sequence }&nbsp;</td>
    			<td>
    				<c:forEach items="${sceneGtype }" var="sceneGtype">
							<c:choose>
								<c:when test="${sceneGtype.gtype == op_Chtypeoperate.scene_gtype }">
									${sceneGtype.name }
								</c:when>
							</c:choose>
					</c:forEach>
    			&nbsp;</td>
    			<td>${op_Chtypeoperate.ch_seat_no }&nbsp;</td>
				<td>${op_Chtypeoperate.ch_depth }&nbsp;</td>
				<td>
					${op_Chtypeoperate.cho_datetype_str }
				&nbsp;</td>
				<td>${op_Chtypeoperate.chtype_id.chtype_displayName }(${op_Chtypeoperate.chtype_id.chtype_no })&nbsp;</td>
				<td>${op_Chtypeoperate.cho_rowname }&nbsp;</td>
				<td>
					<a href="javascript:var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = 'op_chtypeoperate_toEdit.action?op_ChtypeOperate.cho_id=${op_Chtypeoperate.cho_id }&d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&hid_condition='+encodeURI(hid_condition)+'&hid_value='+encodeURI(hid_value);">编辑</a>
					<c:if test="${user.role_id.role_id == 'role-1' }">
						/
						<a href="javascript:if(confirm('确定删除?')){var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = 'op_chtypeoperate_delete.action?op_ChtypeOperate.cho_id=${op_Chtypeoperate.cho_id }&d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&hid_condition='+encodeURI(hid_condition)+'&hid_value='+encodeURI(hid_value);}">删除</a>
					</c:if>
				</td>
    		</tr>
    	</c:forEach>
    </table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="5" align="left" valign="middle">
				<span style="padding-left: 10px; font-size: 12px;">
					共${page.totalCount}条记录，
					每页${page.pageSize}条，
					当前第${page.pageNo}页，
					共${page.totalPages}页
				</span>
			</td>
			<td colspan="6" align="right" valign="middle"
				style="padding-right: 10px; font-size: 12px;"
				class="more">
				<a href="javascript:gotoPage(1)">首&nbsp;&nbsp;页</a>
				<a href="javascript:gotoPage(${page.prePage})">
					上一页
				</a>
				<a href="javascript:gotoPage(${page.nextPage})">
					下一页
				</a>
				<a href="javascript:gotoPage(${page.totalPages})">
					尾&nbsp;&nbsp;页
				</a> 跳转到
				<select onChange="gotoPage(this.value)">
					<script type="text/javascript">
						for(var i=1;i<=parseInt("${page.totalPages}");i++){
						  if(i==parseInt("${page.pageNo}"))
				  			document.write("<option value="+i+" selected>"+i+"</option>");
				 		  else
				  			document.write("<option value="+i+">"+i+"</option>");
						}
					</script>
				</select>
				页
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		var sel_condition = document.getElementById('sel_condition');
		for(var i=0;i<sel_condition.options.length;i++){
			var op = sel_condition.options[i];
			if(op.value=='${hid_condition}'){
				op.selected="selected";
				if(op.value=="scene_gtype"){
					var sel_value = document.getElementById("sel_value");
					for(var j=0;j<sel_value.length;j++){
						var oop = sel_value[j];
						if(oop.value=='${hid_value}'){
							oop.selected = true;
						}
					}
				}else{}
			}
		}
	</script>
  </body>
</html>
