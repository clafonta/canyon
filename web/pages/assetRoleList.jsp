<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetRoleList.title"/></title>
<content tag="heading"><fmt:message key="assetRoleList.heading"/></content>
<meta name="menu" content="AssetRoleMenu"/>
<c:set var="assetRoleListTemp" value="${assetRoleList}" scope="request"/>
<jsp:include page="/WEB-INF/pages/inc_assetRoleList.jsp" />  
