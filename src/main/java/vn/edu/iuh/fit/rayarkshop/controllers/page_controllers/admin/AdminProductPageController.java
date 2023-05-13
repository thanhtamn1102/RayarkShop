package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.Brand;
import vn.edu.iuh.fit.rayarkshop.models.Product;
import vn.edu.iuh.fit.rayarkshop.models.ProductCategory;
import vn.edu.iuh.fit.rayarkshop.models.ProductInventory;
import vn.edu.iuh.fit.rayarkshop.services.BrandService;
import vn.edu.iuh.fit.rayarkshop.services.ProductCategoryService;
import vn.edu.iuh.fit.rayarkshop.services.ProductInventoryService;
import vn.edu.iuh.fit.rayarkshop.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductPageController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInventoryService productInventoryService;

    @GetMapping("/{id}")
    public ModelAndView productPage(@PathVariable("id") int productId) {
        Product product = productService.findById(productId);

        if(product == null)
            throw new NotFoundException("Product Not Found");

        ProductInventory productInventory = productInventoryService.findByProductIdAndProductVariation(productId, null);

        List<ProductCategory> productCategories = productCategoryService.getAll();
        List<Brand> brands = brandService.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject(product);
        modelAndView.addObject("productCategories", productCategories);
        modelAndView.addObject("brands", brands);
        modelAndView.addObject("inventory", productInventory);

        modelAndView.setViewName("admin/add-product");

        return modelAndView;
    }

}
