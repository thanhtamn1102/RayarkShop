package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.rayarkshop.models.ShippingAddress;

import java.util.List;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {

    List<ShippingAddress> getShippingAddressesByCustomerId(int customerId);

    @Transactional
    Integer removeByShippingAddressId(int shippingAddressId);

}
