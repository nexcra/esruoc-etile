<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="${ctx }/js/time.js"></script>
<script type="text/javascript">
	$(function() {
		displayTime("time");
	});
</script>
</head>
<body>
	<div class="top">
		<div class="flashBanner">
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0"
				width="423" height="185">
				<param name="movie" value="${ctx}/flash/banner.swf" />
				<param name="quality" value="high" />
				<embed src="${ctx}/flash/banner.swf" quality="high"
					pluginspage="http://www.macromedia.com/go/getflashplayer"
					type="application/x-shockwave-flash" width="423" height="185"></embed>
			</object>
		</div>
		<div id="time_div">
			现在是:<span id="time"></span>
		</div>
	</div>
</body>
</html>