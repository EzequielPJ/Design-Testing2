<%--
 * action-2.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script>
	$(function() {
		$("#datepicker-1").datepicker({
			minDate : new Date(),
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});
	});
</script>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="sponsorship">

	<form:hidden path="id" />
	<form:hidden path="parade"/>

	<acme:textbox code="sponsorship.urlBanner" path="urlBanner"	readonly="${view}" />
	<acme:textbox code="sponsorship.linkTPage" path="linkTPage"	readonly="${view}" />
	<form:label path="parade">
		<spring:message code="sponsorship.parade"> </spring:message>
	</form:label>
	:<jstl:out value="${sponsorship.parade.ticker}"></jstl:out>

	<h4>
		<spring:message code="sponsorship.creditCard"></spring:message>
	</h4>
	<acme:textbox code="sponsorship.creditCard.holder" path="creditCard.holder" readonly="${view2}" />
<spring:message code="sponsorship.creditCard.make"></spring:message>
	<form:select path="creditCard.make" disabled="${view2}">
		<form:option value="0" label="---" disabled="${view2}" />
			<form:options items="${makes}" disabled="${view2}" />
	</form:select>

	<acme:textbox code="sponsorship.creditCard.number"
		path="creditCard.number" readonly="${view2}" />
	<acme:textbox code="sponsorship.creditCard.expiration"
		path="creditCard.expiration" readonly="${view2}" id="datepicker-1" />
	<acme:textbox code="sponsorship.creditCard.cvv" path="creditCard.cvv"
		readonly="${view2}" />


	<jstl:if test="${not view}">
		<acme:submit name="save" code="sponsorship.save" />
	</jstl:if>
		<jstl:if test="${reactive}">
		<acme:submit name="save" code="sponsorship.save" />
	</jstl:if>
</form:form>
<input type="submit" onclick="window.history.back()"
	value="<spring:message code="sponsorship.cancel"/>" />