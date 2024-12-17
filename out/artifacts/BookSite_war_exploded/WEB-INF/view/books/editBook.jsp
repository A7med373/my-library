<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Редактировать книгу">
    <br>
    <br>
    <br>
    <br>
    <div class="col-md-12 d-flex justify-content-center">
        <p style="color:red">${error}</p>
    </div>
    <div class="tab-content">
        <div class="tab-pane fade show active" id="editBook" role="tabpanel" aria-labelledby="tab-editBook">
            <form id="formEditBook" action="${pageContext.request.contextPath}/books/edit" method="post">
                <!-- Скрытое поле для передачи UUID книги -->
                <input type="hidden" name="uuid" value="${book.uuid()}"/>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <input type="text" id="title" name="title" class="form-control" minlength="6" value="${book.title()}" required/>
                        <label class="form-label" for="title">Название</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <input type="text" id="author" name="author" class="form-control" minlength="2" value="${book.author()}" required/>
                        <label class="form-label" for="author">Автор</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <input type="text" id="genre" name="genre" class="form-control" minlength="3" value="${book.genre()}" required/>
                        <label class="form-label" for="genre">Жанр</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">
                        <textarea id="review" name="review" class="form-control" minlength="10" rows="5" cols="100" required>${book.review()}</textarea>
                        <label class="form-label" for="review">Отзыв</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="isRead" name="isRead" <c:if test="${book.isRead()}">checked</c:if>>
                        <label class="form-check-label" for="isRead">Прочитано</label>
                    </div>
                </div>

                <div class="d-flex justify-content-center">
                    <button id="submit" type="submit" class="btn btn-primary mb-4">Сохранить изменения</button>
                </div>
            </form>
            <br>
            <br>
        </div>
    </div>
</t:mainLayout>
