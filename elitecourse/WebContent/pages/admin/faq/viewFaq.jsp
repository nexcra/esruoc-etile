<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看常见问题</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'listFaq',
									'常见问题管理',
									'admin/faq.do?action=listFaq');
						});

	});
</script>
</head>
<body>
	<table width="100%" border="1" class="formTable">
		<tr>
			<td class="fieldName" width="20%">问题:</td>
			<td class="fieldForm" width="80%">${map.question }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">答案:</td>
			<td class="fieldForm" width="80%">${map.answer }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">参考资料:</td>
			<td class="fieldForm" width="80%">${map.reference }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">创建用户名:</td>
			<td class="fieldForm" width="80%">${map.create_user_name }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">创建时间:</td>
			<td class="fieldForm" width="80%"><fmt:formatDate
					value='${map.create_time }' pattern='yyyy-MM-dd HH:mm:ss' /></td>
		</tr>
		<tr>
			<td class="fieldName"></td>
			<td class="fieldForm"><input type="button" id="returnButton"
				value="返回" class="submitButton" /></td>
		</tr>
	</table>
</body>
</html>
