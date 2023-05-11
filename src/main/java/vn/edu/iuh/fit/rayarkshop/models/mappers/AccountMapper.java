package vn.edu.iuh.fit.rayarkshop.models.mappers;


import vn.edu.iuh.fit.rayarkshop.models.Account;
import vn.edu.iuh.fit.rayarkshop.models.requests.AccountSignupInfoRequest;
import vn.edu.iuh.fit.rayarkshop.utils.EncryptionPasswordGenerator;

public class AccountMapper {

    public static Account toAccount(AccountSignupInfoRequest accountSignupInfo) {
        Account account = new Account(
                accountSignupInfo.getUserName(),
                accountSignupInfo.getEmail(),
                EncryptionPasswordGenerator.encrypt(accountSignupInfo.getPassword())
        );
        return account;
    }

}
