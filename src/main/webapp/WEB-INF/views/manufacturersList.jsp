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
    <title>Manufacturers</title>
    <style type="text/css">
        tr.grey { background: lightgrey;}
        .error { color: red; }
    </style>
</head>
<body>

<p><a href="${pageContext.request.contextPath}">Back to the main page</a></p>

<form action="<c:url value="/showManufacturers"/>" method="POST">
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

<table border="1">
    <CAPTION>List of Manufacturers</CAPTION>
    <tr class="grey">
        <th></th>
        <th>Id</th>
        <th>Name</th>
    </tr>
    <c:forEach items="${list}" var="list">
        <tr>
            <th><input type="radio" id="manufacturerId" name="manufacturerId" value="${list.id}" onclick="getManufacturerId(${list.id})"></th>
            <th>${list.id}</th>
            <th><a href="${pageContext.request.contextPath}/showProducts?filterProducts=yes&idManufacturer=${list.id}">${list.name}</a></th>
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
    <CAPTION>Create new manufacturer</CAPTION>
    <tr>
        <td>Name</td>
        <td>
            <input type="text" name="name">
            <span class="error">${messages.name}</span>
        </td>
        <td><input type="submit" value="Add" name="Add"/></td>
    </tr>
</table>
</form>

<p><a href="${pageContext.request.contextPath}">Back to the main page</a></p>

</body>
</html>