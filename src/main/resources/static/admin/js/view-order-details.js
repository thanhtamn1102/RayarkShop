$(document).ready(function () {
    let orderStatusFormSelect = $('#orderStatusFormSelect');

    orderStatusFormSelect.on('change', function () {
        let salesOrderId = $('#sales-order-id').val();

        $.ajax({
            url: '/api/sales-order/update-status',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                salesOrderId: salesOrderId,
                salesOrderStatus: parseInt(orderStatusFormSelect.val())
            }),
            success: function(data) {
                if(data) {
                    location.reload();
                } else {
                    showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                }
            },
            error: function(error) {
                showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
            }
        });
    });
});