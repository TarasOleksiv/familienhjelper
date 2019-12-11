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

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script defer src="${contextPath}/resources/js/all.js"></script>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <jsp:include page="includes/menu.jsp" />
        </div>

        <div class="col-sm-10">
            <header>
                <div class="container">
                    <h4>Users</h4>
                    <p>
                        <a class="btn btn-primary btn-sm" href="/users/new">Add New User</a>
                    </p>
                </div>
            </header>
            <form action="<c:url value="/users"/>" method="POST">
                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

                <table class="table-grid">
                    <tr>
                        <th></th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Mobile1</th>
                        <th>Mobile2</th>
                        <th>Address</th>
                        <th>Account</th>
                        <th>Bank</th>
                        <th>Role</th>
                        <!--th></th-->
                    </tr>
                    <c:forEach items="${list}" var="list">
                        <tr>
                            <!--td><input type="radio" name="userId" value="${list.id}"></td-->
                            <td>
                                <a href="/users/${list.id}/edit">
                                    <span class="button-edit"><i class="far fa-edit"></i></span>
                                </a>
                            </td>
                            <td>${list.username}</td>
                            <td>${list.email}</td>
                            <td>${list.mobile1}</td>
                            <td>${list.mobile2}</td>
                            <td>${list.address}</td>
                            <td>${list.account}</td>
                            <td>${list.bank}</td>
                            <c:set var="userrole" value="${list.roles.stream().findFirst().get().name}"/>
                            <td>${userrole.substring(userrole.indexOf('_')+1).toLowerCase()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>