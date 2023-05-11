package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.models.Account;
import vn.edu.iuh.fit.rayarkshop.models.FavoriteProductListItem;
import vn.edu.iuh.fit.rayarkshop.services.AccountService;
import vn.edu.iuh.fit.rayarkshop.services.FavoriteProductListItemService;

import java.util.List;

@Controller
@RequestMapping("/accounts/favorite-product-list-manager")
public class FavoriteProductListPageController {

    @Autowired
    private FavoriteProductListItemService favoriteProductListItemService;

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ModelAndView favoriteProductListManagerPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();
        Account account = accountService.getAccountByUserNameOrEmail(usernameOrEmail);
        int customerId = account.getPerson().getId();

        List<FavoriteProductListItem> favoriteProductListItems = favoriteProductListItemService.getByCustomer(customerId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("favoriteProductListItems", favoriteProductListItems);

        modelAndView.setViewName("shop/favorite-product-list-manager");

        return modelAndView;
    }

}
