
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%
	if ( session.getAttribute("admin")  ==null) {
		response.sendRedirect("login.do?action=toLogin");
	}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tkxgz" uri="http://www.tkxgz.com/tkxgz"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="title"
	value="&#x534E;&#x5357;&#x5E08;&#x8303;&#x5927;&#x5B66;&#x666E;&#x901A;&#x8BDD;&#x6C34;&#x5E73;&#x6D4B;&#x8BD5;" />