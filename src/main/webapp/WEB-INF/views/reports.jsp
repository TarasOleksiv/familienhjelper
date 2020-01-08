<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 19.12.2017
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Reports</title>

    <link rel="icon" type="image/png" href="${contextPath}/resources/img/weblogo.png"/>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script defer src="${contextPath}/resources/js/all.js"></script>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <jsp:include page="includes/menu.jsp" />
        </div>

        <div class="col-sm-10">
            <header>
                <div class="container">
                    <h4>Reports</h4>
                    <form id="exportUsers" action="/reports/users/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export users" name="Submit"/></td>
                                <td>
                                    <select id="exportUsersSelect" name="exportType" onchange="setLink(this)">
                                        <option value="csv">csv</option>
                                        <option value="excel">excel</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportMembers" action="/reports/members/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export donors" name="Submit"/></td>
                                <td>
                                    <select id="exportMembersSelect" name="exportType" onchange="setLink(this)">
                                        <option value="csv">csv</option>
                                        <option value="excel">excel</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportBeneficiaries" action="/reports/beneficiaries/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export beneficiaries" name="Submit"/></td>
                                <td>
                                    <select id="exportBeneficiariesSelect" name="exportType" onchange="setLink(this)">
                                        <option value="csv">csv</option>
                                        <option value="excel">excel</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportProjects" action="/reports/projects/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export projects" name="Submit"/></td>
                                <td>
                                    <select id="exportProjectsSelect" name="exportType" onchange="setLink(this)">
                                        <option value="csv">csv</option>
                                        <option value="excel">excel</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportProjectMembers" action="/reports/project/members/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export project donors" name="Submit"/></td>
                                <td>
                                    <select id="exportProjectMembersSelect" name="exportType" onchange="setLink(this)">
                                        <option value="csv">csv</option>
                                        <option value="excel">excel</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportTransactions" action="/reports/transactions/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export transactions" name="Submit"/></td>
                                <td>
                                    <select id="exportTransactionsSelect" name="exportType" onchange="setLink(this)">
                                        <option value="csv">csv</option>
                                        <option value="excel">excel</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </header>

        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script src="${contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/app.js"></script>

</body>
</html>