package vn.edu.iuh.fit.rayarkshop.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.rayarkshop.models.FavoriteProductListItem;
import vn.edu.iuh.fit.rayarkshop.repositories.FavoriteProductListItemRepository;
import vn.edu.iuh.fit.rayarkshop.services.FavoriteProductListItemService;

import java.util.List;

@Component
public class FavoriteProductListItemServiceImpl implements FavoriteProductListItemService {

    @Autowired
    private FavoriteProductListItemRepository favoriteProductListItemRepository;

    @Override
    public FavoriteProductListItem save(FavoriteProductListItem favoriteProductListItem) {
        return favoriteProductListItemRepository.save(favoriteProductListItem);
    }

    @Override
    public Integer removeById(long id) {
        return favoriteProductListItemRepository.removeById(id);
    }

    @Override
    public List<FavoriteProductListItem> getByCustomer(int customerId) {
        return favoriteProductListItemRepository.getFavoriteProductListItemsByCustomerId(customerId);
    }

    @Override
    public FavoriteProductListItem getFavoriteProductListItemByCustomerIdAndProductId(int customerId, int productId) {
        return favoriteProductListItemRepository.findByCustomerIdAndProductId(customerId, productId);
    }

    @Override
    public Integer removeByCustomerIdAndProductId(int customerId, int productId) {
        return favoriteProductListItemRepository.removeByCustomerIdAndProductId(customerId, productId);
    }
}
