
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%
	if (StringUtils.isEmpty((String)session.getAttribute("adminName"))) {
		response.sendRedirect("login.do?action=toLogin");
	}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tkxwz" uri="http://www.tkxwz.com/tkxwz"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="title" value="&#x4E2D;&#x56FD;&#x7535;&#x4FE1;" />