<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="assetTypeDetail.title"/></title>
<content tag="heading"><fmt:message key="assetTypeDetail.heading"/> </content>

<spring:bind path="assetType.*">
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
  <form:form commandName="assetType" method="post" action="editAssetType.html" onsubmit="return validateAssetType(this)" id="assetTypeForm">
    <ul>  
    <form:hidden path="id"/>
   
        <li>
            <canyon:label styleClass="desc" key="assetType.name"/>
            <form:errors path="name" cssClass="fieldError"/>
            <form:input path="name" id="name" cssClass="text large"/>
        </li>
        <li>
            <canyon:label styleClass="desc" key="assetType.description"/>
            <form:errors path="description" cssClass="fieldError"/>
            <form:textarea path="description" id="description" cssClass="text large"/>
        </li>    
    	
    
        <li class="buttonBar bottom">
            <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />        
    				<c:if test="${not empty assetType.id}">				
    				    <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AssetType')" value="<fmt:message key="button.delete"/>" />
    				</c:if>
            
        </li>
    </ul>
  </form:form>
</div>
<script type="text/javascript">
    Form.focusFirstElement($('assetTypeForm'));
</script>

<v:javascript formName="assetType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
