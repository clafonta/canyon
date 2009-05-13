<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="assetAttributeDetail.title"/></title>
<content tag="heading"><fmt:message key="assetAttributeDetail.heading"/> </content>
<div class="largebuffer">
  <c:url var="assetTypeURL" value="/editAssetType.html?from=list&id=1">
    <c:param name="assetTypeId" value="${assetAttribute.assetTypeId}"/>
		<c:param name="view" value="true"/>
  </c:url>
	<a href="<c:out value="${assetTypeURL}" />">Back to <c:out value="${assetAttribute.assetType.name}" /></a>
</div>
<spring:bind path="assetAttribute.*">
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
<div>
  <h3>You're adding an attribute of type:
  <c:choose>
    <c:when test="${assetAttribute.type == 'string'}">
     STRING
    </c:when>
    <c:when test="${assetAttribute.type == 'float'}">
    FLOAT
    </c:when>
    <c:when test="${assetAttribute.type == 'date'}">
    DATE
    </c:when>
    <c:when test="${assetAttribute.type == 'boolean'}">
    BOOLEAN
    </c:when>
    <c:when test="${assetAttribute.type == 'radio'}">
    RADIO
    </c:when>
    <c:when test="${assetAttribute.type == 'checkboxes'}">
    CHECKBOXES
    </c:when>
    <c:when test="${assetAttribute.type == 'dropdown'}">
    DROPDOWN
    </c:when>
    <c:otherwise>
    (Invalid entry)
    </c:otherwise>
  </c:choose>
  </h3>
<div>
  <form:form commandName="assetAttribute" method="post" action="editAssetAttribute.html" onsubmit="return validateAssetAttribute(this)" id="assetAttributeForm">
    <ul>  
    <form:hidden path="id"/>
    <input type="hidden" name="assetTypeId" value="<c:out value="${assetAttribute.assetType.id}" />" />
        <li>
            <canyon:label styleClass="desc" key="assetAttribute.name"/>
            <form:errors path="name" cssClass="fieldError"/>
            <form:input path="name" id="name" cssClass="text large"/>
        </li>
        <li>
            <canyon:label styleClass="desc" key="assetAttribute.description"/>
            <form:errors path="description" cssClass="fieldError"/>
            <form:textarea path="description" id="description" cssClass="text large"/>
        </li>    
    		<li>
            <fieldset>            
                <form:checkbox path="required" id="required"/>
                <label for="required" class="choice"><fmt:message key="assetAttribute.required"/></label>            
            </fieldset>
        </li>
    
        <li class="buttonBar bottom">
            <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />        
    				<c:if test="${not empty assetAttribute.id}">				
    				    <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AssetAttribute')" value="<fmt:message key="button.delete"/>" />
    				</c:if>
            <a href="<c:out value="${assetDetailURL}" />">Cancel</a>
        </li>
    </ul>
  </form:form>
</div>
<script type="text/javascript">
    Form.focusFirstElement($('assetAttributeForm'));
</script>

<v:javascript formName="assetAttribute" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
