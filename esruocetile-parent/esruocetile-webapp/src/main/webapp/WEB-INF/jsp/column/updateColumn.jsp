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
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript">
$(function() {
	$("#updateForm").validate({
		rules : {
			columnName : {
				required : true 
			} 
		}
	});
});
</script>
</head>
<body>
	<!-- <div class="functionList">您正在操作：修改栏目</div>-->
	<form action="${ctx }/column.do?action=updateColumn" name="updateForm"
		id="updateForm" method="post">
		<input type="hidden" name="id" id="id" value="${map.id }" />
		<input type="hidden" name="orderNum" value="2" />
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName" width="40%">栏目名称:</td>
				<td class="fieldForm" width="60%"><input type="text"
					name="columnName" id="columnName" value="${map.column_name }"/><span class="asterisk">*</span></td>
			</tr>
			<!-- 
			<tr>
				<td class="fieldName">是否单页面:</td>
				<td class="fieldForm"><input type="radio" name="columnType"
					value="2" />是 <input type="radio" name="columnType" value="1"
					checked="checked" />否<span class="asterisk">*</span</td>
			</tr>
-->
			<tr>
				<td class="fieldName">说明:</td>
				<td class="fieldForm"><textarea cols="40" rows="5"
						name="description" id="description" >${map.description }</textarea></td>
			</tr>
			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="提交"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
