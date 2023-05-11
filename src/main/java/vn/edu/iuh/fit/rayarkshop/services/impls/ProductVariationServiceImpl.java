package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductVariationRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductVariationService;

@Component
public class ProductVariationServiceImpl implements ProductVariationService {

    @Autowired
    private ProductVariationRepository productVariationRepository;

}
