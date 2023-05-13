package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.services.BrandService;
import vn.edu.iuh.fit.rayarkshop.services.ProductCategoryService;
import vn.edu.iuh.fit.rayarkshop.services.ProductInventoryService;
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

    @Autowired
    private ProductInventoryService productInventoryService;


    @GetMapping("")
    public ModelAndView productManagerPage(@RequestParam(value = "page", defaultValue = "0") int pageIndex) {
        Page<Product> pageInfo = productService.findAll(PageRequest.of(pageIndex, 10));
        Page<Product> pageInfoDangBan = productService.findAllProductByProductStatus(0, PageRequest.of(0, 10));
        Page<Product> pageInfoDaAn = productService.findAllProductByProductStatus(1, PageRequest.of(0, 10));
        Page<Product> pageInfoNgungKinhDoanh = productService.findAllProductByProductStatus(2, PageRequest.of(0, 10));

        List<ProductCategory> productCategories = productCategoryService.getAll();
        List<Brand> brands = brandService.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("pageInfoDangBan", pageInfoDangBan);
        modelAndView.addObject("pageInfoDaAn", pageInfoDaAn);
        modelAndView.addObject("pageInfoNgungKinhDoanh", pageInfoNgungKinhDoanh);
        modelAndView.addObject("productCategories", productCategories);
        modelAndView.addObject("brands", brands);

        modelAndView.setViewName("admin/product/product-manager");

        return modelAndView;
    }

    @GetMapping("/add-product")
    public ModelAndView addProductPage() {
        List<ProductCategory> productCategories = productCategoryService.getAll();
        List<Brand> brands = brandService.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("categories", productCategories);
        modelAndView.addObject("brands", brands);

        modelAndView.setViewName("admin/product/add-product");

        return modelAndView;
    }

    @GetMapping("/view")
    public ModelAndView productPage(@RequestParam int productId) {
        Product product = productService.findById(productId);

        if(product ==  null)
            throw new NotFoundException("Product Not Found");

        ProductInventory productInventory = productInventoryService.findByProductIdAndProductVariation(productId, null);

        List<ProductCategory> productCategories = productCategoryService.getAll();
        List<Brand> brands = brandService.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("product", product);
        modelAndView.addObject("inventory", productInventory);
        modelAndView.addObject("categories", productCategories);
        modelAndView.addObject("brands", brands);

        modelAndView.setViewName("admin/product/view-product");

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam(value = "page", defaultValue = "0") int pageIndex,
                                      @RequestParam String keyword) {
        Page<Product> pageInfo = productService.searchProduct(keyword, PageRequest.of(pageIndex, 10));
        Page<Product> pageInfoDangBan = productService.findAllProductByProductStatus(0, PageRequest.of(0, 10));
        Page<Product> pageInfoDaAn = productService.findAllProductByProductStatus(1, PageRequest.of(0, 10));
        Page<Product> pageInfoNgungKinhDoanh = productService.findAllProductByProductStatus(2, PageRequest.of(0, 10));

        List<ProductCategory> productCategories = productCategoryService.getAll();
        List<Brand> brands = brandService.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("pageInfoDangBan", pageInfoDangBan);
        modelAndView.addObject("pageInfoDaAn", pageInfoDaAn);
        modelAndView.addObject("pageInfoNgungKinhDoanh", pageInfoNgungKinhDoanh);
        modelAndView.addObject("productCategories", productCategories);
        modelAndView.addObject("brands", brands);

        modelAndView.setViewName("admin/product/product-manager");

        return modelAndView;
    }

}
