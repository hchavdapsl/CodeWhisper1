// Create Unit test for ProeductServiceImpl

package com.cg.product.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.cg.product.dto.Product;
import com.cg.product.exception.ProductException;
import com.cg.product.service.ProductServiceImpl;

@Test
public class ProductServiceTest {
	
	ProductServiceImpl productService;

    @Before
	public void setUp() throws Exception {
		productService = new ProductServiceImpl();
        
	}

	// Create unit test for findAll method
    @Test
    public void testFindAll() throws ProductException {
        	assertEquals(productService.findAll().size(), 4);
    }



}