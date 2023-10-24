//create ProductService

package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
	
	Product addProduct(Product product);
	Product updateProduct(Product product);
	Product searchProduct(int id);
	boolean deleteProduct(int id);
	List<Product> findAll();

    List<Product> getAllProducts();

	void save(Product theProduct);

	Optional<Product> findById(int productId);
}