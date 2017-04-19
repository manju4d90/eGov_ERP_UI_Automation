<%--
  ~ eGov suite of products aim to improve the internal efficiency,transparency,
  ~    accountability and the service delivery of the government  organizations.
  ~
  ~     Copyright (C) <2017>  eGovernments Foundation
  ~
  ~     The updated version of eGov suite of products as by eGovernments Foundation
  ~     is available at http://www.egovernments.org
  ~
  ~     This program is free software: you can redistribute it and/or modify
  ~     it under the terms of the GNU General Public License as published by
  ~     the Free Software Foundation, either version 3 of the License, or
  ~     any later version.
  ~
  ~     This program is distributed in the hope that it will be useful,
  ~     but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~     GNU General Public License for more details.
  ~
  ~     You should have received a copy of the GNU General Public License
  ~     along with this program. If not, see http://www.gnu.org/licenses/ or
  ~     http://www.gnu.org/licenses/gpl.html .
  ~
  ~     In addition to the terms of the GPL license to be adhered to in using this
  ~     program, the following additional terms are to be complied with:
  ~
  ~         1) All versions of this program, verbatim or modified must carry this
  ~            Legal Notice.
  ~
  ~         2) Any misrepresentation of the origin of the material is prohibited. It
  ~            is required that all modified versions of this material be marked in
  ~            reasonable ways as different from the original version.
  ~
  ~         3) This license does not grant any rights to any user of the program
  ~            with regards to rights under trademark law for use of the trade names
  ~            or trademarks of eGovernments Foundation.
  ~
  ~   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
  --%>

<%@page import="org.python.modules.jarray"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/cdn.tld" prefix="cdn"%>
<div class="panel-heading custom_form_panel_heading">
	<div class="panel-title">
		<spring:message code="lbl.inspection.appln" />
	</div>
</div>
<div class="form-group">
	<label class="col-sm-3 control-label text-right">Location of
		the plot<span
		class="mandatory"></span></label>
	<div class="col-sm-3 add-margin">
		<form:input class="form-control patternvalidation"
			data-pattern="alphanumericwithspace" maxlength="128" id="locationOfPlot"
			path="docket[0].locationOfPlot" />
		<form:errors path="docket[0].locationOfPlot" required="required"
			cssClass="add-margin error-msg" />
	</div>
</div>
<c:choose>
	<c:when test="${!docketDetail.isEmpty()}">
		<div class="form-group view-content header-color hidden-xs">
			<div class="col-sm-5 text-center">Documents</div>
			<div class="col-sm-3 text-center">Provided</div>
			<div class="col-sm-3 text-center">
				<spring:message code="lbl.remarks" />
			</div>
		</div>
		<c:forEach var="docs" items="${docketDetail}" varStatus="status">
			<div class="form-group">
				<div class="col-sm-5 add-margin check-text text-center">

					<c:out value="${docs.checkListDetail.description}" />


					<form:hidden
						id="docket[0].docketDetail${status.index}checkListDetail.id"
						path="docket[0].docketDetail[${status.index}].checkListDetail.id"
						value="${docs.checkListDetail.id}" />

					<form:hidden
						id="docket[0].docketDetail${status.index}checkListDetail.description"
						path="docket[0].docketDetail[${status.index}].checkListDetail.description"
						value="${docs.checkListDetail.description}" />
				</div>

				<div class="col-sm-3 add-margin">
				 <c:choose>
				<c:when  test="${mode =='editinsp'}">
				<form:radiobutton
						path="docket[0].docketDetail[${status.index}].value" value="true"  />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docket[0].docketDetail[${status.index}].value" value="false"
						/>
					<spring:message code="lbl.no" />
					<form:errors path="docket[0].docketDetail[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:when>
				<c:otherwise>
				<form:radiobutton
						path="docket[0].docketDetail[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docket[0].docketDetail[${status.index}].value" value="false"
						checked="checked" />
						<spring:message code="lbl.no" />
						<form:errors path="docket[0].docketDetail[${status.index}].value"
						cssClass="add-margin error-msg" />
				</c:otherwise>
				</c:choose> 
					<%-- <form:radiobutton
						path="docket[0].docketDetail[${status.index}].value" value="true" />
					<spring:message code="lbl.yes" />
					<form:radiobutton
						path="docket[0].docketDetail[${status.index}].value" value="false"
						checked="checked" />
					<spring:message code="lbl.no" /> --%>
					
				</div>

				<div class="col-sm-3 add-margin text-center">
					<form:textarea class="form-control patternvalidation"
						data-pattern="alphanumericwithspace" maxlength="256"
						id="docket[0].docketDetail${status.index}remarks"
						path="docket[0].docketDetail[${status.index}].remarks"
						 />

					<form:errors path="docket[0].docketDetail[${status.index}].remarks"
						cssClass="add-margin error-msg" />
				</div>
			</div>
		</c:forEach>
	</c:when>
</c:choose>
