<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<div class="thumbnail">
    <img src="${contextPath}/resources/img/logo.jpg">
</div>
<div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <div>
            <h5>User: ${pageContext.request.userPrincipal.name}</h5>
        </div>
        <a class="btn btn-sm btn-primary btn-block" type="submit" onclick="document.forms['logoutForm'].submit()">Sign Out</a>


    </c:if>
</div>
<div>
    <c:if test="${not empty pageContext.request.userPrincipal}">
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <h4>Admin</h4>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/users">Users</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/users">Members</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/users">Projects</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/users">Beneficiaries</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/users">Transactions</a>
                </li>
            </ul>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_FU')}">
            <h4>FU</h4>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/users">Projects</a>
                </li>
            </ul>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
            <h4>Field Contact</h4>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/users">Projects</a>
                </li>
            </ul>
        </c:if>
    </c:if>
</div>