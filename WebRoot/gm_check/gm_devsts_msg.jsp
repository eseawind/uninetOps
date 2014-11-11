<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var addr = "";
		var DG = frameElement.lhgDG;
		J(function(){
	    		addr = J('#txt',DG.curDoc).val();
	    		//alert(addr);
	    		findByaddr(addr);
		});

		function findByaddr(addr){
			var html = "<table>";
			var time = "";
				$.ajax({
				   url: "${ctx}/gm_devsts_findGm_LatestdataByAddr.action", 
			       type: "POST",  
			       dataType: "json",//xml,html,script,json
			       data:{net_addr:addr},
			       error: function(){},  
			       success: function(json){  
			       	  jQuery.each(json, function(i, list){
				       	  if(list.flag == "menu"){
							$("#name").html(list.devName);
							$("#addr").html(list.devAddr);
							$("#recordTime").html(list.recordTime);
							$("#reportTime").html(list.reportTime);
					      }else {  
				       	  	  html += "<input type=\"hidden\" id=\"hidaId\" value=\""+list.hidaId+"\"><tr style=\"font: 12px/1.5 tahoma,arial,宋体;\" align=\"left\"><td width=\"80\">"+list.channelNo+"</td><td width=\"120\" nowrap=\"nowrap\">"+list.channelName+"</td><td width=\"130\" nowrap=\"nowrap\">"+list.deviceNo+"</td><td width=\"75\">"+list.chChlNo+"</td><td width=\"75\"><span id=\"corrValue\">"+list.corrValue+"</span><span>"+list.corrUnit+"</span></td><td width=\"\">"+list.origValue+list.origUnit+"</td></tr>";
						}
				      });
			       	  html += "</table>";
			       	  if(json.length < 1){
			       	  	html = "<table><tr><td>没有查到数据……</td></tr></table>"
			       	  }
			       	$("#val").html(html);
	        	   }  
				});  
		}
		
		function edit(){
			$("#save").attr("disabled","");
			$("#edit").attr("disabled","disabled");
			var time = $("#recordTime").html();
			$("#recordTime").html("<input id=\"time\" value=\""+time+"\" onFocus=\"WdatePicker({isShowClear:false,readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})\" >");
			var valueArr = $("[id$='corrValue']");
			$.each(valueArr,function(i,obj){
				var val = $(obj).html();
				$(obj).html("<input id=\"value\" style=\"width:50px\" value=\""+val+"\">");
			});
		}
		
		function save(){
			var hidaIdArr = [];
			var hidaValueArr = [];
			var idArr = $("[id$='hidaId']");
			$.each(idArr,function(i,obj){
				hidaIdArr.push($(obj).val());
			});
			
			var valueArr = $("[id$='value']");
			$.each(valueArr,function(i,obj){
				hidaValueArr.push($(obj).val());
			});
			var dateTime = $("#time").val();
			if(hidaIdArr.length>0){
				$("#save").attr("disabled","disabled");
				$.ajax({
				    url : "${ctx}/gm_devsts_saveLatestdata.action",
				    type : "POST",
				    async : false,
				    dataType : "json",//xml,html,script,json
				    data : {hidaId:hidaIdArr,hidaValue:hidaValueArr,dateTime:dateTime},
				    error : function() {},
				    success : function(data) {
				      if(data){
				    	  alert("保存成功！");
				      }else{
				    	  alert("保存失败！");
				      }
				    }
				});
			}
		}
	</script>
	
	 <style type="text/css">
        html,body {
            overflow:hidden;
            margin:0px;
            width:100%;
            height:100%;
        }
        .virtual_body {
            width:100%;
            height:100%;
            overflow-y:scroll;
            overflow-x:auto;
            font: 12px/1.5 tahoma,arial,宋体;
        }
        .fixed_div {
            position:absolute;
            z-index:2008;
            border-bottom: 1px solid #85c5ee;
            width: 100%;
            font: 12px/1.5 tahoma,arial,宋体;
            background-color: #85c5ee;
        }
    </style>
	
  </head>
  <body>
   <div class="fixed_div">
    <table>
    	<tr>
    		<td colspan="3" style="font: 14px tahoma,arial,宋体;">设备名称：<span id="name">&nbsp;</span></td>
    		<td colspan="3" style="font: 14px tahoma,arial,宋体;">设备地址：<span id="addr">&nbsp;</span></td>
    	</tr>
    	<tr>
    		<td colspan="3" style="font: 14px tahoma,arial,宋体;">采集时间：<span id="recordTime">&nbsp;</span></td>
    		<td colspan="3" style="font: 14px tahoma,arial,宋体;">上报时间：<span id="reportTime"></span></td>
    		<td><input id="edit" type="button" value="编辑" onclick="edit();" /></td>
    		<td><input id="save" disabled="disabled" type="button" value="保存" onclick="save();" /></td>
    	</tr>
    	<tr id="menu">
    		  <td width="80px;" style="font: 14px tahoma,arial,宋体;">通道编号</td>
    		  <td width="120px" style="font: 14px tahoma,arial,宋体;">通道名称</td>
    		  <td width="115px" style="font: 14px tahoma,arial,宋体;">采集设备编号</td>
    		  <td style="font: 14px tahoma,arial,宋体;">设备通道号</td>
    		  <td width="105px" style="font: 14px tahoma,arial,宋体;">处理后数据</td>
    		  <td style="font: 14px tahoma,arial,宋体;">原数据</td>
    	</tr>
    </table>
    
  </div>
    <div id="val" class="virtual_body" style="position: relative;top: 69px; height: 369px;">
    	
    </div>
  </body>
</html>
