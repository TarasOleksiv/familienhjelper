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

                    <form class="form-edit" action="<c:url value="/users/${user.id}"/>" method="POST">
                        <input type="hidden" name="_method" value="put"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="userId" value="${user.id}"/>

                        <table class="table-input">
                            <CAPTION>User details: <strong>${user.username}</strong></CAPTION>
                            <tr>
                                <td>Username</td>
                                <td>
                                    <input type="text" name="username" value="${user.username}">
                                    <span class="has-error">${messages.username}</span>
                                </td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td>
                                    <input type="text" name="email" value="${user.email}">
                                </td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td>
                                    <input type="text" name="address" value="${user.address}">
                                </td>
                            </tr>
                            <tr>
                                <td>Mobile1</td>
                                <td>
                                    <input type="text" name="mobile1" value="${user.mobile1}">
                                </td>
                            </tr>
                            <tr>
                                <td>Mobile2</td>
                                <td>
                                    <input type="text" name="mobile2" value="${user.mobile2}">
                                </td>
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
                                            <c:forEach var="userrole" items="${user.roles}">
                                                <c:choose>
                                                    <c:when test="${userrole.name == role.name}">
                                                        <option value="${role.name}" selected><c:out value="${role.name}"/></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${role.name}"><c:out value="${role.name}"/></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td>
                                    <input type="password" name="password">
                                    <span class="has-error">${messages.password}</span>
                                </td>
                            </tr>
                            <tr>
                                <td><input class="btn btn-secondary btn-sm btn-cancel btn-block" type="submit" value="Cancel" name="Cancel"/></td>
                                <td><input class="btn btn-success btn-sm btn-block" type="submit" value="Save" name="Save"/></td>
                            </tr>
                        </table>
                    </form>

                    <form class="form-edit" action="<c:url value="/users/${user.id}"/>" method="POST">
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="userId" value="${user.id}"/>

                        <table class="table-input">
                            <tr>
                                <td>
                                    <input id="delete" class="btn btn-danger btn-sm btn-block" type="submit" value="Delete" name="Delete"/>
                                </td>
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
