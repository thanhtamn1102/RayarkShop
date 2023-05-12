package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;
import vn.edu.iuh.fit.rayarkshop.services.CustomerService;
import vn.edu.iuh.fit.rayarkshop.services.ShippingAddressService;
import vn.edu.iuh.fit.rayarkshop.utils.VnProvincesApiService;

import java.util.List;

@Controller
@RequestMapping("/accounts/shipping-addresses")
public class AddressManagerPageController {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VnProvincesApiService vnProvincesApiService;

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ModelAndView addressManagerPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);
        int customerId = account.getPerson().getId();

        List<ShippingAddress> shippingAddresses = shippingAddressService.getShippingAddressesByCustomerId(customerId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("shippingAddresses", shippingAddresses);

        modelAndView.setViewName("shop/shipping-address-manager");

        return modelAndView;
    }

    @PostMapping("/remove")
    public ModelAndView removeAddress(@RequestParam(defaultValue = "1") int customerId,
                                @RequestParam int shippingAddressId) {
        shippingAddressService.removeById(shippingAddressId);
        List<ShippingAddress> shippingAddresses = shippingAddressService.getShippingAddressesByCustomerId(customerId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("shippingAddresses", shippingAddresses);

        modelAndView.setViewName("shop/shipping-address-manager");

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addAddress(@RequestParam String nameOfConsignee,
                             @RequestParam String phone,
                             @RequestParam String provinceId,
                             @RequestParam String districtId,
                             @RequestParam String wardId,
                             @RequestParam String address) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);
        int customerId = account.getPerson().getId();

        Customer customer = customerService.getById(customerId);

        try {
            Province province = vnProvincesApiService.getProvinceById(provinceId).execute().body();
            District district = vnProvincesApiService.getDistrictById(districtId).execute().body();
            Ward ward = vnProvincesApiService.getWardById(wardId).execute().body();
            ShippingAddress shippingAddress = new ShippingAddress(
                    customer,
                    nameOfConsignee,
                    phone,
                    province.getCode(),
                    province.getName(),
                    district.getCode(),
                    district.getName(),
                    ward.getCode(),
                    ward.getName(),
                    address
            );

            shippingAddressService.saveShippingAddress(shippingAddress);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<ShippingAddress> shippingAddresses = shippingAddressService.getShippingAddressesByCustomerId(customerId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("shippingAddresses", shippingAddresses);

        modelAndView.setViewName("shop/shipping-address-manager");

        return modelAndView;
    }

}
