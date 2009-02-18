<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailImport.heading"/></title>
<content tag="heading"><fmt:message key="assetDetailImport.heading"/></content>


<c:choose>

<c:when test="not empty assetDetailList">
  <jsp:include page="/WEB-INF/pages/inc_assetDetailList.jsp" />  
  <div>
    <form method="POST" action="<c:url value="/assetdetail/csv/import.html"/>">
  	      <input type="hidden" name="task" value="save"/>
  				<div class="buttons" style="align:right;">
            <button type="submit" class="positive"><img src="<c:url value="/images/table_add.png"/>" alt="<fmt:message key="button.save"/>" styleClass="icon"/><fmt:message key="button.save"/></button>
   		 	  </div>
    </form>
  </div>
</c:when>
<c:otherwise>
<div class="error">
<span class="errorMessage"><fmt:message key="assetDetailImport.empty"/></span>
</div>
</c:otherwise>
</c:choose>