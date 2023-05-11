package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.rayarkshop.models.Account;
import vn.edu.iuh.fit.rayarkshop.models.Person;
import vn.edu.iuh.fit.rayarkshop.models.requests.AccountInfoUpdateRequest;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;
import vn.edu.iuh.fit.rayarkshop.services.PersonService;

@Controller
@RequestMapping("/accounts")
public class AccountInfoPageController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PersonService personService;


    @GetMapping("")
    public String accountInfoPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Account account = accountService.getAccountByUserNameOrEmail(username);

        model.addAttribute("account", account);
        model.addAttribute("person", account.getPerson());

        return "/shop/account-info";
    }

}