<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'historydataRevise.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style>
	<!--
		table {
		 border-collapse: collapse;
		 border: 2px #bbd1fa solid;
		}
		
		td {
		  border: 1px #bbd1fa solid;
		  font-size: 12px;
		}
		
		
	-->
	</style>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="<%=path %>/js/highcharts/exporting.js"></script>
	<script type="text/javascript" src="<%=path %>/js/date.js"></script>
	<link href="<%=path %>/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/js/date/WdatePicker.js"></script>	
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			loadTd_channels(id);
		}
		
		//加载通道表单
		function loadTd_channels(id){
			var td_channels = document.getElementById("td_channels");
			td_channels.innerHTML = ".";
					$.getJSON("<%=path %>/Gm_Channel_json_findAllByScene_idAndCh_offerSer_run.action?scene_id="+id,{
						random:Math.random()
					},function(types){
						var inner = "没有找到指定的数据";
						if(types.length>0){
							inner = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">";
		  					$.each(types,function(i,type){
			  						inner += "<tr>";
									inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">"+type.chtype_name+"("+type.chtype_no+")";
									inner += "<input type=\"checkbox\" onclick=\"fenlei_quanxuan(this,'ck_"+type.chtype_no+"')\"/> 全选 / 反选";
									inner += "</td>";
									inner += "</tr>";
									inner += "<tr>";
									inner += "<td style=\"border-left: 0px;border-top: 0px; border-right: 0px; width: 900px;\">&nbsp;";
									$.each(type.channels,function(j,channel){
										inner += "<input class=\"ck_"+type.chtype_no+"\" chtype="+type.chtype_no+" name=\"channels\" type=\"checkbox\" value=\""+channel.ch_id+"\">"+channel.ch_name+"("+channel.ch_no+")"+"&nbsp;";
									})
									inner += "</td>";
									inner += "</tr>";
		  					})
		  					inner += "<tr>";
		  					inner += "<td style=\"border:0px; width: 900px;\">&nbsp;</td>";
		  					inner += "</tr>";
		  					inner += "</table>";
		  				}				
		  				td_channels.innerHTML = inner;			
					});
				}
		
		//全选 反选
		function quanxuan(ck){
			var arr = document.getElementsByName("channels");
			if(ck.checked){				
				for(var i=0;i<arr.length;i++){
					var c = arr[i];
					c.checked = true;
				}
			}else{
				for(var i=0;i<arr.length;i++){
					var c = arr[i];
					c.checked = false;
				}
			}
		}
		
		//分类全选 反选
		function fenlei_quanxuan(ck,className){
			var arr = $("input."+className);
			if(ck.checked){				
				for(var i=0;i<arr.length;i++){
					var c = arr[i];
					c.checked = true;
				}
			}else{
				for(var i=0;i<arr.length;i++){
					var c = arr[i];
					c.checked = false;
				}
			}
		}
		
		
		 
		
	</script>

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
    <table width="100%" cellpadding="0" cellspacing="0">
  		<tr>
  			<td colspan="2">
  			</td>
  		</tr>
  		<tr>
  			<td width="100">
  				采集通道<br/>
  			</td>
  			<td id="td_channels">
				
  			</td>
  		</tr>
  		<tr>
  			
  			<td colspan="2" align="center">
  				开始时间：<input type="text" readonly="readonly" id="beginTime" name="beginTime" onClick="WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${beginTime }" />
				结束时间：<input type="text" id="endTime" readonly="readonly" name="endTime" onClick="WdatePicker({readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${endTime }" />
				<input type="button" onclick="find()" value="提交" id="button" name="button">
  			</td>
  		</tr>
  	</table>
  	<table>
  		<tr>
  			
  		</tr>
  	</table>
  </body>
</html>
<script type="text/javascript">
  		if(window.parent.right.scene_tree.selectedNode!=null){
			var curr_node_id = this.parent.right.scene_tree.aNodes[window.parent.right.scene_tree.selectedNode].id;
			var scene_id = curr_node_id.substr(2);
			//alert(scene_id);
			$.getJSON("<%=path %>/op_scene_json_findById.action?op_Scene.scene_id="+scene_id,{
				random:Math.random()
			},function(op_Scene){
				echoSceneTree(op_Scene.scene_id,op_Scene.scene_name,op_Scene.scene_no,op_Scene.scene_rank,op_Scene.scene_gtype);
			});		
		}

        function find(){		    		    	    
			var beginTime = document.getElementById("beginTime").value;
			var endTime = document.getElementById("endTime").value;
			var isChtype = "";
			var isSum = true;
			var obj=$("input:checked[name='channels']");      
			   $.each(obj, function(i,item){          
			     if(isChtype == ""){
			    	 isChtype = item.chtype;
			     }else {
					if(isChtype!=item.chtype){
						alert("请选择相同类型的通道");
						isSum = false;
						return false;
					}
				} 
			});
			if(Date.parse(beginTime.replace(/\-/g,"/"))>Date.parse(endTime.replace(/\-/g,"/"))){
				alert("请重新选择时间……");
				isSum = false;
				return;
			}
			
			var chIds = "";
			$.each(document.getElementsByName("channels"),function(i,channel){
				if(channel.checked){
					chIds += channel.value+",";
				}
			});
			if(chIds.length > 0){
				chIds = chIds.substring(0, chIds.length-1);
			}else {
				alert("请选择通道……");
				isSum = false;
				return;
			}
			var button = document.getElementById("button");
		    button.disabled = "false";
			if(isSum){
				$.ajax({
					url : "<%=path %>/gm_historydata_findHistorydataByChIdAndTime.action",
					type : "POST",
					dataType : "json",//xml,html,script,json
					data : {chIds : chIds,beginTime : beginTime,endTime : endTime},
					error : function() {},
					success : function(json) {
						jQuery.each(json, function(i, json) {
							button.disabled = "";
							alert(json.message);
						});
					}
				});
		 	}
		}
	</script>
