<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="Редактировать комментарий">
    <br>
    <br>
    <br>
    <br>
    <div class="tab-content">
        <div class="tab-pane fade show active" id="editPost" role="tabpanel" aria-labelledby="tab-login">
            <form id="formCreatePost" action="${pageContext.request.contextPath}/comment/edit"
                  method="post">
              <textarea type="text" id="content" name="content" class="form-control" minlength="1" rows="5"
                        cols="10" required>${comment.content()}</textarea>
                <br>
                <button id="submit" type="submit" value="create" class="btn btn-secondary mb-4" data-bs-toggle="modal"
                        data-bs-target="#comment">Изменить комментарий
                </button>
            </form>
            <br>
            <br>
        </div>
    </div>
</t:mainLayout>