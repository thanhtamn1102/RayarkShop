package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.Brand;
import vn.edu.iuh.fit.rayarkshop.repositories.BrandRepository;
import vn.edu.iuh.fit.rayarkshop.services.BrandService;

import java.util.List;

@Component
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> saveAll(List<Brand> brands) {
        return brandRepository.saveAll(brands);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getById(int brandId) {
        return brandRepository.findById(brandId).orElse(null);
    }
}
