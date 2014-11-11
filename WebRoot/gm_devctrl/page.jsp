<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>控制设备信息信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
		//分页查询
		function gotoPage(pageNo){	
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
			//alert(queryValue);	
			window.location.href = "${ctx}/Gm_DevCtrl_findAll.action?page.pageNo=" + pageNo + "&page.pageSize=10&hid_condition=" + encodeURI(hid_condition)+"&hid_value="+encodeURI(hid_value);
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">

<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
<a href="#">控制设备信息信息管理</a> <br>
<s:actionmessage theme="custom" cssClass="success"/>
		  查询条件:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
          	<option value="">-- 请选择 --</option>
          	<option value="dect_no">控制设备编号</option>
          	<option value="dev_no">所属设备编号</option>
          	<option value="scene_name">场景名称</option>
          </select>
          <input id="hid_condition" type="hidden" value="${hid_condition }">
          值:
  				<input id="sel_value" type="text" value="${hid_value }">
  				<input id="hid_value" type="hidden" value="${hid_value }">
  				<input type="button" onclick="document.getElementById('hid_condition').value=document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1)" value="查询" >
  				<input type="button" value="添加设备" onclick="location.href='Gm_DevCtrl_add.action'"/>
	  	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
			<tr class="list_head">
				<td>控制设备编号</td>				
				<td>设备名称</td>
				<td>所属设备编号</td>
				<td>场景名称</td>
				<td>对外提供服务</td>	
				<td>操作</td>
			</tr>
			<c:forEach items="${page.result}" var="devctrl" varStatus="n">
			<tr align="center">
				<td>${devctrl.dect_no}&nbsp;</td>
				<td>${devctrl.dect_name}&nbsp;</td>				
				<!-- td>${devctrl.dect_serial}&nbsp;</td>				
				<td>${devctrl.decttype_id.decttype_displayName}&nbsp;</td -->
				<td>${devctrl.dev_id.dev_no}&nbsp;</td>
				<!-- td>${devctrl.decttype_btnNum}&nbsp;</td>					
				<td>${devctrl.dect_buyDate}&nbsp;</td>
				<td>${devctrl.dect_overDate}&nbsp;</td>
				<td>${devctrl.dect_effectTime}&nbsp;</td>		
				<td>
					<c:if test="${devctrl.dect_state == 0}">
						未使用
					</c:if>
					<c:if test="${devctrl.dect_state == 1}">
						已使用
					</c:if>&nbsp;
				</td -->
				<td>${devctrl.scene_id.scene_name}</td>	
				<td>
					<c:if test="${devctrl.ch_offerSer == 0}">
						否
					</c:if>
					<c:if test="${devctrl.ch_offerSer == 1}">
						是
					</c:if>	&nbsp;	
				</td>
				<!-- td>${devctrl.dect_decsription}&nbsp;</td -->				
				<td>
					<a href="Gm_DevCtrl_edit.action?id=${devctrl.dect_id}&devId=${devctrl.dev_id.dev_id}">编辑</a>
					<c:if test="${user.role_id.role_id == 'role-1' }">
						<a href="Gm_DevCtrl_delete.action?id=${devctrl.dect_id}"
							onclick='return window.confirm("你确定要是删除吗？")'>删除</a>
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
			}
		}
	</script>
  </body>
</html>
