<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }>>考试预约列表</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$(function() {
		//按纽样式
		$(".button1,.button2").hover(function() {
			$(this).addClass("button1Over");
			$(".button1Left", $(this)).show();
			$(".button1Right", $(this)).show();
		}, function() {
			$(this).removeClass("button1Over");
			$(".button1Left", $(this)).hide();
			$(".button1Right", $(this)).hide();
		});

		//全选,反选
		$("#checkAll").click(function() {
			$(".checkList").attr("checked", !!$("#checkAll").attr("checked"));
		});

		//添加考试预约
		$("#newTestBooking").click(
				function() {
					overrideSelectedTab('newTestBooking', '新增考试预约',
							'testBooking.do?action=toAddTestBooking');
				});

		//删除考试预约
		$("#deleteTestBooking")
				.click(
						function() {
							var checkListArr = "";

							if ($(".checkList:checked").length < 1) {
								alert("请选择要删除的选项");
								return false;
							}
							if (!confirm("您真的要删除吗?")) {
								return false;
							}

							//检查通过，进行删除操作
							$(".checkList:checked")
									.each(
											function() {
												checkListArr = checkListArr == "" ? checkListArr
														+ $(this).val()
														: checkListArr + ","
																+ $(this).val();
											});

							$
									.ajax({
										type : "POST",
										url : "testBooking.do?action=deleteTestBooking&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTab(
													'listTestBooking',
													'考试预约管理',
													'testBooking.do?action=listTestBooking');
										}
									});
						});

		//修改考试预约
		$("#updateTestBooking")
				.click(
						function() {
							var checkList = $(".checkList:checked"), checkListLength = checkList.length, checkListValue = checkList
									.val();
							if (checkListLength < 1) {
								alert("请选择要修改的选项");
								return false;
							} else if (checkListLength > 1) {
								alert("对不起，只能选择一项进行修改");
								return false;
							}
							overrideSelectedTab('updateTestBooking', '修改考试预约',
									'testBooking.do?action=toUpdateTestBooking&id='
											+ checkListValue);

						});
		//更新
		$(".update").click(
				function() {
					overrideSelectedTab('updateTestBooking', '修改考试预约',
							'testBooking.do?action=toUpdateTestBooking&id='
									+ $(this).attr("value"));
				});

		//删除
		$(".delete")
				.click(
						function() {

							if (!confirm("您真的要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "testBooking.do?action=deleteTestBooking&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTab(
													'listTestBooking',
													'考试预约管理',
													'testBooking.do?action=listTestBooking');
										}
									});
						});
		//查看
		$(".view").click(
				function() {
					overrideSelectedTab('viewTestBooking', '查看考试预约',
							'testBooking.do?action=viewTestBooking&id='
									+ $(this).attr("value"));
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

	<div class="functionList">

		<div class="button1" id="newTestBooking">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateTestBooking">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>
		<div class="button1" id="deleteTestBooking">
			<div class="button1Left"></div>
			删除
			<div class="button1Right"></div>
		</div>


	</div>

	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>考试名称</th>
				<th>校区</th>
				<th>预约开始时间</th>
				<th>预约结束时间</th>
				<th>最大预约数</th>
				<th>目前预约数</th>
				<th>剩余预约数</th>
				<th>添加时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:if test="${empty page.pageDatas }">
			<tr>
				<td colspan="10">对不起，暂时没有数据</td>
			</tr>

		</c:if>
		<c:forEach items="${page.pageDatas }" var="list" varStatus="vs">
			<tr <c:if test="${vs.index %2==1 }">
					class="a1"
				</c:if>>
				<td><input type="checkbox" class="checkList" name="checkList"
					value="${list.id }" /></td>
				<td>${list.test_booking_name }</td>
				<td><c:if test="${list.campus ==1 }">
						  石牌
						</c:if> <c:if test="${list.campus ==2 }">
						  大学城
						</c:if></td>

				<td><fmt:formatDate value="${list.booking_begin_time }"
						pattern="yyyy-MM-dd hh:ss:mm" /></td>

				<td><fmt:formatDate value="${list.booking_end_time }"
						pattern="yyyy-MM-dd hh:ss:mm" /></td>
				<td>${list.max_booking_num }</td>
				<td>${list.current_booking_num }</td>
				<td>${list.max_booking_num-list.current_booking_num }</td>
				<td><fmt:formatDate value="${list.insert_time }"
						pattern="yyyy-MM-dd hh:ss:mm" /></td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxwz:page url="${ctx }/testBooking.do?action=listTestBooking"
			page="${page }" />
	</div>
</body>
</html>
