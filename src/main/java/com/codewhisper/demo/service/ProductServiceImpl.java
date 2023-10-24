//create ProductServiceImpl to implement methods from ProductService

package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Product;
import java.util.List;

public class ProductServiceImpl implements  ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    //implement methods from ProductService
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    @Override
    public Product findById(int theId) {
        return productRepository.findById(theId);
    }
    
    @Override
    public void save(Product theProduct) {
        productRepository.save(theProduct);
    }
    
    @Override
    public void deleteById(int theId) {
        productRepository.deleteById(theId);
    }

}