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
	jQuery.validator.addMethod("regex", //addMethod第1个参数:方法名称
	function(value, element, params) { //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）
		var exp = new RegExp(params); //实例化正则对象，参数为传入的正则表达式
		return exp.test(value); //测试是否匹配
	}, "格式错误"); //addMethod第3个参数:默认错误信息

	$(function() {
		$("#addForm").validate({
			rules : {
				name : {
					required : true,
					regex : "^[a-zA-Z_]{5,15}\s*$",
					remote : "user.do?action=isUserExist"

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
				name : {
					regex : "请输入以字母、下划线组成的5-30个字符",
					remote : "该用户名已存在，请使用其他名字"
				}
			}
		});
	});
</script>
</head>
<body>
	<form action="${ctx }/user.do?action=addUser" name="addForm"
		id="addForm" method="post">
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName" width="40%">用户名:</td>
				<td class="fieldForm" width="60%"><input type="text"
					name="name" id="name" /><span class="asterisk">*</span></td>
			</tr>

			<tr>
				<td class="fieldName">密码:</td>
				<td class="fieldForm"><input type="password" name="password"
					id="password" /><span class="asterisk">*</span></td>
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
