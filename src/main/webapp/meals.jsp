<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<c:forEach var="mU" items="${mealsUtils}">

    <c:set var = "dateTime" value="${mU.dateTime}" />
    <c:set var="cleanedDateTime" value="${fn:replace(dateTime, 'T', ' ')}" />
    <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime" type="both" />
    <c:set var = "exceed" value="${mU.exceed}" />

    <c:if test="${exceed == false}">
        <span style="color: red; ">
            <p>
              <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
                ${mU.description}
                ${mU.calories}
            </p>
        </span>
    </c:if>

    <c:if test="${exceed == true}">
        <span style="color: black; ">
            <p>
              <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
                ${mU.description}
                ${mU.calories}
            </p>
        </span>
    </c:if>

</c:forEach>
</body>
</html>
