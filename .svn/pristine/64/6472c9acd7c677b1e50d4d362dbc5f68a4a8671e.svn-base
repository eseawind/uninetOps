<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/validate/jquery.validate.css"/>
	<script language="javascript" type="text/javascript" src="${ctx}/js/jquery-1.3.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/validate/jquery.metadata.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/validate/jquery.validate.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/kindeditor/kindeditor.js"></script>
	<style>
	    .tbl {
			border: 2px #bbd1fa solid;
			font-size:16px;
		}
		.tbl td {
			border: 1px #bbd1fa solid;
		}
		.tbl .tblright {
			background-color:#d2dcf0;
			width:20%;
			text-align:right;
		}
		.blue-button {
			letter-spacing:8px;
			line-height:15px;
			font-size:14px;
			background-color:#d2dcf0;
			border:1px #3788c0 solid;
			border-spacing:0px;
			padding-bottom:3px;
			padding-left:3px;
			padding-right:3px;
			padding-top:3px;
			color:#000000;	
		}
	    </style>
	<script type="text/javascript">
		KE.show({
                id : 'content',
    			allowFileManager : true,
    			skinType:'default',   
                autoOnsubmitMode:'true',
    			imageUploadJson : '${ctx}/systemInfo_uploadImage.action',
                afterCreate : function(id) {
					KE.event.ctrl(document, 13, function() {
						KE.sync(id);
						document.forms['example'].submit();
					});
					KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
						KE.sync(id);
						document.forms['example'].submit();
					});
				}
        });
	</script>
  </head>
  
  <body style="font-size:12px;width:100%;height:100%;overflow: auto;padding: 10px;">
			首页 》 
			系统信息配置
    <form action="systemInfo_update.action" method="post">
    	<table cellpadding="0" cellspacing="0" width="1020" class="tbl">
			<tr>
				<td>系统名称:</td>
				<td>
					<input type="text" name="systemInfo.headName" value="${systemInfo.headName}" size="80%"/>
					<input type="hidden" name="systemInfo.id" value="${systemInfo.id}"/>
				</td>
			</tr>
			<tr>
				<td>内容简介:</td>
				<td>
					<textarea id="content" name="systemInfo.content" style="width: 600px;height: 400px;" rows="" cols="">${systemInfo.content }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="提 交" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
					<input type="reset" value="返 回" onclick="history.go(-1);"/>
				</td>
			</tr>
    	</table>
    </form>
  </body>
</html>
