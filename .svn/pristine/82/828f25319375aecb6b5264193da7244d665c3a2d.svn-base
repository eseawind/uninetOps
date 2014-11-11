<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.unism.util.*"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>智能设备配置管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	
	<script type="text/javascript">
		function opdg1(addr)
		{
			document.getElementById("txt").value=addr;
    		var DG = new J.dialog({btnBar:false,maxBtn:false,iconTitle:false,cover:true,title:'查询信息',width:'700',height:'480',id:'test2',page:"gm_check/gm_devsts_msg.jsp"});
    		DG.ShowDialog();
		}	
		
		//配置扩展信息
		function opdg_extform(dev_id){
    		var DG_EXT = new J.dialog({btnBar:false,maxBtn:false,iconTitle:false,cover:true,title:'',width:'700',height:'480',id:'test2',page:"${ctx}/gm_devsts_toExtform.action?gm_Devsts.dev_id.dev_id="+dev_id});
    		DG_EXT.ShowDialog();
		}	
		
		function opdg_devState(dev_id){
    		var DG_EXT = new J.dialog({btnBar:false,maxBtn:false,iconTitle:false,cover:true,title:'',width:'620',height:'230',id:'test3',page:"${ctx}/gm_devsts_toDevState.action?gm_Devsts.dev_id.dev_id="+dev_id});
    		DG_EXT.ShowDialog();
		}
	</script>
	
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			document.getElementById("hid_no").value = 1;
			document.getElementById("hid_scene_id").value = id;
			document.getElementById("hid_searchOrTree").value = "tree";			
			page();	
		}
		
		//分页查询		
		function page(){
			var scene_id = document.getElementById("hid_scene_id").value;
			var no = document.getElementById("hid_no").value;
			var size = document.getElementById("hid_size").value;
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
			var url = "${ctx}/gm_devsts_page.action?searchOrTree="+document.getElementById("hid_searchOrTree").value+"&scene_id="+scene_id+"&hid_condition="+hid_condition+"&hid_value="+hid_value+"&no="+no+"&size="+size;
			//alert(url);
			$.getJSON(url,{
					random:Math.random()
				},function(page){
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
					var sel_gotoPage = document.getElementById('sel_gotoPage');
					sel_gotoPage.options.length = 0;
					if(page.count>0){
						for(var i=1;i<=pageC;i++){
							var op = new Option(i,i);
							if(i==page.no){
								op.selected = true;
							}
							sel_gotoPage.options.add(op);
						}
					}
					
					document.getElementById("div_list").innerHTML = "";
					var inner = "<table cellpadding=\"0\" cellspacing=\"0\" width=\"2200\" class=\"senfe1\">";
					inner += "<tr class=\"list_head\">";
					inner += "<td style=\"width: 100px;\">最新数据</td>"	;
	    			inner += "<td style=\"width: 100px;\">设备地址</td>";
	    			inner += "<td style=\"width: 100px;\">注册身份</td>";
					inner += "<td style=\"width: 100px;\">运行状态</td>";	
	    			inner += "<td style=\"width: 180px;\">最近通讯时间</td>";			
					inner += "<td style=\"width: 180px;\">设备当前时间</td>";
					inner += "<td style=\"width: 100px;\">设备时间状态</td>";		
					inner += "<td style=\"width: 100px;\">信号强度</td>";	
					inner += "<td style=\"width: 100px;\">连接方式</td>";	
					inner += "<td style=\"width: 100px;\">上报周期</td>";
					inner += "<td style=\"width: 100px;\">采集周期</td>";	
					inner += "<td style=\"width: 100px;\">存储周期</td>";	
					inner += "<td style=\"width: 100px;\">心跳时间（秒）</td>";	
					inner += "<td style=\"width: 100px;\">复位次数</td>";	
					inner += "<td style=\"width: 100px;\">数据记录总数</td>";
					inner += "<td style=\"width: 100px;\">数据未报条数</td>";	
					inner += "<td style=\"width: 100px;\">软件版本</td>";	
					inner += "<td style=\"width: 100px;\">硬件版本</td>";					
					inner += "<td style=\"width: 100px;\">通讯质量</td>";	
					inner += "<td style=\"width: 100px;\">设备能量</td>";	
	    			inner += "</tr>";	
	    			for(var i=0;i<page.result.length;i++){
	    				var row = page.result[i];
	    				inner += "<tr>";
	    				inner += "<td align=\"center\"><BUTTON onclick=\"opdg1('"+row.net_addr+"')\">查看</BUTTON></td>";
	    				inner += "<td><a href=\"javascript:opdg_extform('"+ row.dev_id +"')\">"+row.net_addr+"&nbsp;</td>";
	    				inner += "<td>";
	    				//alert(row.dest_regSts);
	    				//alert(row.dest_regSts==0);
	    				if(row.dest_regSts=='0'){
	    					inner += "未注册";
	    				}else if(row.dest_regSts=='1'){
	    					inner += "合法注册";
	    				}else if(row.dest_regSts=='2'){
	    					inner += "非法注册";
	    				}	    				
	    				inner += "&nbsp;</td>";
	    				inner += "<td>";
	    				if(row.dest_workSts=='0'){
	    					inner += "<img alt=\"离线\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/gray_dev.png\" style=\"cursor: pointer;\">";
	    				}else if(row.dest_workSts=='1'){
	    					inner += "<img alt=\"在线\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/green_dev.png\" style=\"cursor: pointer;\">";
	    				}else if(row.dest_workSts=='2'){
	    					inner += "<img alt=\"网关小无线模块故障\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/yellow_dev.png\" style=\"cursor: pointer;\">";
	    				}else if(row.dest_workSts=='3'){
	    					inner += "<img alt=\"小无线能量故障\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/yellow_dev.png\" style=\"cursor: pointer;\">";
	    				}else if(row.dest_workSts=='4'){
	    					inner += "<img alt=\"小无线通讯故障\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/yellow_dev.png\" style=\"cursor: pointer;\">";
	    				}else if(row.dest_workSts=='5'){
	    					inner += "<img alt=\"传感器故障\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/red_dev.png\" style=\"cursor: pointer;\">";
	    				}else if(row.dest_workSts=='6'){
	    					inner += "<img alt=\"传感器超量程\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/red_dev.png\" style=\"cursor: pointer;\">";
	    				}else if(row.dest_workSts=='7'){
	    					inner += "<img alt=\"传感器超变化率\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/red_dev.png\" style=\"cursor: pointer;\">";
	    				}else if(row.dest_workSts=='8'){
	    					inner += "<img alt=\"扩展设备异常\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/green_dev.png\" style=\"cursor: pointer;\">";
	    				}else{
	    					inner += "<img alt=\"离线\" onclick=\"opdg_devState('"+ row.dev_id +"')\" src=\"${ctx}/images/gray_dev.png\" style=\"cursor: pointer;\">";
	    				}
	    				inner += "&nbsp;</td>";
	    				inner += "<td>"+row.dest_lastCommTime+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_curTime+"&nbsp;</td>";
	    				inner += "<td>";
	    				
	    				if(row.dest_timeSts=='0'){
	    					inner += "异步";
	    				}else if(row.dest_timeSts=='1'){
	    					inner += "同步";
	    				}
	    				inner += "&nbsp;</td>";
	    				
	    				inner += "<td>"+row.dest_sigStg+"&nbsp;</td>";
	    				inner += "<td>";
	    				if(row.dest_linkSts=='1'){
	    					inner += "长连接";
	    				}else if(row.dest_linkSts=='2'){
	    					inner += "短连接";
	    				}else if(row.dest_linkSts=='3'){
	    					inner += "无连接";
	    				}
	    				inner += "&nbsp;</td>";
	    				inner += "<td>"+row.dest_repCyc+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_collectCyc+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_storageCyc+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_commCyc+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_resetNum+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_recData+"&nbsp;</td>";
	    				inner += "<td>"+row.dest_norepData+"&nbsp;</td>";	 
	    				inner += "<td>"+row.dest_softVersion+"&nbsp;</td>";	 
	    				inner += "<td>"+row.dest_hardwareVersion+"&nbsp;</td>";	    				
	    				inner += "<td>"+row.dest_commQuaily+"&nbsp;</td>";//通信质量
	    				inner += "<td>"+row.dest_vol+"&nbsp;</td>";//设备能量
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
		
		//查询条件下拉列表改编事件
		function changeValue(value){
			var sel_value_all = document.getElementById("sel_value_all");
			sel_value_all.style.display = "none";
			var sel_value_addr = document.getElementById("sel_value_addr");
			sel_value_addr.style.display = "none";
			var sel_value_work = document.getElementById("sel_value_work");
			sel_value_work.style.display = "none";
			var sel_value_reg = document.getElementById("sel_value_reg");
			sel_value_reg.style.display = "none";			 
			if(value=="dev_addr"){//如果查询条件等于设备地址时
			   	sel_value_addr.style.display = "block";
			}else if(value=="dest_workSts"){//如果查询条件等于运行状态时
			   	sel_value_work.style.display = "block";
			}else if(value=="dest_regSts"){//如果查询条件等于注册身份时
			   	sel_value_reg.style.display = "block";
			}else{
				sel_value_all.style.display = "block";
			}
		}
		
		//查询
		function search(){
			document.getElementById('hid_condition').value=document.getElementById('sel_condition').value;
			var sel_value_addr = document.getElementById("sel_value_addr");
			var sel_value_work = document.getElementById("sel_value_work");
			var sel_value_reg = document.getElementById("sel_value_reg");
			var hid_value = document.getElementById("hid_value");
			if(sel_value_addr.style.display == "block"){
				hid_value.value = sel_value_addr.value;
			}else if(sel_value_work.style.display == "block"){
				hid_value.value = sel_value_work.value;
			}else if(sel_value_reg.style.display == "block"){
				hid_value.value = sel_value_reg.value;
			}else{
				hid_value.value = sel_value_all.value;
				var hid_condition = document.getElementById("hid_condition");
				for(var i=0;i<hid_condition.length;i++){
					var op = hid_condition[i];
					if(op.value=="-1"){
						op.selected = true;
					}
				}
			}
			//document.getElementById('hid_value').value=document.getElementById('sel_value').value;
			document.getElementById("hid_no").value = 1;
			document.getElementById("hid_searchOrTree").value = "search";	
			page();
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;" >
    <a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="javascript:window.location.href=window.location.href">智能设备状态</a> <br/>	  
  	<input id="hid_scene_id" type="hidden" value="-1">
  	<input id="hid_count" type="hidden" value="0"> 
  	<input id="hid_no" type="hidden" value="1">
  	<input id="hid_size" type="hidden" value="12">
  	<input id="hid_searchOrTree" type="hidden" value="tree">
  	<input id="txt" type="hidden" value="">
  	<table cellpadding="0" cellspacing="0" border="0">
  		<tr><td style=" border: 0px;padding: 0px; font-size: 12px;">
	  查询条件:<select id="sel_condition" name="sel_condition" onchange="changeValue(this.value)">
  		<option value="-1">-- 请选择 --</option>
	  	<option value="dev_addr">设备地址</option>
	  	<option value="dest_workSts">运行状态</option>
	  	<option value="dest_regSts">注册身份</option>
	  </select>
	  <input id="hid_condition" name="hid_condition" type="hidden">
   	  值:</td><td style="border: 0px;padding: 0px; font-size: 12px;">
   	  	<input id="sel_value_all" disabled="disabled" style="width: 160px;">
   	  	<input id="sel_value_addr" type="text" style="width: 160px; display: none;">
   	  	<select id="sel_value_work" style="width: 160px; display: none;">
	  		<%
				List<Dest_workSts> dest_workStss = StaticDataManage.getDest_workStss();
				Iterator<Dest_workSts> dest_workStssIterator = dest_workStss.iterator();
			%>
			<%
				while (dest_workStssIterator.hasNext()) {
					Dest_workSts dest_workSts = dest_workStssIterator.next();
			%>
			<option value="<%=dest_workSts.getId()%>"><%=dest_workSts.getName()%></option>
			<%
				}
			%>
   	  	</select>
   	  	<select id="sel_value_reg" style="width: 160px; display: none;">
	  		<%
				List<Dest_regSts> dest_regStss = StaticDataManage.getDest_regStss();
				Iterator<Dest_regSts> dest_regStssIterator = dest_regStss.iterator();
			%>
			<%
				while (dest_regStssIterator.hasNext()) {
					Dest_regSts dest_regSts = dest_regStssIterator.next();
			%>
			<option value="<%=dest_regSts.getId()%>"><%=dest_regSts.getName()%></option>
			<%
				}
			%>
   	  	</select>   	  	
   	  	<input id="hid_value" name="hid_value" type="hidden" style="width: 160px;">
   	  	</td><td style=" border: 0px;padding: 0px; font-size: 12px;">
   	  	<input type="button" class="button1" onclick="search()" value="查询"/>
   	 </td></tr></table>
   	 
  	<div id="div_list">
  		<table cellpadding="0" cellspacing="0" width="2100" class="senfe1">
			<tr class="list_head">
	    		<td style="width: 140px;">最新数据</td>
	    		<td style="width: 100px;">注册身份</td>
	    		<td style="width: 100px;">运行状态</td>
	    		<td style="width: 180px;">最近通讯时间</td>			
				<td style="width: 180px;">设备当前时间</td>
				<td style="width: 100px;">设备时间状态</td>		
				<td style="width: 100px;">信号强度</td>
				<td style="width: 100px;">连接方式</td>
				<td style="width: 100px;">通讯质量</td>
				<td style="width: 100px;">连接方式</td>
				<td style="width: 100px;">上报周期</td>
				<td style="width: 100px;">采集周期</td>
				<td style="width: 100px;">存储周期</td>
				<td style="width: 100px;">心跳时间（秒）</td>
				<td style="width: 100px;">复位次数</td>
				<td style="width: 100px;">数据记录总数</td>
				<td style="width: 100px;">数据未报条数</td>
				<td style="width: 100px;">软件版本</td>
				<td style="width: 100px;">硬件版本</td>
				<td style="width: 100px;">通讯质量</td>
				<td style="width: 100px;">设备能量</td>
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
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
	    		<td>&nbsp;</td>
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
				<select id="sel_gotoPage" onChange="document.getElementById('hid_no').value=this.value;page();">

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
				window.parent.right.scene_tree.selectById('s_'+op_Scene.scene_id);
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
