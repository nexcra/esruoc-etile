<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增友情链接</title>
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
					overrideSelectedTabForSubPage('listFriendsite', '友情链接管理',
							'admin/friendsite.do?action=listFriendsite');
				});

	});

	$(function() {
		$("#addForm").validate({
			rules : {
				id : {
					required : true
				}
				 ,  name  :  {                required  :  true  }  ,  type  :  {                required  :  true  }  ,  link  :  {                required  :  true  }  ,  description  :  {                required  :  true  }  ,  picPath  :  {                required  :  true  }  ,  orderNumber  :  {                required  :  true ,digits:true   } 
			},
			messages : {

			}
		})
	});
</script>
</head>
<body>
	<form action="${ctx }/admin/friendsite.do?action=addFriendsite"
		name="addForm" id="addForm" method="post">
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>
			<tr>
				<td class="fieldName" width="20%">网站名称:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="name" id="name" /><span class="asterisk">*</span></td>
			</tr>
			<!-- <tr>
				<td class="fieldName" width="20%">type:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="type" id="type" /><span class="asterisk">*</span></td>
			</tr> -->
			<tr>
				<td class="fieldName" width="20%">链接:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="link" id="link" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">描述:</td>
				<td class="fieldForm" width="80%"><textarea name="description"
						id="description" rows="4" cols="30"></textarea><span
					class="asterisk">*</span></td>
			</tr>
			<!-- <tr>
				<td class="fieldName" width="20%">picPath:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="picPath" id="picPath" /><span class="asterisk">*</span></td>
			</tr> -->
			<tr>
				<td class="fieldName" width="20%">序号:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="orderNumber" id="orderNumber" /><span class="asterisk">*</span></td>
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
