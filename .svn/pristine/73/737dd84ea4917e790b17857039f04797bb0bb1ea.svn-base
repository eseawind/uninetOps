<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	
	<script type="text/javascript">
		function opdg()
		{
    		//var dg = new J('#btn').dialog({btnBar:false,maxBtn:false,iconTitle:false,cover:true,title:'控制信息',width:'700',height:'480',id:'test2',page:"gm_DevCtrlSts/devCtrlOperateMsg.jsp"});
    		var dg = new J.dialog({id:'id',xButton:false,cover:true,maxBtn:false,iconTitle:false,title:'控制信息',width:'300',height:'240',btnBar:false, page:"gm_DevCtrlSts/devCtrlOperateMsg.jsp"}); 
    		dg.ShowDialog();
		}
		function refresh(){
			window.location.reload();
		}
	</script>
	
	<script type="text/javascript">
		function change(value){
			var html = "";
			if(value == "dect_state"){
				html = "<select id=\"queryValue\"><option value=\"1\">开启</option><option value=\"2\">关闭</option><option value=\"3\">停止</option></select>";
			}else{
				html = "<input type=\"text\" id=\"queryValue\" name=\"queryValue\" style=\"width: 200px;\" value=\"\">";
			}
			$("#values").html(html);
		}
		
		function gotoPage(pageNo){
			var queryName = document.getElementById("queryName").value;	
			var queryValue = document.getElementById("queryValue").value;
			//alert(queryName);
			//alert(queryValue);
			window.location.href = "${ctx}/Gm_DevCtrlSts_page.action?page.pageNo=" + pageNo + "&page.pageSize=10&queryName="+ queryName + "&queryValue=" + encodeURI(queryValue);
		}
		
	</script>
	
	
	<script type="text/javascript">
		var deco_result_timer = null;
		//设备控制
		function operate(dect_id,deco_type){
			document.getElementById("txt").value = dect_id;
			var deco_type_cn;
			if(deco_type == 1){
				deco_type_cn = "启动";
			}else if(deco_type == 2){
				deco_type_cn = "停止";
			}else if(deco_type == 3){
				deco_type_cn = "关";
			}else{
				alert("无效操作");
				return;
			}
			if(confirm("是否"+deco_type_cn+"设备?")){			
				$.getJSON("${ctx}/gm_devctrloperate_operate.action?dect_id="+dect_id+"&deco_type="+deco_type,{
						random:Math.random()
					},function(msm){
						if(msm == '2') {
							alert("没有找到设备！");
							return;
						}
						if(msm == '0') {
							alert("当前设备已被其他用户控制，请稍后再试！");
							return;
						}
						if(msm == '1') {
							alert("请求已发送，请等待...！");
						}
						opdg();
					});
					
			}
					
		}
	</script>
	
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<div>查询条件：<select id="queryName" onchange="change(this.value)">
  		<option value="null">请选择……</option>
  		<option value="dect_no">控制设备编号</option>
  		<option value="scene_name">设备所在场景</option>
  		<option value="dect_state">控制设备状态</option>
  	</select>
  	<input id="txt" type="hidden" value="">
  	<span id="values"><input type="text" id="queryValue" style="width: 200px" value="${queryValue }"></span>
  	<input type="button" onclick="gotoPage(1)" value="查询"></div>
    <table class="senfe1" style="font: font-size:12px;width: 100%;">
    	<tr class="list_head">
    		<td>控制设备编号</td>
    		<td>控制设备名称</td>
    		<td>设备所在场景</td>
    		<td>控制设备状态</td>
    		<td>状态变更时间</td>
    		<td>操作用户姓名</td>
    		<td>控制分类</td>
    		<td>操作</td>
    	</tr>
    	
    	<c:forEach items="${page.result}" var="gm_DevCtrlSts">
    		<tr>
    			<td>${gm_DevCtrlSts.dect_id.dect_no }&nbsp;</td>
    			<td>${gm_DevCtrlSts.dect_id.dect_name }&nbsp;</td>
    			<td>${gm_DevCtrlSts.dect_id.scene_id.scene_name }&nbsp;</td>
    			<c:choose>
    				<c:when test="${gm_DevCtrlSts.dect_state == 1 }">
    					<td>开启</td>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.dect_state == 2 }">
    					<td>停止</td>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.dect_state == 3 }">
    					<td>关闭</td>
    				</c:when>
    				<c:otherwise>
    					<td>未知</td>
    				</c:otherwise>
    			</c:choose>
    			<td>${gm_DevCtrlSts.decst_time }&nbsp;</td>
    			<td>${gm_DevCtrlSts.deco_userName }&nbsp;</td>
    			<c:choose>
    				<c:when test="${gm_DevCtrlSts.deco_sort == 1}">
    					<td>定时</td>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.deco_sort == 2}">
    					<td>自动</td>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.deco_sort == 3}">
    					<td>遥控</td>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.deco_sort == 4}">
    					<td>手动</td>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.deco_sort == 5}">
    					<td>异常保护</td>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.deco_sort == 6}">
    					<td>强制控制</td>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.deco_sort == 7}">
    					<td>手机</td>
    				</c:when>
    				<c:otherwise>
    					<td>未知</td>
    				</c:otherwise>
    			</c:choose>
    			<!-- 
    			<c:choose>
    				<c:when test="${gm_DevCtrlSts.dect_id.decttype_btnNum == 2}">
    					<c:if test="${gm_DevCtrlSts.dect_state == 2 }">
    						<td><input id="btn" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',1)" type="button" value="开启"><input type="button" disabled="disabled" value="停止">&nbsp;</td>
    					</c:if>
    					<c:if test="${gm_DevCtrlSts.dect_state == 1 }">
    						<td><input type="button" disabled="disabled" value="开启"><input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',2)" value="停止">&nbsp;</td>
    					</c:if>
    				</c:when>
    				<c:when test="${gm_DevCtrlSts.dect_id.decttype_btnNum == 3}">
    					<c:if test="${gm_DevCtrlSts.dect_state == 1 }">
    						<td><input type="button" disabled="disabled" value="开启"><input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',2)" value="停止"><input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',3)" value="关闭">&nbsp;</td>
    					</c:if>
    					<c:if test="${gm_DevCtrlSts.dect_state == 2 }">
    						<td><input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',1)" value="开启"><input type="button" disabled="disabled" value="停止"><input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',3)" value="关闭">&nbsp;</td>
    					</c:if>
    					<c:if test="${gm_DevCtrlSts.dect_state == 3 }">
    						<td><input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',1)" value="开启"><input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',2)" value="停止"><input type="button" disabled="disabled" value="关闭">&nbsp;</td>
    					</c:if>
    				</c:when>
    			</c:choose>
    			 -->
    			 <td>
    			 	<c:forEach items="${gm_DevCtrlSts.dect_id.opDevCtrlBtns}" var="opDevCtrlBtn" varStatus="n">
    			 		<c:choose>
    			 			<c:when test="${gm_DevCtrlSts.dect_state == 1 }">
    			 				<c:choose>
    			 					<c:when test="${opDevCtrlBtn.deco_type == 1 }">
    			 						<input type="button" disabled="disabled" value="${opDevCtrlBtn.dectbtn_name }">
    			 					</c:when>
    			 					<c:otherwise>
    			 						<input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',${opDevCtrlBtn.deco_type })" value="${opDevCtrlBtn.dectbtn_name }">
    			 					</c:otherwise>
    			 				</c:choose>
    			 			</c:when>
    			 			<c:when test="${gm_DevCtrlSts.dect_state == 2 }">
    			 				<c:choose>
    			 					<c:when test="${opDevCtrlBtn.deco_type == 2 }">
    			 						<input type="button" disabled="disabled" value="${opDevCtrlBtn.dectbtn_name }">
    			 					</c:when>
    			 					<c:otherwise>
    			 						<input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',${opDevCtrlBtn.deco_type })" value="${opDevCtrlBtn.dectbtn_name }">
    			 					</c:otherwise>
    			 				</c:choose>
    			 			</c:when>
    			 			<c:when test="${gm_DevCtrlSts.dect_state == 3 }">
    			 				<c:choose>
    			 					<c:when test="${opDevCtrlBtn.deco_type == 3 }">
    			 						<input type="button" disabled="disabled" value="${opDevCtrlBtn.dectbtn_name }">
    			 					</c:when>
    			 					<c:otherwise>
    			 						<input id="btn" type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',${opDevCtrlBtn.deco_type })" value="${opDevCtrlBtn.dectbtn_name }">
    			 					</c:otherwise>
    			 				</c:choose>
    			 			</c:when>
    			 			<c:otherwise>
    			 				<input type="button" onclick="operate('${gm_DevCtrlSts.dect_id.dect_id }',${opDevCtrlBtn.deco_type })" value="${opDevCtrlBtn.dectbtn_name }">
    			 			</c:otherwise>
    			 		</c:choose>
    			 	</c:forEach>
    			 </td>
    		</tr>
    	</c:forEach>
    	<tr>
			<td colspan="3" align="left" valign="middle">
				<span style="padding-left: 10px; font-size: 12px;">
					共${page.totalCount}条记录，
					每页${page.pageSize}条，
					当前第${page.pageNo}页，
					共${page.totalPages}页
				</span>
			</td>
			<td colspan="5" align="right" valign="middle"
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
    	var queryName = '${queryName}';
    	var queryValue = '${queryValue}';
    	if(queryName == "dect_state"){
    		var html = "<select id=\"queryValue\"><option value=\"1\">开启</option><option value=\"3\">关闭</option><option value=\"2\">停止</option></select>";
			$("#values").html(html);
    	}
    	if(queryName == "dect_state" && queryValue != ""){
		    var query = document.getElementById("queryValue");
		    for(var i=0;i<query.options.length;i++){
				var op = query.options[i];
				if(op.value==queryValue){
					op.selected="selected";
				}
			}
		     		
		 }
    </script>
    <script type="text/javascript">
    	var queryName = document.getElementById("queryName");
    	var value = '${queryName}';
    	for(var i=0;i<queryName.options.length;i++){
				var op = queryName.options[i];
				if(op.value==value){
					op.selected="selected";
				}
			}
    </script>
    
  </body>
</html>
