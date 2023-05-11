package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrderDetail;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrderDetailId;

public interface SalesOrderDetailRepository extends JpaRepository<SalesOrderDetail, Long> {
}
