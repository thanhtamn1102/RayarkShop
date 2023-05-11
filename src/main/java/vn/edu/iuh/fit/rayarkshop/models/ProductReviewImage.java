package vn.edu.iuh.fit.rayarkshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_review_image")
public class ProductReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_image_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_review_id", nullable = false)
    private ProductReview productReview;

    @Column(name = "file_name", length = 255)
    private String fileName;

    @Column(name = "path", columnDefinition = "text", nullable = false)
    private String path;

    public ProductReviewImage(ProductReview productReview, String fileName, String path) {
        this.productReview = productReview;
        this.fileName = fileName;
        this.path = path;
    }
}
