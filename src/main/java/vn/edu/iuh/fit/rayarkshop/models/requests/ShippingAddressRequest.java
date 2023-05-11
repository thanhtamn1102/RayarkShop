package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingAddressRequest {

    private int shippingAddressId;
    private int customerId;
    private String nameOfConsignee;
    private String phone;
    private String provinceCode;
    private String districtCode;
    private String wardCode;
    private String address;

}
