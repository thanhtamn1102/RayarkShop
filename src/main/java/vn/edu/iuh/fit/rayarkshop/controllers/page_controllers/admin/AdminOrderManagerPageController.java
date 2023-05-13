package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.OrderStatus;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;
import vn.edu.iuh.fit.rayarkshop.services.SalesOrderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/order-manager")
public class AdminOrderManagerPageController {

    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping("")
    public ModelAndView orderManagerPage(@RequestParam(value = "page", defaultValue = "0") int pageIndex) {
        Page<SalesOrder> pageInfo = salesOrderService.findAll(PageRequest.of(pageIndex, 10));
        Page<SalesOrder> pageInfoChoXacNhan = salesOrderService.findAllByStatus(OrderStatus.CHO_XAC_NHAN, PageRequest.of(0, 10));
        Page<SalesOrder> pageInfoDangXuLy = salesOrderService.findAllByStatus(OrderStatus.DANG_XU_LY, PageRequest.of(0, 10));
        Page<SalesOrder> pageInfoDangGiao = salesOrderService.findAllByStatus(OrderStatus.DANG_GIAO, PageRequest.of(0, 10));
        Page<SalesOrder> pageInfoDaGiao = salesOrderService.findAllByStatus(OrderStatus.DA_GIAO, PageRequest.of(0, 10));
        Page<SalesOrder> pageInfoDaHoanThanh = salesOrderService.findAllByStatus(OrderStatus.DA_HOAN_THANH, PageRequest.of(0, 10));
        Page<SalesOrder> pageInfoDaHuy = salesOrderService.findAllByStatus(OrderStatus.DA_HUY, PageRequest.of(0, 10));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("pageInfoChoXacNhan", pageInfoChoXacNhan);
        modelAndView.addObject("pageInfoDangXuLy", pageInfoDangXuLy);
        modelAndView.addObject("pageInfoDangGiao", pageInfoDangGiao);
        modelAndView.addObject("pageInfoDaGiao", pageInfoDaGiao);
        modelAndView.addObject("pageInfoDaHoanThanh", pageInfoDaHoanThanh);
        modelAndView.addObject("pageInfoDaHuy", pageInfoDaHuy);

        modelAndView.setViewName("admin/order/order-manager");

        return modelAndView;
    }

    @GetMapping("/view")
    public ModelAndView orderDetailsPage(@RequestParam("id") long salesOrderId) {
        SalesOrder salesOrder = salesOrderService.findById(salesOrderId);

        if(salesOrder == null)
            throw new NotFoundException("Sales Order Not Found");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("salesOrder", salesOrder);

        modelAndView.setViewName("admin/order/view-order-details");

        return modelAndView;
    }

}
