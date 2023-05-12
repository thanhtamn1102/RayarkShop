package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.Brand;
import vn.edu.iuh.fit.rayarkshop.models.Product;
import vn.edu.iuh.fit.rayarkshop.models.ProductCategory;
import vn.edu.iuh.fit.rayarkshop.services.BrandService;
import vn.edu.iuh.fit.rayarkshop.services.ProductCategoryService;
import vn.edu.iuh.fit.rayarkshop.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin/product-manager")
public class ProductManagerPageController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private BrandService brandService;


    @GetMapping("")
    public ModelAndView productManagerPage() {
        List<Product> pageInfo = productService.findAll();
        List<ProductCategory> productCategories = productCategoryService.getAll();
        List<Brand> brands = brandService.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("products", pageInfo);
        modelAndView.addObject("productCategories", productCategories);
        modelAndView.addObject("brands", brands);

        modelAndView.setViewName("admin/product-manager");

        return modelAndView;
    }

}
