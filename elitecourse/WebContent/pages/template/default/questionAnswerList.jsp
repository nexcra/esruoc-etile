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
				<a href="${ctx }/front/index.do?action=toAddQuestionAnswer">提交问题</a>

				<c:if test="${sessionScope.student ==null}">

					<a href="${ctx }/login.do?action=toLogin">登陆</a>
				</c:if>
				<c:if test="${sessionScope.student !=null}">
					<a>欢迎【${sessionScope.student.name}】同学，您已登录！</a>
				</c:if>
			</div>
			<!--list2 begin-->
			<!--    <table width="100%" border="1" class="article_list_table">
          	<tr>
             <td colspan="2" class="bgColor tl fb" >登陆</td>
            </tr>
            <tr>
              <td width="100" class="bgColor tr" >用户名：</td>
              <td width="350"><input type="text" id="" width="150"></td>
            </tr>
            <tr>
              <td width="100" class="bgColor tr" >留言：</td>
              <td width="350"><textarea style="width:400px"></textarea></td>
            </tr>
          </table> -->
			<!--list2 end-->

			<!--list1 begin-->
			<table width="670" border="1" class="article_list_table">

				<c:if test="${empty page.pageDatas }">
					<tr>
						<td colspan="13">对不起，暂时没有数据</td>
					</tr>

				</c:if>

				<tr>
					<!-- 	<th width="30"></th> -->
					<th width="300">标题</th>
					<th width="100">提问人</th>
					<th width="130">提问时间</th>
				</tr>
				<c:forEach var="questionAnswerList" items="${page.pageDatas }">
					<tr>
						<!-- 	<td width="30" class="tc"><img
							src="message_Image/ico_help.gif" width="16" height="16" /></td> -->
						<td width="300"><a
							href="${ctx }/front/index.do?action=readQuestionAndwerById&id=${questionAnswerList.id}">${questionAnswerList.title
								}</a></td>
						<td width="100" class="fb">${questionAnswerList.create_user_name
							}</td>
						<td width="100" class="fb">${questionAnswerList.create_time }</td>
					</tr>
				</c:forEach>
				<div class="page">
					<div class="pageBar">
						<tkxgz:page url="${ctx }/front/index.do?action=listVideo"
							page="${page }" />
					</div>
				</div>
				<br />
				<!-- 	<tr>
					<td width="30"></td>
					<td colspan="4" class="tdBtn bgColor"><a href="#">回复</a> <a
						href="#">删除</a> <a href="#">注销</a></td>
				</tr> -->
			</table>
			<!--list1 end-->
		</div>
	</div>
	<!--rightSide end-->
	</div>
</body>
</html>