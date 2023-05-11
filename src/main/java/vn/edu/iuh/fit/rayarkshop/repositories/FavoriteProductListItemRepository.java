package vn.edu.iuh.fit.rayarkshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.rayarkshop.models.FavoriteProductListItem;

import java.util.List;

@Repository
public interface FavoriteProductListItemRepository extends JpaRepository<FavoriteProductListItem, Long> {

    Integer removeById(long id);

    List<FavoriteProductListItem> getFavoriteProductListItemsByCustomerId(int customerId);

    FavoriteProductListItem findByCustomerIdAndProductId(int customerId, int productId);

    @Transactional
    Integer removeByCustomerIdAndProductId(int customerId, int productId);

}
