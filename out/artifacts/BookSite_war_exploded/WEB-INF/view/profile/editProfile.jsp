<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Редактировать профиль">
    <br>
    <br>
    <br>
    <br>
    <div class="tab-content">
        <div class="tab-pane fade show active" id="editProfile" role="tabpanel" aria-labelledby="tab-login">
            <form id="formEdit" action="${pageContext.request.contextPath}/profile/edit"
                  enctype="multipart/form-data" method="post">

                <div class="col-md-12 d-flex justify-content-center">
                    <p style="color:red">${error}</p>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <input type="text" id="login" name="login" class="form-control" minlength="6"
                               value="${account.username()}" required>
                        <label class="form-label" for="login">Логин</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="text" id="name" name="name" class="form-control" minlength="2"
                               value="${account.name()}" required/>
                        <label class="form-label" for="name">Имя</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="text" id="lastname" name="lastname" class="form-control" minlength="2"
                               value="${account.lastname()}" required/>
                        <label class="form-label" for="lastname">Фамилия</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="text" id="email" name="email" class="form-control" minlength="5"
                               value="${account.email()}" required/>
                        <label class="form-label" for="email">Электронная почта</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <textarea type="text" id="about" name="about" class="form-control" minlength="1" maxlength="70" rows="5"
                                  cols="30" required>${account.about()}</textarea>
                        <label class="form-label" for="about">Обо мне</label>
                    </div>
                </div>

                <div class="d-flex justify-content-center">
                    <input id="image" name="image" type="file" accept=".jpg, .png, .jpeg"
                           class="btn btn-secondary mb-4">Изменить фото профиля</input>
                </div>

                <div class="d-flex justify-content-center">
                    <button id="update" type="submit" class="btn btn-primary mb-4">Сохранить изменения</button>
                </div>
            </form>
            <br>
            <br>
        </div>
    </div>
</t:mainLayout>