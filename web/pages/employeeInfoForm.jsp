<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="employeeInfoDetail.title"/></title>
<content tag="heading"><fmt:message key="employeeInfoDetail.heading"/></content>

<spring:bind path="employeeInfo.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/application_error.png"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="employeeInfo" method="post" action="editEmployeeInfo.html" onsubmit="return validateEmployeeInfo(this)" id="employeeInfoForm">
<ul>

<form:hidden path="employeeUserId"/>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.employeeUserId"/>
        <form:errors path="employeeUserId" cssClass="fieldError"/>
        <form:input path="employeeUserId" id="employeeUserId" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.employeeDept"/>
        <form:errors path="employeeDept" cssClass="fieldError"/>
        <form:input path="employeeDept" id="employeeDept" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.employeeEmail"/>
        <form:errors path="employeeEmail" cssClass="fieldError"/>
        <form:input path="employeeEmail" id="employeeEmail" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.employeeStatus"/>
        <form:errors path="employeeStatus" cssClass="fieldError"/>
        <form:input path="employeeStatus" id="employeeStatus" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.employeeType"/>
        <form:errors path="employeeType" cssClass="fieldError"/>
        <form:input path="employeeType" id="employeeType" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.employeeFirstName"/>
        <form:errors path="employeeFirstName" cssClass="fieldError"/>
        <form:input path="employeeFirstName" id="employeeFirstName" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.employeeLastName"/>
        <form:errors path="employeeLastName" cssClass="fieldError"/>
        <form:input path="employeeLastName" id="employeeLastName" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.employeeMiddleName"/>
        <form:errors path="employeeMiddleName" cssClass="fieldError"/>
        <form:input path="employeeMiddleName" id="employeeMiddleName" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="employeeInfo.managerUserId"/>
        <form:errors path="managerUserId" cssClass="fieldError"/>
        <form:input path="managerUserId" id="managerUserId" cssClass="text medium"/>
    </li>
 

    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('EmployeeInfo')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </li>
</ul>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('employeeInfoForm'));
</script>

<v:javascript formName="employeeInfo" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
