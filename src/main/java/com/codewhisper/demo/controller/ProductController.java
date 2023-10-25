//create springboot controller for products

package com.codewhisper.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

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
        if(user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            user = userService.findByEmail(name);
            session.setAttribute("user", user);
        }
        List<Cart> cartItems = cartService.findByUsername(user.getName());
        if(cartItems == null)
            cartItems = new ArrayList<>();

        model.addAttribute("cart", cartItems);
		List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
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
        if(product.isPresent()) {
            //add product to cart
            cartService.save(product.get(), user);
            //set cart in session
//            session.setAttribute("cart", cart);
        }
        //redirect to products page
        return "index";
    }    

}