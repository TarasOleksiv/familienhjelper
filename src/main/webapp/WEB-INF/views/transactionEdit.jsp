<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 09.12.2019
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Edit Transaction</title>

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

                    <form id="transactionEdit" class="form-edit" action="<c:url value="/projects/${project.id}/transactions/${transaction.id}"/>" method="POST">
                        <input type="hidden" name="_method" value="put"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="transactionId" value="${transaction.id}"/>
                        <table class="table-input">
                            <CAPTION>Project ${project.name}</CAPTION>
                            <CAPTION>Edit transaction</CAPTION>
                            <tr>
                                <td>Date:</td>
                                <!--td><fmt:formatDate value="${transaction.tradingDate}" pattern="dd.MM.yyyy" /></td-->
                                <td><input type="date" name="tradingDate" value="${transaction.tradingDate}" required></td>
                            </tr>
                            <tr>
                                <td>Amount:</td>
                                <!--td>${transaction.amount}</td-->
                                <td><input type="number" step="0.01" min="0" name="amount" value="${transaction.amount}" required></td>
                            </tr>
                            <tr>
                                <td>Currency:</td>
                                <!--td>${transaction.currency.name}</td-->
                                <td>
                                    <select name="currencyId">
                                        <option></option>
                                        <c:forEach var="currency" items="${listCurrency}">
                                            <c:choose>
                                                <c:when test="${transaction.currency.id == currency.id}">
                                                    <option value="${currency.id}" selected><c:out value="${currency.name}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${currency.id}"><c:out value="${currency.name}"/></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <div class="has-error">${messages.currency}</div>
                                </td>
                            </tr>
                            <!--tr>
                                <td>Amount NOK:</td>
                                <td>${transaction.amountNOK}</td>
                            </tr-->
                            <c:choose>
                                <c:when test="${transaction.isIncome==true}">
                                    <tr>
                                        <td>From Donor:</td>
                                        <!--td>${transaction.member.name}</td-->
                                        <td>
                                            <select name="memberId">
                                                <c:forEach var="member" items="${listMembers}">
                                                    <c:choose>
                                                        <c:when test="${transaction.member.id == member.id}">
                                                            <option value="${member.id}" selected><c:out value="${member.name}"/></option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${member.id}"><c:out value="${member.name}"/></option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                            <div class="has-error">${messages.donor}</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>To:</td>
                                        <td>This project</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>From:</td>
                                        <td>This project</td>
                                    </tr>
                                    <tr>
                                        <td>To Beneficiary:</td>
                                        <!--td>${transaction.beneficiary.name}</td-->
                                        <td>
                                            <select name="beneficiaryId">
                                                <option></option>
                                                <c:forEach var="beneficiary" items="${listBeneficiaries}">
                                                    <c:choose>
                                                        <c:when test="${transaction.beneficiary.id == beneficiary.id}">
                                                            <option value="${beneficiary.id}" selected><c:out value="${beneficiary.name}"/></option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${beneficiary.id}"><c:out value="${beneficiary.name}"/></option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                            <div class="has-error">${messages.beneficiary}</div>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                            <tr>
                                <td>Type:</td>
                                <td>
                                    <select name="transactionTypeId">
                                        <c:forEach var="transactionType" items="${listTransactionTypes}">
                                            <c:choose>
                                                <c:when test="${transaction.transactionType.id == transactionType.id}">
                                                    <option value="${transactionType.id}" selected><c:out value="${transactionType.name}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${pageContext.request.isUserInRole('ROLE_FIELDCONTACT') && transactionType.isDonation}">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${transactionType.id}"><c:out value="${transactionType.name}"/></option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Description:</td>
                                <td>
                                    <textarea rows="4" cols="25" name="description" form="transactionEdit" maxlength="192">${transaction.description}</textarea>
                                </td>
                            </tr>

                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/projects/${project.id}/transactions/${transaction.id}">Cancel</a></td>
                                <td><input class="btn btn-success btn-sm btn-block" type="submit" value="Save" name="Save"/></td>
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
