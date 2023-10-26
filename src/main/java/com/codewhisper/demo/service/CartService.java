package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Cart;
import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.entity.User;

import java.util.List;
// Create CartService interface with methods save, delete, findByUsername and findByUsernameAndProductid
public interface CartService {
    voidsave(Product product, User user);
    Cart findByUsernameAndProductid(String username, int productid);
    List<Cart> findByUsername(String username);
    void delete(Cart cart);
}