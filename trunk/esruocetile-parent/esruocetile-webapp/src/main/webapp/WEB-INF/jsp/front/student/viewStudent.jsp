<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/blue.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton").click(function() {
			history.back(-1);
		});
	});
</script>
</head>
<body>
	<%@include file="/common/top.jsp"%>
	<div class="main">
		<%@include file="/common/left.jsp"%>
		<div class="right">
		<%@include file="/common/frontLogin.jsp"%> 
			<div class="path">
				您现在所在的位置：<a href="${ctx }">首页</a>&nbsp;&gt;&gt; 个人信息&nbsp;&gt;&gt;
			</div>
			<div class="column_article_list">
				<div class="column_name">
					<h2>个人信息</h2>
				</div>
				<div class="article_list">

					<table width="100%" border="1"
						class="test_booking_list_table student_booking_table">
						<tr>
							<td class="fieldName" width="20%">学号:</td>
							<td class="fieldForm" width="80%">${map.student_no }</td>
						</tr>
						<tr>
							<td class="fieldName" width="20%">姓名:</td>
							<td class="fieldForm" width="80%">${map.name }</td>
						</tr>
						<tr>
							<td class="fieldName" width="20%">学院:</td>
							<td class="fieldForm" width="80%">${map.college }</td>
						</tr>
						<tr>
							<td class="fieldName" width="20%">年级:</td>
							<td class="fieldForm" width="80%">${map.grade }</td>
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
							<td class="fieldForm"><input type="button" id="returnButton" value="返回"
								class="button"  /></td>
						</tr>
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>
