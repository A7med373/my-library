<%@ tag description="Default Layout Tag" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="title" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/main.css"/>">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background-color: #e6f2ff">
    <div class="container-fluid">
        <a class="navbar-brand">BookWorld</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="<c:url value="/"/>">О сайте</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/sign-in"/>">Войти</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/profile"/>">Профиль</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/books/list"/>">Книги</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/posts/list"/>">Обзоры</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/create"/>">Написать обзор</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/search"/>">Поиск</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <jsp:doBody/>
</div>
</body>
</html>
