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
      <td><span class="condense"><fmt:message key="assetDetail.assetAdminTeamName"/></span></td> 
      <td><span class="condense"><fmt:message key="assetDetail.assetAdminTeamEmail"/></span></td> 
    </tr>
    <tr>
      <td><span class="condense"><form:input path="assetName" id="assetName" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="assetAdminTeamName" id="assetAdminTeamName" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="assetAdminTeamEmail" id="assetAdminTeamEmail" cssClass="text medium"/></span></td>
    </tr>
    <tr>
	  <td><span class="condense"><fmt:message key="assetDetail.primaryAdminEmployeeEmail"/></span></td>
	  <td><span class="condense"><fmt:message key="assetDetail.secondaryAdminEmployeeEmail"/></span></td>
	  <td><span class="condense"><fmt:message key="assetDetail.primaryOwnerEmployeeEmail"/></span></td>  
	  <td><span class="condense"><fmt:message key="assetDetail.secondaryOwnerEmployeeEmail"/></span></td>
	</tr>
        <tr>
          <td><span class="condense"><form:input path="filterPrimaryAdminEmpEmail" id="filterPrimaryAdminEmpEmail" cssClass="text medium"/></span></td>
          <td><span class="condense"><form:input path="filterSecondaryAdminEmpEmail" id="filterSecondaryAdminEmpEmail" cssClass="text medium"/></span></td>
          <td><span class="condense"><form:input path="filterPrimaryOwnerEmpEmail" id="filterPrimaryOwnerEmpEmail" cssClass="text medium"/></span></td>
          <td><span class="condense"><form:input path="filterSecondaryOwnerEmpEmail" id="filterSecondaryOwnerEmpEmail" cssClass="text medium"/></span></td>
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
  <authz:authorize ifAnyGranted="admin, user">
    <form id="assetDetailForm" method="GET" action="<c:url value="/editAssetDetail.html"/>">
      <select name="assetTypeId">
      <c:forEach var="assetType" items="${assetTypeList}">
      <option value="<c:out value="${assetType.id}"/>"><c:out value="${assetType.name}"/></option>
      </c:forEach>				
      </select>
      <input type="submit" name="create_asset" value="Create Asset"/>
    </form>
  </authz:authorize>
</div>

<h2>Results</h2>

<jsp:include page="/WEB-INF/pages/inc_assetDetailList.jsp" />  