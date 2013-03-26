<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增节点</title>
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
					overrideSelectedTabForSubPage('listNode', '节点管理',
							'admin/node.do?action=listNode');
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
				content : {
					required : true
				},
				orderNumber : {
					required : true,
					digits : true
				},
				type : {
					required : true
				}
			},
			messages : {

			}
		})
	});

	$(function() {
		$("#type").change(function() {
			var val = $.trim($("#type").val());
			if ("text" == val) {
				$("#linkRow").hide();
				$("#contentRow").show();
			} else {
				$("#linkRow").show();
				$("#contentRow").hide();
			}
		})
	});
</script>
</head>
<body>
	<form action="${ctx }/admin/node.do?action=addNode" name="addForm"
		id="addForm" method="post">
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>

			<tr>
				<td class="fieldName" width="20%">所属节点:</td>
				<td class="fieldForm" width="80%"><select name="parentParams"
					id="parentParams"><option value="@@@@0@@0"
							selected="selected">请选择</option>
						<c:forEach var="nodeList" items="${nodeList }">
							<option
								value="${nodeList.fullPathId }@@${nodeList.fullPathName }@@${nodeList.level }@@${nodeList.id }">
								<c:if test="${nodeList.level ==1 }">
								≡&nbsp;${nodeList.name }
								</c:if>
								<c:if test="${nodeList.level ==2 }">
								&nbsp;&nbsp;&nbsp;└&nbsp;${nodeList.name }
								</c:if>
								<c:if test="${nodeList.level ==3 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${nodeList.name }
								</c:if>
								<c:if test="${nodeList.level ==4 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${nodeList.name }
								</c:if>
								<c:if test="${nodeList.level ==5 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${nodeList.name }
								</c:if>
							</option>
						</c:forEach></select><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">节点名称:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="name" id="name" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">链接类型:</td>
				<td class="fieldForm" width="80%"><select id="type" name="type">
						<option value="text">文本</option>
						<option value="link">链接</option>

				</select> <span class="asterisk">*</span></td>
			</tr>
			<tr id="contentRow">
				<td class="fieldName" width="20%">节点内容:</td>
				<td class="fieldForm" width="80%"><textarea name="content"
						id="content"></textarea><span class="asterisk">*</span></td>
			</tr>
			<tr style="display: none" id="linkRow">
				<td class="fieldName" width="20%">链接:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="link" id="link" size="100"/><span class="asterisk">*</span></td>
			</tr>
<input type="hidden"
					name="orderNumber" id="orderNumber" value="4"/>
		<!-- 	<tr >
				<td class="fieldName" width="20%">序号:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="orderNumber" id="orderNumber" /><span class="asterisk">*</span></td>
			</tr> -->

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
		CKEDITOR.replace('content', {
			filebrowserUploadUrl : 'ckeditor/uploader?Type=File',
			filebrowserImageUploadUrl : 'ckeditor/uploader?Type=Image',
			filebrowserFlashUploadUrl : 'ckeditor/uploader?Type=Flash'
		});
	</script>
</body>
</html>
