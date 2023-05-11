$(document).ready(function() {
    $(".btnAddToCart").click(function() {
        let productId = $(this).attr('product-id');

        console.log(productId);

        $.ajax({
            url: '/api/shopping-cart/addToCart',
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                productId: productId,
                quantity: 1
            }),
            success: function(data) {
                if(data)
                    showMessage("Thêm sản phẩm vào giỏ hàng thành công");
                else
                    showMessage("Thêm sản phẩm vào giỏ hàng không thành công");
            },
            error: function(error) {
                showMessage("Thêm sản phẩm vào giỏ hàng không thành công");
            }
        });
    });


    $(".btnAddToFavoriteList").on("click", function () {
        let productId = parseInt($(this).attr("product-id"));
        let favoriteCheck = $(this).attr("favoriteCheck");
        let button = $(this);

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
                    console.log('hello');
                    console.log(data);
                    if(data) {
                        console.log('dieu kien');
                        console.log(data);
                        console.log(button);
                        button.attr("favoriteCheck", "false");
                        button.removeClass("text-danger");
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
                    console.log('hello');
                    console.log(data);
                    if(data) {
                        console.log('dieu kien');
                        console.log(data);
                        console.log(button);
                        button.attr("favoriteCheck", "true");
                        button.addClass("text-danger");
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
    })

    function showMessage(message) {
        $("#message").text(message);
        $("#notification").addClass("show");

        setTimeout(function() {
            $("#notification").removeClass("show");
        }, 2000);
    }

});