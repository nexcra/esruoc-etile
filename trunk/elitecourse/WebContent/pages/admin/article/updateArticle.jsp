<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改文章</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jQuery.validate.plugIn.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>

<script type="text/javascript" src="${ctx }/widget/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton").click(
				function() {
					overrideSelectedTabForSubPage('listArticle', '文章管理',
							'admin/article.do?action=listArticle');
				});

	});

	$(function() {
		$("#updateForm").validate({
			rules : {
				id : {
					required : true
				},
				title : {
					required : true
				},
				columnId : {
					required : true,
				},
				subTitle : {
					required : true
				},
				type : {
					required : true
				},
				content : {
					required : true
				}
			},
			messages : {

			}
		})
	});
</script>
</head>
<body>
	<form action="${ctx }/admin/article.do?action=updateArticle"
		name="updateForm" id="updateForm" method="post">
		<input type="hidden" name="id" id="id" value="${map.id }" />
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>
			<tr>
				<td class="fieldName" width="20%">所属栏目:</td>
				<td class="fieldForm" width="80%"><select name="columnId"
					id="columnId"><option value="">请选择</option>
						<c:forEach var="columnList" items="${columnList }">
							<option value="${columnList.id }"
								<c:if test="${map.column_id == columnList.id }"> selected = 'selected' </c:if>>
								<c:if test="${columnList.level ==1 }">
								≡&nbsp;${columnList.name }
								</c:if>
								<c:if test="${columnList.level ==2 }">
								&nbsp;&nbsp;&nbsp;└&nbsp;${columnList.name }
								</c:if>
								<c:if test="${columnList.level ==3 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${columnList.name }
								</c:if>
								<c:if test="${columnList.level ==4 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${columnList.name }
								</c:if>
								<c:if test="${columnList.level ==5 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${columnList.name }
								</c:if>
							</option>
						</c:forEach>
						<span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">标题:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="title" id="title" value="${map.title }" size="50" /><span
					class="asterisk">*</span></td>
			</tr>

			<tr>
				<td class="fieldName" width="20%">副标题:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="subTitle" id="subTitle" value="${map.sub_title }" size="55" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">来源:</td>
				<td class="fieldForm" width="80%"><select name="type" id="type"><option
							value="">请选择</option>
						<option value="1"
							<c:if test="${map.type == 1 }"> selected = 'selected' </c:if>>本站原创</option>
						<option value="2"
							<c:if test="${map.type == 2 }"> selected = 'selected' </c:if>>网络转载</option>
				</select> <span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">内容:</td>
				<td class="fieldForm" width="80%"><textarea name="content"
						id="content">${map.content }</textarea><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="修改"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /> <input type="button" id="returnButton"
					value="返回" class="submitButton" /></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		CKEDITOR.replace('content', {
			filebrowserUploadUrl : 'ckeditor/uploader?Type=File',
			filebrowserImageUploadUrl : 'ckeditor/uploader?Type=Image',
			filebrowserFlashUploadUrl : 'ckeditor/uploader?Type=Flash'
		});
	</script>
</body>
</html>
