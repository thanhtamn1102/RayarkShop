package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.services.*;

import java.util.List;

@Controller
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartPageController {

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ModelAndView shoppingCartPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);
        int customerId = account.getPerson().getId();

        List<ShippingAddress> shippingAddresses = shippingAddressService.getShippingAddressesByCustomerId(customerId);
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByCustomerId(customerId);

        double subTotal = shoppingCartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("shoppingCartItems", shoppingCartItems);
        modelAndView.addObject("shippingAddresses", shippingAddresses);
        modelAndView.addObject("subTotal", subTotal);

        modelAndView.setViewName("shop/cart");

        return modelAndView;
    }

    @PostMapping("/removeCartItem")
    public ModelAndView removeCartItem(@RequestParam(defaultValue = "1") int customerId,
                                 @RequestParam long cartItemId) {
        shoppingCartItemService.removeById(cartItemId);
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByCustomerId(customerId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("shoppingCartItems", shoppingCartItems);

        modelAndView.setViewName("shop/cart");

        return modelAndView;
    }

    @GetMapping("/getAllByCustomerId")
    public ResponseEntity<?> getAllByCustomerId(@RequestParam int customerId) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByCustomerId(customerId);

        return ResponseEntity.ok(shoppingCartItems);
    }

}
