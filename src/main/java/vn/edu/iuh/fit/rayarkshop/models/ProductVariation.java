package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variation")
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_variation_id")
    private long id;

    @Column(name = "product_variation_sku", length = 60)
    private String sku;

    @Column(name = "product_variation_name", length = 255, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "productVariation")
    private List<ProductVariationProductOptionValue> productVariationProductOptionValues;

    @OneToMany(mappedBy = "productVariation", fetch = FetchType.EAGER)
    private List<ProductPhoto> productPhotos;

    @OneToOne(mappedBy = "productVariation")
    private ProductInventory productInventory;

}
