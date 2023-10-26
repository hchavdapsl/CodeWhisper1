package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Cart;
import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.entity.User;

import java.util.List;

public interface CartService {

    void save(Product product, User user);

    Cart findByUsernameAndProductid(User user, int productId);

    List<Cart> findByUsername(String username);

    void delete(Cart cart);
}
