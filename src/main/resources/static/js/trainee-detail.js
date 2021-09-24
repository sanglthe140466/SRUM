$(document).ready(function () {
    const contextPath = $('meta[name="context"]').attr('content') ?? '';
    const traineeId = $('#traineeId').data('id');
    $("#comments").click(function () {
        $.get({
            url: contextPath + "/trainee/" + traineeId + "/comment",
            success: function (response) {
                $("#myTabContent").html(response);
            }
        });
    });
    $("#attendance").click(function () {
        $.get({
            url: contextPath + "/trainee/" + traineeId + "/attendance",
            success: function (response) {
                $("#myTabContent").html(response);
            }
        });
    });
    $("#interview-result").click(function () {
        $.get({
            url: contextPath + "/trainee/" + traineeId + "/interview-result",
            success: function (response) {
                $("#myTabContent").html(response);
            }
        });
    });
    $("#score").click(function () {
        $.get({
            url: contextPath + "/trainee/" + traineeId + "/score",
            success: function (response) {
                $("#myTabContent").html(response);
            }
        });
    });
    $("#mistakes").click(function () {
        $.get({
            url: contextPath + "/trainee/" + traineeId + "/mistake",
            success: function (response) {
                $("#myTabContent").html(response);
            }
        });
    });

    $('#add_mistake').on('click', function () {
        $.get(contextPath + "/trainee/" + traineeId + "/mistake/create", function (res) {
            $("#modalMistakeContent").html(res);
            $("#modalMistakeTile").html('Add mistake');
            $("#modalMistake").modal('show');
            const formMistake = $("#form-mistake-add");
            formMistake.validate();
            formMistake.on('submit', submitMistakeForm);
        });
    });

    function submitMistakeForm(event) {
        const formMistake = $(this);
        event.preventDefault();
        if (formMistake.valid()) {
            $.ajax({
                url: formMistake.attr('action'),
                type: 'POST',
                data: formMistake.serialize(),
                dataType: 'json',
                success: function (mistake) {
                    Toastify({
                        text: "Add mistake successfully",
                        duration: 3000,
                        close: true,
                        gravity: "top",
                        position: "center",
                        backgroundColor: "#4fbe87",
                    }).showToast();
                    $("#modalMistake").modal('hide');
                    $('#mistakes').trigger('click');
                },
                error: function (response) {
                    if (response.status === 400) {
                        let errorMessages = JSON.parse(response.responseText);
                        let errorUl = $(`<ul class="alert alert-danger"></ul>`);
                        Object.values(errorMessages).forEach(value => {
                            errorUl.append(`<li>${value}</li>`);
                        });
                        $('#form-mistake-error').html(errorUl);
                    } else if (response.status === 404) {
                        Swal.fire({
                            icon: "error",
                            title: response.responseText
                        });
                    } else {
                        Swal.fire({
                            icon: "error",
                            title: "Something went wrong"
                        });
                    }
                }
            });
        }
    }

    let mistakeId;
    $('.update-mistake').on('click', function () {
        const btnUpdateMistake = $(this);
        mistakeId = btnUpdateMistake.data('id');
        $.get(contextPath + "/trainee/" + traineeId + "/mistake/update/" + mistakeId, function (res) {
            $("#modalMistakeContent").html(res);
            $("#modalMistakeTile").html('Update mistake');
            $("#modalMistake").modal('show');
            const formMistake = $("#form-mistake-add");
            formMistake.validate();
            formMistake.on('submit', submitMistakeFormUpdate);
        });
    });

    function submitMistakeFormUpdate(event) {
        const formMistake = $(this);
        event.preventDefault();
        if (formMistake.valid()) {
            $.ajax({
                url: contextPath + "/trainee/" + traineeId + "/mistake/update",
                type: 'PUT',
                data: formMistake.serialize(),
                dataType: 'json',
                success: function (mistake) {
                    Toastify({
                        text: "Update mistake successfully",
                        duration: 3000,
                        close: true,
                        gravity: "top",
                        position: "center",
                        backgroundColor: "#4fbe87",
                    }).showToast();
                    $("#modalMistake").modal('hide');
                    $('#mistakes').trigger('click');
                },
                error: function (response) {
                    if (response.status === 400) {
                        let errorMessages = JSON.parse(response.responseText);
                        let errorUl = $(`<ul class="alert alert-danger"></ul>`);
                        Object.values(errorMessages).forEach(value => {
                            errorUl.append(`<li>${value}</li>`);
                        });
                        $('#form-mistake-error').html(errorUl);
                    } else if (response.status === 404) {
                        Swal.fire({
                            icon: "error",
                            title: response.responseText
                        });
                    } else {
                        Swal.fire({
                            icon: "error",
                            title: "Something went wrong"
                        });
                    }
                }
            });
        }
    }

    $('.delete-Mistake').on('click', deleteMistake);

    function deleteMistake() {
        const btnDelete = $(this);
        const mistakeId = btnDelete.data('id');
        swal({
            title: 'Are you sure?',
            text: "you still want to delete this?",
            icon: 'warning',
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    var CSRF_TOKEN = $('meta[name="csrf-token"]').attr('content');
                    $.ajax({
                        type: "DELETE",
                        url: contextPath + "/trainee/" + traineeId + "/mistake/" + mistakeId + "/delete",
                        data: {_csrf: CSRF_TOKEN},
                        success: function (response) {
                            $('#mistakes').trigger('click');
                            Toastify({
                                text: "Delete mistake successfully",
                                duration: 3000,
                                close: true,
                                gravity: "top",
                                position: "center",
                                backgroundColor: "#4fbe87",
                            }).showToast();
                        }

                    })
                }
            });
    }

});

