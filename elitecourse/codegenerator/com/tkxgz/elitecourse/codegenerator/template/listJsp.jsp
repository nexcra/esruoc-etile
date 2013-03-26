<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>@tableDescription@列表</title>
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

		//添加@tableDescription@
		$("#add@upperBeanName@")
				.click(
						function() {
							overrideSelectedTabForSubPage('add@upperBeanName@',
									'新增@tableDescription@',
									'admin/@lowerBeanName@.do?action=toAdd@upperBeanName@');
						});

		//删除@tableDescription@(顶部按纽栏)
		$("#delete@upperBeanName@")
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
										url : "@lowerBeanName@.do?action=delete@upperBeanName@&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'list@upperBeanName@',
													'@tableDescription@管理',
													'admin/@lowerBeanName@.do?action=list@upperBeanName@');
										}
									});
						});

		//修改@tableDescription@(顶部按纽栏)
		$("#update@upperBeanName@")
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
									'update@upperBeanName@',
									'修改@tableDescription@',
									'admin/@lowerBeanName@.do?action=toUpdate@upperBeanName@&id='
											+ checkListValue);

						});

		//搜索@tableDescription@
		$("#toSearch")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "@lowerBeanName@.do?action=search@upperBeanName@";
							
							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})
						
		//导出@tableDescription@
		$("#toExport@upperBeanName@List")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "@lowerBeanName@.do?action=export@upperBeanName@List";
							
							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})
						
		//获取搜索条件

		function getFormActionCriteria(){
			var formAction = "";
			formAction+=@formActionCriteria@
			return formAction;
		}
		
		//重置
		$("#reset")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'list@upperBeanName@',
									'@tableDescription@管理',
									'admin/@lowerBeanName@.do?action=list@upperBeanName@');
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

		//更新@tableDescription@(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('update@upperBeanName@',
							'修改@tableDescription@',
							'admin/@lowerBeanName@.do?action=toUpdate@upperBeanName@&id='
									+ $(this).attr("value"));
				});

		//删除@tableDescription@(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "@lowerBeanName@.do?action=delete@upperBeanName@&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'list@upperBeanName@',
													'@tableDescription@管理',
													'admin/@lowerBeanName@.do?action=list@upperBeanName@');
										}
									});
						});

		//查看@tableDescription@
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('view@upperBeanName@',
							'查看@tableDescription@',
							'admin/@lowerBeanName@.do?action=view@upperBeanName@&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<div class="button1" id="add@upperBeanName@">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="update@upperBeanName@">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="delete@upperBeanName@">
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
		action="${ctx }/admin/@lowerBeanName@.do?action=search@upperBeanName@"
		name="queryForm" id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			@searchFormFields@
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" name="toSearch" class="submitButton" /> <input type="button"
					value="导出" id="toExport@upperBeanName@List" name="toExport@upperBeanName@List" class="submitButton" /> <input
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
				<th><input type="checkbox" id="checkAll" /></th> @listHeaders@
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
					value="${list.id }" /></td> @listRecords@
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
			url="${ctx }/admin/@lowerBeanName@.do?action=list@upperBeanName@"
			page="${page }" />
	</div>
</body>
</html>
