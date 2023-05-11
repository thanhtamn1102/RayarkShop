package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactPageController {

    @GetMapping("")
    public ModelAndView contactPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("shop/contact");

        return modelAndView;
    }

}
