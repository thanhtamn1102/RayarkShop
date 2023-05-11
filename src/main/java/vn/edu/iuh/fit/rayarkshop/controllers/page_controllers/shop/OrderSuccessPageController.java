package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;
import vn.edu.iuh.fit.rayarkshop.services.SalesOrderService;

@Controller
@RequestMapping("/orders")
public class OrderSuccessPageController {

    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping("/success")
    public String orderSuccessPage(@RequestParam long id, Model model) {
        SalesOrder salesOrder = salesOrderService.findById(id);
        if(salesOrder == null)
            throw new RuntimeException("Sales Order Not Found");

        model.addAttribute("salesOrder", salesOrder);

        return "/shop/sales-order-success";
    }


}
