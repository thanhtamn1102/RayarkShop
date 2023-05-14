package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.constants.AppRole;

@Controller
@RequestMapping("/login")
public class LoginPageController {

    @GetMapping("")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/success")
    public ModelAndView loginSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView modelAndView = new ModelAndView();

        System.out.println(authentication);

        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(AppRole.ADMIN.getRoleName())))
            modelAndView.setViewName("admin/admin");
        else
            modelAndView.setViewName("shop/home");

        return modelAndView;
    }

}
