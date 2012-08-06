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
				<li><a href="http://jwc.scnu.edu.cn/" target="_blank">普通话水平测试网上报名系统</a></li>

				<c:if test="${sessionScope.studentId ==null }">
					<li><a href="#" id="test_booking_login">普通话水平测试网上预约系统</a></li>
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