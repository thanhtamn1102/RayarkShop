package vn.edu.iuh.fit.rayarkshop.controllers;

import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.iuh.fit.rayarkshop.constants.AppRole;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.models.requests.AccountInfoUpdateRequest;
import vn.edu.iuh.fit.rayarkshop.models.requests.AccountSignupInfoRequest;
import vn.edu.iuh.fit.rayarkshop.models.requests.ChangePasswordRequest;
import vn.edu.iuh.fit.rayarkshop.services.*;
import vn.edu.iuh.fit.rayarkshop.utils.UUIDGenerator;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Autowired
    private FirebaseAuthenticationService firebaseAuthenticationService;

    @PostMapping(value = "/signup/user-account")
    public ResponseEntity<?> signupAccount(@RequestBody AccountSignupInfoRequest accountSignupInfo) {
        try {
            UserRecord userRecord = firebaseAuthenticationService.createLoginWithEmailAndPassword(
                    accountSignupInfo.getEmail(),
                    accountSignupInfo.getPassword(),
                    Arrays.asList(AppRole.USER)
            );

            Person person = new Person(
                    userRecord.getUid(),
                    accountSignupInfo.getFullName(),
                    accountSignupInfo.getFullName(),
                    accountSignupInfo.getFullName(),
                    accountSignupInfo.getPhone(),
                    accountSignupInfo.getEmail()
            );

            Customer customer = new Customer(person);

            personService.save(person);
            customerService.save(customer);

            return ResponseEntity.ok(userRecord != null ? 1 : 0);
        } catch (FirebaseAuthException ex) {
            if(ex.getAuthErrorCode() == AuthErrorCode.EMAIL_ALREADY_EXISTS)
                return ResponseEntity.ok(-2);
            throw new RuntimeException(ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccountInfo(@RequestBody AccountInfoUpdateRequest accountInfoUpdate) throws FirebaseAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        Person person = personService.findByUid(uid);

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

        return ResponseEntity.ok(null);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws IOException, FirebaseAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        Person person = personService.findByUid(uid);
        if(person != null) {
            String email = person.getEmail();

            Object o = firebaseAuthenticationService.changePassword(email, changePasswordRequest.getCurrentPassword(), changePasswordRequest.getNewPassword());

            System.out.println(o);

            return ResponseEntity.ok(o);
        }

        return ResponseEntity.ok(null);
    }

    @PostMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(@RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();

        if(file != null) {
            try {
                String fileUrl = firebaseStorageService.uploadFile(file, UUIDGenerator.generate());
                if(fileUrl != null && fileUrl.isEmpty() == false) {
                    boolean b = firebaseAuthenticationService.changePhotoUrl(uid, fileUrl);
                    return ResponseEntity.ok(b);
                }
            } catch (Exception ex) {
                return ResponseEntity.ok(Boolean.FALSE);
            }
        }

        return ResponseEntity.ok(Boolean.FALSE);
    }

}
