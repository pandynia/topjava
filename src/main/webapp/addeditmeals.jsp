<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 16.07.2017
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <title>Add or edit user</title>
</head>
<body>
<form method="POST" action='MealServlet' name="frmAddEditMeals">
    ID meal : <input type="text" readonly="readonly" name="userid"
                     value="<c:out value="${meals.id}" />" /> <br />
    Date Time : <input
        type="text" name="firstName"
        value="<fmt:formatDate pattern="MM/dd/yyyy" value="${user.dob}" />" /> <br />
    Description : <input
        type="text" name="description"
        value="<c:out value="${meals.description}" />" /> <br />
    Calories : <input
        type="text" name="calories"
        value=""<c:out value="${meals.calories}" />" /> <br />
    <input type="submit" value="Submit" />
</form>

</body>
</html>
