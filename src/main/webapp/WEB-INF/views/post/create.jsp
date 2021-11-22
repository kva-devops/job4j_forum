<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="ru">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
          crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Новая тема - Создание</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <h4>Форум</h4>
    </div>
    <div class="row border bg-light">
        <ul class="nav">
            <c:if test="${user != null}">
                <li class="nav-item">
                    <i class="nav-link"><c:out value="${user.username}"/></i>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/logout'/>">Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="row">
        <div class="col-6">
            <h2>Новая тема</h2>
            <form action="<c:url value='/save'/>" method='POST'>
                <div class="mb-3">
                    <label for="nameIdForm" class="form-label">Название темы</label>
                    <input type="text" class="form-control" id="nameIdForm" name="name">
                    <div id="nameHelp" class="form-text">Придумайте название темы</div>
                </div>
                <div class="mb-3">
                    <label for="descIdForm" class="form-label">Описание темы</label>
                    <textarea class="form-control" id="descIdForm" rows="3" name="description"></textarea>
                    <div id="descHelp" class="form-text">Опишите содержание темы</div>
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
            <div class="mb-3">
                <a href="<c:url value='/index'/>">Отмена</a>
            </div>
        </div>
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
