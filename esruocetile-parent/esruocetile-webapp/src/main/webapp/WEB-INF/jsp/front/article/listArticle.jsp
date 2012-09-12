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
<link rel="stylesheet" type="text/css" href="${ctx }/css/blue.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#booking").click(function() {

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
				您现在所在的位置：<a href="${ctx }">首页</a>&nbsp;&gt;&gt;文章列表&gt;&gt;
			</div>
			<div class="column_article_list">
				<div class="column_name">
					<h2>文章列表</h2> 
				</div>
				<div class="article_list">
					<table width="100%" border="0" class="article_list_table">
					
						<c:if test="${empty page.pageDatas }">
							<tr>
								<td width="27" align="center" height="25"></td>
								<td rowspan="3">对不起，还没有数据</td>
							</tr> 
						</c:if>
						<c:forEach items="${page.pageDatas }" var="list" varStatus="vs">
							<tr
								<c:if test="${vs.index %2==1 }">
					  
				</c:if>>
								<td width="27" align="center" height="25">&nbsp;<img
									src="${ctx }/images/skin/default/front/dot2.gif" width="9"
									height="15" /></td>
								<td width="444"><a class=""
									title="文章标题：${list.title }会 &#13;&#10;更新时间：<fmt:formatDate value="${list.insert_time }"
						pattern="yyyy-MM-dd hh:ss:mm" />"
									href="${ctx }/article.do?action=viewArticle&id=${list.id}" target="_blank">
										
										<c:set value="${list.title }" var="c" /> <c:choose>
										<c:when test="${fn:length(c) > 27}">
											<c:out value="${fn:substring(c, 0, 27)}...." />
										</c:when>
										<c:otherwise>
											<c:out value="${c}" />
										</c:otherwise>
									</c:choose>
										 </a></td>
								<td width="88">${list.column_name }</td>
								<td width="89"><fmt:formatDate value="${list.insert_time }"
						pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</table>
					<div class="page"><tkxwz:page url="${ctx }/column.do?action=listArticle&columnId=${param.columnId }" page="${page }" /></div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>
