//create springboot jpa entity for Cart with id, username, productid

package com.codewhisper.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    @Column(name="username")    
	private String username;
    @Column(name="productid")
	private int productid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}

	public Cart(String username, int productid) {
		this.username = username;
		this.productid = productid;
	}

	public Cart() {
	}
}