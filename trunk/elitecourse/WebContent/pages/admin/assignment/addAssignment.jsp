<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增作业</title>
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
					overrideSelectedTabForSubPage('listAssignment', '作业管理',
							'admin/assignment.do?action=listAssignment');
				});

	});

	$(function() {
		$("#addForm").validate({
			rules : {
				id : {
					required : true
				}
				 ,  userId  :  {                required  :  true ,digits:true   }  ,  userName  :  {                required  :  true  }  ,  assignmentClassId  :  {                required  :  true ,digits:true   }  ,  submitTime  :  {                required  :  true  }  ,  classesId  :  {                required  :  true ,digits:true   }  ,  description  :  {                required  :  true  }  ,  content  :  {                required  :  true  }  ,  path  :  {                required  :  true  }  ,  grade  :  {                required  :  true ,digits:true   }  ,  reContent  :  {                required  :  true  }  ,  reTime  :  {                required  :  true  } 
			},
			messages : {

			}
		})
	});
</script>
</head>
<body>
	<form action="${ctx }/admin/assignment.do?action=addAssignment"
		name="addForm" id="addForm" method="post">
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>
			<tr>
				<td class="fieldName" width="20%">userId:</td>
				<td class="fieldForm" width="80%"><select name="userId"
					id="userId"><option value="">请选择</option>
						<c:forEach var="userList" items="${userList }">
							<option value="${userList.id }">${userList.name }</option>
						</c:forEach></select><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">userName:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="userName" id="userName" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">assignmentClassId:</td>
				<td class="fieldForm" width="80%"><select
					name="assignmentClassId" id="assignmentClassId"><option
							value="">请选择</option>
						<c:forEach var="assignmentClassList"
							items="${assignmentClassList }">
							<option value="${assignmentClassList.id }">${assignmentClassList.name
								}</option>
						</c:forEach></select><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">submitTime:</td>
				<td class="fieldForm" width="80%"><input name="submitTime"
					id="submitTime" type="text" class="inputText"
					onclick="WdatePicker({el:'submitTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					<img
					onclick="WdatePicker({el:'submitTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					src="../widget/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" align="absmiddle"> <span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">classesId:</td>
				<td class="fieldForm" width="80%"><select name="classesId"
					id="classesId"><option value="">请选择</option>
						<c:forEach var="classesList" items="${classesList }">
							<option value="${classesList.id }">${classesList.name }</option>
						</c:forEach></select><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">description:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="description" id="description" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">content:</td>
				<td class="fieldForm" width="80%"><textarea name="content"
						id="content"></textarea><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">path:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="path" id="path" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">grade:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="grade" id="grade" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">reContent:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="reContent" id="reContent" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">reTime:</td>
				<td class="fieldForm" width="80%"><input name="reTime"
					id="reTime" type="text" class="inputText"
					onclick="WdatePicker({el:'reTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					<img
					onclick="WdatePicker({el:'reTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
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
	<script type="text/javascript">                  CKEDITOR.replace('content',  {                          filebrowserUploadUrl  :  'ckeditor/uploader?Type=File',                          filebrowserImageUploadUrl  :  'ckeditor/uploader?Type=Image',                          filebrowserFlashUploadUrl  :  'ckeditor/uploader?Type=Flash'                  });          </script>
</body>
</html>
