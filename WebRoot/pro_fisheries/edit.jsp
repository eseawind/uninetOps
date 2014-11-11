<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>编辑</title>   	
   	<link rel="stylesheet" type="text/css" href="${ctx}/js/validate/jquery.validate.css"/>
   	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/validate/jquery.validate.js"></script>
  	<script language="javascript" type="text/javascript" src="${ctx }/js/date.js"></script>
   	<script type="text/javascript">
   		$(document).ready(function(){
			$("#addForm").validate();
   		});
   		//响应场景树
		function echoSceneTree(id,name,no,rank, gtype){
			if(gtype == 1) {
				window.location.href = "${ctx}/fisherirs_edit.action?sceneId=" + id;
			}
		}
		function checkPro_Fisheries(){
			var pondNo=document.getElementById("pondNo").value;
			var phone=document.getElementById("phone").value;
			if(pondNo!=""&&phone!=""){
				var mobilezhengze = /^(130|131|133|135|136|137|138|139|159|158|153|152|186|187|151|189)(\d){8}$/;
				if(mobilezhengze.test(phone)==false){
					window.confirm("您输入的手机号码不正确，请重新输入！");
					document.getElementById("phone").value = "";
				}
				$.getJSON("${ctx}/fisherirs_checkPro_Fisheries.action?phone="+phone
					+"&pondNo="+pondNo				
					,{
					random:Math.random()
				},function(series){
					if(series.length>0){
						if(series=="false"){
							window.confirm("您输入的养殖池编号已存在，请重新输入！");
							document.getElementById("pondNo").value = "";
							document.getElementById("phone").value = "";
						}
					}
				});			
			}
		}		
   	</script>
   	<style type="text/css">
	 table {
	 border-collapse: collapse;
	 border: 2px #bbd1fa solid;
	 }
	
	 td {
	  border: 1px #bbd1fa solid;
	 }
	 
  </style>  
  
  <script type="text/javascript">
  	//var time = "${fisheries.fi_putTime }";
  	//time.substring(0,10);
  	//alert(time);
  	//document.getElementById("putTime").value=time;  	
  </script>
   	
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  	<form action="${ctx}/fisherirs_update.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&hid_condition=${hid_condition}&hid_value=${hid_value}" id="addForm" name="addForm" method="post">
  	<input type="hidden" name="fisheries.fi_id" value="${fisheries.fi_id}">
  	<input type="hidden" name="fisheries.scene.scene_id" value="${fisheries.scene.scene_id}">
  	<input type="hidden" name="fisheries.fi_state" value="${fisheries.fi_state}">
  	<table width="90%" align="center" border="1" cellpadding="0" cellspacing="0">
  	  <tr>
  	     <td align="left" width="20%">养殖池编号：</td>
  	     <td align="left" width="80%">&nbsp;<input type="text" id="pondNo" size="90%" name="fisheries.fi_pondNo" value="${fisheries.fi_pondNo }" class="required" onchange="checkPro_Fisheries()"/></td>
  	  </tr>
  	  <tr>
  	     <td align="left" width="20%">养殖池名称：</td>
  	     <td align="left" width="80%">&nbsp;<input type="text" id="pondName" size="90%" name="fisheries.fi_pondName" value="${fisheries.fi_pondName }" class="required" /></td>
  	  </tr>
  	  <tr>
  	     <td align="left" width="20%">养殖户：</td>
  	     <td align="left" width="80%">&nbsp;<input type="text" id="userName" size="90%" name="fisheries.fi_userName" value="${fisheries.fi_userName }" class="required" /></td>
  	  </tr>
  	  <tr>
  	     <td align="left" width="20%">手机号：</td>
  	     <td align="left" width="80%">&nbsp;<input type="text" id="phone" size="90%" name="fisheries.fi_phone" value="${fisheries.fi_phone }" class="required" onchange="checkPro_Fisheries()"/></td>
  	  </tr>
  	  <tr>
  	     <td align="left">面积：</td>
  	     <td align="left">&nbsp;<input type="text" id="area" size="90%" name="fisheries.fi_area" value="${fisheries.fi_area }" class="number"></td>
  	  </tr>
  	  <tr>
  	     <td align="left">水深：</td>
  	     <td align="left">&nbsp;<input type="text" id="depth" size="90%" name="fisheries.fi_depth" value="${fisheries.fi_depth }" ></td>
  	  </tr>
  	  <tr>
  	     <td align="left">水草种类：</td>
  	     <td align="left">&nbsp;<input type="text" id="aquaticType" size="90%" name="fisheries.fi_aquaticType" value="${fisheries.fi_aquaticType }" ></td>
  	  </tr>
  	  <tr>
  	     <td align="left">养殖对象：</td>
  	     <td align="left">&nbsp;<input type="text" id="cultureObject" size="90%" name="fisheries.fi_cultureObject" value="${fisheries.fi_cultureObject }" ></td>
  	  </tr>
  	  <!-- 新增投放时间   -->
  	   <tr>
  	     <td align="left">投放时间：</td>
  	     <td align="left">&nbsp;<input type="text" onfocus="setday(this,this)" id="putTime" size="90%" name="fisheries.fi_putTime" value="${fisheries.fi_putTime_sub }" ></td>
  	  </tr>
  	  <!--  
  	  <tr>
  	     <td align="left">品种：</td>
  	     <td align="left">&nbsp;<input type="text" id="species" size="90%" name="fisheries.fi_species" value="${fisheries.fi_species }" ></td>
  	  </tr>
  	  <tr>
  	     <td align="left">批次：</td>
  	     <td align="left">&nbsp;<input type="text" id="batch" size="90%" name="fisheries.fi_batch" value="${fisheries.fi_batch }" ></td>
  	  </tr>
  	  <tr>
  	     <td align="left">剩余数：</td>
  	     <td align="left">&nbsp;<input type="text" id="remainNum" size="90%" name="fisheries.fi_remainNum" value="${fisheries.fi_remainNum }" ></td>
  	  </tr>
  	  <tr>
  	     <td align="left">生长阶段：</td>
  	     <td align="left">&nbsp;<input type="text" id="productionStage" size="90%" name="fisheries.fi_productionStage" value="${fisheries.fi_productionStage }" ></td>
  	  </tr>
  	  -->
  	  <tr>
  	     <td align="left">预警值：</td>
  	     <td align="left">&nbsp;<input type="text" id="doyj" size="90%" name="fisheries.fi_doyj" value="${fisheries.fi_doyj}" ></td>
  	  </tr>
  	  <tr>
  	     <td align="left">报警值：</td>
  	     <td align="left">&nbsp;<input type="text" id="fido" size="90%" name="fisheries.fi_do" value="${fisheries.fi_do}" ></td>
  	  </tr>
  	  <tr>
  	     <td colspan="2" align="center">
  	     <input type="submit" value="提交" class="sub" style="background:#bbd1fa;width:60px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	     <input type="button" value="返回" onclick="history.go(-1);" style="background:#bbd1fa;width:60px">
  	     </td>
  	  </tr>
    </table>
    </form>
    <c:if test="${not empty message}">
	<script>alert('${message}');</script>        	
	</c:if>
  </body>
</html>
