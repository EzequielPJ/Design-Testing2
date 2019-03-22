<%--
 * action-2.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="linkRecord">

<form:hidden path="id"/>
	
	<acme:textbox code="linkRecord.title" path="title" readonly="${view}"/>
	<acme:textarea code="linkRecord.description" path="description" readonly="${view}"/>
	<form:select path="brotherhood" disabled="${view}">
		<form:option value="0" label="---" />
			<form:options items="${brotherhoodList}" itemValue="id" itemLabel="name" />
		</form:select>
	<jstl:if test="${not view}">
	<br>
	<acme:submit name="save" code="linkRecord.save"/>
	</jstl:if>
</form:form>
<input type="submit" onclick="window.history.back()" value="<spring:message code="linkRecord.cancel"/>"/>