<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>友情链接列表</title>
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

		//添加友情链接
		$("#addFriendsite").click(
				function() {
					overrideSelectedTabForSubPage('addFriendsite', '新增友情链接',
							'admin/friendsite.do?action=toAddFriendsite');
				});

		//删除友情链接(顶部按纽栏)
		$("#deleteFriendsite")
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
										url : "friendsite.do?action=deleteFriendsite&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listFriendsite', '友情链接管理',
													'admin/friendsite.do?action=listFriendsite');
										}
									});
						});

		//修改友情链接(顶部按纽栏)
		$("#updateFriendsite")
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
							overrideSelectedTabForSubPage('updateFriendsite',
									'修改友情链接',
									'admin/friendsite.do?action=toUpdateFriendsite&id='
											+ checkListValue);

						});

		//搜索友情链接
		$("#toSearch").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "friendsite.do?action=searchFriendsite";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//导出友情链接
		$("#toExportFriendsiteList").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "friendsite.do?action=exportFriendsiteList";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//获取搜索条件

		function getFormActionCriteria() {
			var formAction = "";
			formAction += formAction += "&name=" + $("#name").val();

			return formAction;
		}

		//重置
		$("#reset").click(
				function() {
					overrideSelectedTabForSubPage('listFriendsite', '友情链接管理',
							'admin/friendsite.do?action=listFriendsite');
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

		//更新友情链接(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateFriendsite', '修改友情链接',
							'admin/friendsite.do?action=toUpdateFriendsite&id='
									+ $(this).attr("value"));
				});

		//删除友情链接(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "friendsite.do?action=deleteFriendsite&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listFriendsite', '友情链接管理',
													'admin/friendsite.do?action=listFriendsite');
										}
									});
						});

		//查看友情链接
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewFriendsite', '查看友情链接',
							'admin/friendsite.do?action=viewFriendsite&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<div class="button1" id="addFriendsite">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateFriendsite">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="deleteFriendsite">
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
	<form action="${ctx }/admin/friendsite.do?action=searchFriendsite"
		name="queryForm" id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr>
				<td class="fieldName" width="10%">链接名称:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="name" id="name" value="${bean.name  }" /></td>
				<td class="fieldName" width="10%"></td>
				<td class="fieldForm" width="40%"></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" name="toSearch" class="submitButton" /> <input
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
				<th>网站名称</th>
				<th>类型</th>
				<!-- <th>链接</th> -->
				<th>序号</th>
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
				<%-- <td>${list.type }</td> --%>
				<td>${list.link }</td>
				<td>${list.order_number }</td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxgz:page url="${ctx }/admin/friendsite.do?action=listFriendsite"
			page="${page }" />
	</div>
</body>
</html>
