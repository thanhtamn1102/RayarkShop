package vn.edu.iuh.fit.rayarkshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.models.requests.ProductRequest;
import vn.edu.iuh.fit.rayarkshop.services.*;
import vn.edu.iuh.fit.rayarkshop.utils.UUIDGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInventoryService productInventoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Autowired
    private ProductPhotoService productPhotoService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        double importPrice = -1;
        try {
            importPrice = Double.parseDouble(productRequest.getImportPrice());
        } catch (Exception ex) {
            throw new RuntimeException("Import Price Is Not A Number");
        }

        Brand brand = brandService.getById(productRequest.getBrandId());

        if(brand == null)
            throw new NotFoundException("Brand Not Found");

        List<ProductCategory> categories = new ArrayList<>();

        productRequest.getCategoryIds().forEach(categoryId -> {
            ProductCategory productCategory = productCategoryService.getById(categoryId);
            if(productCategory != null)
                categories.add(productCategory);
        });

        if(categories.size() == 0)
            throw new NotFoundException("Categories Not Found");

        Product product = new Product(
                productRequest.getProductName(),
                importPrice,
                productRequest.getSku(),
                productRequest.getDescription(),
                brand,
                categories
        );

        Product saved = productService.save(product);

        if(saved == null)
            throw new RuntimeException("Saved Product Error");

        ProductInventory productInventory = new ProductInventory(product, null, productRequest.getQuantity());

        ProductInventory productInventorySaved = productInventoryService.save(productInventory);

        return ResponseEntity.ok(productInventorySaved != null ? saved.getId() : -1);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest) {
        double importPrice = -1;
        try {
            importPrice = Double.parseDouble(productRequest.getImportPrice());
        } catch (Exception ex) {
            throw new RuntimeException("Import Price Is Not A Number");
        }

        Brand brand = brandService.getById(productRequest.getBrandId());

        if(brand == null)
            throw new NotFoundException("Brand Not Found");

        List<ProductCategory> categories = new ArrayList<>();

        productRequest.getCategoryIds().forEach(categoryId -> {
            ProductCategory productCategory = productCategoryService.getById(categoryId);
            if(productCategory != null)
                categories.add(productCategory);
        });

        if(categories.size() == 0)
            throw new NotFoundException("Categories Not Found");

        Product product = productService.findById(productRequest.getProductId());

        if(product == null)
            throw new NotFoundException("Product Not Found");

        product.setName(productRequest.getProductName());
        product.setSku(productRequest.getSku());
        product.setImportPrice(importPrice);
        product.setDescription(productRequest.getDescription());
        product.setBrand(brand);
        product.setProductCategories(categories);
        product.setProductStatus(productRequest.getProductStatus());

        Product saved = productService.save(product);

        if(saved == null)
            throw new RuntimeException("Saved Product Error");

        ProductInventory productInventory = productInventoryService.findByProductIdAndProductVariation(productRequest.getProductId(), null);

        if(productInventory == null)
            throw new NotFoundException("Product Inventory Not Found");

        productInventory.setQuantity(productRequest.getQuantity());

        ProductInventory productInventorySaved = productInventoryService.save(productInventory);

        return ResponseEntity.ok(productInventorySaved != null ? saved.getId() : -1);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody Map<String, Object> body) {
        int productId = (int) body.get("productId");
        boolean deleted = productService.deleteProductById(productId);
        return ResponseEntity.ok(deleted);
    }

    @PutMapping("/upload-product-photos")
    public ResponseEntity<?> uploadReviewImages(@RequestParam int productId, @RequestParam("files") List<MultipartFile> files) {
        Product product = productService.findById(productId);

        if(product == null)
            return ResponseEntity.ok(Boolean.FALSE);

        List<ProductPhoto> productPhotos = new ArrayList<>();
        for(MultipartFile file : files) {
            try {
                String fileName = UUIDGenerator.generate();
                String fileUrl = firebaseStorageService.uploadFile(file, fileName);
                if(fileUrl != null && fileUrl.isEmpty() == false)
                    productPhotos.add(new ProductPhoto(
                            product,
                            null,
                            fileName,
                            fileUrl,
                            fileName,
                            fileUrl
                    ));
            } catch (Exception ex) {
                return ResponseEntity.ok(Boolean.FALSE);
            }
        }

        List<ProductPhoto> saved = new ArrayList<>();
        if(productPhotos.size() > 0)
            saved = productPhotoService.saveAll(productPhotos);

        return ResponseEntity.ok(saved.size() > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

}
