<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Beneficiaries</title>

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
                  <h4>Beneficiaries</h4>
                  <p>
                      <a class="btn btn-primary btn-sm" href="/beneficiaries/new">Add New Beneficiary</a>
                  </p>
              </div>
          </header>
          <form action="<c:url value="/beneficiaries"/>" method="POST">
              <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

              <table id="beneficiaryTable" class="table table-striped table-bordered display">
                  <thead>
                  <c:if test="${list.size()>0}">
                      <tr>
                          <!--th onclick="sortTable(0,'beneficiaryTable')"></th-->
                          <th></th>
                          <th>Name</th>
                          <th>Income</th>
                          <th>Field Contact</th>
                          <th>Active</th>
                      </tr>
                  </c:if>
                  </thead>
                  <tbody>
                  <c:forEach items="${list}" var="list">
                      <tr>
                          <td>
                              <a href="/beneficiaries/${list.id}">
                                  <img src="${contextPath}/resources/img/icons8-edit-16.png">
                                  <!--span><i class="far fa-hand-pointer"></i></span-->
                              </a>
                          </td>
                          <td>${list.name}</td>
                          <td>${list.income}</td>
                          <td>${list.user.username} ${list.user.lastName} ${list.user.firstName}</td>
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