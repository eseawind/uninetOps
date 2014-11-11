<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.StaticDataManage"%>
<%@page import="org.unism.util.Scene_type"%>
<%@page import="org.unism.util.Scene_ctype"%>
<%@page import="org.unism.util.Scene_gtype"%>
<%@page import="org.unism.op.domain.Op_Scene"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				return;
			}
			var scene_name = document.getElementById("op_Scene.scene_name");
			if(scene_name.value.length == 0){
				alert("场景名称不能为空");
				return;
			}
			var scene_addr = document.getElementById("op_Scene.scene_addr");
			if(scene_addr.value.length == 0){
				alert("场景所在地不能为空");
				return;
			}
			var sel_scene_type = document.getElementById("sel_scene_type");
			if(sel_scene_type.value == "-1"){
				alert("请选择场景类型");
				return;
			}
			var sel_scene_ctype = document.getElementById("sel_scene_ctype");
			if(sel_scene_ctype.value == "-1"){
				alert("请选择场景类型子类");
				return;
			}
			var scene_longitude=document.getElementById("op_Scene.scene_longitude");
			var rega=/^\d+(\.)?(\d{1,9})?$/;
			if(scene_longitude.value.length>0){				    
			  if(!rega.test(scene_longitude.value)) 
			  {
				alert("填写的经度不符合条件，只能输入数字和小数点");
				return false; 
			  }
			}
			var scene_latitude=document.getElementById("op_Scene.scene_latitude");
			if(scene_latitude.value.length>0){				    
			  if(!rega.test(scene_latitude.value)) 
			  {
				alert("填写的纬度不符合条件，只能输入数字和小数点");
				return false; 
			  }
			}
			var scene_order = document.getElementById("op_Scene.scene_order");
			if(scene_order.value.length>0){
				var re_scene_order = /^[1-9]\d*$/;
				if(!re_scene_order.test(scene_order.value)){
					alert("请填写正确的排序号，任意正整数");
					return false;
				}
			}
			/**
			var txt_scene_rank = document.getElementById("txt_scene_rank");
			var re_txt_scene_rank = /^\d+$/;
			if(!re_txt_scene_rank.test(txt_scene_rank.value)){
				alert("请填写正确的所属等级，0或任意正整数");
				return false;
			}
			*/
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
				area_id.value = "-1";
			}
			//获得所属等级
			var txt_scene_rank = 0;
			//alert(scene_no.length);
			for(var i=0;i<scene_no.value.length;i++){
				//alert(charAt(i));
				if(scene_no.value.charAt(i)=="-"){
					txt_scene_rank += 1;
					//alert(txt_scene_rank);
				}
			}				
			document.getElementById("txt_scene_rank").value = txt_scene_rank;
			
			//如果没改过就不用判断是不是唯一了 直接提交就行
			if(scene_no.value==document.getElementById("no").value){
				document.forms[0].submit();
				return;
			}
			
			//判断场景编号是否唯一
			$.getJSON("${ctx}/op_scene_isExist.action?op_Scene.scene_no="+scene_no.value,{
					random:Math.random()
				},function(isExist){
					if(isExist.value){
						alert(isExist.msg);
						return;
					}else{
						document.forms[0].submit();
						return;
					}
				}
			);	
		}
		
		//场景类型下拉列表改变事件
		//0714 弃用 Wang_Yuliang
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
   		<form action="${ctx }/op_scene_edit.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&scene_pid=${scene_pid}&hid_condition=${hid_condition}&hid_value=${hid_value}" method="post" enctype="multipart/form-data">
   			<input id="op_Scene.area_id.area_id" name="op_Scene.area_id.area_id" type="hidden" value="${op_Scene.area_id.area_id }">
   			<input name="op_Scene.scene_id" type="hidden" value="${op_Scene.scene_id }">
   			<input name="op_Scene.scene_state" type="hidden" value="1">
   			<!--  input name="op_Scene.scene_type" type="hidden" value="${op_Scene.scene_type }">
   			<input name="op_Scene.scene_ctype" type="hidden" value="${op_Scene.scene_ctype }"-->
   			<!-- input name="op_Scene.scene_rank" type="hidden" value="${op_Scene.scene_rank }" -->
   			
   			<!-- input name="op_Scene.scene_createDate" type="text" value="<fmt:formatDate value='${op_Scene.scene_createDate }' pattern='yyyy-MM-dd'/>" -->
   			<input name="op_Scene.scene_creater" type="hidden" value="${op_Scene.scene_creater }">
   			<input name="op_Scene.scene_image" type="hidden" value="${op_Scene.scene_image }">
   			<table cellpadding="0" cellspacing="0" width="1020" class="senfe1">
    			<tr class="list_head">
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td>*场景编号：</td>
					<td>
						<input name="op_Scene.scene_no" value="${op_Scene.scene_no }"/> 必填
						<input type="hidden" id="no" value="${op_Scene.scene_no}">
					</td>
				</tr>
				<tr>
					<td>*场景名称: </td>
					<td><input name="op_Scene.scene_name" value="${op_Scene.scene_name }"/> 必填</td>
				</tr>
				<tr>
					<td>*场景所在地: </td>
					<td><input name="op_Scene.scene_addr" value="${op_Scene.scene_addr }"/> 必填</td>
				</tr>
				<tr>
					<td>*场景类型: </td>
					<td>
						<select id="sel_scene_type" name="op_Scene.scene_type" style="width:154px;">
							<option value="-1">-- 请选择 --</option>
							<%
								Op_Scene op_Scene = (Op_Scene)request.getAttribute("op_Scene");
								Integer type = op_Scene.getScene_type();
								List<Scene_type> scene_types = StaticDataManage.getScene_types();
								Iterator<Scene_type> scene_typesIterator = scene_types.iterator();
							%>
							<%
								while (scene_typesIterator.hasNext()) {
									Scene_type scene_type = scene_typesIterator.next();			
									if(type.equals(Integer.parseInt(scene_type.getId()))){						
							%>
							<option value="<%=scene_type.getId()%>" selected="selected"><%=scene_type.getName()%></option>
							<% 		}else{%>
							<option value="<%=scene_type.getId()%>"><%=scene_type.getName()%></option>				
							<%
									}
								}
							%>
						</select> 必填
					</td>
				</tr>
				<tr>
					<td>*场景类型子类:	</td>
					<td>
						<select id="sel_scene_ctype" name="op_Scene.scene_ctype" style="width:154px;">
							<option value="-1">-- 请选择 --</option>
							<%
								Integer ctype = op_Scene.getScene_ctype();
								List<Scene_ctype> scene_ctypes = StaticDataManage.getScene_ctypes();
								Iterator<Scene_ctype> scene_ctypesIterator = scene_ctypes.iterator();
							%>
							<%
								while (scene_ctypesIterator.hasNext()) {
									Scene_ctype scene_ctype = scene_ctypesIterator.next();
									if(ctype.equals(Integer.parseInt(scene_ctype.getId()))){
							%>
							<option value="<%=scene_ctype.getId()%>" selected="selected"><%=scene_ctype.getName()%></option>
							<% 		}else{%>
							<option value="<%=scene_ctype.getId()%>"><%=scene_ctype.getName()%></option>
							<%
									}
								}
							%>
						</select> 必填
					</td>
				</tr>
				<tr>
					<td>*场景细类：</td>
					<td>
						<select id="sel_scene_gtype" name="op_Scene.scene_gtype" style="width:154px;">
							<option value="-1">-- 请选择 --</option>
							<c:forEach items="${sceneGtype }" var="scenGtype">
								<c:choose>
									<c:when test="${scenGtype.gtype == op_Scene.scene_gtype }">
										<option value="${scenGtype.gtype }" selected="selected">${scenGtype.name }</option>
									</c:when>
									<c:otherwise>
										<option value="${scenGtype.gtype }">${scenGtype.name }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							
						</select> 必填 
					</td>
				</tr>
				<tr style="display: none;">
					<td>
						*所属等级: 
					</td>
					<td>
						<input name="op_Scene.scene_rank" id="txt_scene_rank" value="${op_Scene.scene_rank}"/> 
					</td>
				</tr>
				<tr>
					<td>行政区划:</td>
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
				<input id="op_Scene.scene_createDate" value="${op_Scene.scene_createDate }" style="display: none;"/>
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
						<img id="scene_image" src="${scene_image_url }" width="180" height="72"> <br/>
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
								<input disabled="disabled" type="text" value="${dis_scene_pid.scene_name }">
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
					<td>经度: </td>
					<td><input name="op_Scene.scene_longitude" value="${op_Scene.scene_longitude }"/></td>
				</tr>
				<tr>
					<td>纬度: </td>
					<td><input name="op_Scene.scene_latitude" value="${op_Scene.scene_latitude }"/></td>
				</tr>
				<tr>
					<td>视频点URL: </td>
					<td><input name="op_Scene.scene_videoUrl" value="${op_Scene.scene_videoUrl }"/></td>
				</tr>
				<tr>
					<td>排序号: </td>
					<td><input id="op_Scene.scene_order" name="op_Scene.scene_order" value="${op_Scene.scene_order }"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="提交" onclick="checkForm()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回" onclick="window.location.href='op_scene_page.action?page.pageNo=${page.pageNo}&page.pageSize=${page.pageSize}&scene_pid=${scene_pid}&hid_condition=${hid_condition}&hid_value=${hid_value}'">
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
	//document.getElementById("scene_image").src = "ftp://127.0.0.1:21/scene_image/t0801a1312163213156.jpg";
	//var xax = "ftp://127.0.0.1:21/scene_image/t0801a1312163213156.jpg";
  </script>
</html>
