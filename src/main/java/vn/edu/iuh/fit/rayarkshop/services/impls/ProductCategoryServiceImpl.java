package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.ProductCategory;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductCategoryRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductCategoryService;

import java.util.List;

@Component
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> saveAll(List<ProductCategory> productCategories) {
        return productCategoryRepository.saveAll(productCategories);
    }

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getById(int productCategoryId) {
        return productCategoryRepository.findById(productCategoryId).orElse(null);
    }


}
