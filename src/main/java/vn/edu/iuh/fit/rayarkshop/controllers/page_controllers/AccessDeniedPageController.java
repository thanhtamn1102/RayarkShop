package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedPageController {

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "/access-denied";
    }

}
