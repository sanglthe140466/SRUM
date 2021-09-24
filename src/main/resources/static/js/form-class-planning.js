/**
 * Validate form planning class
 */
function addFormValidate() {
    $("#form-class").validate({
        rules: {
            name: {
                required: true,
                maxlength: 100
            },
            openDate: {
                required: true,
                validateOpenDate: true,
            },
            endDate: {
                required: true,
                validateEndDate: true,

            },
            planCount: {
                required: true,
                digits: true
            }
        },
        messages: {
            name: {
                required: " Please input class name",
                maxlength: "Class name no more than 100 characters"
            },
            openDate: {
                required: " Please input open date",
            },
            endDate: {
                required: " Please input end date",

            },
            planCount: {
                required: " Please input plan count",
                digits: "Plan count must be the number"
            }
        },
        errorElement: "error"
    });
}

/**
 * Validate open date
 */
$.validator.addMethod("validateOpenDate", function () {
    const openDate = $("#openDate").val();
    if ((Date.parse(openDate) - Date.now()) > 0) {
        return true;
    }
    return false;
}, "Open date must be after today's date");

/**
 * Validate open date
 */
$.validator.addMethod("validateEndDate", function () {
    const openDate = $("#openDate").val();
    const endDate = $("#endDate").val();
    if (((Date.parse(endDate) - Date.parse(openDate)) > 0) && ((Date.parse(endDate) - Date.now()) > 0)) {
        return true;
    }
    return false;
}, "End date must be after open date and today's date");




