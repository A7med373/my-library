<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:mainLayout title="${account.username()}">
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
                        </div>
                    </div>
                </div>
            </div>
            <div class="px-4 py-3"><h5 class="mb-0">Обо мне</h5>
                <div class="p-4 rounded shadow-sm bg-light"><p>${account.about()}</p>
                </div>
            </div>
            <div class="px-4 py-3"><h5 class="mb-0">Cтатьи ${account.username()}</h5>
                <br>
                <c:forEach items="${post}" var="post">
                    <div class="post-card">
                        <h6 class="post-name"><a
                                href="<c:url value="/posts/detail?id=${post.uuid()}"/>">${post.title()}</a></h6>
                        <br>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</t:mainLayout>
