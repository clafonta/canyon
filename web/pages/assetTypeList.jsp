<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetTypeList.title"/></title>
<content tag="heading"><fmt:message key="assetTypeList.heading"/></content>
<meta name="menu" content="AssetTypeMenu"/>
<c:set var="assetTypeListTemp" value="${assetTypeList}" scope="request"/>
<jsp:include page="/WEB-INF/pages/inc_assetTypeList.jsp" />  
