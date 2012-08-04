<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>left</title>
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
			}
		})
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
	<div class="left">
		<div class="left_top"></div>
		<div class="left_main" style="position: relative">
			<c:if test="${sessionScope.studentId !=null }">
				<div id="login_success">
					<div>
						欢迎您：${sessionScope.studentName }同学!
						<c:if test="${  empty sessionScope.bookingList   }">
							<span class="warn">温馨提示：您还没有进行考试预约!</span>
						</c:if>
					</div>
					<c:if test="${ !empty sessionScope.bookingList   }">
						<div>
							您已预约：【
							<c:forEach items="${sessionScope.bookingList }" var="list"
								varStatus="vs">
						${list.test_booking_name }
						
						
					</c:forEach>
							】
						</div>
						<div>可修改预约考试时间!</div>
						<div>
							<a href="studentTestBooking.do?action=cancelBooking"> 【取消预约】</a>
						</div>
					</c:if>
					<div>
						<a href="testBooking.do?action=listTestBookingForStudent">
							【考试预约信息】</a>
					</div>
					<div>
						<a
							href="student.do?action=toPersonalDetail&id=${sessionScope.studentId }">
							【个人详细信息】</a>
					</div>

					<div>
						<a href="${ctx }/login.do?action=logoutForStudent"> 【安全退出登录】</a>
					</div>
				</div>
			</c:if>
			<ul>
				<li><a href="http://jwc.scnu.edu.cn/" target="_blank">普通话水平考试报名系统</a></li>

				<c:if test="${sessionScope.studentId ==null }">
					<li><a href="#" id="test_booking_login">普通话水平考试预约系统</a></li>
					<div class="user_login" id="user_login">
						<form name="loginForm" id="loginForm"
							action="${ctx }/login.do?action=processLogin" method="post">
							<table border="0" cellspacing="0" cellpadding="0"
								class="login_table">

								<tr>
									<td width="50" align="right">类&nbsp;&nbsp;型：</td>
									<td width="350"><input name="loginType" value="student"
										type="radio" checked="checked" /> 学生 <input name="loginType"
										value="teacher" type="radio" class="" /> 老师</td>
								</tr>

								<tr>
									<td align="right">用户名：</td>
									<td><input name="name" type="text" class="input_text"
										id="name" value="20092301169" /></td>
								</tr>
								<tr>
									<td align="right">密&nbsp;&nbsp;码：</td>
									<td><input name="password" type="password" id="password"
										class="input_text" value="450981199010245469" /></td>
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
										type="submit" value="登录" /> <input class="button"
										type="reset" value="重置" /> <input class="button"
										type="button" id="close_login" value="关闭" /></td>
								</tr>
							</table>
						</form>
					</div>

				</c:if>
				<c:forEach items="${sessionScope.columnList }" var="columnList">
					<li><a
						href="${ctx }/column.do?action=listArticle&columnId=${columnList.id}&columnName=${columnList.columnName }">${columnList.columnName
							}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="left_bottom"></div>
	</div>
</body>
</html>