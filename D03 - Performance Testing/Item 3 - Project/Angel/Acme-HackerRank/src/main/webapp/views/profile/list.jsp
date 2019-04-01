<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<spring:message code="profile.title.1" />
</p>

<display:table name="profiles" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column titleKey="profile.nick">
		<jstl:out value="${row.nick}" />
	</display:column>
	<display:column titleKey="profile.socialNetworkName">
		<jstl:out value="${row.socialNetworkName}" />
	</display:column>
	<display:column titleKey="profile.link">
		<a href="${row.link}"><jstl:out value="${row.link}" /></a>
	</display:column>
	<display:column titleKey="profile.edit">
		<a href="profile/edit.do?id=${row.id}"><spring:message
				code="profile.edit" /></a>
	</display:column>
	<display:column titleKey="profile.delete">
		<a href="profile/delete.do?id=${row.id}"><spring:message
				code="profile.delete" /></a>
	</display:column>

	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />

</display:table>

<a href="profile/create.do"><spring:message code="profile.add" /></a>
