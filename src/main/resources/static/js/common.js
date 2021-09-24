/**
 * Function format date
 */
function formatDate(date) {
    var date = new Date(date);
    var day = date.getDate(),
        month = date.getMonth() + 1,
        year = date.getFullYear();
    day = day < 10 ? '0' + day : day;
    month = month < 10 ? '0' + month : month;
    return year + '-' + month + '-' + day;
}
/**
 * Display message from controller
 */
function displayMessage() {
    let successSelector = $("#success");
    let errorSelector = $("#error");
    let msgSuccessLength = successSelector.text().length;
    let msgErrorLength = errorSelector.text().length;
    let backgroudColor;
    let message;
    if (successSelector.not(":visible") || errorSelector.not(":visible")) {
        if (msgSuccessLength > 0 || msgErrorLength > 0) {
            if (msgSuccessLength > 0) {
                backgroudColor = "#4fbe87";
                message = successSelector.text();
            } else if (msgErrorLength > 0) {
                backgroudColor = "#f3616d";
                message = errorSelector.text();
            }
            Toastify({
                text: message,
                duration: 5000,
                close: true,
                gravity: "top",
                position: "right",
                backgroundColor: backgroudColor,
            }).showToast();
        }
    }
}