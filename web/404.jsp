<%@ include file="/common/taglibs.jsp"%>

<page:applyDecorator name="medium_onecolumn">

<title><fmt:message key="404.title"/></title>
<content tag="heading"><fmt:message key="404.title"/></content>
<div>
  <p>
      <fmt:message key="404.message">
          <fmt:param><c:url value="/mainMenu.html"/></fmt:param>
      </fmt:message>
  </p>
	<br/>
	<br/>
</div>
</page:applyDecorator>
