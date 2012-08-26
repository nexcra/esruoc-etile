<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/front.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#booking").click(function() {

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
				您现在所在的位置：<a href="${ctx }">首页</a>&nbsp;&gt;&gt;预约信息
			</div>
			<div class="column_article_list">
				<div class="column_name">
					<span>&gt;&gt;</span> 预约信息
				</div>
				<div class="article_list">
					<table width="100%" border="0" class="test_booking_list_table">

						<tr>
							<th width="36%">考试名称</th>
							<th width="10%">校区</th>
							<th width="22%">预约开始时间</th>
							<th width="22%">预约结束时间</th>
							<th width="10%">操作</th>
						</tr>

						<c:if test="${empty page.pageDatas }">
							<tr>
								<td colspan="5">对不起，暂时没有预约信息</td>
							</tr>

						</c:if>

						<c:forEach items="${page.pageDatas }" var="list" varStatus="vs">
							<tr
								<c:if
											test="${list.begin_booking > 0 
										&& list.end_booking > 0 
										&&(list.max_booking_num - list.current_booking_num > 0) }">
										style="color:green;font-weight:bold" 
									</c:if>
								<c:if
											test="${list.max_booking_num - list.current_booking_num <1 }">
										  style="color:red;" 
									</c:if>
								<c:if test="${list.begin_booking < 0 }">
										 style="background:"  
									</c:if>
								<c:if test="${list.end_booking < 0 }">
										  style="color:#999" 
									</c:if>>
								<td>${list.test_booking_name }</td>
								<td><c:if test="${list.campus ==1 }">石牌</c:if> <c:if
										test="${list.campus ==2 }">大学城</c:if></td>
								<td><fmt:formatDate value="${list.booking_begin_time }"
										pattern="yyyy-MM-dd hh:ss:mm" /></td>
								<td><fmt:formatDate value="${list.booking_end_time }"
										pattern="yyyy-MM-dd hh:ss:mm" /></td>
								<td><a
									href="${ctx }/testBooking.do?action=viewTestBookingForStudent&id=${list.id}"
									id="booking"> <c:if
											test="${list.begin_booking > 0 
										&& list.end_booking > 0 
										&&(list.max_booking_num - list.current_booking_num > 0) }">
										<span style="color:green;font-weight:bold">可预约</span>
									</c:if> <c:if
											test="${list.max_booking_num - list.current_booking_num <1 }">
										<span style="color:red;">已约满 </span>
									</c:if> <c:if test="${list.begin_booking < 0 }">
										 未开始 
									</c:if> <c:if test="${list.end_booking < 0 }">
										 <span style="color:#999;">已结束</span>
									</c:if></a></td>
							</tr>
						</c:forEach>
					</table>
					<!--  <div class="page">
						<tkxwz:page
							url="${ctx }/testBooking.do?action=listTestBookingForStudent"
							page="${page }" />
					</div>-->
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>
