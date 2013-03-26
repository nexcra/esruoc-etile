<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看视频</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton").click(
				function() {
					overrideSelectedTabForSubPage('listVideo', '视频管理',
							'admin/video.do?action=listVideo');
				});

	});
</script>
</head>
<body>
	<table width="100%" border="1" class="formTable">
		<tr>
			<td class="fieldName" width="20%">视频名称:</td>
			<td class="fieldForm" width="80%">${map.name }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">来源:</td>
			<td class="fieldForm" width="80%"><c:if
					test="${map.source =='1' }">本站原创</c:if>
				<c:if test="${map.source =='2' }">网络转载</c:if></td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">类型:</td>
			<td class="fieldForm" width="80%"><c:if
					test="${map.type =='FLV' }">FLV</c:if>
				<c:if test="${map.type =='RM' }">RM</c:if>
				<c:if test="${map.type =='FLASH' }">FLASH</c:if>
				<c:if test="${map.type =='REAL' }">REAL</c:if></td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">描述:</td>
			<td class="fieldForm" width="80%">${map.description }</td>
		</tr>
		<tr>
			<td class="fieldName" width="20%">路径:</td>
			<td class="fieldForm" width="80%">${map.path }</td>
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
