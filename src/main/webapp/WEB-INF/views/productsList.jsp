<%--
  Created by IntelliJ IDEA.
  User: t.oleksiv
  Date: 20/12/2017
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Products</title>
    <style type="text/css">
        tr.grey { background: lightgrey;}
        td.pad { padding: 10px; }
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

<table>
    <tr>
        <td class="pad"><a href="${pageContext.request.contextPath}">Main page</a></td>
        <td class="pad"><a href="${pageContext.request.contextPath}/showManufacturers">Manufacturers</a></td>
    </tr>
</table>

<form action="<c:url value="/showProducts"/>" method="POST">
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    <table>
        <CAPTION>Products by manufacturer</CAPTION>
        <tr>
            <td>Manufacturer:</td>
            <td>
                <select name="manufacturerForProductId">
                    <c:forEach var="listManufacturers" items="${listManufacturers}">
                        <option value="${listManufacturers.id}"><c:out value="${listManufacturers.name}"/></option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="Show" name="Show"/></td>
            <td><input type="submit" value="ShowAll" name="ShowAll"/></td>
        </tr>
    </table>

    <table border="1">
        <CAPTION>List of Products</CAPTION>
        <tr class="grey">
            <th></th>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Manufacturer</th>
        </tr>
        <c:forEach items="${listProducts}" var="listProducts">
            <tr>
                <th><input type="radio" name="productId" value="${listProducts.id}"></th>
                <th>${listProducts.id}</th>
                <th>${listProducts.name}</th>
                <th>${listProducts.price}</th>
                <th>${listProducts.manufacturer.name}</th>
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

    <p></p>

    <table>
        <CAPTION>Create new product</CAPTION>
        <tr>
            <td>Name:</td>
            <td>
                <input type="text" name="name" size="50">
                <span class="error">${messages.name}</span>
            </td>
        </tr>
        <tr>
            <td>Price:</td>
            <td>
                <input type="text" name="price">
                <span class="error">${messages.price}</span>
            </td>
        </tr>
        <tr>
            <td>Manufacturer:</td>
            <td>
                <select name="manufacturerId">
                    <c:forEach var="listManufacturers" items="${listManufacturers}">
                        <option value="${listManufacturers.id}"><c:out value="${listManufacturers.name}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Add" name="Add"/></td>
        </tr>
    </table>

    </c:if>
</c:if>

</form>

<table>
    <tr>
        <td class="pad"><a href="${pageContext.request.contextPath}">Main page</a></td>
        <td class="pad"><a href="${pageContext.request.contextPath}/showManufacturers">Manufacturers</a></td>
    </tr>
</table>

</body>
</html>
