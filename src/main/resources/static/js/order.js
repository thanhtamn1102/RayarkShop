$(document).ready(function () {
    let map = new Map();

    let salesOrderId = $('#sales-order-id');
    let btnUploadImage = $('.btn-upload-image');
    let fileInput = $('.input-files-upload');

    for(let i = 0; i < fileInput.length; i++) {
        map.set($(fileInput[i]).attr('product-id'), []);
    }

    btnUploadImage.on('click', function () {
        let button = $(this);
        let productId = button.attr('value');
        $('#file-input' + productId).click();
    });

    fileInput.on('change', event => {
        let input = $(event.target);
        let productId = input.attr('product-id');

        let formDanhGiaSanPham = input.closest('.form-danh-gia-san-pham');

        let previewImageContainer = formDanhGiaSanPham.find('.preview-image-container');
        let uploadFileNotification = formDanhGiaSanPham.find('.uploadNotification');

        console.log(uploadFileNotification);

        if(map.get(productId).length < 5) {
            let fileChooses = event.target.files;

            $.each(fileChooses, (i, file) => {
                const reader = new FileReader();

                if(map.get(productId).indexOf(file) == -1) {
                    map.get(productId).push(file);

                    reader.onload = () => {
                        const img = $('<img>').attr('src', reader.result);
                        const btnRemove = $('<button>').addClass('remove-button btn btn-secondary').html('Remove');
                        const imageContainer = $('<div>').addClass('image-container').append(img).append(btnRemove);
                        previewImageContainer.append(imageContainer);

                        // Bắt sự kiện click vào icon xóa
                        btnRemove.on('click', () => {
                            let index = map.get(productId).indexOf(file);
                            if(index >= 0)
                                map.get(productId).splice(index, 1);
                            imageContainer.remove();
                            uploadFileNotification.html('');
                        });
                    }

                    reader.readAsDataURL(file);
                }
            });
        } else {
            console.log('Vượt quá số lượng');
            uploadFileNotification.html('Chỉ được phép tải lên tối đa 5 hình ảnh');
        }

    });


    $('#btnSubmitDanhGia').on('click', function () {
        let productReviewRequest = {
            salesOrderId: parseInt(salesOrderId.val()),
            productReviewContents: []
        };

        let dsFormDanhGiaSanPham = $('.form-danh-gia-san-pham');
        for(let i = 0; i < dsFormDanhGiaSanPham.length; i++) {
            let formDanhGiaSanPham = $(dsFormDanhGiaSanPham[i]);
            let productId = formDanhGiaSanPham.attr('product-id');
            let rattingChecked = formDanhGiaSanPham.find('.ratting-check').filter(':checked');
            let ratting = 1;
            if(rattingChecked.length > 0)
                ratting = parseInt(rattingChecked.val());
            let comment = formDanhGiaSanPham.find('.txtComment').val();
            let reviewImages = new FormData();

            $.each(map.get(productId), (i, file) => {
                console.log('aaa');
                reviewImages.append('reviewImages', file);
            })

            productReviewRequest.productReviewContents.push({
                productId: productId,
                ratting: ratting,
                comment: comment,
                reviewImages: reviewImages
            });

            console.log('productId: ' + productId);
            console.log('ratting: ' + ratting);
            console.log('comment: ' + comment);
            console.log('reviewImages: ' + reviewImages);
        }

        console.log(productReviewRequest);

        $.ajax({
            url: '/api/product-reviews/add',
            type: "POST",
            data: JSON.stringify(productReviewRequest),
            contentType: 'application/json',
            success: function(data) {
                if(data != null) {
                    console.log(data);
                    for(let i = 0; i < data.length; i++) {
                        let productReviewId = data[i].productReviewId;
                        let productId = data[i].productId;
                        uploadReviewImage(productReviewId, map.get(productId.toString()));
                        location.reload();
                    }
                }
                else
                    showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử lại sau!");
            },
            error: function(error) {
                showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử lại sau!");
            }
        });
    });


    $('.ratting-check').on('change', function() {
        let button = $(this);
        let formRatting = button.closest('.form-ratting');
        let ratting = parseInt(button.val());

        for(let i = 1; i <= ratting; i++) {
            let rattingStar = formRatting.find('.ratting' + i);
            if(rattingStar.length > 0) {
                rattingStar.html('<i class="fas fa-star"></i>');
            }
        }

        for(let j = ratting + 1; j <= 5; j++) {
            let rattingStar = formRatting.find('.ratting' + j);
            if(rattingStar.length > 0)
                rattingStar.html('<i class="far fa-star"></i>');
        }
    });


    function uploadReviewImage(productReviewId, reviewImages) {
        let formData = new FormData();
        formData.append('productReviewId', productReviewId);

        console.log('reviewImage');
        console.log(reviewImages);

        $.each(reviewImages, (i, file) => {
            console.log('a');
            formData.append('files', file);
        });

        $.ajax({
            url: '/api/product-reviews/upload-review-images',
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(data) {
                if(data) {
                    console.log(data);
                }
                else
                    showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử lại sau!");
            },
            error: function(error) {
                showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử lại sau!");
            }
        });
    }


    function showMessage(message) {
        $("#message").text(message);
        $("#notification").addClass("show");

        setTimeout(function() {
            $("#notification").removeClass("show");
        }, 2000);
    }

});