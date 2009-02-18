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
<jsp:include page="/WEB-INF/pages/inc_assetDetailList.jsp" />  