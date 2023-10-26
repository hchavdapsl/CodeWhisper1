//create ProductServiceImpl to implement methods from ProductService

package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Implement ProductService interface
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Optional<Product> search(int id) {
        return productRepository.findById(id);
    }
    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }
}