<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Профиль">
    <br>
    <br>
    <div class="row py-5 px-4">
        <div class="col-md-5 mx-auto">
            <div class="bg-white shadow rounded overflow-hidden">
                <br>
                <div class="px-4 pt-0 pb-4 cover">
                    <div class="media align-items-end profile-head">
                        <div class="profile mr-3"><img src="${account.avatar()}" width="130"
                                                       class="rounded mb-2 img-thumbnail">
                            <div class="media-body mb-5">
                                <h4 class="mt-0 mb-0">${account.username()}</h4>
                                <p class="small mb-4">${account.name()} ${account.lastname()}</p>
                                <p class="small mb-4">${account.email()}</p>
                            </div>
                            <a href="<c:url value="/profile/edit"/>" class="btn btn-primary btn-sm btn-block">Редактировать
                                профиль</a>
                            <button class="btn btn-outline-secondary btn-sm btn-block" data-bs-toggle="modal"
                                    data-bs-target="#exit">Выйти
                            </button>
                            <br>
                            <br>
                            <c:if test="${sessionScope.account != null && sessionScope.account.role().name() == 'admin'}">
                                <a href="<c:url value="/admins"/>">
                                    <button class="btn btn-primary">Список администраторов</button>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>

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



                <div class="px-4 py-3"><h5 class="mb-0">Обо мне</h5>
                    <div class="p-4 rounded shadow-sm bg-light"><p>${account.about()}</p>
                    </div>
                </div>

                <div class="px-4 py-3"><h5 class="mb-0">Мои статьи</h5>
                    <br>
                    <c:forEach items="${post}" var="post">
                        <div class="post-card">
                            <h6 class="post-name"><a
                                    href="<c:url value="/posts/detail?id=${post.uuid()}"/>">${post.title()}</a></h6>
                            <a href="<c:url value="/posts/edit?id=${post.uuid()}"/>"
                               class="btn btn-primary btn-sm btn-block">Редактировать</a>

                            <a href="<c:url value="/posts/delete?id=${post.uuid()}"/>">
                                <button class="btn btn-outline-secondary btn-sm btn-block">Удалить
                                </button>
                            </a>
                            <br>
                            <br>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
</t:mainLayout>