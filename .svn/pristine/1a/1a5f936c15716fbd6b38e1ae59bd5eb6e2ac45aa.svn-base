<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>数据查询</title>
    
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
	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>
	<script type="text/javascript" src="${ctx }/js/date.js"></script>	
	<script type="text/javascript">
		//响应场景树
		function echoSceneTree(id,name,no,rank,gtype){
			loadTd_channels(id);
		}
		
		//加载通道表单
		function loadTd_channels(id){
			var td_channels = document.getElementById("td_channels");
			td_channels.innerHTML = ".";
					$.getJSON("${ctx}/Gm_Channel_json_findAllByScene_idAndCh_offerSer_run.action?scene_id="+id,{
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
	
	<script type="text/javascript">
	
	function update(){
		var chIds = "";
		var isChtype = "";
		var isSum = true;
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
		   
		    var ch_dectDig = document.getElementById("chDectDig").value;
		    var ch_max = document.getElementById("chMax").value;
			var ch_min = document.getElementById("chMin").value;
			var chCorrFormula = document.getElementById("chCorrFormula").value;
			var ch_crateMax = document.getElementById("chCrateMax").value;
			
		   if(ch_dectDig.length>0){
				var re_ch_dectDig = /^[1-9]\d*$/;
				if(!re_ch_dectDig.test(ch_dectDig)){
					alert("小数位数的格式不合法");
					isSum = false;
					return;
				}
			}
		   if(ch_max.length>0){
				var re_ch_max_1 = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
				var re_ch_max_2 = /^-?\d+$/;
				if(!re_ch_max_1.test(ch_max) && !re_ch_max_2.test(ch_max)){
					alert("量程上限的格式不合法");
					isSum = false;
					return;
				}
			}
			
			if(ch_min.length>0){
				var re_ch_min_1 = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
				var re_ch_min_2 = /^-?\d+$/;
				if(!re_ch_min_1.test(ch_min) && !re_ch_min_2.test(ch_min)){
					alert("量程下限的格式不合法");
					isSum = false;
					return;
				}
			}
			if(ch_crateMax.length>0){
				var re_ch_crateMax_1 = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
				var re_ch_crateMax_2 = /^-?\d+$/;
				if(!re_ch_crateMax_1.test(ch_crateMax) && !re_ch_crateMax_2.test(ch_crateMax)){
					alert("变化率上限的格式不合法");
					isSum = false;
					return;
				}
			}
			if(ch_max<ch_min){
				alert("量程上限不能小于量程下限");
				isSum = false;
				return;
			}
		   
		   
		 
		// document.getElementById("chIds").value=chIds;
		 if(isSum){
				$.ajax({
							url : "${ctx}/Gm_Channel_bathUpdate.action",
							type : "POST",
							dataType : "json",//xml,html,script,json
							data : {
								chIds : chIds,chDectDig : ch_dectDig,chMax : ch_max,chMin : ch_min,chCrateMax : ch_crateMax,chCorrFormula : chCorrFormula
							},
							error : function() {},
							success : function(json) {
								jQuery.each(json, function(i, json) {
									alert(json.message);
								});
							}
						});
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
  	</table>
  		<table width="100%">
  			<input id="chIds" name="chIds" type="hidden" value="" >
  		<tr>
  			<td>小数位数：</td>
  			<td><input id="chDectDig" name="chDectDig"></td>
  		</tr>
  		<tr>
  			<td>量程上限：</td>
  			<td><input id="chMax" name="chMax"></td>
  		</tr>
  		<tr>
  			<td>量程下限：</td>
  			<td><input id="chMin" name="chMin"></td>
  		</tr>
  		<tr>
  			<td>变化率上限：</td>
  			<td><input id="chCrateMax" name="chCrateMax"></td>
  		</tr>
  		<tr>
  			<td>校正公式：</td>
  			<td><input id="chCorrFormula" name="chCorrFormula"></td>
  		</tr>
  		<tr>
  			<td colspan="2" align="center">
  			<input type="button" onclick="update()" value="批量修改" >
  			<input type="button" onclick="javascript:history.go(-1);" value=" 返  回 " >
  			</td>
  		</tr>
  	</table>
  	<script type="text/javascript">
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
  