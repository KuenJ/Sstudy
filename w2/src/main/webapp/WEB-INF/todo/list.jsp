<%--
  Created by IntelliJ IDEA.
  User: KJL
  Date: 2023-09-14
  Time: 오후 4:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>List Page</h1>

<ul><c:forEach var="dto" items="${list}">
    <li>${dto}</li>
    </c:forEach>
</ul>

</body>
</html>
