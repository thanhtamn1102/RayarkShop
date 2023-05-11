package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariationProductOptionValue;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariationProductOptionValueId;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductVariationProductOptionValueRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductVariationProductOptionValueService;

@Component
public class ProductVariationProductOptionValueServiceImpl
        implements ProductVariationProductOptionValueService {

    @Autowired
    private ProductVariationProductOptionValueRepository productVariationProductOptionValueRepository;

    @Override
    public ProductVariationProductOptionValue findById(ProductVariationProductOptionValueId id) {
        return productVariationProductOptionValueRepository.findById(id).orElse(null);
    }
}
