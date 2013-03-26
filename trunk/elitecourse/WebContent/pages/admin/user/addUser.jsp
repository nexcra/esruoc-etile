<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增用户</title>
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
					overrideSelectedTabForSubPage('listUser', '用户管理',
							'admin/user.do?action=listUser');
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
				realName : {
					required : true
				},
				password : {
					required : true
				},
				status : {
					required : true
				},
				gender : {
					required : true
				},
				origin : {
					required : true
				},
				birthDate : {
					required : true
				},
				groupId : {
					required : true,
					digits : true
				},
				email : {
					required : true
				},
				telephone : {
					required : true
				},
				isAdmin : {
					required : true
				},
				isLocked : {
					required : true
				},
				age : {
					required : true,
					digits : true
				},
				role : {
					required : true
				},
				/* classesId : {
					required : true,
					digits : true
				}, */
				remark : {
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
	<form action="${ctx }/admin/user.do?action=addUser" name="addForm"
		id="addForm" method="post">
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>
			<tr>
				<td class="fieldName" width="20%">用户名:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="name" id="name" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">真实姓名:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="realName" id="realName" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">身份:</td>
				<td class="fieldForm" width="80%"><select id="isAdmin"
					name="isAdmin">
						<option value="manager">管理员</option>
						<option value="student">学生</option>
				</select> <span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">学号:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="studentNo" id="studentNo" /> </td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">密码:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="password" id="password" /><span class="asterisk">*</span></td>
			</tr>
			<!-- 	<tr>
				<td class="fieldName" width="20%">状态:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="status" id="status" /><span class="asterisk">*</span></td>
			</tr> -->
			<tr>
				<td class="fieldName" width="20%">性别:</td>
				<td class="fieldForm" width="80%"><select id="gender"
					name="gender">
						<option value="male">男</option>
						<option value="female">女</option>

				</select> <span class="asterisk">*</span></td>
			</tr>
			<!-- 		<tr>
				<td class="fieldName" width="20%">origin:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="origin" id="origin" /><span class="asterisk">*</span></td>
			</tr> -->
			<tr>
				<td class="fieldName" width="20%">出生日期:</td>
				<td class="fieldForm" width="80%"><input name="birthDate"
					id="birthDate" type="text" class="inputText"
					onclick="WdatePicker({el:'birthDate',isShowClear:false,dateFmt:'yyyy-MM-dd'})" />
					<img
					onclick="WdatePicker({el:'birthDate',isShowClear:false,dateFmt:'yyyy-MM-dd'})"
					src="../widget/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" align="absmiddle"> <span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">所属组:</td>
				<td class="fieldForm" width="80%"><select name="groupId"
					id="groupId"><option value="">请选择</option>
						<c:forEach var="groupList" items="${groupList }">
							<option value="${groupList.id }">${groupList.name }</option>
						</c:forEach></select><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">邮箱:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="email" id="email" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">电话:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="telephone" id="telephone" /><span class="asterisk">*</span></td>
			</tr>
			
			<!-- 			<tr>
				<td class="fieldName" width="20%">isLocked:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="isLocked" id="isLocked" /><span class="asterisk">*</span></td>
			</tr> -->
			<tr>
				<td class="fieldName" width="20%">年龄:</td>
				<td class="fieldForm" width="80%"><input type="text" name="age"
					id="age" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">角色:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="role" id="role" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">所在班级:</td>
				<td class="fieldForm" width="80%"><select name="classesId"
					id="classesId"><option value="">请选择</option>
						<c:forEach var="classesList" items="${classesList }">
							<option value="${classesList.id }">${classesList.name }</option>
						</c:forEach></select><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">备注:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="remark" id="remark" /><span class="asterisk">*</span></td>
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

</body>
</html>
