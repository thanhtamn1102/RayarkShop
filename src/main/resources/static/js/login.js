$(function() {
    let txtEmail = $('#txtEmail');
    let txtPassword = $('#txtPassword');

    let EmailNontification = $('#EmailNontification');
    let PasswordNontification = $('#PasswordNontification');

    let LoginNotification = $('#LoginNotification');

    function checkEmail() {
        if(txtEmail.val() == "")
        {
            EmailNontification.html("Không được để trống");
            return false;
        }
        EmailNontification.html("");
        return true;
    };

    function checkPassword() {
        if(txtPassword.val() == "")
        {
            PasswordNontification.html("Không được để trống");
            return false;
        }
        PasswordNontification.html("");
        return true;
    };

    txtEmail.blur(function() {checkEmail()});

    txtPassword.blur(function() {checkPassword()});

    txtEmail.keyup(function() {
        EmailNontification.html("");
        LoginNotification.html("")
    });

    txtPassword.keyup(function() {
        PasswordNontification.html("");
        LoginNotification.html("")
    });

});