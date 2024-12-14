<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Список администраторов">
    <br>
    <br>
    <br>
    <p class="h1">Администраторы</p>
    <br>
    <div class="admins-card">
        <c:forEach items="${admins}" var="admin">
            <c:if test="${admin.username() != sessionScope.account.username()}">
            <h5>
                <a href="<c:url value="/someone?username=${admin.username()}"/>">${admin.username()}</a>
                <a href="<c:url value="/admins/delete?username=${admin.username()}"/>">
                    <button class="btn btn-outline-secondary btn-sm btn-block">Удалить</button>
                </a>
            </h5>
            </c:if>
            <br>
        </c:forEach>
    </div>
    <br>

    <button class="btn btn-primary" data-bs-toggle="modal"
            data-bs-target="#admins">Управление администраторами
    </button>

    <form id="adminsForm" method="post" action="${pageContext.request.contextPath}/admins/add"
          enctype="application/x-www-form-urlencoded">

        <div class="modal fade" id="admins" tabindex="-1" aria-labelledby="adminsModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="adminsModalLabel">Управление администраторами</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Закрыть"></button>
                    </div>
                    <div class="modal-body">
                        <input type="text" id="username" name="username" class="form-control" minlength="2"
                               placeholder="Введите логин" required/>
                        <br>
                        <p>${error}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <a href="<c:url value="/admins/add"/>">
                            <button type="submit" class="btn btn-primary">Назначить
                            администратором
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</t:mainLayout>