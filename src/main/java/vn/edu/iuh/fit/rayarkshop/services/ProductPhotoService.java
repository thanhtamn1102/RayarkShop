package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.ProductPhoto;

import java.util.List;

@Service
public interface ProductPhotoService {

    List<ProductPhoto> saveAll(List<ProductPhoto> productPhotos);

    int removeById(long id);

}
