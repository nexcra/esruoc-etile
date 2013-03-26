<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增视频</title>
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

<script type="text/javascript" src="${ctx }/js/common.js"></script>

<script type="text/javascript">
	$(function() {
		$("#returnButton").click(
				function() {
					overrideSelectedTabForSubPage('listVideo', '视频管理',
							'admin/video.do?action=listVideo');
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
				source : {
					required : true
				},
				type : {
					required : true
				},
				
				videoFile:{
					required: true
				},
				description : {
					required : true
				},
				path : {
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
	<form action="${ctx }/admin/video.do?action=addVideo" name="addForm"
		id="addForm" method="post" enctype="multipart/form-data">
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>
			<tr>
				<td class="fieldName" width="20%">视频名称:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="name" id="name" size="40" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">来源:</td>
				<td class="fieldForm" width="80%"><select name="source"
					id="source"><option value="">请选择</option>
						<option value="1">本站原创</option>
						<option value="2">网络转载</option>
				</select> <span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">类型:</td>
				<td class="fieldForm" width="80%"><select name="type" id="type"><option
							value="">请选择</option>
						<option value="FLV">FLV</option>
						<option value="RM">RM</option>
						<option value="REAL">REAL</option>
						<option value="FLASH">FLASH</option>
				</select> <span class="asterisk">*</span></td>


			</tr>
			<tr>
				<td class="fieldName" width="20%">选择文件:</td>
				<td class="fieldForm" width="80%"><input type="file"
					name="videoFile" id="videoFile" size="40" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">描述:</td>
				<td class="fieldForm" width="80%"><textarea rows="4" cols="30"
						name="description" id="description"></textarea><span
					class="asterisk">*</span></td>
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
