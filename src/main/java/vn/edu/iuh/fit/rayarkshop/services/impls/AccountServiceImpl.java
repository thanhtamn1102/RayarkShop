package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.Account;
import vn.edu.iuh.fit.rayarkshop.repositories.AccountRepository;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;

@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByUserNameOrEmail(String userNameOrEmail) {
        return accountRepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);
    }

}
