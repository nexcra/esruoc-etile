<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>作业分类列表</title>
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

		//添加作业分类
		$("#addAssignmentClass")
				.click(
						function() {
							overrideSelectedTabForSubPage('addAssignmentClass',
									'新增作业分类',
									'admin/assignmentClass.do?action=toAddAssignmentClass');
						});

		//删除作业分类(顶部按纽栏)
		$("#deleteAssignmentClass")
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
										url : "assignmentClass.do?action=deleteAssignmentClass&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listAssignmentClass',
													'作业分类管理',
													'admin/assignmentClass.do?action=listAssignmentClass');
										}
									});
						});

		//修改作业分类(顶部按纽栏)
		$("#updateAssignmentClass")
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
									'updateAssignmentClass', '修改作业分类',
									'admin/assignmentClass.do?action=toUpdateAssignmentClass&id='
											+ checkListValue);

						});

		//搜索作业分类
		$("#toSearch").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "assignmentClass.do?action=searchAssignmentClass";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//导出作业分类
		$("#toExportAssignmentClassList")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "assignmentClass.do?action=exportAssignmentClassList";

							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})

		//获取搜索条件

		function getFormActionCriteria() {
			var formAction = "";
			formAction += formAction += "&name=" + $("#name").val();
			formAction += "&classesId=" + $("#classesId").val();

			return formAction;
		}

		//重置
		$("#reset")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'listAssignmentClass', '作业分类管理',
									'admin/assignmentClass.do?action=listAssignmentClass');
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

		//更新作业分类(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateAssignmentClass',
							'修改作业分类',
							'admin/assignmentClass.do?action=toUpdateAssignmentClass&id='
									+ $(this).attr("value"));
				});

		//删除作业分类(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "assignmentClass.do?action=deleteAssignmentClass&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listAssignmentClass',
													'作业分类管理',
													'admin/assignmentClass.do?action=listAssignmentClass');
										}
									});
						});

		//查看作业分类
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewAssignmentClass',
							'查看作业分类',
							'admin/assignmentClass.do?action=viewAssignmentClass&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<div class="button1" id="addAssignmentClass">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateAssignmentClass">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="deleteAssignmentClass">
			<div class="button1Left"></div>
			删除
			<div class="button1Right"></div>
		</div>

		<div class="button2" id="expandSearchTable" style="display: none;">
			<div class="button1Left"></div>
			展开搜索
			<div class="button1Right"></div>
		</div>

	</div>
	<form
		action="${ctx }/admin/assignmentClass.do?action=searchAssignmentClass"
		name="queryForm" id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr>
				<td class="fieldName" width="10%">作业名称:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="name" id="name" value="${bean.name  }" /></td>
				<td class="fieldName" width="10%">分配给班级:</td>
				<td class="fieldForm" width="40%"><select name="classesId"
					id="classesId"><option value="all" selected="selected">全部</option>
						<c:forEach var="classesList" items="${classesList }">
							<option
								<c:if test="${bean.classesId ==classesList.id  }"> selected="selected"</c:if>
								value="${classesList.id }">${classesList.name }</option>
						</c:forEach></select></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" name="toSearch" class="submitButton" /> <!-- <input
					type="button" value="导出" id="toExportAssignmentClassList"
					name="toExportAssignmentClassList" class="submitButton" /> --> <input
					type="reset" id="reset" name="reset" value="重置"
					class="submitButton" /> <input type="reset" id="foldSearchTable"
					value="隐藏搜索" class="submitButton" /></td>
				</td>
			</tr>
		</table>
	</form>
	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>作业名称</th>
				<th>作业描述</th>
				<th>分配给班级</th>
				<th>作业开始时间</th>
				<th>作业截止时间</th>
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
				<td>${list.name }</td>
				<td>${list.description }</td>
				<td>${list.classes_name }</td>
				<td><fmt:formatDate value="${list.start_time }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${list.end_time }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxgz:page
			url="${ctx }/admin/assignmentClass.do?action=listAssignmentClass"
			page="${page }" />
	</div>
</body>
</html>
