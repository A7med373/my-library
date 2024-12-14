$(document).ready(function() {
    let form = $("#searchForm")

    form.submit(() => {
        var title = $("#title").val();

        $.ajax
        ({
            type: "POST",
            data: {title: title},
            url: "/BeerOK/search/add",
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