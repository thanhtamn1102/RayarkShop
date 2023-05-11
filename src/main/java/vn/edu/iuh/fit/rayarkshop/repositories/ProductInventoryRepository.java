package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.ProductInventory;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariation;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {

    ProductInventory findByProductIdAndProductVariation(int productId, ProductVariation productVariation);

}
