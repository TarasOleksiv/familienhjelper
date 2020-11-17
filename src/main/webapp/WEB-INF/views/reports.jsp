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
                        <input type="hidden"  name="allFields"  value="1" id="allUserFields"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export users" name="Submit"/></td>
                                <td>
                                    <select class="select-narrow" id="exportUsersSelect" name="exportType" onchange="setLink(this)">
                                        <option id="csvOptionUser" value="csv">csv</option>
                                        <option id="excelOptionUser" value="excel">excel</option>
                                    </select>
                                </td>
                                <td><button class="btn btn-info btn-sm" type="button"
                                            onclick="showUserFields(this)">Fields</button>
                                </td>
                                <td>
                                    <div id="userFieldsDiv" class="hidden_div">
                                        <select class="select-normal" name="userFields" size="5" multiple="multiple" tabindex="1">
                                            <option value="Username" selected>Username</option>
                                            <option value="First Name">First Name</option>
                                            <option value="Last Name">Last Name</option>
                                            <option value="Email">Email</option>
                                            <option value="Mobile1">Mobile1</option>
                                            <option value="Mobile2">Mobile2</option>
                                            <option value="Address">Address</option>
                                            <option value="Account">Account</option>
                                            <option value="Bank">Bank</option>
                                            <option value="Role">Role</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportMembers" action="/reports/members/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden"  name="allFields"  value="1" id="allMemberFields"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export donors" name="Submit"/></td>
                                <td>
                                    <select class="select-narrow" id="exportMembersSelect" name="exportType" onchange="setLink(this)">
                                        <option id="csvOptionMember" value="csv">csv</option>
                                        <option id="excelOptionMember" value="excel">excel</option>
                                    </select>
                                </td>
                                <td><button class="btn btn-info btn-sm" type="button"
                                            onclick="showMemberFields(this)">Fields</button>
                                </td>
                                <td>
                                    <div id="memberFieldsDiv" class="hidden_div">
                                        <select class="select-normal" name="memberFields" size="5" multiple="multiple" tabindex="1">
                                            <option value="Name" selected>Name</option>
                                            <option value="Email">Email</option>
                                            <option value="Mobile">Mobile</option>
                                            <option value="City">City</option>
                                            <option value="Account">Account</option>
                                            <option value="Bank">Bank</option>
                                            <option value="DonorType">DonorType</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportBeneficiaries" action="/reports/beneficiaries/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden"  name="allFields"  value="1" id="allBeneficiaryFields"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export beneficiaries" name="Submit"/></td>
                                <td>
                                    <select class="select-narrow" id="exportBeneficiariesSelect" name="exportType" onchange="setLink(this)">
                                        <option id="csvOptionBeneficiary" value="csv">csv</option>
                                        <option id="excelOptionBeneficiary" value="excel">excel</option>
                                    </select>
                                </td>
                                <td><button class="btn btn-info btn-sm" type="button"
                                            onclick="showBeneficiaryFields(this)">Fields</button>
                                </td>
                                <td>
                                    <div id="beneficiaryFieldsDiv" class="hidden_div">
                                        <select class="select-normal" name="beneficiaryFields" size="5" multiple="multiple" tabindex="1">
                                            <option value="Name" selected>Name</option>
                                            <option value="Family">Family</option>
                                            <option value="Income">Income</option>
                                            <option value="Income Type">Income Type</option>
                                            <option value="Currency">Currency</option>
                                            <option value="Born">Born</option>
                                            <option value="Field Contact">Field Contact</option>
                                            <option value="Description">Description</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportProjects" action="/reports/projects/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden"  name="allFields"  value="1" id="allProjectFields"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export projects" name="Submit"/></td>
                                <td>
                                    <select class="select-narrow" id="exportProjectsSelect" name="exportType" onchange="setLink(this)">
                                        <option id="csvOptionProject" value="csv">csv</option>
                                        <option id="excelOptionProject" value="excel">excel</option>
                                    </select>
                                </td>
                                <td><button class="btn btn-info btn-sm" type="button"
                                            onclick="showProjectFields(this)">Fields</button>
                                </td>
                                <td>
                                    <div id="projectFieldsDiv" class="hidden_div">
                                        <select class="select-normal" name="projectFields" size="5" multiple="multiple" tabindex="1">
                                            <option value="Name" selected>Name</option>
                                            <option value="Balance NOK">Balance NOK</option>
                                            <option value="Start Date">Start Date</option>
                                            <option value="Stop Date">Stop Date</option>
                                            <option value="Status">Status</option>
                                            <option value="FU">FU</option>
                                            <option value="Field Contact">Field Contact</option>
                                            <option value="Description">Description</option>
                                            <option value="Feedback">Feedback</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportProjectMembers" action="/reports/project/members/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden"  name="allFields"  value="1" id="allProjectMemberFields"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export project donors" name="Submit"/></td>
                                <td>
                                    <select class="select-narrow" id="exportProjectMembersSelect" name="exportType" onchange="setLink(this)">
                                        <option id="csvOptionProjectMember" value="csv">csv</option>
                                        <option id="excelOptionProjectMember" value="excel">excel</option>
                                    </select>
                                </td>
                                <td><button class="btn btn-info btn-sm" type="button"
                                            onclick="showProjectMemberFields(this)">Fields</button>
                                </td>
                                <td>
                                    <div id="projectMemberFieldsDiv" class="hidden_div">
                                        <select class="select-normal" name="projectMemberFields" size="5" multiple="multiple" tabindex="1">
                                            <option value="Project" selected>Project</option>
                                            <option value="Donor">Donor</option>
                                            <option value="Pledge">Pledge</option>
                                            <option value="Start Pledge">Start Pledge</option>
                                            <option value="Stop Pledge">Stop Pledge</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <form id="exportTransactions" action="/reports/transactions/csv" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden"  name="allFields"  value="1" id="allTransactionFields"/>
                        <table class="table-radio table-select">
                            <tr>
                                <td><input class="btn btn-primary btn-sm" type="submit" value="Export transactions" name="Submit"/></td>
                                <td>
                                    <select class="select-narrow" id="exportTransactionsSelect" name="exportType" onchange="setLink(this)">
                                        <option id="csvOptionTransaction" value="csv">csv</option>
                                        <option id="excelOptionTransaction" value="excel">excel</option>
                                    </select>
                                </td>
                                <td><button class="btn btn-info btn-sm" type="button"
                                            onclick="showTransactionFields(this)">Fields</button>
                                </td>
                                <td>
                                    <div id="transactionFieldsDiv" class="hidden_div">
                                        <select class="select-normal" name="transactionFields" size="5" multiple="multiple" tabindex="1">
                                            <option value="Project" selected>Project</option>
                                            <option value="Date">Date</option>
                                            <option value="Amount">Amount</option>
                                            <option value="Currency">Currency</option>
                                            <option value="Amount NOK">Amount NOK</option>
                                            <option value="From Donor">From Donor</option>
                                            <option value="To Beneficiary">To Beneficiary</option>
                                            <option value="Transaction Type">Transaction Type</option>
                                            <option value="Description">Description</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>

                    <form id="exportProjectsPDF" action="/reports/projects/pdf" method="GET">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden"  name="allFields"  value="1" id="allProjectFieldsPDF"/>
                        <table class="table-radio">
                            <thead><h5>Export projects to PDF</h5></thead>
                            <tr>
                                <td><input type="radio" name="isAllProjects" value="true" checked onclick="showProjects(this)"> All projects<br>
                                    <input type="radio" name="isAllProjects" value="false" onclick="showProjects(this)"> Selected project</td>
                                <td><input type="radio" name="isAllFieldContacts" value="true" checked onclick="showFieldContacts(this)"> All Field Contacts<br>
                                    <input type="radio" name="isAllFieldContacts" value="false" onclick="showFieldContacts(this)"> Selected Field Contact</td>
                                <td colspan="2"><input type="radio" name="isWholePeriod" value="true" checked onclick="showPeriod(this)"> Whole period<br>
                                    <input type="radio" name="isWholePeriod" value="false" onclick="showPeriod(this)"> Selected dates</td>
                            </tr>
                            <tr>
                                <td>
                                    <div id="projectsDiv" class="hidden_div">
                                        <select name="projectId">
                                            <c:forEach var="project" items="${listProjects}">
                                                <option value="${project.id}"><c:out value="${project.name}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div id="fieldContactsDiv" class="hidden_div">
                                        <select name="fieldContactId">
                                            <c:forEach var="fieldContact" items="${listFieldContacts}">
                                                <option value="${fieldContact.id}"><c:out value="${fieldContact.username}: ${fieldContact.lastName} ${fieldContact.firstName}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div id="fromDiv" class="hidden_div">
                                        <label for="startDate">From:</label>
                                        <input type="date" name="fromDate" id="startDate">
                                    </div>
                                </td>
                                <td>
                                    <div id="toDiv" class="hidden_div">
                                        <label for="endDate">To:</label>
                                        <input type="date" name="toDate" id="endDate">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4"><input class="btn btn-primary btn-sm" type="submit" value="Generate PDF" name="Submit"/></td>
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