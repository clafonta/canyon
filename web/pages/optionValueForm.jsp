<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="optionValueDetail.title"/></title>
<content tag="heading"><fmt:message key="optionValueDetail.heading"/> </content>
<div class="largebuffer">
  <c:url var="assetAttributeURL" value="/editAssetAttribute.html">
    <c:param name="id" value="${optionValue.assetAttribute.id}"/>
	<c:param name="view" value="true"/>
  </c:url>
	<a href="<c:out value="${assetAttributeURL}" />">Back to <c:out value="${optionValue.assetAttribute.name}" /></a>
</div>
<spring:bind path="optionValue.*">
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
<div class="groupformcenter">
  <form:form commandName="optionValue" method="post" action="editOptionValue.html" onsubmit="return validateOptionValue(this)" id="optionValueForm">
    <ul>  
    <form:hidden path="id"/>
    <input type="hidden" name="assetAttributeId" value="<c:out value="${optionValue.assetAttribute.id}" />" />
        <li>
            <canyon:label styleClass="desc" key="optionValue.value"/>
            <form:errors path="value" cssClass="fieldError"/>
            <form:input path="value" id="name" cssClass="text large"/>
        </li>
        
        <li class="buttonBar bottom">
            <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<fmt:message key="button.save"/>" />        
    				<c:if test="${not empty optionValue.id}">				
    				    <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('OptionValue')" value="<fmt:message key="button.delete"/>" />
    				</c:if>
            <a href="<c:out value="${assetAttributeURL}" />">Cancel</a>
        </li>
    </ul>
  </form:form>

</div>
<script type="text/javascript">
    Form.focusFirstElement($('optionValueForm'));
</script>

<v:javascript formName="optionValue" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>

<!-- FOLLOWING BELONGS TO TWO COLUMN TEMPLATE. -->
</div>    <!-- closes yui-b (main body content)-->          
</div>    <!-- closes yui-main (main body) -->
<!-- SIDEBAR -->
<div class="yui-b"> 
  <h2>Sidebar</h2>
			<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </p>
			<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. </p>
</div>	  
<!-- closes SIDEBAR -->
