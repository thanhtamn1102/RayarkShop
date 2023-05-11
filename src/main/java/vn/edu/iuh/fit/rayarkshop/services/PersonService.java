package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.Person;

@Service
public interface PersonService {

    Person save(Person person);

}
