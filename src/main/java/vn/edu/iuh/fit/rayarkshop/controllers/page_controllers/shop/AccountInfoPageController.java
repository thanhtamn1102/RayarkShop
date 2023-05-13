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
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
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
    public ModelAndView accountInfoPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Account account = accountService.getAccountByUserNameOrEmail(username);

        if(account == null)
            throw new NotFoundException("Not Found Exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("account", account);
        modelAndView.addObject("person", account.getPerson());

        modelAndView.setViewName("shop/account-info");

        return modelAndView;
    }

}
