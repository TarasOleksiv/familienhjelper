<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 20.12.2017
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Beneficiary Details</title>

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

                    <form class="form-edit" action="<c:url value="/beneficiaries/${beneficiary.id}"/>" method="POST">
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="beneficiaryId" value="${beneficiary.id}"/>

                        <table class="table-show">
                            <CAPTION>Beneficiary details:
                                <p><strong>${beneficiary.name}</strong></p>
                            </CAPTION>
                            <tr>
                                <td>Name</td>
                                <td colspan="2">${beneficiary.name}</td>
                            </tr>
                            <tr>
                                <td>Family</td>
                                <td colspan="2">
                                    <textarea rows="4" cols="25" name="family" maxlength="480" disabled>
                                            <c:out value="${beneficiary.family}"/></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td colspan="2">${beneficiary.description}</td>
                            </tr>
                            <tr>
                                <td>Income</td>
                                <td colspan="2">${beneficiary.income}</td>
                            </tr>
                            <tr>
                                <td>Date</td>
                                <td colspan="2"><fmt:formatDate value="${beneficiary.datefield}" pattern="dd.MM.yyyy" /></td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td colspan="2">${beneficiary.status.name}</td>
                            </tr>
                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/beneficiaries">Cancel</a></td>
                                <td><a class="btn btn-warning btn-sm btn-block" href="/beneficiaries/${beneficiary.id}/edit">Edit</a></td>
                                <td><input id="delete" class="btn btn-danger btn-sm btn-block" type="submit" value="Delete" name="Delete"/></td>
                            </tr>
                        </table>
                    </form>

            </c:if>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
