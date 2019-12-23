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
    <title>Reset password</title>

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

                    <form class="form-edit" action="<c:url value="/users/${user.id}/password"/>" method="POST">
                        <!--input type="hidden" name="_method" value="put"/-->
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="userId" value="${user.id}"/>

                        <table class="table-input">
                            <CAPTION>Reset Password</CAPTION>
                            <tr>
                                <td>Username</td>
                                <td>
                                    <input type="text" name="username" value="${user.username}" disabled>
                                </td>
                            </tr>
                            <tr>
                                <td>First Name</td>
                                <td>
                                    <input type="text" name="firstName" value="${user.firstName}" disabled>
                                </td>
                            </tr>
                            <tr>
                                <td>Last Name</td>
                                <td>
                                    <input type="text" name="lastName" value="${user.lastName}" disabled>
                                </td>
                            </tr>
                            <tr>
                                <td>New password</td>
                                <td>
                                    <input type="password" name="password">
                                    <div class="has-error">${messages.password}</div>
                                </td>
                            </tr>
                            <tr>
                                <td>Confirm new password</td>
                                <td>
                                    <input type="password" name="confirmPassword">
                                    <div class="has-error">${messages.confirmPassword}</div>
                                </td>
                            </tr>
                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/users">Cancel</a></td>
                                <td><input class="btn btn-success btn-sm btn-block" type="submit" value="Save" name="Save"/></td>
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
