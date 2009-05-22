<%@ include file="/common/taglibs.jsp"%>
<div id="productsandservices" class="yuimenubar yuimenubarnav">
    <div class="bd">
        <ul class="first-of-type">
            <c:choose>
	            <c:when test="${empty pageContext.request.remoteUser}">
	               <li class="yuimenubaritem-hassubmenu-selected first-of-type"><a class="yuimenubaritemlabel" href="<c:url value="/login.jsp"/>">Login</a></li>	               
								 <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#management">Asset Access Request</a>
		                <div id="shopping" class="yuimenu">
		                    <div class="bd">                    
		                        <ul>
		                            <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/assetDetailList.html" />">See all Assets <b>|</b> Request for Access</a></li>
		                            <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/assetAccessRequests.html" />">Access Request Queue</a></li>                            
		                        </ul>
		                    </div>
		                </div>                                
		            </li>								      
				</c:when>
				<c:otherwise>
		            <li class="yuimenubaritem-hassubmenu-selected first-of-type"><a class="yuimenubaritemlabel" href="<c:url value="/mainMenu.html"/>">Home</a>                
		                <div id="communication"  class="yuimenu">
		                    <div class="bd">
		                        <ul>
		                            <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/editProfile.html"/>">User Profile</a></li>
		                        </ul>
		                    </div>
		                </div>    
		            </li>
		            <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#management">Asset Management</a>
		                <div id="shopping" class="yuimenu">
		                    <div class="bd">                    
		                        <ul>
                                  <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/assetDetailList.html" />">See all Assets <b>|</b> Request for Access</a></li>
                                  <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/assetdetail/csv/import.html" />">Import Assets</a></li>
                                  <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/assetTypes.html" />">Asset Types</a></li>
                                  <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/assetAccessRequests.html" />">Access Request Queue</a></li> 		                            
		                        </ul>
		                    </div>
		                </div>                                
		            </li>
		            <authz:authorize ifAnyGranted="admin">
			            <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#administration">Administration</a>
			                <div id="entertainment" class="yuimenu">
			                    <div class="bd">                    
			                        <ul>
			                            <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/users.html"/>">Application Users</a></li>
			                            <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/activeUsers.htm"/>">Active Sessions</a></li>
			                            <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/reload.html"/>">Reload</a></li>
			                            <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/flushCache.html"/>">Flush Cache</a></li>  
			                            <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/clickstreams.jsp"/>">Clickstreams</a></li>    
                                  <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<c:url value="/employeeInfoList.html" />">Employee Information</a></li>                        
			                        </ul>                    
			                    </div>
			                </div>
			            </li>    
		            </authz:authorize>  
		            <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="<c:url value="/logout.jsp"/>">Logout</a></li>  
                </c:otherwise>		                
            </c:choose>
        </ul>            
    </div>
</div>