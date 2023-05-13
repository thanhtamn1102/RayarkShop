package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorPageController {

    @GetMapping("")
    public ModelAndView errorPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("errorCode", "???");
        modelAndView.addObject("errorTitle", "ĐÃ CÓ LỖI XẢY RA");
        modelAndView.addObject("errorMessage", "Vui lòng thử lại sau");

        modelAndView.setViewName("error");

        return modelAndView;
    }

    @GetMapping("/access-denied")
    public ModelAndView accessDeniedPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("errorCode", 403);
        modelAndView.addObject("errorTitle", "ACCESS DENIED");
        modelAndView.addObject("errorMessage", "Bạn không có quyền truy cập vào tài nguyên này. Vui lòng đăng nhập bằng tài khoản có quyền và thử lại.");

        modelAndView.setViewName("error");

        return modelAndView;
    }

}
