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
<script type="text/javascript">
	$(function() {
		$("#cancelBooking").click(function() {
			if (confirm("您确定取消预约吗？")) {
				return true;
			} else {
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div class="left">
		<div class="left_top">
			现在是:<span id="time"></span>
		</div>
		<div class="left_main" style="position: relative">
			<c:if test="${sessionScope.studentId !=null }">
				<div id="login_success">
					<div>
						欢迎您：${sessionScope.studentName }同学!
						<c:if test="${  empty sessionScope.bookingList   }">
							<span class="warn">温馨提示：您还没有进行测试预约!</span>
						</c:if>
					</div>
					<c:if test="${ !empty sessionScope.bookingList   }">
						<c:forEach items="${sessionScope.bookingList }" var="list"
							varStatus="vs">
							<div>
								您已预约：<a
									href="${ctx }/testBooking.do?action=viewTestBookingForStudent&id=${list.test_booking_id}">【
									${list.test_booking_name } 】</a>
							</div>

							<c:choose>
								<c:when test="${ list.end_booking > 0 }">

									<div>可修改预约测试时间或者取消!</div>
									<div>
										<a id="cancelBooking"
											href="studentTestBooking.do?action=cancelBooking&oldTestBookingId=${list.test_booking_id }">
											【取消预约】</a>
									</div>
								</c:when>
								<c:otherwise>
									<div>
										<span class="warn">您预约的测试已进行或者安排测试中，不能修改或者取消本次预约!</span>
									</div>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</c:if>
					<div>
						<a href="testBooking.do?action=listTestBookingForStudent">
							【测试预约信息】</a>
					</div>
					<div>
						<a
							href="student.do?action=toPersonalDetail&id=${sessionScope.studentId }">
							【个人详细信息】</a>
					</div>
					<div>
						<a
							href="${ctx }/config.do?action=viewConfig&code=bookingDescription">【预约说明】</a>
					</div>

					<div>
						<a href="${ctx }/login.do?action=logoutForStudent"> 【安全退出登录】</a>
					</div>
				</div>
			</c:if>
			<div class="left_button">
				<c:if test="${sessionScope.studentId ==null }">
					<h2 style="padding-top: 10px">
						<a href="http://jwc.scnu.edu.cn/" target="_blank" title="点击报名">
							<img src="${ctx }/images/skin/blue/front/enroll_system.jpg" />
						</a>
					</h2>
					<h2>
						<a href="#" id="test_booking_login" title="点击登录"> <img
							src="${ctx }/images/skin/blue/front/booking_system.jpg" />
						</a>
					</h2>
				</c:if>

			</div>
			<div style="height: 5px;"></div>
			<div class="left_nav">
				<ul>

					<c:forEach items="${ requestScope.columnList }" var="columnList">
						<li><a
							href="${ctx }/column.do?action=listArticle&columnId=${columnList.id}&columnType=${columnList.columnType }">${columnList.columnName
								}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="left_bottom"></div>
	</div>
</body>
</html>