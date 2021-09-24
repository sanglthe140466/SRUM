$(function () {
    $('form').validate({
        errorPlacement: function ($error, $element) {
            $error.insertAfter($element.parent());
        }
    });

    $('#form-reset').on('submit', function (event) {
        event.preventDefault();
        const form = $(this);
        if (form.valid()) {
            $.ajax({
                url: form.attr('action'),
                method: 'POST',
                data: form.serialize(),
                success: function (res) {
                    Toastify({
                        text: res,
                        duration: 10000,
                        close: true,
                        gravity: "bottom",
                        position: "left",
                        backgroundColor: "#4fbe87",
                    }).showToast();
                    const btnSubmit = $('#btn-submit');
                    btnSubmit.prop('disabled', true);
                    let counter = 60;
                    let interval = setInterval(function() {
                        if (counter > 0) {
                            btnSubmit.html(`Reset (${counter})`);
                        } else {
                            clearInterval(interval);
                            btnSubmit.prop('disabled', false);
                            btnSubmit.html('Reset');
                        }

                        counter--;
                    }, 1000);
                },
                error: function (resp) {
                    if (resp.status === 404) {
                        Swal.fire({
                            icon: "error",
                            title: resp.responseText
                        });
                    } else {Swal.fire({
                        icon: "error",
                        title: 'Something went wrong'
                    });}
                }
            })

        }
    })
});