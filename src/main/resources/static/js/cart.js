$(document).ready(function() {

    let radioCheckShippingAddress = $('.radio-check-shipping-address');

    radioCheckShippingAddress.click(function() {
        radioCheckShippingAddress.prop('checked', false);
        $(this).prop('checked', true);
    });

    function datHang(shippingAddressId,
                 phuongThucNhanHang,
                 phuongThucThanhToan,
                 ghiChu) {
        $.ajax({
            url: '/api/sales-order/add',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                shippingAddressId: shippingAddressId,
                phuongThucNhanHang: phuongThucNhanHang,
                phuongThucThanhToan: phuongThucThanhToan,
                ghiChu: ghiChu
            }),
            success: function(response) {
                console.log(response);
                if(response > 0) {
                    window.location.href = location.origin + "/orders/success?id=" + response;
                } else {
                    showMessage('Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau');
                }
            },
            error: function(error) {
                console.error(error);
                showMessage('Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau');
            }
        });
    }

    $('#btnDatHang').on('click', function () {
        let shippingAddressSelected = $('.radio-check-shipping-address').filter(':checked');
        let shippingAddressId = -1;
        let phuongThucNhanHang = $('#form-select-phuong-thuc-nhan-hang').val();
        let hinhThucThanhToanSelected = $('.radio-check-hinh-thuc-thanh-toan').filter(':checked');
        let hinhThucThanhToan = -1;
        let ghiChu = $('#txt-ghi-chu').val();

        if (shippingAddressSelected.length > 0) {
            shippingAddressId = parseInt(shippingAddressSelected.val());
        }
        if(hinhThucThanhToanSelected.length > 0) {
            hinhThucThanhToan = parseInt(hinhThucThanhToanSelected.val());
        }

        datHang(shippingAddressId, phuongThucNhanHang, hinhThucThanhToan, ghiChu);
    });


    let removeConfirmModal = document.getElementById('removeConfirmModal')
    removeConfirmModal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget
        let shoppingCartItemId = button.getAttribute('data-bs-whatever')
        let modalBodyInput = removeConfirmModal.querySelector('.modal-body input')

        modalBodyInput.value = shoppingCartItemId
    });

    $("#btnSubmitRemove").on("click", function () {
        let cartItemId = parseInt($("#removeCartItemId").val());
        console.log(cartItemId)
        $.ajax({
            url: '/api/shopping-cart/remove',
            method: 'GET',
            contentType: 'application/json',
            data: { shoppingCartItemId: cartItemId },
            success: function(response) {
                if(response == 1) {
                    location.reload();
                } else {
                    showMessage("Xóa sản phẩm không thành công");
                }
            },
            error: function(error) {
                console.error(error);
            }
        });
    });


    $(".btn-minus, .btn-plus").on("click", function() {
        console.log("btn minus or btn plus clicked");

        let button = $(this);
        let product = button.closest(".product");
        let countNumProduct = button.closest(".count-num-product");
        let quantityInput = countNumProduct.find(".product-quantity");
        let quantity = parseInt(quantityInput.val());
        let shoppingCartItemId = parseInt(product.find('.shoppingCartItemId').val());
        let quantityNotification = product.find('.quantityNotification');

        console.log(quantityNotification);

        console.log('shopping cart item id: ' + shoppingCartItemId);

        if (button.hasClass("btn-minus") && quantity > 1) {
            quantity = quantity - 1;
        } else if (button.hasClass("btn-plus")) {
            quantity = quantity + 1;
        } else {
            quantityNotification.html('Số lượng tối thiểu là 1');
            return;
        }

        quantityNotification.html('');


        let price = convertCurrencyStringToNumber(product.find(".product-price").text());
        let lineTotal = price * quantity;
        product.find(".product-line-total").text(formatCurrency(lineTotal));

        let productLineTotals = $('.product-line-total');
        let subTotal = 0;
        productLineTotals.each(function() {
            let value = convertCurrencyStringToNumber($(this).text());
            console.log(value);
            subTotal += value;
        });
        $('.total-amount').text(formatCurrency(subTotal));

        $.ajax({
            url: '/api/shopping-cart/update',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                shoppingCartItemId: shoppingCartItemId,
                quantity: quantity
            }),
            success: function(response) {
                let typeOfResponse = typeof (response);
                if(typeOfResponse == 'object') {
                    quantityInput.val(response.quantity);
                } else if(typeOfResponse == 'number') {
                    if(response == -1)
                        quantityNotification.html('Đã đạt lượng tối đa');
                } else {
                    showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                }
            },
            error: function(error) {
                console.error(error);
            }
        });

    });

    function convertCurrencyStringToNumber(currencyString) {
        while (currencyString.indexOf("đ") !== -1) {
            currencyString = currencyString.replace("đ", "");
        }
        while (currencyString.indexOf(" ") !== -1) {
            currencyString = currencyString.replace(" ", "");
        }
        while (currencyString.indexOf(",") !== -1) {
            currencyString = currencyString.replace(",", "");
        }
        while (currencyString.indexOf(".") !== -1) {
            currencyString = currencyString.replace(".", "");
        }
        console.log(currencyString);
        return parseInt(currencyString);
    }

    function formatCurrency(currency) {
        return currency.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    }

    function showMessage(message) {
        $("#message").text(message);
        $("#notification").addClass("show");

        setTimeout(function() {
            $("#notification").removeClass("show");
        }, 2000);
    }

});

