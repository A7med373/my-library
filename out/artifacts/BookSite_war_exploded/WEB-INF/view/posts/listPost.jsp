<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:mainLayout title="Статьи">
    <br>
    <br>
    <div class="post-info">
        <br>
        <p class="h1">Статьи</p>
        <br>

        <!-- Отладочное сообщение: количество постов -->
        <p>Всего постов: <c:out value="${fn:length(posts)}"/></p>

        <!-- Проверка на пустой список постов -->
        <c:if test="${empty posts}">
            <p>Нет доступных статей.</p>
        </c:if>

        <!-- Итерация по списку постов -->
        <c:forEach items="${posts}" var="post">
            <div class="post-card">
                <h4 class="post-name">
                    <a href="<c:url value='/posts/detail?id=${post.uuid()}'/>">${post.title()}</a>
                </h4>
                <p class="post-author">Автор: ${post.author().name()} ${post.author().lastname()}</p>
                <p class="post-date">Дата публикации: ${post.date()}</p>
                <p class="post-content">${fn:substring(post.content(), 0, 200)}...</p>

                <c:if test="${sessionScope.account != null && sessionScope.account.role().name() == 'admin'}">
                    <a href="<c:url value='/posts/delete?id=${post.uuid()}'/>">
                        <button class="btn btn-outline-secondary btn-sm btn-block">Удалить</button>
                    </a>
                    <br>
                </c:if>
                <br>
            </div>
        </c:forEach>

    </div>
</t:mainLayout>
