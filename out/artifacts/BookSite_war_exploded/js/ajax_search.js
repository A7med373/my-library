$(document).ready(function() {
    let form = $("#searchForm")

    form.submit(() => {
        var title = $("#title").val();

        $.ajax
        ({
            type: "POST",
            data: {title: title},
            url: "/BookOK/search/add",
            success: function (result) {
                $('#post-card').html(result);
            },
            error: function () {
                alert("error");
            }
        });
        return false
    });
});