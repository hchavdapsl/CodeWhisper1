//create ProductService

package com.codewhisper.demo.service;

import com.codewhisper.demo.entity.Product;
import java.util.List;

public interface ProductService {
	
	Product addProduct(Product product);
	Product updateProduct(Product product);
	Product searchProduct(int id);
	boolean deleteProduct(int id);
	List<Product> findAll();
	
}