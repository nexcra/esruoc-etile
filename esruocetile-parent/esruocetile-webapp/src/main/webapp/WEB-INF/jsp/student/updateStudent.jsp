<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
<script type="text/javascript">
	$(function() {
		$("#updateForm").validate({
			rules : {
				studentNo : {
					required : true
				},
				name : {
					required : true
				},
				college:{
					required:true
				},
				grade:{
					required:true
				},
				gender : {
					required : true
				},
				nationality : {
					required : true
				},
				notionalityCode : {
					required : true
				},
				dateOfBirth : {
					required : true,
					date : true
				},
				idNo : {
					required : true
				},
				major : {
					required : true
				},
				executiveClaas : {
					required : true
				}

			}
		});

		$("#nationalityValue").click(function() {
			$("#nationalityValue").remove();
			$("#nationality").show();
		});
	});
</script>
</head>
<body>
	<form action="${ctx }/student.do?action=updateStudent"
		name="updateForm" id="updateForm" method="post">
		<input type="hidden" name="id" id="id" value="${map.id }" />
		<table width="100%" border="1" class="formTable">
			<tr>
				<td class="fieldName" width="20%">学号:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="studentNo" id="studentNo" value="${map.student_no }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">姓名:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="name" id="name" value="${map.name }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">学院:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="college" id="college" value="${map.name }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">年级:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="grade" id="grade" value="${map.name }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">性别:</td>
				<td class="fieldForm" width="80%"><select name="gender"
					id="gender">
						<option value="男">男</option>
						<option value="女">女</option>

				</select><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">民族:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="nationality" id="nationalityValue"
					value="${map.nationality }" /> <select name="nationality"
					id="nationality" style="display: none">
						<option value="汉族">汉族</option>
						<option value="蒙古族">蒙古族</option>
						<option value="回族">回族</option>
						<option value="藏族">藏族</option>
						<option value="维吾尔族">维吾尔族</option>
						<option value="维吾尔族">维吾尔族</option>
						<option value="苗族">苗族</option>
						<option value="彝族">彝族</option>
						<option value="壮族">壮族</option>
						<option value="布依族">布依族</option>
						<option value="朝鲜族">朝鲜族</option>
						<option value="满族">满族</option>
						<option value="侗族">侗族</option>
						<option value="瑶族">瑶族</option>
						<option value="白族">白族</option>
						<option value="土家族">土家族</option>
						<option value="哈尼族">哈尼族</option>
						<option value="哈萨克族">哈萨克族</option>
						<option value="傣族">傣族</option>
						<option value="黎族">黎族</option>
						<option value="傈僳族">傈僳族</option>
						<option value="佤族">佤族</option>
						<option value="畲族">畲族</option>
						<option value="高山族">高山族</option>
						<option value="拉祜族">拉祜族</option>
						<option value="水族">水族</option>
						<option value="东乡族">东乡族</option>
						<option value="纳西族">纳西族</option>
						<option value="景颇族">景颇族</option>
						<option value="柯尔克孜族">柯尔克孜族</option>
						<option value="土族">土族</option>
						<option value="达斡尔族">达斡尔族</option>
						<option value="仫佬族">仫佬族</option>
						<option value="羌族">羌族</option>
						<option value="布朗族">布朗族</option>
						<option value="撒拉族">撒拉族</option>
						<option value="毛南族">毛南族</option>
						<option value="仡佬族">仡佬族</option>
						<option value="锡伯族">锡伯族</option>
						<option value="阿昌族">阿昌族</option>
						<option value="普米族">普米族</option>
						<option value="塔吉克族">塔吉克族</option>
						<option value="怒族">怒族</option>
						<option value="乌孜别克族">乌孜别克族</option>
						<option value="俄罗斯族">俄罗斯族</option>
						<option value="鄂温克族">鄂温克族</option>
						<option value="德昂族">德昂族</option>
						<option value="保安族">保安族</option>
						<option value="裕固族">裕固族</option>
						<option value="京族">京族</option>
						<option value="塔塔尔族">塔塔尔族</option>
						<option value="独龙族">独龙族</option>
						<option value="鄂伦春族">鄂伦春族</option>
						<option value="赫哲族">赫哲族</option>
						<option value="门巴族">门巴族</option>
						<option value="珞巴族">珞巴族</option>
						<option value="基诺族">基诺族</option>
						<option value="其他">其他</option>
				</select></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">民族代码:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="notionalityCode" id="notionalityCode"
					value="${map.nationality_code }" /><span class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">出生日期:</td>
				<td class="fieldForm" width="80%"><input name="dateOfBirth"
					id="dateOfBirth"
					value="<fmt:formatDate value="${map.date_of_birth }"
						pattern="yyyy-MM-dd" />"
					type="text" class="inputText"
					onclick="WdatePicker({el:'dateOfBirth',isShowClear:false,dateFmt:'yyyy-MM-dd' })" />
					<img
					onClick="WdatePicker({el:'dateOfBirth',isShowClear:false,dateFmt:'yyyy-MM-dd'})"
					src="widget/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" align="absmiddle"></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">身份证号:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="idNo" id="idNo" value="${map.id_no }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">专业:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="major" id="major" value="${map.major }" /><span
					class="asterisk">*</span></td>
			</tr>
			<tr>
				<td class="fieldName" width="20%">行政班:</td>
				<td class="fieldForm" width="80%"><input type="text"
					name="executiveClaas" id="executiveClaas"
					value="${map.executive_class }" /><span class="asterisk">*</span></td>
			</tr>



			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="提交"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
