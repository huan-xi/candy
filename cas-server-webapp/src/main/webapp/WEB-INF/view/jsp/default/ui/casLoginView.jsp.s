<jsp:directive.include file="includes/top.jsp" />
<%@taglib prefix="huanxi" uri="/WEB-INF/mytags.tld" %>
<div>
    {"status":"login","execution":"${flowExecutionKey}","lt":"${loginTicket}","_eventId":"submit"<c:if test="${pageContext.request.method eq 'POST'}">,"msg":"<huanxi:jsonError/></c:if>"}
</div>
<div class="box" id="login">
    <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
        <huanxi:jsonError/>

        <h2><spring:message code="screen.welcome.instructions" /></h2>
        <section class="row">
            <label for="username"><spring:message code="screen.welcome.label.netid" /></label>
            <c:choose>
                <c:when test="${not empty sessionScope.openIdLocalId}">
                    <strong><c:out value="${sessionScope.openIdLocalId}" /></strong>
                    <input type="hidden" id="username" name="username" value="<c:out value="${sessionScope.openIdLocalId}" />" />
                </c:when>
                <c:otherwise>
                    <spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
                    <form:input cssClass="required" cssErrorClass="error" id="username" size="25" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />
                </c:otherwise>
            </c:choose>
        </section>

        <section class="row">
            <label for="password"><spring:message code="screen.welcome.label.password" /></label>
                <%--
                NOTE: Certain browsers will offer the option of caching passwords for a user.  There is a non-standard attribute,
                "autocomplete" that when set to "off" will tell certain browsers not to prompt to cache credentials.  For more
                information, see the following web page:
                http://www.technofundo.com/tech/web/ie_autocomplete.html
                --%>
            <spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
            <form:password cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
            <span id="capslock-on" style="display:none;"><p><img src="images/warning.png" valign="top"> <spring:message code="screen.capslock.on" /></p></span>
        </section>
        <section class="row">
            <img src="captcha.jpg" onclick="this.src='captcha.jpg?'+Math.random()"  alt="">
            <form:input tabindex="3" path="captcha" placeholder="验证码" htmlEscape="true" maxlength="6" size="15"/>
        </section>

        <section class="row btn-row">
            <input type="hidden" name="lt" value="${loginTicket}" />
            <input type="hidden" name="execution" value="${flowExecutionKey}" />
            <input type="hidden" name="_eventId" value="submit" />
            <input class="btn-submit" name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="6" type="submit" />
            <input class="btn-reset" name="reset" accesskey="c" value="<spring:message code="screen.welcome.button.clear" />" tabindex="7" type="reset" />
        </section>
    </form:form>
</div>
