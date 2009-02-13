<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="assetAccessRequestList.title"/></title>
<content tag="heading"><fmt:message key="assetAccessRequestList.heading"/></content>
<meta name="menu" content="AssetAccessRequestMenu"/>
<div class="group">
<h2>Search / Filter</h2>
<form:form commandName="assetAccessRequestSearchForm" method="GET" action="assetAccessRequests.html" id="assetAccessRequestSearchForm">
  <table summary="" class="plain">
    <tr>
      <td><canyon:label key="assetAccessRequestSearchForm.employeeUserId" styleClass="condense"/></td>
      <td><canyon:label key="assetAccessRequestSearchForm.employeeEmail" styleClass="condense"/></td>
      <td><canyon:label key="assetAccessRequestSearchForm.assetAdministrationEmail" styleClass="condense"/></td>
      <td><canyon:label key="assetAccessRequestSearchForm.startCompleteDateString" styleClass="condense"/></td>      
      <td><canyon:label key="assetAccessRequestSearchForm.complete" styleClass="condense"/></td>
    </tr>
    <tr>
      <td><span class="condense"><form:input path="employeeUserId" id="employeeUserId" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="employeeEmail" id="employeeEmail" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="assetAdministrationEmail" id="assetAdministrationEmail" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="startCompleteDateString" id="startCompleteDateString" cssClass="text small"/> <img src="<c:url value="/images/iconCalendar.gif"/>" alt=""/></span></td>
      <td>
        <span class="condense">
	        <select id="complete" name="complete" class="text medium">
	            <option value="ALL" <c:if test="${assetAccessRequestSearchForm.complete != null}">selected="selected"</c:if> >both</option>
		        <option value="COMPLETED" <c:if test="${assetAccessRequestSearchForm.complete == true}">selected="COMPLETED"</c:if>>true</option>
	            <option value="NOT_COMPLETED" <c:if test="${assetAccessRequestSearchForm.complete == false}">selected="NOT_COMPLETED"</c:if>>false</option>
	         </select>
        </span>
      </td>
    </tr>
    <tr>
      <td><canyon:label key="assetAccessRequestSearchForm.assetName" styleClass="condense"/></span>
      <td><canyon:label key="assetAccessRequestSearchForm.assetRole" styleClass="condense"/></td>
      <td><canyon:label key="assetAccessRequestSearchForm.endCompleteDateString" styleClass="condense"/></td>
      <td></td>
      <td></td>
    </tr>
    <tr>   
      <td><span class="condense"><form:input path="assetName" id="assetName" cssClass="text medium"/></span></td>   
      <td><span class="condense"><form:input path="assetRole" id="assetRole" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="endCompleteDateString" id="endCompleteDateString" cssClass="text small"/> <img src="<c:url value="/images/iconCalendar.gif"/>" alt=""/></span></td>   
      <td></td>
      <td></td>
    </tr>   
  </table>  
<c:url value="assetAccessRequests.html" var="clearFilterUrl">  
</c:url>
<ul>
  <li class="buttonBar bottom">
    <input type="submit" class="button" name="search" value="Search" /> <a href="<c:out value="${clearFilterUrl}"/>">Clear Search</a>
  </li>
</ul>
</form:form>
</div>
<h2>Results</h2>
<c:set var="assetAccessRequestTemp" value="${assetAccessRequestList}" scope="request"/>
<jsp:include page="/WEB-INF/pages/inc_assetAccessRequestList.jsp" />  
