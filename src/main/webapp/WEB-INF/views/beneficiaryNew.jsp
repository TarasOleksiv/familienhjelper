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
    <title>New Beneficiary</title>

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

                    <form id="benNew" class="form-edit" action="<c:url value="/beneficiaries"/>" method="POST">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-input">
                            <CAPTION>Create new beneficiary</CAPTION>
                            <tr>
                                <td>Name</td>
                                <td>
                                    <input type="text" name="name" value="${beneficiary.name}">
                                    <div class="has-error">${messages.name}</div>
                                </td>
                            </tr>
                            <tr>
                                <td>Family</td>
                                <!--td><input type="text" name="family" value="${beneficiary.family}"></td-->
                                <td>
                                    <textarea rows="4" cols="25" name="family" form="benNew" maxlength="480">${beneficiary.family}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <!--td><input type="text" name="description" value="${beneficiary.description}"></td-->
                                <td>
                                    <textarea rows="6" cols="25" name="description" form="benNew" maxlength="2000">${beneficiary.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Born</td>
                                <td><input type="date" name="datefield" value="${beneficiary.datefield}"></td>
                            </tr>
                            <tr>
                                <td>Income</td>
                                <td><input type="number" step="0.01" min="0" name="income" value="${beneficiary.income}"></td>
                            </tr>
                            <tr>
                                <td>Income Type</td>
                                <td>
                                    <select name="incomeTypeId">
                                        <c:forEach var="incomeType" items="${listIncomeTypes}">
                                            <option value="${incomeType.id}"><c:out value="${incomeType.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
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
                            <tr>
                                <td>Field Contact</td>
                                <td>
                                    <select name="userId">
                                        <option></option>
                                        <c:forEach var="user" items="${listUsers}">
                                            <option value="${user.id}"><c:out value="${user.username}: ${user.lastName} ${user.firstName}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/beneficiaries">Cancel</a></td>
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
