<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript">
	$(function() {
		$("#addForm").validate({
			rules : {
				title : {
					required : true

				}
			}
		});

		//转载地址不为空时展开
		if ($("#copyFrom").val() != "") {
			$("#copyFromRow").show();
		}
		
		//展开收缩文章来源为转载时的"转载地址"
		$("#source").change(function() {
			if ($(this).val() == 2) {
				$("#copyFromRow").show();
			} else {
				$("#copyFromRow").hide();
			}
		});

	});
</script>
</head>
<body>
	<!-- <div class="functionList">您正在操作：修改文章</div> -->
	<form action="${ctx }/article.do?action=updateArticle"
		name="updateForm" id="addForm" method="post">
		<input type="hidden" name="id" value="${map.id }" /> <input
			type="hidden" name="orderNum" value="2" /> <input type="hidden"
			name="hitCount" value="2" />
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName" width="100">标题:</td>
				<td class="fieldForm" width="900"><input type="text"
					class="width400" name="title" id="title" value="${map.title }" /><span
					class="asterisk">*</span></td>
			</tr>

			<tr>
				<td class="fieldName" width="100">副标题:</td>
				<td class="fieldForm" width="900"><input type="text"
					class="width400" name="subTitle" id="subTitle"
					value="${map.sub_title }" /></td>
			</tr>

			<tr>
				<td class="fieldName" width="100">来源:</td>
				<td class="fieldForm" width="900"><select name="source"
					id="source">
						<option value="1"
							<c:if test="${map.source ==1 }"> 
						selected="selected"
						</c:if>>本站原创</option>
						<option value="2" id="copyFromOption"
							<c:if test="${map.source ==2 }"> 
						selected="selected"
						</c:if>>转载</option>
				</select><span class="asterisk">*</span></td>
			</tr>
			<tr id="copyFromRow" style="display: none;">
				<td class="fieldName" width="100">转载地址:</td>
				<td class="fieldForm" width="900"><input type="text"
					class="width400" name="copyFrom" id="copyFrom"
					value="${map.copy_from }" /></td>
			</tr>

			<tr>
				<td class="fieldName" width="100">状态:</td>
				<td class="fieldForm" width="900"><select id="status"
					name="status">
						<option value="1"
							<c:if test="${map.status ==1 }">
						selected="selected"
							</c:if>>正式发布</option>
						<option value="2"
							<c:if test="${map.status ==2 }">
						selected="selected"
							</c:if>>待审核</option>
				</select></td>
			</tr>



			<tr>
				<td class="fieldName" width="100">关键字:</td>
				<td class="fieldForm" width="900"><input type="text"
					class="width400" name="keywords" id="keywords"
					value="${map.keywords }" />多个关键字以空格隔开</td>
			</tr>
			<tr>
				<td class="fieldName">栏目:</td>
				<td class="fieldForm"><select name="columnId" id="columnId">
						<c:forEach var="list" items="${list }">
							<option value="${list.id }"
								<c:if test="${list.id == map.column_id}"> selected="selected" </c:if>>${list.columnName
								}</option>
						</c:forEach>


				</select><span class="asterisk">*</span></td>
			</tr>

			<tr>
				<td class="fieldName">内容:</td>
				<td class="fieldForm"><textarea name="content" id="content">
				${map.content }
					</textarea></td>
			</tr>

			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="提交"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /></td>
			</tr>
		</table>
	</form>
	<ckeditor:replace replace="content" basePath="${ctx }/ckeditor/" />
</body>
</html>


