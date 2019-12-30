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
                  <h4>Image</h4>
                  <p>
                      <a class="btn btn-primary btn-sm" href="/projects/${projectId}/images">Back to Images</a>
                  </p>
              </div>
          </header>

          <div class="row text-center" style="display:flex; flex-wrap: wrap;">
              <div class="col-md-9">
                  <div class="thumbnail">
                      <img class="img-responsive" src="${image.link}">
                      <div class="caption limit">${image.description}</div>
                      <div class="caption">
                      <form style="display: inline" action="/projects/${projectId}/images/${image.id}" method="POST">
                          <input type="hidden" name="_method" value="delete"/>
                          <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                          <input type="hidden" name="imageId" value="${image.id}"/>

                          <a class="btn btn-warning btn-sm" href="/projects/${projectId}/images/${image.id}/edit">Edit image</a>
                          <button class="btn btn-sm btn-danger">Delete image</button>
                      </form>
                      </div>
                  </div>
              </div>
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