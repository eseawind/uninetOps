
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>场景信息管理</title>
    
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
	<script type="text/javascript">
		//alert("${op}-${result}-${sms}");
	</script>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
		//分页查询
		function gotoPage(pageNo){
			var scene_pid = document.getElementById("op_Scene.scene_pid").value;
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
			//var scene_name = document.getElementById("op_Scene.scene_name").value;
			window.location.href = "${ctx}/op_scene_page.action?"+ "d="+Math.random()+"&page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&scene_pid=" + encodeURI(scene_pid)+"&hid_condition=" + encodeURI(hid_condition)+"&hid_value=" + encodeURI(hid_value);
		}
		//响应场景树
		function echoSceneTree(id,name,no){
			document.getElementById("op_Scene.scene_pid").value = id;
			document.getElementById('hid_condition').value=document.getElementById('sel_condition').value;
			document.getElementById('hid_value').value=document.getElementById('sel_value').value;
			//document.getElementById("op_Scene.scene_name").value = name;
			gotoPage(1);
		}
		
	</script>
  </head>  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;"> 
			<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
			<a href="#">场景信息管理</a><br/>
  	 
		  查询条件:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
		  	<option value="">-- 请选择 --</option>
		  	<option value="scene_no">场景编号</option>
		  	<option value="scene_name">场景名称</option>
		  	<option value="scene_pname">父场景名称</option>
		  </select>
		  <input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
    	  值:<input id="sel_value" name="sel_value" type="text" value="${hid_value }">
    	  <input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
          <input id="op_Scene.scene_pid" name="op_Scene.scene_pid" type="hidden" value="${op_Scene.scene_pid }">
       
    		<input type="button" class="button1" onclick="document.getElementById('hid_condition').value=document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1)" value="查询"/>
    		<input type="button" class="button1" onclick="var scene_pid = document.getElementById('op_Scene.scene_pid').value;var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = '${ctx }/op_scene_toSave_0714.action?'+'d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_pid=' + encodeURI(scene_pid)+'&hid_condition=' + encodeURI(hid_condition)+'&hid_value=' + encodeURI(hid_value);" value="添加"/>
    		<input type="button" class="button1" onclick="window.location.href = '${ctx }/op_scene_toAddChild.action'" value="添加场景关联"/>
    		<input type="button" class="button1" onclick="window.location.href = '${ctx }/op_scene_toDeleteChild.action'" value="删除场景关联"/>
    <br/>
    <table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
    	<tr class="list_head">
    		<td>场景编号</td>
    		<td>场景名称</td>			
			<td>父场景名称</td>	
			<td>场景所在地</td>	
			<td>场景大类</td>
			<td>场景子类</td>
			<td>场景细类</td>	
			<td>操作</td>
    	</tr>
    	<c:forEach var="op_Scene" items="${page.result }" varStatus="n">
    		<tr>
    			<td>${op_Scene.scene_no }&nbsp;</td>
    			<td>${op_Scene.scene_name }&nbsp;</td>	
    			<td>${op_Scene.scene_pname }&nbsp;</td>
    			<td>${op_Scene.scene_addr }&nbsp;</td>		
				<td>${op_Scene.scene_type_str}&nbsp;</td>
				<td>${op_Scene.scene_ctype_str}&nbsp;</td>	
				<td>
					<c:forEach items="${sceneGtype }" var="sceneGtype">
							<c:choose>
								<c:when test="${sceneGtype.gtype == op_Scene.scene_gtype }">
									${sceneGtype.name }
								</c:when>
							</c:choose>
					</c:forEach>
				&nbsp;</td>
				<td>
					<a href="javascript:var scene_pid = document.getElementById('op_Scene.scene_pid').value;var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = 'op_scene_toEdit_0714.action?op_Scene.scene_id=${op_Scene.scene_id }'+'&d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_pid=' + encodeURI(scene_pid)+'&hid_condition=' + encodeURI(hid_condition)+'&hid_value=' + encodeURI(hid_value);">编辑</a>
					<!-- 
					/
					<a href="javascript:if(confirm('确定停用?')){var scene_pid = document.getElementById('op_Scene.scene_pid').value;var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = 'op_scene_delete.action?op_Scene.scene_id=${op_Scene.scene_id }'+'&d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_pid=' + encodeURI(scene_pid)+'&hid_condition=' + encodeURI(hid_condition)+'&hid_value=' + encodeURI(hid_value);}">停用</a>
					-->
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
			}
		}
	</script>
  </body>
</html>
