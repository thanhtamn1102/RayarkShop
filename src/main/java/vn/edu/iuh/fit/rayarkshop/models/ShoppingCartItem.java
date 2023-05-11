package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart_item")
public class ShoppingCartItem {

    @Id
    @Column(name = "shopping_cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_ref", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    @EqualsAndHashCode.Include
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public ShoppingCartItem(Customer customer, Product product, ProductVariation productVariation, int quantity) {
        this.customer = customer;
        this.product = product;
        this.productVariation = productVariation;
        this.quantity = quantity;
    }

    public int plusQuantity() {
        setQuantity(getQuantity() + 1);
        return getQuantity();
    }

    public int plusQuantity(int quantity) {
        setQuantity(getQuantity() + quantity);
        return getQuantity();
    }

    public int minusQuantity() {
        if(getQuantity() - 1 > 0)
            setQuantity(getQuantity() - 1);
        return getQuantity();
    }

    public int minusQuantity(int quantity) {
        if(getQuantity() - quantity > 0)
            setQuantity(getQuantity() - quantity);
        return getQuantity();
    }

    public double lineTotalCalculate() {
        return getQuantity() * getProduct().getPrice();
    }

}
