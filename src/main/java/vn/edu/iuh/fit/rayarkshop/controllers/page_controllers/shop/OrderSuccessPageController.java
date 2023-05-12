package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;
import vn.edu.iuh.fit.rayarkshop.services.SalesOrderService;

@Controller
@RequestMapping("/orders")
public class OrderSuccessPageController {

    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping("/success")
    public ModelAndView orderSuccessPage(@RequestParam long id) {
        SalesOrder salesOrder = salesOrderService.findById(id);
        if(salesOrder == null)
            throw new RuntimeException("Sales Order Not Found");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("salesOrder", salesOrder);

        modelAndView.setViewName("shop/sales-order-success");

        return modelAndView;
    }


}
