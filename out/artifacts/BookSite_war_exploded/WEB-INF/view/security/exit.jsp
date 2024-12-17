<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Выйти">
    <br>
    <div class="modal fade" id="exit" tabindex="-1" aria-labelledby="exitModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exitModalLabel">Выйти</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Закрыть"></button>
                </div>
                <div class="modal-body">
                    Вы уверены, что хотите выйти?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <a href="<c:url value="/sign-out"/>">
                        <button type="submit" value="edit" class="btn btn-primary">Выйти</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center">
        <br>
        <br>
        <br>
        <br>
        <h1 class="display-6">Хотите выйти?</h1>
        <button class="btn btn-outline-secondary btn-sm btn-block" data-bs-toggle="modal"
                data-bs-target="#exit">
            Выйти
        </button>
    </div>
</t:mainLayout>