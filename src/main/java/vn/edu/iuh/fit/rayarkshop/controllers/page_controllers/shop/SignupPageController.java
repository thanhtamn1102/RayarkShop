package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;

@Controller
@RequestMapping("/signup")
public class SignupPageController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public String signupPage() {
        return "/shop/signup";
    }

//    @PostMapping("/user-account")
//    public String signupAccount(@RequestBody AccountSignupInfoRequest accountSignupInfo) {
//        Account account = AccountMapper.toAccount(accountSignupInfo);
//        account.setRoles(Arrays.asList(Role.ROLE_USER));
//        Account saved = accountService.save(account);
//        return "/login";
//    }

}
