<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 26.09.2017
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form class="navbar-form navbar-right">
    <select onchange='changeLocale($(this).val())'>
        <option value="ru" <c:if test="${pageContext.response.locale.toString().equals('ru')}">selected</c:if>>ru</option>
        <option value="en" <c:if test="${pageContext.response.locale.toString().equals('en')}">selected</c:if>>en</option>
    </select>
</form:form>

<script type="text/javascript">
    function changeLocale(locale) {
        window.location.search = 'lang='+locale;
    }
</script>
