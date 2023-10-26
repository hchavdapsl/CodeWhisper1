package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Cart;
import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.entity.User;
import com.codewhisper.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// add missing imports

// Creat CartServiceImpl which implements Cartservice
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public void save(Product product, User user) {
        cartRepository.save(new Cart(user.getName(), product.getId()));
    }
    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
    @Override
    public List<Cart> findByUsername(String username) {
        return cartRepository.findByUsername(username);
    }
    @Override
    public Cart findByUsernameAndProductid(String username, int productid) {
        return cartRepository.findByUsernameAndProductid(username, productid);
    }       

}