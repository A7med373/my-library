<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="${post.title()}">
    <br>
    <div class="text-center">
        <br>
        <br>
        <p class="h1">${post.title()}</p>
        <br>
        <p class="h4"><a
                href="<c:url value="/someone?username=${post.author().username()}"/>">${post.author().username()}</a>
        </p>
        <br>
        <p class="h6">Дата публикации: ${post.date()}</p>
        <img src="${post.image()}" class="rounded img-thumbnail"/>
        <br>
        <br>
        <p align="left" style="white-space: pre-wrap;">
            <font size="5">${post.content()}</font>
        </p>
        <br>

        <form id="formCreateComment" action="${pageContext.request.contextPath}/comment/add?postId=${post.uuid()}"
              method="post">
            <textarea type="text" id="content" name="content" class="form-control" minlength="1" rows="5"
                      cols="10" required></textarea>
            <br>
            <button id="submit" type="submit" value="create" class="btn btn-secondary mb-4">Оставить комментарий
            </button>
        </form>
    </div>

    <div class="comment-card">
        <c:forEach items="${comment}" var="comment">
            <h5 class="comment-author"><a
                    href="<c:url value="/someone?username=${comment.author().username()}"/>">${comment.author().username()}</a>
            </h5>
            <h6 class="comment-content">${comment.date().getDate()}.${comment.date().getMonth() + 1}.${comment.date().getYear() + 1900}</h6>
            <h4 class="comment-content">${comment.content()}</h4>
            <c:if test="${comment.isEdit() == true}">
                <a href="<c:url value="/comment/edit?id=${comment.uuid()}"/>">
                    <button class="btn btn-primary btn-sm btn-block">Редактировать</button>
                </a>
            </c:if>
            <c:if test="${comment.isEdit() == true || (sessionScope.account != null &&
            sessionScope.account.role().name() == 'admin')}">
                <a href="<c:url value="/comment/delete?id=${comment.uuid()}"/>">
                    <button class="btn btn-outline-secondary btn-sm btn-block">Удалить</button>
                </a>
            </c:if>
            <br>
            <br>
        </c:forEach>
    </div>
</t:mainLayout>

