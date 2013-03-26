<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>视频列表</title>
<script type="text/javascript"
	src="${ctx }/pages/template/default/js/jquery-1.7.2.min.js"></script>
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
			<div class="rightSide">
				<div class="column_article_list">
			<!-- 		<div class="column_name">
						<h2>视频列表</h2>
					</div> -->
					<div class="article_list">
						<table width="100%" border="0" class="article_list_table">
							<c:if test="${empty videoList }">
								<tr>
									<td colspan="13">对不起，暂时没有数据</td>
								</tr>

							</c:if>
							<c:forEach var="videoList" items="${page.pageDatas }">
								<tr>
									<td width="27" align="left" height="25">&nbsp; <img
										src="${ctx }/pages/template/default/images/dot2.gif" width="9"
										height="15" /></td>
									<td width="474"><a class="article_list_title"
										title="视频名称：${videoList.name }  &#13;&#10;更新时间：<fmt:formatDate value="${videoList.create_time }"
						pattern="yyyy-MM-dd hh:mm:ss" />"
										href="${ctx }/front/index.do?action=readVideoById&id=${videoList.id}"
										target="_blank">${videoList.name } </a></td>
									<td width="78"></td>
									<td width="79"><fmt:formatDate
											value="${videoList.create_time }" pattern="yyyy-MM-dd" /></td>
								</tr>
							</c:forEach>
						</table>

						<div class="page">
							<div class="pageBar">
								<tkxgz:page
									url="${ctx }/front/index.do?action=listVideo"
									page="${page }" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--rightSide end-->
	</div>
</body>
</html>