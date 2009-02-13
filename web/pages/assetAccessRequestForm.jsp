<%@ include file="/common/taglibs.jsp"%>
<!-- BEGIN -->
<script type='text/javascript' src='/canyon/dwr/interface/assetRoleInfoDWRManager.js'></script>
<script type='text/javascript' src='/canyon/dwr/engine.js'></script>
<script type='text/javascript' src='/canyon/dwr/util.js'></script>
<title><fmt:message key="assetAccessRequestDetail.title"/></title>
<content tag="heading"><fmt:message key="assetAccessRequestDetail.heading"/></content>
<meta name="menu" content="AssetAccessRequestMenu"/>
<spring:bind path="assetAccessRequest.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/application_error.png"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>
<form:form commandName="assetAccessRequest" method="post" action="editAssetAccessRequest.html" onsubmit="return validateAssetAccessRequest(this)" id="assetAccessRequestForm">
  <form:hidden path="id"/>
  <div>
    <c:url var="viewAssetDetailURL" value="/viewAssetDetail.html">
	  <c:param name="id" value="${assetDetail.id}"/>  
	</c:url>
	<h2>Asset: <a class="focus" href="<c:out value="${viewAssetDetailURL}"/>"><c:out value="${assetDetail.assetName}"/></a></h2>
  </div>
  <div class="groupformcenter">  
	  <input type="hidden" name="assetDetailId" value="<c:out value="${assetDetail.id}"/>"/>	  
	  
	  	  <h2 class="splitter"><fmt:message key="assetAccessRequestForm.step1.blurb"/></h2>
        <div class="splitter">
	          <canyon:label styleClass="desc" key="assetAccessRequest.employeeEmail"/>
	          <input id="employeeEmail" name="employeeEmail" class="text large" type="text" value="<c:out value="${assetAccessRequest.employeeInfo.employeeEmail}"/>"/>
	  		</div>	  	
	  	  <h2 class="splitter"><fmt:message key="assetAccessRequestForm.step2.blurb"/></h2>
        <div class="splitter">			 
  				<table class="greytable ">
  					<c:forEach items="${assetDetail.assetRoles}" var="item">
  						<tr class="greytable_lined">
  							<td align="right" style="vertical-align:middle;padding:0 8px 0 8px;" >
  							  <input type="radio" name="assetRoleId" value="<c:out value="${item.id}"/>" <c:if test="${item.id eq assetAccessRequest.assetRoleId}">checked="checked"</c:if>/>
  							</td>
  							<td style="width:100px;vertical-align:middle;" valign="center" align="center">
  							  <strong><c:out value="${item.name}"/></strong>
  							</td>
  							<td style="vertical-align:middle;padding:5px;" ><c:out value="${item.description}"/></td>
  						</tr>           
  					</c:forEach>         
  				</table>
			  </div>		
	     
	    
	     
	       <authz:authorize ifAnyGranted="admin, user">
	          <c:if test="${assetAccessRequest.id != null}">
	            <form:checkbox path="complete" id="complete"/>
	            <label for="complete" class="choice"><fmt:message key="assetAccessRequest.complete"/></label> 
	          </c:if>     	          
	       </authz:authorize> 
	       <authz:authorize ifNotGranted="admin, user">            
	            <c:choose>
	              <c:when test="${assetAccessRequest.complete && (assetAccessRequest.id != null) }">
	                  <label for="complete" ><fmt:message key="assetAccessRequest.completed"/></label>	 
	              </c:when>
	              <c:when test="${!assetAccessRequest.complete && (assetAccessRequest.id != null)}">
	                 <label for="complete" class="focus"><fmt:message key="assetAccessRequest.incomplete"/></label>
	              </c:when>
	              <c:otherwise>
	              
	              </c:otherwise>
	            </c:choose>                     
	       </authz:authorize>  
	     
	     <ul>
			 <li class="buttonBar bottom">
	     <c:if test="${assetAccessRequest.id eq null}">
	       <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.submit"/>" />
	     </c:if>
	       <c:if test="${assetAccessRequest.id != null}">
	       <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
               <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AssetAccessRequest')" value="<fmt:message key="button.delete"/>" />
               </c:if>    
	       <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
	     </li>
	  </ul>	  
	</div>
</form:form>
<c:if test="${not empty assetAccessRequestForm.id}">
  <h3 class="focus"><fmt:message key="assetAccessRequestForm.approverlist.blurb"/></h3>
  <authz:authorize ifAnyGranted="admin, user">
    <div>
      <ul>
    	  <li>	
          <c:url var="addAssetAccessRequestStatusURL" value="/editAssetAccessRequestStatus.html">
            <c:param name="assetAccessRequestId" value="${assetAccessRequest.id}"/>	
          </c:url>
          <a href="<c:out value="${addAssetAccessRequestStatusURL}"/>"><h3><img src="<c:url value="/images/plus.gif"/>" alt="Add Approver"/> <u>Add Approver</u></h3></a>
    		</li>
    	</ul>
    </div>
  </authz:authorize>
  <c:choose>
      <c:when test="${empty assetAccessRequest.assetAccessRequestStatusList}">
        <p class="mediumbuffer"><fmt:message key="assetAccessRequestForm.approvermanager.blurb"/></p>
      </c:when>
      <c:otherwise>
      <div>        		 
  	    <table class="table large" style="width:700px;">
  	     <thead>
  	  	 <tr><th>Approver</th><th colspan="2">Status</th></tr>
  	  	 </thead>
  	  	 <tbody>       
  				<c:forEach items="${assetAccessRequest.assetAccessRequestStatusList}" var="item">
  					<tr style="background:#FFFFFF;">    						    
  						<td style="width:100px;"><strong><c:out value="${item.approvalUserId}"/></strong></td>    						    
  						<td style="width:100px;">
  						  <strong><c:out value="${item.approvalStatus}"/></strong>
  							<authz:authorize ifAnyGranted="admin, user">
  								<c:url var="editAssetAccessRequestStatusURL" value="/editAssetAccessRequestStatus.html">
  								  <c:param name="id" value="${item.id}"/>	
  								</c:url>
  								<span style="padding:0 0 0 20em;"><a href="<c:out value="${editAssetAccessRequestStatusURL}"/>">Edit</a></span>
  							</authz:authorize>
  						</td>						
  					</tr>
  				</c:forEach>        
  	  		<tbody>
  	    </table>					
      </div>		
    </c:otherwise>
  </c:choose>
</c:if>
<v:javascript formName="assetAccessRequest" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>