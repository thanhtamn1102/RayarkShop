package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_size")
public class ProductSize {

    @Id
    @Column(name = "product_size_id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "size_name", length = 60, nullable = false)
    private String name;

}
