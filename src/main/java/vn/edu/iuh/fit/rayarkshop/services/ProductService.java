package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.Product;
import vn.edu.iuh.fit.rayarkshop.models.ProductCategory;

import java.util.List;

@Service
public interface ProductService {

    Product save(Product product);

    List<Product> saveAll(List<Product> products);

    Page<Product> findAll(Pageable pageable);

    List<Product> findAll();

    Product findById(Integer id);

    Page<Product> searchProduct(String name, Pageable pageable);

    Page<Product> searchProduct(ProductCategory productCategory, Pageable pageable);

    Page<Product> searchProductsByBrandIdIn(List<Integer> brandIds, List<Integer> categoryIds, Pageable pageable);

    List<Product> getListBestSellProducts(int n);

    List<Product> findNewProducts(int n);

    List<Product> findBestRattingProducts(int n);

}
