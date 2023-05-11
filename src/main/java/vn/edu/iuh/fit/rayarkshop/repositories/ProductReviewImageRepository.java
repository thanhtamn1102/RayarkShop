package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.ProductReviewImage;

@Repository
public interface ProductReviewImageRepository extends JpaRepository<ProductReviewImage, Long> {

}
