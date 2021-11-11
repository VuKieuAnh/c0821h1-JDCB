<%--
  Created by IntelliJ IDEA.
  User: kieuanh
  Date: 09/11/2021
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<H1>Danh sach khach hang</H1>
<table>
    <c:forEach var="c" items="${ds}">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.address}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
