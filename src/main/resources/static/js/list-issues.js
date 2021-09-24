$(function () {
    const contextPath = $('meta[name="context"]').attr('content') ?? '';
    const classId = $('#classId').data('id');

    let table1 = document.querySelector('#table1');
    let dataTable = new simpleDatatables.DataTable(table1, {
        columns: [
            {select: 0, sortable: false}
        ]
    });

    $('#form-add-issue').on('submit', function (event) {
        event.preventDefault();
        const form = $(this);
        $.ajax({
            url: form.attr('action'),
            type: 'POST',
            data: form.serialize(),
            dataType: 'json',
            success: function (issue) {
                const date = new Date(issue.date);
                let issueRow = `<tr>
                                <td>
                                    <div class="collapsed collapse-title row fw-bold text-black">
                                            <div class="issue-content col-8"
                                                  data-bs-toggle="collapse"
                                                  data-bs-target="#collapse${issue.id}" aria-expanded="true"
                                                  aria-controls="collapse${issue.id}"
                                                  role="button">
                                                <span class="badge bg-light-primary">${date.getDate()}-${date.getMonth() + 1}-${date.getFullYear()}</span>
                                                <span id="heading${issue.id}">${issue.content}</span>
                                            </div>
                                            <div class="col d-flex align-items-start justify-content-end">
                                                <button class="btn btn-sm btn-primary edit-issue" data-id="${issue.id}""><i class="fa fa-edit"></i></button>
                                            </div>
                                        </div>
                                    <div id="collapse${issue.id}" class="pt-1 collapse">
                                        <hr>
                                        <strong>Solution:
                                            <button class="btn btn-sm btn-outline-primary create-solution" data-id="${issue.id}"><i
                                                class="fa fa-plus"></i></button>
                                        </strong>
                                        <div id="solution-list${issue.id}">
                                        </div>
                                    </div>
                                </td>
                            </tr>`;
                form.trigger('reset');
                $('#list-issues').prepend(issueRow);
                $('.edit-issue').on('click', editIssue);
                $('.create-solution').on('click', createSolution);
                Toastify({
                    text: "Add issue successfully",
                    duration: 3000,
                    close: true,
                    gravity: "top",
                    position: "right",
                    backgroundColor: "#4fbe87",
                }).showToast();
            },
            error: function (error) {
                Swal.fire({
                    icon: "error",
                    title: "Something went wrong"
                });
            }
        });
    });

    $('.edit-issue').on('click', editIssue);

    function editIssue(event) {
        event.preventDefault();
        const btnEdit = $(this);
        const issueId = btnEdit.data('id');
        $.get(contextPath + '/class/' + classId + '/issues/' + issueId, function (resp) {
            $('#modalIssueTitle').html('Edit issue');
            $('#modalIssueContent').html(resp);
            $('#modalIssue').modal('show');
            const formEdit = $('#form-edit-issue');
            formEdit.on('submit', function (event) {
                event.preventDefault();
                $.ajax({
                    url: formEdit.attr('action'),
                    type: 'PUT',
                    data: formEdit.serialize(),
                    success: function (issue) {
                        $('#heading' + issueId).html(issue.content);
                        $('#modalIssue').modal('hide');
                        Toastify({
                            text: "Update issue successfully",
                            duration: 3000,
                            close: true,
                            gravity: "top",
                            position: "right",
                            backgroundColor: "#4fbe87",
                        }).showToast();
                    },
                    error: function (error) {
                        Swal.fire({
                            icon: "error",
                            title: "Something went wrong"
                        });
                    }
                })
            });
        })
    }

    $('.create-solution').on('click', createSolution);

    function createSolution(event) {
        event.preventDefault();
        const btnCreateSolution = $(this);
        const issuesId = btnCreateSolution.data('id');
        $.get(contextPath + "/solutions" + '/issues/' + issuesId, function (resp) {
            $('#modalIssueTitle').html('Create solution');
            $('#modalIssueContent').html(resp);
            $('#modalIssue').modal('show');
            const formCreateSolution = $('#form-create-solution');
            formCreateSolution.on('submit', function (event) {
                event.preventDefault();
                $.ajax({
                    url: formCreateSolution.attr('action'),
                    type: 'POST',
                    data: formCreateSolution.serialize(),
                    success: function (solution) {
                        const solutionDate = new Date(solution.date);
                        $('#solution-list' + issuesId).prepend(`<div class="alert alert-secondary row">
                                                <div class="col-10">
                                                    <span class="badge bg-primary">${solutionDate.getDate()}-${solutionDate.getMonth() + 1}-${solutionDate.getFullYear()}</span>
                                                    <span id="solution-content-${solution.id}">${solution.content}</span>
                                                </div>
                                                <div class="col-2">
                                                    <button class="btn btn-sm btn-primary edit-solution" data-id="${solution.id}"><i
                                                                class="fa fa-edit"></i></button>
                                                </div>
                                            </div>`);
                        $('#modalIssue').modal('hide');
                        Toastify({
                            text: "Create solution successfully",
                            duration: 3000,
                            close: true,
                            gravity: "top",
                            position: "right",
                            backgroundColor: "#4fbe87",
                        }).showToast();
                        $('.edit-solution').on('click', editSolution);
                    },
                    error: function (error) {
                        Swal.fire({
                            icon: "error",
                            title: "Something went wrong"
                        });
                    }
                })
            })
        });
    }

    $('.edit-solution').on('click', editSolution);

    function editSolution(event) {
        event.preventDefault();
        const solutionId = $(this).data('id');
        $.get(contextPath + "/solutions/" + solutionId, function (resp) {
            $('#modalIssueTitle').html('Edit solution');
            $('#modalIssueContent').html(resp);
            $('#modalIssue').modal('show');
            const formEditSolution = $('#form-edit-solution');
            formEditSolution.on('submit', function (event) {
                event.preventDefault();
                $.ajax({
                    url: formEditSolution.attr('action'),
                    type: 'PUT',
                    data: formEditSolution.serialize(),
                    success: function (solution) {
                        $('#solution-content-' + solutionId).html(solution.content);
                        $('#modalIssue').modal('hide');
                        Toastify({
                            text: "Update solution successfully",
                            duration: 3000,
                            close: true,
                            gravity: "top",
                            position: "right",
                            backgroundColor: "#4fbe87",
                        }).showToast();
                    },
                    error: function (error) {
                        Swal.fire({
                            icon: "error",
                            title: "Something went wrong"
                        });
                    }
                });
            })
        });
    }
});