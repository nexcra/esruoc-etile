<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/noLoginCommon.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>答疑列表</title>
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

	<!--rightSide begin-->
	<div class="rightSide" style="width: 670px; overflow: hidden">
		<div class="column_article_list">
			<div class="answerList">
				<a href="${ctx }/front/index.do?action=listQuestionAnswer">答疑列表</a>
				<a href="${ctx }/front/index.do?action=addQuestionAnswer">提交问题</a>
				<c:if test="${sessionScope.student ==null}">

					<a href="${ctx }/login.do?action=toLogin">登陆</a>
				</c:if>
				<c:if test="${sessionScope.student !=null}">
			<a>欢迎【${sessionScope.student.name}】同学，您已登录！</a>	
				</c:if>
			</div>
			<table width="100%" border="1" class="article_list_table">
				<tr>
					<td colspan="2" class="bgColor tl fb">问题详细</td>
				</tr>
				<tr>
					<td width="100" class="bgColor tr">标题：</td>
					<td width="350">${questionAnswer.title }</td>
				</tr>
				<tr>
					<td width="100" class="bgColor tr">描述：</td>
					<td width="350">${questionAnswer.content }</td>
				</tr>
				<tr>
					<td width="100" class="bgColor tr">提问人：</td>
					<td width="350">${questionAnswer.create_user_name }</td>
				</tr>
				<tr>
					<td width="100" class="bgColor tr">提问时间：</td>
					<td width="350"><fmt:formatDate
							value="${questionAnswer.create_time }"
							pattern="yyyy-MM-dd hh:mm:ss" /></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td width="100" class="bgColor tr">问题回复：</td>
					<td width="350"><span style="color: blue">${questionAnswer.re_content
							} </span></td>
				</tr>
				<tr>
					<td width="100" class="bgColor tr">回复人：</td>
					<td width="350"><span style="color: blue">${questionAnswer.re_user_name
							} </span></td>
				</tr>
				<tr>
					<td width="100" class="bgColor tr">回复时间：</td>
					<td width="350"><span style="color: blue"><fmt:formatDate
								value="${questionAnswer.re_time }" pattern="yyyy-MM-dd hh:mm:ss" /></span>
					</td>
				</tr>
			</table>
			<!--list2 end-->


		</div>
	</div>
	<!--rightSide end-->
	</div>
</body>
</html>