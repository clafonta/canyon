<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <content tag="heading"><fmt:message key="userProfile.heading"/></content>
    <meta name="menu" content="UserMenu"/>
    <script type="text/javascript" src="<c:url value='/scripts/selectbox.js'/>"></script>
</head>

<spring:bind path="user.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/application_error.png"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon"/>
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<div class="groupformcenter">
	<form:form commandName="user" method="post" action="editUser.html" onsubmit="return onFormSubmit(this)" id="userForm">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>
	
	<c:if test="${cookieLogin == 'true'}">
	    <form:hidden path="password"/>
	    <form:hidden path="confirmPassword"/>
	</c:if>
	
	<c:if test="${empty user.username}">
	    <input type="hidden" name="encryptPass" value="true"/>
	</c:if>	
	<ul>
		<li class="info">
	        <c:choose>
	            <c:when test="${param.from == 'list'}">
	                <p class="smallbuffer"><fmt:message key="userProfile.admin.message"/></p>
	            </c:when>
	            <c:otherwise>
	                <p class="smallbuffer"><fmt:message key="userProfile.message"/></p>
	            </c:otherwise>
	        </c:choose>
	    </li>
	    <li class="buttonBar">
	        <%-- So the buttons can be used at the bottom of the form --%>
	        <c:set var="buttons">
	            <input type="submit" class="button" name="save" onclick="bCancel=false" value="<fmt:message key="button.save"/>"/>
	
	        <c:if test="${param.from == 'list' and param.method != 'Add'}">
	            <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('user')"
	                value="<fmt:message key="button.delete"/>"/>
	        </c:if>
	
	            <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>"/>
	        </c:set>
	        <c:out value="${buttons}" escapeXml="false"/>
	    </li>
	    
	    <li>
	        <canyon:label styleClass="desc" key="user.username"/>
	        <form:errors path="username" cssClass="fieldError"/>
	        <form:input path="username" id="username" cssClass="text large"/>
	    </li>
	    <c:if test="${cookieLogin != 'true'}">
	    <li>
	        <div>
	            <div class="left">
	                <canyon:label styleClass="desc" key="user.password"/>
	                <form:errors path="password" cssClass="fieldError"/>
	                <form:password path="password" id="password" cssClass="text medium" onchange="passwordChanged(this)"/>
	            </div>
	            <div>
	                <canyon:label styleClass="desc" key="user.confirmPassword"/>
	                <form:errors path="confirmPassword" cssClass="fieldError"/>
	                <form:password path="confirmPassword" id="confirmPassword" cssClass="text medium"/>
	            </div>
	        </div>
	    </li>
	    </c:if>
	    <li>
	        <canyon:label styleClass="desc" key="user.passwordHint"/>
	        <form:errors path="passwordHint" cssClass="fieldError"/>
	        <form:input path="passwordHint" id="passwordHint" cssClass="text large"/>
	    </li>
	    <li>
	        <div class="left">
	            <canyon:label styleClass="desc" key="user.firstName"/>
	            <form:errors path="firstName" cssClass="fieldError"/>
	            <form:input path="firstName" id="firstName" cssClass="text medium" maxlength="50"/>
	        </div>
	        <div>
	            <canyon:label styleClass="desc" key="user.lastName"/>
	            <form:errors path="lastName" cssClass="fieldError"/>
	            <form:input path="lastName" id="lastName" cssClass="text medium" maxlength="50"/>
	        </div>
	    </li>
	    <li>	        
		<canyon:label styleClass="desc" key="user.email"/>
		<form:errors path="email" cssClass="fieldError"/>
		<form:input path="email" id="email" cssClass="text large"/>	        
	    </li>	
	     <li>			    	             
		<canyon:label styleClass="desc" key="user.phoneNumber"/>
		<form:errors path="phoneNumber" cssClass="fieldError"/>
		<form:input path="phoneNumber" id="phoneNumber" cssClass="text medium"/>
	    </li>	
	<c:choose>
	    <c:when test="${param.from == 'list' or param.method == 'Add'}">
	    <li>
	        <fieldset>
	            <legend><fmt:message key="userProfile.accountSettings"/></legend>
	            <form:checkbox path="enabled" id="enabled"/>
	            <label for="enabled" class="choice"><fmt:message key="user.enabled"/></label>
	
	            <form:checkbox path="accountExpired" id="accountExpired"/>
	            <label for="accountExpired" class="choice"><fmt:message key="user.accountExpired"/></label>
	
	            <form:checkbox path="accountLocked" id="accountLocked"/>
	            <label for="accountLocked" class="choice"><fmt:message key="user.accountLocked"/></label>
	
	            <form:checkbox path="credentialsExpired" id="credentialsExpired"/>
	            <label for="credentialsExpired" class="choice"><fmt:message key="user.credentialsExpired"/></label>
	        </fieldset>
	    </li>
	    <li>
	        <fieldset class="pickList">
	            <legend><fmt:message key="userProfile.assignRoles"/></legend>
	            <table class="pickList">
	                <tr>
	                    <th class="pickLabel">
	                        <canyon:label key="user.availableRoles" colon="false" styleClass="required"/>
	                    </th>
	                    <td></td>
	                    <th class="pickLabel">
	                        <canyon:label key="user.roles" colon="false" styleClass="required"/>
	                    </th>
	                </tr>
	                <c:set var="leftList" value="${availableRoles}" scope="request"/>
	                <c:set var="rightList" value="${user.roleList}" scope="request"/>
	                <c:import url="/common/pickList.jsp">
	                    <c:param name="listCount" value="1"/>
	                    <c:param name="leftId" value="availableRoles"/>
	                    <c:param name="rightId" value="userRoles"/>
	                </c:import>
	            </table>
	        </fieldset>
	    </li>
	    </c:when>
	    <c:when test="${not empty user.username}">
	    <li>
	        <strong><canyon:label key="user.roles"/></strong>
	        <c:forEach var="role" items="${user.roleList}" varStatus="status">
	            <c:out value="${role.label}"/><c:if test="${!status.last}">,</c:if>
	            <input type="hidden" name="userRoles" value="<c:out value="${role.label}"/>"/>
	        </c:forEach>
	        <form:hidden path="enabled"/>
	        <form:hidden path="accountExpired"/>
	        <form:hidden path="accountLocked"/>
	        <form:hidden path="credentialsExpired"/>
	    </li>
	    </c:when>
	</c:choose>
	    <li class="buttonBar bottom">
	        <c:out value="${buttons}" escapeXml="false"/>
	    </li>
	</ul>
	</form:form>
</div>
<script type="text/javascript">
    Form.focusFirstElement($('userForm'));
    highlightFormElements();

    function passwordChanged(passwordField) {
        var origPassword = "<c:out value="${user.password}"/>";
        if (passwordField.value != origPassword) {
            createFormElement("input", "hidden",
                              "encryptPass", "encryptPass",
                              "true", passwordField.form);
        }
    }

<!-- This is here so we can exclude the selectAll call when roles is hidden -->
function onFormSubmit(theForm) {
<c:if test="${param.from == 'list'}">
    selectAll('userRoles');
</c:if>
    return validateUser(theForm);
}
</script>

<v:javascript formName="user" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

