package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariation;
import vn.edu.iuh.fit.rayarkshop.models.ShoppingCartItem;
import vn.edu.iuh.fit.rayarkshop.repositories.ShoppingCartItemRepository;
import vn.edu.iuh.fit.rayarkshop.services.ShoppingCartItemService;

import java.util.List;

@Component
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public ShoppingCartItem save(ShoppingCartItem shoppingCartItem) {
        return shoppingCartItemRepository.save(shoppingCartItem);
    }

    @Override
    public List<ShoppingCartItem> getAllByCustomerId(int customerId) {
        return shoppingCartItemRepository.getAllByCustomerId(customerId);
    }

    @Override
    public Integer removeById(long id) {
        return shoppingCartItemRepository.removeShoppingCartItemById(id);
    }

    @Override
    public Integer clearShoppingCartItemByCustomerId(int customerId) {
        return shoppingCartItemRepository.removeShoppingCartItemsByCustomerId(customerId);
    }

    @Override
    public ShoppingCartItem getById(long shoppingCartItemId) {
        return shoppingCartItemRepository.findById(shoppingCartItemId).orElse(null);
    }

    @Override
    public List<ShoppingCartItem> findByCustomerIdAndProductIdAndProductVariation(int customerId, int productId, ProductVariation productVariation) {
        return shoppingCartItemRepository.findByCustomerIdAndProductIdAndProductVariation(customerId, productId, productVariation);
    }

    @Override
    public int updateShoppingCartItemById(long id, int quantity) {
        return shoppingCartItemRepository.updateShoppingCartItemById(id, quantity);
    }

}
