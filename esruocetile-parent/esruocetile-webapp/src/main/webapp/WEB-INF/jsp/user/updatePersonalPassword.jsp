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
				lastPassword : {
					required : true,
					remote : {
						url : "user.do?action=checkLastPassword",
						data : {
							id : function() {
								return $("#id").val();
							}
						}
					}
				},
				password : {
					required : true
				},
				rePassword : {
					required : true,
					equalTo : "#password"
				}
			},
			messages : {
				lastPassword : {
					remote : "原密码错误,请重新输入"
				}
			}
		});
	});
</script>
</head>
<body>
	<div class="functionList">您正在操作：修改个人密码</div>
	<form action="${ctx }/user.do?action=updateUser" name="updateForm"
		id="updateForm" method="post">
		<input type="hidden" name="id" id="id" value="${adminId }" /> <input
			type="hidden" name="name" id="name" value="${adminName }" />
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName" width="40%">原密码:</td>
				<td class="fieldForm" width="60%"><input type="password"
					name="lastPassword" id="lastPassword" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="40%">密码:</td>
				<td class="fieldForm" width="60%"><input type="password"
					name="password" id="password" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName">重复密码:</td>
				<td class="fieldForm"><input type="password" name="rePassword"
					id="rePassword" /><span class="asterisk">*</span></td>
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
