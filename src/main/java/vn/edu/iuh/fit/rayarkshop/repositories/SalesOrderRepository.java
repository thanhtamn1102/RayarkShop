package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;

import java.util.List;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    List<SalesOrder> findAllByCustomerId(int customerId);

    List<SalesOrder> searchSalesOrderById(long salesOrderId);

}
