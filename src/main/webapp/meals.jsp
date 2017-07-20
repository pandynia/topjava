<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Meals</title>
    <style>
        .normal {color: green;}
        .exceeded {color: red;}
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table Border = "1" CellPadding = "10" CellSpacing = "2">
    <thead>
    <tr Height = 50 Width = 150>
        <TH>Date Time</TH>
        <TH>Description</TH>
        <TH>Calories</TH>
    </tr>
    </thead>

<c:forEach var="meal" items="${mealsUtils}">
    <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed" />
    <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
        <td><%=TimeUtil.toString(meal.getDateTime())%></td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
