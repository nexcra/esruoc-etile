<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改答疑</title>
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

<script type="text/javascript" src="${ctx }/widget/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton")
				.click(
						function() {
							overrideSelectedTabForSubPage('listQuestionAnswer',
									'答疑管理',
									'admin/questionAnswer.do?action=listQuestionAnswer');
						});

	});

	$(function() {
		$("#updateForm").validate({
			rules : {
				id : {
					required : true
				},
				title : {
					required : true
				},
				content : {
					required : true
				},
				createUserIp : {
					required : true
				},
				reContent : {
					required : true
				},
				reUserId : {
					required : true,
					digits : true
				},
				reUserName : {
					required : true
				},
				reTime : {
					required : true
				}
			},
			messages : {

			}
		})
	});
</script>
</head>
<body>
	<form
		action="${ctx }/admin/questionAnswer.do?action=updateQuestionAnswer"
		name="updateForm" id="updateForm" method="post">
		<input type="hidden" name="id" id="id" value="${map.id }" />
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>

			<tr>
				<td class="fieldName" width="20%">标题:</td>
				<td class="fieldForm" width="80%">${map.title }</td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">疑问内容:</td>
				<td class="fieldForm" width="80%">${map.content}</td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">提问用户:</td>
				<td class="fieldForm" width="80%">${map.create_user_name }</td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">提问用户IP:</td>
				<td class="fieldForm" width="80%">${map.create_user_ip }</td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">回复内容:</td>
				<td class="fieldForm" width="80%"><textarea name="reContent"
						id="reContent">${map.re_content }</textarea><span class="asterisk">*</span></td>
			</tr>

			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="回复"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /> <input type="button" id="returnButton"
					value="返回" class="submitButton" /></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		CKEDITOR.replace('reContent', {
			filebrowserUploadUrl : 'ckeditor/uploader?Type=File',
			filebrowserImageUploadUrl : 'ckeditor/uploader?Type=Image',
			filebrowserFlashUploadUrl : 'ckeditor/uploader?Type=Flash'
		});
	</script>
</body>
</html>