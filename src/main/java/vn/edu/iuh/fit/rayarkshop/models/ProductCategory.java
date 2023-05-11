package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "num_of_products")
    private int numOfProducts;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ProductCategory> children;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ProductCategory parent;

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(Integer id, ProductCategory parent) {
        this.id = id;
        this.parent = parent;
    }
}
