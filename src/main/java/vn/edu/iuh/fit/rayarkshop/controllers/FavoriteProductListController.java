package vn.edu.iuh.fit.rayarkshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.rayarkshop.models.Account;
import vn.edu.iuh.fit.rayarkshop.models.Customer;
import vn.edu.iuh.fit.rayarkshop.models.FavoriteProductListItem;
import vn.edu.iuh.fit.rayarkshop.models.Product;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;
import vn.edu.iuh.fit.rayarkshop.services.CustomerService;
import vn.edu.iuh.fit.rayarkshop.services.FavoriteProductListItemService;
import vn.edu.iuh.fit.rayarkshop.services.ProductService;

@RestController
@RequestMapping(name = "Favorite Product List Controller",
                value = "/api/favorite-product-list")
public class FavoriteProductListController {

    @Autowired
    private FavoriteProductListItemService favoriteProductListItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/addToFavoriteList")
    public ResponseEntity<?> addToFavoriteList(@RequestParam int productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);
        int customerId = account.getPerson().getId();

        Customer customer = customerService.getById(customerId);
        Product product = productService.findById(productId);

        FavoriteProductListItem favoriteProductListItem = null;
        if(customer != null && product != null) {
            favoriteProductListItem = favoriteProductListItemService.save(new FavoriteProductListItem(customer, product));
        }

        return ResponseEntity.ok(favoriteProductListItem == null ? Boolean.FALSE : Boolean.TRUE);
    }

    @GetMapping("/removeFromFavoriteList")
    public ResponseEntity<?> removeFromFavoriteList(@RequestParam int productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);
        int customerId = account.getPerson().getId();

        Integer b = favoriteProductListItemService.removeByCustomerIdAndProductId(customerId, productId);
        return ResponseEntity.ok(b == 1 ? Boolean.TRUE : Boolean.FALSE);
    }

}
