$(document).ready(function() {
    let txtUserName = $('#txtUserName');
    let txtFullName = $('#txtFullName');
    let txtEmail = $('#txtEmail');
    let txtPhone = $('#txtPhoneNumber');
    let male = $('#male');
    let female = $('#female');
    let birthday = $('#dayOfBirth');
    let btnUpdate = $('#btnUpdate');

    let fullNameNotification = $('#FullNameNotification');
    let phoneNotification = $('#PhoneNotification');
    let dayOfBirthNotification = $('#DayOfBirthNotification');

    let changePasswordModal = $('#changePasswordModal');
    let txtCurrentPassword = $('#txtCurrentPassword');
    let currentPasswordNotification = $('#currentPasswordNotification');
    let txtNewPassword = $('#txtNewPassword');
    let newPasswordNotification = $('#newPasswordNotification');
    let txtConfirmNewPassword = $('#txtConfirmNewPassword');
    let confirmNewPasswordNotification = $('#confirmPasswordNotification');
    let btnSaveChangePassword = $('#btnSaveChangePassword');

    $('#btnUploadImage').on('click', function () {
        $('#uploadAvatarInput').click();
    });

    $('#uploadAvatarInput').on('change', event => {
        let input = $(event.target);
        let files = event.target.files;

        let formData = new FormData();
        $.each(files, (i, file) => {
            formData.append('file', file);
        });

        $.ajax({
            url: '/api/accounts/change-avatar',
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(data) {
                if(data) {
                    location.reload();
                }
                else
                    showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử lại sau!");
            },
            error: function(error) {
                showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử lại sau!");
            }
        });
    });


    txtCurrentPassword.keyup(function() {
        currentPasswordNotification.html("");
    });
    txtNewPassword.keyup(function () {
        newPasswordNotification.html("");
    });
    txtConfirmNewPassword.keyup(function () {
        confirmNewPasswordNotification.html("");
    });

    btnSaveChangePassword.on('click', function () {
        let currentPassword = txtCurrentPassword.val();
        let newPassword = txtNewPassword.val();
        let confirmNewPassword = txtConfirmNewPassword.val();

        if(checkCurrentPassword(currentPassword) && checkNewPassword(newPassword) && checkConfirmPassword(confirmNewPassword)) {
            $.ajax({
                url: '/api/accounts/change-password',
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({
                    currentPassword: currentPassword,
                    newPassword: newPassword
                }),
                success: function(response) {
                    console.log('response: ' + response);
                    if(response == 1) {
                        showMessage("Thay đổi mật khẩu thành công");
                        changePasswordModal.modal('hide');
                    } else if(response == -1) {
                        currentPasswordNotification.html("Mật khẩu hiện tại không chính xác");
                        currentPasswordNotification.addClass("text-danger");
                    } else if(response == -2) {
                        newPasswordNotification.html('Mật khẩu mới giống với mật khẩu hiện tại');
                        newPasswordNotification.addClass('text-danger');
                    } else {
                        showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                    }
                },
                error: function(error) {
                    console.error(error);
                    showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                }
            });
        }
    });


    $('.gender #male').click(function() {
        $('.gender #female').prop('checked', false);
    });
    $('.gender #female').click(function() {
        $('.gender #male').prop('checked', false);
    });

    btnUpdate.on('click', function () {
        console.log("On click");
        if(checkFullName() && checkPhone() && checkDayOfBirth()) {
            $.ajax({
                url: '/api/accounts/update',
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify({
                    userName: txtUserName.val(),
                    fullName: txtFullName.val(),
                    phone: txtPhone.val(),
                    gender: male.is(":checked"),
                    birthday: birthday.val()
                }),
                success: function(response) {
                    console.log('response: ' + response);
                    if(response != null) {
                        location.reload();
                    } else {
                        showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                    }
                },
                error: function(error) {
                    console.error(error);
                    showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                }
            });
        }
    });

    txtFullName.blur(function () {checkFullName()});
    txtPhone.blur(function () {checkPhone()});
    birthday.blur(function () {checkDayOfBirth()});

    txtFullName.keyup(function() {checkFullName()});
    txtPhone.keyup(function() {checkPhone()});
    birthday.keyup(function() {checkDayOfBirth()});

    function showMessage(message) {
        $("#message").text(message);
        $("#notification").addClass("show");

        setTimeout(function() {
            $("#notification").removeClass("show");
        }, 2000);
    }

    function checkFullName() {
        if(txtFullName.val() == "")
        {
            fullNameNotification.html("Không được để trống");
            fullNameNotification.addClass("text-danger");
            return false;
        }

        fullNameNotification.html("");
        fullNameNotification.removeClass("text-danger");
        return true;
    }

    function checkPhone() {
        if(txtPhone.val() == "")
        {
            phoneNotification.html("Không được để trống");
            phoneNotification.addClass("text-danger");
            return false;
        }

        phoneNotification.html("");
        phoneNotification.removeClass("text-danger");
        return true;
    }


    function checkDayOfBirth() {
        if(is18OrOver(birthday.val()) == false) {
            dayOfBirthNotification.html("Tuổi phải lớn hơn hoặc bằng 18");
            dayOfBirthNotification.addClass("text-danger");
            return false;
        }

        dayOfBirthNotification.html("");
        dayOfBirthNotification.removeClass("text-danger");
        return true;
    }

    function is18OrOver(dateString) {
        let now = new Date();
        let dob = new Date(dateString);
        let age = now.getFullYear() - dob.getFullYear();
        let monthDiff = now.getMonth() - dob.getMonth();
        if (monthDiff < 0 || (monthDiff === 0 && now.getDate() < dob.getDate())) {
            age--;
        }
        return age >= 18;
    }

    function checkCurrentPassword(currentPassword) {
        if(currentPassword == "") {
            currentPasswordNotification.html("Không được để trống");
            currentPasswordNotification.addClass("text-danger");
            return false;
        }
        currentPasswordNotification.html("");
        currentPasswordNotification.removeClass("text-danger");
        return true;
    }

    function checkNewPassword(newPassword) {
        if(newPassword == "")
        {
            newPasswordNotification.html("Không được để trống");
            newPasswordNotification.addClass("text-danger");
            return false;
        }
        let regt = /^(?=.*([a-zA-Z]))(?=.*[0-9])(?=.*[!@#&\._])(.{8,})$/;
        if(!regt.test(newPassword))
        {
            newPasswordNotification.html("* Sai định dạng");
            newPasswordNotification.addClass("text-danger");
            return false;
        }
        newPasswordNotification.html("Sử dụng 8 ký tự trở lên và kết hợp chữ cái, chữ số và biểu tượng");
        newPasswordNotification.removeClass("text-danger");
        return true;
    }

    function checkConfirmPassword(confirmPassword) {
        if(confirmPassword == "")
        {
            confirmNewPasswordNotification.html("Không được để trống");
            confirmNewPasswordNotification.addClass("text-danger");
            return false;
        }
        if(confirmPassword != txtNewPassword.val())
        {
            confirmNewPasswordNotification.html("Xác nhận mật khẩu chưa chính xác");
            confirmNewPasswordNotification.addClass("text-danger");
            return false;
        }
        confirmNewPasswordNotification.html("");
        confirmNewPasswordNotification.removeClass("text-danger");
        return true;
    }

});

