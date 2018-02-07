<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 20.12.2017
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Edit User</title>
    <style type="text/css">
        .error { color: red; }
    </style>

    <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h5>User: ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h5>

    </c:if>

</div>

<p><a href="${pageContext.request.contextPath}/admin/showUsers">Back to Users</a></p>

<form action="<c:url value="/admin/editUser"/>" method="POST">
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    <input type="hidden" name="userId" value="${user.id}"/>
    <table>
        <tr>
            <th>Edit user:</th>
            <th><i><u>${user.username}</u></i></th>
        </tr>
    </table>
    <p></p>
    <table>
        <tr>
            <td>Username</td>
            <td>
                <input type="text" name="username" value="${user.username}" size="30">
                <span class="error">${messages.username}</span>
            </td>
        </tr>
        <tr>
            <td>First name</td>
            <td>
                <input type="text" name="firstName" value="${user.firstName}" size="30">
            </td>
        </tr>
        <tr>
            <td>Last name</td>
            <td>
                <input type="text" name="lastName" value="${user.lastName}" size="30">
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td>
                <input type="text" name="email" value="${user.email}" size="30">
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <input type="password" name="password" size="30">
                <span class="error">${messages.password}</span>
            </td>
        </tr>
        <tr>
            <td>Admin</td>
            <td>
                <c:set var="isAdmin" value="False" />
                <c:forEach var="role" items="${user.roles}">
                    <c:choose>
                        <c:when test="${role.name == 'ROLE_ADMIN'}">
                            <c:set var="isAdmin" value="True" />
                        </c:when>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${isAdmin == 'True'}">
                        <input type="checkbox" name="admin" value="true" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="admin" value="true">
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

        <tr>
            <td></td>
        </tr>

        <tr>
            <td><input type="submit" value="Save" name="Save"/></td>
            <td><input type="submit" value="Cancel" name="Cancel"/></td>
        </tr>
    </table>
</form>

<p><a href="${pageContext.request.contextPath}/admin/showUsers">Back to Users</a></p>

</body>
</html>
