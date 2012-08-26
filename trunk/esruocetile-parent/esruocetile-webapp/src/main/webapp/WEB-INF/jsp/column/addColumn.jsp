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
<script type="text/javascript" src="${ctx }/widget/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$(function() {
		$("#addForm").validate({
			rules : {
				columnName : {
					required : true,
					remote : "column.do?action=isColumnExist"
				},
				columnType : {
					required : true
				},
				orderNum : {
					required : true
				},
				showOnNav : {
					required : true
				}
			},
			messages : {
				columnName : {
					remote : "此栏目名已存在，请使用另一名字"
				}
			}
		});

		//展开收缩栏目类型为单页面时候
		$("#columnType").change(function() {
			if ($(this).val() == 1) {
				$("#contentRow").show();
			} else {
				$("#contentRow").hide();
			}
		});
	});
</script>
</head>
<body>
	<form action="${ctx }/column.do?action=addColumn" name="addForm"
		id="addForm" method="post"> 
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName" width="100">栏目名:</td>
				<td class="fieldForm" width="900"><input type="text"
					name="columnName" id="columnName" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName">在导航栏显示:</td>
				<td class="fieldForm"><select name="showOnNav" id="showOnNav">
						<option value="1" selected="selected">是</option>
						<option value="0">否</option>
				</select><span class="asterisk">*</span></td>
			</tr>

			<tr>
				<td class="fieldName">类型:</td>
				<td class="fieldForm"><select name="columnType" id="columnType">
						<option value="2" selected="selected">文章栏目</option>
						<option value="1">单页面</option>
				</select><span class="asterisk">*</span>
					文章栏目：点击文章栏目进入该栏目下的文章列表；单页面：单一页面，直接显示内容</td>
			</tr>
			<tr id="contentRow" style="display: none;">
				<td class="fieldName">页面内容:</td>
				<td class="fieldForm"><textarea name="columnContent"
						id="columnContent">
					</textarea></td>
			</tr>
			<tr>
				<td class="fieldName">序号:</td>
				<td class="fieldForm"><input type="text" name="orderNum"
					id="orderNum" value="1" /><span class="asterisk">*</span>
					序号越大排序越靠前</td>
			</tr>

			<tr>
				<td class="fieldName">说明:</td>
				<td class="fieldForm"><textarea cols="80" rows="3"
						name="description" id="description"></textarea></td>
			</tr>


			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="提交"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		CKEDITOR.replace("columnContent", {
			filebrowserUploadUrl : 'ckeditor/uploader?Type=File',
			filebrowserImageUploadUrl : 'ckeditor/uploader?Type=Image',
			filebrowserFlashUploadUrl : 'ckeditor/uploader?Type=Flash'
		});
	</script>
</body>
</html>
