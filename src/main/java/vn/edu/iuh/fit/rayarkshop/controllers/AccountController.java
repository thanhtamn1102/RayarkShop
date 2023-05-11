package vn.edu.iuh.fit.rayarkshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.models.mappers.AccountMapper;
import vn.edu.iuh.fit.rayarkshop.models.requests.AccountInfoUpdateRequest;
import vn.edu.iuh.fit.rayarkshop.models.requests.AccountSignupInfoRequest;
import vn.edu.iuh.fit.rayarkshop.models.requests.ChangePasswordRequest;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;
import vn.edu.iuh.fit.rayarkshop.services.CustomerService;
import vn.edu.iuh.fit.rayarkshop.services.FirebaseStorageService;
import vn.edu.iuh.fit.rayarkshop.services.PersonService;
import vn.edu.iuh.fit.rayarkshop.utils.EncryptionPasswordGenerator;
import vn.edu.iuh.fit.rayarkshop.utils.UUIDGenerator;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PersonService personService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping(value = "/signup/user-account")
    public ResponseEntity<?> signupAccount(@RequestBody AccountSignupInfoRequest accountSignupInfo) {
        Account account = accountService.getAccountByUserNameOrEmail(accountSignupInfo.getUserName());

        if(account != null)
            return ResponseEntity.ok(-1);

        account = accountService.getAccountByUserNameOrEmail(accountSignupInfo.getEmail());
        if(account != null)
            return ResponseEntity.ok(-2);

        Person person = new Person(
                accountSignupInfo.getFullName(),
                accountSignupInfo.getFullName(),
                accountSignupInfo.getFullName(),
                accountSignupInfo.getPhone(),
                accountSignupInfo.getEmail()
        );

        Customer customer = new Customer(person);

        personService.save(person);
        customerService.save(customer);

        account = AccountMapper.toAccount(accountSignupInfo);
        account.setRoles(Arrays.asList(Role.ROLE_USER));
        account.setPerson(person);

        Account saved = accountService.save(account);
        return ResponseEntity.ok(saved != null ? 1 : 0);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccountInfo(@RequestBody AccountInfoUpdateRequest accountInfoUpdate) {
        Account account = accountService.getAccountByUserNameOrEmail(accountInfoUpdate.getUserName());
        if(account != null) {
            Person person = account.getPerson();
            if(person != null) {
                person.setFirstName(accountInfoUpdate.getFullName());
                person.setMiddleName(accountInfoUpdate.getFullName());
                person.setLastName(accountInfoUpdate.getFullName());
                person.setPhone(accountInfoUpdate.getPhone());
                person.setGender(accountInfoUpdate.isGender());
                person.setBirthday(accountInfoUpdate.getBirthday());

                Person saved = personService.save(person);
                return ResponseEntity.ok(saved);
            }
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);

        BCryptPasswordEncoder encoder = EncryptionPasswordGenerator.getBCryptPasswordEncoder();

        if(!encoder.matches(changePasswordRequest.getCurrentPassword(), account.getHashPass())) {
            return ResponseEntity.ok(-1);
        }

        if(encoder.matches(changePasswordRequest.getNewPassword(), account.getHashPass())) {
            return ResponseEntity.ok(-2);
        }

        account.setHashPass(EncryptionPasswordGenerator.encrypt(changePasswordRequest.getNewPassword()));

        Account saved = accountService.save(account);

        return ResponseEntity.ok(saved == null ? -3 : 1);
    }

    @PostMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(@RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);

        if(file != null) {
            try {
                InputStream inputStream = new BufferedInputStream(file.getInputStream());
                String fileUrl = firebaseStorageService.uploadFile(inputStream, UUIDGenerator.generate());
                if(fileUrl != null && fileUrl.isEmpty() == false) {
                    account.setAvatar(fileUrl);
                    Account saved = accountService.save(account);
                    return ResponseEntity.ok(saved == null ? Boolean.FALSE : Boolean.TRUE);
                }
            } catch (Exception ex) {
                return ResponseEntity.ok(Boolean.FALSE);
            }
        }

        return ResponseEntity.ok(Boolean.FALSE);
    }

}
