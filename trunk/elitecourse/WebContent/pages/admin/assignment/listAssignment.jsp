<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>作业列表</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
	$(function() {

		//按纽样式
		$(".button1,.button2").hover(function() {
			$(this).addClass("button1Over");
			$(".button1Left", $(this)).show();
			$(".button1Right", $(this)).show();
		}, function() {
			$(this).removeClass("button1Over");
			$(".button1Left", $(this)).hide();
			$(".button1Right", $(this)).hide();
		});

		//全选,反选
		$("#checkAll").click(function() {
			$(".checkList").attr("checked", !!$("#checkAll").attr("checked"));
		});

		//添加作业
		$("#addAssignment")
				.click(
						function() {
							overrideSelectedTabForSubPage('addAssignment',
									'新增作业',
									'admin/assignment.do?action=toAddAssignment');
						});

		//删除作业(顶部按纽栏)
		$("#deleteAssignment")
				.click(
						function() {
							var checkListArr = "";

							if ($(".checkList:checked").length < 1) {
								alert("请选择要删除的选项");
								return false;
							}
							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							//检查通过，进行删除操作
							$(".checkList:checked")
									.each(
											function() {
												checkListArr = checkListArr == "" ? checkListArr
														+ $(this).val()
														: checkListArr + ","
																+ $(this).val();
											});

							$
									.ajax({
										type : "POST",
										url : "assignment.do?action=deleteAssignment&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listAssignment',
													'作业管理',
													'admin/assignment.do?action=listAssignment');
										}
									});
						});

		//修改作业(顶部按纽栏)
		$("#updateAssignment")
				.click(
						function() {
							var checkList = $(".checkList:checked"), checkListLength = checkList.length, checkListValue = checkList
									.val();
							if (checkListLength < 1) {
								alert("请选择要修改的选项");
								return false;
							} else if (checkListLength > 1) {
								alert("对不起，只能选择一项进行修改");
								return false;
							}
							overrideSelectedTabForSubPage(
									'updateAssignment',
									'修改作业',
									'admin/assignment.do?action=toUpdateAssignment&id='
											+ checkListValue);

						});

		//搜索作业
		$("#toSearch")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "assignment.do?action=searchAssignment";
							
							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})
						
		//导出作业
		$("#toExportAssignmentList")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "assignment.do?action=exportAssignmentList";
							
							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})
						
		//获取搜索条件

		function getFormActionCriteria(){
			var formAction = "";
			formAction+= formAction  +=  "&userId="+$("#userId").val();
  formAction  +=  "&userName="+$("#userName").val();
  formAction  +=  "&assignmentClassId="+$("#assignmentClassId").val();
  formAction  +=  "&submitTime="+$("#submitTime").val();
  formAction  +=  "&classesId="+$("#classesId").val();
  formAction  +=  "&path="+$("#path").val();
  formAction  +=  "&grade="+$("#grade").val();
  formAction  +=  "&reContent="+$("#reContent").val();
  formAction  +=  "&reTime="+$("#reTime").val();
 
			return formAction;
		}
		
		//重置
		$("#reset")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'listAssignment',
									'作业管理',
									'admin/assignment.do?action=listAssignment');
						})

		//展开搜索栏
		$("#expandSearchTable").click(function() {
			$("#searchTable").show();
			$(this).hide();
			$("#foldSearchTable").show();
		});

		//收缩搜索栏
		$("#foldSearchTable").click(function() {
			$("#searchTable").hide();
			$(this).hide();
			$("#expandSearchTable").show();

		});

		//更新作业(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateAssignment',
							'修改作业',
							'admin/assignment.do?action=toUpdateAssignment&id='
									+ $(this).attr("value"));
				});

		//删除作业(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "assignment.do?action=deleteAssignment&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listAssignment',
													'作业管理',
													'admin/assignment.do?action=listAssignment');
										}
									});
						});

		//查看作业
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewAssignment',
							'查看作业',
							'admin/assignment.do?action=viewAssignment&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<div class="button1" id="addAssignment">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateAssignment">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="deleteAssignment">
			<div class="button1Left"></div>
			删除
			<div class="button1Right"></div>
		</div>

		<div class="button2" id="expandSearchTable" style="display: none;">
			<div class="button1Left"></div>
			展开搜索
			<div class="button1Right"></div>
		</div>

	</div>
	<form
		action="${ctx }/admin/assignment.do?action=searchAssignment"
		name="queryForm" id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr><td  class="fieldName"  width="10%">userId:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="userId"  id="userId"  value="${bean.userId  }"  /></td><td  class="fieldName"  width="10%">userName:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="userName"  id="userName"  value="${bean.userName  }"  /></td></tr><tr><td  class="fieldName"  width="10%">assignmentClassId:</td> <td  class="fieldForm"  width="40%"><select name="assignmentClassId" id="assignmentClassId"><option value="all" selected="selected">全部</option><c:forEach var="assignmentClassList" items="${assignmentClassList }"><option <c:if test="${bean.assignmentClassId ==assignmentClassList.id  }"> selected="selected"</c:if>value="${assignmentClassList.id }">${assignmentClassList.name }</option></c:forEach></select> </td><td  class="fieldName"  width="10%">submitTime:</td> <td  class="fieldForm"  width="40%"> <input  name="submitTime"  id="submitTime"  type="text"  class="inputText"  value="${bean.submitTime }"  onclick="WdatePicker({el:'submitTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  /> <img  onclick="WdatePicker({el:'submitTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  src="../widget/My97DatePicker/skin/datePicker.gif"  width="16"  height="22"  align="absmiddle"> </td></tr><tr><td  class="fieldName"  width="10%">classesId:</td> <td  class="fieldForm"  width="40%"><select name="classesId" id="classesId"><option value="all" selected="selected">全部</option><c:forEach var="classesList" items="${classesList }"><option <c:if test="${bean.classesId ==classesList.id  }"> selected="selected"</c:if>value="${classesList.id }">${classesList.name }</option></c:forEach></select> </td><td  class="fieldName"  width="10%">path:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="path"  id="path"  value="${bean.path  }"  /></td></tr><tr><td  class="fieldName"  width="10%">grade:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="grade"  id="grade"  value="${bean.grade  }"  /></td><td  class="fieldName"  width="10%">reContent:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="reContent"  id="reContent"  value="${bean.reContent  }"  /></td></tr><tr><td  class="fieldName"  width="10%">reTime:</td> <td  class="fieldForm"  width="40%"> <input  name="reTime"  id="reTime"  type="text"  class="inputText"  value="${bean.reTime }"  onclick="WdatePicker({el:'reTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  /> <img  onclick="WdatePicker({el:'reTime',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  src="../widget/My97DatePicker/skin/datePicker.gif"  width="16"  height="22"  align="absmiddle"> </td><td class="fieldName" width="10%"> </td><td class="fieldForm" width="40%"> </td></tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" name="toSearch" class="submitButton" /> <input type="button"
					value="导出" id="toExportAssignmentList" name="toExportAssignmentList" class="submitButton" /> <input
					type="reset" id="reset" name="reset" value="重置"
					class="submitButton" /> <input type="reset" id="foldSearchTable"
					value="隐藏搜索" class="submitButton" /></td>
				</td>
			</tr>
		</table>
	</form>
	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th> <th>user_id</th><th>user_name</th><th>assignment_class_id</th><th>submit_time</th><th>classes_id</th><th>description</th><th>content</th><th>path</th><th>grade</th><th>re_content</th><th>re_time</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:if test="${empty page.pageDatas }">
			<tr>
				<td colspan="13">对不起，暂时没有数据</td>
			</tr>

		</c:if>
		<c:forEach items="${page.pageDatas }" var="list" varStatus="vs">
			<tr <c:if test="${vs.index %2==1 }">
					class="a1"
				</c:if>>
				<td><input type="checkbox" class="checkList" name="checkList"
					value="${list.id }" /></td> <td>${list.user_id }</td><td>${list.user_name }</td><td>${list.assignment_class_id }</td><td><fmt:formatDate value="${list.submit_time }"pattern="yyyy-MM-dd HH:mm:ss" /> </td><td>${list.classes_id }</td><td>${list.description }</td><td>${list.content }</td><td>${list.path }</td><td>${list.grade }</td><td>${list.re_content }</td><td><fmt:formatDate value="${list.re_time }"pattern="yyyy-MM-dd HH:mm:ss" /> </td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxgz:page
			url="${ctx }/admin/assignment.do?action=listAssignment"
			page="${page }" />
	</div>
</body>
</html>
