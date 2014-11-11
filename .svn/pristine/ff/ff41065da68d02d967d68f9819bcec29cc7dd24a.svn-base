<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>显示采集通道信息</title>
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<s:actionmessage theme="custom" cssClass="success"/>
	<script type="text/javascript" src="${ctx }/js/dtree.js"></script>
	<script type="text/javascript">
		//分页查询
		//0713 UP Wang_Yuliang
		function gotoPage(pageNo){	
			//var queryValue = document.getElementById("queryValue").value;
			//var scene_id = document.getElementById("hide_scene_id").value;
//			var scene_id_arr = document.getElementById("hide_scene_id_arr").value;
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
//			window.location.href = "${ctx}/Gm_Channel_findAll.action?page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&hid_condition=" + encodeURI(hid_condition)+"&hid_value="+hid_value+"&scene_id="+scene_id;
			var url = "${ctx}/Gm_Channel_findAll.action?page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&hid_condition=" + encodeURI(hid_condition)+"&hid_value="+hid_value;
//			if(scene_id_arr.length>0)
//				url += "&"+scene_id_arr;
			document.forms[0].action = url;
			document.forms[0].submit();			
		}
		
		//响应场景树
		function echoSceneTree(id,name,no){
//			document.getElementById("hide_scene_id").value = id;
//			document.getElementById('hid_condition').value = document.getElementById('sel_condition').value;
//			document.getElementById('hid_value').value=document.getElementById('sel_value').value;
//			gotoPage(1);
//不能在循环里写查询
			var arr = new Array();	
			arr.push(id);
			arr = findSceneTree_wangyuliang(arr);
			var scene_id_arr_str = "<input type=\"checkbox\" style=\"display: none;\" name=\"scene_id_arr\" value=\"-1\" checked=\"checked\">";
			for(var i=0;i<arr.length;i++){
				scene_id_arr_str += "<input type=\"checkbox\" style=\"display: none;\" name=\"scene_id_arr\" value=\""+arr[i]+"\" checked=\"checked\">";
			}
			document.getElementById("sceneForm").innerHTML = scene_id_arr_str;
			document.getElementById('hid_condition').value = document.getElementById('sel_condition').value;
			document.getElementById('hid_value').value=document.getElementById('sel_value').value;
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
	</script>
  </head>
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
<form id="sceneForm" action="" method="post">  					
	${scene_id_arr }
</form>
<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
<a href="javascript:window.location.href=window.location.href">采集通道信息管理</a><br>
  				
  				<!-- 0713 UP Wang_Yuliang 据小华姐需求 
  				<input id="queryValue" type="text" value="${queryValue }"-->
  				<!-- 通道名称、通道编号、采集设备编号、所属场景名称。 -->
  				查询条件:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
		          	<option value="">-- 请选择 --</option>
		          	<option value="ch_name">通道名称</option>
		          	<option value="ch_no">通道编号</option>
		          	<option value="dev_no">采集设备编号</option>
		          	<option value="scene_name">所属场景名称</option>
		          </select>
          		<input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
          		值:<input id="sel_value" name="sel_value" type="text" value="${hid_value }">
    	  		<input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
  				<!-- input id="hide_scene_id" name="hide_scene_id" type="hidden" value="${scene_id }" -->
  				<!-- input id="hide_scene_id_arr" name="hide_scene_id_arr" type="hidden" value="${scene_id_arr }" -->
  				
  				<input type="button" onclick="document.getElementById('hid_condition').value = document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1);" value="查询" >
  				&nbsp;&nbsp;<input type="button" value="添加通道" onclick="window.location.href='Gm_Channel_save.action'"/>
  				&nbsp;&nbsp;<a href="${ctx }/Op_ChannelType_findAll.action"><font size="4">查看采集通道应用类型</font></a>
  				
	  	<table cellpadding="0" cellspacing="0" class="senfe1" style="width: 100%;">
			<tr class="list_head">
				<td>通道编号</td>
				<td>通道名称</td>
				<td>采集设备</td>				
				<td>所属场景</td>
				<td>应用类型</td>
				<td>单位</td>
				<td>提供服务</td>				
				<td>操作</td>
			</tr>
			<c:forEach items="${page.result}" var="channel" varStatus="n">
			<tr>
				<td>${channel.ch_no}&nbsp;</td>
				<td>${channel.ch_name}&nbsp;</td>
				<td>${channel.dev_collectId.dev_no}&nbsp;</td>
				<!-- td>${channel.ch_chlNo}</td>
				<td>channel.dev_sensorId.dev_no&nbsp;</td>				
				<td>channel.chtype_id.chtype_no&nbsp;</td>
				<td>
					<c:if test="${channel.ch_procesStyle==0}">无效通道</c:if>
					<c:if test="${channel.ch_procesStyle==1}">一般采集，</br>数据上报-存储模式</c:if>
					<c:if test="${channel.ch_procesStyle==2}">实时采集数据，</br>平台定时存储</c:if>
					<c:if test="${channel.ch_procesStyle==3}">校正后存储-</br>设备能量状态显示</c:if>
					<c:if test="${channel.ch_procesStyle==4}">校正后状态为停不存储，</br>其它定时存储-</br>控制设备状态返回显示</c:if>&nbsp;		
				</td>				
				<td>${channel.ch_memoryPeriod}秒&nbsp;</td>
				<td>${channel.ch_dectDig}&nbsp;</td>
				<td>${channel.ch_unit}&nbsp;</td>
				<td>${channel.ch_max}&nbsp;</td>	
				<td>${channel.ch_min}&nbsp;</td>
				<td>${channel.ch_crateMax}&nbsp;</td>
				<td>${channel.ch_corrCyc}&nbsp;</td>				
				<td>${channel.ch_corrFormula}&nbsp;</td>
				<td>${channel.ch_corrUnit}&nbsp;</td>
				<td>
					<c:if test="${channel.ch_state == 0}">未使用</c:if>
					<c:if test="${channel.ch_state == 1}">已使用</c:if>&nbsp;
				</td -->
				<td>${channel.scene_id.scene_name}&nbsp;</td>
				<td>${channel.chtype_id.chtype_displayName}<br/>${channel.chtype_id.chtype_no}&nbsp;</td>
				<!-- td>${channel.ch_seat_no}&nbsp;</td>
				<td>${channel.ch_depth}&nbsp;</td -->
				<td>${channel.ch_corrUnit}&nbsp;</td>
				<td>
					<c:if test="${channel.ch_offerSer == 0}">否</c:if>
					<c:if test="${channel.ch_offerSer == 1}">是</c:if>&nbsp;		
				</td>						
				<td>
					<a href="javascript:window.location.href='${ctx }/Gm_Channel_toEdit.action?gm_Channel.ch_id=${channel.ch_id}'">编辑</a>
					<!-- 
					<a href="javascript:if(confirm('确定停用?停用通道信息将会删除与之关联的【设备上报通道配置信息】、【智能设备故障信息】以及【控制设备状态配置信息】')){window.location.href='${ctx }/Gm_Channel_delete.action?gm_Channel.ch_id=${channel.ch_id}';}">停用</a>
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
