package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.ProductReview;

import java.util.List;

@Service
public interface ProductReviewService {

    ProductReview save(ProductReview productReview);

    ProductReview findById(long id);

    List<ProductReview> findProductReviewsBySalesOrderId(long salesOrderId);

    List<ProductReview> findProductReviewsByProductId(int productId);

}
