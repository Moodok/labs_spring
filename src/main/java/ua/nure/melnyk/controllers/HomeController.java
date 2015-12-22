package ua.nure.melnyk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.nure.melnyk.entity.Product;
import ua.nure.melnyk.entity.User;
import ua.nure.melnyk.facade.MarketFacade;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private MarketFacade marketFacade;

    @Autowired
    private Validator productValidator;

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(productValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, UsernamePasswordAuthenticationToken principal) {
        User user = (User) principal.getPrincipal();
        List<Product> productsByUserId = marketFacade.getProductsByUserId(user.getId());
        model.addAttribute("userProducts", productsByUserId);
        if (!model.containsAttribute("product"))
            model.addAttribute("product", new Product());
        return "home";
    }

    @RequestMapping(path = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, path = "add-product")
    public String addProduct(@NotNull @Valid Product product, BindingResult result, RedirectAttributes attr) {

        attr.addFlashAttribute("org.springframework.validation.BindingResult.product", result);
        attr.addFlashAttribute("product", product);
        if (result.hasErrors())
            return "redirect:/";
        marketFacade.createProduct(product);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, path = "info/{id}")
    public String getProductInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", marketFacade.getProductById(id));
        return "info";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("product", new Product());
        mav.setViewName("info");
        return mav;
    }

}
