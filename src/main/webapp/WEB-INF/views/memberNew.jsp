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
    <title>New Donor</title>

    <link rel="icon" type="image/png" href="${contextPath}/resources/img/weblogo.png"/>
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
                <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') ||
                            pageContext.request.isUserInRole('ROLE_FU')}">

                    <form class="form-edit" action="<c:url value="/members"/>" method="POST">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-input">
                            <CAPTION>Create new donor</CAPTION>
                            <tr>
                                <td>Name</td>
                                <td>
                                    <input type="text" name="name" value="${member.name}">
                                    <div class="has-error">${messages.name}</div>
                                </td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input type="email" name="email" value="${member.email}"></td>
                            </tr>
                            <tr>
                                <td>Mobile</td>
                                <td><input type="tel" pattern="^\+[0-9 ]+$" placeholder="+99 999 999 9999" name="mobile" value="${member.mobile}"></td>
                            </tr>
                            <tr>
                                <td>City</td>
                                <td><input type="text" name="city" value="${member.city}"></td>
                            </tr>
                            <tr>
                                <td>Account</td>
                                <td><input type="text" name="account" value="${member.account}"></td>
                            </tr>
                            <tr>
                                <td>Bank</td>
                                <td><input type="text" name="bank" value="${member.bank}"></td>
                            </tr>
                            <tr>
                                <td>Donor Type</td>
                                <td>
                                    <select name="donorTypeId">
                                        <c:forEach var="donorType" items="${listDonorTypes}">
                                            <option value="${donorType.id}"><c:out value="${donorType.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/members">Cancel</a></td>
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
