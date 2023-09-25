<%--
  Created by IntelliJ IDEA.
  User: KJL
<<<<<<< HEAD
  Date: 2023-09-20
  Time: 오전 11:25
=======
  Date: 2023-09-15
  Time: 오후 3:43
>>>>>>> bc488c7340b714d7cb7580349222d2827ad89408
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<<<<<<< HEAD
    <title>TO Read</title>
</head>
<body>
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}" >
    </div>
    <div>
        <input type="text" name="dueDate" value="${dto.dueDate}">
    </div>
    <div>
        <input type="checkbox" name="finished" ${dto.finished ? "checked": ""}readonly>
    </div>
    <div>
        <a href="/todo/modify?tno=${dto.tno}">Modify/Remove</a>
        <a href="/todo/list">List</a>
    </div>
=======
    <title>Title</title>
</head>
<body>
        <div>${dto.tno}</div>
        <div>${dto.title}</div>
        <div>${dto.dueDate}</div>
        <div>${dto.finished}</div>
>>>>>>> bc488c7340b714d7cb7580349222d2827ad89408
</body>
</html>
