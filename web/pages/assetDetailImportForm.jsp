<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailImport.heading"/></title>
<p style="margin-top:20px; margin-bottom:20px;"><fmt:message key="assetDetailImport.datainfo"/></p>
<div class="splitter " style="margin-bottom:20px;">	
  <table summary="" class="greytable">
    <thead><tr><th>Column</th><th>Length</th><th>Type</th></tr></thead>
	<tbody>
	<tr><td><fmt:message key="assetDetail.assetName"/></td><td>100</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.assetGroupName"/></td><td>100</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.assetDescription"/></td><td>2000</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.assetUsageType"/></td><td>100</td><td>VARCHAR</td></tr>
  <tr><td><fmt:message key="assetDetail.assetLocation"/></td><td>100</td><td>VARCHAR</td></tr>
  <tr><td><fmt:message key="assetDetail.assetAddress"/></td><td>100</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.assetAdminTeamName"/></td><td>50</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.assetAdminTeamEmail"/></td><td>50</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.primaryAdminEmployeeId"/></td><td>50</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.secondaryAdminEmployeeId"/></td><td>50</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.primaryOwnerEmployeeId"/></td><td>50</td><td>VARCHAR</td></tr>
	<tr><td><fmt:message key="assetDetail.secondaryOwnerEmployeeId"/></td><td>50</td><td>VARCHAR</td></tr>
	</tbody>
</table>
</div>
<div>
<h3><fmt:message key="assetDetailImport.example"/></h3>
<textarea style="height:5em;width:700px;wrap:off; overflow: auto;  overflow-x: auto; overflow-y: scroll;">
app01.hosting.domain.net, Big App, Lorem ipsum salt blah, PROD,Enterprise Data Center, 10.101.01.01, DL@domain.net,Big App Admins,UID_3,UID_4,UID_5,UID_6
app02.hosting.domain.net, Big App, Lorem ipsum salt blah, PROD,Enterprise Data Center, 10.101.01.01, DL@domain.net,Big App Admins,UID_3,UID_4,UID_5,UID_6
</textarea>

</div>
<div>
  <p style="margin-top:20px; margin-bottom:20px;"><fmt:message key="assetDetailImport.blurb"/></p>
  <form method="POST" action="<c:url value="/assetdetail/csv/import.html"/>">
	      <input type="hidden" name="task" value="confirm"/>
				<textarea name="csvinput" class="extralarge" ></textarea>
     							<div class="buttons" style="align:right; padding-top:20px;">
											   						<button type="submit" class="positive"><img src="<c:url value="/images/table_add.png"/>" alt="<fmt:message key="icon.note_go"/>" styleClass="icon"/> Import</button>
 		 							</div>
  </form>
</div>