<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Войти">
    <br>
    <br>
    <br>
    <br>
    <div class="tab-content">
        <div class="tab-pane fade show active" id="signIn" role="tabpanel" aria-labelledby="tab-login">
            <form id="formSignIn" action="${pageContext.request.contextPath}/sign-in" method="post">

                <div class="col-md-12 d-flex justify-content-center">
                    <p style="color:red" >${error}</p>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-4">
                        <input type="text" id="login" name="login" class="form-control" required/>
                        <label class="form-label" for="login">Логин или электронная почта</label>
                    </div>
                </div>

                <div class="col-md-12 d-flex justify-content-center">
                    <div class="form-outline m-lg-2">

                        <input type="password" id="password" name="password" class="form-control" required/>
                        <label class="form-label" for="password">Пароль</label>
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-md-12 d-flex justify-content-center">
                        <div class="form-check mb-3 mb-md-0">
                            <input class="form-check-input" type="checkbox" value="" name="check"/>
                            <label class="form-check-label">Запомнить меня</label>
                        </div>
                    </div>
                </div>

                <div class="d-flex justify-content-center">
                    <button id="signInBtn" type="submit" class="btn btn-primary mb-4">Войти</button>
                </div>
            </form>

            <div class="d-flex justify-content-center">
                <p><a href="<c:url value="/registration"/>">Зарегистрироваться</a></p>
            </div>
            <br>
            <br>
        </div>
    </div>
</t:mainLayout>