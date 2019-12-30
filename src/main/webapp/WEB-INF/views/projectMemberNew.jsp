<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 09.12.2019
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Add Project Donor</title>

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
        <div class=class="col-sm-10">
            <c:if test="${not empty pageContext.request.userPrincipal}">

                    <form class="form-edit" action="<c:url value="/projects/${project.id}/donors"/>" method="POST">
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <table class="table-input">
                            <CAPTION>Project ${project.name}</CAPTION>
                            <CAPTION>Add donor</CAPTION>
                            <tr>
                                <td>Donor</td>
                                <td>
                                    <select name="memberId">
                                        <c:forEach var="member" items="${listMembers}">
                                            <option value="${member.id}"><c:out value="${member.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Pledge</td>
                                <td><input type="number" step="0.01" min="0" name="pledge"></td>
                            </tr>
                            <tr>
                                <td>Start pledge</td>
                                <td><input type="date" name="startPledge"></td>
                            </tr>
                            <tr>
                                <td>Stop pledge</td>
                                <td><input type="date" name="stopPledge"></td>
                            </tr>
                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/projects/${project.id}/donors">Cancel</a></td>
                                <td><input class="btn btn-success btn-sm btn-block" type="submit" value="Submit" name="Add"/></td>
                            </tr>
                        </table>
                    </form>

            </c:if>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
