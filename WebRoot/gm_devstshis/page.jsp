<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>智能设备状态历史管理</title>    
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
	<script type="text/javascript" src="${ctx }/js/date_sfm.js"></script>
	<script type="text/javascript">
		//分页查询
		//0704 UP Wang_Yuliang
		//0707 UP Wang_Yuliang
		function gotoPage(pageNo){
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
			var hid_beginTime = document.getElementById("hid_beginTime").value;
			var hid_endTime = document.getElementById("hid_endTime").value;
			var hid_scene_id = document.getElementById("hid_scene_id").value;
			var url = "${ctx}/gm_devstshis_page.action?"+ "d="+Math.random()+"&page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&hid_condition="+encodeURI(hid_condition)+"&hid_value="+encodeURI(hid_value);

			if(hid_beginTime!=''){
				url = url + "&beginTime=" + encodeURI(hid_beginTime);
			}
			if(hid_endTime!=''){
				url = url + "&endTime=" + encodeURI(hid_endTime);
			}
			if(hid_scene_id!=''){
				url = url + "&scene_id=" + encodeURI(hid_scene_id);
			}
			//alert(url);
			window.location.href = url;
		}
		//响应场景树
		//0704 UP Wang_Yuliang
		//0707 UP Wang_Yuliang
		function echoSceneTree(id,name,no){
			document.getElementById("hid_scene_id").value = id;
			gotoPage(1);
		}
		
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;"> 
  	<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
  	<a href="#">智能设备状态历史管理</a> </br>
           查询条件:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
          	<option value="-1">-- 请选择 --</option>
          	<option value="dev_addr">设备地址</option>
          	<option value="dev_name">设备名称</option>
          </select>
          <input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
           值:<input id="sel_value" name="sel_value" type="text" value="${hid_value }">           	
           	<input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
           	开始时间:<input id="txt_beginTime" value="<fmt:formatDate value='${beginTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly="readonly" onfocus="setday(this,this)"/>
           			<input type="hidden" id="hid_beginTime" value="<fmt:formatDate value='${beginTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
           	结束时间:<input id="txt_endTime" value="<fmt:formatDate value='${endTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly="readonly" onfocus="setday(this,this)"/>
           			<input type="hidden" id="hid_endTime" value="<fmt:formatDate value='${endTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
    		<input type="hidden" id="hid_scene_id" value="${scene_id }"/>
    		<input type="button" onclick="document.getElementById('hid_condition').value = document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;document.getElementById('hid_beginTime').value=document.getElementById('txt_beginTime').value;document.getElementById('hid_endTime').value=document.getElementById('txt_endTime').value;document.getElementById('hid_scene_id').value='';gotoPage(1)" value="查询"/>
			<br/>
    
  	<table cellpadding="0" cellspacing="0" width="1840" class="senfe1">
		<tr class="list_head">    		
    		<td style="width: 100px;">设备名称</td>
    		<td style="width: 180px;">设备地址</td>
			<td style="width: 100px;">注册身份</td>
			<td style="width: 100px;">运行状态</td>	
			<td style="width: 180px;">最近通讯时间</td>
			<td style="width: 180px;">设备当前时间</td>		
			<td style="width: 100px;">设备时间状态</td>
			<td style="width: 100px;">信号强度</td>
			<td style="width: 100px;">连接方式</td>		
			<td style="width: 100px;">心跳时间</td>	
			<td style="width: 100px;">上报周期</td>	
			<td style="width: 100px;">复位次数</td>
			<td style="width: 100px;">数据记录总数</td>
			<td style="width: 100px;">数据未报数量</td>
			<td style="width: 100px;">通信质量</td>
			<td style="width: 100px;">设备能量</td>
    	</tr>
    	<c:forEach var="gm_DevstsHis" items="${page.result }" varStatus="n">
    		<tr>
    			<td>${gm_DevstsHis.dev_id.dev_name }&nbsp;</td>
    			<td>${gm_DevstsHis.dev_addr }&nbsp;</td>
    			<td>${gm_DevstsHis.dest_regSts_str }&nbsp;</td>
				<td>${gm_DevstsHis.dest_workSts_str }&nbsp;</td>
				<td>${gm_DevstsHis.dest_lastCommTime_str }&nbsp;</td>
				<td>${gm_DevstsHis.dest_curTime_str }&nbsp;</td>
				<td>${gm_DevstsHis.dest_timeSts_str }&nbsp;</td>
				<td>${gm_DevstsHis.dest_sigStg }&nbsp;</td>
				<td>${gm_DevstsHis.dest_linkSts_str }&nbsp;</td>
				<td>${gm_DevstsHis.dest_commCyc }&nbsp;</td>
				<td>${gm_DevstsHis.dest_repCyc }&nbsp;</td>
				<td>${gm_DevstsHis.dest_resetNum }&nbsp;</td>
				<td>${gm_DevstsHis.dest_recData }&nbsp;</td>
				<td>${gm_DevstsHis.dest_norepData }&nbsp;</td>
				<td>${gm_DevstsHis.dest_commQuaily }&nbsp;</td>
				<td>${gm_DevstsHis.dest_vol }&nbsp;</td>
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
				</a> 
				<script type="text/javascript">
					function tiaozhuan(){
						var txt_zhuandao=document.getElementById('txt_zhuandao');
						var re_txt_zhuandao = /^[1-9]\d*$/;
						//alert(txt_zhuandao.value);
						if(!re_txt_zhuandao.test(txt_zhuandao.value)){
							//alert(11);
							return;
						}else{
							gotoPage(txt_zhuandao.value);
						}
					}
				</script>
				<a href="javascript:tiaozhuan();">跳转</a>
				到第
				<input id="txt_zhuandao" value="${page.pageNo}" style="width: 100px;"/>
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
