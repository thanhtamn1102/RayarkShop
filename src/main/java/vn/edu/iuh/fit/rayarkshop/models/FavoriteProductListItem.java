package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "favorite_product_list_item")
public class FavoriteProductListItem {

    @Id
    @Column(name = "favorite_product_list_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    @EqualsAndHashCode.Include
    private Product product;

    public FavoriteProductListItem(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }
}
