package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/admin")
    public String adminPage() {
        return "/admin/admin";
    }

}
