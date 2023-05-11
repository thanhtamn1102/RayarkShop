package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.OrderCancelDetail;
import vn.edu.iuh.fit.rayarkshop.repositories.OrderCancelDetailRepository;
import vn.edu.iuh.fit.rayarkshop.services.OrderCancelDetailService;

@Component
public class OrderCancelDetailServiceImpl implements OrderCancelDetailService {

    @Autowired
    private OrderCancelDetailRepository orderCancelDetailRepository;

    @Override
    public OrderCancelDetail save(OrderCancelDetail orderCancelDetail) {
        return orderCancelDetailRepository.save(orderCancelDetail);
    }
}
