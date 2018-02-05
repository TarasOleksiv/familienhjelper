<%--
  Created by IntelliJ IDEA.
  User: t.oleksiv
  Date: 20/12/2017
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Manufacturer</title>
    <style type="text/css">
        .error { color: red; }
    </style>
</head>
<body>

<p><a href="${pageContext.request.contextPath}/showManufacturers">Back to Manufacturers</a></p>

<form action="<c:url value="/editManufacturer"/>" method="POST">
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
    <input type="hidden" name="manufacturerId" value="${manufacturer.id}"/>
    <table>
        <tr>
            <th>Edit manufacturer:</th>
            <th><i><u>${manufacturer.name}</u></i></th>
        </tr>
    </table>
    <p></p>
    <table>
        <tr>
            <td>New name</td>
            <td>
                <input type="text" name="newName" value="${manufacturer.name}" size="30">
                <span class="error">${messages.newName}</span>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Save" name="Save"/></td>
            <td><input type="submit" value="Cancel" name="Cancel"/></td>
        </tr>
    </table>
</form>

<p><a href="${pageContext.request.contextPath}/showManufacturers">Back to Manufacturers</a></p>

</body>
</html>
