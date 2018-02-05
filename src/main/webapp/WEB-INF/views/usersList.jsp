<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 19.12.2017
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style type="text/css">
        tr.grey { background: lightgrey;}
        .error { color: red; }
    </style>
</head>
<body>

<p><a href="${pageContext.request.contextPath}">Back to the main page</a></p>

<form action="<c:url value="/showUsers"/>" method="POST">
<table border="1">
    <CAPTION>List of Users</CAPTION>
    <tr class="grey">
        <th></th>
        <th>Id</th>
        <th>Username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${list}" var="list">
        <tr>
            <th><input type="radio" name="userId" value="${list.id}"></th>
            <th>${list.id}</th>
            <th>${list.username}</th>
            <th>${list.firstName}</th>
            <th>${list.lastName}</th>
            <th>${list.email}</th>
        </tr>
    </c:forEach>
</table>

<p></p>

<table>
    <tr>
        <td><input type="submit" value="Edit" name="Edit"/></td>
        <td><input type="submit" value="Delete" name="Delete"/></td>
    </tr>
</table>

    <p></p>

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
        <td><input type="text" name="password"></td>
    </tr>
    <tr>
        <td><input type="submit" value="Add" name="Add"/></td>
    </tr>
</table>
</form>

<p><a href="${pageContext.request.contextPath}">Back to the main page</a></p>

</body>
</html>