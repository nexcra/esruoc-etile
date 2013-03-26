<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增@tableDescription@</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jQuery.validate.plugIn.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
@fckeditorJavaScript@
<script type="text/javascript" src="${ctx }/js/common.js"></script>

<script type="text/javascript">
	$(function() {
		$("#returnButton").click(
				function() {
					overrideSelectedTabForSubPage('list@upperBeanName@', '@tableDescription@管理',
							'admin/@lowerBeanName@.do?action=list@upperBeanName@');
				});

	});

	$(function() {
		$("#addForm").validate({
			rules : {
				id : {
					required : true
				}
				@formFieldValidationRules@
			},
			messages : {

			}
		})
	});
</script>
</head>
<body>
	<form
		action="${ctx }/admin/@lowerBeanName@.do?action=add@upperBeanName@"
		name="addForm" id="addForm" method="post">
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>
			@formFieldsForAddJsp@
			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="添加"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /> <input type="button" id="returnButton"
					value="返回" class="submitButton" /></td>
			</tr>
		</table>
	</form>
	@fckeditorJavaScriptReplace@
</body>
</html>
