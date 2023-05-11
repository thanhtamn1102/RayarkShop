package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.repositories.SalesOrderDetailRepository;
import vn.edu.iuh.fit.rayarkshop.services.SalesOrderDetailService;

@Component
public class SalesOrderDetailServiceImpl implements SalesOrderDetailService {

    @Autowired
    private SalesOrderDetailRepository salesOrderDetailRepository;

}
