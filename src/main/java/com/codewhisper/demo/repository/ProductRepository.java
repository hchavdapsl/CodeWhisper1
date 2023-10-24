// Create JPA ProductRepository

package com.codewhisper.demo.repository;

import com.codewhisper.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product, Integer> {

}