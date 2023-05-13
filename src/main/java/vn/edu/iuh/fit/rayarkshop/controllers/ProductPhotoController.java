package vn.edu.iuh.fit.rayarkshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.rayarkshop.services.ProductPhotoService;

import java.util.Map;

@RestController("/api/product-photos")
public class ProductPhotoController {

    @Autowired
    private ProductPhotoService productPhotoService;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductPhoto(@RequestBody Map<String, Object> body) {
        String productPhotoIdString = (String) body.get("productPhotoId");
        long productPhotoId = -1;
        try {
            productPhotoId = Long.parseLong(productPhotoIdString);
        } catch (Exception ex) {
            throw new RuntimeException("Product Photo Id Is Not A Number");
        }

        int removed = productPhotoService.removeById(productPhotoId);

        return ResponseEntity.ok(removed == 1 ? Boolean.TRUE : Boolean.FALSE);
    }

}
