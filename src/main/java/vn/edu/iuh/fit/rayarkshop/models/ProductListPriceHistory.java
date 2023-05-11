package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_list_price_history")
public class ProductListPriceHistory implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "list_price", nullable = false)
    private Double listPrice;

    @Column(name = "end_date")
    private LocalDate endDate;

}
