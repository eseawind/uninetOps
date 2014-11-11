<!-- 据基础平台数据库设计v0[1].93.6完整版.doc 改 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加场景信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script type="text/javascript">		
		//省下拉列表改变事件
		function sel_sheng_onChange(obj){
			document.getElementById("sel_shi").length=0;
			document.getElementById("sel_shi").add(new Option("-- 请选择 --","-1"));
			document.getElementById("sel_xian").length=0;
			document.getElementById("sel_xian").add(new Option("-- 请选择 --","-1"));
			if(obj.value != "-1"){
				$.getJSON("${ctx}/op_areas_findShiBySheng.action?op_Areas.area_id="+obj.value,{
					random:Math.random()
				},function(list){
					if(list.length>0){
						var sel_shi = document.getElementById("sel_shi");
						$.each(list,function(i,shi){
							sel_shi.add(new Option(shi.area_name,shi.area_id));
						})	
					}else{
						var sel_xian = document.getElementById("sel_xian");
						$.getJSON("${ctx}/op_areas_findXianBySheng.action?op_Areas.area_id="+obj.value,{
							random:Math.random()
						},function(list){
							$.each(list,function(i,xian){
								sel_xian.add(new Option(xian.area_name,xian.area_id));
							})		
						});
					}	
				});
			}	
		}
		
		//市下拉列表改编事件
		function sel_shi_onChange(obj){
			document.getElementById("sel_xian").length=0;
			document.getElementById("sel_xian").add(new Option("-- 请选择 --","-1"));
			if(obj.value != "-1"){
				$.getJSON("${ctx}/op_areas_findXianByShi.action?op_Areas.area_id="+obj.value,{
					random:Math.random()
				},function(list){
					var sel_xian = document.getElementById("sel_xian");
					$.each(list,function(i,xian){
						sel_xian.add(new Option(xian.area_name,xian.area_id));
					})
				});
			}
		}
		
		//表单验证
		function checkForm(){
			var scene_no = document.getElementById("op_Scene.scene_no");
			if(scene_no.value.length == 0){
				alert("场景编号不能为空");
				return false;
			}
			var scene_name = document.getElementById("op_Scene.scene_name");
			if(scene_name.value.length == 0){
				alert("场景名称不能为空");
				return false;
			}
			var scene_addr = document.getElementById("op_Scene.scene_addr");
			if(scene_addr.value.length == 0){
				alert("场景所在地不能为空");
				return false;
			}
			var sel_scene_type = document.getElementById("sel_scene_type");
			if(sel_scene_type.value == "-1"){
				alert("请选择场景类型");
				return false;
			}
			var sel_scene_ctype = document.getElementById("sel_scene_ctype");
			if(sel_scene_ctype.value == "-1"){
				alert("请选择场景类型子类");
				return false;
			}
			var sel_scene_gtype = document.getElementById("sel_scene_gtype");
			if(sel_scene_gtype.value == "-1"){
				alert("请选择场景细类");
				return false;
			}
			
			var txt_scene_rank = document.getElementById("txt_scene_rank");
			var re_txt_scene_rank = /^\d+$/;
			if(!re_txt_scene_rank.test(txt_scene_rank.value)){
				alert("请填写正确的所属等级，0或任意正整数");
				return false;
			}
			//判断所选等级为最高等级
			if(txt_scene_rank.value==0){
				document.getElementById("op_Scene.scene_pid").disabled = true;
			}
			//验证行政区划非空，并将行政区划的ID添加到隐藏域中，通过表单提交
			var sel_sheng = document.getElementById("sel_sheng");
			var sel_shi = document.getElementById("sel_shi");
			var sel_xian = document.getElementById("sel_xian");			
			var area_id = document.getElementById("op_Scene.area_id.area_id");
			if(sel_xian.value != "-1"){
				area_id.value = sel_xian.value;
			}else if(sel_shi.value != "-1"){
				area_id.value = sel_shi.value;
			}else if(sel_sheng.value != "-1"){
				area_id.value = sel_sheng.value;
			}else{
				alert("请选择行政区划");
				return false;
			}
			return true;
		}
		
		//场景类型下拉列表改变事件
		function sel_scene_type_onChange(obj){
			var sel_scene_ctype = document.getElementById("sel_scene_ctype");
			sel_scene_ctype.length = 0;
			sel_scene_ctype.add(new Option("-- 请选择 --","-1"));
			if(obj.value == "1"){
				sel_scene_ctype.add(new Option("设施蔬菜","11"));
				sel_scene_ctype.add(new Option("设施花卉","12"));
			}else if(obj.value == "2"){
				sel_scene_ctype.add(new Option("池塘水产养殖","21"));
				sel_scene_ctype.add(new Option("设施水产养殖","22"));
			}
		}
		
		//场景子类型下拉列表改变事件
		function sel_scene_ctype_onChange(obj){
			/**
			var sel_scene_rank = document.getElementById("sel_scene_rank");
			sel_scene_rank.length = 0;
			sel_scene_rank.add(new Option("-- 请选择 --","-1"));
			if(obj.value == "11"){
				sel_scene_rank.add(new Option("企业","3"));
				sel_scene_rank.add(new Option("基地","2"));
				sel_scene_rank.add(new Option("大棚组","1"));
				sel_scene_rank.add(new Option("大棚","0"));
			}
			if(obj.value == "21"){
				sel_scene_rank.add(new Option("企业","3"));
				sel_scene_rank.add(new Option("基地","2"));
				sel_scene_rank.add(new Option("池塘组","1"));
				sel_scene_rank.add(new Option("池塘","0"));
			}
			*/
		}
		
		//响应场景树
		function echoSceneTree(id,name,no){
			//无响应
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
  		<a href="javascript:window.location.href='welcome.jsp'">首页</a> 》 
		<a href="${ctx }/op_scene_page.action?page.pageSize=12&op_Scene.scene_pid=">场景信息管理</a> 》
		<a href="#">添加场景信息</a> <br/> 
    <form action="${ctx }/op_scene_save.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">       		
			<input id="op_Scene.area_id.area_id" name="op_Scene.area_id.area_id" type="hidden">
			<input id="op_Scene.scene_pid" name="op_Scene.scene_pid" type="hidden" value="FF">
			<input id="op_Scene.scene_state" name="op_Scene.scene_state" type="hidden" value="1">
    		<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
    			<tr class="list_head">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>*场景编号: </td>
					<td><input id="op_Scene.scene_no" name="op_Scene.scene_no"/></td>
				</tr>
				<tr>
					<td>*场景名称: </td>
					<td><input id="op_Scene.scene_name" name="op_Scene.scene_name"/></td>
				</tr>
				<tr>
					<td>*场景所在地: </td>
					<td><input id="op_Scene.scene_addr" name="op_Scene.scene_addr"/></td>
				</tr>
				<tr>
					<td>*场景类型: </td>
					<td>
						<select id="sel_scene_type" name="op_Scene.scene_type" onchange="sel_scene_type_onChange(this)">
							<option value="-1">-- 请选择 --</option>
							<option value="1">设施园艺</option>
							<option value="2">水产养殖</option>
							<option value="3">大田种植</option>
							<option value="4">畜禽养殖</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>*场景类型子类: </td>
					<td>
						<select id="sel_scene_ctype" name="op_Scene.scene_ctype" onchange="sel_scene_ctype_onChange(this)">
							<option value="-1">-- 请选择 --</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>*场景细类: </td>
					<td>
						<select id="sel_scene_gtype" name="op_Scene.scene_gtype">
							<option value="-1">-- 请选择 --</option>
							<option value="1">养殖池塘</option>
							<option value="2">池塘组</option>
							<option value="3">基地</option>
							<option value="4">企业</option>
							<option value="5">项目</option>
							<option value="98">气象站</option>
							<option value="99">移动场景</option>	
						</select>
					</td>
				</tr>
				<tr>
					<td>*所属等级: </td>
					<td><input id="txt_scene_rank" name="op_Scene.scene_rank"></td>
				</tr>
				<tr>
					<td>*行政区划:</td>
					<td>			
						<select id="sel_sheng" onchange="sel_sheng_onChange(this)">
							<option value="-1">-- 请选择 --</option>
						</select>省
						<select id="sel_shi" onchange="sel_shi_onChange(this)">
							<option value="-1">-- 请选择 --</option>
						</select>市
						<select id="sel_xian">
							<option value="-1">-- 请选择 --</option>
						</select>县		
					</td>
				</tr>
				<tr>
					<td>场景的说明: </td>
					<td><input name="op_Scene.scene_desc"/> </td>
				</tr>
				<tr>
					<td>关键字: </td>
					<td><input name="op_Scene.scene_keyWord"/></td>
				</tr>
				<tr>
					<td>场景的图片: </td>
					<td>841*337px<label style="margin-top: 0px;"><s:file name="sceneImage"/></label></td>
				</tr>
				<tr>
					<td>定制路径: </td>
					<td><input name="op_Scene.scene_url"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回" onclick="history.go(-1);">
					</td>
				</tr>
			</table>			
    </form>
  </body>
  <script type="text/javascript">
  	var sel_sheng = document.getElementById("sel_sheng");
  	sel_sheng.length = 0;
	sel_sheng.add(new Option("-- 请选择 --","-1"));
	$.getJSON("${ctx}/op_areas_findSheng.action",{
		random:Math.random()
	},function(list){
		$.each(list,function(i,sheng){
			sel_sheng.add(new Option(sheng.area_name,sheng.area_id));
		})
	});
  </script>
</html>
