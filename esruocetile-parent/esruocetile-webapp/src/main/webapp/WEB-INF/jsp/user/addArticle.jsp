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
	});
</script>
</head>
<body>
	<div class="functionList">您正在操作：增加文章</div>
	<form action="${ctx }/article.do?action=addArticle" name="addForm"
		id="addForm" method="post">
		<input type="hidden" name="orderNum" value="2" />
		<input type="hidden" name="hitCount" value="2" />
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName" width="40%">标题:</td>
				<td class="fieldForm" width="60%"><input type="text"
					name="title" id="title" /><span class="asterisk">*</span></td>
			</tr>
			
				<tr>
				<td class="fieldName" width="40%">栏目:</td>
				<td class="fieldForm" width="60%">

	<select name="columnId" id="columnId">
		<option value="1" selected="selected">栏目1</option>
	</select>
</td>
			</tr>

			<tr>
				<td class="fieldName">内容:</td>
				<td class="fieldForm"><textarea cols="40" rows="5"
						name="content" id="content">
					
					
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
</body>
</html>
