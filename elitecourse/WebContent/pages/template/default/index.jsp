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

	$(function() {
		//加载验证码
		$("#checkCodeImg").attr("src", 'checkCode?now=' + new Date())
		//单击刷新验证码
		.click(function() {
			$(this).attr("src", 'checkCode?now=' + new Date());
		});
	})
</script>
<link href="${ctx }/pages/template/default/css/reset.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/pages/template/default/css/course.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="top"></div>
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
	<!--content begin-->
	<div class="content">

		<div class="mainContent">
			<div class="dateDisplay">
				<span id="time"></span>&nbsp;&nbsp; |&nbsp; &nbsp; <!-- 您所在的位置：首页 -->
			</div>
			<div class="leftSide">
				<div class="leftSideContnt">
					<div class="columnBtn">
						<a href="#"><img
							src="${ctx }/pages/template/default/images/bookIcon.png"
							width="203" height="58" /></a>
					</div>
					<div class="columnBtn">
						<a href="#"><img
							src="${ctx }/pages/template/default/images/courseWare.png" /></a>
					</div>
					<div>
						<img src="${ctx }/pages/template/default/images/passLine.gif" />
					</div>
					
						<div class="login">
						<c:if test="${sessionScope.student !=null}">

	欢迎【${sessionScope.student.name}】同学，您已登录！
						<div>
							<a
								href="${ctx }/front/index.do?action=toUpdatePersonalPassword&id=${sessionScope.student.id }">
								【修改密码】</a>
						</div>
						<div>
							<a
								href="${ctx }/front/index.do?action=toPersonalDetail&id=${sessionScope.student.id }">
								【个人详细信息】</a>
						</div>

						<div>
							<a href="${ctx }/login.do?action=logoutForStudent"> 【安全退出登录】</a>
						</div>

					</c:if>
						<c:if test="${sessionScope.student ==null}">
							<h3>用户登录</h3>
							<div class="inputWrap">
								<form action="${ctx }/login.do?action=studentLogin"
									name="loginForm" method="post">
									用户名：<input type="text" id="name" name="name" size="10"/><br />
									密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password"
										name="password" size="10"/><br />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<!-- 	<input type="text" id="checkCode" name="checkCode" /><img
								src="checkCodeImg" id="checkCodeImg" class="checkCodeImg"
								title="看不清的话请单击刷新（不区分大小写）" /><br />-->
									<input type="submit" class="loginButton" value="">
								</form>
							</div>
						</c:if>
					</div>



					<div>
						<img src="${ctx }/pages/template/default/images/passLine.gif" />
					</div>
					<div class="link">
						<div>
							<img src="${ctx }/pages/template/default/images/link.png"
								width="205" height="31" />
						</div>
						<ul class="linkList">

							<c:forEach var="friendSiteList" items="${friendSiteList }">
								<li><a href="${friendSiteList.link }" target="_blank">${friendSiteList.name
										}</a></li>
							</c:forEach>

						</ul>
					</div>
				</div>
			</div>
			<!--leftSide end-->
			<!--rightSide begin-->
			<div class="rightSide">
				<div class="slider">
					<img src="${ctx }/pages/template/default/images/pic.jpg" />
				</div>
				<div class="intro">
					<div class="introTittle">
						<div class="introTittleText">课程简介</div>
					</div>
					<div class="introContent">${introduction.intro_content }</div>
				</div>
				<!--intro end-->
				<!--newsWrap begin-->
				<div class="newsWrap">

					<c:forEach var="columnList" items="${columnList }">
						<c:if test="${ columnList.level == 1}">
							<div class="newsTop">
								<h2 class="newsName">${columnList.name }</h2>
								<span class="more"><a
									href="${ctx }/front/index.do?action=listArticleByColumnId&columnId=${columnList.id}">更多</a>
									》</span>
							</div>
							<div class="newsContent">
								<div class="newsPic">
									<div class="newsPicPlace">
										<img src="${ctx }/pages/template/default/images/newsPic.gif" />
									</div>
									<h3 class="newPicTitle">课程动态课程动态</h3>
								</div>
								<ul class="newsList">
									<c:forEach var="articleList" items="${articleList }">
										<c:if test="${articleList.columnId == columnList.id }">
											<li><a
												href="${ctx }/front/index.do?action=readArticleById&id=${articleList.id}">

													<c:set value="${articleList.title }" var="c" /> <c:choose>
														<c:when test="${fn:length(c) > 24}">
															<c:out value="${fn:substring(c, 0, 24)}...." />
														</c:when>
														<c:otherwise>
															<c:out value="${c}" />
														</c:otherwise>
													</c:choose>

											</a></li>
										</c:if>
									</c:forEach>
								</ul>
								<div class="cb"></div>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<!--newsWrap end-->

				<!--notice begin-->
				<div class="notice">
					<div class="noticeTop">课程公告</div>
					<div class="noticeCotent">
						<c:forEach var="noticeList" items="${noticeList }">
							<a
								href="${ctx }/front/index.do?action=readNoticeById&id=${noticeList.id}">
								${noticeList.content }
								<p />
							</a>
						</c:forEach>
					</div>
				</div>
				<!--notice end-->
			</div>
			<!--rightSide end-->
			<div class="cb"></div>
		</div>
		<!--mainContent end-->
	</div>
	<!--content end-->



	<!--wrap end-->
	<div class="footer cb">
		<div class="footerContent">
			<p style="color: white;">
				<a href="${ctx }">回到首页</a> | <a
					onclick="setHome(this,window.location)" href="#">设为首页 </a> | <a
					href="#"
					onclick="addFavorite(window.location, '华南师范大学普通话培训');return false">加入收藏
				</a> | <a href="mailto:xxx@qq.com">联系我们 </a> | <a
					href="${ctx }/login.do?action=toLogin" target="_blank">管理登录</a>
			</p>
		</div>
	</div>
</body>
</html>