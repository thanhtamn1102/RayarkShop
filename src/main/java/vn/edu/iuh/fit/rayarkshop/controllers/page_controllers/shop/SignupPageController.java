package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class SignupPageController {

    @GetMapping("")
    public ModelAndView signupPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop/signup");
        return modelAndView;
    }

    @GetMapping("/success")
    public ModelAndView signupSuccessPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/shop/signup-complete");
        return modelAndView;
    }

}
