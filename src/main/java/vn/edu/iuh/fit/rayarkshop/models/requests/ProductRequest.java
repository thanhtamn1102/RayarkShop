package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {

    private int productId;
    private String productName;
    private String sku;
    private String description;
    private String importPrice;
    private int quantity;
    private int brandId;
    private int productStatus;
    private List<Integer> categoryIds;

}
