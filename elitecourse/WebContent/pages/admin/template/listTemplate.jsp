<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>模板列表</title>
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

		//添加模板
		$("#addTemplate")
				.click(
						function() {
							overrideSelectedTabForSubPage('addTemplate',
									'新增模板',
									'admin/template.do?action=toAddTemplate');
						});

		//删除模板(顶部按纽栏)
		$("#deleteTemplate")
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
										url : "template.do?action=deleteTemplate&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + checkListArr,
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listTemplate',
													'模板管理',
													'admin/template.do?action=listTemplate');
										}
									});
						});

		//修改模板(顶部按纽栏)
		$("#updateTemplate")
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
									'updateTemplate',
									'修改模板',
									'admin/template.do?action=toUpdateTemplate&id='
											+ checkListValue);

						});

		//搜索模板
		$("#toSearch")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "template.do?action=searchTemplate";
							
							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})
						
		//导出模板
		$("#toExportTemplateList")
				.click(
						function() {
							var formToExport = document
									.getElementById("queryForm");

							var formAction = "template.do?action=exportTemplateList";
							
							formAction += getFormActionCriteria();

							formToExport.action = formAction;

							formToExport.submit();
							$("#searchTable").show();
						})
						
		//获取搜索条件

		function getFormActionCriteria(){
			var formAction = "";
			formAction+= formAction  +=  "&templateName="+$("#templateName").val();
  formAction  +=  "&fileName="+$("#fileName").val();
  formAction  +=  "&templateCode="+$("#templateCode").val();
  formAction  +=  "&templateContent="+$("#templateContent").val();
 
			return formAction;
		}
		
		//重置
		$("#reset")
				.click(
						function() {
							overrideSelectedTabForSubPage(
									'listTemplate',
									'模板管理',
									'admin/template.do?action=listTemplate');
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

		//更新模板(表格列表操作)
		$(".update").click(
				function() {
					overrideSelectedTabForSubPage('updateTemplate',
							'修改模板',
							'admin/template.do?action=toUpdateTemplate&id='
									+ $(this).attr("value"));
				});

		//删除模板(表格列表操作)
		$(".delete")
				.click(
						function() {

							if (!confirm("您确定要删除吗?")) {
								return false;
							}

							$
									.ajax({
										type : "POST",
										url : "template.do?action=deleteTemplate&randomNum="
												+ new Date().getTime()
												+ Math.random(),
										cache : false,
										data : "id=" + $(this).attr("value"),
										dataType : "text",
										success : function(data) {
											alert(data);
											overrideSelectedTabForSubPage(
													'listTemplate',
													'模板管理',
													'admin/template.do?action=listTemplate');
										}
									});
						});

		//查看模板
		$(".view").click(
				function() {
					overrideSelectedTabForSubPage('viewTemplate',
							'查看模板',
							'admin/template.do?action=viewTemplate&id='
									+ $(this).attr("value"));
				});

	});
</script>
</head>
<body>
	<div class="functionList">

		<div class="button1" id="addTemplate">
			<div class="button1Left"></div>
			新增
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="updateTemplate">
			<div class="button1Left"></div>
			修改
			<div class="button1Right"></div>
		</div>

		<div class="button1" id="deleteTemplate">
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
		action="${ctx }/admin/template.do?action=searchTemplate"
		name="queryForm" id="queryForm" method="post">
		<table width="100%" border="1" class="formTable" id="searchTable">
			<tr><td  class="fieldName"  width="10%">templateName:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="templateName"  id="templateName"  value="${bean.templateName  }"  /></td><td  class="fieldName"  width="10%">fileName:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="fileName"  id="fileName"  value="${bean.fileName  }"  /></td></tr><tr><td  class="fieldName"  width="10%">templateCode:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="templateCode"  id="templateCode"  value="${bean.templateCode  }"  /></td><td  class="fieldName"  width="10%">templateContent:</td> <td  class="fieldForm"  width="40%"><input  type="text"         name="templateContent"  id="templateContent"  value="${bean.templateContent  }"  /></td></tr>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="搜索"
					id="toSearch" name="toSearch" class="submitButton" /> <input type="button"
					value="导出" id="toExportTemplateList" name="toExportTemplateList" class="submitButton" /> <input
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
				<th><input type="checkbox" id="checkAll" /></th> <th>template_name</th><th>description</th><th>file_name</th><th>template_code</th><th>template_content</th>
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
					value="${list.id }" /></td> <td>${list.template_name }</td><td>${list.description }</td><td>${list.file_name }</td><td>${list.template_code }</td><td>${list.template_content }</td>
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
			url="${ctx }/admin/template.do?action=listTemplate"
			page="${page }" />
	</div>
</body>
</html>
