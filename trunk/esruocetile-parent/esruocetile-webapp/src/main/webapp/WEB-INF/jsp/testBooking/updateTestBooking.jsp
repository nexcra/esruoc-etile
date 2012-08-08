<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript">
	$(function() {
		$("#updateForm").validate({
			rules : {
				name : {
					required : true 
				},
				bookingBeginTime:{
					required : true ,
					date:true
				}
				,bookingEndTime:{
					required : true ,
					 date:true
				},
				maxBookingNum:{
					required : true ,
					digits:true
				}
			}
		});
	});
</script>
</head>
<body>
	<form action="${ctx }/testBooking.do?action=updateTestBooking"
		name="updateForm" id="updateForm" method="post">
		<input type="hidden" name="id" value="${map.id }" />
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName" width="20%">考试名称:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="name" id="name" class="width400"
					value="${map.test_booking_name }" /><span class="asterisk">*</span></td>
			</tr>

			<tr>
				<td class="fieldName">校区:</td>
				<td class="fieldForm"><select name="campus" id="campus">
						<option value="1"
							<c:if test="${map.campus ==1 }">
						selected = "selected"
						</c:if>>石牌</option>
						<option value="2"
							<option value="1"  
						<c:if test="${map.campus ==2 }">
						selected = "selected"
						</c:if>
						>大学城</option>
				</select> <span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName">预约开始时间:</td>
				<td class="fieldForm"><input name="bookingBeginTime"
					id="bookingBeginTime" type="text" class="inputText"
					value="<fmt:formatDate value="${map.booking_begin_time }"
						pattern="yyyy-MM-dd hh:ss:mm" />"
					onclick="WdatePicker({el:'bookingBeginTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d}',maxDate:'#F{$dp.$D(\'bookingEndTime\')}'})" />
					<img
					onClick="WdatePicker({el:'bookingBeginTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d}',maxDate:'#F{$dp.$D(\'bookingEndTime\')}'})"
					src="widget/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" align="absmiddle"></td>
			</tr>

			<tr>
				<td class="fieldName">预约结束时间:</td>
				<td class="fieldForm"><input name="bookingEndTime"
					id="bookingEndTime" type="text" class="inputText"
					value="<fmt:formatDate value="${map.booking_end_time }"
						pattern="yyyy-MM-dd hh:ss:mm" />"
					onclick="WdatePicker({el:'bookingEndTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'bookingBeginTime\')}'})" />
					<img
					onClick="WdatePicker({el:'bookingEndTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'bookingBeginTime\')}'})"
					src="widget/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" align="absmiddle"></td>
			</tr>


			<tr>
				<td class="fieldName">最大预约人数:</td>
				<td class="fieldForm"><input type="text" name="maxBookingNum"
					id="maxBookingNum" value="${map.max_booking_num }" /><span
					class="asterisk">*</span></td>
			</tr>

			<tr>
				<td class="fieldName">说明:</td>
				<td class="fieldForm"><textarea rows="5" cols="50"
						name="description" id="description">${map.description }</textarea></td>
			</tr>

			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="提交"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
