package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findFirstByUid(String uid);

}
