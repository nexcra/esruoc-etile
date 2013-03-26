<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>底部</title>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
</head>
<body>
	<div class="clear"></div>
	<div class="bottom">
		<p style="color:white; ">
			<a href="${ctx }">回到首页</a> | <a
				onclick="setHome(this,window.location)" href="#">设为首页   </a> |  <a
				href="#"
				onclick="addFavorite(window.location, '华南师范大学普通话培训');return false">加入收藏 </a> | 
			  <a href="mailto:xxx@qq.com">联系我们 </a> | <a
				href="${ctx }/login.do?action=toLogin" target="_blank">管理登录</a>
		</p>
		<div class="font-content">
			 ${footerMap.content }
		</div>
	</div>
</body>
</html>