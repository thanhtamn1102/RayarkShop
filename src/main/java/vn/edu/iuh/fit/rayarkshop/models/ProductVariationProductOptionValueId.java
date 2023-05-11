package vn.edu.iuh.fit.rayarkshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class ProductVariationProductOptionValueId implements Serializable {

    @EqualsAndHashCode.Include
    private int product;

    @EqualsAndHashCode.Include
    private int productOption;

    @EqualsAndHashCode.Include
    private int productOptionValue;

}
