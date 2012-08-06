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
							<th width="40%">考试名称</th>
							<th width="10%">校区</th>
							<th width="22%">预约开始时间</th>
							<th width="22%">预约结束时间</th>
							<th width="6%">操作</th>
						</tr>

						<c:if test="${empty page.pageDatas }">
							<tr>
								<td colspan="5">对不起，暂时没有预约信息</td>
							</tr>

						</c:if>

						<c:forEach items="${page.pageDatas }" var="list" varStatus="vs">
							<tr
								<c:if test="${vs.index%2==1 }">
									style="background:#fbfbfb"
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
									id="booking">预约</a></td>
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
