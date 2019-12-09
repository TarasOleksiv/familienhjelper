<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="thumbnail">
    <img src="${contextPath}/resources/img/logo.jpg">
</div>
<div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h5>${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h5>

    </c:if>
</div>
<div>
    <c:if test="${not empty pageContext.request.userPrincipal}">
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <h4>Admin</h4>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/showUsers">Users</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/showUsers" target="targetframe">Members</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/showUsers">Projects</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/showUsers">Beneficiary</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/showUsers">Transactions</a>
                </li>
            </ul>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_FU')}">
            <h4>FU</h4>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/showUsers">Projects</a>
                </li>
            </ul>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
            <h4>Field Contact</h4>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/showUsers">Projects</a>
                </li>
            </ul>
        </c:if>
    </c:if>
</div>