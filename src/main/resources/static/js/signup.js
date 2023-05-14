$(function() {
    let signupNotification = $('#SignupNotification');

    let txtFullName= $('#txtFullName');
    let txtPhone = $('#txtPhone');
    let txtEmail = $('#txtEmail');
    let txtPassword = $('#txtPassword');
    let txtConfirmPassword = $('#txtConfirmPassword');

    let FullNameNotification = $('#FullNameNotification');
    let PhoneNotification = $('#PhoneNotification');
    let EmailNontification = $('#EmailNontification');
    let PasswordNontification = $('#PasswordNontification');
    let ConfirmPasswordNontification = $('#ConfirmPasswordNontification');

    let btnSignup = $('#btnSignUp');

    function checkFullName() {
        if(txtFullName.val() == "")
        {
            FullNameNotification.html("Không được để trống");
            FullNameNotification.addClass("text-danger");
            return false;
        }

        FullNameNotification.html("Username sử dụng khi đăng nhập");
        FullNameNotification.removeClass("text-danger");
        return true;
    }

    function checkPhone() {
        if(txtPhone.val() == "")
        {
            PhoneNotification.html("Không được để trống");
            PhoneNotification.addClass("text-danger");
            return false;
        }

        PhoneNotification.html("Username sử dụng khi đăng nhập");
        PhoneNotification.removeClass("text-danger");
        return true;
    }

    function checkEmail() {
        if(txtEmail.val() == "")
        {
            EmailNontification.html("Không được để trống");
            EmailNontification.addClass("text-danger");
            return false;
        }
        let regt = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if(!regt.test(txtEmail.val()))
        {
            EmailNontification.html("* Sai định dạng");
            EmailNontification.addClass("text-danger");
            return false;
        }
        EmailNontification.html("Sử dụng địa chỉ E-mail hiện tại của bạn");
        EmailNontification.removeClass("text-danger");
        return true;
    };

    function checkPassword() {
        if(txtPassword.val() == "")
        {
            PasswordNontification.html("Không được để trống");
            PasswordNontification.addClass("text-danger");
            return false;
        }
        let regt = /^(?=.*([a-zA-Z]))(?=.*[0-9])(?=.*[!@#&\._])(.{8,})$/;
        if(!regt.test(txtPassword.val()))
        {
            PasswordNontification.html("* Sai định dạng");
            PasswordNontification.addClass("text-danger");
            return false;
        }
        PasswordNontification.html("Sử dụng 8 ký tự trở lên và kết hợp chữ cái, chữ số và biểu tượng");
        PasswordNontification.removeClass("text-danger");
        return true;
    };

    function checkConfirmPassword() {
        if(txtConfirmPassword.val() == "")
        {
            ConfirmPasswordNontification.html("Không được để trống");
            return false;
        }
        if(txtConfirmPassword.val() != txtPassword.val())
        {
            ConfirmPasswordNontification.html("Xác nhận mật khẩu chưa chính xác");
            return false;
        }
        ConfirmPasswordNontification.html("");
        return true;
    };

    txtFullName.blur(function () {checkFullName()});

    txtPhone.blur(function () {checkPhone()});

    txtEmail.blur(function() {checkEmail()});

    txtPassword.blur(function() {checkPassword()});

    txtConfirmPassword.blur(function() {checkConfirmPassword()});

    txtFullName.keyup(function () {
        checkFullName();
    });

    txtPhone.keyup(function () {
        checkPhone();
    });

    txtEmail.keyup(function() {
        checkEmail();
    });

    txtPassword.keyup(function() {
        checkPassword();
    });

    txtConfirmPassword.keyup(function() {
        checkConfirmPassword();
    });

    btnSignup.on('click', function () {
        if(checkEmail() && checkPassword() && checkConfirmPassword()) {
            $.ajax({
                url: '/api/accounts/signup/user-account',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    fullName: txtFullName.val(),
                    phone: txtPhone.val(),
                    email: txtEmail.val(),
                    password: txtPassword.val(),
                    confirmPassword: txtConfirmPassword.val()
                }),
                success: function(data) {
                    if(data == 1) {
                        window.location.href = location.origin + "/login";
                    } else if(data == 0) {
                        showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau");
                    } else if(data == -2) {
                        EmailNontification.html("Email đã được đăng kí tài khoản");
                        EmailNontification.addClass("text-danger");
                    }
                },
                error: function(error) {
                    showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                }
            });
        }
    });

});

function showMessage(message) {
    $("#message").text(message);
    $("#notification").addClass("show");

    setTimeout(function() {
        $("#notification").removeClass("show");
    }, 2000);
}