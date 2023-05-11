package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.rayarkshop.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUserNameOrEmail(String userName, String email);

}
