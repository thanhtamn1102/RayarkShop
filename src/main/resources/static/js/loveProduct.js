$(document).ready(function() {
    let removeConfirmModal = document.getElementById('removeConfirmModal')
    removeConfirmModal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let favoriteItemId = button.getAttribute('data-bs-whatever');
        let modalBodyInput = removeConfirmModal.querySelector('.modal-body input')

        modalBodyInput.value = favoriteItemId;
    });

    $('#btnSubmitRemove').on('click', function () {
        let favoriteItemId = parseInt($("#removeFavoriteProductId").val());
        console.log(favoriteItemId)

        $.ajax({
            url: '/api/favorite-product-list/removeFromFavoriteList',
            type: "GET",
            data: {
                productId: favoriteItemId
            },
            success: function(data) {
                if(data) {
                    showMessage("Xóa sản phẩm khỏi danh sách yêu thích thành công");
                    location.reload();
                }
                else
                    showMessage("Xóa sản phẩm khỏi danh sách yêu thích không thành công");
            },
            error: function(error) {
                showMessage("Xóa sản phẩm khỏi danh sách yêu thích không thành công");
            }
        });
    });


    $('.btnAddToCart').click(function() {
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


    $(".btnRemoveFromFavoriteList").on("click", function () {

    });


    function showMessage(message) {
        $("#message").text(message);
        $("#notification").addClass("show");

        setTimeout(function() {
            $("#notification").removeClass("show");
        }, 2000);
    }

});