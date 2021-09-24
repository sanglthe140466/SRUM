$(function () {
    const contextPath = $('meta[name="context"]').attr('content') ?? '';
    let classTable = document.querySelector('#feedback-table');
    let dataTable = new simpleDatatables.DataTable(classTable);

    loadListFeedback();
    loadDropdownClass();

    function loadDropdownClass() {
        $.ajax({
            url: contextPath + "/feedback/class",
            type: 'GET',
            success: function (res) {
                let option = "";
                $.each(res, function (index, obj) {
                    option += `<option value="${obj.id}">${obj.name}</option>`;
                })
                $('#classList').html(option);
            },
            error: function (error) {
                Swal.fire({
                    icon: "error",
                    title: "Something went wrong"
                });
            }
        })
    }

    function loadListFeedback() {
        $.ajax({
            url: contextPath + "/feedback/list",
            type: 'GET',
            success: function (res) {
                let tbody = "";
                $.each(res, function (index, obj) {
                    tbody += `<tr>
                                  <td>${index + 1}</td>
                                  <td><a href="${obj.url}" target="_blank">${obj.name}</a></td>
                                  <td>${formatDate(obj["consultDate"])}</td>
                                  <td>${obj.className}</td>
                              </tr>`;
                })
                $('#listFeedback').html(tbody);
            },
            error: function (error) {
                Swal.fire({
                    icon: "error",
                    title: "Something went wrong"
                });
            }
        })
    }

    const formFeedback = $("#feedback-form");
    formFeedback.on('submit', function (e) {
        e.preventDefault();
        addFeedback();
    });

    function addFeedback() {
        $.ajax({
            url: contextPath + "/feedback/add",
            type: 'POST',
            data: formFeedback.serialize(),
            success: function (res) {
                $('#formAddFeedback').modal('hide');
                Toastify({
                    text: "Create feedback successfully",
                    duration: 3000,
                    close: true,
                    gravity: "top",
                    position: "right",
                    backgroundColor: "#4fbe87",
                }).showToast();
                loadListFeedback();
            },
            error: function (error) {
                Toastify({
                    text: "Create feedback fail",
                    duration: 3000,
                    close: true,
                    gravity: "top",
                    position: "right",
                    backgroundColor: "#f3616d",
                }).showToast();
            }
        })
    }
});