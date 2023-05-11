package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.ProductReview;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductReviewRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductReviewService;

import java.util.List;

@Component
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Override
    public ProductReview save(ProductReview productReview) {
        return productReviewRepository.save(productReview);
    }

    @Override
    public ProductReview findById(long id) {
        return productReviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductReview> findProductReviewsBySalesOrderId(long salesOrderId) {
        return productReviewRepository.findProductReviewsBySalesOrderId(salesOrderId);
    }

    @Override
    public List<ProductReview> findProductReviewsByProductId(int productId) {
        return productReviewRepository.findProductReviewsByProductId(productId);
    }
}
