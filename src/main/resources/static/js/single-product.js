$(document).ready(function() {
    let optionNotification = $('#optionNotification');
    let optionId = -1;
    let optionValueId = -1;
    let isVariations = $('#isVariations').val();

    $('.btnOption').on('click', function() {
        let button = $(this);
        let formOption = button.closest('.form-option');
        let btnOptions = formOption.find('.btnOption');

        btnOptions.removeClass('btn-primary');
        btnOptions.addClass('btn-outline-primary');
        btnOptions.removeClass('active');

        button.removeClass('btn-outline-primary');
        button.addClass('btn-primary');
        button.addClass('active');

        optionNotification.html('');
    });

    $('.optionCheck').on('change', function() {
        optionId = ($(this).attr('option-id'));
        optionValueId = ($(this).attr('option-value-id'));
        optionNotification.html('');
    });


    $('.btnAddToCart').click(function() {
        let quantity = $('.txtQuantity').val();
        let pathArr = window.location.pathname.split('/');

        console.log('isVariation: ' + isVariations);

        if(isVariations == 'true') {
            if(checkChooseOption() == false)
                return;
        }

        $.ajax({
            url: '/api/shopping-cart/addToCart',
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                productId: pathArr[pathArr.length - 1],
                optionId: optionId,
                optionValueId: optionValueId,
                quantity: quantity
            }),
            success: function(data) {
                showMessage("Thêm sản phẩm vào giỏ hàng thành công");
            },
            error: function(error) {
                showMessage("Thêm sản phẩm vào giỏ hàng không thành công");
            }
        });
    });


    function checkChooseOption() {
        if(optionId > 0 && optionValueId > 0) {
            optionNotification.html('');
            return true;
        }

        optionNotification.html('Bạn chưa chọn option cho sản phẩm');
        return false;
    }
});


$(".btnAddToFavoriteList").on("click", function () {
    let button = $(this);
    let productId = parseInt(button.attr("product-id"));
    let favoriteCheck = button.attr("favoriteCheck");

    console.log("productId: " + productId);
    console.log("checked: " + favoriteCheck);

    if(favoriteCheck == 'true') {
        $.ajax({
            url: '/api/favorite-product-list/removeFromFavoriteList',
            type: "GET",
            data: {
                productId: productId
            },
            success: function(data) {
                if(data) {
                    button.removeClass("text-danger");
                    button.attr("favoriteCheck", 'false');
                    showMessage("Xóa sản phẩm khỏi danh sách yêu thích thành công");
                }
                else
                    showMessage("Xóa sản phẩm khỏi danh sách yêu thích không thành công");
            },
            error: function(error) {
                showMessage("Xóa sản phẩm khỏi danh sách yêu thích không thành công");
            }
        });
    } else if(favoriteCheck == 'false') {
        $.ajax({
            url: '/api/favorite-product-list/addToFavoriteList',
            type: "GET",
            data: {
                productId: productId
            },
            success: function(data) {
                if(data) {
                    button.addClass("text-danger");
                    button.attr("favoriteCheck", 'true');
                    showMessage("Thêm sản phẩm vào danh sách yêu thích thành công");
                }
                else
                    showMessage("Thêm sản phẩm vào danh sách yêu thích không thành công");
            },
            error: function(error) {
                showMessage("Thêm sản phẩm vào danh sách yêu thích không thành công");
            }
        });
    }
});


function showMessage(message) {
    $("#message").text(message);
    $("#notification").addClass("show");

    setTimeout(function() {
        $("#notification").removeClass("show");
    }, 2000);
}