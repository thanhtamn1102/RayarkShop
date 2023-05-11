$(document).ready(function() {
    let huyDonHangConfirm = document.getElementById('huyDonHangConfirm');
    let radioCheckLyDoHuyDonHang = $('.radioCheckLyDoHuyDonHang');
    let txtLyDoHuyDonHang = $('#txtLyDoHuyDonHang');
    let btnConfirmHuyDonHang = $('#btnConfirmHuyDonHang');
    let lyDoHuyDonHangNotification = $('#lyDoHuyDonHangNotification');
    let datLaiDonHangModal = document.getElementById('datLaiDonHangModal');

    $('#btnMuaLaiVaXoaGioHang').on('click', function () {
        let salesOrderId = $('#sales-order-id').val();

        $.ajax({
            url: '/api/sales-order/dat-lai',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                salesOrderId: salesOrderId,
                clearShoppingCart: true
            }),
            success: function(response) {
                if(response) {
                    location.href = location.origin + '/shopping-cart';
                } else {
                    showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử thử lại sau!");
                }
            },
            error: function(error) {
                showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử thử lại sau!");
            }
        });
    });

    $('#btnMuaLaiVaKhongXoaGioHang').on('click', function () {
        let salesOrderId = $('#sales-order-id').val();

        $.ajax({
            url: '/api/sales-order/dat-lai',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                salesOrderId: salesOrderId,
                clearShoppingCart: false
            }),
            success: function(response) {
                if(response) {
                    location.href = location.origin + '/shopping-cart';
                } else {
                    showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử thử lại sau!");
                }
            },
            error: function(error) {
                showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử thử lại sau!");
            }
        });
    });

    radioCheckLyDoHuyDonHang.on('click', function () {
        let button = $(this);
        let buttonId = button.attr('id');

        if(buttonId == 'radioCheckKhac') {
            txtLyDoHuyDonHang.prop('hidden', false);
        } else {
            txtLyDoHuyDonHang.prop('hidden', true);
        }
    });

    btnConfirmHuyDonHang.on('click', function () {
        let lyDoHuyDonHangChecked = $('.radioCheckLyDoHuyDonHang').filter(':checked');
        let salesOrderId = parseInt($('#salesOrderId').val());

        let lyDoHuyDonHang;
        if (lyDoHuyDonHangChecked.length > 0) {
            if(lyDoHuyDonHangChecked.attr('id') == 'radioCheckKhac') {
                lyDoHuyDonHang = txtLyDoHuyDonHang.val();
                if(lyDoHuyDonHang == "") {
                    lyDoHuyDonHangNotification.html('Vui lòng nhập lý do hủy đơn hàng');
                    return;
                }
                else
                    lyDoHuyDonHangNotification.html('');
            }
            else
                lyDoHuyDonHang = lyDoHuyDonHangChecked.val();
        }

        $.ajax({
            url: '/api/sales-order/huy-don-hang',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                salesOrderId: salesOrderId,
                reason: lyDoHuyDonHang
            }),
            success: function(response) {
                if(response) {
                    location.reload();
                } else {
                    showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử thử lại sau!");
                }
            },
            error: function(error) {
                showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử thử lại sau!");
            }
        });
    });

    txtLyDoHuyDonHang.keyup(function () {
        lyDoHuyDonHangNotification.html('');
    });


    huyDonHangConfirm.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let salesOrderId = button.getAttribute('data-bs-whatever');
        let modalBodyInput = huyDonHangConfirm.querySelector('.modal-body #salesOrderId');

        modalBodyInput.value = salesOrderId;
    });

    datLaiDonHangModal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let salesOrderId = button.getAttribute('data-bs-whatever');
        let modalBodyInput = datLaiDonHangModal.querySelector('.modal-body #sales-order-id');

        modalBodyInput.value = salesOrderId;
    });


    function showMessage(message) {
        $("#message").text(message);
        $("#notification").addClass("show");

        setTimeout(function() {
            $("#notification").removeClass("show");
        }, 2000);
    }

})