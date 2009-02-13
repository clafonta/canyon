<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetAccessRequestStatusDetail.title"/></title>
<content tag="heading"><fmt:message key="assetAccessRequestStatusDetail.heading"/></content>
<spring:bind path="assetAccessRequestStatus.*">
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
<form:form commandName="assetAccessRequestStatus" method="post" action="editAssetAccessRequestStatus.html" onsubmit="return validateAssetAccessRequestStatus(this)" id="assetAccessRequestStatusForm">
  <ul>
    <form:hidden path="id"/>
    <form:hidden path="assetAccessRequestId"/>
    <li>
      <canyon:label styleClass="desc" key="assetAccessRequestStatus.approvalUserId"/>
      <form:errors path="approvalUserId" cssClass="fieldError"/>
      <form:input path="approvalUserId" id="approvalUserId" cssClass="text medium"/>
    </li>
    <li>
      <canyon:label styleClass="desc" key="assetAccessRequestStatus.approvalStatus"/>
      <form:errors path="approvalStatus" cssClass="fieldError"/>
      <form:select path="approvalStatus" cssClass="text medium">
        <form:option value="PENDING"/>
        <form:option value="APPROVED"/>
        <form:option value="DECLINED"/>						
      </form:select>
    </li>   
    <li class="buttonBar bottom">
      <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />
      <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AssetAccessRequestStatus')" value="<fmt:message key="button.delete"/>" />
      <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<fmt:message key="button.cancel"/>" />
    </li>
  </ul>
</form:form>
<script type="text/javascript">
    Form.focusFirstElement($('assetAccessRequestStatusForm'));
</script>
<v:javascript formName="assetAccessRequestStatus" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
