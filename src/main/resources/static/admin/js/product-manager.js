$(document).ready(function () {
    let removeConfirmModal = document.getElementById('removeConfirmModal');
    removeConfirmModal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let removeId = button.getAttribute('product-id');
        $('#btnSubmitRemove').attr('product-id', removeId);
    });

    $('#btnSubmitRemove').on('click', function () {
        let productId = parseInt($(this).attr('product-id'));

        $.ajax({
            url: '/api/products/delete',
            type: "PUT",
            contentType: 'application/json',
            data: JSON.stringify({
                productId: productId
            }),
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


});