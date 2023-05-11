package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingCartItemRequest {

    private long shoppingCartItemId;
    private int quantity;

}
