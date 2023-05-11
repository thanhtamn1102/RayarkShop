package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.Customer;
import vn.edu.iuh.fit.rayarkshop.models.Person;
import vn.edu.iuh.fit.rayarkshop.repositories.CustomerRepository;
import vn.edu.iuh.fit.rayarkshop.services.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer getByPersonId(int personId) {
        return customerRepository.findByPersonId(personId);
    }

    @Override
    public Customer getByPerson(Person person) {
        return customerRepository.findByPerson(person);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }


}
