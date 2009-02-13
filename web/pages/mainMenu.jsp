<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="mainMenu.title"/></title>
<div>
  <div id="helpkey">
  <h1><a href="<c:url value="/assetDetailList.html"/>"<img src="<c:url value="/images/catalog_100x100.png"/>" alt=""/>Asset Catalog</h1></a>  
  This application contains a listing of digital assets and their details. Details include type of OS, who owns it, who is the administrator and what the asset is used for. 
	A digital asset can be a server, database, web application, or domain. <a href="<c:url value="/assetDetailList.html"/>"><strong>Find Asset</strong></a>
  </div>
	<div id="helpkey">
  <h1><img src="<c:url value="/images/continuous_100x100.png"/>" alt=""/>Daily Checkpoints</h1>  
  We get a daily update on people and your organization. If an employee leaves the company or changes organization, we'll automatically notify
	the asset administrator or owner to make the necessary changes.
  </div>
	<div id="helpkey">
  <h1><img src="<c:url value="/images/audit_review100x100.png"/>" alt=""/>Audit Review</h1>  
  This application supports the ability to store audit review information on each asset. Audit reviews are used to ensure proper seperation of controls and security measures are in place.     
  </div>
</div>