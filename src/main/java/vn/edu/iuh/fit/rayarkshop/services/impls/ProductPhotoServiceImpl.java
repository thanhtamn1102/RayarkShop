package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.ProductPhoto;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductPhotoRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductPhotoService;

import java.util.List;

@Component
public class ProductPhotoServiceImpl implements ProductPhotoService {

    @Autowired
    private ProductPhotoRepository productPhotoRepository;

    @Override
    public List<ProductPhoto> saveAll(List<ProductPhoto> productPhotos) {
        return productPhotoRepository.saveAll(productPhotos);
    }

    @Override
    public int removeById(long id) {
        return productPhotoRepository.deleteProductPhotoById(id);
    }
}
