<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }>>导入学生数据</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript">
	$(function() {
		$("#importForm").validate({
			rules : {
				importExcel : {
					required : true,
					accept : "xls"
				}
			},
			messages : {
				importExcel : {
					required : "请选择文件再导入",
					accept : " 只支持xls文件(excel 2003及其以下版本) "
				}
			}
		});
		$(function() {
			$("#returnButton").click(
					function() {
						overrideSelectedTab('listStudent', '学生信息管理',
								'student.do?action=listStudent');
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
	});
</script>
<title>导入学生数据</title>
</head>
<body>
	<form action="${ctx }/admin/user.do?action=importStudent" name="importForm"
		id="importForm" method="post" enctype="multipart/form-data">
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName">文件:</td>
				<td class="fieldForm"><input type="file" name="importExcel"
					id="importExcel" class="inputText" /></td>
			</tr>

			<tr>
				<td class="fieldName">说明:</td>
				<td class="fieldForm">导入文件为excel,并且此excel必须符合模板中的格式方可导入！</a><a
					href="${ctx }/importExample.xls">导入模板下载(右键，另存为)</a>
				</td>
			</tr>

			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="导入"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /> <input type="button" value="返回"
					class="submitButton" id="returnButton" /></td>
			</tr>
		</table>
	</form>
</body>
</html>