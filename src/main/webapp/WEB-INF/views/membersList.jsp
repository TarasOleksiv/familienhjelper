<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Donors</title>

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
                  <h4>Donors</h4>
                  <p>
                      <a class="btn btn-primary btn-sm" href="/members/new">Add New Donor</a>
                  </p>
              </div>
          </header>
          <form action="<c:url value="/members"/>" method="POST">
              <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

              <table id="memberTable" class="table table-striped table-bordered display">
                  <c:if test="${list.size()>0}">
                      <thead>
                      <tr>
                          <th></th>
                          <th>Name</th>
                          <th>Email</th>
                          <th>Mobile</th>
                          <th>Donor type</th>
                      </tr>
                      </thead>
                  </c:if>
                  <tbody>
                  <c:forEach items="${list}" var="list">
                      <tr>
                          <td>
                              <a href="/members/${list.id}">
                                  <img src="${contextPath}/resources/img/icons8-edit-16.png">
                                  <!--span><i class="far fa-hand-pointer"></i></span-->
                              </a>
                          </td>
                          <td>${list.name}</td>
                          <td>${list.email}</td>
                          <td>${list.mobile}</td>
                          <td>${list.donorType.name}</td>
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