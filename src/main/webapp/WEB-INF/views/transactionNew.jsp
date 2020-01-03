<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 09.12.2019
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>New Transaction</title>

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

                    <form id="transactionNew" class="form-edit" action="<c:url value="/projects/${projectId}/transactions"/>" method="POST">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-input">
                            <CAPTION>Create new transaction</CAPTION>
                            <tr>
                                <td>In/Out</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${transaction.isIncome}">In</c:when>
                                        <c:otherwise>Out</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td>Date</td>
                                <td><input type="date" name="tradingDate" value="${transaction.tradingDate}"></td>
                            </tr>
                            <tr>
                                <td>Amount</td>
                                <td><input type="number" step="0.01" min="0" name="amount" value="${transaction.amount}"></td>
                            </tr>
                            <tr>
                                <td>Currency</td>
                                <td>
                                    <select name="currencyId">
                                        <c:forEach var="currency" items="${listCurrency}">
                                            <option value="${currency.id}"><c:out value="${currency.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <c:choose>
                                <c:when test="${'true'.equals(isIncome)}">
                                    <tr>
                                        <td>From Donor</td>
                                        <td>
                                            <select name="memberId">
                                                <c:forEach var="member" items="${listMembers}">
                                                    <option value="${member.member.id}"><c:out value="${member.member.name}"/></option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>To Beneficiary</td>
                                        <td>
                                            <select name="beneficiaryId">
                                                <c:forEach var="beneficiary" items="${listBeneficiaries}">
                                                    <option value="${beneficiary.id}"><c:out value="${beneficiary.name}"/></option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                            <tr>
                                <td>Type</td>
                                <td>
                                    <select name="transactionTypeId">
                                        <c:forEach var="transactionType" items="${listTransactionTypes}">
                                            <option value="${transactionType.id}"><c:out value="${transactionType.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td>
                                    <textarea form="transactionNew" rows="4" cols="25" name="description" maxlength="192" readonly>${transaction.description}</textarea>
                                </td>
                            </tr>

                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/projects/${projectId}/transactions">Cancel</a></td>
                                <td><input class="btn btn-success btn-sm btn-block" type="submit" value="Submit" name="Add"/></td>
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
