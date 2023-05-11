package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.ProductReviewImage;

@Service
public interface ProductReviewImageService {

    ProductReviewImage save(ProductReviewImage productReviewImage);

}
