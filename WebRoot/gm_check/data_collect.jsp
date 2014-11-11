<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>数据汇总</title>
    
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
			data_collect(id);
		}
	
		//数据汇总
		//0531 UP
		//取 我是都取了，不要算了，不显示了 0531
		function data_collect(id){
			var div_content = document.getElementById("div_content");
			//div_content.innerHTML = "正在加载数据,请等待...";
			div_content.innerHTML = ".";
			$.getJSON("${ctx}/op_chtypeoperate_data_collect.action?scene_id="+id+"&user_id=${userid}",{
				random:Math.random()
			},function(gtypes){//alert(11);
				/**
				 * 数据汇总
				 * @return json
				 * 			[	
				 * 				{
				 * 					scene_gtype:'',
				 * 					head:
				 * 						[
				 * 							'xx','xxx','xxxx'
				 * 						],
				 * 					scenes:
				 * 						[ 		
				 * 							null,				
				 * 							{
				 * 								scene_name:'',
				 * 								time:'yyyy-MM-dd HH:mm:ss',
				 * 								data:
				 * 									[
				 * 										'21','23','','23'
				 * 									]
				 * 							},
				 * 							...
				 * 						]
				 * 				},
				 *				...
				 * 			]
				 * @author Wang_Yuliang
				 */
				var inner = "";
				if(gtypes.length>0){
					for(var i=0;i<gtypes.length;i++){
						var gtype = gtypes[i];
						//inner += gtype.scene_gtype+"<br/>";
						inner += "<table>";
						inner += "<tr bgcolor='#8FABDE'>";
						for(var h=0;h<gtype.head.length;h++){
							var head = gtype.head[h];						
							inner += "<td width=\"150\">"+head+"&nbsp;</td>";
						}
						inner += "</tr>";					
						for(var s=0;s<gtype.scenes.length;s++){
							var scene = gtype.scenes[s];
							if(scene!=null){
								inner += "<tr>";
								inner += "<td>"+scene.scene_name+"&nbsp;</td>";
								inner += "<td>"+scene.time+"&nbsp;</td>";
								for(var d=0;d<scene.data.length;d++){
									var value = scene.data[d];
									inner += "<td>"+value+"&nbsp;</td>";
								}							
								inner += "</tr>";
							}							
						}
						inner += "</table><br/><br/>";	
					}
				}else{
					//inner += "<table width=\"992\">";
					//inner += "<tr>";
					//inner += "<td width=\"40%\">场景名称</td>";
					//inner += "<td width=\"20%\">采集时间</td>";
					//inner += "<td width=\"10%\">溶解氧<br/>(mg/L)</td>";
					//inner += "<td width=\"10%\">溶解氧最大值<br/>(mg/L)</td>";
					//inner += "<td width=\"10%\">溶解氧最小值<br/>(mg/L)</td>";
					//inner += "<td width=\"10%\">水温<br/>(℃)</td>";
					//inner += "<td width=\"8%\">水深<br/>(m)</td>";
					//inner += "<td width=\"8%\">pH</td>";
					//inner += "<td width=\"8%\">电导率<br/>(ms/cm)</td>";
					//inner += "<td width=\"8%\">浊度<br/>(NTU)</td>";
					//inner += "<td width=\"8%\">叶绿素<br/>(ug/L)</td>";
					inner += "</tr>";
					inner += "<tr>";
					inner += "<td width=\"40%\">没有找到指定的数据</td>";
					inner += "<td width=\"20%\">&nbsp;</td>";
					//inner += "<td width=\"10%\">&nbsp;</td>";
					//inner += "<td width=\"10%\">&nbsp;</td>";
					//inner += "<td width=\"10%\">&nbsp;</td>";
					//inner += "<td width=\"10%\">&nbsp;</td>";
					//inner += "<td width=\"8%\">&nbsp;</td>";
					//inner += "<td width=\"8%\">&nbsp;</td>";
					//inner += "<td width=\"8%\">&nbsp;</td>";
					//inner += "<td width=\"8%\">&nbsp;</td>";
					//inner += "<td width=\"8%\">&nbsp;</td>";
					//inner += "</tr>";
					//inner += "</table><br/><br/>";
				}
				//alert(inner);
				div_content.innerHTML = inner;				
			});	
		}
	</script>
  </head>  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			  	<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
				<a href="javascript:window.location.href=window.location.href">数据汇总</a> <br/>
		<div id="div_content">
			<table width="992">
				<tr>
					<td width="40%">场景名称</td>
					<td width="20%">采集时间</td>
					<td width="10%">溶解氧<br/>(mg/L)</td>
					<td width="10%">溶解氧最大值<br/>(mg/L)</td>
					<td width="10%">溶解氧最小值<br/>(mg/L)</td>
					<td width="10%">水温<br/>(℃)</td>
				</tr>
				<tr>
					<td width="40%">请选择场景...</td>
					<td width="20%">&nbsp;</td>
					<td width="10%">&nbsp;</td>
					<td width="10%">&nbsp;</td>
					<td width="10%">&nbsp;</td>
					<td width="10%">&nbsp;</td>
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
