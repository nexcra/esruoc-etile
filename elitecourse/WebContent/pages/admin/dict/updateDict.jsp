<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改字典</title>
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
				overrideSelectedTabForSubPage('listDict', '字典管理',
						'admin/dict.do?action=listDict');
			});

});

	$(function() {
		$("#updateForm").validate({
			rules : {
				id : {
					required : true
				}

				 ,  dictCode  :  {                required  :  true  }  ,  dictName  :  {                required  :  true  }  ,  dictDesc  :  {                required  :  true  }  ,  dictValue  :  {                required  :  true  }  ,  status  :  {                required  :  true  }  ,  isApplicationLevel  :  {                required  :  true  } 
			},
			messages : {

			}
		})
	});
</script>
</head>
<body>
	<form action="${ctx }/admin/dict.do?action=updateDict"
		name="updateForm" id="updateForm" method="post">
		<input type="hidden" name="id" id="id" value="${map.id }" />
		<table width="100%" border="1" class="formTable">
			<c:if test="${error != null }">
				<tr style="color: red; font-weight: bold;">
					<td class="fieldName" width="20%">提示:</td>
					<td class="fieldForm" width="80%">${error }</td>
				</tr>
			</c:if>

			<tr><td  class="fieldName"  width="20%">dictCode:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="dictCode"  id="dictCode"  value="${map.dict_code }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">dictName:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="dictName"  id="dictName"  value="${map.dict_name }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">dictDesc:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="dictDesc"  id="dictDesc"  value="${map.dict_desc }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">dictValue:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="dictValue"  id="dictValue"  value="${map.dict_value }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">status:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="status"  id="status"  value="${map.status }" /><span  class="asterisk">*</span></td> </tr><tr><td  class="fieldName"  width="20%">isApplicationLevel:</td>  <td  class="fieldForm"  width="80%"><input  type="text" name="isApplicationLevel"  id="isApplicationLevel"  value="${map.is_application_level }" /><span  class="asterisk">*</span></td> </tr>

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
