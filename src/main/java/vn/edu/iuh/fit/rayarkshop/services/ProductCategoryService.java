package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.ProductCategory;

import java.util.List;

@Service
public interface ProductCategoryService {

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> saveAll(List<ProductCategory> productCategories);

    List<ProductCategory> getAll();

}
