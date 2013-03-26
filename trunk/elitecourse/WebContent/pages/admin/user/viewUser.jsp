<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看用户</title>
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
									'listUser',
									'用户管理',
									'admin/user.do?action=listUser');
						});

	});
</script>
</head>
<body>
	<table width="100%" border="1" class="formTable">
		<tr>
			<td class="fieldName" width="20%">用户名:</td>
			<td class="fieldForm" width="80%">${map.name }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">真实姓名:</td>
			<td class="fieldForm" width="80%">${map.real_name }</td>
		</tr>
	 
		<tr>
			<td class="fieldName" width="20%">性别:</td>
			<td class="fieldForm" width="80%"><c:if test="${map.gender =='male'}">男</c:if> <c:if
						test="${map.gender =='female'}">女</c:if></td>
		</tr>
		<%-- <tr>
			<td class="fieldName" width="20%">origin:</td>
			<td class="fieldForm" width="80%">${map.origin }</td>
		</tr> --%>
		<tr>
			<td class="fieldName" width="20%">出生日期:</td>
			<td class="fieldForm" width="80%"><fmt:formatDate
					value='${map.birth_date }' pattern='yyyy-MM-dd' /></td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">组:</td>
			<td class="fieldForm" width="80%">${map.group_id }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">邮箱:</td>
			<td class="fieldForm" width="80%">${map.email }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">电话:</td>
			<td class="fieldForm" width="80%">${map.telephone }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">身份:</td>
			<td class="fieldForm" width="80%"><c:if test="${map.is_admin =='manager'}">管理员</c:if> <c:if
						test="${map.is_admin =='student'}">学生</c:if></td>
		</tr>
		<%-- <tr>
			<td class="fieldName" width="20%">is_locked:</td>
			<td class="fieldForm" width="80%">${map.is_locked }</td>
		</tr> --%>
		<tr>
			<td class="fieldName" width="20%">年龄:</td>
			<td class="fieldForm" width="80%">${map.age }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">角色:</td>
			<td class="fieldForm" width="80%">${map.role }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">班级:</td>
			<td class="fieldForm" width="80%">${map.classes_id }</td>
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
			<td class="fieldName" width="20%">更新用户名:</td>
			<td class="fieldForm" width="80%">${map.update_user_name }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">更新时间:</td>
			<td class="fieldForm" width="80%"><fmt:formatDate
					value='${map.update_time }' pattern='yyyy-MM-dd HH:mm:ss' /></td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">备注:</td>
			<td class="fieldForm" width="80%">${map.remark }</td>
		</tr>
		<tr>
			<td class="fieldName"></td>
			<td class="fieldForm"><input type="button" id="returnButton"
				value="返回" class="submitButton" /></td>
		</tr>
	</table>
</body>
</html>
