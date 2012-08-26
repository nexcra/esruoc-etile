<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton").click(
				function() {
					overrideSelectedTab('listColumn', '文章管理',
							'column.do?action=listColumn');
				});

		if ($("#columnType").val() == '1') {
			$("#contentRow").show();
		}

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
			<td class="fieldName" width="100">栏目名称:</td>
			<td class="fieldForm" width="900">${map.column_name }</td>
		</tr>
		<tr>
			<td class="fieldName"  >在导航栏显示:</td>
			<td class="fieldForm"  ><c:if
					test="${map.show_on_nav == 0}">
					否
				</c:if> <c:if test="${map.show_on_nav == 1}">
					是
				</c:if></td>
		</tr>
		<tr>
			<td class="fieldName"  >类型:</td>
			<td class="fieldForm" ><input type="hidden"
				name="columnType" id="columnType" value="${map.column_type }" /> <c:if
					test="${map.column_type == 1}">
					单页面
				</c:if> <c:if test="${map.column_type == 2}">
					文章栏目
				</c:if></td>
		</tr>
		<tr id="contentRow" style="display: none">
			<td class="fieldName" >页面内容:</td>
			<td class="fieldForm" >${map.column_content}</td>
			<tr>
				<td class="fieldName" >序号:</td>
				<td class="fieldForm" >${map.order_num }</td>
			</tr>
			<tr>
				<td class="fieldName">说明:</td>
				<td class="fieldForm">${map.description }</td>
			</tr>
			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="button" value="返回"
					class="submitButton" id="returnButton" /></td>
			</tr>
	</table>
</body>
</html>
