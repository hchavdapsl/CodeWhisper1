//create springboot controller for products

package com.codewhisper.demo.controller;

import com.codewhisper.demo.dto.CartDto;
import com.codewhisper.demo.entity.Cart;
import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.entity.User;
import com.codewhisper.demo.service.CartService;
import com.codewhisper.demo.service.ProductService;
import com.codewhisper.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    //create get mapping that retrieves all the products
    @GetMapping("/products/all")
    public String getProducts(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            user = userService.findByEmail(name);
            session.setAttribute("user", user);
        }
        List<Cart> cart = cartService.findByUsername(user.getName());
        model.addAttribute("cartSize", cart.size());
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/payment")
    public String payment(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            user = userService.findByEmail(name);
            session.setAttribute("user", user);
        }
        model.addAttribute("total", model.getAttribute("total"));
        return "payment";
    }

    @PostMapping("/products/makePayment")
    public String makePayment(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            user = userService.findByEmail(name);
            session.setAttribute("user", user);
        }
        List<Cart> cartItems = cartService.findByUsername(user.getName());
        for (Cart cart : cartItems) {
            cartService.delete(cart);
        }
        model.addAttribute("cartSize", 0);
        return "paymentsuccess";
    }

    @GetMapping("/products/view-cart")
    public String viewCart(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            user = userService.findByEmail(name);
            session.setAttribute("user", user);
        }
        List<Cart> cartItems = cartService.findByUsername(user.getName());
        List<CartDto> cart = new ArrayList<CartDto>();
        Double total = 0.0;
        if (cartItems != null && !cartItems.isEmpty()) {
            for (Cart cartItem : cartItems) {
                Optional<Product> product = productService.findById(cartItem.getProductid());
                if (product.isPresent()) {
                    Product prod = (Product) product.get();
                    cart.add(new CartDto(prod.getName(), prod.getDescription(), prod.getPrice()));
                    total += prod.getPrice();
                }
            }
        }
        model.addAttribute("cartItems", cart);
        model.addAttribute("total", total);
        return "shoppingcart";
    }

    //add post mapping for add-to-cart funtionality form thymleaf
    @PostMapping("/products/add-to-cart")
    public String addToCart(Model model, @RequestParam("productId") int productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //retrieve users cart from session
        Cart cart = (Cart) cartService.findByUsernameAndProductid(user, productId);
        //if cart is null, create a new cart
        if (cart == null) {
            cart = new Cart();
        }
        //retrieve product from product service
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {
            //add product to cart
            cartService.save(product.get(), user);
        }
        //redirect to products page
        return "index";
    }

}