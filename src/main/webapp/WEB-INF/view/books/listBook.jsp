<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:mainLayout title="Список книг">
    <br>
    <br>
    <p class="h1 text-center">Книги по жанрам</p>
    <br>

    <!-- Фантастика -->
    <section>
        <p class="h2">Фантастика</p>
        <div class="book-list">
                <%--@elvariable id="booksFiction" type="java.util.List"--%>
            <c:forEach items="${booksFiction}" var="book">
                <div class="book-item">
                    <h4><a href="<c:url value='/books/detail?id=${book.uuid()}'/>">${book.title()}</a></h4>
                    <p>Автор: ${book.author()}</p>
                    <p>Жанр: ${book.genre()}</p>
                    <c:if test="${sessionScope.account != null && sessionScope.account.role().name() == 'admin'}">
                        <a href="<c:url value='/books/edit?id=${book.uuid()}'/>">
                            <button class="btn btn-primary btn-sm">Редактировать</button>
                        </a>
                        <a href="<c:url value='/books/delete?id=${book.uuid()}'/>">
                            <button class="btn btn-danger btn-sm">Удалить</button>
                        </a>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </section>

    <!-- Научная литература -->
    <section>
        <p class="h2">Научная литература</p>
        <div class="book-list">
                <%--@elvariable id="booksNonFiction" type="java.util.List"--%>
            <c:forEach items="${booksNonFiction}" var="book">
                <div class="book-item">
                    <h4><a href="<c:url value='/books/detail?id=${book.uuid()}'/>">${book.title()}</a></h4>
                    <p>Автор: ${book.author()}</p>
                    <p>Жанр: ${book.genre()}</p>
                    <c:if test="${sessionScope.account != null && sessionScope.account.role().name() == 'admin'}">
                        <a href="<c:url value='/books/edit?id=${book.uuid()}'/>">
                            <button class="btn btn-primary btn-sm">Редактировать</button>
                        </a>
                        <a href="<c:url value='/books/delete?id=${book.uuid()}'/>">
                            <button class="btn btn-danger btn-sm">Удалить</button>
                        </a>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </section>

    <!-- Фэнтези -->
    <section>
        <p class="h2">Фэнтези</p>
        <div class="book-list">
                <%--@elvariable id="booksFantasy" type="java.util.List"--%>
            <c:forEach items="${booksFantasy}" var="book">
                <div class="book-item">
                    <h4><a href="<c:url value='/books/detail?id=${book.uuid()}'/>">${book.title()}</a></h4>
                    <p>Автор: ${book.author()}</p>
                    <p>Жанр: ${book.genre()}</p>
                    <c:if test="${sessionScope.account != null && sessionScope.account.role().name() == 'admin'}">
                        <a href="<c:url value='/books/edit?id=${book.uuid()}'/>">
                            <button class="btn btn-primary btn-sm">Редактировать</button>
                        </a>
                        <a href="<c:url value='/books/delete?id=${book.uuid()}'/>">
                            <button class="btn btn-danger btn-sm">Удалить</button>
                        </a>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </section>

    <c:if test="${sessionScope.account != null && sessionScope.account.role().name() == 'admin'}">
        <a href="<c:url value='/books/add'/>">
            <button class="btn btn-success mt-3">Добавить книгу</button>
        </a>
    </c:if>
</t:mainLayout>
