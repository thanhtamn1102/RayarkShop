package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.OrderCancelDetail;
import vn.edu.iuh.fit.rayarkshop.models.OrderStatus;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;
import vn.edu.iuh.fit.rayarkshop.repositories.SalesOrderRepository;
import vn.edu.iuh.fit.rayarkshop.services.SalesOrderService;

import java.util.List;

@Component
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Override
    public List<SalesOrder> findAllByCustomerId(int customerId) {
        return salesOrderRepository.findAllByCustomerId(customerId);
    }

    @Override
    public SalesOrder save(SalesOrder salesOrder) {
        return salesOrderRepository.save(salesOrder);
    }

    @Override
    public SalesOrder findById(long salesOrderId) {
        return salesOrderRepository.findById(salesOrderId).orElse(null);
    }

    @Override
    public List<SalesOrder> searchSalesOrderById(long salesOrderId) {
        return salesOrderRepository.searchSalesOrderById(salesOrderId);
    }

    @Override
    public boolean huyDonHang(long salesOrderId, String reason) {
        SalesOrder salesOrder = findById(salesOrderId);

        if(salesOrder == null)
            return false;

        OrderCancelDetail orderCancelDetail = new OrderCancelDetail(salesOrder, reason);

        salesOrder.setStatus(OrderStatus.DA_HUY);
        salesOrder.setOrderCancelDetail(orderCancelDetail);

        SalesOrder updated = save(salesOrder);

        return updated != null ? true : false;
    }
}
