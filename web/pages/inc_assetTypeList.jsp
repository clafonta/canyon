<%@ include file="/common/taglibs.jsp"%>
<c:url var="assetTypeUrl" value="/editAssetType.html">
  
</c:url>
<authz:authorize ifAnyGranted="admin, user">
  <c:set var="buttons">
    <input type="button" style="margin-right: 5px" onclick="location.href='<c:out value="${assetTypeUrl}"/>'"  value="Add Type"/>
  </c:set>
  <c:out value="${buttons}" escapeXml="false"/>
</authz:authorize>
<display:table name="assetTypeListTemp" cellspacing="0" cellpadding="0" requestURI="" id="assetTypeList" pagesize="25" class="greytable assetTypeList" export="false">
		<authz:authorize ifAnyGranted="admin, user">
      <display:column property="name" escapeXml="true" sortable="true" url="/editAssetType.html" paramId="id" paramProperty="id" titleKey="assetType.name" class="medium"/>
		</authz:authorize>   
		<authz:authorize ifAnyGranted="ROLE_ANONYMOUS">
      <display:column property="name" escapeXml="true" sortable="true" titleKey="assetType.name"/>
		</authz:authorize>   
    <display:column property="description" escapeXml="true" sortable="true" titleKey="assetType.description"/>    
    <display:setProperty name="paging.banner.item_name" value="assetType"/>
    <display:setProperty name="paging.banner.items_name" value="assetTypes"/>
</display:table>
<authz:authorize ifAnyGranted="admin, user">
<script type="text/javascript">
    highlightTableRows("assetTypeList");
</script>
</authz:authorize>