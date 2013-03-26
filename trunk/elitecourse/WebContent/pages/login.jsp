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
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jQuery.validate.plugIn.js"></script>
<script type="text/javascript">
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
					remote : "验证码错误(不区分大小写)"
				}
			}
		});
		var loginType = $(':input:radio:checked').val();

		if ("student" == loginType) {
			$(".hint").show();
		} else {
			$(".hint").hide();
		}

		$(':input:radio').click(function() {
			var loginType = $(':input:radio:checked').val();
			if ("student" == loginType) {
				$(".hint").show();
			} else {
				$(".hint").hide();
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
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	font-size: 12px;
	background: url(images/skin/default/login/bg.jpg) top repeat-x;
}

.input {
	width: 150px;
	height: 17px;
	border-top: 1px solid #404040;
	border-left: 1px solid #404040;
	border-right: 1px solid #D4D0C8;
	border-bottom: 1px solid #D4D0C8;
}
</style>
</head>
<body>
	<div
		style="position: absolute; z-index: 19700; top: -1970px; left: -1970px; display: none;">
		<iframe src="${ctx }/pages/template/default/images/My97DatePicker.htm"
			frameborder="0" border="0" scrolling="no"
			style="width: 186px; height: 199px;"></iframe>
	</div>
	<form name="loginForm" id="loginForm"
		action="${ctx }/login.do?action=processLogin" method="post">
		<table width="750" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tbody>
				<tr>
					<td height="100">&nbsp;</td>
				</tr>
				<tr>
					<c:if test="${error !=null }">
						<div class="loginError">${error }</div>
					</c:if>
					<c:if test="${message !=null }">
						<div class="loginError">${message }</div>
					</c:if>
				</tr>
				<tr>
					<td><table width="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tbody>
								<tr>
									<td width="423" height="280" valign="top"><table
											width="100%" border="0" cellspacing="0" cellpadding="0">
											<tbody>
												<tr>
													<td><img
														src="${ctx }/pages/template/default/images/ltop.jpg"></td>
												</tr>
												<tr>
													<td><img
														src="${ctx }/pages/template/default/images/llogo.jpg"></td>
												</tr>
											</tbody>
										</table></td>
									<td width="40" align="center" valign="bottom"><img
										src="${ctx }/pages/template/default/images/line.jpg"
										width="23" height="232"></td>
									<td valign="top"><table width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tbody>
												<tr>
													<td height="90" align="center" valign="bottom"><img
														src="${ctx }/pages/template/default/images/ltitle.jpg"></td>
												</tr>
												<tr>
													<td>
														<div></div>
														<table width="100%" border="0" align="center"
															cellpadding="0" cellspacing="5">
															<tbody>
																<tr>
																	<td width="91" height="30" align="right"><strong>
																			用户名：</strong></td>
																	<td width="211"><input type="input" id="name" value="admin"
																		name="name" maxlength="100" class="input"></td>
																</tr>
																<tr>
																	<td height="30" align="right"><strong>密码：</strong></td>
																	<td><input name="password" type="password" value="bookingAdmin"
																		id="password" maxlength="32" class="input"></td>
																</tr>
																<tr>
																	<td height="30" align="right"><strong>验证码：</strong></td>
																	<td><input maxlength="18" id="checkCode" value="努力学习英语"
																		style="float: left; width: 80px;" name="checkCode"><img
																			style="float: left" src="checkCodeImg"
																			id="checkCodeImg" class="checkCodeImg"
																			title="看不清的话请单击刷新（不区分大小写）" /></td>
																</tr>
																<tr>

																	<td height="30" colspan="2" align="center"><input
																		type="image"
																		src="${ctx }/pages/template/default/images/login.jpg"
																		name="submit"> &nbsp; &nbsp;</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table></td>
								</tr>
							</tbody>
						</table></td>
				</tr>
			</tbody>
		</table>
	</form>
	<p
		style="text-align: center; width: 100%; height: 40px; margin-top: 120px;">技术支持：姚淑仪</p>



</body>
</html>
