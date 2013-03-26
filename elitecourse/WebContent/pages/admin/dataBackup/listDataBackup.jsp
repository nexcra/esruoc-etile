<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>数据备份列表</title>
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

		//添加数据备份
		$("#addDataBackup").click(
				function() {
					overrideSelectedTabForSubPage('addDataBackup', '新增数据备份',
							'admin/dataBackup.do?action=toAddDataBackup');
				});
		//恢复数据备份
		$("#restoreDataBackup").click(
				function() {
					var checkListArr = "";

					if ($(".checkList:checked").length < 1) {
						alert("请选择要恢复的选项");
						return false;
					}
					overrideSelectedTabForSubPage('restoreDataBackup',
							'新增数据备份',
							'admin/dataBackup.do?action=toRestoreDataBackup');
				});

		//删除数据备份(顶部按纽栏)
		$("#deleteDataBackup")
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
										url : "dataBackup.do?action=deleteDataBackup&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listDataBackup', '数据备份管理',
													'admin/dataBackup.do?action=listDataBackup');
										}
									});
						});

		//修改数据备份(顶部按纽栏)
		$("#updateDataBackup")
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
							overrideSelectedTabForSubPage('updateDataBackup',
									'修改数据备份',
									'admin/dataBackup.do?action=toUpdateDataBackup&id='
											+ checkListValue);

						});

		//搜索数据备份
		$("#toSearch").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "dataBackup.do?action=searchDataBackup";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//导出数据备份
		$("#toExportDataBackupList").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "dataBackup.do?action=exportDataBackupList";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//获取搜索条件

		function getFormActionCriteria() {
			var formAction = "";
			formAction += formAction += "&name=" + $("#name").val();
			formAction += "&path=" + $("#path").val();

			return formAction;
		}

		//重置
		$("#reset").click(
				function() {
					overrideSelectedTabForSubPage('listDataBackup', '数据备份管理',
							'admin/dataBackup.do?action=listDataBackup');
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

		//更新数据备份(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateDataBackup', '修改数据备份',
							'admin/dataBackup.do?action=toUpdateDataBackup&id='
									+ $(this).attr("value"));
				});

		//删除数据备份(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "dataBackup.do?action=deleteDataBackup&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listDataBackup', '数据备份管理',
													'admin/dataBackup.do?action=listDataBackup');
										}
									});
						});

		//查看数据备份
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewDataBackup', '查看数据备份',
							'admin/dataBackup.do?action=viewDataBackup&id='
									+ $(this).attr("value"));
				});
		//恢复数据备份
		$(".restore").click(
				function() {
					overrideSelectedTabForSubPage('restoreDataBackup',
							'恢复数据备份',
							'admin/dataBackup.do?action=toRestoreDataBackup&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<div class="button1" id="addDataBackup">
			<div class="button1Left"></div>
			备份
			<div class="button1Right"></div>
		</div>
		<!-- <div class="button1" id="restoreDataBackup">
			<div class="button1Left"></div>
			恢复
			<div class="button1Right"></div>
		</div> -->

		<div class="button1" id="deleteDataBackup">
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
	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>备份文件名</th>
				<th>备份时间</th>
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
				<td><fmt:formatDate value='${list.create_time }'
						pattern='yyyy-MM-dd HH:mm:ss' /></td>
				<td><span class="restore operationButton" value="${list.id }">恢复</span><span
					class="operationButton">|</span><span
					class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxgz:page url="${ctx }/admin/dataBackup.do?action=listDataBackup"
			page="${page }" />
	</div>
</body>
</html>
