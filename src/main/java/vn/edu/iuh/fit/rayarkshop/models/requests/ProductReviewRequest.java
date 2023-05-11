package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ProductReviewRequest {

    private long salesOrderId;
    private List<ProductReviewRequestContent> productReviewContents;

}
