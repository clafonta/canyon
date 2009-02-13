<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailList.title"/></title>
<content tag="heading"><fmt:message key="assetDetailList.heading"/></content>
<meta name="menu" content="AssetMenu"/>
<div class="group">
  <h2>Search / Filter</h2>
  <form:form commandName="assetDetail" method="GET" action="assetDetailList.html" id="assetDetailForm">
  <form:hidden path="id"/>
  <table summary="" class="plain">
    <tr>
      <td><span class="condense"><fmt:message key="assetDetail.assetName"/></span></td>
      <td><span class="condense"><fmt:message key="assetDetail.assetGroupName"/></span></td>
      <td><span class="condense"><fmt:message key="assetDetail.assetAddress"/></span></td>  
      <td><span class="condense"><fmt:message key="assetDetail.assetAdminTeamName"/></span></td> 
      <td><span class="condense"><fmt:message key="assetDetail.assetAdminTeamEmail"/></span></td> 
    </tr>
    <tr>
      <td><span class="condense"><form:input path="assetName" id="assetName" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="assetGroupName" id="assetGroupName" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="assetAddress" id="assetAddress" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="assetAdminTeamName" id="assetAdminTeamName" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="assetAdminTeamEmail" id="assetAdminTeamEmail" cssClass="text medium"/></span></td>
    </tr>
    <tr>
	  <td><span class="condense"><fmt:message key="assetDetail.primaryAdminEmployeeEmail"/></span></td>
	  <td><span class="condense"><fmt:message key="assetDetail.secondaryAdminEmployeeEmail"/></span></td>
	  <td><span class="condense"><fmt:message key="assetDetail.primaryOwnerEmployeeEmail"/></span></td>  
	  <td><span class="condense"><fmt:message key="assetDetail.secondaryOwnerEmployeeEmail"/></span></td> 
	  <td><span class="condense"><fmt:message key="assetDetail.assetUsageType"/></span></td>
	</tr>
        <tr>
          <td><span class="condense"><form:input path="filterPrimaryAdminEmpEmail" id="filterPrimaryAdminEmpEmail" cssClass="text medium"/></span></td>
          <td><span class="condense"><form:input path="filterSecondaryAdminEmpEmail" id="filterSecondaryAdminEmpEmail" cssClass="text medium"/></span></td>
          <td><span class="condense"><form:input path="filterPrimaryOwnerEmpEmail" id="filterPrimaryOwnerEmpEmail" cssClass="text medium"/></span></td>
          <td><span class="condense"><form:input path="filterSecondaryOwnerEmpEmail" id="filterSecondaryOwnerEmpEmail" cssClass="text medium"/></span></td>
					<td><span class="condense"><form:input path="assetUsageType" id="assetUsageType" cssClass="text medium"/></span></td>
    </tr>
  </table>
  <c:url value="assetDetailList.html" var="clearFilterUrl"/>
  <ul>
    <li class="buttonBar bottom">
      <input type="submit" class="button" name="save" value="Search" /> <a href="<c:out value="${clearFilterUrl}"/>">Clear Search</a>
    </li>
  </ul>
  </form:form>
</div>
<div>
<h2>Results</h2>
<authz:authorize ifAnyGranted="admin, user">
  <c:set var="buttons">
    <input type="button" style="margin-right: 5px" onclick="location.href='<c:url value="/editAssetDetail.html"/>'" value="Add Asset"/>
  </c:set>
  <p class="smallbuffer">
    <c:out value="${buttons}" escapeXml="false"/>
  </p>
</authz:authorize>

<display:table name="assetDetailList" cellspacing="0" cellpadding="0" requestURI=""
    id="assetDetail" pagesize="25" class="greytable assetDetailList largetable" export="true">
    <display:column titleKey="assetDetail.assetName"  class="small"  sortable="true">
				 <a	class="tooltip" href="<c:url value="/viewAssetDetail.html" />?id=<c:out value="${assetDetail.id}" />">
	 		<c:out value="${assetDetail.assetName}" />
     <span>
		 <table>
       <tr><td><label class="condense"><fmt:message key="assetDetail.assetName"/></label></td><td><c:out value="${assetDetail.assetName}"/></td></tr>
			 <tr><td><label class="condense"><fmt:message key="assetDetail.assetGroupName"/></label></td><td><c:out value="${assetDetail.assetGroupName}"/></td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.assetDescription"/></label></td><td><c:out value="${assetDetail.assetDescription}"/></td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.assetUsageType"/></label></td><td><c:out value="${assetDetail.assetUsageType}"/></td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.assetLocation"/></label></td><td><c:out value="${assetDetail.assetLocation}"/></td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.assetAddress"/></label></td><td><c:out value="${assetDetail.assetAddress}"/></td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.assetAdminTeamName"/></label></td><td><c:out value="${assetDetail.assetAdminTeamName}"/> (<c:out value="${assetDetail.assetAdminTeamEmail}"/>) </td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.primaryAdminEmployeeName"/></label></td><td><c:out value="${assetDetail.primaryAdminEmployeeInfo.employeeFullname}"/></td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.secondaryAdminEmployeeName"/></label></td><td><c:out value="${assetDetail.secondaryAdminEmployeeInfo.employeeFullname}"/></td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.primaryOwnerEmployeeName"/></label></td><td><c:out value="${assetDetail.primaryOwnerEmployeeInfo.employeeFullname}"/></td></tr>
		   <tr><td><label class="condense"><fmt:message key="assetDetail.secondaryOwnerEmployeeName"/></label></td><td><c:out value="${assetDetail.secondaryOwnerEmployeeInfo.employeeFullname}"/></td></tr>
		 </table>   
		 </span></a> 
		</display:column>
		<display:column property="assetGroupName" escapeXml="true" sortable="true" class="large" 
         titleKey="assetDetail.assetGroupName"/>
    <display:column property="assetDescription" escapeXml="true" sortable="true" class="large" 
         titleKey="assetDetail.assetDescription" decorator="org.tll.canyon.webapp.decorator.SlugDecorator"/>
    <display:column property="assetUsageType" escapeXml="true" sortable="true"
         titleKey="assetDetail.assetUsageType"/>
    <display:column property="assetAddress" escapeXml="true" sortable="true"
         titleKey="assetDetail.assetAddress"/>	
		<display:column escapeXml="false" sortable="false" style="width:10px;">
			  <c:if test="${not empty assetDetail.issues}">
				<a	class="tooltip_small" href="<c:url value="/viewAssetDetail.html" />?id=<c:out value="${assetDetail.id}" />">
				<img src="<c:url value="/images/exclamation.png"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />
				<span><strong>Please fix:</strong>
				<ul>
				 <c:forEach var="msg" items="${assetDetail.issues}">
            <li><c:out value="${msg.description}" escapeXml="false"/></li>
        </c:forEach>
			  </ul>
				</span>
				</a>
				</c:if>
		</display:column>

    
    <display:setProperty name="paging.banner.item_name" value="assetDetail"/>
    <display:setProperty name="paging.banner.items_name" value="assetDetails"/>
</display:table>
</div>
<script type="text/javascript">
    highlightTableRows("assetDetailList");
</script>