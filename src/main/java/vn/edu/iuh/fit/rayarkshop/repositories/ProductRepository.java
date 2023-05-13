package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.Product;
import vn.edu.iuh.fit.rayarkshop.models.ProductCategory;
import vn.edu.iuh.fit.rayarkshop.models.ProductStatus;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.removeDate is null order by p.createdDate desc")
    Page<Product> findAllByRemoveDateIsNull(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.removeDate is null and p.productStatus = ?1 order by p.createdDate desc")
    Page<Product> findAllByProductStatus(@Param("productStatus") int productStatus, Pageable pageable);

    Page<Product> searchProductsByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<Product> searchProductsByProductCategoriesContains(ProductCategory productCategory, Pageable pageable);

    Page<Product> searchProductsByBrandIdInAndProductCategoriesIdIn(List<Integer> brandIds, List<Integer> productCategoriesIds, Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.totalSold DESC")
    List<Product> findTopNSortByTotalSoldDesc(int n);

    @Query("SELECT p FROM Product p ORDER BY p.createdDate DESC")
    List<Product> findTopNSortByCreatedDate(int n);

    @Query("select p from Product  p order by p.averageRatting desc")
    List<Product> findTopNSortByAverageRatting(int n);

}
