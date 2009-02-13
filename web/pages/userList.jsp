<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userList.title"/></title>
    <content tag="heading"><fmt:message key="userList.heading"/></content>
    <meta name="menu" content="AdminMenu"/>
</head>

<div>
<ul>
 <li><h3><img src="<c:url value="/images/asterisk_orange.png"/>" alt=""/> To add a user, <a href="<c:url value="/employeeInfoList.html"/>">find the employee</a> and add them.</h3></li>
</ul>
</div>
<div class="group">
  <h2>Search / Filter</h2>  
    <form:form commandName="userSearchForm" method="GET" action="users.html" id="userSearchForm">  
      <table summary="" class="plain">
        <tr>
          <td><canyon:label styleClass="desc" key="user.username"/></td>
          <td><form:input path="username" id="username" cssClass="text medium"/></td>        
          <td><canyon:label styleClass="desc" key="user.firstName"/></td>
          <td><form:input path="firstName" id="firstName" cssClass="text medium"/></td>  
        </tr>
        <tr>      
          <td><canyon:label styleClass="desc" key="user.lastName"/></td>          
          <td><form:input path="lastName" id="lastName" cssClass="text medium"/></td>
          <td><canyon:label styleClass="desc" key="user.email"/></td>          
          <td><form:input path="email" id="email" cssClass="text medium"/></td>
        </tr>
      </table>
        <p class="buttonBar bottom">
          <input type="submit" class="button" name="save" value="Search" /> <a href="<c:url value="/users.html"/>">Clear Search</a>
        </p>
    </form:form>
</div>
<div>
  <h2>Results</h2>
  <display:table name="userList" cellspacing="0" cellpadding="0" requestURI="" 
      defaultsort="1" id="users" pagesize="25" class="largetable greytable" export="true">
      <display:column property="username" escapeXml="true" sortable="true" titleKey="user.username" style="width: 25%"
          url="/editUser.html?from=list" paramId="username" paramProperty="username"/>
      <display:column property="fullName" escapeXml="true" sortable="true" titleKey="activeUsers.fullName" style="width: 34%"/>
      <display:column property="email" sortable="true" titleKey="user.email" style="width: 25%" autolink="true" media="html"/>
      <display:column property="email" titleKey="user.email" media="csv xml excel pdf"/>
      <display:column sortProperty="enabled" sortable="true" titleKey="user.enabled" style="width: 16%; padding-left: 15px" media="html">
          <input type="checkbox" disabled="disabled" <c:if test="${users.enabled}">checked="checked"</c:if>/>
      </display:column>
      <display:column property="enabled" titleKey="user.enabled" media="csv xml excel pdf"/>
  
      <display:setProperty name="paging.banner.item_name" value="user"/>
      <display:setProperty name="paging.banner.items_name" value="users"/>
  
      <display:setProperty name="export.excel.filename" value="User List.xls"/>
      <display:setProperty name="export.csv.filename" value="User List.csv"/>
      <display:setProperty name="export.pdf.filename" value="User List.pdf"/>
  </display:table>
</div>
<script type="text/javascript">
    highlightTableRows("users");
</script>
