<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.unism.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>加载模板信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="stylesheet" href="${ctx}/css/style_shuichan.css" type="text/css" />
		<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/lhgdialog/lhgcore.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/uuid.js"></script>
			
		<script type="text/javascript">
			function gotoPage(no) {
				window.location.href = "${ctx}/devSetup_loadTemplate.action?page.pageNo=" + no ;
			}
			
			var DG = frameElement.lhgDG;
			
			function loadTemplate(devst_template) {
				DG.curDoc.getElementById('devst_template').value = devst_template;
				DG.cancel();
				DG.curWin.loadSet();			
			}
			//分页查询
			function gotoPage(pageNo){	
				var hid_condition = document.getElementById("hid_condition").value;
				var hid_value = document.getElementById("hid_value").value;
				//alert(queryValue);	
				window.location.href = "${ctx}/devSetup_loadTemplate.action?page.pageNo=" + pageNo + "&page.pageSize=10&hid_condition=" + encodeURI(hid_condition)+"&hid_value="+encodeURI(hid_value);
			}
			//删除
			function Del(id){
				if(confirm("你确定要是删除吗？")){
					$.getJSON("${ctx}/devSetup_delete.action?id="+id,{						
						random:Math.random()
					},function(isSuc){
						alert(isSuc.message);
						location.reload();
					});	
				}							
			}
		</script>
	</head>

	<body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
		查询条件:<select id="sel_condition" name="sel_condition" onchange="document.getElementById('sel_value').value='';">
          	<option value="devst_name">模板名称</option>
          	<option value="devst_author">创建者</option>
          </select>
          <input id="hid_condition" type="hidden" value="${hid_condition }">
          值:
  				<input id="sel_value" type="text" value="${hid_value }">
  				<input id="hid_value" type="hidden" value="${hid_value }">
  				<input type="button" onclick="document.getElementById('hid_condition').value=document.getElementById('sel_condition').value;document.getElementById('hid_value').value=document.getElementById('sel_value').value;gotoPage(1)" value="查询" >
		<table cellpadding="0" cellspacing="0" width="100%" class="senfe1">
			<tr class="list_head">
				<td>模板名称</td>
				<td>创建者</td>
				<td>创建时间</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${page.result}" var="devSetupTemplate" varStatus="n">
				<tr align="center">
					<td>${devSetupTemplate.devst_name}&nbsp;
						<input id="devst_template_${n.count}" name="devst_template" type="hidden" value="${devSetupTemplate.devst_template}"/>
					</td>
					<td>${devSetupTemplate.devst_author}&nbsp;</td>
					<td>${devSetupTemplate.devst_createDateTime}&nbsp;</td>
					<td>
						<a onclick="loadTemplate(document.getElementById('devst_template_${n.count}').value)" style="cursor: hand">加载</a>
						<a onclick="Del('${devSetupTemplate.devst_id}')" style="cursor: hand">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="5" align="left" valign="middle">
				<span style="padding-left: 10px; font-size: 12px;">
					共${page.totalCount}条记录，
					每页${page.pageSize}条，
					当前第${page.pageNo}页，
					共${page.totalPages}页
				</span>
			</td>
			<td colspan="6" align="right" valign="middle"
				style="padding-right: 10px; font-size: 12px;"
				class="more">
				<a href="javascript:gotoPage(1)">首&nbsp;&nbsp;页</a>
				<a href="javascript:gotoPage(${page.prePage})">
					上一页
				</a>
				<a href="javascript:gotoPage(${page.nextPage})">
					下一页
				</a>
				<a href="javascript:gotoPage(${page.totalPages})">
					尾&nbsp;&nbsp;页
				</a> 跳转到
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
				页
			</td>
		</tr>
	</table>
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
