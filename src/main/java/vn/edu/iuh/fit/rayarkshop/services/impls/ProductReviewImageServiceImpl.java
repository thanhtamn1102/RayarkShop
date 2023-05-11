package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.ProductReviewImage;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductReviewImageRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductReviewImageService;

@Component
public class ProductReviewImageServiceImpl implements ProductReviewImageService {

    @Autowired
    private ProductReviewImageRepository productReviewImageRepository;

    @Override
    public ProductReviewImage save(ProductReviewImage productReviewImage) {
        return productReviewImageRepository.save(productReviewImage);
    }
}
