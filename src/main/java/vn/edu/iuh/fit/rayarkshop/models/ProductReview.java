package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_review")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id")
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne
    @JoinColumn(name = "sales_order_id", nullable = false)
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", columnDefinition = "text", nullable = false)
    private String comment;

    @OneToMany(mappedBy = "productReview", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductReviewImage> productReviewImages;

    @Column(name = "review_date", nullable = false)
    private LocalDateTime reviewDate;

    public ProductReview(SalesOrder salesOrder, Customer customer, Product product, int rating, String comment) {
        this.salesOrder = salesOrder;
        this.customer = customer;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = LocalDateTime.now();
    }
}
