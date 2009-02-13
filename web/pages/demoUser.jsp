<%@ include file="/common/taglibs.jsp" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<!-- ajaxtags stuff -->
<script type="text/javascript" src="<c:url value='/scripts/ajaxtags.js'/>"></script>

<head>
</head>
<form method="post" action="">
  <input type="text" id="searchTerm" name="searchTerm" size="40" value=""/>
  <span id="indicator" style="display:none;"><img src="<c:url value="/images/indicator.gif"/>"/></span>
</form>

<ajax:autocomplete
        baseUrl="${pageContext.request.contextPath}/ajaxFrontGetAssetDetails.html"
        source="searchTerm"
        target="searchTerm"
        className="autocomplete"
        indicator="indicator"
        minimumCharacters="1"
        parameters="user={searchTerm}"
        parser="new ResponseXmlToHtmlListParser()"/>
