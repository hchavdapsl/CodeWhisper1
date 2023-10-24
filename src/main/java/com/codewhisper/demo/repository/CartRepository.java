
//create springboot JPA repository for the Cart entity

package com.example.demo.repository;

import com.example.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

//add missing imports

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}