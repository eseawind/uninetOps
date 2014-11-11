<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>应用配置</title>
   	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
   	<script type="text/javascript">
   		$(document).ready(function(){
			
   		});
   		//响应场景树
		function echoSceneTree(id,name,no,rank, gtype){
			if(gtype == 1) {
				window.location.href = "${ctx}/fisherirs_edit.action?sceneId=" + id;
			}
		}
		//分页查询
		function gotoPage(pageNo){	
		    var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;		
			window.location.href = "${ctx}/fisherirs_list.action?"+ "d="+Math.random()+"&page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&hid_condition=" + encodeURI(hid_condition)+"&hid_value=" + encodeURI(hid_value);
		}
   	</script>
   	<style type="text/css">
	 table {
	 border-collapse: collapse;
	 border: 2px #bbd1fa solid;
	 }
	 tr{
	 height:25px;
	 }
	 td {
	  border: 1px #bbd1fa solid;
	 }
	 
  </style>   	

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
         <a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
		 <a href="#">应用配置</a><br/>
  	 
		  查询条件:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
		  	<option value="">-- 请选择 --</option>
		  	<option value="scene_name">养殖池名称</option>
		  </select>
		  <input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
    	  值:<input id="sel_value" name="sel_value" type="text" value="${hid_value }">
    	  <input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
    	  <input type="button" class="button1" onclick="document.getElementById('hid_condition').value=document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1)" value="查询"/>
    	  <br/>
    <table cellpadding="0" cellspacing="0" width="100%">
    	<tr bgcolor='#8FABDE'>
    		<td width="10%">序号</td>    		
    		<td width="20%">养殖池名称</td>
    		<td width="20%">所在地</td>
    		<td width="15%">创建者</td>
    		<td width="15%">类型</td>			
			<td width="10%">操作</td>
    	</tr>
    	<c:forEach var="op_Scene" items="${page.result }" varStatus="n">
    		<tr>
    			<td>${n.count }&nbsp;</td>    			
    			<td>${op_Scene.scene_name }&nbsp;</td>	
    			<td>${op_Scene.scene_addr }&nbsp;</td>	
    			<td>${op_Scene.scene_creater }&nbsp;</td>
    			<td>
    				<c:if test="${op_Scene.scene_type == 1}">
						设施园艺
					</c:if>
					<c:if test="${op_Scene.scene_type == 2}">
						水产养殖
					</c:if>
					<c:if test="${op_Scene.scene_type == 3}">
						大田种植
					</c:if>
					<c:if test="${op_Scene.scene_type == 4}">
						畜禽养殖
					</c:if>
    			</td>	
				<td>
					<a href="fisherirs_edit.action?sceneId=${op_Scene.scene_id }">编辑</a>					
				</td>
    		</tr>
    	</c:forEach>
    </table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="5" align="left" valign="middle">
				<span style="padding-left: 10px; font-size: 14px;">
					共${page.totalCount}条记录，
					每页${page.pageSize}条，
					当前第${page.pageNo}页，
					共${page.totalPages}页
				</span>
			</td>
			<td colspan="6" align="right" valign="middle"
				style="padding-right: 10px; font-size: 14px;"
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
	<s:actionmessage theme="custom" cssClass="success"/>
	<script type="text/javascript">
		var sel_condition = document.getElementById('sel_condition');
		for(var i=0;i<sel_condition.options.length;i++){
			var op = sel_condition.options[i];
			if(op.value=='${hid_condition}'){
				op.selected="selected";
			}
		}
	</script>
  </body>
</html>
