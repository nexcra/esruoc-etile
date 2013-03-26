<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户列表</title>
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

		//添加用户
		$("#addUser").click(
				function() {
					overrideSelectedTabForSubPage('addUser', '新增用户',
							'admin/user.do?action=toAddUser');
				});

		//删除用户(顶部按纽栏)
		$("#deleteUser")
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
								url : "user.do?action=deleteUser&randomNum="
										+ new Date().getTime() + Math.random(),
								cache : false,
								data : "id=" + checkListArr,
								dataType : "text",
								success : function(data) {
									alert(data);
									overrideSelectedTabForSubPage('listUser',
											'用户管理',
											'admin/user.do?action=listUser');
								}
							});
						});

		//修改用户(顶部按纽栏)
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
							overrideSelectedTabForSubPage('updateUser', '修改用户',
									'admin/user.do?action=toUpdateUser&id='
											+ checkListValue);

						});

		//搜索用户
		$("#toSearch").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "user.do?action=searchUser";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//导出用户
		$("#toExportUserList").click(function() {
			var formToExport = document.getElementById("queryForm");

			var formAction = "user.do?action=exportUserList";

			formAction += getFormActionCriteria();

			formToExport.action = formAction;

			formToExport.submit();
			$("#searchTable").show();
		})

		//获取搜索条件

		function getFormActionCriteria() {
			var formAction = "";
			formAction += formAction += "&name=" + $("#name").val();
			formAction += "&realName=" + $("#realName").val();
			/* 	formAction += "&status=" + $("#status").val(); */
			formAction += "&gender=" + $("#gender").val();
			/* 	formAction += "&origin=" + $("#origin").val();
				formAction += "&birthDate=" + $("#birthDate").val();
				formAction += "&groupId=" + $("#groupId").val();
				formAction += "&email=" + $("#email").val();
				formAction += "&telephone=" + $("#telephone").val();
				formAction += "&isAdmin=" + $("#isAdmin").val();
				formAction += "&isLocked=" + $("#isLocked").val();
				formAction += "&age=" + $("#age").val();
				formAction += "&role=" + $("#role").val(); */
			formAction += "&classesId=" + $("#classesId").val();

			return formAction;
		}

		//重置
		$("#reset").click(
				function() {
					overrideSelectedTabForSubPage('listUser', '用户管理',
							'admin/user.do?action=listUser');
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

		//更新用户(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateUser', '修改用户',
							'admin/user.do?action=toUpdateUser&id='
									+ $(this).attr("value"));
				});

		//删除用户(表格列表操作)
		$(".delete").click(
				function() {

					if (!confirm("您确定要删除吗?")) {
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
							overrideSelectedTabForSubPage('listUser', '用户管理',
									'admin/user.do?action=listUser');
						}
					});
				});

		//查看用户
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewUser', '查看用户',
							'admin/user.do?action=viewUser&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<div class="button1" id="addUser">
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

		<div class="button2" id="expandSearchTable" style="display: none;">
			<div class="button1Left"></div>
			展开搜索
			<div class="button1Right"></div>
		</div>

	</div>
	<form action="${ctx }/admin/user.do?action=searchUser" name="queryForm"
		id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr>
				<td class="fieldName" width="10%">用户名:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="name" id="name" value="${bean.name  }" /></td>
				<td class="fieldName" width="10%">真实姓名:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="realName" id="realName" value="${bean.realName  }" /></td>
			</tr>
			<tr>
				<td class="fieldName" width="10%">班级:</td>
				<td class="fieldForm" width="40%"><select name="classesId"
					id="classesId"><option value="all" selected="selected">全部</option>
						<c:forEach var="classesList" items="${classesList }">
							<option
								<c:if test="${bean.classesId ==classesList.id  }"> selected="selected"</c:if>
								value="${classesList.id }">${classesList.name }</option>
						</c:forEach></select></td>
				<td class="fieldName" width="10%">性别:</td>
				<td class="fieldForm" width="40%"><select id="gender"
					name="gender"><option value="all">全部</option>
						<option value="male"
							<c:if test="${bean.gender =='male'  }">selected ='selected' </c:if>>男</option>
						<option value="female"
							<c:if test="${bean.gender =='female'  }">selected ='selected' </c:if>>女</option></select>
				</td>
			</tr>
			<%-- 	<tr>
				<td class="fieldName" width="10%">groupId:</td>
				<td class="fieldForm" width="40%"><select name="groupId"
					id="groupId"><option value="all" selected="selected">全部</option>
						<c:forEach var="groupList" items="${groupList }">
							<option
								<c:if test="${bean.groupId ==groupList.id  }"> selected="selected"</c:if>
								value="${groupList.id }">${groupList.name }</option>
						</c:forEach></select></td>
				<td class="fieldName" width="10%">email:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="email" id="email" value="${bean.email  }" /></td>
			</tr>
			<tr>
				<td class="fieldName" width="10%">telephone:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="telephone" id="telephone" value="${bean.telephone  }" /></td>
				<td class="fieldName" width="10%">isAdmin:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="isAdmin" id="isAdmin" value="${bean.isAdmin  }" /></td>
			</tr>
			<tr>
				<td class="fieldName" width="10%">isLocked:</td>
				<td class="fieldForm" width="40%"><input type="text"
					name="isLocked" id="isLocked" value="${bean.isLocked  }" /></td>
				<td class="fieldName" width="10%">age:</td>
				<td class="fieldForm" width="40%"><input type="text" name="age"
					id="age" value="${bean.age  }" /></td>
			</tr> --%>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" name="toSearch" class="submitButton" /> <input
					type="button" value="导出" id="toExportUserList"
					name="toExportUserList" class="submitButton" /> <input
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
				<th>用户名</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>身份</th>
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
				<td>${list.real_name }</td>
				<td><c:if test="${list.gender =='male'}">男</c:if> <c:if
						test="${list.gender =='female'}">女</c:if></td>
				<td><c:if test="${list.is_admin =='manager'}">管理员</c:if> <c:if
						test="${list.is_admin =='student'}">学生</c:if></td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxgz:page url="${ctx }/admin/user.do?action=listUser"
			page="${page }" />
	</div>
</body>
</html>
