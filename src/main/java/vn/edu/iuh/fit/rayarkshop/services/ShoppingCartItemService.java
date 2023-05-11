package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariation;
import vn.edu.iuh.fit.rayarkshop.models.ShoppingCartItem;

import java.util.List;

@Service
public interface ShoppingCartItemService {

    ShoppingCartItem save(ShoppingCartItem shoppingCartItem);

    List<ShoppingCartItem> getAllByCustomerId(int customerId);

    Integer removeById(long id);

    Integer clearShoppingCartItemByCustomerId(int customerId);

    ShoppingCartItem getById(long shoppingCartItemId);

    List<ShoppingCartItem> findByCustomerIdAndProductIdAndProductVariation(int customerId, int productId, ProductVariation productVariation);

    public int updateShoppingCartItemById(long id, int quantity);

}
