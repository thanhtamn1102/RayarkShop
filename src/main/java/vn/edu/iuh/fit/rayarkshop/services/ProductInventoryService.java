package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.ProductInventory;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariation;

@Service
public interface ProductInventoryService {

    ProductInventory findByProductIdAndProductVariation(int productId, ProductVariation productVariation);

    ProductInventory save(ProductInventory productInventory);

}
