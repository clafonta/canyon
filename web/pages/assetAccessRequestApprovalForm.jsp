<%@ include file="/common/taglibs.jsp"%>
<!-- BEGIN -->
<script type='text/javascript' src='/canyon/dwr/interface/assetRoleInfoDWRManager.js'></script>
<script type='text/javascript' src='/canyon/dwr/engine.js'></script>
<script type='text/javascript' src='/canyon/dwr/util.js'></script>
<title><fmt:message key="assetAccessRequestApprovalForm.title"/></title>
<content tag="heading"><fmt:message key="assetAccessRequestApprovalForm.heading"/></content>
<c:if test="${assetAccessRequestStatus.id != null}">
	<div class="groupformcenter">
    <div class="message">
      <ul>
          <li class="fieldset">          
              <table id="plain">
                <tbody>
                  <tr>
                    <th><canyon:label styleClass="condense" key="employeeInfo.employeeUserId"/></th>
                    <td><span class="condense"><c:out value="${assetAccessRequestStatus.assetAccessRequest.employeeInfo.employeeUserId}"/></span></td>
                    <th><canyon:label styleClass="condense" key="employeeInfo.employeeFullName"/></th>
                    <td><span class="condense"><c:out value="${assetAccessRequestStatus.assetAccessRequest.employeeInfo.employeeFullname}"/></span></td>
                 </tr>
                 <tr>
                    <th><canyon:label styleClass="condense" key="assetAccessRequest.assetName"/></th>
                    <td><span class="condense"><c:out value="${assetAccessRequestStatus.assetAccessRequest.assetRole.assetDetail.assetName}"/></span></td>
                    <th><canyon:label styleClass="condense" key="assetAccessRequest.assetRole"/></th>
                    <td><span class="condense"><c:out value="${assetAccessRequestStatus.assetAccessRequest.assetRole.name}"/></span></td>
                 </tr>
                 <tr>
                    <th><canyon:label styleClass="condense" key="assetAccessRequestStatus.approvalUserId"/></th>
                    <td><span class="condense"><c:out value="${assetAccessRequestStatus.approvalUserId}"/></span></td>
                    <th></th>
                    <td></td>
                 </tr>
               </tbody>
              </table>
          </li>
        </ul>
    </div>
		<form:form commandName="assetAccessRequestStatus" method="post" action="assetAccessRequestApproval.html" onsubmit="return validateAssetAccessRequestStatus(this)" id="assetAccessRequestStatus">
		<form:hidden path="id"/>
		  <ul>        
		    <li>
		      <canyon:label styleClass="desc" key="assetAccessRequestStatus.approvalStatus"/>
		      <form:errors path="approvalStatus" cssClass="status"/>		      
           <select id="status" name="status" class="text medium">
               <option value="OTHER" <c:if test="${(assetAccessRequestStatus.approvalStatus ne 'APPROVED') and (assetAccessRequestStatus.approvalStatus ne 'DECLINED')}">selected="selected"</c:if> >- Select -</option>
               <option value="APPROVED" <c:if test="${assetAccessRequestStatus.approvalStatus eq 'APPROVED'}">selected="APPROVED"</c:if>>APPROVED</option>
               <option value="DECLINED" <c:if test="${assetAccessRequestStatus.approvalStatus eq 'DECLINED'}">selected="DECLINED"</c:if>>DECLINED</option>
            </select> 
		    </li>
		    <li>
		      <canyon:label styleClass="desc" key="assetAccessRequestStatus.comment"/>
		      <form:errors path="comment" cssClass="fieldError"/>
		      <form:textarea path="comment" id="comment" cssClass="text large"/>
		    </li>
		     <li class="buttonBar bottom">
		       <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
		     </li>
	  </ul>
	</form:form>	
	<v:javascript formName="assetAccessRequestStatus" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
	<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
  </div>
</c:if>