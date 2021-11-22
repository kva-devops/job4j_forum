<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <i class="nav-link"><c:out value="${user.username}"/></i>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/create'/>">Добавить новую тему</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/logout'/>">Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="row">
        <h2>Пост <c:out value="${post.name}"/></h2>
    </div>
    <div class="row">
        <div class="mb-3">
            <p>Описание: <c:out value="${post.description}"/></p>
        </div>
        <div class="mb-3">
            <p>Дата создания: <c:out value="${post.created.time.toLocaleString()}"/></p>
        </div>
        <div class="mb-3">
            <p>Автор: <c:out value="${post.user.username}"/></p>
        </div>
        <div class="mb-3">
            <c:forEach items="${post.comments}" var="comment">
                <div class="card-content">
                    <p>
                        <strong>${comment}</strong>
                        <c:out value="${comment.created.time.toLocaleString()}"/>
                    </p>
                    <p>
                        ${comment.description}
                    </p>
                </div>
            </c:forEach>
        </div>
        <div class="mb-3">
            <form name="comment" action="<c:url value='/comment/save'/> " method="POST">
                <div class="mb-3">
                    <label for="commentAddId" class="form-label">Новое сообщение</label>
                    <input hidden name="post.id" value="${post.id}">
                    <input type="text" class="form-control" id="commentAddId" name="description" required>
                </div>
                <button type="submit" class="btn btn-primary">Отправить</button>
            </form>
        </div>
    </div>
    <div class="row">
        <a href="<c:url value="/index"/>">Назад</a>
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