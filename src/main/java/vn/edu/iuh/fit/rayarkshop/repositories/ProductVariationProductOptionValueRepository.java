package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariationProductOptionValue;
import vn.edu.iuh.fit.rayarkshop.models.ProductVariationProductOptionValueId;

@Repository
public interface ProductVariationProductOptionValueRepository
        extends JpaRepository<ProductVariationProductOptionValue, ProductVariationProductOptionValueId> {

}
