<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看文章</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/article.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
$(function(){
	$("#font_small").click(
		function (){ 
			fontZoomA();
		}
	);
	
	$("#font_big").click(
		function (){ 
			fontZoomB();
		}
	);
	
	$("#close_window").click(
		function (){  
			window.close();
		}
	);
	
	
});
</script>
</head>
<body>
	<div style="width: 980px; margin: 20px auto; text-align: center;">
		<div style="font-weight: bold; font-size: 24px;">${map.title }</div>
		${map.sub_title }
		<div>${update_time }</div>


		<c:if test="${map.source ==1 }"> 本站原创
						</c:if>

		<c:if test="${map.source ==2 }">

			<a href="${map.copy_from }">转载</a>
		</c:if>

		<div style="border: 1px solid #ccc; margin: 10px 0; text-align: left;">${map.content
			}</div>
		<div style="text-align: right; padding-right: 20px;">作者:${map.author
			}</div>
</body>
</html>
