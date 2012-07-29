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
		//展开搜索栏
		$("#expand").click(function() {
			$("#searchTable").show();
		});

		//收缩搜索栏
		$("#fold").click(function() {
			$("#searchTable").hide();
		});
		//更新
		$(".update").click(
				function() {
					overrideSelectedTab('updateArticle', '修改文章',
							'article.do?action=toUpdateArticle&id='
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
						url : "article.do?action=deleteArticle&randomNum="
								+ new Date().getTime() + Math.random(),
						cache : false,
						data : "id=" + $(this).attr("value"),
						dataType : "text",
						success : function(data) {
							alert(data);
							overrideSelectedTab('listArticle', '文章管理',
									'article.do?action=listArticle');
						}
					});
				});
		//查看
		$(".view").click(
				function() {
					window.open('article.do?action=viewArticle&id='
							+ $(this).attr("value"), "_blank");
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
		<div class="button2" id="expand">
			<div class="button1Left"></div>
			展开搜索栏
			<div class="button1Right"></div>
		</div>

	</div>
	<form action="${ctx }/article.do?action=searchArticle" name="queryForm"
		id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr>
				<td class="fieldName">栏目:</td>
				<td class="fieldForm"><select name="columnId" id="columnId">
						<option value="0" selected="selected">全部</option>
						<c:forEach var="list" items="${list }">
							<option value="${list.id }"
								<c:if test="${bean.columnId == list.id }"> selected="selected" </c:if>>${list.columnName
								}</option>
						</c:forEach>

				</select></td>

				<td class="fieldName"></td>
				<td class="fieldForm"></td>
			</tr>
			<tr>
				<td class="fieldName">标题:</td>
				<td class="fieldForm"><input type="text" class="width350"
					name="title" id="title" value="${bean.title }" /></td>
				<td class="fieldName">副标题:</td>
				<td class="fieldForm"><input type="text" class="width350"
					name="subTitle" id="subTitle" value="${bean.subTitle }" /></td>
			</tr>
			<tr>
				<td class="fieldName">来源:</td>
				<td class="fieldForm"><select name="source" id="source">
						<option value="0" selected="selected">全部</option>
						<option value="1"
							<c:if test="${bean.source == 1 }"> selected="selected" </c:if>>本站原创</option>
						<option value="2"
							<c:if test="${bean.source == 2 }"> selected="selected" </c:if>>转载</option>
				</select></td>
				<td class="fieldName">状态:</td>
				<td class="fieldForm"><select id="status" name="status">
						<option value="0" selected="selected">全部</option>
						<option value="1"
							<c:if test="${bean.status == 1 }"> selected="selected" </c:if>>正式发布</option>
						<option value="2"
							<c:if test="${bean.status == 2 }"> selected="selected" </c:if>>待发布</option>
				</select></td>
			</tr>


			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" class="submitButton" /> <input type="reset"
					value="重置" class="resetButton" /> <input type="reset" id="fold"
					value="隐藏" class="resetButton" /></td>
				</td>
			</tr>
		</table>
	</form>
	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>文章名称</th>
				<th>所属栏目</th>
				<th>来源</th>
				<th>状态</th>
				<th>添加时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:if test="${empty page.pageDatas }">
			<tr>
				<td colspan="6">对不起，暂时没有数据</td>
			</tr>

		</c:if>
		<c:forEach items="${page.pageDatas }" var="list" varStatus="vs">
			<tr title="${list.title }"
				<c:if test="${vs.index %2==1 }">
					class="a1"
				
				</c:if>>
				<td><input type="checkbox" class="checkList" name="checkList"
					value="${list.id }" /></td>
				<td>${list.title }</td>
				<td>${list.column_name }</td>
				<td><c:choose>
						<c:when test="${list.source ==1 }">本站原创</c:when>
						<c:when test="${list.source ==2 }">转开</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${list.status ==1 }">已发布</c:when>
						<c:when test="${list.status ==2 }">待发布</c:when>
						<c:otherwise>未知状态 </c:otherwise>
					</c:choose></td>
				<td><fmt:formatDate value="${list.insert_time }"
						pattern="yyyy-MM-dd hh:ss:mm" /></td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
		</c:forEach>

	</table>
	<div class="pageBar">
		<tkxwz:page url="${ctx }/article.do?action=listArticle"
			page="${page }" />
	</div>
</body>
</html>
