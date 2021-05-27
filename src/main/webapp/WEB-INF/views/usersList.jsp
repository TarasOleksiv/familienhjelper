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

    <link rel="icon" type="image/png" href="${contextPath}/resources/img/weblogo.png"/>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/dataTables.bootstrap.min.css" rel="stylesheet">
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

                <table id="userTable" class="table table-striped table-bordered display">
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th>Username</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Mobile1</th>
                        <th>Role</th>
                        <th>Active</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="list">
                        <tr>
                            <td>
                                <a href="/users/${list.id}">
                                    <img src="${contextPath}/resources/img/icons8-edit-16.png">
                                    <!--span><i class="far fa-hand-pointer"></i></span-->
                                </a>
                            </td>
                            <td>
                                <a href="/users/${list.id}/password">
                                    <img src="${contextPath}/resources/img/icons8-password-16.png">
                                    <!--span><i class="far fa-hand-pointer"></i></span-->
                                </a>
                            </td>
                            <td>${list.username}</td>
                            <td>${list.lastName} ${list.firstName}</td>
                            <td>${list.email}</td>
                            <td>${list.mobile1}</td>
                            <c:set var="userrole" value="${list.roles.stream().findFirst().get().name}"/>
                            <td>${userrole.substring(userrole.indexOf('_')+1).toLowerCase()}</td>
                            <td>
                                <c:if test="${list.active}">
                                    <img src="${contextPath}/resources/img/icons8-green-circle-16.png">
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script src="${contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/app.js"></script>

</body>
</html>