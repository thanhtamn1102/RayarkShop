package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.ProductReview;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;
import vn.edu.iuh.fit.rayarkshop.models.requests.ProductReviewRequest;
import vn.edu.iuh.fit.rayarkshop.services.ProductReviewService;
import vn.edu.iuh.fit.rayarkshop.services.SalesOrderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderPageController {

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private ProductReviewService productReviewService;

    @GetMapping("")
    public ModelAndView orderPage(@RequestParam long salesOrderId) {
        SalesOrder salesOrder = salesOrderService.findById(salesOrderId);
        List<ProductReview> productReviews = new ArrayList<>();

        if(salesOrder != null) {
            productReviews = productReviewService.findProductReviewsBySalesOrderId(salesOrder.getId());
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("salesOrder", salesOrder);
        modelAndView.addObject("productReviews", productReviews);

        modelAndView.setViewName("shop/order");

        return modelAndView;
    }


}
