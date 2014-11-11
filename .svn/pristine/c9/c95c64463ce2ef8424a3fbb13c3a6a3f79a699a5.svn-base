<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>编辑场景信息</title>
    
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
			var txt_scene_rank = document.getElementById("txt_scene_rank");
			var re_txt_scene_rank = /^\d+$/;
			if(!re_txt_scene_rank.test(txt_scene_rank.value)){
				alert("请填写正确的所属等级，0或任意正整数");
				return false;
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
				sel_scene_ctype.add(new Option("设施蔬菜","21"));
				sel_scene_ctype.add(new Option("设施花卉","22"));
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
		<a href="#">编辑场景信息</a> <br/> 
   		<form action="${ctx }/op_scene_edit.action?no=${op_Scene.scene_no}&page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&scene_pid=${scene_pid}&hid_condition=${hid_condition}&hid_value=${hid_value}" method="post" enctype="multipart/form-data" onsubmit="checkForm()">
   			<input id="op_Scene.area_id.area_id" name="op_Scene.area_id.area_id" type="hidden" value="${op_Scene.area_id.area_id }">
   			<input name="op_Scene.scene_id" type="hidden" value="${op_Scene.scene_id }">
   			<input name="op_Scene.scene_state" type="hidden" value="1">
   			<input name="op_Scene.scene_type" type="hidden" value="${op_Scene.scene_type }">
   			<input name="op_Scene.scene_ctype" type="hidden" value="${op_Scene.scene_ctype }">
   			<input name="op_Scene.scene_rank" type="hidden" value="${op_Scene.scene_rank }">
   			<input name="op_Scene.scene_createDate" type="hidden" value="${op_Scene.scene_createDate }">
   			<input name="op_Scene.scene_creater" type="hidden" value="${op_Scene.scene_creater }">
   			<input name="op_Scene.scene_image" type="hidden" value="${op_Scene.scene_image }">
   			<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
    			<tr class="list_head">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>*场景编号：</td>
					<td><input name="op_Scene.scene_no" value="${op_Scene.scene_no }"/> 必须唯一,必填</td>
				</tr>
				<tr>
					<td>*场景名称: </td>
					<td><input name="op_Scene.scene_name" value="${op_Scene.scene_name }"/></td>
				</tr>
				<tr>
					<td>*场景所在地: </td>
					<td><input name="op_Scene.scene_addr" value="${op_Scene.scene_addr }"/></td>
				</tr>
				<tr>
					<td>*场景类型: </td>
					<td>
						<select disabled="disabled">
							<option value="-1">-- 请选择 --</option>
							<c:choose>
								<c:when test="${op_Scene.scene_type == 1 }">
									<option value="1" selected="selected">设施园艺</option>
									<option value="2">水产养殖</option>
									<option value="3">大田种植</option>
									<option value="4">畜禽养殖</option>
								</c:when>
								<c:when test="${op_Scene.scene_type == 2 }">
									<option value="1">设施园艺</option>
									<option value="2" selected="selected">水产养殖</option>
									<option value="3">大田种植</option>
									<option value="4">畜禽养殖</option>
								</c:when>
								<c:when test="${op_Scene.scene_type == 3 }">
									<option value="1">设施园艺</option>
									<option value="2">水产养殖</option>
									<option value="3" selected="selected">大田种植</option>
									<option value="4">畜禽养殖</option>
								</c:when>
								<c:when test="${op_Scene.scene_type == 4 }">
									<option value="1">设施园艺</option>
									<option value="2">水产养殖</option>
									<option value="3">大田种植</option>
									<option value="4" selected="selected">畜禽养殖</option>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td>*场景类型子类:	</td>
					<td>
						<select disabled="disabled" id="sel_scene_ctype">
							<option value="-1">-- 请选择 --</option>
							<c:choose>
								<c:when test="${op_Scene.scene_ctype == 11 }">
									<option value="11" selected="selected">设施蔬菜</option>
									<option value="12">设施花卉</option>
									<option value="21">池塘水产养殖</option>
									<option value="22">设施水产养殖</option>
								</c:when>
								<c:when test="${op_Scene.scene_ctype == 12 }">
									<option value="11">设施蔬菜</option>
									<option value="12" selected="selected">设施花卉</option>
									<option value="21">池塘水产养殖</option>
									<option value="22">设施水产养殖</option>
								</c:when>
								<c:when test="${op_Scene.scene_ctype == 21 }">
									<option value="11">设施蔬菜</option>
									<option value="12">设施花卉</option>
									<option value="21" selected="selected">池塘水产养殖</option>
									<option value="22">设施水产养殖</option>
								</c:when>
								<c:when test="${op_Scene.scene_ctype == 22 }">
									<option value="11">设施蔬菜</option>
									<option value="12">设施花卉</option>
									<option value="21">池塘水产养殖</option>
									<option value="22" selected="selected">设施水产养殖</option>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td>*场景细类：</td>
					<td>
						<select id="sel_scene_gtype" name="op_Scene.scene_gtype">
							<option value="-1">-- 请选择 --</option>
							<c:choose>
								<c:when test="${op_Scene.scene_gtype == 1}">
									<option value="1" selected="selected">养殖池塘</option>
									<option value="2">池塘组</option>
									<option value="3">基地</option>
									<option value="4">企业</option>
									<option value="5">项目</option>
									<option value="98">气象站</option>
									<option value="99">移动场景</option>	
								</c:when>
								<c:when test="${op_Scene.scene_gtype == 2}">
									<option value="1">养殖池塘</option>
									<option value="2" selected="selected">池塘组</option>
									<option value="3">基地</option>
									<option value="4">企业</option>
									<option value="5">项目</option>
									<option value="98">气象站</option>
									<option value="99">移动场景</option>	
								</c:when>
								<c:when test="${op_Scene.scene_gtype == 3}">
									<option value="1">养殖池塘</option>
									<option value="2">池塘组</option>
									<option value="3" selected="selected">基地</option>
									<option value="4">企业</option>
									<option value="5">项目</option>
									<option value="98">气象站</option>
									<option value="99">移动场景</option>	
								</c:when>
								<c:when test="${op_Scene.scene_gtype == 4}">
									<option value="1">养殖池塘</option>
									<option value="2">池塘组</option>
									<option value="3">基地</option>
									<option value="4" selected="selected">企业</option>
									<option value="5">项目</option>
									<option value="98">气象站</option>
									<option value="99">移动场景</option>	
								</c:when>
								<c:when test="${op_Scene.scene_gtype == 5}">
									<option value="1">养殖池塘</option>
									<option value="2">池塘组</option>
									<option value="3">基地</option>
									<option value="4">企业</option>
									<option value="5" selected="selected">项目</option>
									<option value="98">气象站</option>
									<option value="99">移动场景</option>	
								</c:when>
								<c:when test="${op_Scene.scene_gtype == 98}">
									<option value="1">养殖池塘</option>
									<option value="2">池塘组</option>
									<option value="3">基地</option>
									<option value="4">企业</option>
									<option value="5">项目</option>
									<option value="98" selected="selected">气象站</option>
									<option value="99">移动场景</option>	
								</c:when>
								<c:when test="${op_Scene.scene_gtype == 99}">
									<option value="1">养殖池塘</option>
									<option value="2">池塘组</option>
									<option value="3">基地</option>
									<option value="4">企业</option>
									<option value="5">项目</option>
									<option value="98">气象站</option>
									<option value="99" selected="selected">移动场景</option>	
								</c:when>
							</c:choose>
						</select> 
					</td>
				</tr>
				<tr>
					<td>
						*所属等级: 
					</td>
					<td>
						<input disabled="disabled" id="txt_scene_rank" value="${op_Scene.scene_rank}"/> 
					</td>
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
					<td>场景添加时间: </td>
					<td><input id="op_Scene.scene_createDate" value="${op_Scene.scene_createDate }"/> </td>
				</tr>
				<tr>
					<td>创建者: </td>
					<td><input disabled="disabled" value="${op_Scene.scene_creater }"/></td>
				</tr>
				<tr>
					<td>场景的说明: </td>
					<td><input name="op_Scene.scene_desc" value="${op_Scene.scene_desc }"/></td>
				</tr>
				<tr>
					<td>关键字: </td>
					<td><input name="op_Scene.scene_keyWord" value="${op_Scene.scene_keyWord }"/> </td>
				</tr>
				<tr>
					<td>场景的图片: </td>
					<td>841*337px<<LABEL><s:file name ="sceneImage"/></LABEL></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<img src="${op_Scene.scene_image }" width="180" height="72"> <br/>${op_Scene.scene_image }
					</td>
				</tr>
				<tr>
					<td>所属场景父ID: </td>
					<td>
						<c:choose>
							<c:when test="${op_Scene.scene_pid == null}">
								<input disabled="disabled" type="text" value="null">
							</c:when>
							<c:when test="${op_Scene.scene_pid == 'FF'}">
								<input disabled="disabled" type="text" value="${op_Scene.scene_pid }"> 
								<input type="hidden" name="op_Scene.scene_pid" value="${op_Scene.scene_pid }"> 
							</c:when>
							<c:otherwise>
								<input disabled="disabled" type="text" value="${scene_pid.scene_name }">
								<input type="hidden" name="op_Scene.scene_pid" value="${op_Scene.scene_pid }"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>
						定制路径: 
					</td>
					<td>
						<input name="op_Scene.scene_url" value="${op_Scene.scene_url }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回" onclick="history.go(-1);">
					</td>
				</tr>
			</table>				
  		</form>
  </body>
  <script type="text/javascript">
  	var area_id = "${op_Scene.area_id.area_id}";
  	//省
  	var sel_sheng = document.getElementById("sel_sheng");
  	sel_sheng.length = 0;
	sel_sheng.add(new Option("-- 请选择 --","-1"));
	$.getJSON("${ctx}/op_areas_findSheng.action",{
		random:Math.random()
	},function(list){
		$.each(list,function(i,sheng){
			var op_sheng = new Option(sheng.area_name,sheng.area_id);
			if(sheng.area_id.substr(0,2) == area_id.substr(0,2)){				
				op_sheng.selected=true;
				sel_sheng.add(op_sheng);				
			}else{
				sel_sheng.add(op_sheng);
			}
		})
		
		//市
		var sel_shi = document.getElementById("sel_shi");
		$.getJSON("${ctx}/op_areas_findShiBySheng.action?op_Areas.area_id="+area_id.substr(0,2)+"0000",{
			random:Math.random()
		},function(list){		
			$.each(list,function(i,shi){
				var op_shi = new Option(shi.area_name,shi.area_id);
				if(shi.area_id.substr(0,4) == area_id.substr(0,4)){					
					op_shi.selected=true;
					sel_shi.add(op_shi);
				}else{
					sel_shi.add(op_shi);
				}	
			})
			
			//县	
			if(sel_shi.length > 0){
				$.getJSON("${ctx}/op_areas_findXianByShi.action?op_Areas.area_id="+area_id.substr(0,4)+"00",{
					random:Math.random()
				},function(list){
					var sel_xian = document.getElementById("sel_xian");
					$.each(list,function(i,xian){
						var op_xian = new Option(xian.area_name,xian.area_id);
						if(xian.area_id == area_id){					
							op_xian.selected=true;
							sel_xian.add(op_xian);
						}else{
							sel_xian.add(op_xian);
						}	
					})	
				});	
			}else{
				$.getJSON("${ctx}/op_areas_findXianBySheng.action?op_Areas.area_id="+area_id.substr(0,2)+"0000",{
					random:Math.random()
				},function(list){
					var sel_xian = document.getElementById("sel_xian");
					$.each(list,function(i,xian){
						var op_xian = new Option(xian.area_name,xian.area_id);
						if(xian.area_id == area_id){					
							op_xian.selected=true;
							sel_xian.add(op_xian);
						}else{
							sel_xian.add(op_xian);
						}	
					})	
				});
			}
		});	
		
	});	
  </script>
</html>
