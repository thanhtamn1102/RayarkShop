package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedPageController {

    @GetMapping("/access-denied")
    public ModelAndView accessDeniedPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("access-denied");
        return modelAndView;
    }

}
