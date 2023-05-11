package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.Account;
import vn.edu.iuh.fit.rayarkshop.models.Product;
import vn.edu.iuh.fit.rayarkshop.services.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {

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

    @GetMapping(value = {"/", "/home"})
    public ModelAndView homePage() {
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

        List<Product> bestSellProducts = productService.getListBestSellProducts(10);
        List<Product> newProducts = productService.findNewProducts(10);
        List<Product> bestRattingProducts = productService.findBestRattingProducts(10);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("bestSellProducts", bestSellProducts);
        modelAndView.addObject("newProducts", newProducts);
        modelAndView.addObject("bestRattingProducts", bestRattingProducts);
        modelAndView.addObject("favoriteProductListItems", favoriteProductListItems);

        modelAndView.setViewName("/shop/home");

        return modelAndView;
    }

}
