package vn.edu.iuh.fit.rayarkshop.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HuyDonHangRequest {

    private long salesOrderId;
    private String reason;

}
