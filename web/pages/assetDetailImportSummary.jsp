<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailImport.heading"/></title>
<div>
 <c:if test="${not empty errorList}">
 <h3><fmt:message key="assetDetailImport.failure"/></h3>
   <c:forEach var="error" items="${errorList}">
            <img src="<c:url value="/images/exclamation.png"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
 </c:if>
 <p style="padding:20px;">
 <a href="<c:url value="/assetDetailList.html" />"><fmt:message key="assetDetailList.link"/>  </a></li>
 </p>
</div>