<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 20.12.2017
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Edit Project</title>

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

                    <form id="projectEdit" class="form-edit" action="<c:url value="/projects/${project.id}"/>" method="POST">
                        <input type="hidden" name="_method" value="put"/>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        <input type="hidden" name="projectId" value="${project.id}"/>

                        <table class="table-input">
                            <CAPTION>Project details:
                                <p><strong>${project.name}</strong></p>
                            </CAPTION>
                            <tr>
                                <td>Name</td>
                                <td>
                                    <input type="text" name="name" value="${project.name}">
                                    <div class="has-error">${messages.name}</div>
                                </td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td>
                                    <textarea rows="6" cols="25" name="description" form="projectEdit" maxlength="2000">${project.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Start date</td>
                                <td><input type="date" name="startDate" value="${project.startDate}"></td>
                            </tr>
                            <tr>
                                <td>Stop date</td>
                                <td><input type="date" name="stopDate" value="${project.stopDate}"></td>
                            </tr>
                            <tr>
                                <td>Status</td>
                                <td>
                                    <select name="statusName">
                                        <c:forEach var="status" items="${listStatuses}">
                                            <c:choose>
                                                <c:when test="${project.status.name == status.name}">
                                                    <option value="${status.name}" selected><c:out value="${status.name}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${!pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
                                                        <option value="${status.name}"><c:out value="${status.name}"/></option>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Field Contact</td>
                                <td>
                                    <select name="fieldContactId">
                                        <c:choose>
                                            <c:when test="${pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
                                                <option value="${userPrincipal.id}"><c:out value="${userPrincipal.username}: ${userPrincipal.lastName} ${userPrincipal.firstName}"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option></option>
                                                <c:forEach var="user" items="${listFieldContactUsers}">
                                                    <c:choose>
                                                        <c:when test="${project.fieldContactUser.username == user.username}">
                                                            <option value="${user.id}" selected><c:out value="${user.username}: ${user.lastName} ${user.firstName}"/></option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${user.id}"><c:out value="${user.username}: ${user.lastName} ${user.firstName}"/></option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>
                            </tr>
                            <c:if test="${!pageContext.request.isUserInRole('ROLE_FIELDCONTACT')}">
                                <tr>
                                    <td>FU responsible</td>
                                    <td>
                                        <select name="fuId">
                                            <option></option>
                                            <c:forEach var="user" items="${listFuUsers}">
                                                <c:choose>
                                                    <c:when test="${project.fuUser.username == user.username}">
                                                        <option value="${user.id}" selected><c:out value="${user.username}: ${user.lastName} ${user.firstName}"/></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${user.id}"><c:out value="${user.username}: ${user.lastName} ${user.firstName}"/></option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>Feedback</td>
                                <td>
                                    <textarea rows="6" cols="25" name="feedback" form="projectEdit" maxlength="2000">${project.feedback}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>Link to images</td>
                                <td>
                                    <textarea rows="6" cols="25" name="imageFolderLink" form="projectEdit" maxlength="2000">${project.imageFolderLink}</textarea>
                                </td>
                            </tr>

                            <tr>
                                <td>Active</td>
                                <c:choose>
                                    <c:when test="${project.active}">
                                        <c:choose>
                                            <c:when test="${pageContext.request.isUserInRole('ROLE_FU')}">
                                                <td><input type="checkbox" name="active" value="true" checked onclick="return false;"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><input type="checkbox" name="active" value="true" checked></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${pageContext.request.isUserInRole('ROLE_FU')}">
                                                <td><input type="checkbox" name="active" value="true" onclick="return false;"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><input type="checkbox" name="active" value="true"></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </tr>

                            <tr>
                                <td><a class="btn btn-cancel btn-sm btn-block" href="/projects/${project.id}">Cancel</a></td>
                                <td><input class="btn btn-success btn-sm btn-block" type="submit" value="Save" name="Save"/></td>
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
