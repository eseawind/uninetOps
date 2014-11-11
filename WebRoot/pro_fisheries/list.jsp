<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Ӧ������</title>
   	<script type="text/javascript" src="${ctx }/js/jquery-1.3.2.min.js"></script>
   	<script type="text/javascript">
   		$(document).ready(function(){
			
   		});
   		//��Ӧ������
		function echoSceneTree(id,name,no,rank, gtype){
			if(gtype == 1) {
				window.location.href = "${ctx}/fisherirs_edit.action?sceneId=" + id;
			}
		}
		//��ҳ��ѯ
		function gotoPage(pageNo){	
		    var hid_condition = document.getElementById("hid_condition").value;
			var hid_value = document.getElementById("hid_value").value;		
			window.location.href = "${ctx}/fisherirs_list.action?"+ "d="+Math.random()+"&page.pageNo=" + pageNo + "&page.pageSize=${page.pageSize}&hid_condition=" + encodeURI(hid_condition)+"&hid_value=" + encodeURI(hid_value);
		}
   	</script>
   	<style type="text/css">
	 table {
	 border-collapse: collapse;
	 border: 2px #bbd1fa solid;
	 }
	 tr{
	 height:25px;
	 }
	 td {
	  border: 1px #bbd1fa solid;
	 }
	 
  </style>   	

  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
         <a href="javascript:window.location.href='welcome.jsp'">��ҳ</a> �� 
		 <a href="#">Ӧ������</a><br/>
  	 
		  ��ѯ����:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
		  	<option value="">-- ��ѡ�� --</option>
		  	<option value="scene_name">��ֳ������</option>
		  </select>
		  <input id="hid_condition" name="hid_condition" type="hidden" value="${hid_condition }">
    	  ֵ:<input id="sel_value" name="sel_value" type="text" value="${hid_value }">
    	  <input id="hid_value" name="hid_value" type="hidden" value="${hid_value }">
    	  <input type="button" class="button1" onclick="document.getElementById('hid_condition').value=document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1)" value="��ѯ"/>
    	  <br/>
    <table cellpadding="0" cellspacing="0" width="100%">
    	<tr bgcolor='#8FABDE'>
    		<td width="10%">���</td>    		
    		<td width="20%">��ֳ������</td>
    		<td width="20%">���ڵ�</td>
    		<td width="15%">������</td>
    		<td width="15%">����</td>			
			<td width="10%">����</td>
    	</tr>
    	<c:forEach var="op_Scene" items="${page.result }" varStatus="n">
    		<tr>
    			<td>${n.count }&nbsp;</td>    			
    			<td>${op_Scene.scene_name }&nbsp;</td>	
    			<td>${op_Scene.scene_addr }&nbsp;</td>	
    			<td>${op_Scene.scene_creater }&nbsp;</td>
    			<td>
    				<c:if test="${op_Scene.scene_type == 1}">
						��ʩ԰��
					</c:if>
					<c:if test="${op_Scene.scene_type == 2}">
						ˮ����ֳ
					</c:if>
					<c:if test="${op_Scene.scene_type == 3}">
						������ֲ
					</c:if>
					<c:if test="${op_Scene.scene_type == 4}">
						������ֳ
					</c:if>
    			</td>	
				<td>
					<a href="fisherirs_edit.action?sceneId=${op_Scene.scene_id }">�༭</a>					
				</td>
    		</tr>
    	</c:forEach>
    </table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="5" align="left" valign="middle">
				<span style="padding-left: 10px; font-size: 14px;">
					��${page.totalCount}����¼��
					ÿҳ${page.pageSize}����
					��ǰ��${page.pageNo}ҳ��
					��${page.totalPages}ҳ
				</span>
			</td>
			<td colspan="6" align="right" valign="middle"
				style="padding-right: 10px; font-size: 14px;"
				class="more">
				<a href="javascript:gotoPage(1)">��&nbsp;&nbsp;ҳ</a>
				<a href="javascript:gotoPage(${page.prePage})">
					��һҳ
				</a>
				<a href="javascript:gotoPage(${page.nextPage})">
					��һҳ
				</a>
				<a href="javascript:gotoPage(${page.totalPages})">
					β&nbsp;&nbsp;ҳ
				</a> ��ת��
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
				ҳ
			</td>
		</tr>
	</table>
	<s:actionmessage theme="custom" cssClass="success"/>
	<script type="text/javascript">
		var sel_condition = document.getElementById('sel_condition');
		for(var i=0;i<sel_condition.options.length;i++){
			var op = sel_condition.options[i];
			if(op.value=='${hid_condition}'){
				op.selected="selected";
			}
		}
	</script>
  </body>
</html>
