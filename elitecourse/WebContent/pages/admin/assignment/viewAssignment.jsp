<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看作业</title>
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
									'listAssignment',
									'作业管理',
									'admin/assignment.do?action=listAssignment');
						});

	});
</script>
</head>
<body>
	<table width="100%" border="1" class="formTable">
		 <tr>              <td  class="fieldName"  width="20%">user_id:</td>                <td  class="fieldForm"  width="80%">${map.user_id  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">user_name:</td>                <td  class="fieldForm"  width="80%">${map.user_name  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">assignment_class_id:</td>                <td  class="fieldForm"  width="80%">${map.assignment_class_id  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">submit_time:</td>                <td  class="fieldForm"  width="80%"><fmt:formatDate value='${map.submit_time }' pattern='yyyy-MM-dd HH:mm:ss'/></td>  </tr>  <tr>              <td  class="fieldName"  width="20%">classes_id:</td>                <td  class="fieldForm"  width="80%">${map.classes_id  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">description:</td>                <td  class="fieldForm"  width="80%">${map.description  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">content:</td>                <td  class="fieldForm"  width="80%">${map.content  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">path:</td>                <td  class="fieldForm"  width="80%">${map.path  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">grade:</td>                <td  class="fieldForm"  width="80%">${map.grade  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">re_content:</td>                <td  class="fieldForm"  width="80%">${map.re_content  }</td>  </tr>  <tr>              <td  class="fieldName"  width="20%">re_time:</td>                <td  class="fieldForm"  width="80%"><fmt:formatDate value='${map.re_time }' pattern='yyyy-MM-dd HH:mm:ss'/></td>  </tr> 
		<tr>
			<td class="fieldName"></td>
			<td class="fieldForm"><input type="button" id="returnButton"
				value="返回" class="submitButton" /></td>
		</tr>
	</table>
</body>
</html>
