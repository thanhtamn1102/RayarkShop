package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariation;
import vn.edu.iuh.fit.rayarkshop.models.ShoppingCartItem;

import java.util.List;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    List<ShoppingCartItem> getAllByCustomerId(int customerId);

    @Transactional
    Integer removeShoppingCartItemById(long shoppingCartItemId);

    @Transactional
    Integer removeShoppingCartItemsByCustomerId(int customerId);

    List<ShoppingCartItem> findByCustomerIdAndProductIdAndProductVariation(int customerId, int productId, ProductVariation productVariation);

    @Modifying
    @Transactional
    @Query("UPDATE ShoppingCartItem sci SET sci.quantity = :quantity WHERE sci.id = :id")
    int updateShoppingCartItemById(@Param("id") Long id, @Param("quantity") Integer quantity);

}
