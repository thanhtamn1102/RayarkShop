package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.ProductPhoto;

@Repository
public interface ProductPhotoRepository extends JpaRepository<ProductPhoto, Long> {

    int deleteProductPhotoById(long id);

}
