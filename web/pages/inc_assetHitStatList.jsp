<%@ include file="/common/taglibs.jsp"%>

<display:table name="assetHitStatListTemp" cellspacing="0" cellpadding="0" requestURI="" id="assetHitStatList" pagesize="50" class="greytable assetHitStatList largetable" export="true">
    <display:column property="userIdentifierConnectingToAsset" sortable="true" titleKey="assetHitStat.userIdentifierConnectingToAsset" />
    <display:column property="userIdentifierFromOS" escapeXml="true" sortable="true" titleKey="assetHitStat.userIdentifierFromOS" />
    <display:column property="applicationUsedToConnect" escapeXml="true" sortable="true" titleKey="assetHitStat.applicationUsedToConnect" />
    <display:column property="connectingIP" sortable="true" titleKey="assetHitStat.connectingIP" />
		<display:column property="host" sortable="true" titleKey="assetHitStat.host"/>
    <display:column property="lastLoginTimestamp" escapeXml="true" sortable="true" titleKey="assetHitStat.lastLoginTimestamp" decorator="org.tll.canyon.webapp.decorator.SimpleDateDecorator"/>
    <display:column property="numberOfLogins" escapeXml="true" sortable="true" titleKey="assetHitStat.numberOfLogins" />
    <display:setProperty name="paging.banner.item_name" value="assetHitStat"/>
    <display:setProperty name="paging.banner.items_name" value="assetHitStats"/>
</display:table>
<script type="text/javascript">
    highlightTableRows("assetHitStatList");
</script>
