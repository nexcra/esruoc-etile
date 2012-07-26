<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }>>文章列表</title>
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

		//添加文章
		$("#newArticle").click(
				function() {
					overrideSelectedTab('newArticle', '新增文章',
							'article.do?action=toAddArticle');
				});

		//删除文章
		$("#deleteArticle")
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
										url : "article.do?action=deleteArticle&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTab('listArticle',
													'文章管理',
													'article.do?action=listArticle');
										}
									});
						});

		//修改文章
		$("#updateArticle")
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
							overrideSelectedTab('updateArticle', '修改文章',
									'article.do?action=toUpdateArticle&id='
											+ checkListValue);

						});
		$(".view").click(
				function() {
					overrideSelectedTab('viewArticle', '查看文章',
							'article.do?action=viewArticle&id='
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

		<div class="button1" id="newArticle">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateArticle">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>
		<div class="button1" id="deleteArticle">
			<div class="button1Left"></div>
			删除
			<div class="button1Right"></div>
		</div>


	</div>

	<table width="90%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>文章名称</th>
				<th>所属栏目</th>
				<th>状态</th>
				<th>添加时间</th>
				<th>操作</th>
		</thead>
		</tr>

		<c:forEach items="${page.pageDatas }" var="list">
			<tr title="${list.title }
			">
				<td><input type="checkbox" class="checkList" name="checkList"
					value="${list.id }" /></td>
				<td>${list.title }</td>
				<td>${list.column_name }</td>
				<td><c:choose>
						<c:when test="${list.status ==1 }">已发布</c:when>
						<c:when test="${list.status ==2 }">待发布</c:when>
						<c:otherwise>未知状态 </c:otherwise>
					</c:choose></td>
				<td><fmt:formatDate value="${list.insert_time }"
						pattern="yyyy-MM-dd hh:ss:mm" /></td>
				<td>
					<div class="view operationButton" value="${list.id }">查看</div>
				</td>
		</c:forEach>

	</table>
	<div class="pageBar">
		<tkxwz:page url="${ctx }/article.do?action=listArticle"
			page="${page }" />
	</div>
</body>
</html>
