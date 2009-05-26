<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailDetail.title"/></title>
<content tag="heading"><fmt:message key="assetDetailDetail.heading"/> - <span class="focus"><c:out value="${assetDetail.assetName}"/></span></content>
<spring:bind path="assetDetail.*">
    <c:if test="${not empty status.errorMessages}">
	    <div class="error">    
	        <c:forEach var="error" items="${status.errorMessages}">
	            <img src="<c:url value="/images/iconWarning.gif"/>"
	                alt="<fmt:message key="icon.warning"/>" class="icon" />
	            <c:out value="${error}" escapeXml="false"/><br />
	        </c:forEach>
	    </div>
    </c:if>
</spring:bind>
<c:url var="assetHitStatURL" value="/assetHitStatList.html?from=list">
    <c:param name="assetDetailId" value="${assetDetail.id}"/>
    <c:param name="view" value="detailonly"/>    
</c:url>
<c:if test="${assetDetail.id != null}">    
    <ul>
      <authz:authorize ifAnyGranted="admin, user">
        <li><h3><img src="<c:url value="/images/asterisk_orange.png"/>" alt=""/> <a href="<c:out value="${assetHitStatURL}" />"><u>See hit stats</u> <img src="<c:url value="/images/book_open.png"/>" alt="<fmt:message key="icon.information"/>" styleClass="icon"/></a></h3></li>
      </authz:authorize>
    </ul>
    
</c:if>


 <c:if test="${not empty assetDetail.issues}">
   <div class="error">			
  				<strong>Please fix:</strong><br/><br/>
  				<ul>
  				 <c:forEach var="msg" items="${assetDetail.issues}">
              <li><img src="<c:url value="/images/exclamation.png"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />  <c:out value="${msg.description}" escapeXml="false"/></li>
          </c:forEach>
  			  </ul>
   </div>				
</c:if>

<h2 class="splitter"><fmt:message key="assetDetailDetail.heading"/> - <span class="focus"><c:out value="${assetDetail.assetName}"/></span></h2>
<div class="splitter">

<table class="greytable largetable largefont">
	<tbody>
   	  
		<tr><th class="right" width="20%"><fmt:message key="assetDetail.assetName"/></th><td><c:out value="${assetDetail.assetName}"/></td></tr>
		<authz:authorize ifAnyGranted="admin, user">
      <tr><th></th><td align="right">
      				<form method="get" action="<c:url value="/editAssetDetail.html?from=list"/>">
               <input type="hidden" name="id" value="<c:out value="${assetDetail.id}"/>"/>
               <div class="buttons">
          		   <button type="submit" class="positive"><img src="<c:url value="/images/application_edit.png"/>" alt="<fmt:message key="icon.edit"/>" styleClass="icon"/> Edit Asset</button>
           		 </div>
          		 </form>
      </td></tr>
      </authz:authorize>
			<c:if test="${assetDetail.id != null}">
     				<tr><th></th><td align="right">
     				<form method="get" action="<c:url value="/editAssetAccessRequest.html"/>">
     							<input type="hidden" name="assetDetailId" value="<c:out value="${assetDetail.id}"/>"/>
     							<div class="buttons" style="align:right;">
		   						<button type="submit" class="positive"><img src="<c:url value="/images/key_add.png"/>" alt="<fmt:message key="icon.note_go"/>" styleClass="icon"/> Request Access</button>
 		 							</div>
		 				</form>
		 				</td></tr>
			</c:if>
	</tbody>
</table>
</div>

<c:if test="${assetDetail.id != null}">
	
  
	<authz:authorize ifAnyGranted="admin, user">
	  
    <h2 class="splitter"><fmt:message key="assetHistoryNote.heading"/></h2>
    <div class="splitter">
        <div class="groupformcenter" style="padding:5px;">
  		  <display:table name="assetDetail.assetHistoryNotes" cellspacing="0" cellpadding="0" pagesize="20" class="largetable greytable" export="false">
          <display:column property="comment" escapeXml="true" sortable="true" titleKey="assetHistoryNote.comment"/>
  				<display:column property="createTimestamp" escapeXml="true" sortable="true" titleKey="assetHistoryNote.create.timstamp"/>
  				<display:column property="user.username" escapeXml="true" sortable="true" titleKey="assetHistoryNote.username"/>
  			</display:table>
				<p style="margin-top:15px;">
          <form method="POST" action="<c:url value="/assetdetail/note/add.html"/>">
      		  <div style="padding:0px 0px 10px 0px;">
              <input type="hidden" name="assetDetailId" value="<c:out value="${assetDetail.id}"/>"/>
      		    <textarea name="comment" style="width: 600px; height: 6em; "></textarea>
      		 </div>
           <div class="buttons">
      		   <button type="submit" class="positive"><img src="<c:url value="/images/note_add.png"/>" alt="<fmt:message key="icon.note_go"/>" styleClass="icon"/><fmt:message key="assetHistoryNote.addcomment"/></button>
       		 </div>
      		 </form>
				</p>
      	</div>		 
    </div>
  </authz:authorize>

  <h2 class="splitter"><fmt:message key="assetRoleList.heading"/></h2>
	<div class="splitter">
	     <p>
    		 <c:set var="assetRoleListTemp" value="${assetDetail.assetRoles}" scope="request"/>
  			 <jsp:include page="/WEB-INF/pages/inc_assetRoleList.jsp" />
			 </p>
	</div>

  <h2 class="splitter"><fmt:message key="assetAccessRequestList.blurb"/></h2>
	<div class="splitter">
    <display:table name="assetAccessRequestList" cellspacing="0" cellpadding="0" requestURI="" id="assetAccessRequestList" pagesize="70" class="largetable greytable assetAccessRequestList" export="false">
        <display:column property="employeeUserId" escapeXml="true" sortable="true" titleKey="assetAccessRequest.employeeUserId"/>  
        <display:column property="employeeInfo.employeeFullname" escapeXml="true" sortable="true" titleKey="employeeInfo.employeeFullName"/> 
        <display:column property="employeeInfo.employeeEmail" escapeXml="true" sortable="true" titleKey="employeeInfo.employeeEmail"/>
        <display:column property="employeeInfo.employeeDept" escapeXml="true" sortable="true" titleKey="employeeInfo.employeeDept"/>    
        <display:column property="employeeInfo.managerFullname" escapeXml="true" sortable="true" titleKey="employeeInfo.managerFullName"/>
        <display:column property="employeeInfo.managerEmail" escapeXml="true" sortable="true" titleKey="employeeInfo.managerEmail"/>
        <display:column property="employeeInfo.managerDept" escapeXml="true" sortable="true" titleKey="employeeInfo.managerDept"/>      
        <display:column property="assetRole.name" escapeXml="true" sortable="true" titleKey="assetRole.name"/>
        <display:setProperty name="paging.banner.item_name" value="assetAccessRequest"/>
        <display:setProperty name="paging.banner.items_name" value="assetAccessRequests"/>
  	</display:table>
	</div>

</c:if>