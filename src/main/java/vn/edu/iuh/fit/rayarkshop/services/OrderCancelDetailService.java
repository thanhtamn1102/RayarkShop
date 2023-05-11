package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.OrderCancelDetail;

@Service
public interface OrderCancelDetailService {

    OrderCancelDetail save(OrderCancelDetail orderCancelDetail);

}
