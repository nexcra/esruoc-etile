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
<script type="text/javascript" src="${ctx }/js/common.js"></script>

<script type="text/javascript">
	$(function() {
		$("#returnButton").click(
				function() {
					overrideSelectedTabForSubPage('listUser', '用户管理',
							'admin/user.do?action=listUser');
				});

	});
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
						style="color: red; font-size: 14px; font-weight: bold">以下数据由于用户名重复，未能成功导入，请核查:</span></th>
				</tr>
			</thead>
		</table>

		<table width="100%" id="mytab" border="1" class="t1">
			<thead>
				<tr>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>学号</th>
					<th>性别</th>
					<th>出生年月</th>
					<th>邮箱</th>
					<th>电话</th>
					<th>班级</th>
					<th>年龄</th>
					<th>备注</th>
				</tr>
			</thead>



			<c:forEach items="${duplicateStudentData }" var="list" varStatus="vs">
				<tr <c:if test="${vs.index %2==1 }">
					class="a1"
				</c:if>>
					<td>${list.name }</td>
					<td>${list.realName }</td>
					<td>${list.studentNo}</td>
					<td><c:if test="${list.gender =='male' }">男</c:if> <c:if
							test="${list.gender =='female' }">女</c:if></td>
					<td>${list.birthDate }</td>
					<td>${list.email }</td>
					<td>${list.telephone }</td>
					<td>${list.classesId }</td>
					<td>${list.age }</td>
					<td>${list.remark }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${!empty failToUpdateStudentData }">
		<table width="100%" border="1" class="t1">
			<thead>
				<tr>
					<th><span
						style="color: red; font-size: 14px; font-weight: bold">以下数据可能由于该用户名已存在，未能成功导入，请核查:</span></th>
				</tr>
			</thead>
		</table>

		<table width="100%" id="mytab" border="1" class="t1">
			<thead>
				<tr>
					<th>用户名</th>
				</tr>
			</thead>



			<c:forEach items="${failToUpdateStudentData }" var="list"
				varStatus="vs">
				<tr <c:if test="${vs.index %2==1 }">
					class="a1"
				</c:if>>
					<td>${list.name }</td>
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
