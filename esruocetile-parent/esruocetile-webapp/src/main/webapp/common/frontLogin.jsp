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
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript">
	$(function() {
		$("#test_booking_login").click(function() {
			if ($("#user_login").hasClass("show")) {
				$("#user_login").removeClass("show");
				$("#user_login").hide();
				$(this).removeClass("to_login");
			} else {
				$("#user_login").addClass("show");
				$("#user_login").show();
				$(this).addClass("to_login");
				var loginType = $(':input:radio:checked').val();
				if ("student" == loginType) {
					$(".hint").show();
				}
			}
		})

		$(':input:radio').click(function() {
			var loginType = $(':input:radio:checked').val();
			if ("student" == loginType) {
				$(".hint").show();
			} else {
				$(".hint").hide();
			}
		});

		$("#close_login").click(function() {
			$("#user_login").removeClass("show");
			$("#user_login").hide();
			$("#test_booking_login").removeClass("to_login");
		});
		
		

	});
	$(function() {
		//验证表单
		$("#loginForm").validate({
			rules : {
				loginType : {
					required : true
				},
				name : {
					required : true
				},
				password : {
					required : true
				},
				checkCode : {
					required : true,
					remote : "login.do?action=isCheckCodeValid"
				}
			},
			messages : {
				checkCode : {
					remote : "验证码错误,不分大小写"
				}
			}
		});

		//加载验证码
		$("#checkCodeImg").attr("src", 'checkCode?now=' + new Date())
		//单击刷新验证码
		.click(function() {
			$(this).attr("src", 'checkCode?now=' + new Date());
		});

	});
</script>


</head>

<div class="user_login" id="user_login">
	<form name="loginForm" id="loginForm"
		action="${ctx }/login.do?action=processLogin" method="post">
		<table border="0" cellspacing="0" cellpadding="0" class="login_table">

			<tr>
				<td align="right" width="100">类&nbsp;&nbsp;型：</td>
				<td width="300"><input name="loginType" value="student"
					type="radio" checked="checked" /> 学生 <input name="loginType"
					value="teacher" type="radio" class="" /> 老师</td>
			</tr>

			<tr>
				<td align="right">用户名：</td>
				<td><input name="name" type="text" class="input_text" id="name" /><span
					class="hint" style="display: none">&nbsp;请输入学号</span></td>
			</tr>

			<tr>
				<td align="right">密&nbsp;&nbsp;码：</td>
				<td><input name="password" type="password" id="password"
					class="input_text" /><span class="hint" style="display: none">&nbsp;请输入身份证号</span></td>
			</tr>
			<tr>
				<td align="right" width="100">验证码：</td>
				<td width="300"><input name="checkCode" type="text"
					id="checkCode" class="input_text_checkcode" /> <img
					src="checkCodeImg" id="checkCodeImg" class="checkCodeImg"
					title="看不清的话请单击刷新（不区分大小写）" align="top" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input class="button"
					type="submit" value="登录" /> <input class="button" type="reset"
					value="重置" /> <input class="button" type="button" id="close_login"
					value="关闭" /></td>
			</tr>
			<tr>
				<td colspan="2" ><div class="bookingStep">
						<a target="_blank"
							href="${ctx }/config.do?action=viewConfig&code=bookingDescription"  style="font-weight:bold">&nbsp;预约说明  &gt;&gt;</a>
					</div></td>
			</tr>
		</table>
	</form>
</div>