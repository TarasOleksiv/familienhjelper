<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Main Page</title>
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
    	body { padding-top: 10px; }
    </style>
</head>
<body>

<div class="container">
  	<div class="row">
      <div class="col-lg-2 col-sm-2">
          <jsp:include page="includes/menu.jsp" />
      </div>
      <div class="col-lg-10 col-sm-10">
          <div class="thumbnail">
              <img src="${contextPath}/resources/img/logo.jpg">
          </div>
      </div>
    </div>
  </div>

</body>
</html>