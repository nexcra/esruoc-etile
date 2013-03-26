<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改考试分类</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/validate.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jQuery.validate.plugIn.js"></script>
<script type="text/javascript"
	src="${ctx }/widget/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.validate.message.cn.js"></script>
	

<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript">
$(function() {
	$("#returnButton").click(
			function() {
				overrideSelectedTabForSubPage('listExamClass', '考试分类管理',
						'admin/examClass.do?action=listExamClass');
			});

});

	$(function() {
		$("#updateForm").validate({
			rules : {
				id : {
					required : true
				}

				 ,  name  :  {                required  :  true  }  ,  orderNumber  :  {                required  :  true ,digits:true   }  ,  totalMark  :  {                required  :  true ,digits:true   }  ,  radioSubjectNum  :  {                required  :  true ,digits:true   }  ,  status  :  {                required  :  true  }  ,  radioSubjectMark  :  {                required  :  true ,digits:true   }  ,  checkboxSubjectNumber  :  {                required  :  true ,digits:true   }  ,  checkboxSubjectMark  :  {                required  :  true ,digits:true   }  ,  judgeSubjectNumber  :  {                required  :  true ,digits:true   }  ,  judgeSubjectMark  :  {                required  :  true ,digits:true   }  ,  blankSubjectNumber  :  {                required  :  true ,digits:true   }  ,  blankSubjectMark  :  {                required  :  true ,digits:true   } 
			},
			messages : {

			}
		})
	});
</script>
</head>
<body>
	<form action="${ctx }/admin/examClass.do?action=updateExamClass"
		name="updateForm" id="updateForm" method="post">
		<input type="hidden" name="id" id="id" value="${map.id }" />
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>

			<tr><td  class="fieldName"  width="20%">name:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="name"  id="name"  value="${map.name }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">orderNumber:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="orderNumber"  id="orderNumber"  value="${map.order_number }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">totalMark:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="totalMark"  id="totalMark"  value="${map.total_mark }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">radioSubjectNum:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="radioSubjectNum"  id="radioSubjectNum"  value="${map.radio_subject_num }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">status:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="status"  id="status"  value="${map.status }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">radioSubjectMark:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="radioSubjectMark"  id="radioSubjectMark"  value="${map.radio_subject_mark }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">checkboxSubjectNumber:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="checkboxSubjectNumber"  id="checkboxSubjectNumber"  value="${map.checkbox_subject_number }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">checkboxSubjectMark:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="checkboxSubjectMark"  id="checkboxSubjectMark"  value="${map.checkbox_subject_mark }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">judgeSubjectNumber:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="judgeSubjectNumber"  id="judgeSubjectNumber"  value="${map.judge_subject_number }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">judgeSubjectMark:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="judgeSubjectMark"  id="judgeSubjectMark"  value="${map.judge_subject_mark }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">blankSubjectNumber:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="blankSubjectNumber"  id="blankSubjectNumber"  value="${map.blank_subject_number }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">blankSubjectMark:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="blankSubjectMark"  id="blankSubjectMark"  value="${map.blank_subject_mark }" /><span  class="asterisk">*</span></td> </tr>

			<tr>
				<td class="fieldName"></td>
				<td class="fieldForm"><input type="submit" value="修改"
					class="submitButton" /> <input type="reset" value="重置"
					class="resetButton" /> <input type="button" id="returnButton"
					value="返回" class="submitButton" /></td>
			</tr>
		</table>
	</form>
	
</body>
</html>
