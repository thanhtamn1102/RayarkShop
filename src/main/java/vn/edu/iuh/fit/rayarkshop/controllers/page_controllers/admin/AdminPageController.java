package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminPageController {

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("admin/admin");

        return modelAndView;
    }

}
