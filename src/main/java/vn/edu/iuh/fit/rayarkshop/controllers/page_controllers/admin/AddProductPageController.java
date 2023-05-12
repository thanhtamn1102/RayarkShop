package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.Brand;
import vn.edu.iuh.fit.rayarkshop.models.ProductCategory;
import vn.edu.iuh.fit.rayarkshop.services.BrandService;
import vn.edu.iuh.fit.rayarkshop.services.ProductCategoryService;

import java.util.List;

@Controller
@RequestMapping("/admin/add-product")
public class AddProductPageController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public ModelAndView addProductPage() {
        List<ProductCategory> productCategories = productCategoryService.getAll();
        List<Brand> brands = brandService.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productCategories", productCategories);
        modelAndView.addObject("brands", brands);

        modelAndView.setViewName("admin/add-product");

        return modelAndView;
    }

}
