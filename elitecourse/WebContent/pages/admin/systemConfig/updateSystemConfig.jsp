<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改系统配置</title>
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
					overrideSelectedTabForSubPage('listSystemConfig', '系统配置管理',
							'admin/systemConfig.do?action=listSystemConfig');
				});

	});

	$(function() {
		$("#updateForm").validate({
			rules : {
				id : {
					required : true
				}

				,
				siteName : {

					required : true
				},
				copyright : {
					required : true
				},
				siteOwner : {
					required : true
				},
				contactPhone : {
					required : true
				},
				contactEmail : {
					required : true
				},
				status : {
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
	<form action="${ctx }/admin/systemConfig.do?action=updateSystemConfig"
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
				<td class="fieldName" width="20%">网站名字:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="siteName" id="siteName" value="${map.site_name }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">版权:</td>
				<td class="fieldForm" width="80%"><textarea name="copyright"
						id="copyright">${map.copyright }</textarea> <span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">网站所有者:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="siteOwner" id="siteOwner" value="${map.site_owner }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">联系电话:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="contactPhone" id="contactPhone" value="${map.contact_phone }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">联系邮件:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="contactEmail" id="contactEmail" value="${map.contact_email }" /><span
					class="asterisk">*</span></td>
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
		CKEDITOR.replace('copyright', {
			filebrowserUploadUrl : 'ckeditor/uploader?Type=File',
			filebrowserImageUploadUrl : 'ckeditor/uploader?Type=Image',
			filebrowserFlashUploadUrl : 'ckeditor/uploader?Type=Flash'
		});
	</script>
</body>
</html>
