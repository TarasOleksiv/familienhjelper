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
                                <td>Income</td>
                                <td><input type="number" step="0.01" min="0" name="income" value="${beneficiary.income}"></td>
                            </tr>
                            <tr>
                                <td>Start date</td>
                                <td><input type="date" name="datefield" value="${beneficiary.datefield}"></td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td>
                                    <select name="statusName">
                                        <c:forEach var="status" items="${listStatuses}">
                                            <c:choose>
                                                <c:when test="${beneficiary.status.name == status.name}">
                                                    <option value="${status.name}" selected><c:out value="${status.name}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${!pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
                                                        <option value="${status.name}"><c:out value="${status.name}"/></option>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
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
