package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesOrderRequest {

    private int shippingAddressId;
    private int phuongThucNhanHang;
    private int phuongThucThanhToan;
    private String ghiChu;

}
