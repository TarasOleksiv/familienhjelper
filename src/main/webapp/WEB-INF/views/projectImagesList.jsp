<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Project Images</title>

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
                  <h4>Images</h4>
                  <c:if test="${listImages.size()<8}">
                  <c:if test="${!pageContext.request.isUserInRole('ROLE_HELPER')}">
                      <p>
                          <a class="btn btn-primary btn-sm" href="/projects/${projectId}/images/new">Add image</a>
                      </p>
                  </c:if>
                  </c:if>
                  <p>
                      <a class="btn btn-primary btn-sm" href="/projects/${projectId}">Back to Project</a>
                  </p>
              </div>
          </header>

          <div class="row text-center" style="display:flex; flex-wrap: wrap;">
              <c:forEach var="image" items="${listImages}">
                  <div class="col-md-3 col-sm-6">
                      <div class="thumbnail fix">
                          <img src="${image.link}">
                          <div class="caption">
                              <h5 class="limit">${image.description}</h5>
                          </div>
                          <p>
                              <a href="/projects/${projectId}/images/${image.id}" class="btn btn-primary btn-sm">View</a>
                          </p>
                      </div>
                  </div>
              </c:forEach>
          </div>

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