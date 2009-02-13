<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="assetDetailDetail.title"/></title>
<content tag="heading"><fmt:message key="assetDetailDetail.heading"/>
  <c:if test="${not empty assetDetail.assetName}">
  :<span class="focus"><c:out value="${assetDetail.assetName}"/></span>
  </c:if>
</content>
<meta name="menu" content="AssetMenu"/>
<spring:bind path="assetDetail.*">
      <c:if test="${not empty status.errorMessages}">
      
      <div class="error">    
        <ul>
	  <li><strong class="big"></strong>          
          <c:forEach var="error" items="${status.errorMessages}">
              <li class="error"><c:out value="${error}" escapeXml="false"/></li>
          </c:forEach>
        </ul>
      </div>
    </c:if>
</spring:bind>
<c:url var="assetHitStatURL" value="/assetHitStatList.html?from=list">
    <c:param name="assetDetailId" value="${assetDetail.id}"/>
    <c:param name="view" value="detailonly"/>      
</c:url>
<c:if test="${assetDetail.id != null}">    
    <ul>
      <authz:authorize ifAnyGranted="admin, user">
        <li><h3><img src="<c:url value="/images/asterisk_orange.png"/>" alt=""/> <a href="<c:out value="${assetHitStatURL}" />"><u>See hit stats</u> <img src="<c:url value="/images/book_open.png"/>" alt="<fmt:message key="icon.information"/>" styleClass="icon"/></a></h3></li>
      </authz:authorize>
      <c:url var="editAssetAccessRequestUrl" value="/editAssetAccessRequest.html">
          <c:param name="assetDetailId" value="${assetDetail.id}"/>
      </c:url>
      <li><h3><img src="<c:url value="/images/asterisk_orange.png"/>" alt=""/> If you need to request access to this asset, then click <a href="<c:out value="${editAssetAccessRequestUrl}"/>"><u>here</u>. <img src="<c:url value="/images/note_go.png"/>" alt="<fmt:message key="icon.note_go"/>" styleClass="icon"/></a></h3></li>
    </ul>
</c:if>
<c:choose>
  <c:when test="${viewonly}">
    <c:url var="assetDetailURL" value="/editAssetDetail.html?from=list">
        <c:param name="id" value="${assetDetail.id}"/>      
    </c:url>    
    <table class="table largetable">
      <tbody>
        <tr><th width="20%">Name:</th><td><c:out value="${assetDetail.assetName}"/> <authz:authorize ifAnyGranted="admin, user">[<a href="<c:out value="${assetDetailURL}" />">Edit</a>]</authz:authorize></td></tr>
        <tr><th width="20%">Description:</th><td><c:out value="${assetDetail.assetDescription}"/></td></tr>
        <tr><th width="20%">Usage:</th><td><c:out value="${assetDetail.assetUsageType}"/></td></tr>
        <tr><th width="20%">Location:</th><td><c:out value="${assetDetail.assetLocation}"/></td></tr>
        <tr><th width="20%">Address:</th><td><c:out value="${assetDetail.assetAddress}"/></td></tr>
      </tbody>
    </table>
  </c:when>
  <c:otherwise>
