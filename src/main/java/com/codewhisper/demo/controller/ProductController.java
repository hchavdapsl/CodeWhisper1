//create springboot controller for products

package com.codewhisper.demo.controller;

import com.codewhisper.demo.entity.Cart;
import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.entity.User;
import com.codewhisper.demo.service.CartService;
import com.codewhisper.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;
	
    //create get mapping that retrieves all the products
	@GetMapping("/products")
	public String getProducts(Model model) {
		List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
	}

    //add post mapping for add-to-cart funtionality form thymleaf
    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(Model model, @RequestParam("productId") int productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //retrieve users cart from session
        Cart cart = (Cart) session.getAttribute("cart");
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
            session.setAttribute("cart", cart);
        }
        //redirect to products page
        return "redirect:/products";
    }    

}