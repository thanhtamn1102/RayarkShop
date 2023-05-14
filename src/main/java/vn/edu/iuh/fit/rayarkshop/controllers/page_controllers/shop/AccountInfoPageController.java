package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.Person;
import vn.edu.iuh.fit.rayarkshop.services.PersonService;

@Controller
@RequestMapping("/accounts")
public class AccountInfoPageController {

    @Autowired
    private PersonService personService;


    @GetMapping("")
    public ModelAndView accountInfoPage() throws FirebaseAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();

        UserRecord user = FirebaseAuth.getInstance().getUser(uid);

        Person person = personService.findByUid(uid);

        if(user == null)
            throw new NotFoundException("Not Found Exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", user);
        modelAndView.addObject("person", person);

        modelAndView.setViewName("shop/account-info");

        return modelAndView;
    }

}
