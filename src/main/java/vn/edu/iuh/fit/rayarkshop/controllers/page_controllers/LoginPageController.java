package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.Account;
import vn.edu.iuh.fit.rayarkshop.models.Role;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;

@Controller
@RequestMapping("/login")
public class LoginPageController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/success")
    public ModelAndView loginSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("account", account);

        if(account.getRoles().contains(Role.ROLE_ADMIN))
            modelAndView.setViewName("/admin/admin");
        else
            modelAndView.setViewName("/shop/home");

        return modelAndView;
    }

}
