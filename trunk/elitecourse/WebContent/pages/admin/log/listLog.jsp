<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>日志列表</title>
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

		//添加日志
		$("#addLog").click(
				function() {
					overrideSelectedTabForSubPage('addLog', '新增日志',
							'admin/log.do?action=toAddLog');
				});

		//删除日志(顶部按纽栏)
		$("#deleteLog")
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

							$.ajax({
								type : "POST",

								url : "log.do?action=deleteLog&randomNum="
										+ new Date().getTime() + Math.random(),
								cache : false,
								data : "id=" + checkListArr,
								dataType : "text",
								success : function(data) {
									alert(data);
									overrideSelectedTabForSubPage('listLog',
											'日志管理',
											'admin/log.do?action=listLog');
								}
							});
						});

		//修改日志(顶部按纽栏)
		$("#updateLog")
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
							overrideSelectedTabForSubPage('updateLog', '修改日志',
									'admin/log.do?action=toUpdateLog&id='
											+ checkListValue);

						});

		//搜索日志
		$("#toSearch").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "log.do?action=searchLog";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//导出日志
		$("#toExportLogList").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "log.do?action=exportLogList";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//获取搜索条件

		function getFormActionCriteria() {
			var formAction = "";
			formAction += formAction += "&logType=" + $("#logType").val();
			formAction += "&userName=" + $("#userName").val();

			return formAction;
		}

		//重置
		$("#reset").click(
				function() {
					overrideSelectedTabForSubPage('listLog', '日志管理',
							'admin/log.do?action=listLog');
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

		//更新日志(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateLog', '修改日志',
							'admin/log.do?action=toUpdateLog&id='
									+ $(this).attr("value"));
				});

		//删除日志(表格列表操作)
		$(".delete").click(
				function() {

					if (!confirm("您确定要删除吗?")) {
						return false;
					}

					$.ajax({
						type : "POST",
						url : "log.do?action=deleteLog&randomNum="
								+ new Date().getTime() + Math.random(),
						cache : false,
						data : "id=" + $(this).attr("value"),
						dataType : "text",
						success : function(data) {
							alert(data);
							overrideSelectedTabForSubPage('listLog', '日志管理',
									'admin/log.do?action=listLog');
						}
					});
				});

		//查看日志
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewLog', '查看日志',
							'admin/log.do?action=viewLog&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<!-- <div class="button1" id="addLog">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateLog">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>
 -->
		<div class="button1" id="deleteLog">
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
	<form action="${ctx }/admin/log.do?action=searchLog" name="queryForm"
		id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr>
				<td class="fieldName" width="10%">日志类型:</td>
				<td class="fieldForm" width="40%"><select id="logType"
					name="logType">
						<option value="all">全部</option>
						<option value="login"
							<c:if test="${bean.logType == 'login'}"> selected='selected' </c:if>>登录</option>
						<option value="logout"
							<c:if test="${bean.logType == 'logout'}"> selected='selected'</c:if>>注销</option>
						<option value="insert"
							<c:if test="${bean.logType == 'insert'}"> selected='selected' </c:if>>新增</option>
						<option value="update"
							<c:if test="${bean.logType == 'update'}"> selected='selected' </c:if>>更新</option>
						<option value="delete"
							<c:if test="${bean.logType == 'delete'}"> selected='selected' </c:if>>删除</option>
				</select></td>
				<td class="fieldName" width="10%">用户名:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="userName" id="userName" value="${bean.userName  }" /></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" name="toSearch" class="submitButton" /> <input
					type="button" value="导出" id="toExportLogList"
					name="toExportLogList" class="submitButton" /> <input type="reset"
					id="reset" name="reset" value="重置" class="submitButton" /> <input
					type="reset" id="foldSearchTable" value="隐藏搜索" class="submitButton" /></td>
				</td>
			</tr>
		</table>
	</form>
	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>日志类型</th>
				<th>用户名</th>
				<th>模块</th>
				<th>描述</th>
				<th>IP地址</th>
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
				<td><c:if test="${list.log_type == 'login' }">登录</c:if> <c:if
						test="${list.log_type == 'logout' }">注销</c:if> <c:if
						test="${list.log_type == 'insert' }">新增</c:if> <c:if
						test="${list.log_type == 'update' }">更新</c:if> <c:if
						test="${list.log_type == 'delete' }">删除</c:if></td>
				<td>${list.user_name }</td>
				<td>${list.module }</td>
				<td>${list.description }</td>
				<td>${list.ip_address }</td>
				<td><span class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxgz:page url="${ctx }/admin/log.do?action=listLog" page="${page }" />
	</div>
</body>
</html>
