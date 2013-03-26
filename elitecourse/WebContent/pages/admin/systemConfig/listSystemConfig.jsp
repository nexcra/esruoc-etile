<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统配置列表</title>
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

		//添加系统配置
		$("#addSystemConfig").click(
				function() {
					overrideSelectedTabForSubPage('addSystemConfig', '新增系统配置',
							'admin/systemConfig.do?action=toAddSystemConfig');
				});

		//删除系统配置(顶部按纽栏)
		$("#deleteSystemConfig")
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
										url : "systemConfig.do?action=deleteSystemConfig&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listSystemConfig',
													'系统配置管理',
													'admin/systemConfig.do?action=listSystemConfig');
										}
									});
						});

		//修改系统配置(顶部按纽栏)
		$("#updateSystemConfig")
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
							overrideSelectedTabForSubPage('updateSystemConfig',
									'修改系统配置',
									'admin/systemConfig.do?action=toUpdateSystemConfig&id='
											+ checkListValue);

						});

		//搜索系统配置
		$("#toSearch").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "systemConfig.do?action=searchSystemConfig";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//导出系统配置
		$("#toExportSystemConfigList").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "systemConfig.do?action=exportSystemConfigList";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//获取搜索条件

		function getFormActionCriteria() {
			var formAction = "";
			formAction += formAction += "&siteName=" + $("#siteName").val();
			formAction += "&copyright=" + $("#copyright").val();
			formAction += "&siteOwner=" + $("#siteOwner").val();
			formAction += "&contactPhone=" + $("#contactPhone").val();
			formAction += "&contactEmail=" + $("#contactEmail").val();
			formAction += "&status=" + $("#status").val();

			return formAction;
		}

		//重置
		$("#reset").click(
				function() {
					overrideSelectedTabForSubPage('listSystemConfig', '系统配置管理',
							'admin/systemConfig.do?action=listSystemConfig');
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

		//更新系统配置(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateSystemConfig',
							'修改系统配置',
							'admin/systemConfig.do?action=toUpdateSystemConfig&id='
									+ $(this).attr("value"));
				});

		//删除系统配置(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "systemConfig.do?action=deleteSystemConfig&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listSystemConfig',
													'系统配置管理',
													'admin/systemConfig.do?action=listSystemConfig');
										}
									});
						});

		//查看系统配置
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewSystemConfig', '查看系统配置',
							'admin/systemConfig.do?action=viewSystemConfig&id='
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
				<th>网站名字</th>
				<th>版权</th>
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
				<td>${list.site_name }</td>
				<td>${list.copyright }</td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="view operationButton" value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxgz:page
			url="${ctx }/admin/systemConfig.do?action=listSystemConfig"
			page="${page }" />
	</div>
</body>
</html>
