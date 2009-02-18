<%@ include file="/common/taglibs.jsp"%>

<div>
<display:table name="assetDetailList" cellspacing="0" cellpadding="0" requestURI=""
    id="assetDetail" pagesize="25" class="greytable assetDetailList largetable" export="true">
    <display:column titleKey="assetDetail.assetName"  class="small"  sortable="true">
				 <a	class="tooltip" href="<c:url value="/viewAssetDetail.html" />?id=<c:out value="${assetDetail.id}" />">
				 <canyon:slug value="${assetDetail.assetName}" maxLength="25"/>
     <span>
		 <strong><fmt:message key="assetDetail.assetName"/>:</strong> <c:out value="${assetDetail.assetName}"/><br/>
		 <strong><fmt:message key="assetDetail.assetGroupName"/>:</strong> <c:out value="${assetDetail.assetGroupName}"/><br/>
		 <strong><fmt:message key="assetDetail.assetDescription"/>:</strong> <c:out value="${assetDetail.assetDescription}"/><br/>
		 <strong><fmt:message key="assetDetail.assetUsageType"/>:</strong> <c:out value="${assetDetail.assetUsageType}"/><br/>
		 <strong><fmt:message key="assetDetail.assetLocation"/>:</strong> <c:out value="${assetDetail.assetLocation}"/><br/>
		 <strong><fmt:message key="assetDetail.assetAddress"/>:</strong> <c:out value="${assetDetail.assetAddress}"/><br/>
		 <strong><fmt:message key="assetDetail.assetAdminTeamName"/>:</strong> <c:out value="${assetDetail.assetAdminTeamName}"/> (<c:out value="${assetDetail.assetAdminTeamEmail}"/>) <br/>
		 <strong><fmt:message key="assetDetail.primaryAdminEmployeeName"/>:</strong> <c:out value="${assetDetail.primaryAdminEmployeeInfo.employeeFullname}"/><br/>
		 <strong><fmt:message key="assetDetail.secondaryAdminEmployeeName"/>:</strong> <c:out value="${assetDetail.secondaryAdminEmployeeInfo.employeeFullname}"/><br/>
		 <strong><fmt:message key="assetDetail.primaryOwnerEmployeeName"/>:</strong> <c:out value="${assetDetail.primaryOwnerEmployeeInfo.employeeFullname}"/><br/>
		 <strong><fmt:message key="assetDetail.secondaryOwnerEmployeeName"/>:</strong> <c:out value="${assetDetail.secondaryOwnerEmployeeInfo.employeeFullname}"/><br/>		    
		 </span></a> 
		</display:column>
		<display:column escapeXml="true" sortable="true" titleKey="assetDetail.assetGroupName">
      <canyon:slug value="${assetDetail.assetGroupName}" maxLength="15"/>
		</display:column>
    <display:column escapeXml="true" sortable="true" class="large" 
         titleKey="assetDetail.assetDescription">
        <canyon:slug value="${assetDetail.assetDescription}" maxLength="25"/>
		</display:column>
    <display:column escapeXml="true" sortable="true"
         titleKey="assetDetail.assetUsageType">
				 <canyon:slug value="${assetDetail.assetUsageType}" maxLength="15"/>
		</display:column>
    <display:column escapeXml="true" sortable="true"
         titleKey="assetDetail.assetAddress">	
		<canyon:slug value="${assetDetail.assetAddress}" maxLength="15"/>
		</display:column>
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