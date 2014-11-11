<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>删除网络关联</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/checked_devnet.js"></script>
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no){
			/**
			document.getElementById("hidden_scene_id").value = id;
			document.getElementById("txt_scene_no").value = no;
			document.getElementById("txt_scene_name").value = name;
			
			//加载待选子场景
			var sel_childScene = document.getElementById("sel_childScene");
			sel_childScene.length = 0;
			$.getJSON("${ctx}/op_scene_findChildedList.action?op_Scene.scene_id="+id+"&user_id=${user.user_id}",{
				random:Math.random()
			},function(list){
				$.each(list,function(i,child){
					sel_childScene.add(new Option(child.scene_name,child.scene_id));
				})
			});
			
			//加载已选子场景	
			var sel_childedScene = document.getElementById("sel_childedScene");
			sel_childedScene.length = 0;
			$.getJSON("${ctx}/op_scene_findChildedList.action?op_Scene.scene_id="+id+"&user_id=${user.user_id}",{
				random:Math.random()
			},function(list){
				$.each(list,function(i,childed){
					sel_childedScene.add(new Option(childed.scene_name,childed.scene_id));
				})
			});
			*/
		}
		
		//响应设备树
		function echoDeviceTree(id,addr,no){
			document.getElementById("hidden_net_id").value = id;
			document.getElementById("txt_net_no").value = no;
			document.getElementById("txt_net_addr").value = addr;
			
			//加载待选子场景
			var sel_childDevNet = document.getElementById("sel_childDevNet");
			sel_childDevNet.length = 0;
			$.getJSON("${ctx}/gm_devnet_findChildedList.action?gm_DevNet.net_id="+id+"&user_id=${user.user_id}",{
				random:Math.random()
			},function(list){
				$.each(list,function(i,child){
					sel_childDevNet.add(new Option(child.net_no+"-"+child.net_addr,child.net_id));
				})
			});
			
			//加载已选子场景	
			/**		
			var sel_childedScene = document.getElementById("sel_childedScene");
			sel_childedScene.length = 0;
			$.getJSON("${ctx}/op_scene_findChildedList.action?op_Scene.scene_id="+id+"&user_id=${user.user_id}",{
				random:Math.random()
			},function(list){
				$.each(list,function(i,childed){
					sel_childedScene.add(new Option(childed.scene_name,childed.scene_id));
				})
			});
			*/
		}
		
		//保存
		function doSubmit(){
			var net_id = document.getElementById("hidden_net_id").value;
			var sel_childed = document.getElementById("sel_childedDevNet");
			var net_id_list = "";
			if (sel_childed.length > 0) {
				for (i = 0; i < sel_childed.length; i++) {// 获取控件下所有元素个数
					net_id_list = net_id_list + (sel_childed.options[i].value + ",");
				}
				net_id_list = net_id_list.substr(0,net_id_list.length-1);
			}else{
				alert("请选择要删除的子网络");
			}
				
			$.getJSON("${ctx}/gm_devnet_deleteChild.action?gm_DevNet.net_id="+net_id+"&net_id_list="+net_id_list,{
				random:Math.random()
			},function(suc){
				if(suc){
					alert("操作已成功");
				}
			});
		}	
	</script>
  </head>

  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">  
  	  <!-- 直接用删除关联改的，可能命名不太规范，就把左侧那个下拉列表加载的东西改了下 -->
  <form action="#">
	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="javascript:window.location.href='${ctx }/gm_devnet_page.action?page.pageSize=12&gm_DevNet.net_addr='">网络信息管理</a> 》
  	<a href="#">删除网络关联</a>  
	   	<input disabled="disabled" type="hidden" id="hidden_net_id"/><br/>
	  	<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
			<tr class="list_head">
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td style="width:12px; border-right: 0px;">&nbsp;</td>
				<td>网络编号：</td>
				<td>
					<input disabled="disabled" type="text" id="txt_net_no"/>
				</td>
			</tr>
			<tr>
				<td style="width:12px; border-right: 0px;">&nbsp;</td>
				<td>网络地址：</td>
				<td>
					<input disabled="disabled" type="text" id="txt_net_addr"/>
				</td>
			</tr>	
			<tr>
				<td colspan="3">子网络：</td>
			</tr>
			<tr>
				<td colspan="3">
					<table>
			  			<tr>
							<td align="right" height="250">
								<select id="sel_childDevNet" name="sel_childDevNet"
									size="16" multiple="multiple"
									style="width: 150px; font-size: 12px; height: 100%;background: white;">
								</select>
							</td>
							<td nowrap="nowrap">
								<table width="100%" border="0">
									<tr>
										<td style="border: 0px;">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td align="center">
											<input type="button" name="toCommit" value=" 部分添加 " id="btnQuery" style="font: bold;"
												class="bigbtnStyle" onclick="javascript:moveOpt(this.form.sel_childDevNet,this.form.sel_childedDevNet);" />
										</td>
									</tr>
									<tr>
										<td align="center">
											<input type="button" value=" 部分删除" id="toRollback" style="font: bold;"
												class="bigbtnStyle" onclick="del()" />
										</td>
									</tr>
									<tr>
										<td align="center">
											<input type="button" value=" 全部添加 " name="allToSelCols" id="toRollback" style="font: bold;"
												class="bigbtnStyle" onclick="javascript:selectAll(this.form.sel_childDevNet);moveOpt(this.form.sel_childDevNet,this.form.sel_childedDevNet);" />
										</td>
									</tr>
									<tr>
										<td align="center">
											<input type="button" value=" 全部删除 " name="allToAllCols" id="toRollback" style="font: bold;"
												class="bigbtnStyle" onclick="delAllOtherOption()" />
										</td>
									</tr>
									<!-- 
									<tr>
										<td align="center">
											
										</td>
									</tr>
									 -->
								</table>
							</td>
							<td height="250" align="left">
								<select id="sel_childedDevNet" name="sel_childedDevNet"
									style="width: 150px; font-size: 12px;background: white;" size="16"
									multiple="multiple">
								</select>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
			<tr>
				<td colspan="3" align="center">
				    <input type="button" value="提 交" class="bigbtnStyle" style="font: bold;" onclick="doSubmit()" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="返 回" onclick="window.location.href='${ctx }/gm_devnet_page.action?page.pageSize=12&gm_DevNet.net_addr='">
				</td>
			</tr>
	  	</table>
		</form> 
	 </body>
</html>
