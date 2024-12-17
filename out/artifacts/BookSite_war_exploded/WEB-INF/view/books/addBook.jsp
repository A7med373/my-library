<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Добавить книгу">
    <br>
    <br>
    <br>
    <br>
    <div class="col-md-12 d-flex justify-content-center">
        <p style="color:red">${error}</p>
    </div>
    <div class="tab-content">
        <div class="tab-pane fade show active" id="editPost" role="tabpanel" aria-labelledby="tab-login">
            <form id="formCreateBook" action="${pageContext.request.contextPath}/books/add" method="post">
            <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <textarea type="text" id="title" name="title" class="form-control" minlength="6" cols="100"
                                  required></textarea>
                        <label class="form-label" for="title">Название</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <textarea type="text" id="author" name="author" class="form-control" minlength="2" cols="100"
                                  required></textarea>
                        <label class="form-label" for="author">Автор</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <textarea type="text" id="genre" name="genre" class="form-control" minlength="3" cols="100"
                                  required></textarea>
                        <label class="form-label" for="genre">Жанр</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">
                        <textarea type="text" id="review" name="review" class="form-control" minlength="10" rows="5"
                                  cols="100" required></textarea>
                        <label class="form-label" for="review">Отзыв</label>
                    </div>
                </div>

                <div class="d-flex justify-content-center">
                    <button id="submit" type="submit" class="btn btn-primary mb-4">Добавить книгу</button>
                </div>
            </form>
            <br>
            <br>
        </div>
    </div>
</t:mainLayout>
