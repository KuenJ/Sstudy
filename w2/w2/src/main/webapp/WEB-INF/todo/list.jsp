<%--
  Created by IntelliJ IDEA.
  User: KJL
<<<<<<< HEAD
  Date: 2023-09-19
  Time: 오후 4:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TodoList</title>
</head>
<body>
<h1>Todo List</h1>
<h2>${loginInfo}</h2><h3>${loginInfo.mname}</h3>
<ul>
    <c:forEach items="${dtoList}" var="dto">


        <li>
            <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "DONE": "NOT YET"}</span>
        </li>
    </c:forEach>
</ul>


<form action="/logout" method="post">
    <button type="submit">logout</button>
</form>


=======
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

>>>>>>> bc488c7340b714d7cb7580349222d2827ad89408
</body>
</html>
