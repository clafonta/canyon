<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetHitStatList.title"/></title>
<c:url value="/editAssetDetail.html" var="editAssetDetailUrl">
  <c:param name="id" value="${assetDetail.id}" />
	<c:param name="view" value="true" />												
</c:url>
<content tag="heading"><fmt:message key="assetHitStatList.heading"/>: <span class="focus"><a href="<c:out value="${editAssetDetailUrl}"/>"><u><c:out value="${assetDetail.assetName}"/></u></a></span></content>
<meta name="menu" content="AssetHitStatMenu"/>
<div class="group">
  <h2>Search / Filter</h2>
  <form:form commandName="assetHitStatSearchForm" method="GET" action="assetHitStatList.html" id="assetHitStatSearchForm">
  <form:hidden path="assetDetailId"/>
  <table summary="" class="plain">
    <tr>
      <td><canyon:label key="assetHitStatSearchForm.userIdentifierConnectingToAsset" styleClass="condense"/></td>      
      <td><canyon:label key="assetHitStatSearchForm.minNumberOfLoginsString" styleClass="condense"/></td>
      <td><canyon:label key="assetHitStatSearchForm.startLastLoginTimestampString" styleClass="condense"/></td>
    </tr>
    <tr>
      <td><span class="condense"><form:input path="userIdentifierConnectingToAsset" id="userIdentifierConnectingToAsset" cssClass="text medium"/></span></td>      
      <td><span class="condense"><form:input path="minNumberOfLoginsString" id="minNumberOfLoginsString" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="startLastLoginTimestampString" id="startLastLoginTimestampString" cssClass="text small"/> <img src="<c:url value="/images/iconCalendar.gif"/>" alt="" /></span></td>
    </tr>
    <tr>
      <td><canyon:label key="assetHitStatSearchForm.applicationUsedToConnect" styleClass="condense"/></td>
      <td><canyon:label key="assetHitStatSearchForm.maxNumberOfLoginsString" styleClass="condense"/></td>      
      <td><canyon:label key="assetHitStatSearchForm.endLastLoginTimestampString" styleClass="condense"/></td>
    </tr>
    <tr>
      <td><span class="condense"><form:input path="applicationUsedToConnect" id="applicationUsedToConnect" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="maxNumberOfLoginsString" id="maxNumberOfLoginsString" cssClass="text medium"/></span></td>      
      <td><span class="condense"><form:input path="endLastLoginTimestampString" id="endLastLoginTimestampString" cssClass="text small"/> <img src="<c:url value="/images/iconCalendar.gif"/>" alt="" /></span></td>
    </tr>
     <tr>
      <td><canyon:label  key="assetHitStatSearchForm.userIdentifierFromOS" styleClass="condense"/></td>
      <td><canyon:label key="assetHitStatSearchForm.connectingIP" styleClass="condense"/></td>
      <td><canyon:label key="assetHitStatSearchForm.host" styleClass="condense"/></td>
    </tr>
    <tr>
      <td><span class="condense"><form:input path="userIdentifierFromOS" id="userIdentifierFromOS" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="connectingIP" id="connectingIP" cssClass="text medium"/></span></td>
      <td><span class="condense"><form:input path="host" id="host" cssClass="text medium"/></span></td>
    </tr>
</table>                        
    
  <c:url value="assetHitStatList.html" var="clearFilterUrl">
    <c:param name="id" value="${assetHitStat.id}" />
    <c:param name="assetDetailId" value="${assetHitStatSearchForm.assetDetailId}" />
    <c:param name="view" value="detailonly" />
  	<c:param name="from" value="list" />
  </c:url>
  <ul>
    <li class="buttonBar bottom">
      <input type="submit" class="button" name="save" value="Search" /> <a href="<c:out value="${clearFilterUrl}"/>">Clear Search</a>
    </li>
  </ul>
  </form:form>
</div>
<c:set var="assetHitStatListTemp" value="${assetHitStatList}" scope="request"/>
<div>
<h2>Results</h2>
<jsp:include page="/WEB-INF/pages/inc_assetHitStatList.jsp" />
</div>  
