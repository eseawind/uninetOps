<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'show.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="js/ztree/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" href="css/style_shuichan.css" type="text/css" />
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/ztree/jquery.ztree-2.6.js"></script>
 	<script>
    var setting = {
    		isSimpleData: true,
    		treeNodeKey: "id",
    		treeNodeParentKey: "pId",
    		showLine: true,
    		root:{ 
    			isRoot:true,
    			nodes:[]
    		}
    };

    var zNodes = ${netTreeJson};
    
    $(document).ready(function(){
    	var zTree = $("#treeDemo").zTree(setting, zNodes);
    	zTree.expandAll(true);
	});
    	
    
  //响应场景树
	function echoSceneTree(id,name,no,rank,gtype){
		//不响应
		//var ids = dd();
		//var idArr = ids.split(",");
		if(gtype != 2 && gtype != 3 && gtype != 4 && gtype != 5){
			window.location.href = "${ctx}/devSetup_showByScene.action?sceneId="+id;
		}
	}
    
    </script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='${ctx }/devSetup_list.action'">首页</a> 》 
	<a href="javascript:window.location.href='${ctx }/devSetup_list.action'">设备配置信息管理</a> 》 查看 <br>
    <table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
  		<tr class="list_head">
  			<td colspan="3"><b>设备信息</b></td>
  		</tr>
  		<tr>
  			<td>设备编号:${device.dev_no}</td>
  			<td>设备名称:${device.dev_name}</td>
  			<td>所属场景:${device.scene_id.scene_name}</td>
  		</tr>
  		<tr>
  			<td>网络编号:${devNet.net_no}</td>
  			<td>网络地址:${devNet.net_addr}</td>
  			<td>连接方式:${devNet.linkStsStr}</td>
  		</tr>
  		<tr class="list_head">
  			<td colspan="3"><b>网络节点信息</b></td>
  		</tr>
  		<tr>
  			<td colspan="3">
  				<ul id="treeDemo" class="tree"></ul>
  			</td>
  		</tr>
  		<tr class="list_head">
  			<td colspan="3"><b>通道信息</b></td>
  		</tr>
  		<tr>
  			<td colspan="3">
  				<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
  					<tr>
  						<td>上报顺序</td>
	  					<td>通道编号</td>
	  					<td>通道名称</td>
	  					<td>应用类型</td>
	  					<td>采集设备</td>
						<td>通道号</td>
						<td>所属场景</td>
						<td>是否对外服务</td>
						<td>数据处理方式</td>
  					</tr>
  					<c:forEach items="${devChannelList}" var="devChannel">
  						<tr>
	  						<td>${devChannel.dch_order}&nbsp;</td>
	  						<td>${devChannel.ch_id.ch_no}&nbsp;</td>
	  						<td>${devChannel.ch_id.ch_name}&nbsp;</td>
	  						<td>${devChannel.ch_id.chtype_id.chtype_displayName}(${devChannel.ch_id.chtype_id.chtype_no})&nbsp;</td>
	  						<td>${devChannel.ch_id.dev_collectId.dev_no}&nbsp;</td>
	  						<td>${devChannel.ch_id.ch_chlNo}&nbsp;</td>
	  						<td>${devChannel.ch_id.scene_id.scene_name}&nbsp;</td>
	  						<td>${devChannel.ch_id.offerSerStr}&nbsp;</td>
	  						<td>${devChannel.ch_id.procesStyleStr}&nbsp;</td>
  						</tr>
  					</c:forEach>
  				</table>
  			</td>
  		</tr>
  		<tr class="list_head">
  			<td colspan="3"><b>控制设备</b></td>
  		</tr>
  		<tr>
  			<td colspan="3">
  				<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
  					<tr>
  						<td>设备编号</td>
	  					<td>设备名称</td>
	  					<td>所属智能设备</td>
	  					<td>所属场景</td>
						<td>是否对外服务</td>
						<td>按钮数量</td>
  					</tr>
  					<c:forEach items="${devCtrlList}" var="devCtrl">
  						<tr>
	  						<td>${devCtrl.dect_no}&nbsp;</td>
	  						<td>${devCtrl.dect_name}&nbsp;</td>
	  						<td>${devCtrl.dev_id.dev_no}&nbsp;</td>
	  						<td>${devCtrl.scene_id.scene_name}&nbsp;</td>
	  						<td>${devCtrl.ch_offerSer}&nbsp;</td>
	  						<td>${devCtrl.decttype_btnNum}&nbsp;</td>
  						</tr>
  					</c:forEach>
  				</table>
  			</td>
  		</tr>
  	</table>
  </body>
</html>
