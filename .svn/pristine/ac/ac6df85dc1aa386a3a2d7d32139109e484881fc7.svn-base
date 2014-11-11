<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>设备信息管理</title>    
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
			//var dev_name = document.getElementById("hide_dev_name").value;
			var scene_id = document.getElementById("hide_scene_id").value;
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
			window.location.href = "${ctx}/gm_device_page.action?"+ "d="+Math.random()+"&page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&scene_id="+scene_id+"&hid_condition="+encodeURI(hid_condition)+"&hid_value="+encodeURI(hid_value);
		}
		//响应场景树
		//0704 UP Wang_Yuliang
		//0707 UP Wang_Yuliang
		function echoSceneTree(id,name,no){
			 document.getElementById("hide_scene_id").value = id;
			 document.getElementById('hid_condition').value = document.getElementById('sel_condition').value;
			 document.getElementById('hid_value').value=document.getElementById('sel_value').value;
			 gotoPage(1);
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;"> 
  	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="#">设备信息管理</a> </br>
    <!-- 设备名称：<input id="gm_Device.dev_name" name="gm_Device.dev_name" type="text" value="${gm_Device.dev_name }" -->
          <!-- 0704 UP Wang_Yuliang -->
          <!-- input id="hide_dev_name" name="hide_dev_name" type="hidden" value="${gm_Device.dev_name }" -->
          <!-- 0707 UP Wang_Yuliang -->
           查询条件:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
          	<option value="">-- 请选择 --</option>
          	<option value="dev_no">设备编号</option>
          	<option value="scene_name">所属场景名称</option>
          </select>
          <input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
           值:<input id="sel_value" name="sel_value" type="text" value="${hid_value }">
    	  <input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
          <input id="hide_scene_id" name="hide_scene_id" type="hidden" value="${scene_id }">
    		<input type="button" onclick="document.getElementById('hid_condition').value = document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1)" value="查询"/>
    		<input type="button" onclick="var scene_id = document.getElementById('hide_scene_id').value;var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = '${ctx }/gm_device_toSave_0715.action?d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_id='+scene_id+'&hid_condition='+encodeURI(hid_condition)+'&hid_value='+encodeURI(hid_value);" value="添加"/>
    <br/>
    
  	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
		<tr class="list_head">    		
    		<td>设备编号</td>
    		<td>设备名称</td>
			<td>所属场景名称</td>
			<td>所属场景编号</td>
			<td>设备序列号</td>			
			<td>操作</td>
    	</tr>
    	<c:forEach var="gm_Device" items="${page.result }" varStatus="n">
    		<tr>
    			<td>${gm_Device.dev_no }&nbsp;</td>
    			<td>${gm_Device.dev_name }&nbsp;</td>
    			<td>${gm_Device.scene_id.scene_name }&nbsp;</td>
    			<td>${gm_Device.scene_id.scene_no }&nbsp;</td>
    			<td>${gm_Device.dev_serial }&nbsp;</td>
    			<!-- td>${gm_Device.dev_name }&nbsp;</td>	
    			<td>
					<c:choose>
						<c:when test="${gm_Device.dev_btype == 0 }">
							M2M
						</c:when>
						<c:when test="${gm_Device.dev_btype == 1 }">
							WSN
						</c:when>
						<c:when test="${gm_Device.dev_btype == 2 }">
							智能单元
						</c:when>
						<c:when test="${gm_Device.dev_btype == 3 }">
							执行器(可控设备)
						</c:when>
						<c:when test="${gm_Device.dev_btype == 4 }">
							传感器
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${gm_Device.dev_model }&nbsp;</td>
				<td>${gm_Device.dev_powerType }&nbsp;</td>			
				<td>${gm_Device.dev_imsi }&nbsp;</td>
				<td>${gm_Device.dev_buyDate }&nbsp;</td>				
				<td>${gm_Device.dev_overDate }&nbsp;</td>
				<td>${gm_Device.dev_effectTime }&nbsp;</td>
				<td>${gm_Device.cust_saleId.sup_name }&nbsp;</td>
				<td>${gm_Device.cust_serviceId.sup_name }&nbsp;</td>
				<td>${gm_Device.scene_id.scene_no }&nbsp;</td-->
				<td>
					<a href="javascript:var scene_id = document.getElementById('hide_scene_id').value;var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = 'gm_device_toEdit_0715.action?gm_Device.dev_id=${gm_Device.dev_id }&d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_id='+scene_id+'&hid_condition='+encodeURI(hid_condition)+'&hid_value='+encodeURI(hid_value);">编辑</a>
					<!-- 
					/
					<a href="javascript:if(confirm('确定停用?')){var scene_id = document.getElementById('hide_scene_id').value;var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = 'gm_device_delete.action?gm_Device.dev_id=${gm_Device.dev_id }&d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_id='+scene_id+'&hid_condition='+encodeURI(hid_condition)+'&hid_value='+encodeURI(hid_value);}">停用</a>
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
