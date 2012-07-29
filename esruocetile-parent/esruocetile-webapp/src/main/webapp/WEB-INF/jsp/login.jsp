<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }>>后台登录</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/login.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript">
	$(function() {
		//验证表单
		$("#loginForm").validate({
			rules : {
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
					remote : "验证码错误(不区分大小写)"
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
<body>
	<form name="loginForm" id="loginForm"
		action="${ctx }/login.do?action=processLogin" method="post">
		<div id="wrapper">
			<div id="loginTitle">
				<img src="${ctx }/images/skin/default/login/loginTitle.jpg" />
			</div>
			<div id="loginTop">
				<img src="${ctx }/images/skin/default/login/loginTop.jpg" />
			</div>
			<div id="loginFrame">
				<c:if test="${error !=null }">
					<div class="loginError">${error }</div>
				</c:if>
				<div class="loginInput">


					<div class="usernameIcon">用户名:</div>
					<div class="usernameInput">
						<input type="text" name="name" id="name" class="username" />
					</div>
				</div>
				<div class="cb"></div>
				<div class="loginInput">
					<div class="passwordIcon">密&nbsp;码：</div>
					<div class="passwordInput">
						<input type="password" name="password" id="password"
							class="password" />
					</div>
				</div>

				<div class="loginInput">
					<div class="checkCodeIcon">验证码:</div>
					<div class="passwordInput">
						<input type="text" name="checkCode" id="checkCode"
							class="checkCode" /><img src="checkCodeImg" id="checkCodeImg"
							class="checkCodeImg" title="看不清的话请单击刷新（不区分大小写）" />
					</div>
				</div>
				<div class="cb"></div>
				<div class="loginButton">
					<input type="submit" class="loginSubmitButton" value="" /> <input
						type="reset" class="loginResetButton" value="" />
				</div>

			</div>
			<div id="loginBottom">
				<img src="${ctx }/images/skin/default/login/loginBottom.jpg" />
			</div>
		</div>
	</form>
</body>
</html>
