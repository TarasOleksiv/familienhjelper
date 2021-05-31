<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Projects</title>

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
                  <h4>Projects</h4>
                  <c:if test="${not empty pageContext.request.userPrincipal}">
                      <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                          <p>
                              <a class="btn btn-primary btn-sm" href="/projects/donation">Recalculate Donation</a>
                          </p>
                          <p>
                              <a class="btn btn-primary btn-sm" href="/projects/expense">Recalculate Expense</a>
                          </p>
                      </c:if>
                  </c:if>
                  <div class="row">
                      <div class="col-xs-4">
                          <c:if test="${!pageContext.request.isUserInRole('ROLE_HELPER')}">
                              <a class="btn btn-primary btn-sm" href="/projects/new">Add New Project</a>
                          </c:if>
                      </div>
                      <div class="col-xs-8">
                          <strong>Total Balance: ${totalBalance} NOK</strong>
                      </div>
                  </div>
                  <p></p>
              </div>
          </header>
          <form action="<c:url value="/projects"/>" method="POST">
              <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

              <table id="projectTable" class="table table-striped table-bordered display">
                  <c:if test="${list.size()>0}">
                      <thead>
                      <tr>
                          <th></th>
                          <th>Name</th>
                          <th>Balance</th>
                          <th>Donation</th>
                          <th>Expense</th>
                          <th>FU</th>
                          <th>Field contact</th>
                          <th>Status</th>
                          <th>Active</th>
                      </tr>
                      </thead>
                  </c:if>
                  <tbody>
                  <c:forEach items="${list}" var="list">
                      <tr>
                          <td>
                              <a href="/projects/${list.id}">
                                  <img src="${contextPath}/resources/img/icons8-edit-16.png">
                              </a>
                          </td>
                          <td>${list.name}</td>
                          <td>${list.balance}</td>
                          <td>${list.donation}</td>
                          <td>${list.expense}</td>
                          <td>${list.fuUser.username}</td>
                          <td>${list.fieldContactUser.username}</td>
                          <td>${list.status.name}</td>
                          <td>
                              <c:if test="${list.active}">
                                  <img src="${contextPath}/resources/img/icons8-green-circle-16.png">
                              </c:if>
                          </td>
                      </tr>
                  </c:forEach>
                  </tbody>
              </table>
          </form>
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