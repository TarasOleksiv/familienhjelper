<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manufacturers & Products</title>
    <style type="text/css">
        td.pad { padding: 10px; }
    </style>

    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h5>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h5>

    </c:if>


<h1>Manufacturers and Products</h1>

<table>
    <tr>
        <td class="pad"><a href="${pageContext.request.contextPath}/showManufacturers"><h3>Manufacturers</h3></a></td>
        <td class="pad"><a href="${pageContext.request.contextPath}/showProducts"><h3>Products</h3></a></td>
        <c:if test="${not empty pageContext.request.userPrincipal}">
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <td class="pad"><a href="${pageContext.request.contextPath}/admin/showUsers"><h3>Users</h3></a></td>
            </c:if>
        </c:if>
    </tr>
</table>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
