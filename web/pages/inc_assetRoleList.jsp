<%@ include file="/common/taglibs.jsp"%>
<c:url var="assetRoleUrl" value="/editAssetRole.html">
  <c:param name="assetDetailId" value="${assetDetail.id}"/>
</c:url>
<authz:authorize ifAnyGranted="admin, user">
  <c:set var="buttons">
    <input type="button" style="margin-right: 5px" onclick="location.href='<c:out value="${assetRoleUrl}"/>'"  value="Add Role"/>
  </c:set>
  <c:out value="${buttons}" escapeXml="false"/>
</authz:authorize>
<display:table name="assetRoleListTemp" cellspacing="0" cellpadding="0" requestURI="" id="assetRoleList" pagesize="25" class="greytable assetRoleList" export="false">
		<authz:authorize ifAnyGranted="admin, user">
      <display:column property="name" escapeXml="true" sortable="true" url="/editAssetRole.html" paramId="id" paramProperty="id" titleKey="assetRole.name" class="medium"/>
		</authz:authorize>   
		<authz:authorize ifAnyGranted="ROLE_ANONYMOUS">
      <display:column property="name" escapeXml="true" sortable="true" titleKey="assetRole.name"/>
		</authz:authorize>   
    <display:column property="description" escapeXml="true" sortable="true" titleKey="assetRole.description"/>
    <display:column property="customerSensitiveData" escapeXml="true" sortable="true" titleKey="assetRole.customerSensitiveData"/>
    <display:setProperty name="paging.banner.item_name" value="assetRole"/>
    <display:setProperty name="paging.banner.items_name" value="assetRoles"/>
</display:table>
<authz:authorize ifAnyGranted="admin, user">
<script type="text/javascript">
    highlightTableRows("assetRoleList");
</script>
</authz:authorize>