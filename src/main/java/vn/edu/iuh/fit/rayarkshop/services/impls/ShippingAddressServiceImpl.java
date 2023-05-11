package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.ShippingAddress;
import vn.edu.iuh.fit.rayarkshop.repositories.ShippingAddressRepository;
import vn.edu.iuh.fit.rayarkshop.services.ShippingAddressService;

import java.util.List;

@Component
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Override
    public List<ShippingAddress> getShippingAddressesByCustomerId(int customerId) {
        return shippingAddressRepository.getShippingAddressesByCustomerId(customerId);
    }

    @Override
    public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress) {
        return shippingAddressRepository.save(shippingAddress);
    }

    @Override
    public Integer removeById(int shippingAddressId) {
        return shippingAddressRepository.removeByShippingAddressId(shippingAddressId);
    }

    @Override
    public ShippingAddress getById(int shippingAddressId) {
        return shippingAddressRepository.findById(shippingAddressId).orElse(null);
    }
}
