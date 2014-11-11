<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>控制设备状态管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<s:actionmessage theme="custom" cssClass="success"/>
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			document.getElementById("isScene").checked=true;
			document.getElementById("isScenehidden").value = "true";
			var ids = getChildren();
			/*
			if(ids != null && ids != ""){
				var idsArr = ids.split(",");
				if(idsArr.length > 60){
					alert("场景选择过多，请在精确选择！");
					return;
				}
			}
			*/
			//var code = $("#code").val();
			//var sceneName = $("#sceneName").val();
			//$("#codehidden").val(code);
			//$("#pagehidden").val(1);
			//$("#sceneNamehidden").val(sceneName);
			$("#sceneIdhidden").val(ids);
			$("#myform").submit();
			//window.location.href = "${ctx}/devSetup_list.action?sceneId=" + ids + "&code=" + code + "&sceneName=" + encodeURI(sceneName);
		}
	
		function gotoPage(no) {
			var ids = getChildren();
			var code = $("#code").val();
			var sceneName = $("#sceneName").val();
			$("#codehidden").val(code);
			$("#pagehidden").val(no);
			$("#sceneNamehidden").val(sceneName);
			if(document.getElementById("isScene").checked){
				$("#sceneIdhidden").val(ids);
			}
			
			$("#myform").submit();
			//window.location.href = "${ctx}/devSetup_list.action?page.pageNo=" + no + "&code=" + code + "&sceneName=" + encodeURI(sceneName) + "&sceneId="+ids;
		}
		
		var dev_id_arr_str;
		
		//删除
		function del(dev_id){
			if(confirm('是否确定删除？如果确定删除的话，与此设备相关的所有信息和数据将彻底删除，不可恢复。')){			    
				$.getJSON("${ctx}/gm_device_manage_del.action?gm_Device.dev_id="+dev_id,{						
						random:Math.random()
					},function(isSuc){						
						if(isSuc.type==1){							
							dev_id_arr_str = isSuc.message;
						}else{
							alert(isSuc.message);
						}
						prompt();
					}
				);				
			}
		}
		
		var DG = new J.dialog(
		{
			xButton : false,
			btnBar : false,
			maxBtn : false,
			iconTitle : false,
			cover : true,
			title : '提示信息',
			width : '470',
			height : '400',
			id : 'test2',
			page : "devSetup/gm_devchannelMsg.jsp"
		});
		
		function prompt() {
			DG.ShowDialog();
		}
		function closeWin() {
			DG.cancel();
		}
		
		function dd(){
			var d = window.parent.right.scene_tree;
			var str = "";
				str+=d.aNodes[d.selectedNode].id.substr(2)+",";
	    	for (var n=0; n<d.aNodes.length; n++) {
		        if(d.aNodes[n].id == d.aNodes[d.selectedNode].id){
		          getAllChildren(d.aNodes[n]);
		        }
		    }
		    function getAllChildren(node){
	      		for (var n=0; n<d.aNodes.length; n++) {
			        if (d.aNodes[n].pid == node.id) {
			        	str+=d.aNodes[n].id.substr(2)+",";
			            getAllChildren(d.aNodes[n]);		
			        }
				}
    		}
    		return str;
		}
		function changeS(c){
			if(c.checked){
				document.getElementById("isScenehidden").value = "true";
			}else{
				document.getElementById("isScenehidden").value = "false";
			}
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
	<a href="#">设备配置信息管理</a> <br>
				设备编号:
				<input type="text" value="${code}" id="code" /> 
				所属场景:
				<input type="text" value="${sceneName}" id="sceneName" /> 
				&nbsp;&nbsp;&nbsp;
				<input id="isScene" type="checkbox" onclick="changeS(this);">是否查询关联场景
				<input type="button" value="查询" onclick="gotoPage(1)" /> 
				<input type="button" value="添加" onclick="window.location.href = '${ctx }/devSetup_toSave.action'">
	  	<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
			<tr class="list_head">
				<td>设备编号</td>
				<td>所属场景</td>
				<td>设备名称</td>
				<td>设备序列号</td>
				<td>设备型号</td>
				<td>配置</td>
			</tr>
			<c:forEach items="${page.result}" var="devSetup" varStatus="n">
			<tr align="center">
				<tr>
					<td>${devSetup.dev_no}&nbsp;</td>
					<td>${devSetup.scene_id.scene_name}&nbsp;</td>
					<td>${devSetup.dev_name}&nbsp;</td>
					<td>${devSetup.dev_serial}&nbsp;</td>
					<td>${devSetup.dev_model}&nbsp;</td>
					<td>
						<a href="${ctx}/devSetup_show.action?id=${devSetup.dev_id}">查看</a>
						<c:if test="${user.role_id.role_id == 'role-1' }">
							/
							<a href="javascript:del('${devSetup.dev_id}')">删除</a>&nbsp;
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
	<form id="myform" action="${ctx}/devSetup_list.action" method="post">
		<input id="pagehidden" type="hidden" name="page.pageNo" value="">
		<input id="sceneNamehidden" type="hidden" name="sceneName" value="">
		<input id="codehidden" type="hidden" name="code" value="">
		<input id="sceneIdhidden" type="hidden" name="sceneId" value="">
		<input id="isScenehidden" type="hidden" name="isScene" value="">
	</form>
  </body>
  <script type="text/javascript">
  $(document).ready(function(){
	 var isScene = "${isScene}";
	 if(isScene == "" || isScene == "false"){
		 document.getElementById("isScene").checked=false;
		 document.getElementById("isScenehidden").value = "false";
	 }else{
		 document.getElementById("isScene").checked=true;
		 document.getElementById("isScenehidden").value = "true";
	 }
  });
  
  
  function getChildren() {
		var d = this.parent.right.scene_tree;
		var str = "";
		if(d.selectedNode!=null){
			//alert(d.aNodes[d.selectedNode]._ls);
			//if(d.aNodes[d.selectedNode]._ls){
				str+=d.aNodes[d.selectedNode].id.substr(2)+",";
			//}
	  	for (var n=0; n<d.aNodes.length; n++) {
		        if(d.aNodes[n].id == d.aNodes[d.selectedNode].id){
		          getAllChildren(d.aNodes[n]);
		        }
		    }
		    function getAllChildren(node){
	    		for (var n=0; n<d.aNodes.length; n++) {
			        if (d.aNodes[n].pid == node.id) {
			        	//if(d.aNodes[n]._ls){
			        		str+=d.aNodes[n].id.substr(2)+",";
			        	//}
			            getAllChildren(d.aNodes[n]);		
			        }
				}
			}
		}
		
		return str;
}
  </script>
</html>
