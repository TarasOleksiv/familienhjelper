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
    <title>Transaction Details</title>

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

                    <form class="form-edit" action="<c:url value="/projects/${project.id}/transactions/${transaction.id}"/>" method="POST">
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="transactionId" value="${transaction.id}"/>

                        <table class="table-show">
                            <CAPTION>Project ${project.name}</CAPTION>
                            <CAPTION>Transaction details:</CAPTION>
                            <tr>
                                <td>Date:</td>
                                <td colspan="2"><fmt:formatDate value="${transaction.tradingDate}" pattern="dd.MM.yyyy" /></td>
                            </tr>
                            <tr>
                                <td>Amount:</td>
                                <td colspan="2">${transaction.amount}</td>
                            </tr>
                            <tr>
                                <td>Currency:</td>
                                <td colspan="2">${transaction.currency.name}</td>
                            </tr>
                            <tr>
                                <td>Amount NOK:</td>
                                <td colspan="2">${transaction.amountNOK}</td>
                            </tr>
                            <c:choose>
                                <c:when test="${transaction.isIncome==true}">
                                    <tr>
                                        <td>From Donor:</td>
                                        <td colspan="2">${transaction.member.name}</td>
                                    </tr>
                                    <tr>
                                        <td>To:</td>
                                        <td colspan="2">This project</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>From:</td>
                                        <td colspan="2">This project</td>
                                    </tr>
                                    <tr>
                                        <td>To Beneficiary:</td>
                                        <td colspan="2">${transaction.beneficiary.name}</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            <tr>
                                <td>Type:</td>
                                <td colspan="2">${transaction.transactionType.name}</td>
                            </tr>
                            <tr>
                                <td>Description:</td>
                                <td colspan="2">
                                    <textarea rows="4" cols="25" name="description" maxlength="192" readonly>${transaction.description}</textarea>
                                </td>
                            </tr>

                            <tr>
                                <td><a class="btn btn-primary btn-sm btn-block" href="/projects/${project.id}/transactions">Transactions</a></td>
                                <c:if test="${!pageContext.request.isUserInRole('ROLE_HELPER')}">
                                <td>
                                    <c:choose>
                                        <c:when test="${isTransactionPossible}">
                                            <a class="btn btn-warning btn-sm btn-block" href="/projects/${project.id}/transactions/${transaction.id}/edit">Edit</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-warning btn-sm btn-block disabled" aria-disabled="true" href="/projects/${project.id}/transactions/${transaction.id}/edit">Edit</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${isTransactionPossible}">
                                            <input id="delete" class="btn btn-danger btn-sm btn-block" type="submit" value="Delete" name="Delete"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input id="delete" class="btn btn-danger btn-sm btn-block" type="submit" disabled value="Delete" name="Delete"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                </c:if>
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
