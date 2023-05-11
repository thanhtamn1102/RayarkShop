package vn.edu.iuh.fit.rayarkshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_variation_product_option_value")
@IdClass(ProductVariationProductOptionValueId.class)
public class ProductVariationProductOptionValue {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_option_value_id")
    private ProductOptionValue productOptionValue;

    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

}
