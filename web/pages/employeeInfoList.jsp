<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="employeeInfoList.title"/></title>
<content tag="heading"><fmt:message key="employeeInfoList.heading"/></content>
<meta name="menu" content="EmployeeInfoMenu"/>

<div class="group">
  <h2>Search / Filter</h2>  
    <form:form commandName="employeeInfoForm" method="GET" action="employeeInfoList.html" id="employeeInfoForm">  
      <table summary="" class="plain">
        <tr>
          <td><canyon:label styleClass="condense" key="employeeInfo.employeeUserId"/></td>
          <td><canyon:label styleClass="condense" key="employeeInfo.employeeFirstName"/></td>        
          <td><canyon:label styleClass="condense" key="employeeInfo.employeeDept"/></td>
          <td><canyon:label styleClass="condense" key="employeeInfo.employeeLastName"/></td>
        </tr>
        <tr>
          <td><form:input path="employeeUserId" id="employeeUserId" cssClass="text medium"/></td>          
          <td><form:input path="employeeFirstName" id="employeeFirstName" cssClass="text medium"/></td>
          <td><form:input path="employeeDept" id="employeeDept" cssClass="text medium"/></td>
          <td><form:input path="employeeLastName" id="employeeLastName" cssClass="text medium"/></td>
        </tr>
      </table>
        <p class="buttonBar bottom">
          <input type="submit" class="button" name="save" value="Search" /> <a href="<c:url value="/employeeInfoList.html"/>">Clear Search</a>
        </p>
    </form:form>
</div>
<div>
<h2>Results</h2>
   
<display:table name="employeeInfoList" cellspacing="0" cellpadding="0" id="employeeInfo" pagesize="25" class="largetable greytable employeeInfoList" export="false">
  	 
		<display:column titleKey="employeeInfo.employeeUserId"  class="small "  sortable="true" >
				 <a	class="tooltip" href="<c:url value="/employeeInfoList.html?view=detail" />&employeeUserId=<c:out value="${employeeInfo.employeeUserId}" />">
	 		<c:out value="${employeeInfo.employeeUserId}" />
     <span>
		 <table>
				
		   <tr><td><canyon:label styleClass="condense" key="employeeInfo.employeeUserId"/></td><td><c:out value="${employeeInfo.employeeUserId}"/></td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.employeeFullName"/></td><td><c:out value="${employeeInfo.employeeFullname}"/></td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.employeeEmail"/></td><td><c:out value="${employeeInfo.employeeEmail}"/></td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.employeeStatus"/></td><td>
			  <c:choose>
                      <c:when test="${employeeInfo.employeeType == 'E'}">
                        Employee
                      </c:when>
                      <c:otherwise>
                        Non-employee
                      </c:otherwise>
                    </c:choose>
                    /
                    <c:choose>
                      <c:when test="${employeeInfo.employeeStatus == 'A'}">
                        Active
                      </c:when>
                      <c:otherwise>
                        Non-active
                      </c:otherwise>
                    </c:choose>
			 </td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.employeeDept"/></td><td><c:out value="${employeeInfo.employeeDept}"/></td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.managerUserId"/></td><td><c:out value="${employeeInfo.manager.employeeUserId}"/></td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.managerName"/></td><td><c:out value="${employeeInfo.manager.employeeFullname}"/></td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.managerEmail"/></td><td><c:out value="${employeeInfo.manager.employeeEmail}"/></td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.managerStatus"/></td><td>
			  <c:choose>
                      <c:when test="${employeeInfo.manager.employeeType == 'E'}">
                        Employee
                      </c:when>
                      <c:otherwise>
                        Non-employee
                      </c:otherwise>
                    </c:choose>
                    /
                    <c:choose>
                      <c:when test="${employeeInfo.manager.employeeStatus == 'A'}">
                        Active
                      </c:when>
                      <c:otherwise>
                        Non-active
                      </c:otherwise>
                    </c:choose>
			 </td></tr>
			 <tr><td><canyon:label styleClass="condense" key="employeeInfo.managerDept"/></td><td><c:out value="${employeeInfo.manager.employeeDept}"/></td></tr>			
		 </table>   
		 </span></a> 
		</display:column>
    <display:column property="employeeDept" escapeXml="true" sortable="true" titleKey="employeeInfo.employeeDept"/>    
    <display:column property="employeeFirstName" escapeXml="true" sortable="true" titleKey="employeeInfo.employeeFirstName"/>
    <display:column property="employeeLastName" escapeXml="true" sortable="true" titleKey="employeeInfo.employeeLastName"/>
    <display:column property="manager.employeeFullname" escapeXml="true" sortable="true" titleKey="employeeInfo.managerName"/>
		<display:column property="manager.employeeUserId" escapeXml="true" sortable="true" titleKey="employeeInfo.managerUserId"/>
		<authz:authorize ifAnyGranted="admin, user">   
		  <display:column sortable="false">
                <c:url value="editUser.html" var="editUserUrl">
                    <c:param name="username" value="${employeeInfo.employeeUserId}" />
                    <c:param name="employeeEmail" value="${employeeInfo.employeeEmail}" />
                    <c:param name="employeeFirstName" value="${employeeInfo.employeeFirstName}" />
                    <c:param name="employeeLastName" value="${employeeInfo.employeeLastName}" />
                    <c:param name="from" value="list" />
                    <c:param name="method" value="Add" />
                </c:url>
                <a href="<c:out value="${editUserUrl}"/>"><img src="<c:url value="/images/key_add.png"/>" alt="Grant Access"/></a>
		  </display:column>           
    </authz:authorize>   
    <display:setProperty name="paging.banner.item_name" value="employee"/>
    <display:setProperty name="paging.banner.items_name" value="employeeInfos"/>
</display:table>
</div>
<script type="text/javascript">
    highlightTableRows("employeeInfoList");
</script>
