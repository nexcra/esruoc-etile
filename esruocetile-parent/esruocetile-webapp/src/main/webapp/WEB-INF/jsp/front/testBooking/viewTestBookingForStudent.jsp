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
<script type="text/javascript">
	$(function() {
		$("#returnButton").click(function() {
			history.back(-1);
		});

		$("#submitBooking").click(function() {
			var isBooking = $("#isBooking").val(), confirmMessage = "您确定预约吗？";
			if ("yes" == isBooking) {
				confirmMessage = "您之前已有预约信息，确定要修改预约吗？";
			}

			if (confirm(confirmMessage)) {
				return true;
			} else {
				return false;
			}
		});
	});
</script>
</head>
<body>
	<%@include file="/common/top.jsp"%>
	<div class="main">
		<%@include file="/common/left.jsp"%>
		<div class="right">
			<%@include file="/common/frontLogin.jsp"%>
			<div class="path">
				您现在所在的位置：<a href="${ctx }">首页</a>&nbsp;&gt;&gt; 查看预约信息
			</div>
			<div class="column_article_list">
				<div class="column_name">
					<h2>预约信息</h2>
				</div>
				<div class="article_list">

					<form
						action="${ctx }/studentTestBooking.do?action=addStudentTestBooking"
						method="post">
						<input type="hidden" name="testBookingId" id="testBookingId"
							value="${map.id }" />
						<table width="100%" border="1"
							class="test_booking_list_table student_booking_table">
							<tr>
								<td class="fieldName" width="20%">测试任务名称:</td>
								<td class="fieldForm" width="80%">${map.test_booking_name }</td>
							</tr>
							<tr>
								<td class="fieldName">考点:</td>
								<td class="fieldForm"><c:if test="${map.campus ==1 }">
						石牌
						</c:if> <c:if test="${map.campus ==2 }">
						 大学城 
						</c:if></td>
							</tr>
							<tr>
								<td class="fieldName">预约开始时间:</td>
								<td class="fieldForm"><fmt:formatDate
										value="${map.booking_begin_time }"
										pattern="yyyy-MM-dd hh:ss:mm" /></td>
							</tr>
							<tr>
								<td class="fieldName">预约结束时间:</td>
								<td class="fieldForm"><fmt:formatDate
										value="${map.booking_end_time }" pattern="yyyy-MM-dd hh:ss:mm" />
								</td>
							</tr>
							<tr>
								<td class="fieldName">最大预约人数:</td>
								<td class="fieldForm">${map.max_booking_num }</td>
							</tr>
							<tr>
								<td class="fieldName">当前预约人数:</td>
								<td class="fieldForm">${map.current_booking_num }</td>
							</tr>
							<tr>
								<td class="fieldName">剩余预约人数:</td>
								<td class="fieldForm">${map.max_booking_num -
									map.current_booking_num }</td>
							</tr>
							<tr>
								<td class="fieldName">添加时间:</td>
								<td class="fieldForm"><fmt:formatDate
										value="${map.insert_time }" pattern="yyyy-MM-dd hh:ss:mm" /></td>
							</tr>
							<tr>
								<td class="fieldName">说明:</td>
								<td class="fieldForm">${map.description }</td>
							</tr>
							<tr>
								<td class="fieldName"></td>
								<td class="fieldForm"><c:if
										test="${map.max_booking_num -
									map.current_booking_num >0 && map.begin_booking > 0 
										&& map.end_booking > 0 }">

										<!-- 有预约信息的话，就写在这里，然后用js处理提示信息 -->
										<c:if test="${ !empty sessionScope.bookingList   }">
											<input type="hidden" name="isBooking" id="isBooking"
												value="yes" />
										</c:if>
										<input type="submit" value="预约" id="submitBooking"
											class="button" />
									</c:if> <input type="button" value="返回" class="button"
									id="returnButton" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>
