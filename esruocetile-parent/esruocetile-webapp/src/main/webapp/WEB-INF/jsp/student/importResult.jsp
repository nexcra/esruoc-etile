<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }>>导入数据信息</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$(function() {

		$("#returnButton").click(
				function() {
					overrideSelectedTab('importDate', '导入学生数据',
							"data.do?action=toImportStudentData");
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


	<div class="pageBar"
		style="text-align: left; color: green; font-size: 14px; font-weight: bold">
		成功导入<span>${successImportCount }</span>条数据!<br />
	</div>

	<c:if test="${!empty duplicateStudentData }">
		<table width="100%" border="1" class="t1">
			<thead>
				<tr>
					<th><span
						style="color: red; font-size: 14px; font-weight: bold">以下数据由于学号重复，未能成功导入，请核查:</span></th>
				</tr>
			</thead>
		</table>

		<table width="100%" id="mytab" border="1" class="t1">
			<thead>
				<tr>
					<th>学号</th>
					<th>姓名</th>
					<th>学院</th>
					<th>年级</th>
					<th>性别</th>
					<th>身份证号</th>
					<th>专业</th>
				</tr>
			</thead>



			<c:forEach items="${duplicateStudentData }" var="list" varStatus="vs">
				<tr <c:if test="${vs.index %2==1 }">
					class="a1"
				</c:if>>
					<td>${list.studentNo }</td>
					<td>${list.name }</td>
					<td>${list.college }</td>
					<td>${list.grade }</td>
					<td>${list.gender }</td>
					<td>${list.idNo }</td>
					<td>${list.major }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<table width="100%" border="1" class="formTable">
		<tr>

			<td class="fieldForm" align="center"><input type="button"
				value="返回" class="submitButton" id="returnButton" /></td>
		</tr>
	</table>
</body>
</html>
