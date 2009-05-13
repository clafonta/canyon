<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="optionValueDetail.title"/></title>
<content tag="heading"><fmt:message key="optionValueDetail.heading"/> </content>
<div class="largebuffer">
  <c:url var="assetAttributeURL" value="/editAssetAttribute.html?from=list&id=1">
    <c:param name="assetAttributeId" value="${optionValue.assetAttributeId}"/>
	<c:param name="view" value="true"/>
  </c:url>
	<a href="<c:out value="${assetAttributeURL}" />">Back to <c:out value="${optionValue.assetAttribute.name}" /></a>
</div>
<spring:bind path="optionValue.*">
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
  <form:form commandName="optionValue" method="post" action="editOptionValue.html" onsubmit="return validateOptionValue(this)" id="optionValueForm">
    <ul>  
    <form:hidden path="id"/>
    <input type="hidden" name="assetAttributeId" value="<c:out value="${optionValue.assetAttribute.id}" />" />
        <li>
            <canyon:label styleClass="desc" key="optionValue.value"/>
            <form:errors path="value" cssClass="fieldError"/>
            <form:input path="value" id="name" cssClass="text large"/>
        </li>
        
        <li class="buttonBar bottom">
            <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />        
    				<c:if test="${not empty optionValue.id}">				
    				    <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('OptionValue')" value="<fmt:message key="button.delete"/>" />
    				</c:if>
            <a href="<c:out value="${assetAttributeURL}" />">Cancel</a>
        </li>
    </ul>
  </form:form>
</div>
<script type="text/javascript">
    Form.focusFirstElement($('optionValueForm'));
</script>

<v:javascript formName="optionValue" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
