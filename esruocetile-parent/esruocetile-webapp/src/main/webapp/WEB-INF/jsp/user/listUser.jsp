<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }>>用户列表</title>
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

		//添加用户
		$("#newUser").click(function() {
			overrideSelectedTab('newUser', '新增用户', 'user.do?action=toAddUser');
		});

		//删除用户
		$("#deleteUser")
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

							$.ajax({
								type : "POST",
								url : "user.do?action=deleteUser&randomNum="
										+ new Date().getTime() + Math.random(),
								cache : false,
								data : "id=" + checkListArr,
								dataType : "text",
								success : function(data) {
									alert(data);
									overrideSelectedTab('listUser', '用户管理',
											'user.do?action=listUser');
								}
							});
						});

		//修改用户
		$("#updateUser")
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
							overrideSelectedTab('updateUser', '修改用户',
									'user.do?action=toUpdateUser&id='
											+ checkListValue);

						});

		//修改个人密码
		$("#updatePersonalPassword").click(
				function() {
					window.parent.f_addTab('updatePersonalPassword', '修改个人密码',
							'user.do?action=toUpdatePersonalPassword');
				});
		
		//更新
		$(".update").click(
				function() {
					overrideSelectedTab('updateUser', '修改用户',
							'user.do?action=toUpdateUser&id='
									+ $(this).attr("value"));
				});

		//删除
		$(".delete").click(
				function() {

					if (!confirm("您真的要删除吗?")) {
						return false;
					}

					$.ajax({
						type : "POST",
						url : "user.do?action=deleteUser&randomNum="
								+ new Date().getTime() + Math.random(),
						cache : false,
						data : "id=" + $(this).attr("value"),
						dataType : "text",
						success : function(data) {
							alert(data);
							overrideSelectedTab('listUser', '用户管理',
									'user.do?action=listUser');
						}
					});
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

		<div class="button1" id="newUser">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateUser">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>
		<div class="button1" id="deleteUser">
			<div class="button1Left"></div>
			删除
			<div class="button1Right"></div>
		</div>

		<div class="button2" id="updatePersonalPassword">
			<div class="button1Left"></div>
			修改个人密码
			<div class="button1Right"></div>
		</div>

	</div>

	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>用户</th>
				<th>操作</th>
		</tr>
		</thead>

		<c:forEach items="${page.pageDatas }" var="list" varStatus="vs">
			<tr 
				<c:if test="${vs.index %2==1 }">
					class="a1"
				</c:if>
			>
				<td><input type="checkbox" class="checkList" name="checkList"
					value="${list.id }" /></td>
				<td>${list.name }</td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="delete operationButton" value="${list.id }">删除</span></td>
			</tr>
		</c:forEach>

	</table>
	<div class="pageBar">
		<tkxwz:page url="${ctx }/user.do?action=listUser" page="${page }" />
	</div>
</body>
</html>
