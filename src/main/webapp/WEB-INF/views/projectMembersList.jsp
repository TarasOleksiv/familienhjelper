<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Project Donors</title>

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
                  <h4>Donors</h4>
                  <c:if test="${listDonors.size()<8}">
                        <c:if test="${!pageContext.request.isUserInRole('ROLE_HELPER')}">
                            <p>
                                <a class="btn btn-primary btn-sm" href="/projects/${projectId}/donors/new">Add Donor</a>
                            </p>
                        </c:if>
                  </c:if>
                  <p>
                      <a class="btn btn-primary btn-sm" href="/projects/${projectId}">Back to Project</a>
                  </p>
              </div>
          </header>
          <form action="<c:url value="/projects/${projectId}/donors"/>" method="POST">
              <input type="hidden" name="_method" value="delete"/>
              <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

              <table id="projectMemberTable" class="table table-striped table-bordered display">
                  <thead>
                  <c:if test="${listDonors.size()>0}">
                      <tr>
                          <c:if test="${!pageContext.request.isUserInRole('ROLE_HELPER')}">
                            <th></th>
                          </c:if>
                          <th>Name</th>
                          <th>Pledge</th>
                          <th>Start pledge</th>
                          <th>Stop pledge</th>
                      </tr>
                  </c:if>
                  </thead>
                  <tbody>
                  <c:forEach items="${listDonors}" var="list">
                      <tr>
                          <c:if test="${!pageContext.request.isUserInRole('ROLE_HELPER')}">
                              <td>
                                  <a href="/projects/${projectId}/donors/${list.id}/delete">
                                      <img src="${contextPath}/resources/img/icons8-delete-16.png">
                                  </a>
                              </td>
                          </c:if>
                          <td>${list.member.name}</td>
                          <td>${list.pledge}</td>
                          <td><fmt:formatDate value="${list.startPledge}" pattern="dd.MM.yyyy" /></td>
                          <td><fmt:formatDate value="${list.stopPledge}" pattern="dd.MM.yyyy" /></td>
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