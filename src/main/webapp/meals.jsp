<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.jsp">Home</a></h3>
<h2>Meals</h2>

<table Border = "1" CellPadding = "10" CellSpacing = "2">
    <tr Height = 50 Width = 150>
        <TH>ID</TH>
        <TH>Date Time</TH>
        <TH>Description</TH>
        <TH>Calories</TH>
        <th>Actions</th>
    </tr>

    <c:forEach var="mU" items="${mealsUtils}">
        <c:set var="id" value="${mU.key}"/>
        <c:set var="val" value="${mU.value}"/>
        <c:set var="dateTime" value="${val.dateTime}" />
        <c:set var="cleanedDateTime" value="${fn:replace(dateTime, 'T', ' ')}" />
        <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime" type="both" />


        ${mU.exceed ? "<tr style=\"color: red;\">" : "<tr style=\"color: green;\">"}
        <td>${id}</td>
        <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" /></td>
        <td>${val.description}</td>
        <td>${val.calories}</td>
        <td><a href="meals?action=update">Update</a>/<a href="meals?action=delete&mealId=<c:out value="${id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
