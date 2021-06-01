<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Main Page</title>

    <link rel="icon" type="image/png" href="${contextPath}/resources/img/weblogo.png"/>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

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
                  <p align="center">
                      <img class="img-responsive" src="${contextPath}/resources/img/Familielogo.jpg">
                  </p>
                  <p align="center">
                      <img class="img-responsive" src="${contextPath}/resources/img/slogan_eng.png">
                  </p>
                  <p align="center">
                      <img class="img-responsive" src="${contextPath}/resources/img/slogan_nor.png">
                  </p>
              </div>
          </header>
      </div>
    </div>
  </div>

</body>
</html>