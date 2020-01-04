<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Transactions</title>

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
                  <h4>Project: ${project.name}</h4>
                  <h5>Balance: ${project.balance==null?0.00:project.balance} NOK</h5>
                  <form action="/projects/${projectId}/transactions/new" method="GET">
                      <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                      <table class="table-radio">
                          <tr>
                              <td><input class="btn btn-primary btn-sm" type="submit" value="Add transaction" name="Submit"/></td>
                              <td>
                                  <input type="radio" name="isIncome" value="true" checked> Income<br>
                                  <input type="radio" name="isIncome" value="false"> Outcome
                              </td>
                          </tr>
                      </table>
                  </form>
                  <table class="table-padding">
                      <tr>
                          <td><a class="btn btn-primary btn-sm" href="/projects/${projectId}">Back to Project</a></td>
                      </tr>
                  </table>
                  <h4>Transactions</h4>
              </div>
          </header>

          <form action="<c:url value="/projects"/>" method="POST">
              <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

              <table id="transactionTable" class="table table-striped table-bordered display">
                  <c:if test="${listTransactions.size()>0}">
                      <thead>
                      <tr>
                          <th></th>
                          <th>Date</th>
                          <th>Amount NOK</th>
                          <th>From donor</th>
                          <th>To beneficiary</th>
                      </tr>
                      </thead>
                  </c:if>
                  <tbody>
                  <c:forEach items="${listTransactions}" var="list">
                      <tr>
                          <td>
                              <a href="/projects/${project.id}/transactions/${list.id}">
                                  <img src="${contextPath}/resources/img/icons8-edit-16.png">
                              </a>
                          </td>
                          <td><fmt:formatDate value="${list.tradingDate}" pattern="dd.MM.yyyy" /></td>
                          <td>${list.amountNOK}</td>
                          <td>${list.member.name}</td>
                          <td>${list.beneficiary.name}</td>
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