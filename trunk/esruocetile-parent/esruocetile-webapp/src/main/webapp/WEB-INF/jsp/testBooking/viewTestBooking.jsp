<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton").click(
				function() {
					overrideSelectedTab('listTestBooking', '考试预约信息',
							'testBooking.do?action=listTestBooking');
				});

	});
	/**
	 *  调用父窗口的overrideSelectedTab方法打开
	 * @param tabid tab的ID
	 * @param text 要显示的文本
	 * @param url 打开的url地址
	 */
	function overrideSelectedTab(tabid, text, url) {
		window.parent.overrideSelectedTab(tabid, text, url);
	}
</script>
</head>
<body>
	<table width="100%" border="1" class="formTable">
		<tr>
			<td class="fieldName" width="20%">考试名称:</td>
			<td class="fieldForm" width="80%">${map.name }</td>
		</tr>
		<tr>
			<td class="fieldName">校区:</td>
			<td class="fieldForm"><c:if test="${map.campus ==1 }">
						石牌
						</c:if> <c:if test="${map.campus ==2 }">
						 大学城 
						</c:if></td>
		</tr>
		<tr>
			<td class="fieldName">预约开始时间:</td>
			<td class="fieldForm"><fmt:formatDate
					value="${map.booking_begin_time }" pattern="yyyy-MM-dd hh:ss:mm" />
			</td>
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
			<td class="fieldForm"><input type="button" value="返回"
				class="submitButton" id="returnButton" /></td>
		</tr>
	</table>
	</form>
</body>
</html>
