package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddToCartRequest {

    private int productId;
    private int optionId;
    private int optionValueId;
    private int quantity;

}
