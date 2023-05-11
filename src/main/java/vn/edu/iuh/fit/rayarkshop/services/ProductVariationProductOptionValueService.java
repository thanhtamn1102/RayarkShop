package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariationProductOptionValue;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariationProductOptionValueId;

@Service
public interface ProductVariationProductOptionValueService {

    ProductVariationProductOptionValue findById(ProductVariationProductOptionValueId id);

}
