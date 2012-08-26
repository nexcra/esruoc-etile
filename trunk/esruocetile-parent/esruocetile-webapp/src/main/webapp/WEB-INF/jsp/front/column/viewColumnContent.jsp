<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/front.css" />
<script type="text/javascript" src="${ctx }/js/article.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#font_small").click(function() {
			fontZoomA();
		});

		$("#font_big").click(function() {
			fontZoomB();
		});

		$("#close_window").click(function() {
			window.close();
		});

	});
</script>
</head>
<body>
	<%@include file="/common/top.jsp"%>
	<div class="main">
		<%@include file="/common/left.jsp"%>
		<div class="right">
			<%@include file="/common/frontLogin.jsp"%>
			<div class="path">
				您现在所在的位置：<a href="${ctx }">首页</a>&gt;&gt; 查看内容 &gt;&gt;
			</div>
			<div class="column_name">
				<span>&gt;&gt;</span> 查看内容
			</div>
			<div class="article_title">${map.column_name }</div>


			<div class="article_content" id="article_content">
				${map.column_content }</div>
			<div class="article_control">
				【字体： <span id="font_small" class="cursor_pointer">小</span> <span
					id="font_big" class="cursor_pointer">大</span>】 【 <span
					id="close_window" class="cursor_pointer">关闭窗口</span>】
			</div>
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>
