<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>智能设备状态</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			document.getElementById("hid_scene_id").value = id;
			document.getElementById("hid_dev_addr").value = document.getElementById("txt_dev_addr").value;
			page();	
		}
		
		//分页查询		
		function page(){
			var scene_id = document.getElementById("hid_scene_id").value;
			var dev_addr = document.getElementById("hid_dev_addr").value;
			//alert(dev_addr);
			var no = document.getElementById("hid_no").value;
			var size = document.getElementById("hid_size").value;
			$.getJSON("${ctx}/gm_device_manage_page.action?scene_id="+scene_id+"&dev_addr="+dev_addr+"&no="+no+"&size="+size,{
					random:Math.random()
				},function(page){//alert(11);
					document.getElementById("hid_count").value = page.count;
					var count = document.getElementById("hid_count").value;
					var pageC = 1;
					if((count%document.getElementById("hid_size").value)>0){
						pageC = (count/document.getElementById("hid_size").value)+1;
					}else{
						if(count>0){
							pageC = (count/document.getElementById("hid_size").value);
						}	
					}
					//alert((pageC+"").indexOf('.'));
					if((pageC+"").indexOf('.')!=-1){	
						pageC = (pageC+"").substr(0,(pageC+"").indexOf('.'));
					}		
					document.getElementById("sp_l").innerHTML = "共"+page.count+"条记录，	每页"+page.size+"条，当前第"+page.no+"页，	共"+pageC+"页";
					
					document.getElementById("div_list").innerHTML = "";
					var inner = "<table cellpadding=\"0\" cellspacing=\"0\" width=\"2000\" class=\"senfe1\">";
					inner += "<tr class=\"list_head\">";
					inner += "<td style=\"width: 100px;\">设备名称</td>";
	    			inner += "<td style=\"width: 100px;\">设备地址</td>";
	    			inner += "<td style=\"width: 100px;\">注册身份</td>";
	    			inner += "<td style=\"width: 200px;\">最近通讯时间</td>";			
					inner += "<td style=\"width: 200px;\">设备当前时间</td>";
					inner += "<td style=\"width: 100px;\">设备时间状态</td>";		
					inner += "<td style=\"width: 100px;\">运行状态</td>";	
					inner += "<td style=\"width: 100px;\">信号强度</td>";	
					inner += "<td style=\"width: 100px;\">通讯质量</td>";	
					inner += "<td style=\"width: 100px;\">连接方式</td>";	
					inner += "<td style=\"width: 100px;\">心跳时间（秒）</td>";	
					inner += "<td style=\"width: 100px;\">上报周期（短连接）</td>";	
					inner += "<td style=\"width: 100px;\">设备能量</td>";	
					inner += "<td style=\"width: 100px;\">复位次数</td>";	
					inner += "<td style=\"width: 100px;\">数据记录总数</td>";	
					inner += "<td style=\"width: 100px;\">标记删除</td>";
					inner += "<td style=\"width: 100px;\">操作</td>";
	    			inner += "</tr>";	
	    			for(var i=0;i<page.result.length;i++){
	    				var row = page.result[i];
	    				inner += "<tr>";
	    				inner += "<td>"+row.dev_name+"&nbsp;</td>";
	    				inner += "<td>"+row.net_addr+"&nbsp;</td>";
	    				inner += "<td>";
	    				if(row.dest_regSts==0){
	    					inner += "未注册";
	    				}else if(row.dest_regSts==1){
	    					inner += "合法注册";
	    				}else if(row.dest_regSts==2){
	    					inner += "非法注册";
	    				}	    				
	    				inner += "&nbsp;</td>";
	    				inner += "<td>"+row.dest_lastCommTime+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_curTime+"&nbsp;</td>";
	    				inner += "<td>";
	    				if(row.dest_timeSts==0){
	    					inner += "异步";
	    				}else if(row.dest_timeSts==1){
	    					inner += "同步";
	    				}
	    				inner += "&nbsp;</td>";
	    				inner += "<td>";
	    				if(row.dest_workSts==0){
	    					inner += "离线";
	    				}else if(row.dest_workSts==1){
	    					inner += "在线";
	    				}
	    				inner += "&nbsp;</td>";
	    				inner += "<td>"+row.dest_sigStg+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_commQuaily+"&nbsp;</td>";
	    				inner += "<td>";
	    				if(row.dest_linkSts==1){
	    					inner += "长连接";
	    				}else if(row.dest_linkSts==2){
	    					inner += "短连接";
	    				}else if(row.dest_linkSts==2){
	    					inner += "临时长连接";
	    				}
	    				inner += "&nbsp;</td>";
	    				inner += "<td>"+row.dest_commCyc+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_repCyc+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_vol+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_resetNum+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_recData+"&nbsp;</td>";
	    				inner += "<td>";
	    				if(row.dev_state==1){
	    					inner += "未删除";
	    				}else{
	    					inner += "已删除";
	    				}
	    				inner += "&nbsp;</td>";
	    				inner += "<td><a href=\"javascript:del('"+row.dev_id+"')\">删除</a>&nbsp;</td>";
	    				inner += "</tr>";
	    			}
	    			inner += "</table>";
	    			document.getElementById("div_list").innerHTML = inner;	    			
				}
			);	
		}
		
		//前一页
		function bb(){
			var no = document.getElementById("hid_no").value;
			if(no>1){
				document.getElementById("hid_no").value = (no-1);
			}else{
				document.getElementById("hid_no").value = 1;
			}
			page();
		}
		
		//后一页
		function nn(){
			var count = document.getElementById("hid_count").value;
			var no = document.getElementById("hid_no").value;
			var pageC = 1;
			if((count%document.getElementById("hid_size").value)>0){
				pageC = (count/document.getElementById("hid_size").value)+1;
			}else{
				if(count>0){
					pageC = (count/document.getElementById("hid_size").value);
				}	
			}
			if((pageC+"").indexOf('.')!=-1){	
				pageC = (pageC+"").substr(0,(pageC+"").indexOf('.'));
			}	
			if(no<pageC){
				document.getElementById("hid_no").value = (parseInt(no)+1);
			}else{				
				document.getElementById("hid_no").value = pageC;
			}
			page();
		}
		
		//最后一页
		function zz(){
			var count = document.getElementById("hid_count").value;
			var pageC = 1;
			if((count%document.getElementById("hid_size").value)>0){
				pageC = (count/document.getElementById("hid_size").value)+1;
			}else{
				if(count>0){
					pageC = (count/document.getElementById("hid_size").value);
				}	
			}
			if((pageC+"").indexOf('.')!=-1){	
				pageC = (pageC+"").substr(0,(pageC+"").indexOf('.'));
			}		
			document.getElementById("hid_no").value = pageC;
			page();
		}
		
		//删除
		function del(dev_id){
			if(confirm('是否确定删除？如果确定删除的话，与此设备相关的所有信息和数据将彻底删除，不可恢复。')){
				$.getJSON("${ctx}/gm_device_manage_del.action?gm_Device.dev_id="+dev_id,{
						random:Math.random()
					},function(isSuc){
						alert(isSuc.message);
						page();
					}
				);
			}			
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="javascript:window.location.href=window.location.href">设备维护</a> <br/>
  	设备地址:
  	<input type="text" id="txt_dev_addr" name="txt_dev_addr" value="${hid_dev_addr }"/>
  	<input type="hidden" id="hid_dev_addr" name="hid_dev_addr" value="${hid_dev_addr }"/>
  	<input type="button" value="查 询" onclick="document.getElementById('hid_dev_addr').value = document.getElementById('txt_dev_addr').value;page();">   	
  	<input id="hid_scene_id" type="hidden" value="-1">
  	<input id="hid_count" type="hidden" value="0"> 
  	<input id="hid_no" type="hidden" value="1">
  	<input id="hid_size" type="hidden" value="12">
  	<div id="div_list">
  		<table cellpadding="0" cellspacing="0" width="2000" class="senfe1">
			<tr class="list_head">
	    		<td style="width: 100px;">设备名称</td>
	    		<td style="width: 100px;">设备地址</td>
	    		<td style="width: 100px;">注册身份</td>
	    		<td style="width: 200px;">最近通讯时间</td>			
				<td style="width: 200px;">设备当前时间</td>
				<td style="width: 100px;">设备时间状态</td>		
				<td style="width: 100px;">运行状态</td>
				<td style="width: 100px;">信号强度</td>
				<td style="width: 100px;">通讯质量</td>
				<td style="width: 100px;">连接方式</td>
				<td style="width: 100px;">心跳时间（秒）</td>
				<td style="width: 100px;">上报周期（短连接）</td>
				<td style="width: 100px;">设备能量</td>
				<td style="width: 100px;">复位次数</td>
				<td style="width: 100px;">数据记录总数</td>
				<td style="width: 100px;">标记删除</td>
				<td style="width: 100px;">操作</td>
	    	</tr>
	    	<tr>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td><a href="#">删除</a></td>
	    	</tr>
	    </table>		
  	</div>  
  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="5" align="left" valign="middle">
				<span id="sp_l" style="padding-left: 10px; font-size: 12px;">
					共xx条记录，
					每页xx条，
					当前第xx页，
					共xx页
				</span>
			</td>
			<td colspan="6" align="right" valign="middle"
				style="padding-right: 10px; font-size: 12px;"
				class="more">
				<a href="javascript:document.getElementById('hid_no').value=1;page();">首&nbsp;&nbsp;页</a>
				<a href="javascript:bb();">
					上一页
				</a>
				<a href="javascript:nn();">
					下一页
				</a>
				<a href="javascript:zz();">
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
	  	if('${op_Scene.scene_id}'!=''){	
			$.getJSON("${ctx}/op_scene_json_findById.action?op_Scene.scene_id=${op_Scene.scene_id}",{
				random:Math.random()
			},function(op_Scene){
				//alert(window.parent.scene_tree.selectedNode);
				window.parent.scene_tree.selectById('s_'+op_Scene.scene_id);
				echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
			});	
		}else{	//alert(11);
			//默认加载	
			//alert(this.parent.scene_tree.selectedNode!=null);
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
		}
  	</script>  	 
  </body>
</html>
