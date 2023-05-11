package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class SalesOrderDetailId implements Serializable {

    @EqualsAndHashCode.Include
    private long salesOrder;

    @EqualsAndHashCode.Include
    private int product;

}
