$("#trainee-form").validate({
    errorElement: "error"
});
$(function() {
    if($('#typeBox').value != 'Fresher'){
        $(".fresher-box").hide();
    }

    $('#typeBox').on('change', function() {
        if ( this.value == 'Fresher')
        {
            $(".fresher-box").show();
        }
        else
        {
            $(".fresher-box").hide();
        }
    });
});