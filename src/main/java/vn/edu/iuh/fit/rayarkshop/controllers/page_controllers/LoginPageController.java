package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.rayarkshop.models.Account;
import vn.edu.iuh.fit.rayarkshop.models.Role;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;

@Controller
@RequestMapping("/login")
public class LoginPageController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public String loginPage() {
        return "/login";
    }

    @GetMapping("/success")
    public String loginSuccess(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);

        model.addAttribute("account", account);

        if(account.getRoles().contains(Role.ROLE_ADMIN))
            return "/admin/admin";
        return "/shop/home";
    }

}
