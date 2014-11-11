<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>控制设备操作历史管理</title>    
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
	<script type="text/javascript" src="${ctx }/js/date_sfm.js"></script>
	<script type="text/javascript">
		//分页查询
		function gotoPage(pageNo){
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
			var hid_beginTime = document.getElementById('hid_beginTime').value;
			var hid_endTime = document.getElementById('hid_endTime').value;
			//var hid_scene_id = document.getElementById("hid_scene_id").value;
			var url = "${ctx}/gm_devctrloperatehistory_page.action?"+ "d="+Math.random()+"&page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&hid_condition="+encodeURI(hid_condition)+"&hid_value="+encodeURI(hid_value);
			if(hid_beginTime!=''){
				url = url + "&beginTime=" + encodeURI(hid_beginTime);
			}
			if(hid_endTime!=''){
				url = url + "&endTime=" + encodeURI(hid_endTime);
			}
			/**
			if(hid_scene_id!=''){
				url = url + "&scene_id="+encodeURI(hid_scene_id);
			}
			window.location.href = url;
			*/
			document.forms[0].action = url;
			document.forms[0].submit();	
		}
		
		//响应场景树
		function echoSceneTree(id,name,no){
			var arr = new Array();	
			arr.push(id);
			arr = findSceneTree_wangyuliang(arr);
			var scene_id_arr_str = "<input type=\"checkbox\" style=\"display: none;\" name=\"scene_id_arr\" value=\"-1\" checked=\"checked\">";
			for(var i=0;i<arr.length;i++){
				scene_id_arr_str += "<input type=\"checkbox\" style=\"display: none;\" name=\"scene_id_arr\" value=\""+arr[i]+"\" checked=\"checked\">";
			}
			document.getElementById("sceneForm").innerHTML = scene_id_arr_str;
			gotoPage(1);
		}
		
		//根据场景ID集合 返回所有相关场景ID集合 同级以场景编号排序 (限定scene_id范围，的通道用回与用户相关的场景树向下查询)
		function findSceneTree_wangyuliang(arr){
			var scene_id = arr[arr.length-1];
			var child_list = window.parent.scene_tree.getChild("s_"+scene_id);
			/** 可以不排序
			for(var i=0;i<child_list.length;i++){
				for(var j=0;j<((child_list.length-1)-i);j++){
					if(child_list[j].id>child_list[j+1].id){
						var t = child_list[j];
						child_list[j] = child_list[j+1];
						child_list[j+1] = t; 
					}
				}
			}
			*/
			for(var i=0;i<child_list.length;i++){
				var node = child_list[i];
				if(child_list.length > 0){
					arr.push(node.id.substr(2));
					arr = findSceneTree_wangyuliang(arr);
				}
			}
			return arr;
		}
		
		//查询条件下拉列表改变事件
		function sel_condition_onchange(){
			document.getElementById("sel_value_no").style.display = 'none';
			document.getElementById("sel_value_no").value = '';
			document.getElementById("sel_value_result").style.display = 'none';
			var condition = document.getElementById("sel_condition").value;
			if(condition == 'deco_result'){
				document.getElementById("sel_value_result").style.display = 'block';
			}else{
				document.getElementById("sel_value_no").style.display = 'block';
			}
		}
		
		//查询
		function search(){
			var sel_condition = document.getElementById('sel_condition');
			document.getElementById('hid_condition').value = sel_condition.value;
			var hid_value = document.getElementById("hid_value");
			if(sel_condition.value=='deco_result'){
				hid_value.value = document.getElementById("sel_value_result").value;
			}else if(sel_condition.value == 'dect_no'){
				hid_value.value = document.getElementById("sel_value_no").value;
			}else{
				hid_value.value = '';
			}
			document.getElementById('hid_beginTime').value=document.getElementById('txt_beginTime').value;
			document.getElementById('hid_endTime').value=document.getElementById('txt_endTime').value;
			document.getElementById('sceneForm').innerHTML='';
			gotoPage(1)
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
	 
  	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="#">控制设备操作历史管理</a> </br>
          
          <table cellpadding="0" cellspacing="0" border="0">
  		<tr><td style=" border: 0px;padding: 0px; font-size: 12px;">
           查询条件:<select id="sel_condition" name="sel_condition" onchange="sel_condition_onchange()">
          	<option value="-1">-- 请选择 --</option>
          	<option value="dect_no">控制设备编号</option>
          	<option value="deco_result">返回结果</option>
          </select>
          <input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
          </td><td style=" border: 0px;padding: 0px; font-size: 12px;">
           值:</td><td style=" border: 0px;padding: 0px; font-size: 12px;">
           <input id="sel_value_no" type="text" value="${hid_value }">
           	<select id="sel_value_result" style="width:156px; display:none;">
           		<%
					List<Deco_result> deco_results = StaticDataManage.getDeco_results();
					Iterator<Deco_result> deco_resultsIterator = deco_results.iterator();
				%>
				<%
					while (deco_resultsIterator.hasNext()) {
						Deco_result deco_result = deco_resultsIterator.next();
				%>
				<option value="<%=deco_result.getId()%>"><%=deco_result.getName()%></option>
				<%
					}
				%>
           	</select>           
    	  <input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
    	  </td><td style=" border: 0px;padding: 0px; font-size: 12px;">
    	      	             	开始时间:<input id="txt_beginTime" value="<fmt:formatDate value='${beginTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly="readonly" onfocus="setday(this,this)"/>
           			<input type="hidden" id="hid_beginTime" value="<fmt:formatDate value='${beginTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
           	结束时间:<input id="txt_endTime" value="<fmt:formatDate value='${endTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly="readonly" onfocus="setday(this,this)"/>
           			<input type="hidden" id="hid_endTime" value="<fmt:formatDate value='${endTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
    		<!--  input type="hidden" id="hid_scene_id" value="${scene_id }"/-->
    	<input type="button" onclick="search()" value="查询"/>
    	</td></tr></table>
    <br/>
    
  	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
		<tr class="list_head">    		
    		<td>控制设备编号</td>
    		<td>请求时间</td>
			<td>操作类型</td>
			<td>返回结果</td>
			<td>返回错误码</td>			
			<td>操作用户姓名</td>
			<td>用户类型</td>
			<td>用户IP</td>
    	</tr>
    	<c:forEach var="gm_DevCtrlOperateHistory" items="${page.result }" varStatus="n">
    		<tr>
    			<td>${gm_DevCtrlOperateHistory.dect_id.dect_no }&nbsp;</td>
    			<td>${gm_DevCtrlOperateHistory.deco_time }&nbsp;</td>
    			<td>${gm_DevCtrlOperateHistory.deco_type_str }&nbsp;</td>
    			<td>${gm_DevCtrlOperateHistory.deco_result_str}&nbsp;</td>
    			<td>${gm_DevCtrlOperateHistory.deco_errorCode_str}&nbsp;</td>
    			<td>${gm_DevCtrlOperateHistory.deco_userName }&nbsp;</td>	
				<td>${gm_DevCtrlOperateHistory.deco_userType_str}&nbsp;</td>
				<td>${gm_DevCtrlOperateHistory.deco_userIp }&nbsp;</td>
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
<form id="sceneForm" action="" method="post">  					
	${scene_id_arr }
</form>		
	<script type="text/javascript">
		//默认选中
		var sel_condition = document.getElementById('sel_condition');
		for(var i=0;i<sel_condition.options.length;i++){
			var op = sel_condition.options[i];
			if(op.value=='${hid_condition}'){
				op.selected="selected";
				if(op.value == 'deco_result'){
					sel_condition_onchange();
					var sel_value_result = document.getElementById("sel_value_result");
					for(var j=0;j<sel_value_result.length;j++){
						var oop = sel_value_result[j];
						if(oop.value == '${hid_value}'){
							oop.selected = 'selected';
						}
					}				
				}
			}
		}
	</script>
  </body>
</html>
