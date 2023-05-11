package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    @EqualsAndHashCode.Include
    private long id;

//    @ManyToOne
//    @JoinColumn(name = "province_code", nullable = false)
//    private Province province;
//
//    @ManyToOne
//    @JoinColumn(name = "district_code", nullable = false)
//    private District district;
//
//    @ManyToOne
//    @JoinColumn(name = "ward_code", nullable = false)
//    private Ward ward;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

}
