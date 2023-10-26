//create ProductService

package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Product;

import java.util.List;
import java.util.Optional;

// Create ProductService interface with add, update, search, delete, findAll, save and findById

public interface ProductService {
    Product add(Product product);
    Product update(Product product);
    Optional<Product> search(int id);
    void delete(int id);
    List<Product> findAll();
    Product save(Product product);
    Optional<Product> findById(int id);
}