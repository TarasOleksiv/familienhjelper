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
    <title>Edit Beneficiary</title>

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

                    <form id="benEdit" class="form-edit" action="<c:url value="/beneficiaries/${beneficiary.id}"/>" method="POST">
                        <input type="hidden" name="_method" value="put"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="beneficiaryId" value="${beneficiary.id}"/>

                        <table class="table-input">
                            <CAPTION>Beneficiary details:
                                <p><strong>${beneficiary.name}</strong></p>
                            </CAPTION>
                            <tr>
                                <td>Name</td>
                                <td>
                                    <input type="text" name="name" value="${beneficiary.name}">
                                    <div class="has-error">${messages.name}</div>
                                </td>
                            </tr>
                            <tr>
                                <td>Family</td>
                                <td>
                                    <textarea rows="4" cols="25" name="family" form="benEdit" maxlength="480">${beneficiary.family}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td>
                                    <textarea rows="6" cols="25" name="description" form="benEdit" maxlength="2000">${beneficiary.description}</textarea>
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
                                            <c:choose>
                                                <c:when test="${beneficiary.incomeType.id == incomeType.id}">
                                                    <option value="${incomeType.id}" selected><c:out value="${incomeType.name}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${incomeType.id}"><c:out value="${incomeType.name}"/></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Currency</td>
                                <td>
                                    <select name="currencyId">
                                        <c:forEach var="currency" items="${listCurrency}">
                                            <c:choose>
                                                <c:when test="${beneficiary.currency.id == currency.id}">
                                                    <option value="${currency.id}" selected><c:out value="${currency.name}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${currency.id}"><c:out value="${currency.name}"/></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Field Contact</td>
                                <td>
                                    <select name="userId">
                                        <c:choose>
                                            <c:when test="${pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
                                                <option value="${userPrincipal.id}"><c:out value="${userPrincipal.username}: ${userPrincipal.lastName} ${userPrincipal.firstName}"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option></option>
                                                <c:forEach var="user" items="${listUsers}">
                                                    <c:choose>
                                                        <c:when test="${beneficiary.user.id == user.id}">
                                                            <option value="${user.id}" selected><c:out value="${user.username}: ${user.lastName} ${user.firstName}"/></option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${user.id}"><c:out value="${user.username}: ${user.lastName} ${user.firstName}"/></option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td>Active</td>
                                <c:choose>
                                    <c:when test="${beneficiary.active}">
                                        <c:choose>
                                            <c:when test="${pageContext.request.isUserInRole('ROLE_FU')}">
                                                <td><input type="checkbox" name="active" value="true" checked onclick="return false;"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><input type="checkbox" name="active" value="true" checked></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${pageContext.request.isUserInRole('ROLE_FU')}">
                                                <td><input type="checkbox" name="active" value="true" onclick="return false;"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><input type="checkbox" name="active" value="true"></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </tr>

                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/beneficiaries/${beneficiary.id}">Cancel</a></td>
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
