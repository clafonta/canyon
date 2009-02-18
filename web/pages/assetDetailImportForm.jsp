<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailImport.heading"/></title>

<div class="groupformcenter" style="padding-top:5px;">	
<p style="margin-top:20px; margin-bottom:20px;"><fmt:message key="assetDetailImport.datainfo"/></p>
  <table summary="" class="greytable greytable_lined">
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
<h3><a href="<c:url value="/sample.csv"/>"><fmt:message key="assetDetailImport.example"/><img src="<c:url value="/images/ico_file_csv.png"/>" alt=""></a></h3>
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