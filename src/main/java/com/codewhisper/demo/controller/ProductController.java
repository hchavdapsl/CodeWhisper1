//create springboot controller for products

package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
	
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
        //retrieve users cart from session
        Cart cart = (Cart) session.getAttribute("cart");
        //if cart is null, create a new cart
        if (cart == null) {
            cart = new Cart();
        }
        //retrieve product from product service
        Product product = productService.getProductById(productId);
        //add product to cart
        cart.addProduct(product);
        //set cart in session
        session.setAttribute("cart", cart);
        //redirect to products page
        return "redirect:/products";
    }    

}