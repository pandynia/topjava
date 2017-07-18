<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 16.07.2017
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add/edit new meal</title>
</head>
<body>

<form method="POST" action="meals" name="frmAddEdit">
    Meal ID : <input type="text" readonly="readonly" name="id"
                     value="<c:out value="${mealId}" />" /> <br />


    <fmt:parseDate value="${meals.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="date" />

    Date : <input
        type="datetime" name="datetime"
        value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" />" /> <br />

    Description : <input
        type="text" name="description"
        value="<c:out value="${meals.description}" />" /> <br />

    Calories : <input
        type="text" name="calories"
        value="<c:out value="${meals.calories}" />" /> <br />

    <input type="submit" value="Submit" />
</form>
</body>
</html>
