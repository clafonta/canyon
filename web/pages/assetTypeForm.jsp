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
            <form:textarea path="description" id="description" cssClass="text medium"/>
        </li>    
    	
    
        <li class="buttonBar bottom">
            <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />        
    				<c:if test="${not empty assetType.id}">				
    				    <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AssetType')" value="<fmt:message key="button.delete"/>" />
    				</c:if>
            
        </li>
    </ul>
  </form:form>
  <c:if test="${assetType.id != null}">    
  <h1>Preview</h1>
	<c:forEach var="assetAttribute" items="${assetType.assetAttributeList}">
    <c:url var="assetAttributeURL" value="/editAssetAttribute.html">
      <c:param name="id" value="${assetAttribute.id}"/>
    </c:url>
		<form>
		<ul>
		<li>			 			  <a href="<c:out value="${assetAttributeURL}"/>">Edit</a> | Delete
											<label for="name" class="desc"><c:out value="${assetAttribute.name}"/></label>
											<c:out value="${assetAttribute.description}"/> <br />
		<c:choose>
		
	  					<c:when test="${assetAttribute.type == 'dropdown'}">
							  			<select class="medium">
								  		<c:forEach var="optionValue" items="${assetAttribute.optionValueList}">
								  		<option><c:out value="${optionValue.value}"/></option>
											</c:forEach>
											</select>
							</c:when>
							<c:when test="${assetAttribute.type == 'String' or assetAttribute.type == 'string'}">
											<input type="text" class="text large"/>
							</c:when>
							<c:when test="${assetAttribute.type == 'radio'}">
								  		<c:forEach var="optionValue" items="${assetAttribute.optionValueList}">
											<input type="radio" name="group1" class="medium" value="<c:out value="${optionValue.value}"/>"/> <c:out value="${optionValue.value}"/> <br />
											</c:forEach>
							</c:when>
							<c:when test="${assetAttribute.type == 'checkboxes'}">
								  		<c:forEach var="optionValue" items="${assetAttribute.optionValueList}">
											<input type="checkbox" name="group1" class="medium" value="<c:out value="${optionValue.value}"/>"/><c:out value="${optionValue.value}"/><br />
											</c:forEach>
							</c:when>
							<c:otherwise>
							AssetAttribute TYPE unknown: <c:out value="${assetAttribute.type}"/>
							</c:otherwise>
							</c:choose>
							</li>
							</ul>
  </c:forEach>
	</form>
  <div class="groupformcenter">    
     <h5>Add another attribute</h5>
     <form method="get" action="<c:url value="/editAssetAttribute.html"/>" >
      <input type="hidden" name="assetTypeId" value="<c:out value="${assetType.id}"/>"/>
      <select name="type">
        <option value="string">string</option>
        <option value="float">float</option>
        <option value="date">date</option>
        <option value="boolean">boolean</option>
        <option value="radio">radio</option>
        <option value="dropdown">dropdown</option>
        <option value="checkboxes">checkboxes</option>
      </select>
       <input type="submit" name="add_attribute"  value="Add Attribute" />        
  
      </form>
  </div>    
</c:if>

   
</div>

<script type="text/javascript">
    Form.focusFirstElement($('assetTypeForm'));
</script>

<v:javascript formName="assetType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
