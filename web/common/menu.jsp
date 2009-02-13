<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="WEB-INF/classes/cssHorizontalMenu.vm" permissions="rolesAdapter">
<ul id="primary-nav" class="menuList">
    <li class="pad">&nbsp;</li>    
    <c:if test="${empty pageContext.request.remoteUser}">
        <li><a href="<c:url value="/login.jsp"/>" style="width: 125px;" class="<c:if test="${currentMenu != 'AssetAccessRequestMenu'}">current</c:if>"><fmt:message key="login.title"/></a></li>
        <li><a href="<c:url value="/assetAccessRequests.html"/>" style="width: 125px;" class="<c:if test="${currentMenu == 'AssetAccessRequestMenu'}">current</c:if>"><fmt:message key="assetAccessRequestList.title"/></a></li>
  	</c:if>
    <menu:displayMenu name="MainMenu"/>    
    <!-- <menu:displayMenu name="FileUpload"/>  -->
    <menu:displayMenu name="AssetMenu"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    
    <!--AssetAccessRequestStatus-START-->
    <menu:displayMenu name="AssetAccessRequestStatusMenu"/>
    <!--AssetAccessRequestStatus-END-->
    <!--EmployeeInfo-START-->
    <menu:displayMenu name="EmployeeInfoMenu"/>
    <!--EmployeeInfo-END-->
   
   
</ul>





</menu:useMenuDisplayer>