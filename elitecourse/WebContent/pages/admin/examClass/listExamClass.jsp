<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>考试分类列表</title>
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

		//添加考试分类
		$("#addExamClass")
				.click(
						function() {
							overrideSelectedTabForSubPage('addExamClass',
									'新增考试分类',
									'admin/examClass.do?action=toAddExamClass');
						});

		//删除考试分类(顶部按纽栏)
		$("#deleteExamClass")
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
										url : "examClass.do?action=deleteExamClass&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listExamClass',
													'考试分类管理',
													'admin/examClass.do?action=listExamClass');
										}
									});
						});

		//修改考试分类(顶部按纽栏)
		$("#updateExamClass")
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
									'updateExamClass',
									'修改考试分类',
									'admin/examClass.do?action=toUpdateExamClass&id='
											+ checkListValue);

						});

		//搜索考试分类
		$("#toSearch")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "examClass.do?action=searchExamClass";
							
							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})
						
		//导出考试分类
		$("#toExportExamClassList")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "examClass.do?action=exportExamClassList";
							
							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})
						
		//获取搜索条件

		function getFormActionCriteria(){
			var formAction = "";
			formAction+= formAction  +=  "&name="+$("#name").val();
  formAction  +=  "&totalMark="+$("#totalMark").val();
  formAction  +=  "&radioSubjectNum="+$("#radioSubjectNum").val();
  formAction  +=  "&status="+$("#status").val();
  formAction  +=  "&radioSubjectMark="+$("#radioSubjectMark").val();
  formAction  +=  "&checkboxSubjectNumber="+$("#checkboxSubjectNumber").val();
  formAction  +=  "&checkboxSubjectMark="+$("#checkboxSubjectMark").val();
  formAction  +=  "&judgeSubjectNumber="+$("#judgeSubjectNumber").val();
  formAction  +=  "&judgeSubjectMark="+$("#judgeSubjectMark").val();
  formAction  +=  "&blankSubjectNumber="+$("#blankSubjectNumber").val();
  formAction  +=  "&blankSubjectMark="+$("#blankSubjectMark").val();
 
			return formAction;
		}
		
		//重置
		$("#reset")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'listExamClass',
									'考试分类管理',
									'admin/examClass.do?action=listExamClass');
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

		//更新考试分类(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateExamClass',
							'修改考试分类',
							'admin/examClass.do?action=toUpdateExamClass&id='
									+ $(this).attr("value"));
				});

		//删除考试分类(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "examClass.do?action=deleteExamClass&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listExamClass',
													'考试分类管理',
													'admin/examClass.do?action=listExamClass');
										}
									});
						});

		//查看考试分类
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewExamClass',
							'查看考试分类',
							'admin/examClass.do?action=viewExamClass&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<div class="button1" id="addExamClass">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateExamClass">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="deleteExamClass">
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
		action="${ctx }/admin/examClass.do?action=searchExamClass"
		name="queryForm" id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr><td  class="fieldName"  width="10%">name:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="name"  id="name"  value="${bean.name  }"  /></td><td  class="fieldName"  width="10%">totalMark:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="totalMark"  id="totalMark"  value="${bean.totalMark  }"  /></td></tr><tr><td  class="fieldName"  width="10%">radioSubjectNum:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="radioSubjectNum"  id="radioSubjectNum"  value="${bean.radioSubjectNum  }"  /></td><td  class="fieldName"  width="10%">status:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="status"  id="status"  value="${bean.status  }"  /></td></tr><tr><td  class="fieldName"  width="10%">radioSubjectMark:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="radioSubjectMark"  id="radioSubjectMark"  value="${bean.radioSubjectMark  }"  /></td><td  class="fieldName"  width="10%">checkboxSubjectNumber:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="checkboxSubjectNumber"  id="checkboxSubjectNumber"  value="${bean.checkboxSubjectNumber  }"  /></td></tr><tr><td  class="fieldName"  width="10%">checkboxSubjectMark:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="checkboxSubjectMark"  id="checkboxSubjectMark"  value="${bean.checkboxSubjectMark  }"  /></td><td  class="fieldName"  width="10%">judgeSubjectNumber:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="judgeSubjectNumber"  id="judgeSubjectNumber"  value="${bean.judgeSubjectNumber  }"  /></td></tr><tr><td  class="fieldName"  width="10%">judgeSubjectMark:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="judgeSubjectMark"  id="judgeSubjectMark"  value="${bean.judgeSubjectMark  }"  /></td><td  class="fieldName"  width="10%">blankSubjectNumber:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="blankSubjectNumber"  id="blankSubjectNumber"  value="${bean.blankSubjectNumber  }"  /></td></tr><tr><td  class="fieldName"  width="10%">blankSubjectMark:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="blankSubjectMark"  id="blankSubjectMark"  value="${bean.blankSubjectMark  }"  /></td><td class="fieldName" width="10%"> </td><td class="fieldForm" width="40%"> </td></tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" name="toSearch" class="submitButton" /> <input type="button"
					value="导出" id="toExportExamClassList" name="toExportExamClassList" class="submitButton" /> <input
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
				<th><input type="checkbox" id="checkAll" /></th> <th>name</th><th>order_number</th><th>total_mark</th><th>radio_subject_num</th><th>status</th><th>radio_subject_mark</th><th>checkbox_subject_number</th><th>checkbox_subject_mark</th><th>judge_subject_number</th><th>judge_subject_mark</th><th>blank_subject_number</th><th>blank_subject_mark</th>
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
					value="${list.id }" /></td> <td>${list.name }</td><td>${list.order_number }</td><td>${list.total_mark }</td><td>${list.radio_subject_num }</td><td>${list.status }</td><td>${list.radio_subject_mark }</td><td>${list.checkbox_subject_number }</td><td>${list.checkbox_subject_mark }</td><td>${list.judge_subject_number }</td><td>${list.judge_subject_mark }</td><td>${list.blank_subject_number }</td><td>${list.blank_subject_mark }</td>
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
			url="${ctx }/admin/examClass.do?action=listExamClass"
			page="${page }" />
	</div>
</body>
</html>
