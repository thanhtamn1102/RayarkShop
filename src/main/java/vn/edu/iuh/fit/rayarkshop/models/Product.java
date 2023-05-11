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
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "total_sold")
    private Integer totalSold;

    @Column(name = "average_rating")
    private Double averageRatting;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns =  @JoinColumn(name = "product_category_id")
    )
    private List<ProductCategory> productCategories;

//    @ManyToMany
//    @JoinTable(
//            name = "product_product_size",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns =  @JoinColumn(name = "product_size_id")
//    )
//    private List<ProductSize> productSizes;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductPhoto> productPhotos;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductVariation> productVariations;

    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptions;

    public Product(String name, Double price, String description, Brand brand, List<ProductCategory> productCategories) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.productCategories = productCategories;
        this.createdDate = LocalDateTime.now();
    }
}
