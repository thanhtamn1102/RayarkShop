package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales_order")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_order_id")
    @EqualsAndHashCode.Include
    private long id;

    @ManyToOne
    @JoinColumn(name = "sales_person_id")
    private SalesPerson salesPerson;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "ship_date")
    private LocalDateTime shipDate;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "total_due")
    private double totalDue;

    @Column(name = "comment", columnDefinition = "text")
    private String comment;

    @Column(name = "order_status", nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SalesOrderDetail> salesOrderDetails;

    @OneToOne(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private OrderCancelDetail orderCancelDetail;

    public SalesOrder(Customer customer, String comment, ShippingAddress shippingAddress) {
        this.customer = customer;
        this.comment = comment;
        this.shippingAddress = shippingAddress;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.CHO_XAC_NHAN;
    }

    public void setSalesOrderDetails(List<SalesOrderDetail> salesOrderDetails) {
        this.salesOrderDetails = salesOrderDetails;
        this.subTotal = salesOrderDetails.stream().mapToDouble(SalesOrderDetail::getLineTotal).sum();
        this.totalDue = this.subTotal;
    }
}
