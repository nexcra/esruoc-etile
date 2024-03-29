<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/blue.css" />
<script type="text/javascript" src="${ctx }/js/time.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript" src="${ctx }/js/myfocus-2.0.2.min.js"></script>
<script type="text/javascript">
	myFocus.set({
		id : 'myFocus',//焦点图盒子ID
		pattern : 'mF_fscreen_tb',//风格应用的名称
		time : 3,//切换时间间隔(秒)
		trigger : 'click',//触发切换模式:'click'(点击)/'mouseover'(悬停)
		width : 306,//设置图片区域宽度(像素)
		height : 250,//设置图片区域高度(像素)
		txtHeight : '0'//文字层高度设置(像素),'default'为默认高度，0为隐藏
	});
</script>
</head>
<body>
	<%@include file="/common/top.jsp"%>
	<div class="main">
		<div class="justForAdjudst"></div>
		<%@include file="/common/left.jsp"%>
		<div class="right">
			<%@include file="/common/frontLogin.jsp"%>
			<div class="path">
				您现在所在的位置：<a href="${ctx }">首页</a>&nbsp;&gt;&gt;
			</div>
			<div class="newest">
				<div class="pic_article">
					<div id="myFocus">
						<!--焦点图盒子-->
						<div class="loading">
							<img src="${ctx }/images/skin/default/slide/loading.gif"
								alt="请稍候..." />
						</div>
						<!--载入画面(可删除) -->
						<div class="pic"> 
							<ul>
								<li><img src="${ctx }/images/skin/default/slide/02.jpg"
									thumb="" alt="#" text="" /></li>
								<li><img src="${ctx }/images/skin/default/slide/01.jpg"
									thumb="" alt="#" text="" /></li>
								<li><img src="${ctx }/images/skin/default/slide/03.jpg"
									thumb="" alt="#" text="" /></li>
						</div>
					</div>
				</div>
				<div class="newest_article">
					<h2> </h2>
					<ul>
						<c:forEach items="${recentArticleList.pageDatas }"
							var="recentArticleList" varStatus="vs" end="6">
							<li><a title="${recentArticleList.title}"
								href="${ctx }/article.do?action=viewArticle&id=${recentArticleList.id }">
									<c:set value="${recentArticleList.title}" var="c" /> <c:choose>
										<c:when test="${fn:length(c) > 20}">
											<c:out value="${fn:substring(c, 0, 20)}...." />
										</c:when>
										<c:otherwise>
											<c:out value="${c}" />
										</c:otherwise>
									</c:choose>
							</a></li>

						</c:forEach> 
					</ul>
				</div>
				<div class="clear"></div>
				<div class="column_article">
					<h2>
						 <a href="${ctx }/column.do?action=listArticleByColumnCode&columnCode=cszn"> 测试指南</a>
					</h2>
					<ul>
						<c:forEach items="${csznArticleList.pageDatas }" var="list"
							varStatus="vs" end="6">
							<li><a title="${list.title}"
								href="${ctx }/article.do?action=viewArticle&id=${list.id }">
									<c:set value="${list.title}" var="c" /> <c:choose>
										<c:when test="${fn:length(c) > 17}">
											<c:out value="${fn:substring(c, 0, 17)}...." />
										</c:when>
										<c:otherwise>
											<c:out value="${c}" />
										</c:otherwise>
									</c:choose>
							</a></li>

						</c:forEach>
					</ul>
					<div class="column_article_bottom"></div>
				</div>
				<div class="column_article">
					<h2>
						 <a href="${ctx }/column.do?action=listArticleByColumnCode&columnCode=tzga">通知公告</a>
					</h2>
					<ul>
						<c:forEach items="${tzggArticleList.pageDatas }" var="list"
							varStatus="vs" end="5">
							<li><a title="${list.title}"
								href="${ctx }/article.do?action=viewArticle&id=${list.id }">
									<c:set value="${list.title}" var="c" /> <c:choose>
										<c:when test="${fn:length(c) > 17}">
											<c:out value="${fn:substring(c, 0, 17)}...." />
										</c:when>
										<c:otherwise>
											<c:out value="${c}" />
										</c:otherwise>
									</c:choose>
							</a></li>

						</c:forEach>
					</ul>
					<div class="column_article_bottom"></div>
				</div>
				<div class="column_banner">
					<div class="column_banner_div">
						<h2>机构设置</h2>
						<ul>
							<c:forEach items="${jgszArticleList.pageDatas }" var="list"
								varStatus="vs" end="6">
								<li><a
									href="${ctx }/article.do?action=viewArticle&id=${list.id }">
										<c:set value="${list.title}" var="c" /> <c:choose>
											<c:when test="${fn:length(c) > 10}">
												<c:out value="${fn:substring(c, 0, 10)}...." />
											</c:when>
											<c:otherwise>
												<c:out value="${c}" />
											</c:otherwise>
										</c:choose>
								</a> |</li>

							</c:forEach>
						</ul>
					</div>
					<div class="column_banner_div">
						<h2>政策文件</h2>
						<ul>
							
							<c:forEach items="${zcwjColumnList }" var="list"
								varStatus="vs" end="6">
								<li><a
									href="${ctx }/column.do?action=listArticle&columnId=${list.id}&columnType=${list.columnType }">
										<c:set value="${list.columnName}" var="c" /> <c:choose>
											<c:when test="${fn:length(c) > 10}">
												<c:out value="${fn:substring(c, 0, 10)}...." />
											</c:when>
											<c:otherwise>
												<c:out value="${c}" />
											</c:otherwise>
										</c:choose>
								</a> |</li>

							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
				 
			</div>
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>
