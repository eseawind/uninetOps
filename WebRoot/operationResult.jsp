<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>操作结果</title>
	</head>
	<body>
		<form action="">
			<table>
				<tr>
					<td>
						<c:if test="${result == 'success'}">
							<h1>恭喜您！操作成功！<br/>${sms}</h1>							
						</c:if>
						<c:if test="${result == null}">
							<h1 style="color: red;">对不起，此操作失败，请重新检查后再操作！<br/>${sms}</h1>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<a href="${ctx }/${list }">继续操作</a>
					</td>
				</tr>
			</table>
			${desc }
		</form>
	</body>
</html>
