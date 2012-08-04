<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/front.css" />
<script type="text/javascript" src="${ctx }/js/time.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		displayTime("time");
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
			<div class="newest">
				<div class="pic_article">
					<img src="${ctx }/images/skin/default/front/demo_pic.jpg" />
				</div>
				<div class="newest_article">
					<h2>最新消息</h2>
					<ul>
						<c:forEach items="${recentArticleList.pageDatas }"
							var="recentArticleList" varStatus="vs" end="6">
							<li><a
								href="${ctx }/article.do?action=viewArticle&id=${recentArticleList.id }">
									<c:set value="${recentArticleList.title}" var="c" /> <c:choose>
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
				</div>
				<div class="column_article">
					<h2>
						<span>&gt;&gt;</span> 测试指南
					</h2>
					<ul>
						<li><a href="#">通知公告通知公告通知公告通知公告通公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告</a></li>
					</ul>
				</div>
				<div class="column_article">
					<h2>
						<span>&gt;&gt;</span> 最新公告
					</h2>
					<ul>
						<li><a href="#">通知公告通知公告通知公告通知公告通公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通告</a></li>
					</ul>
				</div>
				<div class="column_banner">
					<div class="column_banner_div">
						<h2>机构设置</h2>
						<ul>
							<li><a href="#">机构设置</a> |</li>
							<li><a href="#">机构职责</a> |</li>
							<li><a href="#">人员分工</a> |</li>
							<li><a href="#">办公地点</a></li>
						</ul>
					</div>
					<div class="column_banner_div">
						<h2>政策文件</h2>
						<ul>
							<li><a href="#">国家文件</a> |</li>
							<li><a href="#">广东文件</a> |</li>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
				<div class="column_article">
					<h2>
						<span>&gt;&gt;</span> 常见问题
					</h2>
					<ul>
						<li><a href="#">通知公告通知公告通知公告通知公告通公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告</a></li>
					</ul>
				</div>
				<div class="column_article">
					<h2>
						<span>&gt;&gt;</span> 文档下载
					</h2>
					<ul>
						<li><a href="#">通知公告通知公告通知公告通知公告通公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通公告</a></li>
						<li><a href="#">通知公告通知公告通知公告通知公告通告</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/bottom.jsp"%>
</body>
</html>
