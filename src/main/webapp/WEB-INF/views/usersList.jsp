<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 19.12.2017
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Users</title>
    <style type="text/css">
        tr.grey { background: lightgrey;}
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

<p><a href="${pageContext.request.contextPath}">Back to the main page</a></p>

<form action="<c:url value="/admin/showUsers"/>" method="POST">
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
<table border="1">
    <CAPTION>List of Users</CAPTION>
    <tr class="grey">
        <th></th>
        <th>Id</th>
        <th>Username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Admin</th>
    </tr>
    <c:forEach items="${list}" var="list">
        <tr>
            <th><input type="radio" name="userId" value="${list.id}"></th>
            <th>${list.id}</th>
            <th>${list.username}</th>
            <th>${list.firstName}</th>
            <th>${list.lastName}</th>
            <th>${list.email}</th>
            <c:set var="isAdmin" value="False" />
            <c:forEach var="role" items="${list.roles}">
                <c:choose>
                    <c:when test="${role.name == 'ROLE_ADMIN'}">
                        <c:set var="isAdmin" value="True" />
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${isAdmin == 'True'}">
                    <th>+</th>
                </c:when>
                <c:otherwise>
                    <th></th>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>

<p></p>

    <c:if test="${not empty pageContext.request.userPrincipal}">
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">

            <table>
                <tr>
                    <td><input type="submit" value="Edit" name="Edit"/></td>
                    <td><input type="submit" value="Delete" name="Delete"/></td>
                </tr>
            </table>

        </c:if>
    </c:if>

    <p></p>
</form>

<c:if test="${not empty pageContext.request.userPrincipal}">
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">

        <form action="<c:url value="/admin/addUser"/>" method="POST">
            <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            <table>
                <CAPTION>Create new user</CAPTION>
                <tr>
                    <td>Username</td>
                    <td>
                        <input type="text" name="username">
                        <span class="error">${messages.username}</span>
                    </td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName"></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>
                        <input type="password" name="password">
                        <span class="error">${messages.password}</span>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add" name="Add"/></td>
                </tr>
            </table>
        </form>

    </c:if>
</c:if>

<p><a href="${pageContext.request.contextPath}">Back to the main page</a></p>

</body>
</html>