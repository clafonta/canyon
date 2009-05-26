<%@ include file="/common/taglibs.jsp"%>
<title><fmt:message key="assetDetailImport.heading"/></title>
<div>
  <p style="margin-top:20px; margin-bottom:20px;"><fmt:message key="assetDetailImport.blurb"/></p>
  <form method="POST" action="<c:url value="/assetdetail/csv/import.html"/>">
	   <input type="hidden" name="task" value="confirm"/>
		 <textarea name="csvinput" class="extralarge" ></textarea>
     <div class="buttons" style="align:right; padding-top:20px;">
		   <button type="submit" class="positive"><img src="<c:url value="/images/table_add.png"/>" alt="" styleClass="icon"/> <fmt:message key="assetDetailImport.import"/></button>
 		 </div>
  </form>
</div>
<div class="group" style="margin-top:20px;width:50%;">	
<p style="padding-top:20px; margin-bottom:20px;"><fmt:message key="assetDetailImport.datainfo"/></p>
  <table summary="" class="greytable" style="font-family:Courier New, sans-serif; font-size: 0.8em; word-spacing: -0.25em; background:#ffffff; padding:15px;">
    <thead><tr><th>Column</th><th>Length</th><th>Type</th></tr></thead>
	<tbody>
	<tr><td><fmt:message key="assetDetail.assetName"/></td><td>100</td><td>VARCHAR</td></tr>
	</tbody>
</table>
<h3><a href="<c:url value="/sample.csv"/>"><fmt:message key="assetDetailImport.example"/><img src="<c:url value="/images/ico_file_csv.png"/>" alt=""></a></h3>
</div>