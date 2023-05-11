package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales_order_detail")
public class SalesOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_order_detail_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "sales_order_id", nullable = false)
    private SalesOrder salesOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "line_total", nullable = false)
    private double lineTotal;

    public SalesOrderDetail(SalesOrder salesOrder, Product product, ProductVariation productVariation, int quantity, double unitPrice) {
        this.salesOrder = salesOrder;
        this.product = product;
        this.productVariation = productVariation;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = this.unitPrice * this.quantity;
    }
}
