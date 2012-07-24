<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }>>导入学生数据</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript">
	$(function() {
		$("#importForm").validate({
			rules : {
				importExcel : {
					required : true,
					accept : "xls"
				}
			},
			messages : {
				importExcel : {
					required : "请选择文件再导入",
					accept : " 只支持xls文件(excel 2003及其以下版本) "
				}
			}
		});
	});
</script>
<title>导入学生数据</title>
</head>
<body>
	<form action="data.do?action=import" name="importForm" id="importForm"
		method="post" enctype="multipart/form-data">
		<div>请选择excel 2003及其以下版本的文件导入</div>
		<div>下载<a href="#">导入模板</a></div>
		<input type="file" name="importExcel" id="importExcel"> <input
			type="submit" value="导入">
	</form>
</body>
</html>