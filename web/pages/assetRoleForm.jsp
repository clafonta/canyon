<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="assetRoleDetail.title"/></title>
<content tag="heading"><fmt:message key="assetRoleDetail.heading"/> : <font color="black"> <c:out value="${assetRole.assetDetail.assetName}" /></font></content>
<div class="largebuffer">
  <c:url var="assetDetailURL" value="/editAssetDetail.html?from=list&id=1">
    <c:param name="assetDetailId" value="${assetRole.assetDetailId}"/>
		<c:param name="view" value="true"/>
  </c:url>
	<a href="<c:out value="${assetDetailURL}" />">Back to <c:out value="${assetRole.assetDetail.assetName}" /></a>
</div>
<spring:bind path="assetRole.*">
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
<div class="groupformcenter">
  <form:form commandName="assetRole" method="post" action="editAssetRole.html" onsubmit="return validateAssetRole(this)" id="assetRoleForm">
    <ul>  
    <form:hidden path="id"/>
    <input type="hidden" name="assetDetailId" value="<c:out value="${assetRole.assetDetail.id}" />" />
        <li>
            <canyon:label styleClass="desc" key="assetRole.name"/>
            <form:errors path="name" cssClass="fieldError"/>
            <form:input path="name" id="name" cssClass="text large"/>
        </li>
        <li>
            <canyon:label styleClass="desc" key="assetRole.description"/>
            <form:errors path="description" cssClass="fieldError"/>
            <form:textarea path="description" id="description" cssClass="text large"/>
        </li>    
    		<li>
            <fieldset>            
                <form:checkbox path="customerSensitiveData" id="customerSensitiveData"/>
                <label for="customerSensitiveData" class="choice"><fmt:message key="assetRole.customerSensitiveData"/></label>            
            </fieldset>
        </li>
    
        <li class="buttonBar bottom">
            <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />        
    				<c:if test="${not empty assetRole.id}">				
    				    <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AssetRole')" value="<fmt:message key="button.delete"/>" />
    				</c:if>
            <a href="<c:out value="${assetDetailURL}" />">Cancel</a>
        </li>
    </ul>
  </form:form>
</div>
<script type="text/javascript">
    Form.focusFirstElement($('assetRoleForm'));
</script>

<v:javascript formName="assetRole" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
