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
    public String productPage(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "9") int size,
                              @RequestParam(defaultValue = "name-asc") String sort,
                              @RequestParam(name = "brands", required = false) Optional<List<Integer>> brandFilters,
                              @RequestParam(name = "categories", required = false) Optional<List<Integer>> categoryFilters,
                              Model model) {
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

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("brands", brands);
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("sort", sort);
        model.addAttribute("brandFilters", brandFilters.isPresent() ? brandFilters.get() : new ArrayList<>());
        model.addAttribute("categoryFilters", categoryFilters.isPresent() ? categoryFilters.get() : new ArrayList<>());
        model.addAttribute("favoriteProductListItems", favoriteProductListItems);

        return "shop/search-result";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "9") int size,
                                @RequestParam(defaultValue = "name-asc") String sort,
                                @RequestParam(name = "brands", required = false) Optional<List<Integer>> brandFilters,
                                @RequestParam(name = "categories", required = false) Optional<List<Integer>> categoryFilters,
                                @RequestParam String key,
                                Model model) {
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

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("brands", brands);
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("key", key);
        model.addAttribute("sort", sort);
        model.addAttribute("brandFilters", brandFilters.isPresent() ? brandFilters.get() : new ArrayList<>());
        model.addAttribute("categoryFilters", categoryFilters.isPresent() ? categoryFilters.get() : new ArrayList<>());
        model.addAttribute("favoriteProductListItems", favoriteProductListItems);

        return "shop/search-result";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable(name = "id") String id,
                             Model model) {
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

        model.addAttribute("product", product);
        model.addAttribute("productFromCategory", productFromCategory);
        model.addAttribute("favoriteProduct", favoriteProductListItem != null ? true : false);
        model.addAttribute("productReviews", productReviews);

        return "shop/single-product";
    }

}
