<%@ include file="/common/taglibs.jsp"%>

<div>
<display:table name="assetDetailList" cellspacing="0" cellpadding="0" requestURI=""
    id="assetDetail" pagesize="25" class="greytable assetDetailList largetable" export="true">
    <display:column titleKey="assetDetail.assetName"  class="small"  sortable="true">
				 <a	class="tooltip" href="<c:url value="/viewAssetDetail.html" />?id=<c:out value="${assetDetail.id}" />">
	 		<c:out value="${assetDetail.assetName}"  />
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
		<display:column property="assetGroupName" escapeXml="true" sortable="true"
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