<div class="groupformcenter">
    <form:form commandName="assetDetail" method="post" action="editAssetDetail.html" onsubmit="return validateAssetDetail(this)" id="assetDetailForm">
    <form:hidden path="id"/>   
    <ul>         
        <li>
            <canyon:label styleClass="desc" key="assetDetail.assetName"/>
            <form:errors path="assetName" cssClass="fieldError"/>
            <form:input path="assetName" id="assetName" cssClass="text large"/>
        </li> 
				<li>
            <canyon:label styleClass="desc" key="assetDetail.assetGroupName"/>
            <form:errors path="assetGroupName" cssClass="fieldError"/>
            <form:input path="assetGroupName" id="assetGroupName" cssClass="text large"/>
        </li>    
        <li>
            <canyon:label styleClass="desc" key="assetDetail.assetDescription"/>
            <form:errors path="assetDescription" cssClass="fieldError"/>
            <form:textarea path="assetDescription" id="assetDescription" cssClass="text large"/>
        </li>    
        <li>
              <canyon:label styleClass="desc" key="assetDetail.assetUsageType"/>
              <form:errors path="assetUsageType" cssClass="fieldError"/>           
              <form:select path="assetUsageType" cssClass="text medium">
                <form:option value="Other"/>
				        <form:option value="DEV"/>
                <form:option value="SIT"/>
                <form:option value="UAT"/>
                <form:option value="CAPLAB"/>
                <form:option value="PRODUCTION"/>                
              </form:select>

        </li>  
				<li>
            <canyon:label styleClass="desc" key="assetDetail.assetLocation"/>
            <form:errors path="assetLocation" cssClass="fieldError"/>
            <form:input path="assetLocation" id="assetLocation" cssClass="text large"/>
        </li>  
        <li>
            <canyon:label styleClass="desc" key="assetDetail.assetAddress"/>
            <form:errors path="assetAddress" cssClass="fieldError"/>
            <form:input path="assetAddress" id="assetAddress" cssClass="text large"/>
        </li>
             
        <li>
            <fieldset>
                <legend>Settings</legend>
                <form:checkbox path="enabled" id="enabled"/>
                <label for="enabled" class="choice"><fmt:message key="assetDetail.enabled"/></label>            
            </fieldset>
        </li>    
        <li>
            <canyon:label styleClass="desc" key="assetDetail.assetAdminTeamName"/>
            <form:errors path="assetAdminTeamName" cssClass="fieldError"/>
            <form:input path="assetAdminTeamName" id="assetAdminTeamName" cssClass="text large"/>
        </li>
        <li>
            <canyon:label styleClass="desc" key="assetDetail.assetAdminTeamEmail"/>
            <form:errors path="assetAdminTeamEmail" cssClass="fieldError"/>
            <form:input path="assetAdminTeamEmail" id="assetAdminTeamEmail" cssClass="text large"/>
        </li>
        <li>
            <canyon:label styleClass="desc" key="assetDetail.filterPrimaryAdminEmpEmail"/>
            <form:errors path="filterPrimaryAdminEmpEmail" cssClass="fieldError"/>
						<canyon:focus flag="${not assetDetail.primaryAdminEmployeeInfo.active and not empty assetDetail.id}" flagMessage="Inactive/invalid user"/>
            <form:input path="filterPrimaryAdminEmpEmail" id="filterPrimaryAdminEmpEmail" cssClass="text large"/>
        </li>
        <li>
      			<canyon:label styleClass="desc" key="assetDetail.filterSecondaryAdminEmpEmail"/>
      			<form:errors path="filterSecondaryAdminEmpEmail" cssClass="fieldError"/>
      			<canyon:focus flag="${not assetDetail.secondaryAdminEmployeeInfo.active and not empty assetDetail.id}" flagMessage="Inactive/invalid user"/>
      			<form:input path="filterSecondaryAdminEmpEmail" id="filterSecondaryAdminEmpEmail" cssClass="text large"/>
        </li>  
        <li>
      			<canyon:label styleClass="desc" key="assetDetail.filterPrimaryOwnerEmpEmail"/>
      			<form:errors path="filterPrimaryOwnerEmpEmail" cssClass="fieldError"/>
      			<canyon:focus flag="${not assetDetail.primaryOwnerEmployeeInfo.active and not empty assetDetail.id}" flagMessage="Inactive/invalid user"/>
      			<form:input path="filterPrimaryOwnerEmpEmail" id="filterPrimaryOwnerEmpEmail" cssClass="text large"/> 
			
        </li> 
        <li>
      			<canyon:label styleClass="desc" key="assetDetail.filterSecondaryOwnerEmpEmail"/>
      			<form:errors path="filterSecondaryOwnerEmpEmail" cssClass="fieldError"/>
      			<canyon:focus flag="${not assetDetail.secondaryOwnerEmployeeInfo.active and not empty assetDetail.id}" flagMessage="Inactive/invalid user"/>
      			<form:input path="filterSecondaryOwnerEmpEmail" id="filterSecondaryOwnerEmpEmail" cssClass="text large"/>
        </li> 
        <li class="buttonBar bottom">
            <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
            <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AssetDetail')" value="<fmt:message key="button.delete"/>" />
            <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
        </li>
    </ul>
    </form:form>
</div>
    <script type="text/javascript">
        Form.focusFirstElement($('assetDetailForm'));
    </script>
    <v:javascript formName="assetDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
    <script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
  
  </c:otherwise>
</c:choose>

<c:if test="${assetDetail.id != null}">
  <h2><fmt:message key="assetRoleList.heading"/></h2>
  <c:set var="assetRoleListTemp" value="${assetDetail.assetRoles}" scope="request"/>
  <jsp:include page="/WEB-INF/pages/inc_assetRoleList.jsp" />
</c:if>