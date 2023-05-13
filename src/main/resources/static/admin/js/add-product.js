$(document).ready(function() {
    let files = [];

    let productPhotoBox = $('#product-photo-box');

    let categoriesSelectedBox = $('#categories-selected-box');
    let categoryFormSelect = $('#categoryFormSelect');

    let txtProductName = $('#txtProductName');
    let txtSku = $('#txtSku');
    let txtDescription = $('#txtDescription');
    let txtImportPrice = $('#txtImportPrice');
    let txtQuantity = $('#txtQuantity');
    let brandFormSelect = $('#brandFormSelect');

    let productNameNotification = $('#productNameNotification');
    let skuNotification = $('#skuNotification');
    let descriptionNotification = $('#descriptionNotification');
    let importPriceNotification = $('#importPriceNotification');
    let quantityNotification = $('#quantityNotification');
    let categoryNotification = $('#categoryNotification');
    let brandNotification = $('#brandNotification');

    let inputFile = $('#inputFile');
    let btnUploadImage = $('#btnUploadImage');
    btnUploadImage.on('click', function () {
        inputFile.click();
    });

    inputFile.on('change', event => {
        let input = $(event.target);

        if(files.length < 5) {
            let fileChooses = event.target.files;

            $.each(fileChooses, (i, file) => {
                const reader = new FileReader();

                if(files.indexOf(file) == -1) {
                    files.push(file);

                    reader.onload = () => {
                        const img = $('<img>').attr('src', reader.result);
                        const btnRemove = $('<button>').addClass('remove-button btn btn-secondary').html('Remove');
                        const imageContainer = $('<div>').addClass('image-container').append(img).append(btnRemove);
                        productPhotoBox.append(imageContainer);

                        // Bắt sự kiện click vào icon xóa
                        btnRemove.on('click', () => {
                            let index = files.indexOf(file);
                            if(index >= 0)
                                files.splice(index, 1);
                            imageContainer.remove();
                        });
                    }

                    reader.readAsDataURL(file);
                }
            });
        } else {
            showMessage('Chỉ được phép tải lên tối đa 5 hình ảnh');
        }
    });

    categoryFormSelect.on('change', function() {
        categoryNotification.html('');

        let selectedOption = $(this).children("option:selected");
        let categorySelectedValue = selectedOption.val();
        let categorySelectedText = selectedOption.text();

        let btnCategorySelects = $('.btn-category-selected');
        for(let i = 0; i < btnCategorySelects.length; i++) {
            if($(btnCategorySelects[i]).attr('category-id') == categorySelectedValue)
                return;
        }

        let btnCategorySelected = $('<button>').addClass('btn-category-selected btn btn-sm btn-outline-warning me-1')
                                                .attr('category-id', categorySelectedValue);
        let spanCategorySelectedName = $('<span>').html(categorySelectedText);
        let iconRemove = $('<i>').addClass('fas fa-times ms-1');

        iconRemove.on('click', function() {
            btnCategorySelected.remove();
        });

        btnCategorySelected.append(spanCategorySelectedName).append(iconRemove);

        categoriesSelectedBox.append(btnCategorySelected);
    });

    $('#btnSave').on('click', function () {
        let productName = txtProductName.val();
        let description = txtDescription.val();
        let importPrice = txtImportPrice.val();
        let quantity = txtQuantity.val();
        let sku = txtSku.val();
        let brand = parseInt(brandFormSelect.val());
        let categories = [];

        let btnCategoriesSelects = $('.btn-category-selected');
        for(let i = 0; i < btnCategoriesSelects.length; i++) {
            categories.push(parseInt($(btnCategoriesSelects[i]).attr('category-id')));
        }

        if(checkProductName(productName) && checkImportPrice(importPrice) && checkQuantity(quantity)
            && checkCategory(categories) && checkBrand(brand)) {
            $.ajax({
                url: '/api/products/add',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    productName: productName,
                    sku: sku,
                    description: description,
                    importPrice: importPrice,
                    quantity: quantity,
                    brandId: brand,
                    categoryIds: categories
                }),
                success: function(data) {
                    if(data > 0) {
                        if(files.length > 0) {
                            uploadProductPhoto(data, files);
                        } else {
                            location.href = location.origin + "/admin/product-manager";
                        }
                    } else {
                        showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                    }
                },
                error: function(error) {
                    showMessage("Lỗi: Đã có lỗi xảy ra vui lòng thử lại sau!");
                }
            });
        }
    });

    function uploadProductPhoto(productId, productPhotos) {
        let formData = new FormData();
        formData.append('productId', productId);

        $.each(productPhotos, (i, file) => {
            formData.append('files', file);
        });

        $.ajax({
            url: '/api/products/upload-product-photos',
            type: "PUT",
            data: formData,
            processData: false,
            contentType: false,
            success: function(data) {
                if(data) {
                    location.href = location.origin + "/admin/product-manager";
                }
                else
                    showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử lại sau!");
            },
            error: function(error) {
                showMessage("Lỗi: Đã có lỗi xảy ra. Vui lòng thử lại sau!");
            }
        });
    }

    function checkProductName(productName) {
        if(productName == "") {
            productNameNotification.html("Không được để trống");
            return false;
        }
        productNameNotification.html('');
        return true;
    }

    function checkImportPrice(importPrice) {
        if(importPrice == "") {
            importPriceNotification.html("Không được để trống");
            return false;
        } else if(isNaN(importPrice)) {
            importPriceNotification.html("Giá nhập phải là số");
            return false;
        } else if(parseFloat(importPrice) <= 0) {
            importPriceNotification.html("Giá nhập phải > 0");
            return false;
        }
        importPriceNotification.html('');
        return true;
    }

    function checkQuantity(quantity) {
        if(quantity == "") {
            quantityNotification.html("Không được để trống");
            return false;
        } else if(isNaN(quantity)) {
            quantityNotification.html("Số lượng phải là số");
            return false;
        } else if(parseFloat(quantity) <= 0) {
            quantityNotification.html("Số lượng phải > 0");
        }
        quantityNotification.html('');
        return true;
    }

    function checkCategory(categories) {
        if(categories.length <= 0) {
            categoryNotification.html("Sản phẩm phải thuộc it nhất một danh mục");
            return false;
        }
        categoryNotification.html('');
        return true;
    }

    function checkBrand(brand) {
        if(brand <= 0) {
            brandNotification.html("Sản phẩm phải thuộc một thương hiệu");
            return false;
        }
        brandNotification.html("");
        return true;
    }

    txtProductName.keyup(function () {
        checkProductName();
    });

    txtImportPrice.keyup(function () {
        checkImportPrice();
    });

    txtQuantity.keyup(function () {
        checkQuantity();
    });

    brandFormSelect.on('change', function () {
        brandNotification.html('');
    });

});