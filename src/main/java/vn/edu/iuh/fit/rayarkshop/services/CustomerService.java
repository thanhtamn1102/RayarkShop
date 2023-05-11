package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.Customer;
import vn.edu.iuh.fit.rayarkshop.models.Person;

@Service
public interface CustomerService {

    Customer getById(int id);

    Customer getByPersonId(int personId);

    Customer getByPerson(Person person);

    Customer save(Customer customer);

}
