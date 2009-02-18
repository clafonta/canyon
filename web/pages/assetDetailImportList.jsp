<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailImport.heading"/></title>
<content tag="heading"><fmt:message key="assetDetailImport.heading"/></content>
<c:choose>
  <c:when test="${not empty assetDetailList}">
    <jsp:include page="/WEB-INF/pages/inc_assetDetailList.jsp" />  
    <div>
      <form method="POST" action="<c:url value="/assetdetail/csv/import.html"/>">
    	      <input type="hidden" name="task" value="save"/>
						<ul>
						<li>
						<div>
	            <div class="left">
        				<span class="buttons" style="align:right;">
                  <button type="submit" class="positive"><img src="<c:url value="/images/table_add.png"/>" alt="<fmt:message key="button.save"/>" styleClass="icon"/> <fmt:message key="button.save"/></button>
         		 	  </span>
							</div>
							<div style="margin-top:1em;">
							<a href="<c:url value="/assetdetail/csv/import.html"/>"><fmt:message key="button.cancel"/></a>
							</di>
						</div>
						</li>
						</ul>
      </form>
    </div>
		
  </c:when>
  <c:otherwise>
    <div class="error">
    		 <span class="errorMessage"><fmt:message key="assetDetailImport.empty"/></span> <a href="<c:url value="/assetdetail/csv/import.html"/>"><fmt:message key="button.cancel"/></a>
    </div>
  </c:otherwise>
</c:choose>