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
        <div>
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <div class="list-group">
            <li class="list-group-item">
                User: ${pageContext.request.userPrincipal.name}
            </li>
            <li class="list-group-item">
                <a class="btn btn-sm btn-primary btn-block" type="submit" onclick="document.forms['logoutForm'].submit()">Sign Out</a>
            </li>
        </div>
    </c:if>
</div>
<div>
    <c:if test="${not empty pageContext.request.userPrincipal}">
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <p class="lead">Admin</p>
            <div class="list-group">
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/users">Users</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/members">Donors</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/beneficiaries">Beneficiaries</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/projects">Projects</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/transactions">Transactions</a>
                </li>
            </div>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_FU')}">
            <p class="lead">FU</p>
            <div class="list-group">
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/members">Donors</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/beneficiaries">Beneficiaries</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/projects">Projects</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/transactions">Transactions</a>
                </li>
            </div>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
            <p class="lead">Field Contact</p>
            <div class="list-group">
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/beneficiaries">Beneficiaries</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/projects">Projects</a>
                </li>
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/transactions">Transactions</a>
                </li>
            </div>
        </c:if>
    </c:if>
</div>