package vn.edu.iuh.fit.rayarkshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.models.dto.ShoppingCartItemDTO;
import vn.edu.iuh.fit.rayarkshop.models.mappers.ShoppingCartItemMapper;
import vn.edu.iuh.fit.rayarkshop.models.requests.AddToCartRequest;
import vn.edu.iuh.fit.rayarkshop.models.requests.ShoppingCartItemRequest;
import vn.edu.iuh.fit.rayarkshop.services.*;

import java.util.List;

@RestController
@RequestMapping(name = "Shopping Cart Controller",
        value = "/api/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInventoryService productInventoryService;

    @Autowired
    private ProductVariationProductOptionValueService productVariationProductOptionValueService;

    @Autowired
    private PersonService personService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        if(addToCartRequest.getQuantity() <= 0)
            return ResponseEntity.ok(Boolean.FALSE);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        int personId = personService.findByUid(uid).getId();
        Customer customer = customerService.getByPersonId(personId);

        ProductVariationProductOptionValue variationProductOptionValue =
                productVariationProductOptionValueService.findById(
                        new ProductVariationProductOptionValueId(
                                addToCartRequest.getProductId(),
                                addToCartRequest.getOptionId(),
                                addToCartRequest.getOptionValueId())
                );

        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.findByCustomerIdAndProductIdAndProductVariation(
                customer.getId(),
                addToCartRequest.getProductId(),
                (variationProductOptionValue != null ? variationProductOptionValue.getProductVariation() : null)
        );

        ShoppingCartItem shoppingCartItem;

        if(shoppingCartItems != null && shoppingCartItems.size() > 0) {
            shoppingCartItem = shoppingCartItems.get(0);
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + addToCartRequest.getQuantity());
            int updated = shoppingCartItemService.updateShoppingCartItemById(shoppingCartItem.getId(), shoppingCartItem.getQuantity());
            return ResponseEntity.ok(updated > 0 ? Boolean.TRUE : Boolean.FALSE);
        } else {
            Product product = productService.findById(addToCartRequest.getProductId());

            if(product != null) {
                shoppingCartItem = new ShoppingCartItem(
                        customer,
                        product,
                        (variationProductOptionValue != null ? variationProductOptionValue.getProductVariation() : null),
                        addToCartRequest.getQuantity()
                );
                ShoppingCartItem saved = shoppingCartItemService.save(shoppingCartItem);
                return ResponseEntity.ok(saved == null ? Boolean.FALSE : Boolean.TRUE);
            }
        }
        return ResponseEntity.ok(Boolean.FALSE);
    }

    @GetMapping("/getAllByCustomerId")
    public ResponseEntity<?> getAllByCustomerId(@RequestParam int customerId) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByCustomerId(customerId);
        return ResponseEntity.ok(shoppingCartItems);
    }

    @GetMapping("/remove")
    public ResponseEntity<?> removeShoppingCartItem(@RequestParam int shoppingCartItemId) {
        Integer b = shoppingCartItemService.removeById(shoppingCartItemId);
        return ResponseEntity.ok(b);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShoppingCartItem(@RequestBody ShoppingCartItemRequest shoppingCartItemRequest) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemService.getById(shoppingCartItemRequest.getShoppingCartItemId());

        if(shoppingCartItem != null) {
            ProductInventory productInventory = productInventoryService.findByProductIdAndProductVariation(
                    shoppingCartItem.getProduct().getId(),
                    shoppingCartItem.getProductVariation()
            );

            if(productInventory != null) {
                if(shoppingCartItemRequest.getQuantity() <= productInventory.getQuantity())
                    shoppingCartItem.setQuantity(shoppingCartItemRequest.getQuantity());
                else
                    return ResponseEntity.ok(-1);

                ShoppingCartItem saved = shoppingCartItemService.save(shoppingCartItem);
                saved.lineTotalCalculate();

                ShoppingCartItemDTO shoppingCartItemDTO = null;

                if(saved != null)
                    shoppingCartItemDTO = ShoppingCartItemMapper.toShoppingCartItemDTO(saved);

                return ResponseEntity.ok(shoppingCartItemDTO);
            }
        }
        return ResponseEntity.ok(null);
    }

}
