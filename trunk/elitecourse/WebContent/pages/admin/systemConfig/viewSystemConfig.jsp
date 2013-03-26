<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看系统配置</title>
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
									'listSystemConfig',
									'系统配置管理',
									'admin/systemConfig.do?action=listSystemConfig');
						});

	});
</script>
</head>
<body>
	<table width="100%" border="1" class="formTable">
		<tr>
			<td class="fieldName" width="20%">网站名字:</td>
			<td class="fieldForm" width="80%">${map.site_name }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">版权:</td>
			<td class="fieldForm" width="80%">${map.copyright }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">网站所有者:</td>
			<td class="fieldForm" width="80%">${map.site_owner }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">联系电话:</td>
			<td class="fieldForm" width="80%">${map.contact_phone }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">联系邮件:</td>
			<td class="fieldForm" width="80%">${map.contact_email }</td>
		</tr>
		 
		<tr>
			<td class="fieldName"></td>
			<td class="fieldForm"><input type="button" id="returnButton"
				value="返回" class="submitButton" /></td>
		</tr>
	</table>
</body>
</html>
