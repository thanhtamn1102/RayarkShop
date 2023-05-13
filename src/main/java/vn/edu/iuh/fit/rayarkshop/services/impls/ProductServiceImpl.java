package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.Product;
import vn.edu.iuh.fit.rayarkshop.models.ProductCategory;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByRemoveDateIsNull(pageable);
    }

    @Override
    public Page<Product> findAllProductByProductStatus(int productStatus, Pageable pageable) {
        return productRepository.findAllByProductStatus(productStatus, pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Product> searchProduct(String name, Pageable pageable) {
        return productRepository.searchProductsByNameContainsIgnoreCase(name, pageable);
    }

    @Override
    public Page<Product> searchProduct(ProductCategory productCategory, Pageable pageable) {
        return productRepository.searchProductsByProductCategoriesContains(productCategory, pageable);
    }

    @Override
    public Page<Product> searchProductsByBrandIdIn(List<Integer> brandIds, List<Integer> categoryIds, Pageable pageable) {
        return productRepository.searchProductsByBrandIdInAndProductCategoriesIdIn(brandIds, categoryIds, pageable);
    }

    @Override
    public List<Product> getListBestSellProducts(int n) {
        return productRepository.findTopNSortByTotalSoldDesc(n);
    }

    @Override
    public List<Product> findNewProducts(int n) {
        return productRepository.findTopNSortByCreatedDate(n);
    }

    @Override
    public List<Product> findBestRattingProducts(int n) {
        return productRepository.findTopNSortByAverageRatting(n);
    }

    @Override
    public boolean deleteProductById(int productId) throws NotFoundException {
        Product product = findById(productId);
        if(product == null)
            throw new NotFoundException("Product Not Found");
        product.setRemoveDate(LocalDateTime.now());
        Product saved = save(product);
        return saved != null ? true : false;
    }

}
