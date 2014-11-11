<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新供应商信息e</title>
    <link href="css/style_shuichan.css" rel="stylesheet" type="text/css" />
   	<script language="javascript" type="text/javascript">
		function isValidate() {
			var supname = document.getElementById("sup_name").value;
			var suptype = document.getElementById("sup_type").value;
			var supcountry = document.getElementById("sup_country").value;
			var supaddress = document.getElementById("sup_address").value;
			var supzip = document.getElementById("sup_zip").value;
			var supphon = document.getElementById("sup_phon").value;
			var supfax = document.getElementById("sup_fax").value;
			var supcontactName = document.getElementById("sup_contactName").value;
			var supcontactPhone = document.getElementById("sup_contactPhone").value;
			var supcontactMobile = document.getElementById("sup_contactMobile").value;
			var supcontactEmail = document.getElementById("sup_contactEmail").value;
			var emailzhengze = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			var mobilezhengze = /^(130|131|132|133|134|135|136|137|138|139|159|156|157|158|153|155|152|154|151|180|181|182|183|184|185|186|187|188|189)(\d){8}$/;
			var phonezhengze = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			var postzhengze = /^[0-9]{6}$/;

			if(supname == ""){
				window.confirm("供应商名称不能为空！");
				return false;
			}
			
			if(supzip != ""){
				if(postzhengze.test(supzip)==false){
					window.confirm("邮编不正确，请重新输入！");
					return false;
				}
			}
			if(supphon != ""){
				if(phonezhengze.test(supphon)==false){
					window.confirm("电话不正确，请重新输入！");
					return false;
				}
			}
			if(supcontactPhone != ""){
				if(phonezhengze.test(supcontactPhone)==false){
					window.confirm("电话不正确，请重新输入！");
					return false;
				}
			}
			if(supcontactMobile != ""){
				if(mobilezhengze.test(supcontactMobile)==false){
					window.confirm("手机不正确，请重新输入！");
					return false;
				}
			}
			if(supcontactEmail != ""){
				if(emailzhengze.test(supcontactEmail)==false){
					window.confirm("邮箱格式不正确，请重新输入！");
					return false;
				}
			}
			return true;
		}
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			<a href="javascript:window.location.href='${ctx }/welcome.jsp'">首页</a> 》 
			<a href="javascript:window.location.href='${ctx }/Op_Supplier_findAll.action'">供应商信息管理</a> 》 
			<a href="#">编辑供应商信息</a> <br>
    <form action="Op_Supplier_update.action" method="post">
    	<c:forEach items="${requestScope.list}" var="supplier">
	  	<table width="1020" border="0" cellpadding="0" cellspacing="0" class="senfe1">
	  		<tr class="list_head">
	  			<td colspan="2">&nbsp;</td>
	  		</tr>	
    		<tr>
				<td>供应商名称</td>
				<td>
					<input type="hidden" name="op_Supplier.sup_id" value="${supplier.sup_id}"/>
					<input type="text" name="op_Supplier.sup_name" id="sup_name" value="${supplier.sup_name}"/>
				</td>
			</tr>			
			<tr>
				<td>供应商类型</td>
				<td>
					<select name="op_Supplier.sup_type" id="sup_type">
						<option value="0">销售商</option>
						<option value="1">服务商</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>供应商国家</td>
				<td><input type="text" name="op_Supplier.sup_country" id="sup_country" value="${supplier.sup_country}"/></td>
			</tr>
			<tr>
				<td>供应商地址</td>
				<td><input type="text" name="op_Supplier.sup_address" id="sup_address" value="${supplier.sup_address}"/></td>
			</tr>
			<tr>
				<td>供应商邮编</td>
				<td><input type="text" name="op_Supplier.sup_zip" id="sup_zip" value="${supplier.sup_zip}"/></td>
			</tr>
			<tr>
				<td>供应商固定电话</td>
				<td><input type="text" name="op_Supplier.sup_phone" id="sup_phon" value="${supplier.sup_phone}"/></td>
			</tr>
			<tr>
				<td>供应商传真</td>
				<td><input type="text" name="op_Supplier.sup_fax" id="sup_fax" value="${supplier.sup_fax}"/></td>
			</tr>
			<tr>
				<td>联系人姓名</td>
				<td><input type="text" name="op_Supplier.sup_contactName" id="sup_contactName" value="${supplier.sup_contactName}"/></td>
			</tr>
			<tr>
				<td>联系人电话</td>
				<td><input type="text" name="op_Supplier.sup_contactPhone" id="sup_contactPhone" value="${supplier.sup_contactPhone}"/></td>
			</tr>
			<tr>
				<td>联系人手机</td>
				<td><input type="text" name="op_Supplier.sup_contactMobile" id="sup_contactMobile" value="${supplier.sup_contactMobile}"/></td>
			</tr>
			<tr>
				<td>联系人邮箱</td>
				<td><input type="text" name="op_Supplier.sup_contactEmail" id="sup_contactEmail" value="${supplier.sup_contactEmail}"/></td>
			</tr>			
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="提 交" onclick="return isValidate()"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
					<input type="reset" value="返 回" onclick="window.location.href='Op_Supplier_findAll.action'"/>
				</td>
			</tr>
    	</table>
    	</c:forEach>
    </form>
  </body>
</html>
