package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.Account;

@Service
public interface AccountService {

    Account save(Account account);

    Account getAccountByUserNameOrEmail(String userNameOrEmail);

}
