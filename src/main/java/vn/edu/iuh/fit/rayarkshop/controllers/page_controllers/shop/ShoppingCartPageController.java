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
    public String shoppingCartPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);
        int customerId = account.getPerson().getId();

        List<ShippingAddress> shippingAddresses = shippingAddressService.getShippingAddressesByCustomerId(customerId);
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByCustomerId(customerId);

        double subTotal = shoppingCartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

        model.addAttribute("shoppingCartItems", shoppingCartItems);
        model.addAttribute("shippingAddresses", shippingAddresses);
        model.addAttribute("subTotal", subTotal);

        return "shop/cart";
    }

    @PostMapping("/removeCartItem")
    public String removeCartItem(@RequestParam(defaultValue = "1") int customerId,
                                 @RequestParam long cartItemId,
                                 Model model) {
        shoppingCartItemService.removeById(cartItemId);
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByCustomerId(customerId);
        model.addAttribute("shoppingCartItems", shoppingCartItems);

        return "shop/cart";
    }

//    @PostMapping("/addToCart")
//    public ResponseEntity<?> addToCart(@RequestParam int customerId,
//                                       @RequestParam int productId,
//                                       @RequestParam(defaultValue = "1") int quantity) {
//        Customer customer = customerService.getById(customerId);
//        if(customer == null) {
//            return ResponseEntity.ok("Customer Not Found");
//        }
//
//        Product product = productService.findById(productId);
//        if(product == null) {
//            return ResponseEntity.ok("Product Not Found");
//        }
//
//        if(quantity < 1) {
//            return ResponseEntity.ok("Quantity needs to be greater than or equal to 1");
//        }
//
//        ShoppingCartItem shoppingCartItem = shoppingCartItemService.save(new ShoppingCartItem(customer, product, quantity));
//
//        return ResponseEntity.ok(shoppingCartItem != null ? Boolean.TRUE : Boolean.FALSE);
//    }

    @GetMapping("/getAllByCustomerId")
    public ResponseEntity<?> getAllByCustomerId(@RequestParam int customerId) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByCustomerId(customerId);

        return ResponseEntity.ok(shoppingCartItems);
    }

}
