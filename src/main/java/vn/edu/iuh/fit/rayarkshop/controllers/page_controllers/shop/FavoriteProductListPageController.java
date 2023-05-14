package vn.edu.iuh.fit.rayarkshop.controllers.page_controllers.shop;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.rayarkshop.exceptions.NotFoundException;
import vn.edu.iuh.fit.rayarkshop.models.FavoriteProductListItem;
import vn.edu.iuh.fit.rayarkshop.services.FavoriteProductListItemService;
import vn.edu.iuh.fit.rayarkshop.services.PersonService;

import java.util.List;

@Controller
@RequestMapping("/accounts/favorite-product-list-manager")
public class FavoriteProductListPageController {

    @Autowired
    private FavoriteProductListItemService favoriteProductListItemService;

    @Autowired
    private PersonService personService;

    @GetMapping("")
    public ModelAndView favoriteProductListManagerPage() throws FirebaseAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();

        UserRecord user = FirebaseAuth.getInstance().getUser(uid);

        if(user == null)
            throw new NotFoundException("Not Found Exception");

        int customerId = personService.findByUid(uid).getId();

        List<FavoriteProductListItem> favoriteProductListItems = favoriteProductListItemService.getByCustomer(customerId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("favoriteProductListItems", favoriteProductListItems);

        modelAndView.setViewName("shop/favorite-product-list-manager");

        return modelAndView;
    }

}
