package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.constants.AppRole;
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
    private PersonService personService;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView homePage() throws FirebaseAuthException {
        List<Product> favoriteProductListItems = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);

        if(authentication.isAuthenticated() &&
                authentication.getAuthorities().contains(new SimpleGrantedAuthority(AppRole.ANONYMOUS.getRoleName())) == false) {
            String uid = authentication.getName();

            System.out.println(uid);

            UserRecord user = FirebaseAuth.getInstance().getUser(uid);

            System.out.println(user);

            if(user != null) {
                int customerId = personService.findByUid(uid).getId();

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

        modelAndView.setViewName("shop/home");

        return modelAndView;
    }

}
