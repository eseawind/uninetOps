<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>设备上报通道信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<style type="text/css">

	</style>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">
		//响应设备树
		function echoDeviceTree(id,addr,no,type){	
			if(type == 0){
				document.getElementById("hidden_net_id").value = id;
				document.getElementById("txt_net_no").value = no;
				document.getElementById("txt_net_addr").value = addr;
				
				//加载M2M节点下的所有通道
				$.getJSON("${ctx}/Gm_DevChannel_findAllChannelByM2M_And_AlertIsMapping.action?net_id="+id+"&user_id=${user.user_id}&addr="+addr,{
					random:Math.random()
				},function(channels){
					/**
					 * @return json
					 * 			[
					 * 				{
					 * 					ch_id:'?',
					 * 					ch_no:'?',
					 * 					ch_name:'?',
					 * 					ch_chlNo:?,
					 * 					dev_collectId:
					 * 									{
					 * 										dev_id:'?',
					 * 										dev_no:'?',
					 * 										dev_name:'?',
					 * 										net_addr:'?'
					 * 									}
					 * 					devChannel:
					 * 								{
					 * 									dch_order:?,
					 * 									ch_procesStyle:?,
					 * 									ch_MemoryPeriod:?
					 * 								} 					
					 * 				}
					 * 			]
					 */
					var inner = "没有找到指定的数据";
					if(channels.length>0){
						inner = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"senfe1\">";
						inner+= "<tr class=\"list_head\">";
						inner+= "<td>通道编号</td>";
						inner+= "<td>通道名称</td>";
						inner+= "<td>所属设备(地址)</td>";
						inner+= "<td>是否已配置到设备上报配置表中</td>";
						inner+= "<td>通道顺序</td>";
						inner+= "<td>通道数据处理方式</td>";
						inner+= "<td>存储周期</td>";
						inner+= "</tr>";
						for(var i=0;i<channels.length;i++){
							var channel = channels[i];
							inner+= "<tr>";
							inner+= "<td>";
							inner+= channel.ch_no;
							inner+= "<input id=\""+channel.ch_id+"__hid_ch_id\" type=\"hidden\" name=\"hid_ch_id\" value=\""+channel.ch_id+"\">";
							if(channel.dev_collectId!=null){
								inner+= "<input id=\""+channel.ch_id+"__hid_dev_id\" name=\"hid_dev_id\" type=\"hidden\" value=\""+channel.dev_collectId.dev_id+"\"/>";
							}else{
								inner+= "<input id=\""+channel.ch_id+"__hid_dev_id\" name=\"hid_dev_id\" type=\"hidden\" value=\"-1\"/>";
							}
							inner+= "</td>";
							inner+= "<td>";
							inner+= channel.ch_name;
							inner+= "</td>"
							inner+= "<td>";
							if(channel.dev_collectId!=null){
								inner+= channel.dev_collectId.net_addr;
							}else{
								inner+= "未知";
							}
							inner+= "</td>";
							inner+= "<td>";
							if(channel.devChannel!=null){
								inner+= "<input id=\""+channel.ch_id+"__ck_isMapping\" name=\"ck_isMapping\" type=\"checkbox\" value=\""+channel.ch_id+"\" checked=\"checked\" onclick=\"ck_isMapping_onClick(this)\"/>";
							}else{
								inner+= "<input id=\""+channel.ch_id+"__ck_isMapping\" name=\"ck_isMapping\" type=\"checkbox\" value=\""+channel.ch_id+"\" onclick=\"ck_isMapping_onClick(this)\"/>";
							}
							inner+= "</td>";
							inner+= "<td>";
							if(channel.devChannel!=null){
								inner+= "<select id=\""+channel.ch_id+"__sel_dch_order\" name=\"sel_dch_order\">";
							}else{
								inner+= "<select id=\""+channel.ch_id+"__sel_dch_order\" name=\"sel_dch_order\" disabled=\"disabled\">";
							}								
							inner+= "<option value=\"-1\">-- 请选择 --</option>";
							if(channel.devChannel!=null){
								for(var j=0;j<channels.length;j++){
									if(channel.devChannel.dch_order == (j+1)){
										inner+= "<option value=\""+(parseInt(j)+1)+"\" selected=\"selected\">"+(parseInt(j)+1)+"</option>";
									}else{
										inner+= "<option value=\""+(parseInt(j)+1)+"\">"+(parseInt(j)+1)+"</option>";
									}
								}	
							}else{
								for(var j=0;j<channels.length;j++){
									inner+= "<option value=\""+(parseInt(j)+1)+"\">"+(parseInt(j)+1)+"</option>";
								}
							}
							inner+= "</select>";
							inner+= "</td>";
							inner+= "<td>";
							if(channel.devChannel!=null){
								inner+= "<select id=\""+channel.ch_id+"__sel_ch_procesStyle\" name=\"sel_ch_procesStyle\">";
							}else{
								inner+= "<select id=\""+channel.ch_id+"__sel_ch_procesStyle\" name=\"sel_ch_procesStyle\" disabled=\"disabled\">";
							}
							inner+= "<option value=\"-1\">-- 请选择 --</option>";
							if(channel.devChannel!=null){
								if(channel.devChannel.ch_procesStyle==0){
									inner+= "<option value=\"0\" selected=\"selected\">表示不处理</option>";
									inner+= "<option value=\"1\">校正后存储</option>";
									inner+= "<option value=\"2\">校正后定时存储</option>";
									inner+= "<option value=\"3\">校正后存储-设备能量状态显示</option>";
									inner+= "<option value=\"4\">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>";
								}
								if(channel.devChannel.ch_procesStyle==1){
									inner+= "<option value=\"0\">表示不处理</option>";
									inner+= "<option value=\"1\" selected=\"selected\">校正后存储</option>";
									inner+= "<option value=\"2\">校正后定时存储</option>";
									inner+= "<option value=\"3\">校正后存储-设备能量状态显示</option>";
									inner+= "<option value=\"4\">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>";
								}
								if(channel.devChannel.ch_procesStyle==2){
									inner+= "<option value=\"0\">表示不处理</option>";
									inner+= "<option value=\"1\">校正后存储</option>";
									inner+= "<option value=\"2\" selected=\"selected\">校正后定时存储</option>";
									inner+= "<option value=\"3\">校正后存储-设备能量状态显示</option>";
									inner+= "<option value=\"4\">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>";
								}
								if(channel.devChannel.ch_procesStyle==3){
									inner+= "<option value=\"0\">表示不处理</option>";
									inner+= "<option value=\"1\">校正后存储</option>";
									inner+= "<option value=\"2\">校正后定时存储</option>";
									inner+= "<option value=\"3\" selected=\"selected\">校正后存储-设备能量状态显示</option>";
									inner+= "<option value=\"4\">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>";
								}
								if(channel.devChannel.ch_procesStyle==4){
									inner+= "<option value=\"0\">表示不处理</option>";
									inner+= "<option value=\"1\">校正后存储</option>";
									inner+= "<option value=\"2\">校正后定时存储</option>";
									inner+= "<option value=\"3\">校正后存储-设备能量状态显示</option>";
									inner+= "<option value=\"4\" selected=\"selected\">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>";
								}
								if(channel.devChannel.ch_procesStyle==5){
									inner+= "<option value=\"0\">表示不处理</option>";
									inner+= "<option value=\"1\">校正后存储</option>";
									inner+= "<option value=\"2\">校正后定时存储</option>";
									inner+= "<option value=\"3\">校正后存储-设备能量状态显示</option>";
									inner+= "<option value=\"4\">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>";
								}
								if(channel.devChannel.ch_procesStyle==6){
									inner+= "<option value=\"0\">表示不处理</option>";
									inner+= "<option value=\"1\">校正后存储</option>";
									inner+= "<option value=\"2\">校正后定时存储</option>";
									inner+= "<option value=\"3\">校正后存储-设备能量状态显示</option>";
									inner+= "<option value=\"4\">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>";
									
								}
							}else{	
								inner+= "<option value=\"0\">表示不处理</option>";
								inner+= "<option value=\"1\">校正后存储</option>";
								inner+= "<option value=\"2\">校正后定时存储</option>";
								inner+= "<option value=\"3\">校正后存储-设备能量状态显示</option>";
								inner+= "<option value=\"4\">校正后状态为停不存储，其它定时存储-控制设备状态返回显示</option>";
							}	
							inner+= "</select>";
							inner+= "</td>";
							inner+= "<td>";
							if(channel.devChannel!=null){
								inner+= "<input id=\""+channel.ch_id+"__txt_ch_MemoryPeriod\" name=\"txt_ch_MemoryPeriod\" type=\"text\" size=\"4\" value=\""+channel.devChannel.ch_MemoryPeriod+"\">秒";
							}else{
								inner+= "<input id=\""+channel.ch_id+"__txt_ch_MemoryPeriod\" name=\"txt_ch_MemoryPeriod\" type=\"text\" size=\"4\" disabled=\"disabled\">秒";
							}
							inner+= "</td>";	
							inner+= "</tr>";
						}
						inner+= "</table>";	
					}
					document.getElementById("div_list").innerHTML = inner;
				});
			}else{
				alert("点选设备树，选择【远程网 M2M】类型的网络节点！");
			}
		}
		
		function ck_isMapping_onClick(ele){
			if(ele.checked){
				document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__sel_dch_order").disabled=false;
				document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__sel_ch_procesStyle").disabled=false;
				document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__txt_ch_MemoryPeriod").disabled=false;
			}else{
				document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__sel_dch_order").disabled=true;
				document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__sel_ch_procesStyle").disabled=true;
				document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__txt_ch_MemoryPeriod").disabled=true;
			}
		}
		
		//表单提交
		function doSubmit(){
			var ch_id_arr = "";
			var uc_ch_id_arr = "";
			var dev_id_arr = "";
			var dch_order_arr = "";
			var ch_procesStyle_arr = "";
			var ch_memoryPeriod_arr = "";
			var eles = document.getElementsByName("ck_isMapping");
			for(var i=0;i<eles.length;i++){
				var ele = eles[i];
				if(ele.checked){
					var ch_id = document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__hid_ch_id").value;	
					var dev_id = document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__hid_dev_id").value;
					var dch_order = document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__sel_dch_order").value;
					var ch_procesStyle = document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__sel_ch_procesStyle").value;
					var ch_MemoryPeriod = document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__txt_ch_MemoryPeriod").value;
					if(dev_id.length==0){
						alert("保存失败 所属设备未知");
						return;
					}
					if(dch_order==-1){
						alert("请选择通道顺序");
						return;
					}
					if(ch_procesStyle==-1){
						alert("请选择通道数据处理方式");
						return;
					}
					var re_ch_MemoryPeriod = /^[1-9]\d*$/;
					if(!re_ch_MemoryPeriod.test(ch_MemoryPeriod)){
						alert("存储周期只能是正整数");
						return;
					}	
					ch_id_arr += ch_id+",";
					dev_id_arr += dev_id+",";
					dch_order_arr += dch_order+",";
					ch_procesStyle_arr += ch_procesStyle+",";
					ch_memoryPeriod_arr += ch_MemoryPeriod+",";					
				}else{
					var uc_ch_id = document.getElementById(ele.id.substr(0,ele.id.indexOf("__"))+"__hid_ch_id").value;	
					uc_ch_id_arr += uc_ch_id+",";
				}
			}
			if(ch_id_arr.length>0){
				ch_id_arr = ch_id_arr.substr(0,ch_id_arr.length-1);
			}
			if(uc_ch_id_arr.length>0){
				uc_ch_id_arr = uc_ch_id_arr.substr(0,uc_ch_id_arr.length-1);
			}
			if(dev_id_arr.length>0){
				dev_id_arr = dev_id_arr.substr(0,dev_id_arr.length-1);
			}
			if(dch_order.length>0){
				dch_order = dch_order.substr(0,dch_order.length-1);
			}
			if(ch_procesStyle_arr.length>0){
				ch_procesStyle_arr = ch_procesStyle_arr.substr(0,ch_procesStyle_arr.length-1);
			}
			if(ch_memoryPeriod_arr.length>0){
				ch_memoryPeriod_arr = ch_memoryPeriod_arr.substr(0,ch_memoryPeriod_arr.length-1);
			}
			
			$.getJSON("${ctx}/Gm_DevChannel_manage.action?ch_id_arr="+ch_id_arr+"&uc_ch_id_arr="+uc_ch_id_arr+"&dev_id_arr="+dev_id_arr+"&dch_order_arr="+dch_order_arr+"&ch_procesStyle_arr="+ch_procesStyle_arr+"&ch_memoryPeriod_arr="+ch_memoryPeriod_arr+"&net_id="+document.getElementById("hidden_net_id").value,{
				random:Math.random()
			},function(message){
				alert(message.value);
			});
		}
	</script>
  </head>

	<body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;text-align: left;">
	<form action="#">
		<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》
		<a href="#">设备上报通道配置管理</a> 》
		<a href="#">管理</a> </br>
	   	<input disabled="disabled" type="hidden" id="hidden_net_id"/><br/>
	  	网络编号：<input disabled="disabled" type="text" id="txt_net_no"/><br/>
	  	网络地址：<input disabled="disabled" type="text" id="txt_net_addr"/><br/>
	  	<input type="button" value="提 交" onclick="if(document.getElementById('hidden_net_id').value!=''){doSubmit();}else{alert('请选择M2M网络节点');}">
	  	<!-- 显示通道的表格 -->
	  	<div id="div_list">
	  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="senfe1">
	  		<tr class="list_head">
	  			<td>通道编号</td>
	  			<td>通道名称</td>
	  			<td>所属设备(地址)</td>
	  			<td>是否已配置到设备上报配置表中</td>
	  			<td>通道顺序</td>
	  			<td>通道数据处理方式</td>
	  			<td>存储周期</td>
	  		</tr>
	  		<tr>
	  			<td>
	  				&nbsp;
	  			</td>
	  			<td>&nbsp;</td>
	  			<td>
					&nbsp;
				</td>
	  			<td>
					&nbsp;
				</td>
	  			<td>
	  				&nbsp;
	  			</td>
	  			<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
	  		</tr>
	  	</table>
	  	</div>
	</form> 
	</body>
</html>
