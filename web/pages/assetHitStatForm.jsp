<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="assetHitStatDetail.title"/></title>
<content tag="heading"><fmt:message key="assetHitStatDetail.heading"/></content>

<spring:bind path="assetHitStat.*">
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

<form:form commandName="assetHitStat" method="post" action="editAssetHitStat.html" onsubmit="return validateAssetHitStat(this)" id="assetHitStatForm">
<ul>

    <li>
        <canyon:label styleClass="desc" key="assetHitStat.userIdentifierConnectingToAsset"/>
        <form:errors path="userIdentifierConnectingToAsset" cssClass="fieldError"/>
        <form:input path="userIdentifierConnectingToAsset" id="userIdentifierConnectingToAsset" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="assetHitStat.applicationUsedToConnect"/>
        <form:errors path="applicationUsedToConnect" cssClass="fieldError"/>
        <form:input path="applicationUsedToConnect" id="applicationUsedToConnect" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="assetHitStat.connectingIP"/>
        <form:errors path="connectingIP" cssClass="fieldError"/>
        <form:input path="connectingIP" id="connectingIP" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="assetHitStat.host"/>
        <form:errors path="host" cssClass="fieldError"/>
        <form:input path="host" id="host" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="assetHitStat.lastLoginTimestamp"/>
        <form:errors path="lastLoginTimestamp" cssClass="fieldError"/>
        <form:input path="lastLoginTimestamp" id="lastLoginTimestamp" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="assetHitStat.numberOfLogins"/>
        <form:errors path="numberOfLogins" cssClass="fieldError"/>
        <form:input path="numberOfLogins" id="numberOfLogins" cssClass="text medium"/>
    </li>

    <li>
        <canyon:label styleClass="desc" key="assetHitStat.userIdentifierFromOS"/>
        <form:errors path="userIdentifierFromOS" cssClass="fieldError"/>
        <form:input path="userIdentifierFromOS" id="userIdentifierFromOS" cssClass="text medium"/>
    </li>

<form:hidden path="id"/>

    <li>
        <canyon:label styleClass="desc" key="assetHitStat.assetDetailId"/>
        <form:errors path="assetDetailId" cssClass="fieldError"/>
        <form:input path="assetDetailId" id="assetDetailId" cssClass="text medium"/>
    </li>

    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AssetHitStat')" value="<fmt:message key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </li>
</ul>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('assetHitStatForm'));
</script>

<v:javascript formName="assetHitStat" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
