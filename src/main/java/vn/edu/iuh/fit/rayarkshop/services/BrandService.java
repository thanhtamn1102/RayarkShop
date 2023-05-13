package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.Brand;

import java.util.List;

@Service
public interface BrandService {

    Brand save(Brand brand);

    List<Brand> saveAll(List<Brand> brands);

    List<Brand> getAll();

    Brand getById(int brandId);

}
