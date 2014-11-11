<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>网络信息管理</title>
    
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
			var scene_id = document.getElementById("hide_scene_id").value;
			var hid_condition = document.getElementById('hid_condition').value;
			var hid_value = document.getElementById('hid_value').value;
			window.location.href = "${ctx}/gm_devnet_page.action?"+ "d="+Math.random()+"&page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&scene_id="+scene_id+"&hid_condition="+hid_condition+"&hid_value="+hid_value;
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
  	<a href="#">网络信息管理</a> </br>
  	<!-- 0707 UP Wang_Yuliang -->
    <!-- 网络地址：<input id="gm_DevNet.net_addr" name="gm_DevNet.net_addr" type="text" value="${gm_DevNet.net_addr }">
    	  0704 UP Wang_Yuliang 
          <input id="hide_net_addr" name="hide_net_addr" type="hidden" value="${gm_DevNet.net_addr }"-->
          
            查询条件:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
          	<option value="">-- 请选择 --</option>
          	<option value="net_addr">网络地址</option>
          </select>
          <input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
           值:<input id="sel_value" name="sel_value" type="text" value="${hid_value }">
            <input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
          <input id="hide_scene_id" name="hide_scene_id" type="hidden" value="${scene_id }">
    		<input type="button" onclick="document.getElementById('hid_condition').value = document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1)" value="查询"/>
    		<input type="button" onclick="var scene_id = document.getElementById('hide_scene_id').value;var hid_condition = document.getElementById('hid_condition').value;	var hid_value = document.getElementById('hid_value').value;window.location.href = '${ctx }/gm_devnet_toSave_0716.action?d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_id='+scene_id+'&hid_condition='+hid_condition+'&hid_value='+hid_value;" value="添加"/>
    		<input type="button" onclick="window.location.href = '${ctx }/gm_devnet_toAddChild.action'" value="添加网络关联"/>
    		<input type="button" onclick="window.location.href = '${ctx }/gm_devnet_toDeleteChild.action'" value="删除网络关联"/>
    <br/>
    
  	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
		<tr class="list_head">
    		<td>网络编号</td>
    		<td>网络地址</td>
			<td>父网络编号</td>
			<td>SIM卡号</td>
			<td>短信中心号</td>
			<td>操作</td>
    	</tr>
    	<c:forEach var="gm_DevNet" items="${page.result }" varStatus="n">
    		<tr>
    			<td>${gm_DevNet.net_no }&nbsp;</td>
    			<!-- td>${gm_DevNet.dev_id.dev_no }&nbsp;</td -->
    			<td>${gm_DevNet.net_addr }&nbsp;</td>
    			<td>${gm_DevNet.net_pno }&nbsp;</td>
    			<!-- td>
    				<c:choose>
						<c:when test="${gm_DevNet.net_type == 0 }">
							远程网 M2M
						</c:when>
						<c:when test="${gm_DevNet.net_type == 1 }">
							无线传感网络 wsn
						</c:when>
						<c:when test="${gm_DevNet.net_type == 2 }">
							有线传感网 智能传感器
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>&nbsp;
    			</td>
    			<td>
					<c:choose>
						<c:when test='${gm_DevNet.net_role == "01" }'>
							网关
						</c:when>
						<c:when test='${gm_DevNet.net_role == "02" }'>
							独立设备
						</c:when>
						<c:when test='${gm_DevNet.net_role == "11" }'>
							RD
						</c:when>
						<c:when test='${gm_DevNet.net_role == "12" }'>
							ED
						</c:when>
						<c:when test='${gm_DevNet.net_role == "13" }'>
							CD
						</c:when>
						<c:when test='${gm_DevNet.net_role == "14" }'>
							LD
						</c:when>
						<c:when test='${gm_DevNet.net_role == "21" }'>
							传感器
						</c:when>
						<c:when test='${gm_DevNet.net_role == "22" }'>
							变送器
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td>${gm_DevNet.net_depth }&nbsp;</td>
				<td>
					<c:choose>
						<c:when test="${gm_DevNet.net_linkSts == 0 }">
							无效
						</c:when>
						<c:when test="${gm_DevNet.net_linkSts == 1 }">
							长连接
						</c:when>
						<c:when test="${gm_DevNet.net_linkSts == 2 }">
							短连接
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>			
				<td>${gm_DevNet.net_appType }&nbsp;</td -->
				<td>${gm_DevNet.net_sim }&nbsp;</td>				
				<td>${gm_DevNet.net_smsc }&nbsp;</td>
				<!-- td>${gm_DevNet.net_pltType }&nbsp;</td -->
				<td>
					<a href="javascript:var scene_id = document.getElementById('hide_scene_id').value;var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = '${ctx }/gm_devnet_toEdit_0716.action?gm_DevNet.net_id=${gm_DevNet.net_id }&d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_id='+scene_id+'&hid_condition='+hid_condition+'&hid_value='+hid_value;">编辑</a>
					<!-- 
					/
					<a href="javascript:if(confirm('确定停用?')){var scene_id = document.getElementById('hide_scene_id').value;var hid_condition = document.getElementById('hid_condition').value;var hid_value = document.getElementById('hid_value').value;window.location.href = '${ctx }/gm_devnet_delete_0716.action?gm_DevNet.net_id=${gm_DevNet.net_id }&user_id=${user.user_id}&d='+Math.random()+'&page.pageNo=${page.pageNo }&page.pageSize=${page.pageSize}&scene_id='+scene_id+'&hid_condition='+hid_condition+'&hid_value='+hid_value;}">停用</a>
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
