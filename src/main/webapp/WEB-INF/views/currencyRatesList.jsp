<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Currency Rates</title>

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
                  <h4>Currency Rates</h4>
                  <%--<p>
                      <a class="btn btn-primary btn-sm" href="/projects/new">Add New Currency Rate</a>
                  </p>--%>
              </div>
          </header>
          <form action="<c:url value="/currencyrates"/>" method="POST">
              <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

              <table id="currencyRatesTable" class="table table-striped table-bordered display">
                  <c:if test="${list.size()>0}">
                      <thead>
                      <tr>
                          <th></th>
                          <th>Currency</th>
                          <th>Rate (Currency/NOK)</th>
                      </tr>
                      </thead>
                  </c:if>
                  <tbody>
                  <c:forEach items="${list}" var="list">
                      <tr>
                          <td>
                              <a href="/currencyrates/${list.id}/edit">
                                  <img src="${contextPath}/resources/img/icons8-edit-property-16.png">
                              </a>
                          </td>
                          <td>${list.targetCurrency}</td>
                          <td>${list.rate}</td>
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