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
    <title>Project Details</title>

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

                    <form class="form-edit" action="<c:url value="/projects/${project.id}"/>" method="POST">
                        <input type="hidden" name="_method" value="delete"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="projectId" value="${project.id}"/>

                        <table class="table-show">
                            <CAPTION>Project details:
                                <p><strong>${project.name}</strong></p>
                            </CAPTION>
                            <tr>
                                <td>Name</td>
                                <td colspan="2">${project.name}</td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td colspan="2">
                                    <textarea rows="6" cols="25" name="description" maxlength="40000" readonly>${project.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Start date</td>
                                <td colspan="2"><fmt:formatDate value="${project.startDate}" pattern="dd.MM.yyyy" /></td>
                            </tr>
                            <tr>
                                <td>Stop date</td>
                                <td colspan="2"><fmt:formatDate value="${project.stopDate}" pattern="dd.MM.yyyy" /></td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td colspan="2">${project.status.name}</td>
                            </tr>
                            <tr>
                                <td>Field Contact</td>
                                <td colspan="2">${project.fieldContactUser.username} ${project.fieldContactUser.lastName} ${project.fieldContactUser.firstName}</td>
                            </tr>
                            <tr>
                                <td>FU responsible</td>
                                <td colspan="2">${project.fuUser.username} ${project.fuUser.lastName} ${project.fuUser.firstName}</td>
                            </tr>
                            <tr>
                                <td>Feedback</td>
                                <td colspan="2">
                                    <textarea rows="6" cols="25" name="feedback" maxlength="40000" readonly>${project.feedback}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Active</td>
                                <td colspan="2">
                                    <c:if test="${project.active}">
                                        <img src="${contextPath}/resources/img/icons8-green-circle-16.png">
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>Balance</td>
                                <td>
                                    <div id="NOK">${currencyBalanceMap.NOK}</div>
                                    <div id="UAH" class="hidden_div">${currencyBalanceMap.UAH}</div>
                                    <div id="RUB" class="hidden_div">${currencyBalanceMap.RUB}</div>
                                    <div id="EUR" class="hidden_div">${currencyBalanceMap.EUR}</div>
                                    <div id="USD" class="hidden_div">${currencyBalanceMap.USD}</div>
                                </td>
                                <td>
                                    <select name="currencyId" onchange="showCurrency(this)" style="width:70px">
                                        <c:forEach var="currency" items="${listCurrency}">
                                            <option value="${currency.name}"><c:out value="${currency.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"><a class="btn btn-primary btn-sm btn-block" href="/projects/${project.id}/donors">Donors</a></td>
                            </tr>
                            <tr>
                                <c:choose>
                                    <c:when test="${pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
                                        <c:choose>
                                            <c:when test="${empty project.imageFolderLink}">
                                                <td colspan="3"><a class="btn btn-primary btn-sm btn-block" disabled>Images Folder Link</a></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td colspan="3"><a class="btn btn-primary btn-sm btn-block" target="_blank" href="${project.imageFolderLink}">Images Folder Link</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="1"><a class="btn btn-primary btn-sm btn-block" href="/projects/${project.id}/images">Images</a></td>
                                        <c:choose>
                                            <c:when test="${empty project.imageFolderLink}">
                                                <td colspan="2"><a class="btn btn-primary btn-sm btn-block" disabled>Images Folder Link</a></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td colspan="2"><a class="btn btn-primary btn-sm btn-block" target="_blank" href="${project.imageFolderLink}">Images Folder Link</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td colspan="3"><a class="btn btn-primary btn-sm btn-block" href="/projects/${project.id}/transactions">Transactions</a></td>
                            </tr>
                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/projects">Cancel</a></td>
                                <td><a class="btn btn-warning btn-sm btn-block" href="/projects/${project.id}/edit">Edit</a></td>
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
<script src="${contextPath}/resources/js/app.js"></script>

</body>
</html>
