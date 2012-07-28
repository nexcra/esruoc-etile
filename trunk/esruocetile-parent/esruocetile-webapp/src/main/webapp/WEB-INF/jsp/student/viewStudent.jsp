<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton").click(
				function() {
					overrideSelectedTab('listStudent', '学生信息管理',
							'student.do?action=listStudent');
				});

	});
	/**
	 *  调用父窗口的overrideSelectedTab方法打开
	 * @param tabid tab的ID
	 * @param text 要显示的文本
	 * @param url 打开的url地址
	 */
	function overrideSelectedTab(tabid, text, url) {
		window.parent.overrideSelectedTab(tabid, text, url);
	}
</script>
</head>
<body>
	<table width="100%" border="1" class="formTable">
		<tr>
			<td class="fieldName" width="20%">学号:</td>
			<td class="fieldForm" width="80%">${map.student_no }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">姓名:</td>
			<td class="fieldForm" width="80%">${map.name }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">性别:</td>
			<td class="fieldForm" width="80%">${map.gender }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">民族:</td>
			<td class="fieldForm" width="80%">${map.nationality }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">民族代码:</td>
			<td class="fieldForm" width="80%">${map.nationality_code }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">出生日期:</td>
			<td class="fieldForm" width="80%"><fmt:formatDate
					value="${map.date_of_birth }" pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">身份证号:</td>
			<td class="fieldForm" width="80%">${map.id_no }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">专业:</td>
			<td class="fieldForm" width="80%">${map.major }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">行政班:</td>
			<td class="fieldForm" width="80%">${map.executive_class }</td>
		</tr>

		<tr>
			<td class="fieldName"></td>
			<td class="fieldForm"><input type="button" value="返回"
				class="submitButton" id="returnButton" /></td>
		</tr>
	</table>
</body>
</html>
