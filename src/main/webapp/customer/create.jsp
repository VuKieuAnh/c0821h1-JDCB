<%--
  Created by IntelliJ IDEA.
  User: kieuanh
  Date: 09/11/2021
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Tao moi khach hang</h1>
<form method="post">
    <input type="text" name="id">
    <input type="text" name="name">
    <input type="text" name="address">
    <input type="text" name="email">
    <select name="type">
        <c:forEach items="${typecus}" var="t">
            <option value="${t.id}">${t.name}</option>
        </c:forEach>
    </select>
    <button type="submit">Tao moi</button>
</form>
</body>
</html>
