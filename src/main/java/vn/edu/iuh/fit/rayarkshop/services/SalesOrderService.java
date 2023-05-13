package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.OrderStatus;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;

import java.util.List;

@Service
public interface SalesOrderService {

    List<SalesOrder> findAllByCustomerId(int customerId);

    public SalesOrder save(SalesOrder salesOrder);

    SalesOrder findById(long salesOrderId);

    List<SalesOrder> searchSalesOrderById(long salesOrderId);

    boolean huyDonHang(long salesOrderId, String reason);

    Page<SalesOrder> findAll(Pageable pageable);

    Page<SalesOrder> findAllByStatus(OrderStatus orderStatus, Pageable pageable);

}
