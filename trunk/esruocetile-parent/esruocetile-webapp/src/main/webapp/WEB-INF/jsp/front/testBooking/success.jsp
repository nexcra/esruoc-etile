<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/front.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#returnButton").click(function() {
			history.back(-1);
		});
	});
</script>
</head>
<body>
	<%@include file="/common/top.jsp"%>
	<div class="main">
		<%@include file="/common/left.jsp"%>
		<div class="right">
			<div class="path">
				您现在所在的位置：<a href="${ctx }">首页</a>&nbsp;&gt;&gt;
			</div>
			<div class="column_article_list">
				 预约成功，请<a href="返回"></a>
			</div>
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>
