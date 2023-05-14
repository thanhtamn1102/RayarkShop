package vn.edu.iuh.fit.rayarkshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.models.mappers.SalesOrderDetailMapper;
import vn.edu.iuh.fit.rayarkshop.models.requests.HuyDonHangRequest;
import vn.edu.iuh.fit.rayarkshop.models.requests.ProductReviewRequest;
import vn.edu.iuh.fit.rayarkshop.models.requests.ProductReviewRequestContent;
import vn.edu.iuh.fit.rayarkshop.models.requests.SalesOrderRequest;
import vn.edu.iuh.fit.rayarkshop.services.*;
import vn.edu.iuh.fit.rayarkshop.utils.UUIDGenerator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(name = "Sales Order Controller", value = "/api/sales-order")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private ProductReviewImageService productReviewImageService;

    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public ResponseEntity<?> addSalesOrderService(@RequestBody SalesOrderRequest salesOrderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        int customerId = personService.findByUid(uid).getId();

        Customer customer = customerService.getById(customerId);
        ShippingAddress shippingAddress = shippingAddressService.getById(salesOrderRequest.getShippingAddressId());
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllByCustomerId(customerId);

        SalesOrder salesOrder = new SalesOrder(customer, salesOrderRequest.getGhiChu(), shippingAddress);
        List<SalesOrderDetail> salesOrderDetails = shoppingCartItems.stream()
                .map(shoppingCartItem -> SalesOrderDetailMapper.toSalesOrderDetail(salesOrder, shoppingCartItem))
                .toList();

        salesOrder.setSalesOrderDetails(salesOrderDetails);

        SalesOrder saved = salesOrderService.save(salesOrder);

        if(saved != null) {
            shoppingCartItemService.clearShoppingCartItemByCustomerId(customerId);
        }

        return ResponseEntity.ok(saved != null ? saved.getId() : -1);
    }

    @PutMapping("/huy-don-hang")
    public ResponseEntity<?> huyDonHang(@RequestBody HuyDonHangRequest huyDonHangRequest) {
        long salesOrderId = huyDonHangRequest.getSalesOrderId();
        boolean b = salesOrderService.huyDonHang(salesOrderId, huyDonHangRequest.getReason());
        return ResponseEntity.ok(b);
    }

    @PostMapping("/dat-lai")
    public ResponseEntity<?> datLaiDonHang(@RequestBody Map<String, Object> requestBody) {
        long salesOrderId = Long.parseLong((String) requestBody.get("salesOrderId"));
        boolean clearShoppingCart = (boolean) requestBody.get("clearShoppingCart");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        int personId = personService.findByUid(uid).getId();
        Customer customer = customerService.getByPersonId(personId);

        SalesOrder salesOrder = salesOrderService.findById(salesOrderId);
        if(salesOrder != null) {
            if(clearShoppingCart) {
                shoppingCartItemService.clearShoppingCartItemByCustomerId(customer.getId());
            }

            List<Boolean> b = new ArrayList<>();
            salesOrder.getSalesOrderDetails().forEach(salesOrderDetail -> {
                boolean added = addToCart(
                        customer,
                        salesOrderDetail.getProduct().getId(),
                        salesOrderDetail.getProductVariation(),
                        salesOrderDetail.getQuantity()
                );
                if(added) b.add(added);
            });
            return ResponseEntity.ok(b.size() > 0 ? Boolean.TRUE : Boolean.FALSE);
        }
        return ResponseEntity.ok(Boolean.FALSE);
    }

    @PutMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestBody Map<String, Object> body) {
        String orderIdString = (String) body.get("salesOrderId");
        long orderId = -1;
        try {
            orderId = Long.parseLong(orderIdString);
        } catch (Exception ex) {
            throw new RuntimeException("Order Id Is Not A Number");
        }

        SalesOrder salesOrder = salesOrderService.findById(orderId);

        if(salesOrder == null)
            throw new NotFoundException("Order Not Found");

        int salesOrderStatus = (int) body.get("salesOrderStatus");
        salesOrder.setStatus(OrderStatus.values()[salesOrderStatus]);

        System.out.println(orderIdString);
        System.out.println(salesOrderStatus);

        SalesOrder saved = salesOrderService.save(salesOrder);

        return ResponseEntity.ok(saved == null ? Boolean.FALSE : Boolean.TRUE);
    }

    public boolean addToCart(Customer customer, int productId, ProductVariation productVariation, int quantity) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.findByCustomerIdAndProductIdAndProductVariation(
                customer.getId(),
                productId,
                productVariation
        );

        ShoppingCartItem shoppingCartItem;

        if(shoppingCartItems != null && shoppingCartItems.size() > 0) {
            shoppingCartItem = shoppingCartItems.get(0);
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
            int updated = shoppingCartItemService.updateShoppingCartItemById(shoppingCartItem.getId(), shoppingCartItem.getQuantity());
            return updated > 0 ? Boolean.TRUE : Boolean.FALSE;
        } else {
            Product product = productService.findById(productId);
            if(product != null) {
                shoppingCartItem = new ShoppingCartItem(customer, product, productVariation, quantity);
                ShoppingCartItem saved = shoppingCartItemService.save(shoppingCartItem);
                return saved == null ? Boolean.FALSE : Boolean.TRUE;
            }
        }
        return false;
    }

}
