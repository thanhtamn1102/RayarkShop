package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactPageController {

    @GetMapping("")
    public String contactPage() {
        return "shop/contact";
    }

}
