package vn.edu.iuh.fit.rayarkshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_cancel_detail")
public class OrderCancelDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_cancel_detail_id")
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "sales_order_id", nullable = false)
    private SalesOrder salesOrder;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "reason", length = 512, nullable = false)
    private String reason;

    public OrderCancelDetail(SalesOrder salesOrder, String reason) {
        this.salesOrder = salesOrder;
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
    }
}
