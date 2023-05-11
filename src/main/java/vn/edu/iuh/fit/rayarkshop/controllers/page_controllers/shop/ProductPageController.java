package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductPageController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private FavoriteProductListItemService favoriteProductListItemService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductReviewService productReviewService;

    @GetMapping("")
    public ModelAndView productPage(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "9") int size,
                                    @RequestParam(defaultValue = "name-asc") String sort,
                                    @RequestParam(name = "brands", required = false) Optional<List<Integer>> brandFilters,
                                    @RequestParam(name = "categories", required = false) Optional<List<Integer>> categoryFilters) {
        List<Product> favoriteProductListItems = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            String usernameOrEmail = authentication.getName();
            Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);

            if(account != null) {
                int customerId = account.getPerson().getId();

                favoriteProductListItems = favoriteProductListItemService.getByCustomer(customerId).stream()
                        .map(favoriteProductListItem -> favoriteProductListItem.getProduct()).toList();
            }
        }


        List<Brand> brands = brandService.getAll();
        List<ProductCategory> productCategories = productCategoryService.getAll();


        String sortArr[] = sort.split("-");
        Page<Product> pageInfo = productService.searchProductsByBrandIdIn(
                brandFilters.isPresent() ? brandFilters.get() : brands.stream().map(brand -> brand.getId()).toList(),
                categoryFilters.isPresent() ? categoryFilters.get() : productCategories.stream().map(productCategory -> productCategory.getId()).toList(),
                PageRequest.of(page, size, sortArr[1].equals("asc") ? Sort.by(sortArr[0]).ascending() : Sort.by(sortArr[0]).descending())
        );

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("brands", brands);
        modelAndView.addObject("productCategories", productCategories);
        modelAndView.addObject("sort", sort);
        modelAndView.addObject("brandFilters", brandFilters.isPresent() ? brandFilters.get() : new ArrayList<>());
        modelAndView.addObject("categoryFilters", categoryFilters.isPresent() ? categoryFilters.get() : new ArrayList<>());
        modelAndView.addObject("favoriteProductListItems", favoriteProductListItems);

        modelAndView.setViewName("shop/search-result");

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "9") int size,
                                @RequestParam(defaultValue = "name-asc") String sort,
                                @RequestParam(name = "brands", required = false) Optional<List<Integer>> brandFilters,
                                @RequestParam(name = "categories", required = false) Optional<List<Integer>> categoryFilters,
                                @RequestParam String key) {
        List<Product> favoriteProductListItems = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            String usernameOrEmail = authentication.getName();
            Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);

            if(account != null) {
                int customerId = account.getPerson().getId();

                favoriteProductListItems = favoriteProductListItemService.getByCustomer(customerId).stream()
                        .map(favoriteProductListItem -> favoriteProductListItem.getProduct()).toList();
            }
        }

        Page<Product> pageInfo = productService.searchProduct(key, PageRequest.of(0, 9));
        List<Brand> brands = brandService.getAll();
        List<ProductCategory> productCategories = productCategoryService.getAll();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("brands", brands);
        modelAndView.addObject("productCategories", productCategories);
        modelAndView.addObject("key", key);
        modelAndView.addObject("sort", sort);
        modelAndView.addObject("brandFilters", brandFilters.isPresent() ? brandFilters.get() : new ArrayList<>());
        modelAndView.addObject("categoryFilters", categoryFilters.isPresent() ? categoryFilters.get() : new ArrayList<>());
        modelAndView.addObject("favoriteProductListItems", favoriteProductListItems);

        modelAndView.setViewName("shop/search-result");

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getProduct(@PathVariable(name = "id") String id) {
        FavoriteProductListItem favoriteProductListItem = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            String usernameOrEmail = authentication.getName();
            Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);

            if(account != null) {
                int customerId = account.getPerson().getId();

                favoriteProductListItem = favoriteProductListItemService.getFavoriteProductListItemByCustomerIdAndProductId(customerId, Integer.parseInt(id));
            }
        }

        Product product = productService.findById(Integer.parseInt(id));
        List<Product> productFromCategory = new ArrayList<>();
        List<ProductReview> productReviews = new ArrayList<>();

        if(product != null) {
            for (ProductCategory productCategory : product.getProductCategories()) {
                Page<Product> pageProductsFormCategory = productService.searchProduct(productCategory, PageRequest.of(0, 12));
                while(pageProductsFormCategory.hasNext() && productFromCategory.size() < 12) {
                    pageProductsFormCategory.nextPageable();
                    productFromCategory.addAll(pageProductsFormCategory.getContent());
                }
                if(productFromCategory.size() >= 10)
                    break;
            }

            productReviews = productReviewService.findProductReviewsByProductId(product.getId());
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("product", product);
        modelAndView.addObject("productFromCategory", productFromCategory);
        modelAndView.addObject("favoriteProduct", favoriteProductListItem != null ? true : false);
        modelAndView.addObject("productReviews", productReviews);

        modelAndView.setViewName("shop/single-product");

        return modelAndView;
    }

}
