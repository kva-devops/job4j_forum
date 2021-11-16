<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="ru">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
          crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Форум</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h4>Форум</h4>
    </div>
    <div class="row border bg-light">
        <ul class="nav">
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="/login">Войти</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <i class="nav-link"><c:out value="${user.name}"/></i>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Выйти</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/create'/>">Добавить новую тему</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Тема</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${posts}" var="post">
                <tr>
                    <td>
                        <a href="<c:url value='/post?id=${post.id}'/> " ><c:out value="${post.name}"/></a>
                    </td>
                    <td>
                        <c:out value="${post.description}" />
                    </td>
                    <td>
                        <c:out value="${post.created.time.toLocaleString()}" />
                    </td>
                    <td>
                        <p>Автор: <c:out value="${post.author.name}" /></p>
                    </td>
                    <td>
                        <a href="<c:out value='/update?id=${post.id}'/>">Редактировать</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
        integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
        crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
        integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
        crossorigin="anonymous">
</script>
</body>
</html>