<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增作业分类</title>
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
		$("#returnButton")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'listAssignmentClass', '作业分类管理',
									'admin/assignmentClass.do?action=listAssignmentClass');
						});

	});

	$(function() {
		$("#addForm").validate({
			rules : {
				id : {
					required : true
				},
				name : {
					required : true
				},
				description : {
					required : true
				},
				orderNumber : {
					required : true,
					digits : true
				},
				classesId : {
					required : true,
					digits : true
				},
				classesName : {
					required : true
				},
				startTime : {
					required : true
				},
				endTime : {
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
	<form
		action="${ctx }/admin/assignmentClass.do?action=addAssignmentClass"
		name="addForm" id="addForm" method="post">
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>
			<tr>
				<td class="fieldName" width="20%">作业名称:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="name" id="name" size="50"/><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">作业描述:</td>
				<td class="fieldForm" width="80%"><textarea name="description"
						id="description"></textarea><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">序号:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="orderNumber" id="orderNumber" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">分配给班级:</td>
				<td class="fieldForm" width="80%"><select name="classesId"
					id="classesId"><option value="">请选择</option>
						<c:forEach var="classesList" items="${classesList }">
							<option value="${classesList.id }">${classesList.name }</option>
						</c:forEach></select><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">作业开始时间</td>
				<td class="fieldForm" width="80%"><input name="startTime"
					id="startTime" type="text" class="inputText"
					onclick="WdatePicker({el:'startTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					<img
					onclick="WdatePicker({el:'startTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					src="../widget/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" align="absmiddle"> <span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">作业截止时间:</td>
				<td class="fieldForm" width="80%"><input name="endTime"
					id="endTime" type="text" class="inputText"
					onclick="WdatePicker({el:'endTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					<img
					onclick="WdatePicker({el:'endTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					src="../widget/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" align="absmiddle"> <span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="添加"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /> <input type="button" id="returnButton"
					value="返回" class="submitButton" /></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		CKEDITOR.replace('description', {
			filebrowserUploadUrl : 'ckeditor/uploader?Type=File',
			filebrowserImageUploadUrl : 'ckeditor/uploader?Type=Image',
			filebrowserFlashUploadUrl : 'ckeditor/uploader?Type=Flash'
		});
	</script>
</body>
</html>
