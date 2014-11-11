<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>曲线分析</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   	<style type="text/css">
		table {
			border-collapse: collapse;
			border: 2px #bbd1fa solid;
		}
		tr{
		 height:28px;
		}
		td {
			border: 1px #bbd1fa solid;
			font-size: 12px;	
			text-align: center;		
		}	 
  	</style>
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			var hid_gtype = document.getElementById("hid_gtype");
			if(hid_gtype.value != gtype){
				if(gtype == 1){
					this.location = "${ctx }/op_scene_toDataCollect_0523.action";
				}else if(gtype == 2){
					this.location = "${ctx }/op_scene_toDataCollect.action";
				}else if(gtype == 3){
					this.location = "${ctx }/op_scene_toDataCollect.action";
				}else if(gtype == 4){
					this.location = "${ctx }/op_scene_toDataCollect.action";
				}else if(gtype == 5){
					this.location = "${ctx }/op_scene_toDataCollect.action";
				}else if(gtype == 98){
					this.location = "${ctx }/op_scene_toDataCollect_0613.action";
				}else if(gtype == 99){
					this.location = "${ctx }/op_scene_toDataCollect.action";
				}else{
					this.location = "${ctx }/op_scene_toDataCollect.action";
				}
			}else{
				data_collect(id);
			}	
		}
	
		//数据汇总
		//0531 UP
		//取 我是都取了，不要算了，不显示了 0531
		function data_collect(id){
			var div_content = document.getElementById("div_content");
			//div_content.innerHTML = "正在加载数据,请等待...";
			div_content.innerHTML = ".";
			$.getJSON("${ctx}/op_scene_json_data_collect_0613.action?op_Scene.scene_id="+id,{
				random:Math.random()
			},function(scenes){
				var inner = "";
				if(scenes.length>0){
					inner += "<table width=\"992\">";
					inner += "<tr bgcolor='#8FABDE'>";
					inner += "<td width=\"32%\">场景名称</td>";
					inner += "<td width=\"20%\">采集时间</td>";
					inner += "<td width=\"8%\">风速<br/>(MPH)</td>";
					inner += "<td width=\"8%\">风向</td>";
					inner += "<td width=\"8%\">温度<br/>(℃)</td>";
					inner += "<td width=\"8%\">湿度<br/>(%)</td>";
					inner += "<td width=\"8%\">太阳辐射<br/>(W/㎡)</td>";
					inner += "<td width=\"8%\">大气压力<br/>(Pa)</td>";
					inner += "</tr>";					
					$.each(scenes,function(i,scene){
						inner += "<tr>";
						inner += "<td width=\"32%\">"+scene.scene_name+"</td>";
						inner += "<td width=\"20%\">"+scene.time+"</td>";
						inner += "<td width=\"8%\">"+scene._1200_S+"</td>";
						inner += "<td width=\"8%\">"+scene._1200_D+"</td>";
						inner += "<td width=\"8%\">"+scene.KQSD1201_T+"</td>";
						inner += "<td width=\"8%\">"+scene.KQSD1201_H +"</td>";
						inner += "<td width=\"8%\">"+scene.RAD_10+"</td>";
						inner += "<td width=\"8%\">"+scene.PA_P+"</td>";
						inner += "</tr>";						
					})
					inner += "</table><br/><br/>";
				}else{//alert(22);
					inner += "<table width=\"992\">";
					inner += "<tr>";
					inner += "<td width=\"32%\">场景名称</td>";
					inner += "<td width=\"20%\">采集时间</td>";
					inner += "<td width=\"8%\">风速<br/>(MPH)</td>";
					inner += "<td width=\"8%\">风向</td>";
					inner += "<td width=\"8%\">温度<br/>(℃)</td>";
					inner += "<td width=\"8%\">湿度<br/>(%)</td>";
					inner += "<td width=\"8%\">太阳辐射<br/>(W/㎡)</td>";
					inner += "<td width=\"8%\">大气压力<br/>(Pa)</td>";
					inner += "</tr>";
					inner += "<tr>";
					inner += "<td width=\"32%\">没有找到指定的数据</td>";
					inner += "<td width=\"20%\">&nbsp;</td>";
					inner += "<td width=\"8%\">&nbsp;</td>";
					inner += "<td width=\"8%\">&nbsp;</td>";
					inner += "<td width=\"8%\">&nbsp;</td>";
					inner += "<td width=\"8%\">&nbsp;</td>";
					inner += "<td width=\"8%\">&nbsp;</td>";
					inner += "<td width=\"8%\">&nbsp;</td>";
					inner += "</tr>";
					inner += "</table><br/><br/>";
				}
				//alert(inner);
				div_content.innerHTML = inner;
			});	
		}
	</script>
  </head>  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<input id="hid_gtype" type="hidden" value="98">
		<div id="div_content">
			<table width="992">
				<tr>
					<td width="32%">场景名称</td>
					<td width="20%">采集时间</td>
					<td width="8%">风速<br/>(MPH)</td>
					<td width="8%">风向</td>
					<td width="8%">温度<br/>(℃)</td>
					<td width="8%">湿度<br/>(%)</td>
					<td width="8%">太阳辐射<br/>(W/㎡)</td>
					<td width="8%">大气压力<br/>(Pa)</td>
				</tr>
				<tr>
					<td width="32%">请选择场景...</td>
					<td width="20%">&nbsp;</td>
					<td width="8%">&nbsp;</td>
					<td width="8%">&nbsp;</td>
					<td width="8%">&nbsp;</td>
					<td width="8%">&nbsp;</td>
					<td width="8%">&nbsp;</td>
					<td width="8%">&nbsp;</td>
				</tr>
			</table>
			<br/><br/>
		</div>
		<script type="text/javascript">
			//默认加载
			if(window.parent.right.scene_tree.selectedNode!=null){
				var curr_node_id = this.parent.right.scene_tree.aNodes[window.parent.right.scene_tree.selectedNode].id;
				var scene_id = curr_node_id.substr(2);
				//alert(scene_id);
				$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
					random:Math.random()
				},function(op_Scene){
					echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
				});		
			}
		</script>
  </body>
</html>
