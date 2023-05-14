package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.services.CustomerService;
import vn.edu.iuh.fit.rayarkshop.services.PersonService;
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
    private PersonService personService;

    @Autowired
    private VnProvincesApiService vnProvincesApiService;

    @GetMapping("")
    public ModelAndView addressManagerPage() throws FirebaseAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();

        UserRecord user = FirebaseAuth.getInstance().getUser(uid);

        if(user == null)
            throw new NotFoundException("Not Found Exception");

        int customerId = personService.findByUid(uid).getId();

        List<ShippingAddress> shippingAddresses = shippingAddressService.getShippingAddressesByCustomerId(customerId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("shippingAddresses", shippingAddresses);

        modelAndView.setViewName("shop/shipping-address-manager");

        return modelAndView;
    }

    @PostMapping("/remove")
    public ModelAndView removeAddress(@RequestParam(defaultValue = "1") int customerId,
                                @RequestParam int shippingAddressId) {
        int i = shippingAddressService.removeById(shippingAddressId);

        if(i == 0)
            throw new RuntimeException("Runtime Exception");

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
                             @RequestParam String address) throws FirebaseAuthException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();

        UserRecord user = FirebaseAuth.getInstance().getUser(uid);

        if(user == null)
            throw new NotFoundException("Not Found Exception");

        int customerId = personService.findByUid(uid).getId();

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

            ShippingAddress saved = shippingAddressService.saveShippingAddress(shippingAddress);

            if(saved == null)
                throw new RuntimeException("Runtime Exception");
        } catch (Exception ex) {
            throw new RuntimeException("Runtime Exception");
        }

        List<ShippingAddress> shippingAddresses = shippingAddressService.getShippingAddressesByCustomerId(customerId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("shippingAddresses", shippingAddresses);

        modelAndView.setViewName("shop/shipping-address-manager");

        return modelAndView;
    }

}
