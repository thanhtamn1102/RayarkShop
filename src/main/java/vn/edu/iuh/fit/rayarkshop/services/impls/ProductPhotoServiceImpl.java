package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.repositories.ProductPhotoRepository;
import vn.edu.iuh.fit.rayarkshop.services.ProductPhotoService;

@Component
public class ProductPhotoServiceImpl implements ProductPhotoService {

    @Autowired
    private ProductPhotoRepository productPhotoRepository;

}
