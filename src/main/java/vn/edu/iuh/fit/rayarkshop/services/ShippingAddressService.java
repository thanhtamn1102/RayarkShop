package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.ShippingAddress;

import java.util.List;

@Service
public interface ShippingAddressService {

    List<ShippingAddress> getShippingAddressesByCustomerId(int customerId);

    ShippingAddress saveShippingAddress(ShippingAddress shippingAddress);

    Integer removeById(int shippingAddressId);

    ShippingAddress getById(int shippingAddressId);

}
