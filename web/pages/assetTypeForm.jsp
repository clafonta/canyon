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
  <c:if test="${assetType.id != null}">    
  <h1>Attributes</h1>
  <display:table name="assetType.assetAttributeList" cellspacing="0" cellpadding="0" requestURI="" id="assetType.assetAttributeList" pagesize="25" class="greytable assetAttributeList" export="false">
		<authz:authorize ifAnyGranted="admin, user">
      <display:column property="name" escapeXml="true" sortable="true" url="/editAssetAttribute.html" paramId="id" paramProperty="id" titleKey="assetAttribute.name" class="medium"/>
		</authz:authorize>   
		<authz:authorize ifAnyGranted="ROLE_ANONYMOUS">
      <display:column property="name" escapeXml="true" sortable="true" titleKey="assetAttribute.name"/>
		</authz:authorize>   
    <display:column property="description" escapeXml="true" sortable="true" titleKey="assetAttribute.description"/> 
    <display:column property="type" escapeXml="true" sortable="true" titleKey="assetAttribute.type"/>    
    <display:setProperty name="paging.banner.item_name" value="assetAttribute"/>
    <display:setProperty name="paging.banner.items_name" value="assetAttributes"/>
  </display:table>

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
