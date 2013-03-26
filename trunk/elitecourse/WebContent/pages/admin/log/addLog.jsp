<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增日志</title>
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
					overrideSelectedTabForSubPage('listLog', '日志管理',
							'admin/log.do?action=listLog');
				});

	});

	$(function() {
		$("#addForm").validate({
			rules : {
				id : {
					required : true
				}
				 ,  logType  :  {                required  :  true  }  ,  userId  :  {                required  :  true ,digits:true   }  ,  userName  :  {                required  :  true  }  ,  module  :  {                required  :  true  }  ,  description  :  {                required  :  true  }  ,  ipAddress  :  {                required  :  true  } 
			},
			messages : {

			}
		})
	});
</script>
</head>
<body>
	<form
		action="${ctx }/admin/log.do?action=addLog"
		name="addForm" id="addForm" method="post">
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>
			<tr><td  class="fieldName"  width="20%">logType:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="logType"  id="logType"  /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">userId:</td>  <td  class="fieldForm"  width="80%"><select name="userId" id="userId"><option value="">请选择</option><c:forEach var="userList" items="${userList }"><option value="${userList.id }">${userList.name }</option></c:forEach></select><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">userName:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="userName"  id="userName"  /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">module:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="module"  id="module"  /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">description:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="description"  id="description"  /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">ipAddress:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="ipAddress"  id="ipAddress"  /><span  class="asterisk">*</span></td> </tr>
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
