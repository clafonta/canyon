<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailImport.heading"/></title>
<content tag="heading"><fmt:message key="assetDetailImport.heading"/></content>


<jsp:include page="/WEB-INF/pages/inc_assetDetailList.jsp" />  

<div>
  <form method="POST" action="<c:url value="/assetdetail/csv/import.html"/>">
	      <input type="hidden" name="task" value="save"/>
				<div class="buttons" style="align:right;">
          <button type="submit" class="positive"><img src="<c:url value="/images/table_add.png"/>" alt="<fmt:message key="icon.note_go"/>" styleClass="icon"/>Save</button>
 		 	  </div>
  </form>
</div>