<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }>>学生列表</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>

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

		//添加学生
		$("#newStudent").click(
				function() {
					overrideSelectedTab('newStudent', '新增学生',
							'student.do?action=toAddStudent');
				});

		//删除学生
		$("#deleteStudent")
				.click(
						function() {
							var checkListArr = "";

							if ($(".checkList:checked").length < 1) {
								alert("请选择要删除的选项");
								return false;
							}
							if (!confirm("您真的要删除吗?")) {
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
										url : "student.do?action=deleteStudent&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTab('listStudent',
													'学生管理',
													'student.do?action=listStudent');
										}
									});
						});

		//修改学生
		$("#updateStudent")
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
							overrideSelectedTab('updateStudent', '修改学生',
									'student.do?action=toUpdateStudent&id='
											+ checkListValue);

						});

		//导入学生
		$("#importStudent").click(
				function() {
					overrideSelectedTab('importStudent', '导入学生数据',
							'data.do?action=toImportStudentData');
				});

		//展开搜索栏
		$("#expand").click(function() {
			$("#searchTable").show();
		});
		
		//收缩搜索栏
		$("#fold").click(function() {
			$("#searchTable").hide();
		});

		//更新
		$(".update").click(
				function() {
					overrideSelectedTab('updateStudent', '修改文章',
							'student.do?action=toUpdateStudent&id='
									+ $(this).attr("value"));
				});

		//删除
		$(".delete").click(
				function() {

					if (!confirm("您真的要删除吗?")) {
						return false;
					}

					$.ajax({
						type : "POST",
						url : "student.do?action=deleteStudent&randomNum="
								+ new Date().getTime() + Math.random(),
						cache : false,
						data : "id=" + $(this).attr("value"),
						dataType : "text",
						success : function(data) {
							alert(data);
							overrideSelectedTab('listStudent', '学生管理',
									'student.do?action=listStudent');
						}
					});
				});
		//查看
		$(".view").click(
				function() {
					overrideSelectedTab('viewStudent', '查看学生',
							'student.do?action=viewStudent&id='
									+ $(this).attr("value"));
				});

	});

	/**
	 *  调用父窗口的overrideSelectedTab方法打开
	 * @param tabid tab的ID
	 * @param text 要显示的文本
	 * @param url 打开的url地址
	 */
	function overrideSelectedTab(tabid, text, url) {
		window.parent.overrideSelectedTab(tabid, text, url);
	}
</script>
</head>
<body>

	<div class="functionList">

		<div class="button1" id="newStudent">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateStudent">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>
		<div class="button1" id="importStudent">
			<div class="button1Left"></div>
			导入
			<div class="button1Right"></div>
		</div>
		<div class="button1" id="deleteStudent">
			<div class="button1Left"></div>
			删除
			<div class="button1Right"></div>
		</div>

		<div class="button2" id="expand">
			<div class="button1Left"></div>
			展开搜索栏
			<div class="button1Right"></div>
		</div>


	</div>
	<form action="${ctx }/student.do?action=searchStudent" name="queryForm"
		id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr>
				<td class="fieldName">学号:</td>
				<td class="fieldForm"><input type="text" name="studentNo"
					id="studentNo" value="${bean.studentNo }" /></td>
				<td class="fieldName">姓名:</td>
				<td class="fieldForm"><input type="text" name="name" id="name"
					value="${bean.name }" /></td>
			</tr>
			<tr>
				<td class="fieldName">学院:</td>
				<td class="fieldForm"><input type="text" name="college"
					id="college" value="${bean.college }" /></td>
				<td class="fieldName">年级:</td>
				<td class="fieldForm"><input type="text" name="grade"
					id="grade" value="${bean.grade }" /></td>
			</tr>

			<tr>
				<td class="fieldName">性别:</td>
				<td class="fieldForm"><select name="gender" id="gender">
						<option>全部</option>
						<option
							<c:if test="${bean.gender =='男' }">
						selected = "selected"
						</c:if>>男</option>
						<option
							<c:if test="${bean.gender =='女' }">
						selected = "selected"
						</c:if>>女</option>
				</select></td>
				<td class="fieldName">身份证号:</td>
				<td class="fieldForm"><input type="text" name="idNo" id="idNo"
					value="${bean.idNo }" /></td>
			</tr>


			<tr>
				<td class="fieldName">专业:</td>
				<td class="fieldForm"><input type="text" name="major"
					id="major" value="${bean.major }" /></td>
				<td class="fieldName">行政班:</td>
				<td class="fieldForm"><input type="text" name="executiveClaas"
					id="executiveClaas" value="${bean.executiveClaas }" /></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /> <input type="reset" id="fold" value="隐藏"
					class="resetButton" /></td>
				</td>
			</tr>
		</table>
	</form>
	<table width="100%" id="mytab" border="1" class="t1">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>学号</th>
				<th>姓名</th>
				<th>学院</th>
				<th>年级</th>
				<th>性别</th>
				<!-- <th>民族</th>
				<th>民族代码</th> -->
				<th>出生日期</th>
				<th>身份证号</th>
				<th>专业</th>
				<th>行政班</th>
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
					value="${list.id }" /></td>
				<td>${list.student_no }</td>
				<td>${list.name }</td>
				<td>${list.college }</td>
				<td>${list.grade }</td>
				<td>${list.gender }</td>
				<!-- <td>${list.nationality_code }</td>
				<td>${list.nationality }</td> -->
				<td><fmt:formatDate value="${list.date_of_birth }"
						pattern="yyyy-MM-dd" /></td>
				<td>${list.id_no }</td>
				<td>${list.major }</td>
				<td>${list.executive_class }</td>
				<td><span class="update operationButton" value="${list.id }">修改</span>
					<span class="operationButton">|</span> <span
					class="delete operationButton" value="${list.id }">删除</span><span
					class="operationButton">|</span> <span class="view operationButton"
					value="${list.id }">查看</span></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pageBar">
		<tkxwz:page url="${ctx }/student.do?action=listStudent"
			page="${page }" />
	</div>
</body>
</html>
