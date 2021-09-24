$(document).ready(function () {
    const contextPath = $('meta[name="context"]').attr('content') ?? '';

    $('#form-add-comment').on('submit', function (event) {
        event.preventDefault();
        const form = $(this);
        const trainee = $('#add-comment').data('id');
        var CSRF_TOKEN = $('meta[name="csrf-token"]').attr('content');

        $.ajax({
            url: contextPath + "/trainee/" + trainee + "/comment/create",
            type: 'POST',
            data: {
                _csrf: CSRF_TOKEN,
                content: $('#comment').val(),
                trainee: trainee
            },
            success: function (response) {
                Toastify({
                    text: "add comment successfully",
                    duration: 3000,
                    close: true,
                    gravity: "top",
                    position: "center",
                    backgroundColor: "#4fbe87",
                }).showToast();
                $("#myTabContent").html(response);

            }
        });

    });
});