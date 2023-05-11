package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "num_of_products")
    private int numOfProducts;

    public Brand(String name) {
        this.name = name;
    }
}
