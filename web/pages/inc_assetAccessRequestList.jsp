<%@ include file="/common/taglibs.jsp"%>
<c:out value="${buttons}" escapeXml="false"/>
<display:table name="assetAccessRequestTemp" cellspacing="0" cellpadding="0" requestURI=""
    id="assetAccessRequestList" pagesize="25" class="greytable assetAccessRequestList" export="true">
    <display:column property="id" escapeXml="true" sortable="true"
        url="/editAssetAccessRequest.html" paramId="id" paramProperty="id"
        titleKey="assetAccessRequest.id"/>
    <display:column property="employeeUserId" escapeXml="true" sortable="true"
         titleKey="assetAccessRequest.employeeUserId"/>    
     <display:column property="employeeInfo.employeeEmail" escapeXml="true" sortable="true"
         titleKey="assetAccessRequest.employeeInfo.employeeEmail"/>    
    <display:column property="assetRole.assetDetail.assetName" escapeXml="true" sortable="true"
         titleKey="assetAccessRequest.assetName"/>
    <display:column property="assetRole.name" escapeXml="true" sortable="true"
         titleKey="assetAccessRequest.assetRole"/>    
    <display:column property="complete" escapeXml="true" sortable="true"
         titleKey="assetAccessRequest.complete"/>
    <display:column property="requestCreateDate" escapeXml="true" sortable="true"
         titleKey="assetAccessRequest.requestCreateDate"/>
    <display:column property="completeDate" escapeXml="true" sortable="true"
         titleKey="assetAccessRequest.completeDate"/>
    <display:setProperty name="paging.banner.item_name" value="assetAccessRequest"/>
    <display:setProperty name="paging.banner.items_name" value="assetAccessRequests"/>
</display:table>
<script type="text/javascript">
    highlightTableRows("assetAccessRequestList");
</script>
