<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="assetAccessRequestStatusList.title"/></title>
<content tag="heading"><fmt:message key="assetAccessRequestStatusList.heading"/></content>
<meta name="menu" content="AssetAccessRequestStatusMenu"/>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editAssetAccessRequestStatus.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<display:table name="assetAccessRequestStatusList" cellspacing="0" cellpadding="0" requestURI=""
    id="assetAccessRequestStatusList" pagesize="25" class="table assetAccessRequestStatusList" export="true">

    <display:column property="id" escapeXml="true" sortable="true"
        url="/editAssetAccessRequestStatus.html" paramId="id" paramProperty="id"
        titleKey="assetAccessRequestStatus.id"/>
    <display:column property="approvalUserId" escapeXml="true" sortable="true"
         titleKey="assetAccessRequestStatus.approvalUserId"/>
    <display:column property="approvalStatus" escapeXml="true" sortable="true"
         titleKey="assetAccessRequestStatus.approvalStatus"/>
    <display:column property="assetAccessRequestId" escapeXml="true" sortable="true"
         titleKey="assetAccessRequestStatus.assetAccessRequestId"/>
    <display:setProperty name="paging.banner.item_name" value="assetAccessRequestStatus"/>
    <display:setProperty name="paging.banner.items_name" value="assetAccessRequestStatuss"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("assetAccessRequestStatusList");
</script>
