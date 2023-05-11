package vn.edu.iuh.fit.rayarkshop.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_address")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_adress_id")
    private int shippingAddressId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "name_of_consignee", length = 60, nullable = false)
    private String nameOfConsignee;

    @Column(name = "phone", length = 15, nullable = false)
    private String phone;

    @Column(name = "province_code", length = 2, nullable = false)
    private String provinceCode;

    @Column(name = "province_name", length = 60, nullable = false)
    private String provinceName;

    @Column(name = "district_code", length = 3, nullable = false)
    private String districtCode;

    @Column(name = "district_name", length = 60, nullable = false)
    private String districtName;

    @Column(name = "ward_code", length = 5, nullable = false)
    private String wardCode;

    @Column(name = "ward_name", length = 60, nullable = false)
    private String wardName;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    public ShippingAddress(Customer customer, String nameOfConsignee, String phone, String provinceCode, String provinceName, String districtCode, String districtName, String wardCode, String wardName, String address) {
        this.customer = customer;
        this.nameOfConsignee = nameOfConsignee;
        this.phone = phone;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.wardCode = wardCode;
        this.wardName = wardName;
        this.address = address;
    }
}
