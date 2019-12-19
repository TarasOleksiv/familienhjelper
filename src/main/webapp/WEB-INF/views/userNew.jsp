<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 09.12.2019
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>New User</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <jsp:include page="includes/menu.jsp" />
        </div>
        <div class=class="col-sm-10">
            <c:if test="${not empty pageContext.request.userPrincipal}">
                <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">

                    <form class="form-edit" action="<c:url value="/users"/>" method="POST">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-input">
                            <CAPTION>Create new user</CAPTION>
                            <tr>
                                <td>Username</td>
                                <td>
                                    <input type="text" name="username" value="${user.username}">
                                    <div class="has-error">${messages.username}</div>
                                </td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input type="email" name="email" value="${user.email}"></td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td><input type="text" name="address" value="${user.address}"></td>
                            </tr>
                            <tr>
                                <td>Mobile1</td>
                                <td><input type="tel" pattern="^\+[0-9 ]+$" placeholder="+99 999 999 9999" name="mobile1" value="${user.mobile1}"></td>
                            </tr>
                            <tr>
                                <td>Mobile2</td>
                                <td><input type="tel" pattern="^\+[0-9 ]+$" placeholder="+99 999 999 9999" name="mobile2" value="${user.mobile2}"></td>
                            </tr>
                            <tr>
                                <td>Account</td>
                                <td><input type="text" name="account" value="${user.account}"></td>
                            </tr>
                            <tr>
                                <td>Bank</td>
                                <td><input type="text" name="bank" value="${user.bank}"></td>
                            </tr>
                            <tr>
                                <td>Role</td>
                                <td>
                                <select name="roleName">
                                    <c:forEach var="role" items="${listRoles}">
                                        <option value="${role.name}"><c:out value="${role.name}"/></option>
                                    </c:forEach>
                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td>
                                    <input type="password" name="password">
                                    <div class="has-error">${messages.password}</div>
                                </td>
                            </tr>
                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/users">Cancel</a></td>
                                <td><input class="btn btn-success btn-sm btn-block" type="submit" value="Submit" name="Add"/></td>
                            </tr>
                        </table>
                    </form>

                </c:if>
            </c:if>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
