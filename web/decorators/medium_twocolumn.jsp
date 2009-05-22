<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>        
        <meta http-equiv="Cache-Control" content="no-store"/>        
        <meta http-equiv="Pragma" content="no-cache"/>        
        <meta http-equiv="Expires" content="0"/>        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
        <c:set var="ctxPath" value="${pageContext.request.contextPath}" scope="request"/>
        <meta name="author" content="chad lafontaine (chad.lafontaine@gmail.com)"/>
        <link rel="icon" href="<c:url value="/images/favicon.ico"/>"/>
        <title><decorator:title/> | <fmt:message key="webapp.name"/></title>
        <link rel="shortcut icon" href="<c:url value="/images/favicon.ico" />" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/yui/build/reset-fonts-grids/reset-fonts-grids.css" />" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/yui/build/menu/assets/skins/sam/menu.css" /> "/>     
        <link type="text/css" rel="stylesheet" href="<c:url value="/yui/build/container/assets/skins/sam/container.css" /> "/>       
        <link type="text/css" rel="stylesheet" href="<c:url value="/yui/build/calendar/assets/skins/sam/calendar.css" /> " />
        <link type="text/css" rel="stylesheet" href="<c:url value="/styles/style.css" />" />     
        <link type="text/css" rel="stylesheet" href="<c:url value="/styles/forms.css" />"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/styles/messages.css" />" /> 
		<link type="text/css" rel="stylesheet" href="<c:url value="/styles/accordion.css" />" />
        <script type="text/javascript" src="<c:url value="/yui/build/utilities/utilities.js" /> "></script>
        <script type="text/javascript" src="<c:url value="/yui/build/element/element-beta-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/yui/build/button/button-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/yui/build/container/container-min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/yui/build/menu/menu.js" /> "></script>
        <script type="text/javascript" src="<c:url value="/scripts/prototype.js" />"></script>
        <script type="text/javascript" src="<c:url value="/scripts/util.js" />"></script>    
		<script type="text/javascript" src="<c:url value="/scripts/effects.js" />"></script> 
		<script type="text/javascript" src="<c:url value="/scripts/accordion.js" />"></script>  
        <script type="text/javascript" src="<c:url value="/scripts/global.js" />"></script>
        <script type="text/javascript">
            YAHOO.util.Event.onContentReady("productsandservices", function () {
                var oMenuBar = new YAHOO.widget.MenuBar("productsandservices", { 
                                                            autosubmenudisplay: true, 
                                                            hidedelay: 750, 
                                                            lazyload: true });
                oMenuBar.render();
            });
        </script>      
		<style>
        #doc2 {padding:5px;}
        </style>
        <decorator:head/>    
    </head>
	<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/> class="yui-skin-sam">
	<div id="doc2" class="yui-t5">
	    <div id="hd">       
	        <div id="logo-right">        
	             <c:choose>
	                <c:when test="${pageContext.request.remoteUser != null}">
	                    <span><fmt:message key="user.status"/><b><authz:authentication operation="fullName"/></b> (<a href="<c:url value="/editProfile.html" />">Edit Profile</a>) </span>
	                </c:when>
	                <c:otherwise>
	                    <a href="<c:url value="/login.jsp" />">Log in</a>
	                </c:otherwise>
	            </c:choose>	            
	        </div>
	        <div id="logo-left">
	            <a href="<c:url value="/mainMenu.html" />"><img  src="<c:url value="/images/logo_canyon_fffacd_40x40.png" />" border="0" alt="Canyon"  /></a> <p class="logo-text"><strong><fmt:message key="webapp.name"/></strong> <fmt:message key="webapp.subtitle"/></p>            
	        </div>      
	        <jsp:include page="/common/navmenu.jsp"/>
	    </div>
		  <div id="bd">     
          <div id="yui-main">    
      		    <div class="yui-b">
      		        <%@ include file="/common/messages.jsp" %>
      		        <h1><decorator:getProperty property="page.heading"/></h1>
      		        <decorator:body/>        
      		    <!--  PUT THE FOLLOWING AT THE END OF A PAGE TO GET THE SIDEBAR -->
              <!-- ---------------- BEGIN ---------------- -->
              <!--
              </div>              
      		</div>
          <div class="yui-b">
                 SIDEBAR NAV LOGIC HERE        			
          </div>              
              -->
              <!-- ----------------- END ---------------- -->
      </div>
	    <div id="ft">
	        <jsp:include page="/common/footer.jsp"/>
	    </div>
	</div>
	</body>
</html>

