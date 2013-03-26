<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看权限</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'listPrivilege',
									'权限管理',
									'admin/privilege.do?action=listPrivilege');
						});

	});
</script>
</head>
<body>
	<table width="100%" border="1" class="formTable">
		 <tr>              <td  class="fieldName"  width="20%">privilege_code:</td>                <td  class="fieldForm"  width="80%">${map.privilege_code  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">name:</td>                <td  class="fieldForm"  width="80%">${map.name  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">status:</td>                <td  class="fieldForm"  width="80%">${map.status  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">create_user_name:</td>                <td  class="fieldForm"  width="80%">${map.create_user_name  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">create_time:</td>                <td  class="fieldForm"  width="80%"><fmt:formatDate value='${map.create_time }' pattern='yyyy-MM-dd HH:mm:ss'/></td>  </tr>  <tr>              <td  class="fieldName"  width="20%">remark:</td>                <td  class="fieldForm"  width="80%">${map.remark  }</td>  </tr> 
		<tr>
			<td class="fieldName"></td>
			<td class="fieldForm"><input type="button" id="returnButton"
				value="返回" class="submitButton" /></td>
		</tr>
	</table>
</body>
</html>
