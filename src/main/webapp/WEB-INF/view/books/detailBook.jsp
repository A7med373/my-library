<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="${book.title()}">
    <br>
    <div class="text-center">
        <br>
        <br>
        <p class="h1">${book.title()}</p>
        <br>
        <h4 class="text-muted">Автор: ${book.author()}</h4>
        <br>
        <p class="text-muted"><strong>Жанр:</strong> ${book.genre()}</p>
        <br>
        <p align="left" style="white-space: pre-wrap;">
            <font size="5">${book.review()}</font>
        </p>
        <br>
        <p align="left" class="text-muted">
            <strong>Статус:</strong>
            <c:choose>
                <c:when test="${book.isRead()}">Прочитано</c:when>
                <c:otherwise>Не прочитано</c:otherwise>
            </c:choose>
        </p>
    </div>
</t:mainLayout>
