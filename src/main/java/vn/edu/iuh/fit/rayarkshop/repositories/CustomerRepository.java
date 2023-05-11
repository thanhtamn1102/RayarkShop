package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.Customer;
import vn.edu.iuh.fit.rayarkshop.models.Person;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByPersonId(int personId);

    Customer findByPerson(Person person);

}
