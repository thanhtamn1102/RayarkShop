package vn.edu.iuh.fit.rayarkshop.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingCartItemDTO {

    private long shoppingCartItemId;
    private int productId;
    private double productPrice;
    private int quantity;
    private double lineTotal;

}
