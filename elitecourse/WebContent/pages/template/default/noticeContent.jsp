<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<script type="text/javascript"
	src="${ctx }/pages/template/default/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/pages/template/default/js/time.js"></script>
<script type="text/javascript"
	src="${ctx }/pages/template/default/js/time.js"></script>
<script type="text/javascript">
	$(function() {
		displayTime("time");
	});
</script>
<link href="${ctx }/pages/template/default/css/reset.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/pages/template/default/css/course.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<!--wrap begin-->
	<div class="top"></div>
	<!--content begin-->
	<!--navList begin-->
	<div class="navList">
		<div class="navLeft"></div>
		<ul id="nav">
			<li><a href="${ctx }/front/index.do">首页</a></li>
			<c:forEach items="${nodeList }" var="nodeList" varStatus="vs">
				<c:if test="${nodeList.level == 1 }">
					<li><a
						href="${ctx }/front/index.do?action=readNode&parentId=${nodeList.id}">${nodeList.name
							}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		<div class="navRight"></div>
	</div>
	<!--navList end-->
	<div class="content">
		<div class="mainContent">
			<div class="dateDisplay">
				<span id="time"></span>&nbsp;&nbsp; |&nbsp; &nbsp; 您所在的位置：首页
			</div>
			<div class="leftSide">
				<div class="leftSideContnt">
					<ul class="columnList">
						<c:forEach var="nodeSubList" items="${nodeSubList }">
							<li><a
								href="${ctx
								}/front/index.do?action=readNode&id=${nodeSubList.id}">
									<c:if test="${nodeSubList.level ==1 }">
								≡&nbsp;${nodeSubList.name }
								</c:if> <c:if test="${nodeSubList.level ==2 }">
								&nbsp;&nbsp;&nbsp;└&nbsp;${nodeSubList.name }
								</c:if> <c:if test="${nodeSubList.level ==3 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${nodeSubList.name }
								</c:if> <c:if test="${nodeSubList.level ==4 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${nodeSubList.name }
								</c:if> <c:if test="${nodeSubList.level ==5 }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└&nbsp;${nodeSubList.name }
								</c:if>
							</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="pattern"></div>
			</div>
			<!--leftSide end-->
			<!--rightSide begin-->
			<div class="rightSide">
				<div class="column_article_list">
					<div class="column_name">
						<h2>查看公告</h2>
					</div>

					<div class="article_title">${notice.title }</div>
					<div class="article_other_info">
						<fmt:formatDate value="${notice.create_time }"
							pattern="yyyy-MM-dd hh:mm:ss" />
					</div>
					<div class="article_content" id="article_content">${notice.content
						}</div>

				</div>
			</div>
			<!--rightSide end-->
			<div class="cb"></div>
		</div>
		<!--mainContent end-->
	</div>
	<!--content end-->


	<div class="footer cb">
		<div class="footerContent">版权所有&copy;华南师范大学</div>
	</div>
</body>
</html>