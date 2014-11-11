<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.unism.gm.action.form.DevstsForm" %>
<%@page import="javax.persistence.criteria.CriteriaBuilder.In"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

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
	<link href="${ctx }/css/style_shuichan.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<script type="text/javascript">
		function findHistorydata(){
			var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;
			var hid_scene_id = document.getElementById("hid_scene_id").value;
			window.location.href = "${ctx}/gm_devsts_findAllDest_WorkStsList.action?hid_condition="+hid_condition+"&hid_value="+hid_value+"&scene_id="+hid_scene_id;
		}
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			document.getElementById("hid_scene_id").value = id;
			findHistorydata();
		}
	</script>
  </head>  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    	<table  cellpadding="0" cellspacing="0" width="95%">
    		<tr>
    			<td>
				     <SELECT id="hid_condition" style=" width: 156px;">
							<option value="dev_addr">设备地址</option>
					 </SELECT>
					 <input id="hid_value" name="hid_value" style="width: 156px;" value="${hid_value }">
    				 <input type="button" onclick="findHistorydata();" value="查询">
    				 <input type="hidden" id="hid_scene_id" value="${scene_id }"/>
    			</td>
    		</tr>
    	</table>
    	<table  cellpadding="0" cellspacing="0" width="99%" class="senfe1">
    	    <tr bgcolor='#8FABDE' align="center" style="height:25px;">
    			<td width="25%">场景名称</td>
    			<td width="25%">设备状态</td>
    			<td width="70%">详细信息</td>
    		</tr>
    		<%    		    
    			Object mapObj = request.getAttribute("map");
    			if(mapObj != null) {
    				Map<String, DevstsForm> map = (Map<String, DevstsForm>)mapObj;    				
    				for(Map.Entry<String, DevstsForm> entry : map.entrySet())   {     					
    					DevstsForm form = entry.getValue();
    					String scene_name=form.getScene_name();
						String dest_workSts=form.getDest_workSts();
						String dest_workStsInfo=form.getDest_workStsInfo();
						if("".equals(dest_workStsInfo)){
						  dest_workStsInfo="&nbsp;";
						}						
						if("在线".equals(dest_workSts)){	
							out.print("<tr align='center'>");										
	    					out.print("<td><font color='green'><b>" + scene_name + "</b></font></td>");
	    					out.print("<td><font color='green'><b>" + dest_workSts + "</b></font></td>");
	    					out.print("<td><font color='green'><b>" + dest_workStsInfo + "</b></font></td>");   
	    					out.print("</tr>"); 					
    					}
    					else if("离线".equals(dest_workSts)){	
							out.print("<tr align='center'>");										
	    					out.print("<td><font color='gray'><b>" + scene_name + "</b></font></td>");
	    					out.print("<td><font color='gray'><b>" + dest_workSts + "</b></font></td>");
	    					out.print("<td><font color='gray'><b>" + dest_workStsInfo + "</b></font></td>");   
	    					out.print("</tr>"); 					
    					}else{
	    					out.print("<tr align='center'>");										
	    					out.print("<td><font color='red'><b>" + scene_name + "</b></font></td>");
	    					out.print("<td><font color='red'><b>" + dest_workSts + "</b></font></td>");
	    					out.print("<td><font color='red'><b>" + dest_workStsInfo + "</b></font></td>");   
	    					out.print("</tr>");
    					}
    					
    				} 
    			}
    		%>
    	</table>
  </body>
</html>
