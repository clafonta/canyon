<%@ include file="/common/taglibs.jsp" %>
    <div id="divider"><div></div></div>
    <div style="padding:0 0 2em 0;">
      <span class="left">Wired together with AppFuse Version 1.9.4           
          <c:if test="${pageContext.request.remoteUser != null}">
          | <fmt:message key="user.status"/> <authz:authentication operation="fullName"/>
          </c:if>
      </span>
      <span class="right">
          &copy; 2009 <a href="http://www.thelaboriouslabs.com"><fmt:message key="webapp.team"/></a>
      </span>
    </div>
    <!-- Built on Tuesday Jul 01, 2008 at 02:59 PM PDT by U159282 -->
