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
    <title>User details</title>

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
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="userId" value="${user.id}"/>

                        <table class="table-show">
                            <CAPTION>User details: <strong>${user.username}</strong></CAPTION>
                            <tr>
                                <td>Username</td>
                                <td colspan="2">${user.username}</td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td colspan="2">${user.email}</td>
                            </tr>
                            <tr>
                                <td>Address</td>
                                <td colspan="2">${user.address}</td>
                            </tr>
                            <tr>
                                <td>Mobile1</td>
                                <td colspan="2">${user.mobile1}</td>
                            </tr>
                            <tr>
                                <td>Mobile2</td>
                                <td colspan="2">${user.mobile2}</td>
                            </tr>
                            <tr>
                                <td>Account</td>
                                <td colspan="2">${user.account}</td>
                            </tr>
                            <tr>
                                <td>Bank</td>
                                <td colspan="2">${user.bank}</td>
                            </tr>
                            <tr>
                                <td>Role</td>
                                <td colspan="2">${user.roles.stream().findFirst().get().name}</td>
                            </tr>
                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/users">Cancel</a></td>
                                <td><a class="btn btn-warning btn-sm btn-block" href="/users/${user.id}/edit">Edit</a></td>
                                <td><input id="delete" class="btn btn-danger btn-sm btn-block" type="submit" value="Delete" name="Delete"/></td>
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
