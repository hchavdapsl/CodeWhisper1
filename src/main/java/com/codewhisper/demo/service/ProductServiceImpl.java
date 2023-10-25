//create ProductServiceImpl to implement methods from ProductService

package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Product;
import com.codewhisper.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements  ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product searchProduct(int id) {
        return null;
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    //implement methods from ProductService
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int theId) {
        return productRepository.findById(theId);
    }
    
    @Override
    public void save(Product theProduct) {
        productRepository.save(theProduct);
    }

    public void deleteById(int theId) {
        productRepository.deleteById(theId);
    }

}