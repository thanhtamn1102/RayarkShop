package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.ProductInventory;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariation;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductInventoryRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductInventoryService;

@Component
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Override
    public ProductInventory findByProductIdAndProductVariation(int productId, ProductVariation productVariation) {
        return productInventoryRepository.findByProductIdAndProductVariation(productId, productVariation);
    }

    @Override
    public ProductInventory save(ProductInventory productInventory) {
        return productInventoryRepository.save(productInventory);
    }
}
