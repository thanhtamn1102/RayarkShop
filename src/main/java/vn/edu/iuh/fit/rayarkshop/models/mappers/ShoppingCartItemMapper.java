package vn.edu.iuh.fit.rayarkshop.models.mappers;

import vn.edu.iuh.fit.rayarkshop.models.ShoppingCartItem;
import vn.edu.iuh.fit.rayarkshop.models.dto.ShoppingCartItemDTO;

public class ShoppingCartItemMapper {

    public static ShoppingCartItemDTO toShoppingCartItemDTO(ShoppingCartItem shoppingCartItem) {
        ShoppingCartItemDTO shoppingCartItemDTO = new ShoppingCartItemDTO(
                shoppingCartItem.getId(),
                shoppingCartItem.getProduct().getId(),
                shoppingCartItem.getProduct().getPrice(),
                shoppingCartItem.getQuantity(),
                shoppingCartItem.lineTotalCalculate()
        );
        return shoppingCartItemDTO;
    }

}
