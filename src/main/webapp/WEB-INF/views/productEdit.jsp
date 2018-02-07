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
    <title>Edit Product</title>
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

<p><a href="${pageContext.request.contextPath}/showProducts">Back to Products</a></p>

<form action="<c:url value="/editProduct"/>" method="POST">
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    <input type="hidden" name="productId" value="${product.id}"/>
    <table>
        <tr>
            <th>Edit product:</th>
            <th><i><u>${product.name}</u></i></th>
        </tr>
    </table>
    <p></p>
    <table>
        <tr>
            <td>New name</td>
            <td>
                <input type="text" name="newName" value="${product.name}" size="50">
                <span class="error">${messages.newName}</span>
            </td>
        </tr>
        <tr>
            <td>New price:</td>
            <td>
                <input type="text" name="newPrice" value="${product.price}">
                <span class="error">${messages.newPrice}</span>
            </td>
        </tr>
        <tr>
            <td>Manufacturer:</td>
            <td>
                <select name="manufacturerId">
                    <c:forEach var="listManufacturers" items="${listManufacturers}">
                        <c:choose>
                            <c:when test="${product.manufacturer.id==listManufacturers.id}">
                                <option value="${listManufacturers.id}" selected><c:out value="${listManufacturers.name}"/></option>
                            </c:when>
                            <c:otherwise>
                                <option value="${listManufacturers.id}"><c:out value="${listManufacturers.name}"/></option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Save" name="Save"/></td>
            <td><input type="submit" value="Cancel" name="Cancel"/></td>
        </tr>
    </table>
</form>

<p><a href="${pageContext.request.contextPath}/showProducts">Back to Products</a></p>

</body>
</html>
