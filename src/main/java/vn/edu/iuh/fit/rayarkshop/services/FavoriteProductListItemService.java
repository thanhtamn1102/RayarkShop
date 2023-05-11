package vn.edu.iuh.fit.rayarkshop.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.rayarkshop.models.FavoriteProductListItem;

import java.util.List;

@Service
public interface FavoriteProductListItemService {

    FavoriteProductListItem save(FavoriteProductListItem favoriteProductListItem);
    Integer removeById(long id);
    List<FavoriteProductListItem> getByCustomer(int customerId);

    FavoriteProductListItem getFavoriteProductListItemByCustomerIdAndProductId(int customerId, int productId);

    Integer removeByCustomerIdAndProductId(int customerId, int productId);

}
