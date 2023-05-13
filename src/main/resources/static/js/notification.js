function showMessage(message) {
    $("#message").text(message);
    $("#notification").addClass("show");

    setTimeout(function() {
        $("#notification").removeClass("show");
    }, 2000);
}