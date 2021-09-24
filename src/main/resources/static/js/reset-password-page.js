$(function () {
    const form = $('#form-reset-password');

    form.validate({
        rules: {
            'password-confirm': {
                equalTo: '#password'
            }
        },
        errorPlacement: function ($error, $element) {
            $error.insertAfter($element.parent());
        }
    });

    form.on('submit', (e) => {
        e.preventDefault();
        if (form.valid()) {
            $.ajax({
                url: form.attr('action'),
                method: 'PUT',
                data: form.serialize(),
                success: (resp) => {
                    Swal.fire({
                        icon: "success",
                        title: resp
                    }).then(() => {
                        window.location.href = $('meta[name="login"]').attr('href');
                    });
                },
                error: (resp) => {
                    Swal.fire({
                        icon: "error",
                        title: resp.responseText
                    });
                }
            })
        }
    });
});