package vn.edu.iuh.fit.rayarkshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.models.dto.AddProductReviewDTO;
import vn.edu.iuh.fit.rayarkshop.models.requests.ProductReviewRequest;
import vn.edu.iuh.fit.rayarkshop.models.requests.ProductReviewRequestContent;
import vn.edu.iuh.fit.rayarkshop.services.*;
import vn.edu.iuh.fit.rayarkshop.utils.UUIDGenerator;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product-reviews")
public class ProductReviewController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public ResponseEntity<?> productReviews(@RequestBody ProductReviewRequest productReviewRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        int customerId = personService.findByUid(uid).getId();

        Customer customer = customerService.getById(customerId);

        SalesOrder salesOrder = salesOrderService.findById(productReviewRequest.getSalesOrderId());

        if(salesOrder == null) {
            return ResponseEntity.ok(Boolean.FALSE);
        }

        List<ProductReview> saveds = new ArrayList<>();

        for(ProductReviewRequestContent productReviewContent : productReviewRequest.getProductReviewContents()) {
            Product product = productService.findById(productReviewContent.getProductId());
            if (product == null)
                return ResponseEntity.ok(null);

            ProductReview productReview = new ProductReview(salesOrder, customer, product, productReviewContent.getRatting(), productReviewContent.getComment());

            ProductReview saved = productReviewService.save(productReview);
            if (saved != null)
                saveds.add(saved);
        }

        return ResponseEntity.ok(saveds != null ? saveds.stream().map(saved -> new AddProductReviewDTO(saved.getId(), saved.getProduct().getId())).toList() : null);
    }

    @PostMapping("/upload-review-images")
    public ResponseEntity<?> uploadReviewImages(@RequestParam long productReviewId, @RequestParam("files") List<MultipartFile> files) {
        ProductReview productReview = productReviewService.findById(productReviewId);

        if(productReview == null)
            return ResponseEntity.ok(Boolean.FALSE);

        List<InputStream> inputStreamList = new ArrayList<>();
        if(files != null) {
            inputStreamList = files.stream().map(file -> {
                try {
                    InputStream inputStream = new BufferedInputStream(file.getInputStream());
                    return inputStream;
                } catch (Exception ex) {
                    return null;
                }
            }).toList();
        }

        List<ProductReviewImage> productReviewImages = new ArrayList<>();
        for(InputStream inputStream : inputStreamList) {
            try {
                String fileName = UUIDGenerator.generate();
                String fileUrl = firebaseStorageService.uploadFile(inputStream, fileName);
                if(fileUrl != null && fileUrl.isEmpty() == false)
                    productReviewImages.add(new ProductReviewImage(productReview, fileName, fileUrl));
            } catch (Exception ex) {
                return ResponseEntity.ok(Boolean.FALSE);
            }
        }

        productReview.setProductReviewImages(productReviewImages);

        ProductReview saved = productReviewService.save(productReview);

        return ResponseEntity.ok(saved == null ? Boolean.FALSE : Boolean.TRUE);
    }

}
