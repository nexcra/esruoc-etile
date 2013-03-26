<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>课程简介列表</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
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

		//添加课程简介
		$("#addCourseIntroduction")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'addCourseIntroduction', '新增课程简介',
									'admin/courseIntroduction.do?action=toAddCourseIntroduction');
						});

		//删除课程简介(顶部按纽栏)
		$("#deleteCourseIntroduction")
				.click(
						function() {
							var checkListArr = "";

							if ($(".checkList:checked").length < 1) {
								alert("请选择要删除的选项");
								return false;
							}
							if (!confirm("您确定要删除吗?")) {
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
										url : "courseIntroduction.do?action=deleteCourseIntroduction&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listCourseIntroduction',
													'课程简介管理',
													'admin/courseIntroduction.do?action=listCourseIntroduction');
										}
									});
						});

		//修改课程简介(顶部按纽栏)
		$("#updateCourseIntroduction")
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
							overrideSelectedTabForSubPage(
									'updateCourseIntroduction', '修改课程简介',
									'admin/courseIntroduction.do?action=toUpdateCourseIntroduction&id='
											+ checkListValue);

						});

		//搜索课程简介
		$("#toSearch")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "courseIntroduction.do?action=searchCourseIntroduction";

							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})

		//导出课程简介
		$("#toExportCourseIntroductionList")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "courseIntroduction.do?action=exportCourseIntroductionList";

							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})

		//获取搜索条件

		function getFormActionCriteria() {
			var formAction = "";
			formAction += formAction += "&courseHostName="
					+ $("#courseHostName").val();
			formAction += "&introContent=" + $("#introContent").val();
			formAction += "&pic=" + $("#pic").val();

			return formAction;
		}

		//重置
		$("#reset")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'listCourseIntroduction', '课程简介管理',
									'admin/courseIntroduction.do?action=listCourseIntroduction');
						})

		//展开搜索栏
		$("#expandSearchTable").click(function() {
			$("#searchTable").show();
			$(this).hide();
			$("#foldSearchTable").show();
		});

		//收缩搜索栏
		$("#foldSearchTable").click(function() {
			$("#searchTable").hide();
			$(this).hide();
			$("#expandSearchTable").show();

		});

		//更新课程简介(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateCourseIntroduction',
							'修改课程简介',
							'admin/courseIntroduction.do?action=toUpdateCourseIntroduction&id='
									+ $(this).attr("value"));
				});

		//删除课程简介(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "courseIntroduction.do?action=deleteCourseIntroduction&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listCourseIntroduction',
													'课程简介管理',
													'admin/courseIntroduction.do?action=listCourseIntroduction');
										}
									});
						});

		//查看课程简介
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewCourseIntroduction',
							'查看课程简介',
							'admin/courseIntroduction.do?action=viewCourseIntroduction&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>课程主持人</th>
			<!-- 	<th>简介</th>
				<th>图片</th> -->
				<th>操作</th>
			</tr>
		</thead>
		<c:if test="${empty page.pageDatas }">
			<tr>
				<td colspan="13">对不起，暂时没有数据</td>
			</tr>

		</c:if>
		<c:forEach items="${page.pageDatas }" var="list" varStatus="vs">
			<tr <c:if test="${vs.index %2==1 }">
					class="a1"
				</c:if>>
				<td><input type="checkbox" class="checkList" name="checkList"
					value="${list.id }" /></td>
				<td>${list.course_host_name }</td>
			<%-- 	<td>${list.intro_content }</td>
				<td>${list.pic }</td> --%>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="view operationButton" value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxgz:page
			url="${ctx }/admin/courseIntroduction.do?action=listCourseIntroduction"
			page="${page }" />
	</div>
</body>
</html>
