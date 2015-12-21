package ua.nure.melnyk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.facade.MarketFacade;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private MarketFacade marketFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, UsernamePasswordAuthenticationToken principal) {
        User user = (User) principal.getPrincipal();
        List<Product> productsByUserId = marketFacade.getProductsByUserId(user.getId());
        model.addAttribute("userProducts", productsByUserId);

        return "home";
    }

    @RequestMapping(path = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


}
