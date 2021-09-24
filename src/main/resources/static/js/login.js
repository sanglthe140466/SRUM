$(function () {
    $('form').validate({
        errorPlacement: function($error, $element) {
            $error.insertAfter($element.parent());
        }
    });
});