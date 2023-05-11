package vn.edu.iuh.fit.rayarkshop.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddProductReviewDTO {

    private long productReviewId;
    private int productId;

}
