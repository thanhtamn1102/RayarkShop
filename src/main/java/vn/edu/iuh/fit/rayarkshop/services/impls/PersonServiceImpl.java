package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.Person;
import vn.edu.iuh.fit.rayarkshop.repositories.PersonRepository;
import vn.edu.iuh.fit.rayarkshop.services.PersonService;

@Component
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
