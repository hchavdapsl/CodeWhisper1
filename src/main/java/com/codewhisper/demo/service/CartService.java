package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.entity.User;

public interface CartService {

    void save(Product product, User user);
}
