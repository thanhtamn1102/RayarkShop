package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.repositories.EmployeeRepository;
import vn.edu.iuh.fit.rayarkshop.services.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

}
