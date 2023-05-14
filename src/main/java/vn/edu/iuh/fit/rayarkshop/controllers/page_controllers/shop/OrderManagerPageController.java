package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.SalesOrder;
import vn.edu.iuh.fit.rayarkshop.services.PersonService;
import vn.edu.iuh.fit.rayarkshop.services.SalesOrderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sales-orders")
public class OrderManagerPageController {

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private PersonService personService;

    @GetMapping("")
    public ModelAndView orderManagerPage() throws FirebaseAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();

        UserRecord user = FirebaseAuth.getInstance().getUser(uid);

        if(user == null)
            throw new NotFoundException("Not Found Exception");

        int customerId = personService.findByUid(uid).getId();

        List<SalesOrder> salesOrders = salesOrderService.findAllByCustomerId(customerId);
        List<SalesOrder> dsDonHangChoXacNhan = new ArrayList<>();
        List<SalesOrder> dsDonHangDangXuLy = new ArrayList<>();
        List<SalesOrder> dsDonHangDangGiao = new ArrayList<>();
        List<SalesOrder> dsDonHangDaGiao = new ArrayList<>();
        List<SalesOrder> dsDonHangDaHoanThanh = new ArrayList<>();
        List<SalesOrder> dsDonHangDaHuy = new ArrayList<>();

        if(salesOrders != null) {
            for(SalesOrder salesOrder : salesOrders) {
                switch (salesOrder.getStatus()) {
                    case CHO_XAC_NHAN:
                        dsDonHangChoXacNhan.add(salesOrder);
                        break;
                    case DANG_XU_LY:
                        dsDonHangDangXuLy.add(salesOrder);
                        break;
                    case DANG_GIAO:
                        dsDonHangDangGiao.add(salesOrder);
                        break;
                    case DA_GIAO:
                        dsDonHangDaGiao.add(salesOrder);
                        break;
                    case DA_HOAN_THANH:
                        dsDonHangDaHoanThanh.add(salesOrder);
                        break;
                    case DA_HUY:
                        dsDonHangDaHuy.add(salesOrder);
                }
            }
        } else {
            salesOrders = new ArrayList<>();
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("salesOrders", salesOrders);
        modelAndView.addObject("dsDonHangChoXacNhan", dsDonHangChoXacNhan);
        modelAndView.addObject("dsDonHangDangXuLy", dsDonHangDangXuLy);
        modelAndView.addObject("dsDonHangDangGiao", dsDonHangDangGiao);
        modelAndView.addObject("dsDonHangDaGiao", dsDonHangDaGiao);
        modelAndView.addObject("dsDonHangDaHoanThanh", dsDonHangDaHoanThanh);
        modelAndView.addObject("dsDonHangDaHuy", dsDonHangDaHuy);

        modelAndView.setViewName("shop/order_management");

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView searchOrder(@RequestParam String searchKey) throws FirebaseAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();

        UserRecord user = FirebaseAuth.getInstance().getUser(uid);

        if(user == null)
            throw new NotFoundException("Not Found Exception");

        int customerId = personService.findByUid(uid).getId();

        long orderId = -1;
        try {
            orderId = Long.parseLong(searchKey);
        } catch (Exception ex) {}

        List<SalesOrder> salesOrders = salesOrderService.findAllByCustomerId(customerId);
        List<SalesOrder> dsDonHangChoXacNhan = new ArrayList<>();
        List<SalesOrder> dsDonHangDangXuLy = new ArrayList<>();
        List<SalesOrder> dsDonHangDangGiao = new ArrayList<>();
        List<SalesOrder> dsDonHangDaGiao = new ArrayList<>();
        List<SalesOrder> dsDonHangDaHoanThanh = new ArrayList<>();
        List<SalesOrder> dsDonHangDaHuy = new ArrayList<>();

        List<SalesOrder> searchResult = new ArrayList<>();

        if(salesOrders != null) {
            for(SalesOrder salesOrder : salesOrders) {
                if(salesOrder.getId() == orderId
                    || salesOrder.getSalesOrderDetails()
                        .stream()
                        .filter(item -> item.getProduct().getName().toLowerCase().contains(searchKey.toLowerCase()))
                        .toList()
                        .size() > 0) {
                    searchResult.add(salesOrder);
                }

                switch (salesOrder.getStatus()) {
                    case CHO_XAC_NHAN:
                        dsDonHangChoXacNhan.add(salesOrder);
                        break;
                    case DANG_XU_LY:
                        dsDonHangDangXuLy.add(salesOrder);
                        break;
                    case DANG_GIAO:
                        dsDonHangDangGiao.add(salesOrder);
                        break;
                    case DA_GIAO:
                        dsDonHangDaGiao.add(salesOrder);
                        break;
                    case DA_HOAN_THANH:
                        dsDonHangDaHoanThanh.add(salesOrder);
                        break;
                    case DA_HUY:
                        dsDonHangDaHuy.add(salesOrder);
                }
            }
        } else {
            salesOrders = new ArrayList<>();
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("salesOrders", searchResult);
        modelAndView.addObject("dsDonHangChoXacNhan", dsDonHangChoXacNhan);
        modelAndView.addObject("dsDonHangDangXuLy", dsDonHangDangXuLy);
        modelAndView.addObject("dsDonHangDangGiao", dsDonHangDangGiao);
        modelAndView.addObject("dsDonHangDaGiao", dsDonHangDaGiao);
        modelAndView.addObject("dsDonHangDaHoanThanh", dsDonHangDaHoanThanh);
        modelAndView.addObject("dsDonHangDaHuy", dsDonHangDaHuy);

        modelAndView.setViewName("shop/order_management");

        return modelAndView;
    }

}
