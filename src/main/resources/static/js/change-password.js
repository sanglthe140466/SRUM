$(function () {
    jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
        return this.optional(element) || value != $(param).val();
    }, "Please specify a different (non-default) value");

    $('form').validate({
        rules: {
            'newPassword': {
                notEqualTo: '#password'
            },
            'password-confirm': {
                equalTo: '#newPassword'
            }
        },
        messages: {
            'newPassword': {
                notEqualTo: 'New password must be different from the old password'
            }
        },
        errorPlacement: function($error, $element) {
            $error.insertAfter($element.parent());
        }
    });
});