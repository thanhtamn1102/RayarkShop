package vn.edu.iuh.fit.rayarkshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.rayarkshop.models.*;
import vn.edu.iuh.fit.rayarkshop.models.requests.ShippingAddressRequest;
import vn.edu.iuh.fit.rayarkshop.services.CustomerService;
import vn.edu.iuh.fit.rayarkshop.services.ShippingAddressService;
import vn.edu.iuh.fit.rayarkshop.utils.VnProvincesApiService;

import java.util.Map;

@RestController
@RequestMapping(name = "Shipping Address Controller", value = "/api/shipping-address")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VnProvincesApiService vnProvincesApiService;

    @GetMapping("/get")
    public ResponseEntity<?> getShippingAddress(@RequestParam int shippingAddressId) {
        ShippingAddress shippingAddress = shippingAddressService.getById(shippingAddressId);
        return ResponseEntity.ok(shippingAddress);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addShippingAddress(@RequestBody ShippingAddressRequest shippingAddressRequest) {
        try {
            Customer customer = customerService.getById(shippingAddressRequest.getCustomerId());
            Province province = vnProvincesApiService.getProvinceById(shippingAddressRequest.getProvinceCode()).execute().body();
            District district = vnProvincesApiService.getDistrictById(shippingAddressRequest.getDistrictCode()).execute().body();
            Ward ward = vnProvincesApiService.getWardById(shippingAddressRequest.getWardCode()).execute().body();

            ShippingAddress shippingAddress = new ShippingAddress(
                    customer,
                    shippingAddressRequest.getNameOfConsignee(),
                    shippingAddressRequest.getPhone(),
                    province.getCode(),
                    province.getName(),
                    district.getCode(),
                    district.getName(),
                    ward.getCode(),
                    ward.getName(),
                    shippingAddressRequest.getAddress()
            );

            ShippingAddress saved = shippingAddressService.saveShippingAddress(shippingAddress);

            return ResponseEntity.ok(saved);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShippingAddress(@RequestBody ShippingAddressRequest shippingAddressRequest) {
        try {
            ShippingAddress shippingAddress = shippingAddressService.getById(shippingAddressRequest.getShippingAddressId());

            if(shippingAddress != null) {
                Province province = null;
                District district = null;
                Ward ward = null;

                if(!shippingAddress.getProvinceCode().equals(shippingAddressRequest.getProvinceCode()))
                    province = vnProvincesApiService.getProvinceById(shippingAddressRequest.getProvinceCode()).execute().body();
                if(!shippingAddress.getDistrictCode().equals(shippingAddressRequest.getDistrictCode()))
                    district = vnProvincesApiService.getDistrictById(shippingAddressRequest.getDistrictCode()).execute().body();
                if(!shippingAddress.getWardCode().equals(shippingAddressRequest.getWardCode()))
                    ward = vnProvincesApiService.getWardById(shippingAddressRequest.getWardCode()).execute().body();

                if(province != null) {
                    shippingAddress.setProvinceCode(province.getCode());
                    shippingAddress.setProvinceName(province.getName());
                }

                if(district != null) {
                    shippingAddress.setDistrictCode(district.getCode());
                    shippingAddress.setDistrictName(district.getName());
                }

                if(ward != null) {
                    shippingAddress.setWardCode(ward.getCode());
                    shippingAddress.setWardName(ward.getName());
                }

                shippingAddress.setPhone(shippingAddressRequest.getPhone());
                shippingAddress.setAddress(shippingAddressRequest.getAddress());

                ShippingAddress saved = shippingAddressService.saveShippingAddress(shippingAddress);

                return ResponseEntity.ok(saved);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeShippingAddress(@RequestBody Map<String, Object> body) {
        int shippingAddressId = (int) body.get("shippingAddressId");
        int i = shippingAddressService.removeById(shippingAddressId);
        return ResponseEntity.ok(i == 1 ? Boolean.TRUE : Boolean.FALSE);
    }

}
