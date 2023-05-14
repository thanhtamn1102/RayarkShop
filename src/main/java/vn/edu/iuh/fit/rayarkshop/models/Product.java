package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.edu.iuh.fit.rayarkshop.constants.Constants;

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

    @Column(name = "import_price")
    private double importPrice;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "total_sold")
    private Integer totalSold;

    @Column(name = "average_rating")
    private Double averageRatting;

    @Column(name = "sku", length = 255)
    private String sku;

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

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductPhoto> productPhotos;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "remove_date")
    private LocalDateTime removeDate;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductVariation> productVariations;

    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptions;

    @Column(name = "product_status", nullable = true)
    private int productStatus;

    public Product(String name, double importPrice, String sku, String description, Brand brand, List<ProductCategory> productCategories) {
        this.name = name;
        this.importPrice = importPrice;
        this.price = importPrice * (1.0 + 0.4);
        this.description = description;
        this.brand = brand;
        this.productCategories = productCategories;
        this.createdDate = LocalDateTime.now();
        this.averageRatting = 0.0;
        this.totalSold = 0;
        this.productStatus = 0;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
        this.price = importPrice * (1.0 + 0.4);
    }
}
